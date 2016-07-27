package com.woyi.towerzj_app.bean.peidianx;

import java.util.ArrayList;
import java.util.List;

import com.woyi.towerzj_app.bean.AppBaseBean;

/**
 * 
* 此类描述的是：配电箱
* @author: 罗然
* @version: 2015-7-20 上午9:56:31
* @ClassName: AppPowerList 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean.peidianx
 */
public class AppPowerList extends AppBaseBean{

	private List<AppPowerBean> appPowerList = new ArrayList<AppPowerBean>();

	public List<AppPowerBean> getAppPowerList() {
		return appPowerList;
	}

	public void setAppPowerList(List<AppPowerBean> appPowerList) {
		this.appPowerList = appPowerList;
	}
	
	
}