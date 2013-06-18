package de.hska.shareyourspot.android.activites;

import java.io.ByteArrayInputStream;
import java.io.IOException;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Parties;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.Picture;
import de.hska.shareyourspot.android.domain.Post;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.AlertHelper;
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
	private static final int PICTURE_COMPRESS_RATE = 50;
	private UserStore uStore = new UserStore();
	private RestClient restClient = new RestClient();
	private Context ctx = this;
	private List<String> groupList;
	private int THUMBNAIL_SIZE = 64;
	
	//TODO CHANGE TO DB DATA
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parties parties = new Parties();
		this.groupList = new ArrayList();
		try {
			parties = restClient.getPartiesByUser(uStore.getUser(ctx));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(parties == null)
			{
				Party p = new Party();
				p.setName("No Groups joined");
				
				parties = new Parties();
				parties.addParty(p);
			}
		for(Party party : parties.getAllParties())
		{
			this.groupList.add(party.getName());
		}
		
		setContentView(R.layout.activity_new_post);
		Spinner spinner = (Spinner) findViewById(R.id.groupSpinner);
		ArrayAdapter<String> adp2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,this.groupList);
		spinner.setAdapter(adp2);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  switch (item.getItemId()) {
	  case R.id.action_logout:
		  	uStore.logout(ctx);
	        finish();
	    break;

	  case R.id.action_postlist:
			toPostList();
			finish();
			break;

		case R.id.action_newGroup:
			toNewGroup();
			finish();
			break;

	  default:
	    break;
	  }

	  return true;
	} 
	
	public void toPostList() {
		Intent intent = new Intent(this, PostList.class);
		startActivity(intent);
	}
	
	public void toNewGroup() {
		Intent intent = new Intent(this, NewGroup.class);
		startActivity(intent);
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
	
	public void pushPost(View view) throws IOException {
		
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
		Bitmap bitmap_thumbnail = bitmapDrawable .getBitmap();
		
		//Create ThumbNail
		Float width = new Float(bitmap_thumbnail.getWidth());
		Float height = new Float(bitmap_thumbnail.getHeight());
		Float ratio = width/height;
		
		bitmap_thumbnail = Bitmap.createScaledBitmap(bitmap_thumbnail, (int)(THUMBNAIL_SIZE * ratio), THUMBNAIL_SIZE, false);
		
		//Create
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, PICTURE_COMPRESS_RATE, stream);
		byte[] imageInByte = stream.toByteArray();

		stream = new ByteArrayOutputStream();
		bitmap_thumbnail.compress(Bitmap.CompressFormat.JPEG, PICTURE_COMPRESS_RATE, stream);
		byte[] thumbInByte = stream.toByteArray();
	
		
		//Create Post
		Picture pic = new Picture();
		pic.setImgData(imageInByte);
		pic.setImgType(Bitmap.CompressFormat.JPEG.toString());
		
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
		
		// Create Picutre on Server
		
		
		// Create Post
		// TODO GroupSeleced
		
		Post post = new Post(postText, party);
		post.setPicture(1L);
		post.setCreatedByUser(uStore.getUser(ctx));
		restClient.createPost(post);
		
		
		Intent intent = new Intent(this, PostList.class);
		startActivity(intent);


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
