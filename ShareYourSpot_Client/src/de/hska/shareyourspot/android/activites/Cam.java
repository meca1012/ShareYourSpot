package de.hska.shareyourspot.android.activites;

import java.io.ByteArrayOutputStream;
import de.hska.shareyourspot.android.R;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;


public class Cam extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		
	}

	 @Override
	 public void onActivityResult(int requestCode, int resultCode, Intent data) {        
	     if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	         if (resultCode == Activity.RESULT_OK) {
	             Bitmap bmp = (Bitmap) data.getExtras().get("data");
	             ByteArrayOutputStream stream = new ByteArrayOutputStream();
	             bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
	             byte[] byteArray = stream.toByteArray();
	
	             //TODO DO SOMETHING WITH THE PICUTRE
	        	 Intent intent = new Intent(this, MainActivity.class);
	    		 startActivity(intent);
	
	         } else if (resultCode == Activity.RESULT_CANCELED) {
	    		 Intent intent = new Intent(this, MainActivity.class);
	    		 startActivity(intent);
	         } else {
	    		 Intent intent = new Intent(this, MainActivity.class);
	    		 startActivity(intent);
	         }
	     }               
	 }


}