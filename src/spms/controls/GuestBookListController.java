package spms.controls;

import java.util.HashMap;
import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlGuestBookDao;

@Component("/page/list.do")
public class GuestBookListController implements Controller, DataBinding{
	MySqlGuestBookDao guestBookDao;
	
	public GuestBookListController setGuestBookDao(MySqlGuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
			"orderCond", String.class	
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orderCond", model.get("orderCond"));
		
		model.put("guestBooks", guestBookDao.selectList(paramMap));
		return "/page/MemberList.jsp";
	}

}
