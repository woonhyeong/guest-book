package spms.servlets;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import dao.MemberDao;

@WebServlet("/page/list")
@SuppressWarnings("serial")
public class MemberListServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {	
		try {
			ServletContext sc = this.getServletContext();			
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
						
			request.setAttribute("members", memberDao.selectList());
			request.setAttribute("viewUrl", "/page/MemberList.jsp");
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
