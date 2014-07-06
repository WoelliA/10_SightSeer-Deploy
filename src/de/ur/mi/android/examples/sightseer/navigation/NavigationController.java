package de.ur.mi.android.examples.sightseer.navigation;

import org.xml.sax.ext.Locator2;

import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import de.ur.mi.android.examples.sightseer.data.PointOfInterest;
import de.ur.mi.android.examples.sightseer.views.NavigationActivity;



public class NavigationController implements LocationListener{
	
	
	private PointOfInterest poi;
	private NavigationListener navigationListener;
	private LocationManager lm;
	

	public NavigationController(NavigationListener navigationListener, PointOfInterest poi, LocationManager lm) {
		this.navigationListener = navigationListener;
		this.poi = poi;
		this.lm =lm;
	}
	

	private void lastLocation (){
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
		//navigationListener.
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
}
