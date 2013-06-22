package de.hska.shareyourspot.android.activites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Parties;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;

public class Groups extends Activity {

	private RestClient restClient = new RestClient();
	final Context context = this;

	private Party lookForParty;
	private List<Party> foundParties;
	private ListView listGroups;
	private ArrayList<String> meineListe;

	private UserStore uStore = new UserStore();
	private Context ctx = this;

	public final String groupId = "groupId";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.groups);
//		getActionBar().setDisplayHomeAsUpEnabled(true);

		this.foundParties = new ArrayList<Party>();
		this.meineListe = new ArrayList<String>();
		this.listGroups = (ListView) findViewById(R.id.list_groups);

		//Parties parties = this.restClient.getAllParties();
		User u = null;
		try {
			u = uStore.getUser(ctx);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = new User();
		user.setUserId(u.getUserId());
		Parties parties = this.restClient.getPartiesByUser(user);

		if (parties != null) {

			this.foundParties.addAll(parties.getAllParties());

			for (Party party : this.foundParties) {
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

	// public void onClickSearch(View view) {
	//
	// this.lookForParty = new Party();
	// EditText editText = (EditText)
	// findViewById(R.id.editText_enterGroupName);
	// this.lookForParty.setName(editText.getText().toString());
	//
	// Parties parties = this.restClient.searchParties(this.lookForParty);
	//
	// this.foundParties.addAll(parties.getAllParties());
	//
	// for (Party party : foundParties) {
	// if (party.getName() != null || party.getName().isEmpty()) {
	// this.meineListe.add(party.getName());
	// }
	// }
	// ListAdapter listenAdapter = new ArrayAdapter<String>(this,
	// android.R.layout.simple_list_item_1, meineListe);
	//
	// this.listGroups.setAdapter(listenAdapter);
	//
	// this.listGroups.setOnItemClickListener(new OnItemClickListener() {
	//
	// @Override
	// public void onItemClick(AdapterView<?> parent, View view,
	// int position, long id) {
	//
	// String item = ((TextView) view).getText().toString();
	//
	// Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG)
	// .show();
	//
	// groupDetail(item);
	// }
	// });
	// }

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.groups, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_logout:
			uStore.logout(ctx);
			finish();
			break;

		

		// Respond to the action bar's Up/Home button
		// case android.R.id.home:
		// Intent upIntent = NavUtils.getParentActivityIntent(this);
		// if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
		// This activity is NOT part of this app's task, so create a new
		// task
		// when navigating up, with a synthesized back stack.
		// TaskStackBuilder.create(this)
		// Add all of this activity's parents to the back stack
		// .addNextIntentWithParentStack(upIntent)
		// Navigate up to the closest parent
		// .startActivities();
		// } else {
		// This activity is part of this app's task, so simply
		// navigate up to the logical parent activity.
		// NavUtils.navigateUpTo(this, upIntent);
		// }
		// return true;

		default:
			break;
		}

		return true;
	}

	public void toPostList() {
		Intent intent = new Intent(this, PostList.class);
		startActivity(intent);
	}

	public void toNewGroup(View view) {
		Intent intent = new Intent(this, NewGroup.class);
		startActivity(intent);
	}

}
