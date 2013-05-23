package de.hska.shareyourspot.android.activites;




import com.example.shareyourspot_client.R;

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
		setContentView(R.layout.activity_main);
		
		 final Button button = (Button) findViewById(R.id.restTestButton);
         button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 	restClient = new RestClient();
            	 	User test = restClient.getBenutzerXML();
            	 	TextView text = (TextView) findViewById(R.id.textRestResult);
            	 	text.setText(test.toString());
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
