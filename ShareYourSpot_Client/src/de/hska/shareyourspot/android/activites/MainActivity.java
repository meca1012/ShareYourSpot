package de.hska.shareyourspot.android.activites;

import java.io.IOException;
import java.net.HttpURLConnection;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.AlertHelper;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	private RestClient restClient = new RestClient();
	final Context context = this;
	private UserStore uStore = new UserStore();
	
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
		 	
			
			
			if(u.getName() == null || u.getName().isEmpty() || u.getPassword() == null || u.getPassword().isEmpty())
			{
				new AlertHelper(context, R.string.loginFailureTitle, R.string.loginFailureText, "Retry").fireAlert();
			}
			else
			{
				int code = restClient.loginUser(u);
			if(code ==  HttpURLConnection.HTTP_ACCEPTED)
			{
				try {
					uStore.storeUser(this, u);
					User test = uStore.getUser(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 Intent intent = new Intent(this, PostList.class);
				 startActivity(intent);
			}
			else
			{
				new AlertHelper(context, R.string.loginFailureTitle, R.string.loginFailureText, "Retry").fireAlert();
			}
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
	  

		   
	 }
	 
