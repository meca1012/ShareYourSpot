package de.hska.shareyourspot.android.activites;

import java.io.ByteArrayInputStream;


import java.io.ByteArrayOutputStream;
import java.util.Date;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.Picture;
import de.hska.shareyourspot.android.domain.Post;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.GoogleMapsHelper;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class NewPost extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private UserStore uStore = new UserStore();
	private RestClient restClient = new RestClient();
	private Context ctx = this;
	
	//TODO CHANGE TO DB DATA
	private final String[] str={"Gruppe 1","Gruppe 2","Gruppe 3","Gruppe 4","Gruppe 5"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_post);
		Spinner spinner = (Spinner) findViewById(R.id.groupSpinner);
		ArrayAdapter<String> adp2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,str);
		spinner.setAdapter(adp2);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  switch (item.getItemId()) {
	  case R.id.action_logout:
		  	uStore.logout(ctx);
	        finish();
	    break;


	  default:
	    break;
	  }

	  return true;
	} 
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_post, menu);
		return true;
	}

	public void startCam(View view) {

		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

	}
	
	public void pushPost(View view) {
		
		//Get LocationHelper
				
		GoogleMapsHelper locationHelper = new GoogleMapsHelper(this);
		if(!locationHelper.canGetLocation())
		{
			locationHelper.showSettingsAlert();
		}
		
		//Get Post Text
		TextView txtview = (TextView) findViewById(R.id.postText);
		String postText = txtview.getText().toString();

		
		//Build of PictureObject
		// First get ImageDate from ImageView
		ImageView imageView = (ImageView)findViewById(R.id.newImagePost);
		Drawable drawable = imageView.getDrawable();
		
		BitmapDrawable bitmapDrawable = ((BitmapDrawable) drawable);
		Bitmap bitmap = bitmapDrawable .getBitmap();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] imageInByte = stream.toByteArray();
		//ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
	
		Picture pic = new Picture();
		pic.setImgData(imageInByte);
		pic.setImgType("JPEG");
		
		//Get PossitionData
		Location location = locationHelper.getLocation();
		
		if(location == null)
		{
			//TODO pic.setLongitude(null);
			//TODO pic.setLatitude(null);
		}
		else
		{
			pic.setLongitude(location.getLongitude());
			pic.setLatitude(location.getLatitude());
		}
		
		//Get selected Group
		Spinner spinner = (Spinner) findViewById(R.id.groupSpinner);
		String group = spinner.getSelectedItem().toString();
		Party party = new Party();
		party.setName(group);
		party.setPartyId(1L);

		
		
		// Create Post
		// TODO GroupSeleced
		Post post = new Post(postText, pic, party);
		restClient.createPost(post);
		
		//Test
		Serializer serializer = new Persister();
		String output = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			serializer.write(post, os);
			output = new String(os.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(output.toString());


	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				Bitmap bmp = (Bitmap) data.getExtras().get("data");
//				ByteArrayOutputStream stream = new ByteArrayOutputStream();
//				bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//				byte[] byteArray = stream.toByteArray();
				ImageView pictureButton = (ImageView)findViewById(R.id.newImagePost);
				pictureButton.setImageBitmap(bmp);
				
				// TODO DO SOMETHING WITH THE PICUTRE
//				Intent intent = new Intent(this, NewPost.class);
//				startActivity(intent);

			} else if (resultCode == Activity.RESULT_CANCELED) {
				Intent intent = new Intent(this, NewPost.class);
				startActivity(intent);
			} else {
				Intent intent = new Intent(this, NewPost.class);
				startActivity(intent);
			}
		}
	}
}