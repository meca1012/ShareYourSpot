package de.hska.shareyourspot.android.domain.lists;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "longCommentIds")
public class LongCommentIds {

	@Element(name = "commentId")
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
	
	public void setComments(Long commentId) {
		if(this.comments == null){
			this.comments = new ArrayList<Long>();
		}
		this.comments.add(commentId);
	}
	
}



