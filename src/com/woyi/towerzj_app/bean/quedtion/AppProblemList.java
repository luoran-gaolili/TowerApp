package com.woyi.towerzj_app.bean.quedtion;

import java.util.ArrayList;
import java.util.List;

import com.woyi.towerzj_app.bean.AppBaseBean;

/**
 * 
* �����������ǣ�����
* @author: ��Ȼ
* @version: 2015-7-20 ����10:19:22
* @ClassName: AppProblemList 
* @��Ŀ�� towerzj_app
* @����com.woyi.towerzj_app.bean.quedtion
 */
public class AppProblemList extends AppBaseBean{

	private List<AppProblemBean> AppProblemList = new ArrayList<AppProblemBean>();

	public List<AppProblemBean> getAppProblemList() {
		return AppProblemList;
	}

	public void setAppProblemList(List<AppProblemBean> appProblemList) {
		AppProblemList = appProblemList;
	}
	
}