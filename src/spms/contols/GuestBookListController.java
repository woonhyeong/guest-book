package spms.contols;

import java.util.Map;

import dao.GuestBookDao;

public class GuestBookListController implements Controller{
	GuestBookDao guestBookDao;
	
	public GuestBookListController setGuestBookDao(GuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("guestBooks", guestBookDao.selectList());
		return "/page/MemberList.jsp";
	}

}
