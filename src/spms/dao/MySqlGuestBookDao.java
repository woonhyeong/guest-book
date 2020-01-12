package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.vo.GuestBook;

@Component("GuestBookDao")
public class MySqlGuestBookDao implements GuestBookDao {
	SqlSessionFactory sqlSessionFactory;
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	public List<GuestBook> selectList(HashMap<String,Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.GuestBookDao.selectList", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	public int insert(GuestBook guestBook) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.GuestBookDao.insert", guestBook);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	public GuestBook selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.GuestBookDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
	}
	
	public int delete(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("spms.dao.GuestBookDao.delete", no);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	public int update(GuestBook guestBook) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("spms.dao.GuestBookDao.update", guestBook);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	public boolean check(String password, int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			String searchedPassword = sqlSession.selectOne("spms.dao.GuestBookDao.check", no);
	
			if (password.equals(searchedPassword)) {
				return true;
			} else { 
				return false;
			}
		} finally {
			sqlSession.close();
		}
		
	}
}
