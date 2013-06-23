package de.hska.shareyourspot.android.activites;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Parties;
import de.hska.shareyourspot.android.domain.Party;
import de.hska.shareyourspot.android.domain.Post;
import de.hska.shareyourspot.android.domain.Posts;
import de.hska.shareyourspot.android.helper.AlertHelper;
import de.hska.shareyourspot.android.helper.GoogleMapsHelper;
import de.hska.shareyourspot.android.helper.UserStore;
import de.hska.shareyourspot.android.restclient.RestClient;

public class NewPost extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int PICTURE_COMPRESS_RATE = 100;
	private UserStore uStore = new UserStore();
	private RestClient restClient = new RestClient();
	private Parties parties = new Parties();
	private Context ctx = this;
	private List<String> groupList;
	public final String tabIndex = "tabIndex";
	//TODO CHANGE TO DB DATA
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.parties = new Parties();
		this.groupList = new ArrayList();
		try {
			parties = restClient.getPartiesByUser(uStore.getUser(ctx));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(parties == null)
			{
			
				AlertHelper alert = new AlertHelper(ctx, R.string.ShareFailureTitle, R.string.ShareFailureText);
				alert.alertDialogBuilder.setCancelable(false);
				alert.alertDialogBuilder.setNeutralButton("Go to groups", new DialogInterface.OnClickListener() {          
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			            dialog.dismiss();
			            startGroupCreate();
			            
			        }
			    });
				alert.fireAlert();
				
	        
			
				
			
			return;
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
	
	public void startGroupCreate()
	{
		Intent intent = new Intent(this, NewGroup.class);
		startActivity(intent);
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
		Party selectedParty = new Party();
		if(this.parties == null)
		{
			//Hier müssen die parties nachgeladen werden
		}
		else
		{
			Spinner spinnerView = (Spinner) findViewById(R.id.groupSpinner);
			String groupSpinnertext = spinnerView.getSelectedItem().toString();
			for(Party p : this.parties.getAllParties())
			{
				if(p.getName() == groupSpinnertext)
				{
					selectedParty = p;
				}
			}
		}
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
				

		//Get PossitionData
		Location location = locationHelper.getLocation();
				
				Post post = new Post(postText, selectedParty);
				post.setCreatedByUser(uStore.getUser(ctx));
				post.setLatitude(location.getLatitude());
				post.setLongitude(location.getLongitude());
				Post newPost = restClient.createPost(post);	
		
		//Create
		if(newPost == null)
			{
			new AlertHelper(ctx, R.string.loginFailureTitle,
					R.string.loginFailureText, "Retry").fireAlert();
			return;
			}
				
		String path = getCacheDir().toString();
		File outputDir = new File(path);
		File f = new File(path+File.separator+ newPost.getPostId() +".jpg");

		        if (!outputDir.exists()) {
		             outputDir.mkdir();

		        }
		       
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, PICTURE_COMPRESS_RATE, stream);
		byte[] imageInByte = stream.toByteArray();

        //write the bytes in file
        FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        try {
			fos.write(imageInByte);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        new uploadImageAsyncTask().execute(f);	
				
		Intent intent = new Intent(this, AndroidTabLayoutActivity.class);
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
				Intent intent = new Intent(this, AndroidTabLayoutActivity.class);
				intent.putExtra(tabIndex, 2);
				startActivity(intent);
			} else {
				Intent intent = new Intent(this, NewPost.class);
				startActivity(intent);
			}
		}
	}
	
	public class ProgressInputStream extends InputStream {

	    /* Key to retrieve progress value from message bundle passed to handler */
	    public static final String PROGRESS_UPDATE = "progress_update";

	    private static final int TEN_KILOBYTES = 1024 * 40;

	    private InputStream inputStream;
	    //private Handler handler;

	    private long progress;
	    private long lastUpdate;

	    private boolean closed;

	    public ProgressInputStream(InputStream inputStream) {
	        this.inputStream = inputStream;

	        this.progress = 0;
	        this.lastUpdate = 0;

	        this.closed = false;
	    }

	    @Override
	    public int read() throws IOException {
	        int count = inputStream.read();
	        return incrementCounterAndUpdateDisplay(count);
	    }

	    @Override
	    public int read(byte[] b, int off, int len) throws IOException {
	        int count = inputStream.read(b, off, len);
	        return incrementCounterAndUpdateDisplay(count);
	    }

	    @Override
	    public void close() throws IOException {
	        super.close();
	        if (closed)
	            throw new IOException("already closed");
	        closed = true;
	    }

	    private int incrementCounterAndUpdateDisplay(int count) {
	        if (count < 0)
	            progress += count;
	        lastUpdate = maybeUpdateDisplay(progress, lastUpdate);
	        return count;
	    }

	    private long maybeUpdateDisplay(long progress, long lastUpdate) {
	        if (progress - lastUpdate < TEN_KILOBYTES) {
	            lastUpdate = progress;
	            sendLong(PROGRESS_UPDATE, progress);
	        }
	        return lastUpdate;
	    }

	    public void sendLong(String key, long value) {
	        Bundle data = new Bundle();
	        data.putLong(key, value);

	        Message message = Message.obtain();
	        message.setData(data);
	    }
	}

	private class uploadImageAsyncTask extends AsyncTask<File, Void, Void> {

		@Override
		protected Void doInBackground(File... params) {
	            FTPClient ftpClient = new FTPClient();

	            try {
					ftpClient.connect(InetAddress
					        .getByName("hskaebusiness.square7.ch"));
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					ftpClient.login("hskaebusiness_AndroidApp", "123123123");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					System.out.println("status :: " + ftpClient.getStatus());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	            try {
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	                        //Your File path set here 
//	                        File file = new File("/sdcard/my pictures/image.png");  
	            BufferedInputStream buffIn = null;
				try {
					buffIn = new BufferedInputStream(
					        new FileInputStream(params[0]));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            ftpClient.enterLocalPassiveMode();
	            ProgressInputStream progressInput = new ProgressInputStream(
	                    buffIn);

	            boolean result = false;
				try {
					result = ftpClient.storeFile(params[0].getName(),
					        progressInput);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	            System.out.println("result is  :: " + result);
	            try {
					buffIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					ftpClient.logout();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					ftpClient.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	            return null;
		}
	}
}
