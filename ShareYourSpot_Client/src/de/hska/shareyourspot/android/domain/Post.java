package de.hska.shareyourspot.android.domain;

import java.io.Serializable;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import de.hska.shareyourspot.android.helper.Constants;


@Root
public class Post implements Constants, Serializable {

	private static final long serialVersionUID = -7769905910137170288L;

	@Element(required=false)
	private Long postId = KEINE_ID;

	@Element(required=false)
	private User createdByUser;
	
	@Element(required=false)
	private Party postedInParty;

	@Element(required=false)
	private Long picture;

	@Element(required=false)
	private String text;
	
	//private List<Comment> comments;

	@Element(required=false)
	private Long created;

	@Element(required=false)
	private Long modified;

	@Element(required=false)
	// TODO: add mapping
	private Party party;

	public Post(String text,Party group) {
		this.text = text;
		this.party = group;
	}
	
	public Post()
	{}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getPicture() {
		return picture;
	}

	public void setPicture(Long picture) {
		this.picture = picture;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	// public Picture getPicture() {
	// return picture;
	// }
	// public void setPicture(Picture picture) {
	// this.picture = picture;
	// }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

}
