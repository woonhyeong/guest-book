package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.dao.MySqlGuestBookDao;

@Component("/page/list.do")
public class GuestBookListController implements Controller{
	MySqlGuestBookDao guestBookDao;
	
	public GuestBookListController setGuestBookDao(MySqlGuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("guestBooks", guestBookDao.selectList());
		return "/page/MemberList.jsp";
	}

}
