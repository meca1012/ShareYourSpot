package de.hska.shareyourspot.android.activites;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.helper.UserStore;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Post_Detail extends Activity {
	
	private UserStore uStore = new UserStore();
	private Context ctx = this;

	public final String postId = "postId";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int groupNr = getIntent().getIntExtra(postId, 0);
		setContentView(R.layout.activity_post_detail);
		TextView spotText = (TextView) findViewById(R.id.textView_spot_text);
		spotText.setText("POST NR. "  + groupNr  + " was tiped");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.post__detail, menu);
//		return true;
//	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  switch (item.getItemId()) {
	  case R.id.action_logout:
		  	uStore.logout(ctx);
	        finish();
	    break;
	    
	  case R.id.action_groups:
		  	toGroups();
	        finish();
	    break;

	  default:
	    break;
	  }

	  return true;
	} 
	
		
	public void newGroup(View view) {
		Intent intent = new Intent(this, Friends.class);
		startActivity(intent);
	}
	
	public void newPostCreate(View view) {
		Intent intent = new Intent(this, NewPost.class);
		startActivity(intent);
	}
	
	public void showPostList(View view) {
		Intent intent = new Intent(this, PostList.class);
		startActivity(intent);
	}
	
	public void toGroups() {
		Intent intent = new Intent(this, Groups.class);
		startActivity(intent);
	}

}
