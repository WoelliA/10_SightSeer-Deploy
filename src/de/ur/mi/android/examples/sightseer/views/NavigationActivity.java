package de.ur.mi.android.examples.sightseer.views;

import de.ur.mi.android.examples.sightseer.R;
import de.ur.mi.android.examples.sightseer.data.PointOfInterest;
import de.ur.mi.android.examples.sightseer.helpers.IntentHelper;
import de.ur.mi.android.examples.sightseer.navigation.NavigationController;
import de.ur.mi.android.examples.sightseer.navigation.NavigationDetail;
import de.ur.mi.android.examples.sightseer.navigation.NavigationListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Point;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent.PointerProperties;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NavigationActivity extends Activity implements NavigationListener,
		OnClickListener {

	PointOfInterest poi;
	NavigationController controller;
	TextView gpsSignal;
	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		initUI();
		initNavigation();
	}

	private void initUI() {
		TextView naviHeader = (TextView) findViewById(R.id.texViewDestiantion);
		TextView naviDistance = (TextView) findViewById(R.id.textViewDistance);
		gpsSignal = (TextView) findViewById(R.id.textViewGPSSignal);

		naviHeader.setText(poi.getTitle());
		naviDistance.setText("2");

	}

	private void initNavigation() {
		Bundle bundle = getIntent().getExtras();
		long id = (Long) bundle.get("id");
		poi = IntentHelper.getPoiForID(id);
		LocationManager lm = (LocationManager) this
				.getSystemService(LOCATION_SERVICE);
		this.controller = new NavigationController(this, poi, lm);

	}

	@Override
	public void onSignalFound() {
		gpsSignal.setVisibility(View.GONE);
	}

	@Override
	public void onSignalLost() {
		gpsSignal.setVisibility(View.VISIBLE);

	}

	@Override
	public void onTargetChanged(PointOfInterest poi) {

	}

	@Override
	public void onTargetReached(PointOfInterest poi) {
		if (progressDialog != null)
			progressDialog.dismiss();
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(getResources().getString(
				R.string.destination_reached_dialog_title));
		alertDialogBuilder.setMessage(getResources().getString(
				R.string.destination_reached_dialog_text));
		alertDialogBuilder.setCancelable(false);
		alertDialogBuilder.setPositiveButton(R.string.error_dialog_confirm,
				this);
		alertDialogBuilder.create().show();

	}

	@Override
	public void onNavigationDetailChanged(NavigationDetail navDetail) {
		ImageView navigationCompass = (ImageView) findViewById(R.id.imageViewCompass);
		navigationCompass.setRotation(0);
		navigationCompass.setRotation(controller.getDirection());

	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		Intent intent = new Intent(this, LocationListActivity.class);
		this.startActivity(intent);
	}

}
