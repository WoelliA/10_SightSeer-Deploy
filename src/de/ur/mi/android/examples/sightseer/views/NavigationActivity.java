package de.ur.mi.android.examples.sightseer.views;

import de.ur.mi.android.examples.sightseer.R;
import de.ur.mi.android.examples.sightseer.data.PointOfInterest;
import de.ur.mi.android.examples.sightseer.helpers.IntentHelper;
import de.ur.mi.android.examples.sightseer.navigation.NavigationController;
import de.ur.mi.android.examples.sightseer.navigation.NavigationDetail;
import de.ur.mi.android.examples.sightseer.navigation.NavigationListener;
import android.app.Activity;
import android.graphics.Point;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent.PointerProperties;
import android.widget.TextView;

public class NavigationActivity extends Activity implements NavigationListener {

	PointOfInterest poi;
	NavigationController controller;

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
		TextView gpsSignal = (TextView) findViewById(R.id.textViewGPSSignal);

		naviHeader.setText(poi.getTitle());
		naviDistance.setText("2");

	}

	private void initNavigation() {
		Bundle bundle = getIntent().getExtras();
		long id = (Long) bundle.get("id");
		poi = IntentHelper.getPoiForID(id);
		LocationManager lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
		this.controller = new NavigationController(this, poi, lm);

	}


	@Override
	public void onSignalFound() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSignalLost() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTargetChanged(PointOfInterest poi) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTargetReached(PointOfInterest poi) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNavigationDetailChanged(NavigationDetail navDetail) {
		// TODO Auto-generated method stub

	}

}
