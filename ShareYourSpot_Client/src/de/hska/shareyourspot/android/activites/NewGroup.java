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
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
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
	private Party lookForParty;
	private List<Party> foundParties;
	private ListView listGroups;
	private ArrayList<String> meineListe;
	public final String groupId = "groupId";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newgroup);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		this.meineListe = new ArrayList<String>();
		this.foundParties = new ArrayList<Party>();
		this.lookForParty = new Party();
		this.listGroups = (ListView) findViewById(R.id.list_FriendsToGroup);
		EditText editText = (EditText) findViewById(R.id.editText2);
		this.lookForParty.setName(editText.getText().toString());

		Parties parties = this.restClient.getAllParties();
		if (parties != null) {
			this.foundParties.addAll(parties.getAllParties());

			for (Party party : foundParties) {
				if (party.getName() != null) {
					this.meineListe.add(party.getName());
				}
			}
		}
//		else{
//		this.meineListe.add("");}
		ListAdapter listenAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, meineListe);

		this.listGroups.setAdapter(listenAdapter);

		this.listGroups.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String item = ((TextView) view).getText().toString();

				Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG)
						.show();

				groupDetail(item);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_group, menu);
		return true;
	}

	public void groupDetail(String name) {
		Intent intent = new Intent(this, Group_Detail.class);

		for (Party party : this.foundParties) {
			if (party.getName().equalsIgnoreCase(name)) {
				intent.putExtra(this.groupId, Long.valueOf(party.getPartyId()));
			}
		}

		startActivity(intent);
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
			
		case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;	    
		}
	    return super.onOptionsItemSelected(item);
	}

	public void toPostList() {
		Intent intent = new Intent(this, PostList.class);
		startActivity(intent);
	}

	
	public void showGroups() {
		Intent intent = new Intent(this, Groups.class);
		startActivity(intent);
	}

	public void createNewGroup(View view) throws IOException {

		EditText groupname = (EditText) findViewById(R.id.editText1);
		this.newParty = new Party();
		this.newParty.setName(groupname.getText().toString());
		
		User user = uStore.getUser(ctx);
	
		this.newParty.addUserToParty(user.getUserId());

		int i = this.restClient.createGroup(this.newParty);
		System.out.println(i);

		Intent intent = new Intent(this, Groups.class);
		startActivity(intent);
	}
	
	public void searchGroup(View view) {
	
		EditText groupname = (EditText) findViewById(R.id.editText2);
		this.newParty = new Party();
		this.newParty.setName(groupname.getText().toString());

		this.meineListe = new ArrayList<String>();
		
		Party party = this.restClient.getPartyByName(this.newParty);
		if (party != null) {
			this.meineListe.add(party.getName());
		} 
//		else {
//			this.meineListe.add("");
//		}
		ListAdapter listenAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, meineListe);

		this.listGroups.setAdapter(listenAdapter);
	}
	
	public void onClickSearch(View view) {
		this.meineListe = new ArrayList<String>();
		this.foundParties = new ArrayList<Party>();
		this.lookForParty = new Party();
		EditText editText = (EditText) findViewById(R.id.editText2);
		this.lookForParty.setName(editText.getText().toString());

		Parties parties = this.restClient.getAllParties();
		if (parties != null) {
			this.foundParties.addAll(parties.getAllParties());

			for (Party party : foundParties) {
				if (party.getName() != null || party.getName().isEmpty()) {
					this.meineListe.add(party.getName());
				}
			}
		} 
//		else {
//			this.meineListe.add("");
//		}
		ListAdapter listenAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, meineListe);

		this.listGroups.setAdapter(listenAdapter);

		this.listGroups.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String item = ((TextView) view).getText().toString();

				Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG)
						.show();

				groupDetail(item);
			}
		});
	}

}
