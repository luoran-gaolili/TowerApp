package com.woyi.towerzj_app.bean.tawei;

import java.util.ArrayList;
import java.util.List;

import com.woyi.towerzj_app.bean.AppBaseBean;

/**
 * 
* �����������ǣ���Φ
* @author: ��Ȼ
* @version: 2015-7-19 ����2:15:04
* @ClassName: AppMastList 
* @��Ŀ�� towerzj_app
* @����com.woyi.towerzj_app.bean.tawei
 */
public class AppMastList extends AppBaseBean{

	private List<AppMastBean> appMastList = new ArrayList<AppMastBean>();

	public List<AppMastBean> getAppMastList() {
		return appMastList;
	}

	public void setAppMastList(List<AppMastBean> appMastList) {
		this.appMastList = appMastList;
	}
	
	
}