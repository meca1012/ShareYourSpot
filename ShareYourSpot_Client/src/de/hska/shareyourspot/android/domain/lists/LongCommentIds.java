package de.hska.shareyourspot.android.domain.lists;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LongCommentIds")
@XmlAccessorType(FIELD)
public class LongCommentIds {

	@XmlElement(name = "commentId")
	private List<Long> comments;
	
	public LongCommentIds() {
		super();
	}

	public List<Long> getComments() {
		return comments;
	}

	public void setComments(List<Long> comments) {
		this.comments = comments;
	}
}



