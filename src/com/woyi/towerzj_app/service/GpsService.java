package com.woyi.towerzj_app.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;

import com.woyi.towerzj_app.util.UtilTool;

public class GpsService extends Service {
	private Gps gps=null;
	private boolean threadDisable=false; 

	@Override
	public void onCreate() {
		super.onCreate();

		gps=new Gps(GpsService.this);

		new Thread(new Runnable(){
			@Override
			public void run() {
				while (!threadDisable) { 
					if(!UtilTool.isGpsEnabled((LocationManager)getSystemService(Context.LOCATION_SERVICE))){
						Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);  
						startActivity(callGPSSettingIntent);
						return;
					} 
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Location location=null;
					if(gps!=null){ //当结束服务时gps为空
						//获取经纬度
						location=gps.getLocation();
						if(location==null){
						}
					}

					//发送广播
					Intent intent=new Intent();
					intent.putExtra("lat", location==null?"":location.getLatitude()+""); 
					intent.putExtra("lon", location==null?"":location.getLongitude()+""); 
					intent.setAction("com.woyi.towerzj_app.service.GpsService"); 
					sendBroadcast(intent);
				}

			}
		}).start();

	}

	@Override
	public void onDestroy() {
		threadDisable=true;
		if(gps!=null){
			gps.closeLocation();
			gps=null;
		}
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}


}