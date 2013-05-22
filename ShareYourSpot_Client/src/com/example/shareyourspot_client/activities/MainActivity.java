package com.example.shareyourspot_client.activities;


import com.example.shareyourspot_client.R;
import com.example.shareyourspot_client.R.layout;
import com.example.shareyourspot_client.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	 public void login(View view) {
		 System.out.println("Wurde in MainActicity gerufen");
		 Intent intent = new Intent(this, RestTest.class);
		 startActivity(intent);
	 }
	 
}
