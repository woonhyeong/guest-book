package spms.contols;

import java.util.Map;

import javax.servlet.ServletContext;

import dao.GuestBookDao;
import spms.vo.GuestBook;

public class GuestBookUpdateController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {

		GuestBookDao guestBookDao = (GuestBookDao) model.get("guestBookDao");

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
