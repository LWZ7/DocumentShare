package filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import bean.User;

/**
 * Servlet Filter implementation class MemberFilter
 */
@WebFilter("/MemberFilter")
public class MemberFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MemberFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        String contextPath=request.getServletContext().getContextPath();
	 
	        String[] noNeedAuthPage = new String[]{
	                "add",
	                "get",
	                "login.jsp",
	                "register.jsp",
	                "findPassword.jsp",
	                "room.jsp"};
	        
	        //System.out.println("这里是memberfilter");
	        String uri = request.getRequestURI();
	        //System.out.println(uri);///DocumentShare/login.jsp
	        uri =StringUtils.remove(uri, contextPath);
	        //System.out.println(uri);///login.jsp
	        
	        String method = StringUtils.substringAfterLast(uri,"/");
	        //System.out.println(method);
	        
	        if(!uri.startsWith("/admin_user_")&&!Arrays.asList(noNeedAuthPage).contains(method)){
	        	//System.out.println("hello");
                User user =(User) request.getSession().getAttribute("user");
                if(null==user) {
                    response.sendRedirect("login.jsp");
                    return;
                }
	        }
	        chain.doFilter(request, response);
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
