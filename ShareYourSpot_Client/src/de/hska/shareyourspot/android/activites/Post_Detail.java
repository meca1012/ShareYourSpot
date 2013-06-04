package de.hska.shareyourspot.android.activites;

import de.hska.shareyourspot.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Post_Detail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post__detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post__detail, menu);
		return true;
	}

}
