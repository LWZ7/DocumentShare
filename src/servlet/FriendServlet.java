package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Document;
import bean.Friend;
import bean.User;
import util.Page;

/**
 * Servlet implementation class FriendServlet
 */
@WebServlet("/FriendServlet")
public class FriendServlet extends BaseBackServlet {
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		String i = request.getParameter("id");
		if(i.equals("")) {
			return "@admin_friend_addFail";
		}
		int fid = Integer.parseInt(i);

		User user = (User)request.getSession().getAttribute("user");
		Friend friend = new Friend();
		friend.setUid(fid);
		friend.setFid(user.id);
		friend.setVerify(0);
		friend.setRequestName(user.name);
		friendDAO.add(friend);
		
		return "@admin_friend_addRequest";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		
		Friend friend = friendDAO.get(id);
		
		int uid = friend.uid;
		int fid = friend.fid;
		
		friendDAO.delete(uid , fid);
		friendDAO.delete(fid , uid);
		
		return "@admin_friend_list";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		return null;
	}
	
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("user");

		int uid = user.getId();
		int verify = 1;//已经过认证
		List<Friend> friends = friendDAO.list(page.getStart(),page.getCount(), uid , verify);
        int total = friendDAO.getTotal(uid);
		page.setTotal(total);
		
		request.setAttribute("page", page);
        request.setAttribute("friends", friends);
		
		return "admin/listFriend.jsp";
	}
	
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		
		User user = (User)request.getSession().getAttribute("user");
		
		int id = Integer.parseInt(request.getParameter("id"));
		friendDAO.update(id);
		
		Friend friend = friendDAO.get(id);
		
		
		int uid = friend.fid;
		int fid = friend.uid;
		
		friend.setUid(uid);
		friend.setFid(fid);
		friend.setVerify(1);
		friend.setRequestName(user.name);
		
		friendDAO.add(friend);
		
		return "@admin_friend_verifyList";
	}
	
	public String listfile(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("user");
		int uid = user.id;
		int fid = (int)request.getSession().getAttribute("fid");
		List<Document> Documents = privilegeDAO.list(page.getStart(), page.getCount(), uid, fid);
		//List<Document> Documents = fileDAO.list(page.getStart(),page.getCount(), fid);
		
        //int total = fileDAO.getTotal(fid);
		int total = Documents.size();
		page.setTotal(total);
		
		request.setAttribute("page", page);
        request.setAttribute("Documents", Documents);
		
		return "admin/listFriendFile.jsp";
	}
	
	public String getFid(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Friend friend = friendDAO.get(id);
		int fid = friend.fid;
		HttpSession seesion = request.getSession();
		seesion.setAttribute("fid", fid);
		return "@admin_friend_listfile";
	}
	
	public String find(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		return "admin/searchFriend.jsp";
	}
	
	public String get(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		
		String name = request.getParameter("name");
		User user = userDAO.get(name);
		List<User> users = new ArrayList<User>();
		users.add(user);
		int total = users.size();
		page.setTotal(total);
		request.setAttribute("page", page);
        request.setAttribute("users", users);
		return "admin/listUser.jsp";
	}
	
	public String addFail(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		return "admin/addFail.jsp";
	}
	
	public String addRequest(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		return "admin/addRequest.jsp";
	}
	
	public String verifyList(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("user");
		int uid = user.getId();
		int verify = 0;//未经过认证
		List<Friend> friends = friendDAO.list(page.getStart(),page.getCount(), uid , verify);
        int total = friendDAO.getTotal(uid);
		page.setTotal(total);
		
		request.setAttribute("page", page);
        request.setAttribute("Friends", friends);
		
		return "admin/newsList.jsp";
	}
	
	public String refuse(HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		
		friendDAO.delete(id);
		return "@admin_friend_verifyList";
	}

}
