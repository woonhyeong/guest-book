package dao;

import java.util.List;

import spms.vo.GuestBook;

public interface GuestBookDao {
	List<GuestBook> selectList() throws Exception;
	int insert(GuestBook guestBook) throws Exception;
	int delete(int no) throws Exception;
	GuestBook selectOne(int no) throws Exception;
	int update(GuestBook guestBook) throws Exception;
	public boolean check(String password, int no) throws Exception;
//	public GuestBook exist(String email, String password) throws Exception;
}
