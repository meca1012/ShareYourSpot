package de.hska.shareyourspot.android.activites;

import java.util.ArrayList;
import java.util.List;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.Picture;
import de.hska.shareyourspot.android.domain.Post;
import de.hska.shareyourspot.android.domain.Posts;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.UserStore;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PostList extends Activity {
	private UserStore uStore = new UserStore();
	private Context ctx = this;
	private List<Post> posts;
	private List<String> postsTitle;
	public final String postId = "postId";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_post_list);
			// __ MOCK
			
			Posts posts = new Posts();
			Post post1 = new Post("Post 1", new Picture(),new Party());
			Post post2 = new Post("Post 2", new Picture(),new Party());
			Post post3 = new Post("Post 3", new Picture(),new Party());
			Post post4 = new Post("Post 4", new Picture(),new Party());
			posts.addPost(post1);
			posts.addPost(post2);
			posts.addPost(post3);
			posts.addPost(post4);
		
			// __

			// TODO: add users to listUsers on UI
			this.posts = new ArrayList<Post>();
		
			this.posts.addAll(posts.getAllPosts());
			
			this.postsTitle = new ArrayList<String>();
			
			for (Post post : this.posts) {
				this.postsTitle.add(post.getText());
				}
			
			
			ListAdapter listenAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, this.postsTitle);

			ListView listUsers = (ListView) findViewById(R.id.list);
			listUsers.setAdapter(listenAdapter);

			listUsers.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					startPostDetail(position);
				}
			});

			
	}

	public void startPostDetail(int id)
	{
		Intent intent = new Intent(this, Post_Detail.class);
		intent.putExtra(postId, id);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  switch (item.getItemId()) {
	  case R.id.action_logout:
		  	uStore.logout(ctx);
	        finish();       
	        break;
	        
	  case R.id.action_groups:
		  showGroups();
	        finish();
	    break;
	    
	  case R.id.action_new_post:
		  newPostCreate();
	        finish();
	    break;

	  default:
	    break;
	  }

	  return true;
	} 
	
	public void newPostCreate() {
		Intent intent = new Intent(this, NewPost.class);
		startActivity(intent);
	}
	
	public void showGroups() {
		Intent intent = new Intent(this, Groups.class);
		startActivity(intent);
	}
	
}
