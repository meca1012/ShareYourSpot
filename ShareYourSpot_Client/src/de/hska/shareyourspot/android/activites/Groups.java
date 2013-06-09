package de.hska.shareyourspot.android.activites;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Party;

public class Groups extends Activity {

	private List<Party> groups;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.groups);

		// TODO: load groups of the user
	}

	public void showFriends(View view) {
		Intent intent = new Intent(this, Friends.class);
		startActivity(intent);
	}

	// public void showMySpots(View view) {
	// Intent intent = new Intent(this, Spots.class);
	// startActivity(intent);
	// }

	public void newGroup(View view) {
		Intent intent = new Intent(this, NewGroup.class);
		startActivity(intent);
	}

	public List<Party> getGroups() {
		return groups;
	}

	public void setGroups(List<Party> groups) {
		this.groups = groups;
	}

}
