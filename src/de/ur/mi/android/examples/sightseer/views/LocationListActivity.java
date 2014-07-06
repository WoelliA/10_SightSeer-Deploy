package de.ur.mi.android.examples.sightseer.views;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import de.ur.mi.android.examples.sightseer.R;
import de.ur.mi.android.examples.sightseer.data.DataController;
import de.ur.mi.android.examples.sightseer.data.IDataListener;
import de.ur.mi.android.examples.sightseer.data.LocationDatabase;
import de.ur.mi.android.examples.sightseer.data.PointOfInterest;
import android.content.DialogInterface.OnClickListener;

public class LocationListActivity extends Activity implements IDataListener {
	
	ListView listView;
	LocationDatabase db;
	LocationListAdapter adapter;
	DataController controller;
	ProgressDialog progressDialog;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		init();
	}
	
	private void init() {
		initNavigation();
		initUI();
		initLocationData();
	}
	
	private void initNavigation() {
		
	}
	
	private void initUI() {
		setContentView(R.layout.location_list_activity);
		listView = (ListView) findViewById(R.id.listViewLocations);
	}
	
	
	
	private void initLocationData() {
		controller = new DataController(this, this);
		controller.startGetData();
		
		
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.menu_update_sights:
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}

	@Override
	public void onDataUpdateInitiated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDataUpdateCanceled() {
		if(progressDialog != null)
			progressDialog.dismiss();
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setTitle(getResources().getString(R.string.network_error_title));
			alertDialogBuilder.setMessage(getResources().getString(R.string.network_error_text));
			alertDialogBuilder.setCancelable(false);
			alertDialogBuilder.setPositiveButton(R.string.error_dialog_dismiss_text, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					controller.startGetData();
					
				}
			});
			alertDialogBuilder.create().show();
		
	}

	@Override
	public void onDataUpdateCompleted() {
		if(progressDialog != null)
			progressDialog.dismiss();
		
	}

	@Override
	public void onNewDataAvailable(ArrayList<PointOfInterest> pois) {
		// TODO Auto-generated method stub
		adapter = new LocationListAdapter(this, pois);
		listView.setAdapter(adapter);
	}


}
