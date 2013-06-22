package de.hska.shareyourspot.android.domain;

import java.io.Serializable;
import java.util.List;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import de.hska.shareyourspot.android.helper.Constants;
import de.hska.shareyourspot.android.helper.IdListHelper;


@Root(name="post")
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
	
	@Element(required=false)
	private double longitude;
	
	@Element(required=false)
	private double latitude;
	
	@Element(required=false)
	private String commentsOfPost;

	@Element(required=false)
	private Long created;

	@Element(required=false)
	private Long modified;
	
	@Element(required=false)
	private Double totalRating;

	@Element(required=false)
	// TODO: add mapping - 22.06.2013 is this really needed here?
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}
	
	public List<Long> getCommentsOfPost() {
		return IdListHelper.StringToIdList(this.commentsOfPost);
	}

	public void setCommentsOfPost(List<Long> commentsOfPost) {
		this.commentsOfPost = IdListHelper.ListToString(commentsOfPost);
	}
	
	public void addCommentToPost(Long id)
	{
		this.commentsOfPost = IdListHelper.AddSingleIdToString(this.commentsOfPost, id);
	}

	public Double getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(Double totalRating) {
		this.totalRating = totalRating;
	}

}
