package de.hska.shareyourspot.android.domain;

import java.io.Serializable;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import de.hska.shareyourspot.android.helper.Constants;
import de.hska.shareyourspot.android.helper.IdListHelper;

@Root(name = "comment")
public class Comment implements Constants, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6645513518498674717L;

	@Element(required = false)
	private Long commentId = KEINE_ID;

	@Element(required = false)
	private Long postId;

	@Element(required = false)
	private String text;
	
	@Element(required = false)
	private String createdByUsername;

	@Element(required = false)
	private Long created;

	@Element(required = false)
	private Long modified;
	
	@Element(required=false)
	private Double rating;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	
	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getCreatedByUsername() {
		return createdByUsername;
	}

	public void setCreatedByUsername(String createdByUsername) {
		this.createdByUsername = createdByUsername;
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

}
