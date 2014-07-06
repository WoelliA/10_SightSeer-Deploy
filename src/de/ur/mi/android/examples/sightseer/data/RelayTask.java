package de.ur.mi.android.examples.sightseer.data;

import android.os.AsyncTask;


public class RelayTask extends AsyncTask<String, Integer, JsonResult> {

	private JsonRequest request;
	private TaskListener<JsonResult> listener;


	public RelayTask(TaskListener<JsonResult> listener, JsonRequest request){
		this.listener = listener;
		this.request = request;			
	}	 
	
	@Override
	protected void onPostExecute(JsonResult result) {
		listener.onRequestFinished(result);
		super.onPostExecute(result);
	}
	
	@Override
	protected JsonResult doInBackground(String... arg0) {
		try {
			return request.execute();
		} catch (Exception e) {
			// TODO: handle exception
			listener.onRequestFailed(0, "");
			
		}
		return null;
	}
}
