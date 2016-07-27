package com.woyi.towerzj_app.util;

import android.location.LocationManager;


public class UtilTool {
	public static boolean isGpsEnabled(LocationManager locationManager) {
		boolean isOpenGPS = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
		boolean isOpenNetwork = locationManager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER);
		if (isOpenGPS || isOpenNetwork) {
			return true;
		}
		return false;
	} 

}