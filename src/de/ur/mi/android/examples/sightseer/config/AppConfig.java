package de.ur.mi.android.examples.sightseer.config;

public class AppConfig {
	
	public class Server {
		public static final String URL_GET_ALL = "http://132.199.139.24/~baa56852/ursightseer/api/v1/sights/all/";
	}
	
	
	public class Sensors {
		public static final int GPS_UPDATE_INTERVALL = 500;
		public static final int GPS_DISTANCE_THRESHOLD = 1;
	}
	
	public class Data {
		public static final int DATABASE_VERSION = 1;
		public static final String DATABASE_KEY = "sightseer";
		public static final String TABLE_KEY = "_locations";
		public static final String ID_KEY = "_id";
		public static final String TITLE_KEY = "_title";
		public static final String INFO_KEY = "_info";
		public static final String LASTVISIT_KEY = "_lastVisit";
		public static final String LATITUDE_KEY = "_latitude";
		public static final String LONGITUDE_KEY = "_longitude";
		public static final String ALTITUDE_KEY = "_altitude";
		
	}
	
}
