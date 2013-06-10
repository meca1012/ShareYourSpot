package de.hska.shareyourspot.android.activites;

import java.util.List;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.domain.Users;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class Friends extends Activity {

	private RestClient restClient = new RestClient();
	
	private List<User> friends;
	
	private List<User> foundUsers;
	
	private User lookFor;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends);
	}

	public void showGroups(View view) {
		Intent intent = new Intent(this, Groups.class);
		startActivity(intent);
	}

	public void onClickSearch(View view) {
		// TODO: search for users and get list, add friends	
		this.lookFor = new User();
		
		EditText username = (EditText) findViewById(R.id.lookForUser);
		this.lookFor.setName(username.getText().toString());
			
		Users users = this.restClient.searchUser(this.lookFor);
		
		View listUsers = findViewById(R.id.listUsers);
		
//		TODO: add users to listUsers on UI
		
		this.foundUsers.addAll(users.getAllUser());
		Intent intent = new Intent(this, Friends.class);
		startActivity(intent);
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

}
