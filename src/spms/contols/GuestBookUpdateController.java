package spms.contols;

import java.util.Map;

import javax.servlet.ServletContext;

import dao.GuestBookDao;
import spms.vo.GuestBook;

public class GuestBookUpdateController implements Controller {
	GuestBookDao guestBookDao;

	public GuestBookUpdateController setGuestBookDao(GuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if (model.get("guestBook") == null) {
			model.put("guestBook", guestBookDao.selectOne((Integer) model.get("no")));
			return "/page/MemberUpdateForm.jsp";

		} else {

			GuestBook guestBook = (GuestBook) model.get("guestBook");
			if (guestBookDao.check(guestBook.getPassword(), guestBook.getNo())) {
				guestBookDao.update(guestBook);
				return "redirect:list.do";
			} else {
				return "/page/CheckFail.jsp";
			}
		}
	}
}
