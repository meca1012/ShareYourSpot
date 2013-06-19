package de.hska.shareyourspot.android.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import android.provider.SyncStateContract.Helpers;

import de.hska.shareyourspot.android.domain.lists.LongPartyIds;
import de.hska.shareyourspot.android.domain.lists.LongPostIds;
import de.hska.shareyourspot.android.helper.IdListHelper;

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
	
	@Element(required=false)
	private String usersInParty;
	
	@Element(required=false)
	private String postsOfParty;

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

	public List<Long> getUsersInParty() {
		return IdListHelper.StringToIdList(this.usersInParty);
	}

	public void setUsersInParty(List<Long> usersInParty) {
		this.usersInParty = IdListHelper.ListToString(usersInParty);;
	}

	public void addUserToParty(Long id)
	{
		this.usersInParty = IdListHelper.AddSingleIdToString(this.usersInParty, id);;
	}
		
	public List<Long> getPostsOfParty() {
		return IdListHelper.StringToIdList(this.postsOfParty);
	}

	public void setPostsOfParty(List<Long> postsOfParty) {
		this.postsOfParty = IdListHelper.ListToString(postsOfParty);
	}
	
	public void addPostToParty(Long id)
	{
		this.postsOfParty = IdListHelper.AddSingleIdToString(this.postsOfParty, id);;
	}
	
		
}
