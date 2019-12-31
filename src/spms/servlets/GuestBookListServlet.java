package spms.servlets;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import dao.GuestBookDao;

@WebServlet("/page/list")
@SuppressWarnings("serial")
public class GuestBookListServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {	
		try {
			ServletContext sc = this.getServletContext();			
			GuestBookDao guestBookDao = (GuestBookDao)sc.getAttribute("guestBookDao");
						
			request.setAttribute("guestBooks", guestBookDao.selectList());
			request.setAttribute("viewUrl", "/page/MemberList.jsp");
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
