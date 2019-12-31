package spms.servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MySqlGuestBookDao;
import spms.vo.GuestBook;

@WebServlet("/page/add")
@SuppressWarnings("serial")
public class GuestBookAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("viewUrl", "/page/MemberForm.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			MySqlGuestBookDao guestBookDao = (MySqlGuestBookDao)sc.getAttribute("guestBookDao");
			
			GuestBook guestBook = (GuestBook)request.getAttribute("guestBook");
			guestBookDao.insert(guestBook);
			
			request.setAttribute("viewUrl", "redirect:list.do");
			
		} catch (Exception e) {
			throw new ServletException(e);
		} 
	}
}
