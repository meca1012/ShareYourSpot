package de.hska.shareyourspot.android.activites;




import de.hska.shareyourspot.android.R;

import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestTest extends Activity {
	
	private RestClient restClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rest_test);
		
		 final Button getbutton = (Button) findViewById(R.id.getRestTest);
         final Button clearButton = (Button) findViewById(R.id.clearRestTest);
         
         getbutton.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 	restClient = new RestClient();
            	 	User member = restClient.getBenutzerXML();
            	 	TextView viewId = (TextView) findViewById(R.id.restTesterId);
            	 	viewId.setText(member.getId().toString());
            	 	
            	 	TextView viewName = (TextView) findViewById(R.id.restTesterName);
            	 	viewName.setText(member.getName().toString());
            	 	
            	 	TextView viewMail = (TextView) findViewById(R.id.restTesterMail);
            	 	viewMail.setText(member.getEmail().toString());
            	 	
            	 	TextView viewPassword = (TextView) findViewById(R.id.restTesterPassword);
            	 	viewPassword.setText(member.getPassword().toString());
            	 	
            	 	TextView viewCreated = (TextView) findViewById(R.id.restTesterCreated);
            	 	viewCreated.setText(member.getCreated().toString());
 	
             }
         });
         
         clearButton.setOnClickListener(new View.OnClickListener() {
        	 public void onClick(View v) {
         	 	TextView viewId = (TextView) findViewById(R.id.restTesterId);
         	 	viewId.setText("");
         	 	
         	 	TextView viewName = (TextView) findViewById(R.id.restTesterName);
         	 	viewName.setText("");
         	 	
         	 	TextView viewMail = (TextView) findViewById(R.id.restTesterMail);
         	 	viewMail.setText("");
         	 	
         	 	TextView viewPassword = (TextView) findViewById(R.id.restTesterPassword);
         	 	viewPassword.setText("");
         	 	
         	 	TextView viewCreated = (TextView) findViewById(R.id.restTesterCreated);
         	 	viewCreated.setText("");
        		 
        	 }
       });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
