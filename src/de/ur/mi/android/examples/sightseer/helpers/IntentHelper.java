package de.ur.mi.android.examples.sightseer.helpers;

import java.util.HashMap;
import java.util.HashSet;

import android.content.Context;
import android.content.Intent;
import de.ur.mi.android.examples.sightseer.data.PointOfInterest;
import de.ur.mi.android.examples.sightseer.views.NavigationActivity;

public class IntentHelper {
	
	private static HashMap<Long,PointOfInterest> pois = new HashMap<Long, PointOfInterest>();

	public static void navigateTo(Context context,
			Class<NavigationActivity> class1, PointOfInterest poi) {
		pois.put(poi.getId(), poi);
		
		Intent poiSelect = new Intent(context, NavigationActivity.class);
		poiSelect.putExtra("id", poi.getId());
		context.startActivity(poiSelect);
	}
	
	public static PointOfInterest getPoiForID (long ID){
		
		return pois.get(ID);
	}
}
