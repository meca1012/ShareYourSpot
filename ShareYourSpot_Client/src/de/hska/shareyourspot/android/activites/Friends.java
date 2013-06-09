package de.hska.shareyourspot.android.activites;

import java.util.List;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Friends extends Activity {

	private List<User> friends;

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

		// TODO: search my friend
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

}
