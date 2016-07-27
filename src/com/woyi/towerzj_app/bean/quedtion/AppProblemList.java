package com.woyi.towerzj_app.bean.quedtion;

import java.util.ArrayList;
import java.util.List;

import com.woyi.towerzj_app.bean.AppBaseBean;

/**
 * 
* 此类描述的是：问题
* @author: 罗然
* @version: 2015-7-20 上午10:19:22
* @ClassName: AppProblemList 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean.quedtion
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