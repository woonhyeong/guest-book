package spms.contols;

import java.util.Map;

import dao.MySqlGuestBookDao;
import spms.vo.GuestBook;

public class GuestBookAddController implements Controller {
	MySqlGuestBookDao guestBookDao;
	
	public GuestBookAddController setGuestBookDao(MySqlGuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if (model.get("guestBook") == null) {
			return "/page/MemberForm.jsp";
		} else {
			GuestBook guestBook = (GuestBook)model.get("guestBook");
			guestBookDao.insert(guestBook);
			
			return "redirect:list.do";
		}
	}

}