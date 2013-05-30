package de.hska.shareyourspot.android.activites;

import java.net.HttpURLConnection;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	private RestClient restClient = new RestClient();
	
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

	public void registerAction(View view) {
		 Intent intent = new Intent(this, Register.class);
		 startActivity(intent);
	}	
	 public void loginAction(View view) {
		 	User u = new User();
		 	
		 	EditText username = (EditText) findViewById(R.id.loginUsername);
			u.setName(username.getText().toString());
			
		 	EditText password = (EditText) findViewById(R.id.loginPassword);
			u.setPassword(password.getText().toString());
		 	
			int code = restClient.registerUser(u);
			if(code ==  HttpURLConnection.HTTP_ACCEPTED)
			{//Start new page
				
			}
			else
			{
				//FireEvent
			}
			
	 }
	 
	 public void restTester(View view) {
		 Intent intent = new Intent(this, RestTest.class);
		 startActivity(intent);
	 }
	 
	 public void mapTester(View view) {
		 Intent intent = new Intent(this, GoogleMaps.class);
		 startActivity(intent);
	 }
	 
	 public void camTester(View view) {
		 Intent intent = new Intent(this, Cam.class);
		 startActivity(intent);
		  }
	 

		   
	 }
	 
