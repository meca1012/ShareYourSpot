package de.hska.shareyourspot.android.viewpager;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.activites.Friends;
import de.hska.shareyourspot.android.activites.Groups;
import de.hska.shareyourspot.android.activites.PostList;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TabHost;


public class ViewPagerMainActivity extends FragmentActivity{
	private TabHost mTabHost;
	private ViewPager  mViewPager;
	private TabsAdapter mTabsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.viewpager_layout);
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		mViewPager = (ViewPager)findViewById(R.id.pager);
		//Create our tab adapter
		mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);

		//add our tabs to the adapter
		mTabsAdapter.addTab(mTabHost.newTabSpec("friends").setIndicator("Friends"),
				Friends.class, null);
		mTabsAdapter.addTab(mTabHost.newTabSpec("groups").setIndicator("Groups"),
				Groups.class, null);
		mTabsAdapter.addTab(mTabHost.newTabSpec("postlist").setIndicator("PostList"),
				PostList.class, null);

		if (savedInstanceState != null) {
			//restore the last selected tab if we can
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("tab", mTabHost.getCurrentTabTag());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbarmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		super.onOptionsItemSelected(item);
//
//		switch(item.getItemId()){
//		case R.id.logout:
//			showConfirmationDialog();
//			break;
//		case R.id.settings:
//			startActivity(new Intent(this, Settings.class));
//			break;
//		}
//		return true;
//	}

//	private void showConfirmationDialog() {
//		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//			// set title
//			alertDialogBuilder.setTitle("Möchten Sie sich wirklich abmelden?");
//			// set dialog message
//			alertDialogBuilder
//				.setCancelable(false)
//				.setPositiveButton("Ja",new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog,int id) {
//						startActivity(new Intent(ViewPagerMainActivity.this, MainActivity.class));
//					}
//				  })
//				.setNegativeButton("Nein",new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog,int id) {
//						dialog.cancel();
//					}
//				});
// 
//				// create alert dialog
//				AlertDialog alertDialog = alertDialogBuilder.create();
// 
//				// show it
//				alertDialog.show();
//			}
}
