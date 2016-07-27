package com.woyi.towerzj_app.util;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class MapApplication extends Application {

	private static MapApplication mInstance = null;
	public boolean m_bKeyRight = true;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
		CrashHandler crashHandler = CrashHandler.getInstance();  
		crashHandler.init(getApplicationContext());  
	}

	public static MapApplication getInstance() {
		return mInstance;

	}

}