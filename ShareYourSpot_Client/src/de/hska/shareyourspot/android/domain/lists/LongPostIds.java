package de.hska.shareyourspot.android.domain.lists;


import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "longPostIds")
public class LongPostIds {

	@ElementList(name = "postId")
	private List<Long> posts;
	
	public LongPostIds() {
		super();
	}

	public List<Long> getPosts() {
		return posts;
	}

	public void setPosts(List<Long> posts) {
		this.posts = posts;
	}
	
	public void setPosts(Long postId) {
		if(this.posts == null){
			this.posts = new ArrayList<Long>();
		}
		this.posts.add(postId);
	}
	
}



