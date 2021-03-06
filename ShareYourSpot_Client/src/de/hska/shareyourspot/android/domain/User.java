package de.hska.shareyourspot.android.domain;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import de.hska.shareyourspot.android.helper.IdListHelper;

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
	private Long created;
	
	@Element(required=false)
	private Long modified;
	
	@Element(required=false)
	private String userInParty;

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
	public Long getCreated() {
		return created;
	}
	public void setCreated(Long created) {
		this.created = created;
	}

	public List<Long> getPartiesOfUser() {
		return IdListHelper.StringToIdList(this.userInParty);
	}

	public void setPartiesOfUser(List<Long> partiesOfUser) {
		this.userInParty = IdListHelper.ListToString(partiesOfUser);
	}	

	@Override
	public String toString() {
		return "UserId=" + userId + ", email=" + email + ", name=" + name;
	}
	
}
