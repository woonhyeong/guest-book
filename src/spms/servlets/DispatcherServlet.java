package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.GuestBook;

@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String servletPath = request.getServletPath();

		try {
			String pageControllerPath = null;

			if ("/page/list.do".equals(servletPath)) {
				pageControllerPath = "/page/list";
			} else if ("/page/add.do".equals(servletPath)) {
				pageControllerPath = "/page/add";
				if (request.getParameter("email") != null) {
					request.setAttribute("guestBook", new GuestBook().setEmail(request.getParameter("email"))
							.setPassword(request.getParameter("password")).setContent(request.getParameter("content")));
				}
			} else if ("/page/update.do".equals(servletPath)) {
				pageControllerPath = "/page/update";
				if (request.getParameter("content") != null) {
					request.setAttribute("guestBook", new GuestBook().setNo(Integer.parseInt(request.getParameter("no")))
							.setContent(request.getParameter("content")).setPassword(request.getParameter("password")));
				}
			} else if ("/page/delete.do".equals(servletPath)) {
				pageControllerPath = "/page/delete";
			} else if ("/auth/login.do".equals(servletPath)) {
				pageControllerPath = "/auth/login";
			} else if ("/auth/logout.do".equals(servletPath)) {
				pageControllerPath = "/auth/logout";
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
			rd.include(request, response);
			
			String viewUrl = (String)request.getAttribute("viewUrl");
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}
