package de.hska.shareyourspot.android.activites;

import java.io.IOException;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.R.layout;
import de.hska.shareyourspot.android.R.menu;
import de.hska.shareyourspot.android.domain.Comment;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

public class AddComment extends Activity {

	public final String postId = "postId";
	private long postIdent;
	private UserStore uStore = new UserStore();
	private Context ctx = this;
	private RestClient restClient = new RestClient();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.postIdent = getIntent().getLongExtra(postId, 0);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_add_comment);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_comment, menu);
		return true;
	}
	
	public void backToPost(View view){
		Intent intent = new Intent(this, Post_Detail.class);
		intent.putExtra(postId, this.postIdent);
		startActivity(intent);
	}
	
	public void addComment(View view){
		EditText editText = (EditText) findViewById(R.id.commentText);
		Comment comment = new Comment();
		comment.setText(editText.getText().toString());
		comment.setPostId(this.postIdent);
		
		RatingBar ratingBar= (RatingBar)findViewById(R.id.comment_Rating);
		Double dbl = (double) ratingBar.getRating();
		comment.setRating(dbl);
		
		User user = null;
		try {
			user = uStore.getUser(ctx);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comment.setCreatedByUsername(user.getName());
		
		this.restClient.addComment(comment);
		
		editText.setText("");
		ratingBar.setRating(0);
		Intent intent = new Intent(this, Post_Detail.class);
		intent.putExtra(this.postId, postIdent);
		recreate();
		startActivity(intent);
				
	}

}
