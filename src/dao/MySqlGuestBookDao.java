package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.GuestBook;

@Component("GuestBookDao")
public class MySqlGuestBookDao implements GuestBookDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	public List<GuestBook> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select MNO, EMAIL, CONTENT, CRE_DATE, MOD_DATE from GUESTBOOKS order by MNO DESC");
			
			ArrayList<GuestBook> guestBooks = new ArrayList<GuestBook>();
			
			while(rs.next()) {
				guestBooks.add(new GuestBook().setNo(rs.getInt("MNO"))
						.setEmail(rs.getString("EMAIL"))
						.setContent(rs.getString("CONTENT"))
						.setCreatedDate(rs.getDate("CRE_DATE"))
						.setModifiedDate(rs.getDate("MOD_DATE")));
			}
			
			return guestBooks;
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection!=null) connection.close();} catch(Exception e) {}
		}
	}
	
	public int insert(GuestBook guestBook) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("insert into GUESTBOOKS(EMAIL, PWD, CONTENT, CRE_DATE, MOD_DATE)"
					+ " VALUES(?, ?, ?, now(), now())");
			stmt.setString(1, guestBook.getEmail());
			stmt.setString(2, guestBook.getPassword());
			stmt.setString(3, guestBook.getContent());
			
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection!=null) connection.close();} catch(Exception e) {}
		}
	}
	
	public int delete(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			
			return stmt.executeUpdate("delete from GUESTBOOKS where MNO="
					+ no);
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection!=null) connection.close();} catch(Exception e) {}
		}
	}
	
	public GuestBook selectOne(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select MNO, EMAIL,CONTENT, CRE_DATE from GUESTBOOKS where MNO="
					+ no);
			rs.next();
			
			return new GuestBook().setNo(rs.getInt("MNO")).setEmail(rs.getString("EMAIL")).setContent(rs.getString("CONTENT")).setCreatedDate(rs.getDate("CRE_DATE"));
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection!=null) connection.close();} catch(Exception e) {}
		}
	}
	
	public int update(GuestBook guestBook) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("update GUESTBOOKS set CONTENT=?, MOD_DATE=now()"
					+ " where MNO=?");
			stmt.setString(1, guestBook.getContent());
			stmt.setInt(2, guestBook.getNo());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection!=null) connection.close();} catch(Exception e) {}
		}
	}
	
	public boolean check(String password, int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select PWD from GUESTBOOKS where MNO="+no);
			rs.next();
			
			if(rs.getString("PWD").equals(password)){
				return true;
			} else return false;
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection!=null) connection.close();} catch(Exception e) {}
		}
	}
	
	public GuestBook exist(String email, String password) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("select EMAIL, PWD from GUESTBOOKS where EMAIL=? and PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new GuestBook().setPassword(rs.getString("PWD"))
						.setEmail(rs.getString("EMAIL"));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection!=null) connection.close();} catch(Exception e) {}
		}

	}
}
