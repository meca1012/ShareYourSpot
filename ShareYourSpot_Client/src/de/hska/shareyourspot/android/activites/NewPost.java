package de.hska.shareyourspot.android.activites;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.Post;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class NewPost extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
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
		
		//Get Post Text
		TextView txtview = (TextView) findViewById(R.id.postText);
		String postText = txtview.getText().toString();
		
		
		//Get Image
		ImageView pic = (ImageView)findViewById(R.id.newImagePost);

		
		BitmapDrawable bitmapDrawable = ((BitmapDrawable) pic.getDrawable());
		Bitmap bitmap = bitmapDrawable .getBitmap();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] imageInByte = stream.toByteArray();
		ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
		
	
		//Get selected Group
		Spinner spinner = (Spinner) findViewById(R.id.groupSpinner);
		String group = spinner.getSelectedItem().toString();
		Party party = new Party();
		party.setName(group);
		
//		<Post p = new Post(postText, bis, group);>
//		TODO: hier weitermachen
//		Post p = new Post(postText, bis, party);

		

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