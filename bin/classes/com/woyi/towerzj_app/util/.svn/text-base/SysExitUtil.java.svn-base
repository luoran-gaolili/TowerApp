package com.woyi.towerzj_app.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 关闭掉所有的activity，退出程序
 * 
 * @author dongkt
 * @date 2014-9-3
 * @time 下午3:18:10
 */
public class SysExitUtil {

	// 建立一个public static的list用来放activity
	public static List<ForwardActivity> activityList = new ArrayList<ForwardActivity>();

	// finish所有list中的activity
	public static void exit() {
		int siz = activityList.size();
		for (int i = 0; i < siz; i++) {
			if (activityList.get(i) != null) {
				((ForwardActivity) activityList.get(i)).finish();
			}
		}
	}

	public static void remove(ForwardActivity o) {
		if (activityList != null && activityList.size() > 0) {
			activityList.remove(o); 
		}
	}

	public static ForwardActivity isExisit(String activityName) {
		if (activityName == null || activityName.length() <= 0) {
			return null;
		}
		if (activityList != null && activityList.size() > 0) {
			for (ForwardActivity ac : activityList) {
				if (activityName.equals(ac.getActivityName())) {
					return ac;
				}
			}
		}
		return null;
	}
}