package de.hska.shareyourspot.android.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import de.hska.shareyourspot.android.R;

public class Groups extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.groups);		
	}
	
	public void showFriends(View view) {
		Intent intent = new Intent(this, Friends.class);
		startActivity(intent);
	}
	
//	public void showMySpots(View view) {
//		Intent intent = new Intent(this, Spots.class);
//		startActivity(intent);
//	}
	
	public void newGroup(View view) {
		Intent intent = new Intent(this, NewGroup.class);
		startActivity(intent);
	}


}
