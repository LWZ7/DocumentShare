package servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.core.env.SystemEnvironmentPropertySource;

import bean.Document;
import bean.Friend;
import bean.User;
import util.Page;
import util.DateUtil;

/**
 * Servlet implementation class FileServlet
 * 大问题，文件的基本信息和文件本身是两个不同的东西，两个列表不能同时用一个<c:foreach>搞定
 * 或许可以把文件本身作为document.java的属性之一？
 * 可以把文件的url放进数据库中
 * 
 * 我需不需要在数据库里面保存文件路径呢？好像不需要，因为jsp源码中的href指向的是有下载功能的那个servlet所在的路径，
 * 下载哪个文件根据附带参数filename决定的，如果这个文件在上传文件夹里面的话，就可以通过流的方式把文件下载下来。
 * 这时候要解决的问题是如果不同的人上传同名字的文件，该怎么区分哪个文件是我的，哪个文件是他的。
 * 这时候一个解决方法是 fileName = file.id + fileName，
 * 这个文件名是用户不能改的，用户可以改的那个文件名是呈现在页面上的
 * 我需不需要专门设置一个realfileName呢？
 * 我可不可以用file.id代替上传到文件夹的文件名呢？
 */

//@WebServlet("/FileServlet")
@MultipartConfig
public class FileServlet extends BaseBackServlet {

	//上传文件，并且把文件基本信息添加到数据库
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException {
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
		String fileName = null;
		String type = null;
		for(String s:params.keySet()) {
			fileName = s;
		}
		//System.out.println(fileName);
		type = params.get(fileName);

		String lastName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		//System.out.println(lastName);
		Date d = DateUtil.today();
		User user = (User)request.getSession().getAttribute("user");
		int uid = user.getId();

		String ownerName = user.name;
		
		Document doc = new Document();
		doc.setDate(d);
		doc.setFileName(fileName);
		doc.setUid(uid);
		doc.setType(lastName);
		doc.setUserName(ownerName);
		//添加文件基本信息
		Document fi = fileDAO.add(doc);
		String RealFileName = fi.id+"."+fi.type;
		fi.setRealName(RealFileName);
		fileDAO.updateRealFileName(fi);
		//System.out.println(this.getServletContext().getRealPath("fileFolder"));
		System.out.println(request.getSession().getServletContext().getRealPath("fileFolder"));
		File fileFolder= new File(request.getSession().getServletContext().getRealPath("fileFolder"));
		File file = new File(fileFolder,fi.getRealName());
		
		try {
			if(null!=is && 0!=is.available()){
			    try(FileOutputStream fos = new FileOutputStream(file)){
			        byte b[] = new byte[1024 * 1024];
			        int length = 0;
			        while (-1 != (length = is.read(b))) {
			            fos.write(b, 0, length);
			        }
			        fos.flush();	
			        System.out.println("上传成功");
			    }
			    catch(Exception e){
			    	e.printStackTrace();
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return "@admin_file_list";
	}
	
	
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException {
		
		String fileName = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		
		Document doc = new Document();
		doc.setFileName(fileName);
		doc.setId(id);
		fileDAO.update(doc);
		
		return "@admin_file_list";

	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		fileDAO.delete(id);
		privilegeDAO.delete(id);
		
		return "@admin_file_list";
	}
	
	//呈现的时候，怎么把文件基本信息跟文件本身同时在同一行中呢？
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");

		int uid = user.getId();
		List<Document> Documents = fileDAO.list(page.getStart(),page.getCount(), uid);
		List<Friend> friends = friendDAO.list(page.getStart(),page.getCount(), uid);
        int total = fileDAO.getTotal(uid);
		page.setTotal(total);
		
		request.setAttribute("page", page);
        request.setAttribute("Documents", Documents);
        request.setAttribute("friends", friends);
		
		return "admin/listFile.jsp";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Document doc = fileDAO.get(id);
		request.setAttribute("doc", doc);
		return "admin/editFile.jsp";
	}
	
	public String download(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException {
		
		String fileName = request.getParameter("filename");
		//System.out.println("这里是fileservlet download");
		//System.out.println(fileName);
		//fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
		
		String path = request.getSession().getServletContext().getRealPath("fileFolder");
		File file = new File(path + "/" + fileName);
		
		if(!file.exists()) {
			fileDAO.delete(fileName);
			//HttpSession seesion = request.getSession();
			request.setAttribute("message", "下载的资源已删除！！");
			//seesion.setAttribute("msg", "下载的资源已删除！！");
			return "message.jsp";
		}
		String realname = fileName.substring(fileName.indexOf("_")+1);
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
		FileInputStream in = new FileInputStream(path + "/" + fileName);
		OutputStream out = response.getOutputStream();
		byte b[] = new byte[1024];
		int len = -1;
		while((len = in.read(b))!=-1) {
			out.write(b,0,len);
		}
		in.close();
		out.close();
		return "@admin_file_list";
	}	
}
