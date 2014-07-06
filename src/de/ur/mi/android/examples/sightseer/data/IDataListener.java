package de.ur.mi.android.examples.sightseer.data;

import java.util.ArrayList;

public interface IDataListener {

	public void onDataUpdateInitiated();
	public void onDataUpdateCanceled();
	public void onDataUpdateCompleted();
	public void onNewDataAvailable(ArrayList<PointOfInterest> pois);
}
