package com.woyi.towerzj_app.bean.xudianchi;

import java.util.ArrayList;
import java.util.List;

import com.woyi.towerzj_app.bean.AppBaseBean;

/**
 * 
* 此类描述的是：蓄电池
* @author: 罗然
* @version: 2015-7-19 下午6:45:35
* @ClassName: AppBatteryList 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean.xudianchi
 */
public class AppBatteryList extends AppBaseBean{

	private List<AppBatteryBean> appBatteryList = new ArrayList<AppBatteryBean>();

	public List<AppBatteryBean> getAppBatteryList() {
		return appBatteryList;
	}

	public void setAppBatteryList(List<AppBatteryBean> appBatteryList) {
		this.appBatteryList = appBatteryList;
	}
	
	
}