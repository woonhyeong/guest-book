package spms.contols;

import java.util.Map;

import dao.GuestBookDao;
import spms.vo.GuestBook;

public class GuestBookAddController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if (model.get("guestBook") == null) {
			return "/page/MemberForm.jsp";
		} else {
			GuestBookDao guestBookDao = (GuestBookDao)model.get("guestBookDao");
			
			GuestBook guestBook = (GuestBook)model.get("guestBook");
			guestBookDao.insert(guestBook);
			
			return "redirect:list.do";
		}
	}

}
