package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GuestBookDao;

@WebServlet("/page/delete")
@SuppressWarnings("serial")
public class GuestBookDeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ServletContext ctx = this.getServletContext();
			GuestBookDao guestBookDao = (GuestBookDao)ctx.getAttribute("guestBookDao");
			
			guestBookDao.delete(Integer.parseInt(request.getParameter("no")));
			request.setAttribute("viewUrl", "redirect:list.do");
			
		} catch (Exception e) {
			throw new ServletException(e);
		} 
	}
}
