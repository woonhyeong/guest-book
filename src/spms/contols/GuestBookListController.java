package spms.contols;

import java.util.Map;

import dao.GuestBookDao;

public class GuestBookListController implements Controller{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		GuestBookDao guestBookDao = (GuestBookDao)model.get("guestBookDao");
		model.put("guestBooks", guestBookDao.selectList());
		return "/page/MemberList.jsp";
	}

}
