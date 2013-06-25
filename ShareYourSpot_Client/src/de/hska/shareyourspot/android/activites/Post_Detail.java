package de.hska.shareyourspot.android.activites;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Comment;
import de.hska.shareyourspot.android.domain.Comments;
import de.hska.shareyourspot.android.domain.Parties;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.Post;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.AlertHelper;
import de.hska.shareyourspot.android.helper.LazyAdapterComments;
import de.hska.shareyourspot.android.helper.LazyAdapterGroups;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Post_Detail extends Activity {
	
	private UserStore uStore = new UserStore();
	private Context ctx = this;
	private RestClient restClient = new RestClient();
	protected Post post; 
	public final String longitude = "longitude";
	public final String latitude = "latitude";
	public String imageUrl = "http://hskaebusiness.square7.ch/ShareYourSpot/";
	public String imageEnd = ".jpg";
	public double ratingResults = 0;
	private long postIdent;
	public final String postId = "postId";
	private ListView listComments;
	//private ArrayList<String> shownComments;
	private List<Comment>foundComments = new ArrayList<Comment>();
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    private LazyAdapterComments adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		this.postIdent = getIntent().getLongExtra(postId, 0);
		setContentView(R.layout.activity_post_detail);
		this.post = this.restClient.getPost(postIdent);
		 ArrayList<HashMap<String, String>> commentList = new ArrayList<HashMap<String, String>>();
		 
		if(this.post != null)
		{
			ImageView imageView = (ImageView) findViewById(R.id.imageViewPostDetail);
			new DownloadImageTask(imageView).execute(imageUrl + this.post.getPostId() + imageEnd);
			TextView spotterName = (TextView) findViewById(R.id.textView_spotter);
		spotterName.setText(this.post.getCreatedByUser().getName() + " wrote, ");
		
		TextView spotText = (TextView) findViewById(R.id.textView_spot_text);
		spotText.setText(this.post.getText());
		}
		else
		{
			new AlertHelper(ctx, R.string.dataLoadFailureTitle,
					R.string.dataLoadFailureText, "Next Try").fireAlert();
		}
		
		this.foundComments = new ArrayList<Comment>();
		//this.shownComments = new ArrayList<String>();
		this.listComments = (ListView) findViewById(R.id.listView_comments);
		List<Comment> comments = new ArrayList<Comment>();
		if(this.post.getComments()!=null){
		comments = this.post.getComments();
		}
		if (comments != null) {

			this.foundComments = comments;

			for (Comment comment : this.foundComments) {
				if (comment.getText() != null) {
					
					this.ratingResults += comment.getRating();
					//this.shownComments.add(comment.getCreatedByUsername() + ": " + comment.getText());
					
					 HashMap<String, String> map = new HashMap<String, String>();

			            if(comment.getText() !=  null)
			            {
			            map.put(KEY_ID, comment.getCommentId().toString());
			            map.put(KEY_TITLE,comment.getCreatedByUsername() + ": " + comment.getText());
			            }
			            // adding HashList to ArrayList
			            commentList.add(map);					
				}
			}
			this.ratingResults = this.ratingResults/this.foundComments.size();
			DecimalFormat df = new DecimalFormat("#0.0");			
			TextView textView = (TextView) findViewById(R.id.textView_midvalue);
			textView.setText(df.format(this.ratingResults));
		}
//		else{
//		this.meineListe.add("");}

        adapter=new LazyAdapterComments(this, commentList);
        this.listComments.setAdapter(adapter);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_detail, menu);
		return true;
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.post__detail, menu);
//		return true;
//	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  switch (item.getItemId()) {
	  case R.id.action_logout:
		  	uStore.logout(ctx);
	        finish();
	    break;
	    
	  case android.R.id.home:
		  	onBackPressed();
	        finish();
	    break;
	  
	  default:
	    break;
	  }

	  return true;
	} 
	
		
	
	 
		public void startMap(View view) {
			//TODO Karlruhe gegen Postdatenersetzen
			LatLng test = new LatLng(49.014,  8.4043);
			Intent intent = new Intent(this, GoogleMaps.class);
			intent.putExtra(this.longitude, test.longitude);
			intent.putExtra(this.latitude,  test.latitude);
			startActivity(intent);
		}
	    	
		
		public void showFullPic(View view) {
			//TODO Karlruhe gegen Postdatenersetzen
			Intent intent = new Intent(this, FullScreenImage.class);
			intent.putExtra(this.postId, postIdent);
			startActivity(intent);
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
	
	public void addComment(View view){
		
		Intent intent = new Intent(this, AddComment.class);
		intent.putExtra(this.postId, postIdent);
		startActivity(intent);
				
	}

}
