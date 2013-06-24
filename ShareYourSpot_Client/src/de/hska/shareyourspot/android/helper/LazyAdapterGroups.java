package de.hska.shareyourspot.android.helper;

import java.util.ArrayList;
import java.util.HashMap;

import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.activites.PostList;
import de.hska.shareyourspot.android.imageLoader.ImageLoader;
 
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class LazyAdapterGroups extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;

 
    public LazyAdapterGroups(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_group, null);
 
        TextView title = (TextView)vi.findViewById(R.id.title); // title
         
        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);
 
        // Setting all values in listview
        title.setText(song.get(PostList.KEY_TITLE));
        return vi;
    }
}