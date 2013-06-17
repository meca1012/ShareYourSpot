package de.hska.shareyourspot.android.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "user")
public class User {
	
	@Element(required=false)
	private Long userId;
	
	@Element(required=false)
	private boolean userApproved;
	
	@Element(required=false)
	private String email;
	
	@Element(required=false)
	private String name;
	
	@Element(required=false)
	private String password;
	
	@Element(required=false)
	private Date created;
	
	@Element(required=false)
	private Date modified;
	
	@ElementList(required=false)
	private List<Long> partyIds;

	public User(){}
	
	public User(String nEmail, String nName, String nPassword){
		this.email = nEmail;
		this.name = nName; 
		this.password = nPassword;
		
	}
	public boolean isUserApproved() {
		return userApproved;
	}

	public void setUserApproved(boolean userApproved) {
		this.userApproved = userApproved;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public List<Long> getPartyIds() {
		return this.partyIds;
	}
	
	public void setPartyIds(List<Long> partyIds) {
		this.partyIds = partyIds;
	}
	
	@Override
	public String toString() {
		return "UserId=" + userId + ", email=" + email + ", name=" + name;
	}
	
	
}
