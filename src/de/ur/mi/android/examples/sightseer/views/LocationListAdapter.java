package de.ur.mi.android.examples.sightseer.views;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import de.ur.mi.android.examples.sightseer.R;
import de.ur.mi.android.examples.sightseer.data.PointOfInterest;

public class LocationListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<PointOfInterest> locations;

	public LocationListAdapter(Context context, ArrayList<PointOfInterest> locations) {
		this.context = context;
		this.locations = locations;
	}

	@Override
	public int getCount() {
		return locations.size();
	}

	@Override
	public Object getItem(int position) {
		return locations.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.location_list_item, null);
        }
		
        return convertView;
	}
	


}
