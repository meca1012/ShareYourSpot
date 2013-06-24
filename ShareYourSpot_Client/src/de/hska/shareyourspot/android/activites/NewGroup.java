package de.hska.shareyourspot.android.activites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Parties;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.AlertHelper;
import de.hska.shareyourspot.android.helper.LazyAdapterGroups;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
	public final String tabIndex = "tabIndex";
	public static final String KEY_ID = "id";
	public static final String KEY_TITLE = "title";
	private LazyAdapterGroups adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.newgroup);
		

		this.meineListe = new ArrayList<String>();
		this.foundParties = new ArrayList<Party>();
		this.lookForParty = new Party();
		this.listGroups = (ListView) findViewById(R.id.list_FriendsToGroup);
		EditText editText = (EditText) findViewById(R.id.editText2);
		this.lookForParty.setName(editText.getText().toString());

		Parties parties = this.restClient.getAllParties();
		ArrayList<HashMap<String, String>> partyList = new ArrayList<HashMap<String, String>>();

		if (parties != null && !parties.getAllParties().isEmpty()) {
			this.foundParties = parties.getAllParties();
			for (Party party : foundParties) {
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();

				if (party.getName() != null) {
					map.put(KEY_ID, party.getPartyId().toString());
					map.put(KEY_TITLE, party.getName());
				}
				// adding HashList to ArrayList
				partyList.add(map);
			}

			// Getting adapter by passing xml data ArrayList
			adapter = new LazyAdapterGroups(this, partyList);
			this.listGroups.setAdapter(adapter);

			this.listGroups.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					// String item = ((TextView) view).getText().toString();
					String item = foundParties.get(position).getName();
					Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG)
							.show();

					groupDetail(item);
				}
			});
		}
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

		case android.R.id.home:
			onBackPressed();
			finish();
			break;

		default:
			break;

		}
		return true;
	}

	public void createNewGroup(View view) throws IOException {

		EditText groupname = (EditText) findViewById(R.id.commentText);
		String groupText = groupname.getText().toString();

		if (groupText == null || groupText.isEmpty()) {
			new AlertHelper(ctx, R.string.groupCreateFailureGroupnameTitle,
					R.string.groupCreateFailureGroupnameText, "Back")
					.fireAlert();
			return;
		}

		this.newParty = new Party();
		this.newParty.setName(groupText);

		User user = uStore.getUser(ctx);

		this.newParty.addUserToParty(user.getUserId());

		int i = this.restClient.createGroup(this.newParty);
		System.out.println(i);

		Intent intent = new Intent(this, AndroidTabLayoutActivity.class);
		intent.putExtra(tabIndex, 1);
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
		// else {
		// this.meineListe.add("");
		// }
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
		// else {
		// this.meineListe.add("");
		// }
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
