package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.vo.Member;

public class MemberDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	public List<Member> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select MNO, MNAME, EMAIL, CONTENT, CRE_DATE, MOD_DATE from MEMBERS order by MNO DESC");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while(rs.next()) {
				members.add(new Member().setNo(rs.getInt("MNO"))
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"))
						.setContent(rs.getString("CONTENT"))
						.setCreatedDate(rs.getDate("CRE_DATE"))
						.setModifiedDate(rs.getDate("MOD_DATE")));
			}
			
			return members;
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection!=null) connection.close();} catch(Exception e) {}
		}
	}
	
	public int insert(Member member) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("insert into MEMBERS(EMAIL, PWD, MNAME, CONTENT, CRE_DATE, MOD_DATE)"
					+ " VALUES(?, ?, ?, ?, now(), now())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			stmt.setString(4, member.getContent());
			
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
			
			return stmt.executeUpdate("delete from MEMBERS where MNO="
					+ no);
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection!=null) connection.close();} catch(Exception e) {}
		}
	}
	
	public Member selectOne(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select MNO, EMAIL, MNAME, CONTENT, CRE_DATE from MEMBERS where MNO="
					+ no);
			rs.next();
			
			return new Member().setNo(rs.getInt("MNO")).setEmail(rs.getString("EMAIL")).setName(rs.getString("MNAME")).setContent(rs.getString("CONTENT")).setCreatedDate(rs.getDate("CRE_DATE"));
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection!=null) connection.close();} catch(Exception e) {}
		}
	}
	
	public int update(Member member) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("update MEMBERS set EMAIL=?, MNAME=?, CONTENT=?, MOD_DATE=now()"
					+ " where MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getContent());
			stmt.setInt(4, member.getNo());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection!=null) connection.close();} catch(Exception e) {}
		}
	}
	
	public Member exist(String email, String password) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("select EMAIL, MNAME from MEMBERS where EMAIL=? and PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Member().setName(rs.getString("MNAME"))
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
