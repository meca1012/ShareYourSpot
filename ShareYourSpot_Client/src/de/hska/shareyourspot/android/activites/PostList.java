package de.hska.shareyourspot.android.activites;

import java.util.List;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Post;
import de.hska.shareyourspot.android.helper.UserStore;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PostList extends Activity {
	private UserStore uStore = new UserStore();
	private Context ctx = this;
	private List<Post> posts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_list);
		
//		Load the latest posts and show the last 3
		
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


	  default:
	    break;
	  }

	  return true;
	} 
	
	public void newPostCreate(View view) {
		Intent intent = new Intent(this, NewPost.class);
		startActivity(intent);
	}
	
	
	
	public void showGroups(View view) {
		Intent intent = new Intent(this, Groups.class);
		startActivity(intent);
	}
	
}

