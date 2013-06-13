package de.hska.shareyourspot.android.activites;

import java.util.ArrayList;
import java.util.List;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.helper.UserStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class NewGroup extends Activity {
	private UserStore uStore = new UserStore();
	private Context ctx = this;
	
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newgroup);

		// TODO: load friends of the user
		
		@SuppressWarnings("rawtypes")
		List valueList = new ArrayList<String>();
		
		for (int i = 0; i < 10; i++) {
		int friend = 0;
		valueList.add(friend+i);		
	}
		
		
	
		@SuppressWarnings({ "rawtypes" })
		ListAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, valueList);
		final ListView lv = (ListView)findViewById(R.id.list_FriendsToGroup);

		lv.setAdapter(adapter);
		
//		lv.setOnItemClickListener(new OnItemClickListener() {
//			
//		@Override
//		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		
//			Intent intent = new Intent();
//		intent.setClassName(getPackageName(), getPackageName()+”.TutorialListViewActivityAnzeige”);
//		intent.putExtra(“selected”, lv.getAdapter().getItem(arg2).toString());
//		startActivity(intent);
//		}
//		});
	}

}
