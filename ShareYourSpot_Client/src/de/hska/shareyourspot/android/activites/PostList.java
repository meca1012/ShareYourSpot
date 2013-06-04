package de.hska.shareyourspot.android.activites;

import de.hska.shareyourspot.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class PostList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_list, menu);
		return true;
	}

	public void newPostCreate(View view) {
		Intent intent = new Intent(this, NewPost.class);
		startActivity(intent);
	}
}
