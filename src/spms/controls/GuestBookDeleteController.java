package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlGuestBookDao;

@Component("/page/delete.do")
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
