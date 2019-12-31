package spms.contols;

import java.util.Map;

import dao.GuestBookDao;

public class GuestBookDeleteController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		GuestBookDao guestBookDao = (GuestBookDao)model.get("guestBookDao");
		guestBookDao.delete((Integer)model.get("no"));
		return "redirect:list.do";
	}

}
