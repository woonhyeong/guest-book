package spms.contols;

import java.util.Map;

import dao.MySqlGuestBookDao;

public class GuestBookDeleteController implements Controller {
	MySqlGuestBookDao guestBookDao;

	public GuestBookDeleteController setGuestBookDao(MySqlGuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		guestBookDao.delete((Integer) model.get("no"));
		return "redirect:list.do";
	}

}
