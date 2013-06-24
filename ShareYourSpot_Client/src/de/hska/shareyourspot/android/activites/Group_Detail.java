package de.hska.shareyourspot.android.activites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Parties;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.domain.lists.LongPartyIds;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Group_Detail extends Activity {
	
	private UserStore uStore = new UserStore();
	private Context ctx = this;
	public final String groupId = "groupId";
	public final String groupMember = "groupMember";
	public boolean groupMemberBool; 
	private RestClient restClient = new RestClient();
	private long group = Long.valueOf(0);
	private Party party = new Party();
	public final String tabIndex = "tabIndex";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_group_detail);

		this.group = getIntent().getLongExtra(this.groupId, group);
		this.groupMemberBool = getIntent().getBooleanExtra(this.groupMember, false);
		
		if(groupMemberBool)
		{
			Button button = (Button) findViewById(R.id.btn_JoinGroup);
			button.setEnabled(false);
		}
		
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

		 case android.R.id.home:
			  	onBackPressed();
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

		List<Long> parties = new ArrayList<Long>();

		parties.add(Long.valueOf(this.group));	
		user.setPartiesOfUser(parties);
		user = this.restClient.joinGroup(user.getUserId(),Long.valueOf(this.group));
		System.out.println("implement me");
		toStart();
	}
	
	public void leaveGroup(View view) throws IOException{
		User user = uStore.getUser(ctx);
		user = this.restClient.leaveGroup(user.getUserId(),Long.valueOf(this.group));
		System.out.println("implement me");
		toStart();
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
	
	public void toStart() {
		Intent intent = new Intent(this, AndroidTabLayoutActivity.class);
		intent.putExtra(tabIndex, 1);
		startActivity(intent);
	}
}
