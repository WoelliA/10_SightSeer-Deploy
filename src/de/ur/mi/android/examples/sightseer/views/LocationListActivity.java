package de.ur.mi.android.examples.sightseer.views;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LauncherActivity.ListItem;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import de.ur.mi.android.examples.sightseer.R;
import de.ur.mi.android.examples.sightseer.data.DataController;
import de.ur.mi.android.examples.sightseer.data.IDataListener;
import de.ur.mi.android.examples.sightseer.data.LocationDatabase;
import de.ur.mi.android.examples.sightseer.data.PointOfInterest;
import de.ur.mi.android.examples.sightseer.helpers.IntentHelper;
import de.ur.mi.android.examples.sightseer.navigation.NavigationController;
import android.content.DialogInterface.OnClickListener;

public class LocationListActivity extends Activity implements IDataListener, OnItemClickListener{

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
		listView.setOnItemClickListener(this);

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
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle(getResources().getString(
				R.string.update_dialog_title));
		progressDialog.setMessage(getResources().getString(
				R.string.update_dialog_text));
		progressDialog.show();

	}

	@Override
	public void onDataUpdateCanceled() {
		if (progressDialog != null)
			progressDialog.dismiss();
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(getResources().getString(
				R.string.network_error_title));
		alertDialogBuilder.setMessage(getResources().getString(
				R.string.network_error_text));
		alertDialogBuilder.setCancelable(false);
		alertDialogBuilder.setPositiveButton(R.string.error_dialog_confirm,
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						controller.startGetData();

					}
				});
		alertDialogBuilder.create().show();

	}

	@Override
	public void onDataUpdateCompleted() {
		if (progressDialog != null)
			progressDialog.dismiss();

	}

	@Override
	public void onNewDataAvailable(ArrayList<PointOfInterest> pois) {
		// TODO Auto-generated method stub
		adapter = new LocationListAdapter(this, pois);
		listView.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			Object item = (Object) adapter.getItem(arg2);
			PointOfInterest poi = (PointOfInterest) item;
			
			IntentHelper.navigateTo(this, NavigationActivity.class, poi);
			
	}

}
