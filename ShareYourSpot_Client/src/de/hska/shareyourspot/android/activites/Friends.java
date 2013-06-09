package de.hska.shareyourspot.android.activites;

import de.hska.shareyourspot.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Friends extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends);		
	}
	
	public void showGroups(View view) {
		Intent intent = new Intent(this, Groups.class);
		startActivity(intent);
	}

}
