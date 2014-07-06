package de.ur.mi.android.examples.sightseer.data;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;

public class DataController implements IDataFetcherListener{
	
	
	private Context context;
	private IDataListener listener;
	private LocationDatabase locDB;
	
	

	public DataController(Context context, IDataListener listener) {
		locDB = new LocationDatabase(context);
		this.context = context;
		this.listener = listener;
	}

	public void startGetData(){
		listener.onDataUpdateInitiated();
		DataFetcher fetcher = new DataFetcher(this);
		fetcher.startGetResults();
	}

	@Override
	public void onDataFetched(ArrayList<PointOfInterest> locations) {
		// TODO Auto-generated method stub
		listener.onNewDataAvailable(locations);
		locDB.update(locations);
		
		
		
	}

	@Override
	public void onError(int error) {
		// TODO Auto-generated method stub
		listener.onDataUpdateCanceled();
	}

}
