package de.hska.shareyourspot.android.activites;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.R.layout;
import de.hska.shareyourspot.android.R.menu;
import de.hska.shareyourspot.android.helper.UserStore;
import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class AndroidTabLayoutActivity extends TabActivity{
	
	private UserStore uStore = new UserStore();
	private Context ctx = this;
	private TabHost tabHost; 
	private String tabIndexString = "tabIndex";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_tab_layout);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		tabHost = getTabHost();
        
        // Tab for Postlist
        TabSpec postspec = tabHost.newTabSpec("Post List");
        // setting Title and Icon for the Tab
        postspec.setIndicator("Post List",getResources().getDrawable(R.drawable.postlist));
        Intent postIntent = new Intent(this, PostList.class);
        postspec.setContent(postIntent);
        tabHost.getTabWidget().setBackgroundColor(Color.parseColor("#f7f9f6"));
         
        // Tab for Groups
        TabSpec groupspec = tabHost.newTabSpec("Groups");        
        groupspec.setIndicator("Groups",getResources().getDrawable(R.drawable.group));
        Intent groupIntent = new Intent(this, Groups.class);
        groupspec.setContent(groupIntent);
         
        // Tab for Newpost
        TabSpec newspec = tabHost.newTabSpec("New Post");
        newspec.setIndicator("New Post",getResources().getDrawable(R.drawable.camera));
        Intent newIntent = new Intent(this, NewPost.class);
        newspec.setContent(newIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(postspec); // Adding photos tab
        tabHost.addTab(groupspec); // Adding songs tab
        tabHost.addTab(newspec); // Adding videos tab
        
        int tabIndexInt = getIntent().getIntExtra(tabIndexString, 0);
        this.tabHost.setCurrentTab(tabIndexInt);
        
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.android_tab_layout, menu);	
				
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
	
