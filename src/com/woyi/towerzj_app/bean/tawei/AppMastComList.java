package com.woyi.towerzj_app.bean.tawei;

import java.util.ArrayList;
import java.util.List;

import com.woyi.towerzj_app.bean.AppBaseBean;

/**
 * 
* �����������ǣ���Φ��Ӫ��
* @author: ��Ȼ
* @version: 2015-7-19 ����2:15:41
* @ClassName: AppMastComList 
* @��Ŀ�� towerzj_app
* @����com.woyi.towerzj_app.bean.tawei
 */
public class AppMastComList extends AppBaseBean{

	private List<AppMastComBean> appMastComList = new ArrayList<AppMastComBean>();

	public List<AppMastComBean> getAppMastComList() {
		return appMastComList;
	}
	public void setAppMastComList(List<AppMastComBean> appMastComList) {
		this.appMastComList = appMastComList;
	}

	
}