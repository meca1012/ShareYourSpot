package de.hska.shareyourspot.android.domain;

import java.util.Date;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "user")
public class User {
	
	@Attribute(required=false)
	private Long id;

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
	
	public User(){}
	
	public User(String nEmail, String nName, String nPassword){
		this.email = nEmail;
		this.name = nName; 
		this.password = nPassword;
		
	}
	
	public Long getId() {
		return id;
	}
	public void setUserId(Long userId) {
		this.id = userId;
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
	
	@Override
	public String toString() {
		return "UserId=" + id + ", email=" + email + ", name=" + name;
	}
	
	
}
