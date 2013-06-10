package de.hska.shareyourspot.android.domain;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;
import de.hska.shareyourspot.android.helper.Constants;
import de.hska.shareyourspot.android.domain.Picture;

public class Post implements Constants, Serializable {

	private static final long serialVersionUID = -7769905910137170288L;

	private Long postId = KEINE_ID;
	private User createdByUser;
	private Party postedInParty;
	private byte[] previewImage;
	private String previewImageType;
	private Picture picture;
	private String text;
	//private List<Comment> comments;
	private Date created;
	private Date modified;

	// TODO: add mapping
	private Party party;

	public Post(String text, Picture pic, Party Group) {
		this.picture = pic;
		this.text = text;
		this.party = party;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	public byte[] getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(byte[] previewImage) {
		this.previewImage = previewImage;
	}

	public String getPreviewImageType() {
		return previewImageType;
	}

	public void setPreviewImageType(String previewImageType) {
		this.previewImageType = previewImageType;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
