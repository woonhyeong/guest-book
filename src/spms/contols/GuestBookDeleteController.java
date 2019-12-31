package spms.contols;

import java.util.Map;

import dao.MySqlGuestBookDao;
import spms.bind.DataBinding;

public class GuestBookDeleteController implements Controller, DataBinding {
	MySqlGuestBookDao guestBookDao;

	public GuestBookDeleteController setGuestBookDao(MySqlGuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
		return this;
	}

	public Object[] getDataBinders() {
	    return new Object[]{
	        "no", Integer.class
	    };
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		guestBookDao.delete((Integer) model.get("no"));
		return "redirect:list.do";
	}

}
