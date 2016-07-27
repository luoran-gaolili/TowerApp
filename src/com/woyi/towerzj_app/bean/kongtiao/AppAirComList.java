package com.woyi.towerzj_app.bean.kongtiao;

import java.util.ArrayList;
import java.util.List;

import com.woyi.towerzj_app.bean.AppBaseBean;

/**
 * 
* 此类描述的是：空调运营商
* @author: 罗然
* @version: 2015-7-19 下午6:05:10
* @ClassName: AppAirComList 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean.kongtiao
 */
public class AppAirComList extends AppBaseBean{

	private List<AppAirComBean> appAirComList = new ArrayList<AppAirComBean>();

	public List<AppAirComBean> getAppAirComList() {
		return appAirComList;
	}
	public void setAppAirComList(List<AppAirComBean> appAirComList) {
		this.appAirComList = appAirComList;
	}

	
}