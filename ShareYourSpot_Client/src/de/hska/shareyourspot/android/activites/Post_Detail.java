package de.hska.shareyourspot.android.activites;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Post;
import de.hska.shareyourspot.android.helper.AlertHelper;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;
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
	private RestClient restClient = new RestClient();
	private Post post; 

	public final String postId = "postId";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		long postIdent = getIntent().getLongExtra(postId, 0);
		setContentView(R.layout.activity_post_detail);
		this.post = this.restClient.getPost(postIdent);
		
		if(this.post != null)
		{
		TextView spotterName = (TextView) findViewById(R.id.textView_spotter);
		spotterName.setText(this.post.getCreatedByUser().getName());
		
		TextView spotText = (TextView) findViewById(R.id.textView_spot_text);
		spotText.setText(this.post.getText());
		}
		else
		{
			new AlertHelper(ctx, R.string.dataLoadFailureTitle,
					R.string.dataLoadFailureText, "Next Try").fireAlert();
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_detail, menu);
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
	    
	  case R.id.action_postlist:
			toPostList();
			finish();
			break;
	    
	  			
		case R.id.action_new_post:
			toNewPost();
			finish();
			break;
	   
	  default:
	    break;
	  }

	  return true;
	} 
	
		
	public void toNewPost() {
		Intent intent = new Intent(this, NewPost.class);
		startActivity(intent);
	}
	
	 public void toPostList() {
			Intent intent = new Intent(this, PostList.class);
			startActivity(intent);
		}
	 
			
	    	
	
	public void toGroups() {
		Intent intent = new Intent(this, Groups.class);
		startActivity(intent);
	}

}
