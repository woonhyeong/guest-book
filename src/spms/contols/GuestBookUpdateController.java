package spms.contols;

import java.util.Map;

import javax.servlet.ServletContext;

import dao.MySqlGuestBookDao;
import spms.bind.DataBinding;
import spms.vo.GuestBook;

public class GuestBookUpdateController implements Controller, DataBinding {
	MySqlGuestBookDao guestBookDao;

	public GuestBookUpdateController setGuestBookDao(MySqlGuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] { "no", Integer.class, "guestBook", spms.vo.GuestBook.class}; 
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		GuestBook guestBook = (GuestBook)model.get("guestBook");
		
		if (guestBook.getEmail() == null) {
			model.put("guestBook", guestBookDao.selectOne((Integer) model.get("no")));
			return "/page/MemberUpdateForm.jsp";

		} else {
			if (guestBookDao.check(guestBook.getPassword(), guestBook.getNo())) {
				guestBookDao.update(guestBook);
				return "redirect:list.do";
			} else {
				return "/page/CheckFail.jsp";
			}
		}
	}
}
