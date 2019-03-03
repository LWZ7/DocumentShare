package servlet;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.HtmlUtils;

import bean.User;
import util.Email;
import util.Page;

public class UserServlet extends BaseBackServlet {
	
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		String num = request.getParameter("name");
		String passward = request.getParameter("password");
		String email_address = request.getParameter("email");
		User user = userDAO.get(num);
		if (user==null) {
			user = new User();
			user.setName(num);
			user.setPassword(passward);
			user.setEmail_address(email_address);
			userDAO.add(user);
			request.setAttribute("msg", "注册成功！");
			return "login.jsp";
		}else {
			request.setAttribute("msg", "用户名已经被使用,不能使用");
            return "register.jsp"; 
		}
	};
	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		return null;
	}
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		return null;
	};
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		return null;
	};
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		return null;
	};
	
	public String get(HttpServletRequest request, HttpServletResponse response, Page page) {
		String num = request.getParameter("num");
		String passward = request.getParameter("passward");
		
		return null;
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response, Page page) {
	    String name = request.getParameter("name");
	    name = HtmlUtils.htmlEscape(name);
	    String password = request.getParameter("password");    
	     
	    User user = userDAO.get(name,password);
	      
	    if(null==user){
	        request.setAttribute("msg", "账号或密码错误");
	        return "login.jsp";
	    }
	    request.getSession().setAttribute("user", user);
	    return "@index.jsp";
	}  
	
	public String logout(HttpServletRequest request, HttpServletResponse response, Page page) {
		if(request.getSession().getAttribute("user")!=null) {
			request.getSession().invalidate();
		}
		return "login.jsp";
	}
	
	public String sendPasswordTo (HttpServletRequest request, HttpServletResponse response, Page page) throws GeneralSecurityException{
		String name = request.getParameter("name"); 
		String emai = request.getParameter("email");
		User user = userDAO.getUser(name, emai);
		if (user!=null) {
			String subject = user.getName()+"的密码";
			String content = user.getName()+"您好！您的密码是"+user.getPassword();
			try {
				Email.sendEmail(emai, subject, content);
			}catch(Exception e) {
				request.setAttribute("msg", "用户名或注册邮箱错误");
				return "login.jsp";
			}
			request.setAttribute("msg", "邮件发送成功！");
			return "login.jsp";
		}else {
			request.setAttribute("msg", "用户名或注册邮箱错误");
			return "login.jsp";
		}
	}
}
