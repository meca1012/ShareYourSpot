package de.hska.shareyourspot.android.activites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.domain.Users;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Friends extends Activity {

	private RestClient restClient = new RestClient();

	final Context context = this;

	private List<User> friends;

	private List<User> foundUsers;

	private User lookFor;

	private ListView listUsers;

	private ArrayList<String> meineListe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends);

		this.listUsers = (ListView) findViewById(R.id.list_friends);
	}

	public void showGroups(View view) {
		Intent intent = new Intent(this, Groups.class);
		startActivity(intent);
	}

	public void onClickSearch(View view) {

		this.lookFor = new User();
		EditText username = (EditText) findViewById(R.id.editText_enter_name);
		this.lookFor.setName(username.getText().toString());

		// TODO: remove todo, Users users =
		// this.restClient.searchUser(this.lookFor);

		// __ MOCK
		Users users = new Users();
		User user1 = new User();
		user1.setName("test");
		user1.setEmail("ttttt@tttt.de");
		users.addUser(user1);
		User user2 = new User();
		user2.setName("harry");
		user2.setEmail("ttttt@tttt.de");
		users.addUser(user2);
		// __

		// TODO: add users to listUsers on UI
		this.foundUsers = new ArrayList<User>();
		this.foundUsers.addAll(users.getAllUser());

		this.meineListe = new ArrayList<String>();

		for (User user : foundUsers) {
			if (user.getName() != null || user.getName().isEmpty()) {
				this.meineListe.add(user.getName());
			}
		}

		ListAdapter listenAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, meineListe);

		// ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
		// android.R.layout.simple_list_item_1, this.foundUsers);

		// this.listUsers = (ListView) findViewById(R.id.list_friends);

		// listUsers.setAdapter(adapter);
		this.listUsers.setAdapter(listenAdapter);

		this.listUsers.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String item = ((TextView) view).getText().toString();

				Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG)
						.show();
			}
		});
		// String item = this.listUsers.getOnItemClickListener().toString();
		String test = "";
		System.out.println(test);
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public User getLookFor() {
		return lookFor;
	}

	public void setLookFor(User lookFor) {
		this.lookFor = lookFor;
	}

	public List<User> getFoundUsers() {
		return foundUsers;
	}

	public void setFoundUsers(List<User> foundUsers) {
		this.foundUsers = foundUsers;
	}

	public ListView getListUsers() {
		return listUsers;
	}

	public void setListUsers(ListView listUsers) {
		this.listUsers = listUsers;
	}

	public ArrayList<String> getMeineListe() {
		return meineListe;
	}

	public void setMeineListe(ArrayList<String> meineListe) {
		this.meineListe = meineListe;
	}

//	@Override
//	public void onListItemClick(ListView l, View v, int position, long id) {
//		// Do something when a list item is clicked
//		String item = ((TextView) v).getText().toString();
//
//		Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG)
//				.show();
//	}
	
//	private class StableArrayAdapter extends ArrayAdapter<String> {
//
//		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
//
//		public StableArrayAdapter(Context context, int textViewResourceId,
//				List<String> objects) {
//			super(context, textViewResourceId, objects);
//			for (int i = 0; i < objects.size(); ++i) {
//				mIdMap.put(objects.get(i), i);
//			}
//		}
//
//		@Override
//		public long getItemId(int position) {
//			String item = getItem(position);
//			return mIdMap.get(item);
//		}
//
//		@Override
//		public boolean hasStableIds() {
//			return true;
//		}
//
//	}

}
