package de.hska.shareyourspot.android.domain;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="posts")
public class Posts {
	
		@ElementList(inline=true)
		private ArrayList<Post> posts;

		public Posts() {
			posts = new ArrayList<Post>();
		}
		public Posts(ArrayList<Post> posts) {
			this.posts = posts;
		}

		public boolean isEmpty() {
			if (posts.size() == 0)
				return true;
			else
				return false;
		}

		public int size() {
			return posts.size();
		}

		public void addPost(Post post) {
			posts.add(post);
		}

		public void addPostList(List<Post> postList) {
			posts.addAll(postList);
		}

		public ArrayList<Post> getAllPosts() {
			return posts;
		}
		public void setPosts(ArrayList<Post> posts) {
			this.posts = posts;
		}

		@Override
		public String toString() {
			String str = null;
			if (posts.size() == 0)
				str = "Es gibt keinen Post!";
			else {
				str = "Posts = [\n";
				for (Post post : posts) {
					str += (post.getPostId() + " " + post.getText() + "\n");
				}
				str += "]";
			}
			return str;
		}

}
