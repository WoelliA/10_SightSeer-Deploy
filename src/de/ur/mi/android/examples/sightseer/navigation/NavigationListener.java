package de.ur.mi.android.examples.sightseer.navigation;

import de.ur.mi.android.examples.sightseer.data.PointOfInterest;


public interface NavigationListener {

	public void onSignalFound();
	public void onSignalLost();
	public void onTargetChanged(PointOfInterest poi);
	public void onTargetReached(PointOfInterest poi);
	public void onNavigationDetailChanged(NavigationDetail navDetail);

}
