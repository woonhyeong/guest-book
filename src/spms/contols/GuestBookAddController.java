package spms.contols;

import java.util.Map;

import dao.MySqlGuestBookDao;
import spms.bind.DataBinding;
import spms.vo.GuestBook;

public class GuestBookAddController implements Controller, DataBinding {
	MySqlGuestBookDao guestBookDao;

	public GuestBookAddController setGuestBookDao(MySqlGuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
		return this;
	}

	public Object[] getDataBinders() {
		return new Object[] { "guestBook", spms.vo.GuestBook.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		GuestBook guestBook = (GuestBook)model.get("guestBook");
		
		if (guestBook.getEmail() == null) {
			return "/page/MemberForm.jsp";
		} else {
			guestBookDao.insert(guestBook);

			return "redirect:list.do";
		}
	}

}
