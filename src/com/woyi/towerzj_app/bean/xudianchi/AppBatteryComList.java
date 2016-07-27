package com.woyi.towerzj_app.bean.xudianchi;

import java.util.ArrayList;
import java.util.List;

import com.woyi.towerzj_app.bean.AppBaseBean;

/**
 * 
* 此类描述的是：蓄电池运营商
* @author: 罗然
* @version: 2015-7-19 下午6:46:05
* @ClassName: AppBatteryComList 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean.xudianchi
 */
public class AppBatteryComList extends AppBaseBean{

	private List<AppBatteryComBean> appBatteryComList = new ArrayList<AppBatteryComBean>();

	public List<AppBatteryComBean> getAppBatteryComList() {
		return appBatteryComList;
	}
	public void setAppBatteryComList(List<AppBatteryComBean> appBatteryComList) {
		this.appBatteryComList = appBatteryComList;
	}

	
}