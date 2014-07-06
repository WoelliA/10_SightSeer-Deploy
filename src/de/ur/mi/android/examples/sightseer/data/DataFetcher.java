package de.ur.mi.android.examples.sightseer.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.ur.mi.android.examples.sightseer.config.AppConfig;
import android.os.AsyncTask;

public class DataFetcher implements TaskListener<JsonResult>{

	private IDataFetcherListener listener;
	private RelayTask relayTask;

	public DataFetcher(IDataFetcherListener listener) {
		this.listener = listener;
		JsonRequest request = new JsonRequest(AppConfig.Server.URL_GET_ALL, "");
		this.relayTask = new RelayTask(this , request);
	}

	private ArrayList<PointOfInterest> deserialize(JSONObject jsonObject) {
		JSONArray sights;
		ArrayList<PointOfInterest> poiList = new ArrayList<PointOfInterest>();
		try {
			sights = jsonObject.getJSONArray("sights");
			for (int i = 0; i < sights.length(); i++) {
				JSONObject sight = sights.getJSONObject(i);
				PointOfInterest poi = new PointOfInterest(sight);
				poiList.add(poi);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return poiList;
	}
	
	@Override
	public void onRequestFinished(JsonResult res) {
		// TODO Auto-generated method stub
		listener.onDataFetched(deserialize(res.getJsonObject()));
	}
	
	public void startGetResults (){
		this.relayTask.execute("");
	}

	@Override
	public void onRequestFailed(int errorID, String message) {
		// TODO Auto-generated method stub
		listener.onError(errorID);
	}
	
	
}