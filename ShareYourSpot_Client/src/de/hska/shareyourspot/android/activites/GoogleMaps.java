package de.hska.shareyourspot.android.activites;

import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.helper.GoogleMapsHelper;

public class GoogleMaps extends FragmentActivity {
		
	  
	  private GoogleMap map;
	  private GoogleMapsHelper mapsHelper;
	  public final String longitude = "longitude";
	  public final String latitude = "latitude";
	  private LatLng coordinates;
		
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_google_maps);
	    coordinates = new LatLng(getIntent().getDoubleExtra(latitude, 49.014), getIntent().getDoubleExtra(longitude,  8.4043));
	    
//	    DefaultHttpClient httpclient = new DefaultHttpClient();
//	    HttpHost proxy = new HttpHost("proxy.hs-karlrsuhe.de", 8888);
//	    httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	    
	    
	    map = ((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	    
		if (map==null) {
			Log.d("MAPFragment", "Map Fragment Not Found or no Map in it!!");
			return;
		}
		
	    Marker mapSpot = map.addMarker(new MarkerOptions().position(coordinates).title("Spot"));

	    // Move the camera instantly to hamburg with a zoom of 15.
	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15));

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
