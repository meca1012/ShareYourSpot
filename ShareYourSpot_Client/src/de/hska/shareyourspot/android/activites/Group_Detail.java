package de.hska.shareyourspot.android.activites;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.helper.UserStore;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Group_Detail extends Activity {
	
	private UserStore uStore = new UserStore();
	private Context ctx = this;
	public final String groupName = "groupName";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_detail);
		String group = getIntent().getStringExtra(this.groupName);
		
		TextView groupN = (TextView) findViewById(R.id.textView_groupname);
		groupN.setText(group);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_detail, menu);
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
	
	public void joinGroup(){
//		TODO: implement joinGroup in Rest
		System.out.println("implement me");
	}
}
