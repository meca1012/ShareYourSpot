package de.hska.shareyourspot.android.domain.lists;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LongPostIds")
@XmlAccessorType(FIELD)
public class LongPostIds {

	@XmlElement(name = "postId")
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
}



