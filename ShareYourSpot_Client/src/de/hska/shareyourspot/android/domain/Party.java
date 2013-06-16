package de.hska.shareyourspot.android.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Attribute;


public class Party implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7408353739310692920L;

	private Long partyId;

	private String name;

	private List<User> usersInParty;

	private List<Post> postsOfParty;
	
	private Date created;

	private Date modified;
	
	public Party() {
	}
	
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsersInParty() {
		return usersInParty;
	}

	public void setUsersInParty(List<User> usersInParty) {
		this.usersInParty = usersInParty;
	}

	public List<Post> getPostsOfParty() {
		return postsOfParty;
	}

	public void setPostsOfParty(List<Post> postsOfParty) {
		this.postsOfParty = postsOfParty;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
}
