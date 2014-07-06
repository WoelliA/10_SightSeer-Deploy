package de.ur.mi.android.examples.sightseer.data;

import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.location.LocationManager;
import de.ur.mi.android.examples.sightseer.config.AppConfig;

public final class PointOfInterest{
	
	private final long id;
	private final String title;
	private final String info;
	private final String lastVisited;
	private final double latitude;
	private final double longitude;
	private final double altitude;
	
	public PointOfInterest(long id, String title, String info,
			String lastVisited, double latitude, double longitude, double altitude) {
		this.id = id;
		this.title = title;
		this.info = info;
		this.lastVisited = lastVisited;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}
	
	public PointOfInterest(JSONObject json) throws JSONException {
		this.id = json.getLong(AppConfig.Data.ID_KEY);
		this.title = json.getString(AppConfig.Data.TITLE_KEY);
		this.info = json.getString(AppConfig.Data.INFO_KEY);
		this.lastVisited = "";
		this.latitude = json.getDouble(AppConfig.Data.LATITUDE_KEY);
		this.longitude = json.getDouble(AppConfig.Data.LONGITUDE_KEY);;
		this.altitude = json.getDouble(AppConfig.Data.ALTITUDE_KEY);;
	}
	
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getInfo() {
		return info;
	}

	public String getLastVisited() {
		return lastVisited;
	}

	public Location getLocation() {
		Location location = new Location(LocationManager.GPS_PROVIDER);
		location.setAltitude(altitude);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		return location;
	}

	@Override
	public String toString() {
		return title;
	}
	
	public String toJson() {
		return "{\"_id\":\""+id+"\",\"_title\":\""+title+"\",\"_info\":\""+info+"\",\"_latitude\":\""+latitude+"\",\"_longitude\":\""+longitude+"\",\"_altitude\":\""+altitude+"\"}";
	}
	

}
