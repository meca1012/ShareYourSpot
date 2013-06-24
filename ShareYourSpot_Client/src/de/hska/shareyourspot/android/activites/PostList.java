package de.hska.shareyourspot.android.activites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.android.gms.internal.em;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.Picture;
import de.hska.shareyourspot.android.domain.Post;
import de.hska.shareyourspot.android.domain.Posts;
import de.hska.shareyourspot.android.domain.User;
import de.hska.shareyourspot.android.helper.LazyAdapter;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PostList extends Activity {
	private UserStore uStore = new UserStore();
	private Context ctx = this;
	protected Posts posts;
	private List<String> postsTitle;
	private int maxPostLength = 35;
	private int count = 1;
	private RestClient restclient = new RestClient();

    static final String URL = "http://api.androidhive.info/music/music.xml";
    // XML node keys
    public static final String KEY_SONG = "song"; // parent node
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_ARTIST = "artist";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_THUMB_URL = "thumb_url";
    public String testPicPath = "http://www.hs-karlsruhe.de/fileadmin/_t3templates/images/hska_logo_RGB_small.jpg";
    public String webSpaceBaseURL  = "http://hskaebusiness.square7.ch/ShareYourSpot/";
    public String webSpaceEndURL = ".jpg";
    private LazyAdapter adapter;
	
	
	
	public final String postId = "postId";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_post_list);
		this.posts = new Posts();
		
		 ArrayList<HashMap<String, String>> postList = new ArrayList<HashMap<String, String>>();
		
		User user = null;
		try {
			user = uStore.getUser(ctx);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		try {
			this.posts = restclient.getPostByUser(uStore.getUser(ctx));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			if(this.posts != null)
			{
				this.postsTitle = new ArrayList<String>();
				List<Post> postArrayList = this.posts.getAllPosts();
				Collections.sort(postArrayList);
				for (Post post : postArrayList) {
			            // creating new HashMap
			            HashMap<String, String> map = new HashMap<String, String>();

			            // adding each child node to HashMap key =&gt; value
			            map.put(KEY_ID, post.getPostId().toString());
			            String posttext = post.getText();
			            if(posttext == null)
			            {
			            	posttext = "";
			            }
			            if(posttext.length() > 15)
			            {
			            	posttext = posttext.substring(0,15);
			            }
			            Date DateObject = new Date(post.getCreated());
			            map.put(KEY_TITLE, posttext);
			            map.put(KEY_ARTIST, post.getCreatedByUser().getName());
			            map.put(KEY_DURATION, DateObject.toString());
			            map.put(KEY_THUMB_URL, webSpaceBaseURL + post.getPostId().toString() + webSpaceEndURL);
			            //map.put(KEY_THUMB_URL, this.testPicPath);
			            // adding HashList to ArrayList
			            postList.add(map);
			        }
			 
					ListView listUsers = (ListView) findViewById(R.id.list);
			 
			        // Getting adapter by passing xml data ArrayList
			        adapter=new LazyAdapter(this, postList);
			        listUsers.setAdapter(adapter);
			 

			listUsers.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					List<Post> postList = posts.getAllPosts();
					Long postId = postList.get(position).getPostId();
					startPostDetail(postId);
				}
			});

			}
			else
			{
			
			this.posts = new Posts();
			this.postsTitle = new ArrayList<String>();
			this.postsTitle.add("No Spots...you better start sharing!");
			ListAdapter listenAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, this.postsTitle);

			ListView listUsers = (ListView) findViewById(R.id.list);
			listUsers.setAdapter(listenAdapter);
			listUsers.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					Intent intent = new Intent(ctx, NewPost.class);
					startActivity(intent);
				}
			});
			}
			
	}

	public void startPostDetail(Long id)
	{
		System.out.println(this.posts.toString());
		Intent intent = new Intent(this, Post_Detail.class);
		intent.putExtra(postId, id);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
	
		
}
