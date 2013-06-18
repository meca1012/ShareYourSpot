package de.hska.shareyourspot.android.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "party")
public class Party implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7408353739310692920L;

	@Element(required=false)
	private Long partyId;
	
	@Element(required=false)
	private String name;

	@ElementList(required=false, name="userInParties")
	private List<User> userInParty;
	
	@ElementList(name = "usersInParty", required=false)
	private List<Long> userIdsInParty;
	
	public List<Long> getUserIdsInParty() {
		return this.userIdsInParty;
	}
	
	public void setUserIdsInParty(List<Long> userIdsInParty) {
		this.userIdsInParty = userIdsInParty;
	}

	@ElementList(required=false)
	private List<Post> postsOfParty;
	
	@Element(required=false)
	private Long created;

	@Element(required=false)
	private Long modified;
	
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

	public List<User> getUserInParty() {
		return userInParty;
	}

	public void setUserInParty(List<User> userInParty) {
		this.userInParty = userInParty;
	}

	public List<Post> getPostsOfParty() {
		return postsOfParty;
	}

	public void setPostsOfParty(List<Post> postsOfParty) {
		this.postsOfParty = postsOfParty;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Long getModified() {
		return modified;
	}

	public void setModified(Long modified) {
		this.modified = modified;
	}
	
}
