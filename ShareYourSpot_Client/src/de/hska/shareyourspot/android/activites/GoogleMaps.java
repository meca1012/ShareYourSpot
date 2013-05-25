package de.hska.shareyourspot.android.activites;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.helper.GoogleMapsHelper;

public class GoogleMaps extends FragmentActivity {
		static final LatLng KARLSRUHE = new LatLng(49.014,  8.4043);

	  
	  private GoogleMap map;
	  private GoogleMapsHelper mapsHelper;
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_google_maps);
	    	    
	    
	    map = ((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	    
		if (map==null) {
			Log.d("MAPFragment", "Map Fragment Not Found or no Map in it!!");
			return;
		}
		
	    Marker mapSpot = map.addMarker(new MarkerOptions().position(KARLSRUHE).title("Karlsruhe"));

	    // Move the camera instantly to hamburg with a zoom of 15.
	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(KARLSRUHE, 15));

	    // Zoom in, animating the camera.
	    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	  }
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

	} 
