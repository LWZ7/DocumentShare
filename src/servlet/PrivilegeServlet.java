package servlet;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import util.Page;

/**
 * Servlet implementation class PrivilegeServlet
 */
@WebServlet("/PrivilegeServlet")
public class PrivilegeServlet extends BaseBackServlet {
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("user");
		int uid = user.id;
		String [] fids = request.getParameterValues("permission");
		int did = Integer.parseInt(request.getParameter("id"));
		
		//如果获取的permission是null，说明用户在该文件上没有设置权限，也就意味着所有人都不能看
		if(fids==null) {
			//获取该文件原来有权限查看的朋友的id
			ArrayList old_permission_friendId = privilegeDAO.listFid(page.getStart(), page.getCount(), uid, did);
			for (int i = 0; i < old_permission_friendId.size(); i++) {
				privilegeDAO.update(uid , old_permission_friendId.get(i) , did);
			}
			return "@admin_file_list";
		}
		
		//新设置的查看该文件的朋友们的Id
		ArrayList new_permission_friendsId = super.StringToInt(fids);
		//获取该文件原来有权限查看的朋友的id
		ArrayList old_permission_friendId = privilegeDAO.listFid(page.getStart(), page.getCount(), uid, did);
		//遍历旧权限数组，如果旧数组的元素不在新数组里面的话，更新该元素的权限
		for (int i = 0; i < old_permission_friendId.size(); i++) {
			if(!new_permission_friendsId.contains(old_permission_friendId.get(i))) {
				privilegeDAO.update(uid , old_permission_friendId.get(i) , did);
			}
		}
		//遍历新数组，如果新数组的元素不在旧数组里面的话，往数据库插入一条数据
		for (int i = 0; i < new_permission_friendsId.size(); i++) {
			if(!new_permission_friendsId.contains(old_permission_friendId)) {
				privilegeDAO.add(uid , new_permission_friendsId.get(i) , did);
			}
		}
		return "@admin_file_list";
	}
	
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		return null;
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		return null;
	}
	
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		return null;
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		
		return null;
	}
}
