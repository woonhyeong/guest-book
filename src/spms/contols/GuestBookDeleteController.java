package spms.contols;

import java.util.Map;

import dao.GuestBookDao;

public class GuestBookDeleteController implements Controller {
	GuestBookDao guestBookDao;

	public GuestBookDeleteController setGuestBookDao(GuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		guestBookDao.delete((Integer) model.get("no"));
		return "redirect:list.do";
	}

}
