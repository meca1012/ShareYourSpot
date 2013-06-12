package de.hska.shareyourspot.android.activites;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.domain.Party;

public class Groups extends Activity {

	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.groups);

		// TODO: load groups of the user
		
			
				@SuppressWarnings("rawtypes")
				List valueList = new ArrayList<String>();
				
				for (int i = 0; i < 10; i++) {
				int group = 0;
				valueList.add(group+i);		
			}
			
				@SuppressWarnings({ "rawtypes" })
				ListAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, valueList);
				final ListView lv = (ListView)findViewById(R.id.list_groups);

				lv.setAdapter(adapter);
	}

	public void showSpots(View view) {
		Intent intent = new Intent(this, PostList.class);
		startActivity(intent);
	}

	public void newGroup(View view) {
		Intent intent = new Intent(this, NewGroup.class);
		startActivity(intent);
	}

	

}
