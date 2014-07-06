package de.ur.mi.android.examples.sightseer.data;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import de.ur.mi.android.examples.sightseer.config.AppConfig;

public class LocationDatabase {
	@SuppressWarnings("unused")
	private LocationDatabaseHelper helper;

	public LocationDatabase(Context context) {
		helper = new LocationDatabaseHelper(context, AppConfig.Data.DATABASE_KEY, null, AppConfig.Data.DATABASE_VERSION);
	}

	
	private class LocationDatabaseHelper extends SQLiteOpenHelper {
	
		private static final String DATABASE_CREATE = "create table "
				+ AppConfig.Data.TABLE_KEY + " ("
				+ AppConfig.Data.ID_KEY + " integer primary key autoincrement, "
				+ AppConfig.Data.TITLE_KEY + " text not null, " 
				+ AppConfig.Data.INFO_KEY + " text not null, "
				+ AppConfig.Data.LASTVISIT_KEY + " text not null, "
				+ AppConfig.Data.LATITUDE_KEY + " real not null, "
				+ AppConfig.Data.LONGITUDE_KEY + " real not null, "
				+ AppConfig.Data.ALTITUDE_KEY + " real not null);";

		
		public LocationDatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

	}


	public void update(ArrayList<PointOfInterest> locations) {
		// TODO Auto-generated method stub
		for (int i = 0; i < locations.size(); i++) {
			ContentValues values = new ContentValues();
			PointOfInterest poi = locations.get(i);
			values.put(AppConfig.Data.ID_KEY, poi.getId());
			values.put( AppConfig.Data.TITLE_KEY, poi.getTitle());
			values.put( AppConfig.Data.INFO_KEY ,poi.getInfo());
			values.put( AppConfig.Data.LASTVISIT_KEY ,poi.getLastVisited());
			values.put( AppConfig.Data.LONGITUDE_KEY ,poi.getLocation().getLongitude());
			values.put( AppConfig.Data.ALTITUDE_KEY ,poi.getLocation().getAltitude());
			
			SQLiteDatabase db = this.helper.getWritableDatabase();
			int updateRows = db.update(AppConfig.Data.DATABASE_KEY, values, AppConfig.Data.ID_KEY+"=?", new String[]{poi.getId()+""});
			if(updateRows == 0){
				db.insert(AppConfig.Data.TABLE_KEY, null, values);
			}
		}
	}

 }
