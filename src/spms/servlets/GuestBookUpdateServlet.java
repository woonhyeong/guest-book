package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MySqlGuestBookDao;
import spms.vo.GuestBook;

@WebServlet("/page/update")
@SuppressWarnings("serial")
public class GuestBookUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			ServletContext sc = this.getServletContext();			
			MySqlGuestBookDao guestBookDao = (MySqlGuestBookDao)sc.getAttribute("guestBookDao");
			request.setAttribute("guestBook", guestBookDao.selectOne(Integer.parseInt(request.getParameter("no"))));
			request.setAttribute("viewUrl", "/page/MemberUpdateForm.jsp");
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			ServletContext sc = this.getServletContext();
			MySqlGuestBookDao guestBookDao = (MySqlGuestBookDao)sc.getAttribute("guestBookDao");
			
			GuestBook guestBook = (GuestBook)request.getAttribute("guestBook");
			if (guestBookDao.check(guestBook.getPassword(), guestBook.getNo())) {
				guestBookDao.update(guestBook);
				request.setAttribute("viewUrl", "redirect:list.do");
			} else {
				request.setAttribute("viewUrl", "/page/CheckFail.jsp");
			}
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
