package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.PrivilegeDao;
import dao.FriendDao;
import dao.FileDao;
import dao.UserDAO;
import util.Email;
import util.Page;

@MultipartConfig
public abstract class BaseBackServlet extends HttpServlet {

	public abstract String add(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException ;
	public abstract String delete(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException ;
	public abstract String edit(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException ;
	public abstract String update(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException ;
	public abstract String list(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException ;
	
	
	protected PrivilegeDao privilegeDAO = new PrivilegeDao();
	protected FriendDao friendDAO = new FriendDao();
	protected FileDao fileDAO = new FileDao();
	protected UserDAO userDAO = new UserDAO();
	//protected Email email = new Email();

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try{
			/*获取分页信息*/
			int start= 0;
			int count = 5;
			try {
				start = Integer.parseInt(request.getParameter("page.start"));
			} catch (Exception e){
				//e.printStackTrace();
			}
			try {
				count = Integer.parseInt(request.getParameter("page.count"));
			} catch (Exception e) {
				//e.printStackTrace();
			}
			Page page = new Page(start,count);
			//如果start和count是空值，就默认start=0,count=5

			/*借助反射，调用对应的方法*/
			String method = (String) request.getAttribute("method");

//			System.out.println(method);
			// 获取这个名字叫做method，参数类型是http.HttpServletRequest , HttpServletResponse , Page的方法
			Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
					javax.servlet.http.HttpServletResponse.class,Page.class);//this是父对象
			//调用方法m
			String redirect = m.invoke(this,request, response,page).toString();
//			System.out.println("hhhh");
//			System.out.println(redirect);
			
			/*根据方法的返回值，进行相应的客户端跳转，服务端跳转，或者仅仅是输出字符串*/
			
			if(redirect.startsWith("@"))
				response.sendRedirect(redirect.substring(1));
			else if(redirect.startsWith("%"))
				response.getWriter().print(redirect.substring(1));
			else
				request.getRequestDispatcher(redirect).forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//上传文件的函数，将请求中上传的文件传入输入流InputStream
	public InputStream parseUpload(HttpServletRequest request, Map<String, String> params) {
		InputStream is =null;
		try {
            DiskFileItemFactory factory = new DiskFileItemFactory();//获取FileItem的创建工厂FileItemFactory
            // 设置上传文件的大小限制为1M
            //factory.setSizeThreshold(1024 * 10240);
            ServletFileUpload upload = new ServletFileUpload(factory);//将FileItem的创建工厂实例传入到ServletFileUpload实例中进行初始化
    
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();//96行-99行，从请求reqeust中将上传的二进制文件转换为FileItem对象
                if (!item.isFormField()) {//如果item不是表单
                    // item.getInputStream() 获取上传文件的输入流
            		String filename = item.getName();
            		//System.out.println(filename);
            		String contentType = item.getContentType();
            		//System.out.println(contentType);
            		params.put(filename, contentType);
                    is = item.getInputStream();
                } else {
                	String paramName = item.getFieldName();
                	//System.out.println(paramName);
                	String paramValue = item.getString();
                	//System.out.println(paramValue);
                	paramValue = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8");
                	params.put(paramName, paramValue);
                }
            }	             
        } catch (Exception e) {
            e.printStackTrace();
        }
		return is;
	}
	public ArrayList StringToInt(String[] arrs){
		ArrayList beans = new ArrayList();
		
		for (int i = 0 ; i < arrs.length ; i++) {
			beans.add(Integer.parseInt(arrs[i]));
		}
		return beans;
	}
	
}
