package de.ur.mi.android.examples.sightseer.navigation;

import org.xml.sax.ext.Locator2;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import de.ur.mi.android.examples.sightseer.R;
import de.ur.mi.android.examples.sightseer.data.PointOfInterest;
import de.ur.mi.android.examples.sightseer.views.NavigationActivity;

public class NavigationController implements LocationListener {

	private PointOfInterest poi;
	private NavigationListener navigationListener;
	private LocationManager lm;

	public NavigationController(NavigationListener navigationListener,
			PointOfInterest poi, LocationManager lm) {
		this.navigationListener = navigationListener;
		this.poi = poi;
		this.lm = lm;
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	@Override
	public void onLocationChanged(Location location) {	
		float[] results = new float[3];
		Location.distanceBetween(location.getLatitude(), location
				.getLongitude(), poi.getLocation().getLatitude(), poi
				.getLocation().getLongitude(), results);
		navigationListener.onNavigationDetailChanged(new NavigationDetail(poi
				.getTitle(), results[0], results[1]));
		if(results[0] < 1){
			navigationListener.onTargetReached(poi);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		if (status == GpsStatus.GPS_EVENT_STARTED
				|| status == GpsStatus.GPS_EVENT_FIRST_FIX) {
			navigationListener.onSignalFound();
		}
		if (status == GpsStatus.GPS_EVENT_STOPPED) {
			navigationListener.onSignalLost();
		}
	}
	
	public float getDirection () {
		Criteria criteria = new Criteria();
		String bestProvider = lm.getBestProvider(criteria, false);
		Location lastKnownLocation = lm.getLastKnownLocation(bestProvider);
		float bearing = lastKnownLocation.bearingTo(poi.getLocation());
		float currentBearing = lastKnownLocation.getBearing();
		float direction = bearing - currentBearing;
		return direction;
	}
}
