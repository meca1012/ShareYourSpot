package de.hska.shareyourspot.android.activites;



import java.io.ByteArrayOutputStream;

import de.hska.shareyourspot.android.R;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	    
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
		 Intent intent = new Intent(this, RestTest.class);
		 startActivity(intent);
	 }
	 
	 public void restTester(View view) {
		 Intent intent = new Intent(this, RestTest.class);
		 startActivity(intent);
	 }
	 
	 public void mapTester(View view) {
		 Intent intent = new Intent(this, GoogleMaps.class);
		 startActivity(intent);
	 }
	 
	 public void camTester(View view) {
		 Intent intent = new Intent(this, Cam.class);
		 startActivity(intent);
		  }
	 

		   
	 }
	 
