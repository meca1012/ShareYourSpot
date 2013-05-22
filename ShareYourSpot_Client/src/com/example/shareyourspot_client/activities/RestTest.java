package com.example.shareyourspot_client.activities;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.example.shareyourspot_client.R;
import com.example.shareyourspot_client.R.id;
import com.example.shareyourspot_client.R.layout;
import com.example.shareyourspot_client.R.menu;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RestTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rest_test);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rest_test, menu);
		return true;
	}

	public void restTest(View view) {
		Button b = (Button)findViewById(R.id.my_button);
		b.setClickable(false);
		new LongRunningGetIO().execute();
	}
	
	private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
		
		protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
	       InputStream in = entity.getContent();
	         StringBuffer out = new StringBuffer();
	         int n = 1;
	         while (n>0) {
	             byte[] b = new byte[4096];
	             n =  in.read(b);
	             if (n>0) out.append(new String(b, 0, n));
	         }
	         return out.toString();
	    }
		 
		@Override
		protected String doInBackground(Void... params) {
			 HttpClient httpClient = new DefaultHttpClient();
			 HttpContext localContext = new BasicHttpContext();
             HttpGet httpGet = new HttpGet("http://ec2-54-245-155-31.us-west-2.compute.amazonaws.com:8080/KlinikumServer/rest/server/restInfo");
			 //HttpGet httpGet = new HttpGet("http://ec2-54-245-155-31.us-west-2.compute.amazonaws.com:8080/KlinikumServer/rest/patient/getPatientXML");
			 
			 String text = null;
             try {
                   HttpResponse response = httpClient.execute(httpGet, localContext);
                   HttpEntity entity = response.getEntity();
                   text = getASCIIContentFromEntity(entity);
             } catch (Exception e) {
            	 return e.getLocalizedMessage();
             }
             return text;
		}	
		
		protected void onPostExecute(String results) {
			if (results!=null) {
				EditText et = (EditText)findViewById(R.id.my_edit);
				et.setText(results);
			}
			Button b = (Button)findViewById(R.id.my_button);
			b.setClickable(true);
		}
    }
}