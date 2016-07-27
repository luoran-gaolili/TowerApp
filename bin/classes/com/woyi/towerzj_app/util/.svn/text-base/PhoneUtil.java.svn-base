package com.woyi.towerzj_app.util;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.telephony.TelephonyManager;

public class PhoneUtil {

	/**
	 * 读取手机串号
	 * 
	 * @param context
	 * @return
	 */
	public static String getPhoneDeviceId(Context context) {
		TelephonyManager tphmanager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tphmanager.getDeviceId();
	}

	/**
	 * 读取手机号
	 * 
	 * @param context
	 * @return
	 */
	public static String getLine1Number(Context context) {
		TelephonyManager tphmanager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tphmanager.getLine1Number();
	}

	public static String getPackageName(Context context) {
		String packageName = context.getPackageName();
		return packageName;
	}

	public static String getTopActivityName(Context context) {
		String topActivityClassName = null;
		ActivityManager activityManager = (ActivityManager) (context
				.getSystemService(android.content.Context.ACTIVITY_SERVICE));
		List<RunningTaskInfo> runningTaskInfos = activityManager
				.getRunningTasks(1);
		if (runningTaskInfos != null) {
			ComponentName f = runningTaskInfos.get(0).topActivity;
			topActivityClassName = f.getClassName();
		}
		return topActivityClassName;
	}

	public static boolean isRunningForeground(Context context) {
		String packageName = getPackageName(context);
		String topActivityClassName = getTopActivityName(context);
		if (packageName != null && topActivityClassName != null
				&& topActivityClassName.startsWith(packageName)) {
			return true;
		} else {
			return false;
		}
	}
}
