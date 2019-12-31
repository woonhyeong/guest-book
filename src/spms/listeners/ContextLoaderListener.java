package spms.listeners;


import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import dao.MySqlGuestBookDao;
import spms.contols.GuestBookAddController;
import spms.contols.GuestBookDeleteController;
import spms.contols.GuestBookListController;
import spms.contols.GuestBookUpdateController;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	DataSource ds;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();
			
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/studydb");

			
			MySqlGuestBookDao guestBookDao = new MySqlGuestBookDao();
			guestBookDao.setDataSource(ds);
			
			sc.setAttribute("/page/list.do", new GuestBookListController().setGuestBookDao(guestBookDao));
			sc.setAttribute("/page/add.do", new GuestBookAddController().setGuestBookDao(guestBookDao));
			sc.setAttribute("/page/update.do", new GuestBookUpdateController().setGuestBookDao(guestBookDao));
			sc.setAttribute("/page/delete.do", new GuestBookDeleteController().setGuestBookDao(guestBookDao));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
