package de.hska.shareyourspot.android.activites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NewGroup extends Activity {
	private RestClient restClient = new RestClient();
	private UserStore uStore = new UserStore();
	private Context ctx = this;
	private Party newParty;
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_group, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  switch (item.getItemId()) {
	  case R.id.action_logout:
		  	uStore.logout(ctx);
	        finish();
	    break;
	    
	  case R.id.action_postlist:
			toPostList();
			finish();
			break;

		case R.id.action_newGroup:
			toNewGroup();
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
	
	public void toPostList() {
		Intent intent = new Intent(this, PostList.class);
		startActivity(intent);
	}
	
	public void toNewGroup() {
		Intent intent = new Intent(this, NewGroup.class);
		startActivity(intent);
	}
	
	public void toNewPost() {
		Intent intent = new Intent(this, NewPost.class);
		startActivity(intent);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newgroup);

	}
		
	
		
		public void createNewGroup(View view) throws IOException {
			
			EditText groupname = (EditText) findViewById(R.id.editText1);
			this.newParty = new Party();
			this.newParty.setName(groupname.getText().toString());
			
			List<User> newUsers = new ArrayList<User>();
			User user = uStore.getUser(ctx);
			newUsers.add(user);
			this.newParty.setUsersInParty(newUsers);
			
			int i = this.restClient.createGroup(this.newParty);
			System.out.println(i);
			
			Intent intent = new Intent(this, Groups.class);
			startActivity(intent);
		}
	
	

}
