package de.hska.shareyourspot.android.activites;

import java.io.IOException;
import java.net.HttpURLConnection;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.AlertHelper;
import de.hska.shareyourspot.android.helper.AsyncBooleanResult;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	private RestClient restClient = new RestClient();
	final Context context = this;
	private UserStore uStore = new UserStore();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		try {
			User u = uStore.getUser(context);
			
			if (u != null && u.isUserApproved()) {
				u.setUserApproved(true);
				uStore.storeUser(this, u);
				Intent intent = new Intent(this, AndroidTabLayoutActivity.class);
		        intent.putExtra("finish", true);
		        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
		        startActivity(intent);
		        finish();	
			}
		} catch (IOException e) {
			setContentView(R.layout.activity_main);
			findViewById(R.id.loginBtnLogin).setOnClickListener(this);
		}
		
		setContentView(R.layout.activity_main);
		findViewById(R.id.loginBtnLogin).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void registerAction(View view) {
		Intent intent = new Intent(this, Register.class);
		startActivity(intent);
	}
	
	public boolean loginAction() {
		User u = new User();
		boolean loggedIn = false;
		
		EditText username = (EditText) findViewById(R.id.loginUsername);
		u.setName(username.getText().toString());

		EditText password = (EditText) findViewById(R.id.loginPassword);
		u.setPassword(password.getText().toString());

		if (u.getName() == null || u.getName().isEmpty()
				|| u.getPassword() == null || u.getPassword().isEmpty()) {
				return false;
		} else {
			
			User responseUser = null;
			try{
				responseUser = restClient.loginUser(u);
			}
			catch(Exception e){}
			
			if (responseUser != null && responseUser.getPassword() != null)
			{
				if (responseUser.getPassword().endsWith(u.getPassword())) {
					try {
						responseUser.setUserApproved(true);
						uStore.storeUser(this, responseUser);
						Intent intent = new Intent(this, AndroidTabLayoutActivity.class);
				        intent.putExtra("finish", true);
				        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
				        startActivity(intent);
				        loggedIn = true;
				        finish();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
						return false;
				}
			
			} else {
					return false;
			}
		}
		return loggedIn;

	}

	public void restTester(View view) {
		Intent intent = new Intent(this, RestTest.class);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		v.setEnabled(false);
	        AsyncTask<Void, Void, AsyncBooleanResult> task = new AsyncTask<Void, Void, AsyncBooleanResult>() {
	                private ProgressDialog pd;
	                @Override
	                protected void onPreExecute() {
	                         pd = new ProgressDialog(context);
	                         pd.setTitle("Login processing...");
	                         pd.setMessage("Please wait.");
	                         pd.setCancelable(false);
	                         pd.setIndeterminate(true);
	                         pd.show();
	                }
	                @Override
	                protected AsyncBooleanResult doInBackground(Void... arg0) {
	                 boolean logedIn = loginAction();
	                 return new AsyncBooleanResult(logedIn);
	                 }
	                 @Override
	                 protected void onPostExecute(AsyncBooleanResult result) {
	                	 if(result.isResult())
	                	 {
	                	 
	                      pd.dismiss();
	                	 }
	                	 else
	                	 {
	                		 pd.dismiss();
	                		 findViewById(R.id.loginBtnLogin).setEnabled(true);
	                		 new AlertHelper(context, R.string.loginFailureTitle, R.string.loginFailureText, "Try Again").fireAlert();
	                	 }
	                 }
	        };
	        task.execute((Void[])null);
	}
	
	
	
	

}
