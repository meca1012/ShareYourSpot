package de.hska.shareyourspot.android.domain;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "comments")
public class Comments {

	@ElementList(inline = true)
	private ArrayList<Comment> comments;

	public Comments() {
		this.comments = new ArrayList<Comment>();
	}

	public Comments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public boolean isEmpty() {
		if (this.comments.size() == 0)
			return true;
		else
			return false;
	}

	public int size() {
		return this.comments.size();
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public void addCommentList(List<Comment> comments) {
		this.comments.addAll(comments);
	}

	public ArrayList<Comment> getAllComments() {
		return comments;
	}

	public void setUsers(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		String str = null;
		if (comments.size() == 0)
			str = "Es gibt keine Comments!";
		else {
			str = "Parties = [\n";
			for (Comment comment : comments) {
				str += (comment.getCommentId() + " " + comment.getText() + "\n");
			}
			str += "]";
		}
		return str;
	}

}
