package de.hska.shareyourspot.android.activites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Parties;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Group_Detail extends Activity {
	
	private UserStore uStore = new UserStore();
	private Context ctx = this;
	public final String groupId = "groupId";
	private RestClient restClient = new RestClient();
	private long group = Long.valueOf(0);
	private Party party = new Party();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_detail);

		this.group = getIntent().getLongExtra(this.groupId, group);
		
		TextView groupN = (TextView) findViewById(R.id.textView_groupname);
		this.party = this.restClient.getParty(this.group);
		groupN.setText(this.party.getName());
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

		case R.id.action_postlist:
			toPostList();
			finish();
			break;

		case R.id.action_groups:
			toGroups();
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
	
	public void joinGroup(View view) throws IOException{
//		TODO: implement joinGroup in Rest
		User user = uStore.getUser(ctx);
		Parties parties = new Parties();
		Party party = new Party();
		party.setPartyId(Long.valueOf(this.group));
		parties.addParty(party);	
		user.setParties(parties);
		user = this.restClient.joinGroup(user);
		System.out.println("implement me");
		toGroups();
	}
	
	public void showSpots(View view) {
		Intent intent = new Intent(this, PostList.class);
		startActivity(intent);
	}

	public void newGroup(View view) {
		Intent intent = new Intent(this, NewGroup.class);
		startActivity(intent);
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
	
	public void toGroups() {
		Intent intent = new Intent(this, Groups.class);
		startActivity(intent);
	}
}
