package de.hska.shareyourspot.android.activites;

import java.io.InputStream;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.R.layout;
import de.hska.shareyourspot.android.R.menu;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;

public class FullScreenImage extends Activity {

	private long postIdent;
	public final String postId = "postId";
	public String imageUrl = "http://hskaebusiness.square7.ch/ShareYourSpot/";
	public String imageEnd = ".jpg";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_screen_image);
		this.postIdent = getIntent().getLongExtra(postId, 0);
		
		
		Intent intent = getIntent();
		this.postIdent = getIntent().getLongExtra(postId, 0);
		ImageView imageView = (ImageView) findViewById(R.id.fullimage);
		String url = imageUrl + this.postIdent + imageEnd;
		new DownloadImageTask(imageView).execute(imageUrl + this.postIdent + imageEnd);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.full_screen_image, menu);
		return true;
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	    ImageView bmImage;

	    public DownloadImageTask(ImageView bmImage) {
	        this.bmImage = bmImage;
	    }

	    protected Bitmap doInBackground(String... urls) {
	        String urldisplay = urls[0];
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new java.net.URL(urldisplay).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }

	    protected void onPostExecute(Bitmap result) {
	        bmImage.setImageBitmap(result);
	    }
	}
}
