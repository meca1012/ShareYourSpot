package de.hska.shareyourspot.android.activites;

import java.io.ByteArrayOutputStream;

import de.hska.shareyourspot.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class NewPost extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_post);
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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				Bitmap bmp = (Bitmap) data.getExtras().get("data");
//				ByteArrayOutputStream stream = new ByteArrayOutputStream();
//				bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//				byte[] byteArray = stream.toByteArray();
				ImageButton pictureButton = (ImageButton)findViewById(R.id.newPostImage); 
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