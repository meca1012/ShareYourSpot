package de.hska.shareyourspot.android.activites;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Parties;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.restclient.RestClient;
import de.hska.shareyourspot.android.helper.UserStore;


public class Groups extends Activity {

	private RestClient restClient = new RestClient();
	final Context context = this;

	private Party lookForParty;
	private List<Party> foundParties;
	private ListView listGroups;
	private ArrayList<String> meineListe;

	private UserStore uStore = new UserStore();
	private Context ctx = this;

	public final String groupName = "groupName";

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.groups);

		this.foundParties = new ArrayList<Party>();
		this.meineListe = new ArrayList<String>();
		this.listGroups = (ListView) findViewById(R.id.list_groups);
	}

	public void onClickSearch(View view) {

		this.lookForParty = new Party();
		EditText editText = (EditText) findViewById(R.id.editText_enterGroupName);
		this.lookForParty.setName(editText.getText().toString());

		Parties parties = this.restClient.searchParties(this.lookForParty);

		this.foundParties.addAll(parties.getAllParties());

		for (Party party : foundParties) {
			if (party.getName() != null || party.getName().isEmpty()) {
				this.meineListe.add(party.getName());
			}
		}
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
						
//						startGroupDetail(item);
			}
		});
	}
	
//	public void startGroupDetail(String name)
//	{
//		Intent intent = new Intent(this, Group_Detail.class);
//		intent.putExtra(groupName, name);
//		startActivity(intent);
//	}

	public void showSpots(View view) {
		Intent intent = new Intent(this, PostList.class);
		startActivity(intent);
	}

	public void newGroup(View view) {
		Intent intent = new Intent(this, NewGroup.class);
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


	  default:
	    break;
	  }

	  return true;
	} 

}
