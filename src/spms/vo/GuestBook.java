package spms.vo;

import java.util.Date;

public class GuestBook {
	protected int 		no;
	protected String 	email;
	protected String 	password;
	protected String 	content;
	protected Date		createdDate;
	protected Date		modifiedDate;
	
	public int getNo() {
		return no;
	}
	public GuestBook setNo(int no) {
		this.no = no;
		return this;
	}
		
	public String getEmail() {
		return email;
	}
	public GuestBook setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	public GuestBook setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public String getContent() {
		return content;
	}
	public GuestBook setContent(String content) {
		this.content = content;
		return this;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public GuestBook setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public GuestBook setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
}
