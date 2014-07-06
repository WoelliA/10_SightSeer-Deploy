package de.ur.mi.android.examples.sightseer.data;

import java.util.ArrayList;

public interface IDataFetcherListener {
	public void onDataFetched(ArrayList<PointOfInterest> locations);
	public void onError(int error);
}