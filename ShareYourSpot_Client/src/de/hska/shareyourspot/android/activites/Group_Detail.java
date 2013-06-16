package de.hska.shareyourspot.android.activites;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.helper.UserStore;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;

public class Group_Detail extends Activity {
	
	private UserStore uStore = new UserStore();
	private Context ctx = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group__detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group__detail, menu);
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
}