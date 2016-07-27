package com.woyi.towerzj_app.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class ForwardActivity extends FragmentActivity {
	public static final String PREFERENCES_NAME = "loginName";
	
	private String activityName;//activity 名称
	

	public ApplicationData getAppData(){
		Application application=getApplication();
			return (ApplicationData)application;
	}
	
	
	public String getActivityName() {
		return activityName;
	}
	

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * 页面之间跳转
	 * 
	 * @param packageContext
	 *            跳转前的视图View
	 * @param cls
	 *            要跳转的视图View
	 * @param bundle
	 *            跳转传值
	 */
	public void forwardIntent(Context packageContext, Class<?> cls) {
		this.forwardIntent(packageContext, cls, null);
	}

	/**
	 * 页面之间跳转
	 * 
	 * @param packageContext
	 *            跳转前的视图View
	 * @param cls
	 *            要跳转的视图View
	 * @param bundle
	 *            跳转传值
	 */
	public void forwardIntent(Context packageContext, Class<?> cls,
			Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(packageContext, cls);
		if (bundle != null)
			intent.putExtras(bundle);

		startActivity(intent);
	}

	/**
	 * 页面之间跳转
	 * 
	 * @param flag
	 *            设置跳转标准
	 * @param packageContext
	 *            跳转前的视图View
	 * @param cls
	 *            要跳转的视图View
	 * @param bundle
	 *            跳转传值
	 * @param flag
	 *            是否将跳转前的视图finish，true为finish，否则就保留
	 */

	public void forwardIntent(int flag, Context packageContext, Class<?> cls,
			Bundle bundle, boolean b) {
		Intent intent = new Intent();
		intent.setClass(packageContext, cls);
		intent.setFlags(flag);
		if (bundle != null)
			intent.putExtras(bundle);

		startActivity(intent);
		// overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		if (b)
			this.finish();
	}

	/**
	 * 页面之间跳转
	 * 
	 * @param packageContext
	 *            跳转前的视图View
	 * @param cls
	 *            要跳转的视图View
	 * @param bundle
	 *            跳转传值
	 * @param flag
	 *            是否将跳转钱的视图finish，true为finish，否则就保留
	 */
	public void forwardIntent(Context packageContext, Class<?> cls,
			Bundle bundle, boolean flag) {
		this.forwardIntent(packageContext, cls, bundle);
		if (flag)
			this.finish();
	}

	/**
	 * 页面之间跳转
	 * 
	 * @param intent
	 *            跳转的Intent
	 * @param flag
	 *            是否将原页面finish，true为finish,否则为保留
	 */
	public void forwardIntent(Intent intent, boolean flag) {
		startActivity(intent);
		// overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		if (flag) {
			this.finish();
		}
	}

	/**
	 * 页面之间跳转,并保留当前的视图
	 * 
	 * @param intent
	 *            跳转的Intent
	 */
	public void forwardIntent(Intent intent) {
		forwardIntent(intent, false);
	}

	/**
	 * 使用Toast显示信息
	 * 
	 * @param message
	 *            显示的信息
	 */
	public void toastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	// 检查GPS是否打开
	public boolean isGpsValid() {
		boolean isValid = false;
		LocationManager locationMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
		isValid = locationMgr.isProviderEnabled("gps");
		if (!isValid) {
			Dialog dialog = new AlertDialog.Builder(this)
					.setTitle("设置")
					.setMessage("打开GPS")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								// @Override
								public void onClick(
										DialogInterface dialoginterface, int i) {
									startActivity(new Intent(
											Settings.ACTION_LOCATION_SOURCE_SETTINGS));
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								// @Override
								public void onClick(
										DialogInterface dialoginterface, int i) {

								}
							}).create();
			dialog.show();
		}
		return isValid;
	}

	/**
	 * 使用Toast显示信息
	 * 
	 * @param message
	 *            显示的信息
	 */
	public void toastMessage(Object message) {
		if (message != null) {
			Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		SysExitUtil.remove(this);
	}
	
	/**
	 * 比较两个时间的大小
	 * @param s1
	 * @param s2
	 * @param dateFormatStr
	 * @return
	 */
	public int compareTime(String s1, String s2, String dateFormatStr){
		DateFormat df = new SimpleDateFormat(dateFormatStr);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(s1));
			c2.setTime(df.parse(s2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c1.compareTo(c2);
	}
	

	
	
	
	
}