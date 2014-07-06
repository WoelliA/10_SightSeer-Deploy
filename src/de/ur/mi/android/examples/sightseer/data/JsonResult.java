package de.ur.mi.android.examples.sightseer.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.JsonReader;

public class JsonResult {

	private String requestIdentifier;
	private JSONObject obj;


	public JsonResult(String responseString, String requestIdentifier) {
		this.requestIdentifier = requestIdentifier;
		try {
			obj = new JSONObject(responseString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getIdentifier() {
		return requestIdentifier;
	}

	public JSONObject getJsonObject() {
		
		return obj;
	}
	


}
