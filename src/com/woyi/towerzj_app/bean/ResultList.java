package com.woyi.towerzj_app.bean;

import java.util.ArrayList;
import java.util.List;

import com.woyi.towerzj_app.bean.donghuan.DongHuanBean;
import com.woyi.towerzj_app.bean.jifang.AppRoomBean;
import com.woyi.towerzj_app.bean.jifang.AppRoomComBean;
import com.woyi.towerzj_app.bean.kgdy.AppSwitchBean;
import com.woyi.towerzj_app.bean.kgdy.AppSwitchComBean;
import com.woyi.towerzj_app.bean.kongtiao.AppAirBean;
import com.woyi.towerzj_app.bean.kongtiao.AppAirComBean;
import com.woyi.towerzj_app.bean.peidianx.AppPowerBean;
import com.woyi.towerzj_app.bean.peidianx.AppPowerComBean;
import com.woyi.towerzj_app.bean.quedtion.AppProblemBean;
import com.woyi.towerzj_app.bean.tawei.AppMastBean;
import com.woyi.towerzj_app.bean.tawei.AppMastComBean;
import com.woyi.towerzj_app.bean.tianxian.AppAntennaBean;
import com.woyi.towerzj_app.bean.xudianchi.AppBatteryBean;
import com.woyi.towerzj_app.bean.xudianchi.AppBatteryComBean;

/**
 * 
 * 此类描述的是：服务器数据信息
 * 
 * @author: 罗然
 * @version: 2015-7-20 下午4:56:49
 * @ClassName: ResultList
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.bean
 */
public class ResultList extends AppBaseBean {

	// 问题
	List<AppProblemBean> allProblemList = new ArrayList<AppProblemBean>();

	// 塔桅
	private List<AppMastBean> appMastList = new ArrayList<AppMastBean>();
	private List<AppMastComBean> appMastComList = new ArrayList<AppMastComBean>();
	// 机房
	private List<AppRoomBean> appRoomList = new ArrayList<AppRoomBean>();
	private List<AppRoomComBean> appRoomComList = new ArrayList<AppRoomComBean>();
	// 空调
	private List<AppAirBean> appAirList = new ArrayList<AppAirBean>();
	private List<AppAirComBean> appAirComList = new ArrayList<AppAirComBean>();
	// 蓄电池
	private List<AppBatteryBean> appBatteryList = new ArrayList<AppBatteryBean>();
	private List<AppBatteryComBean> appBatteryComList = new ArrayList<AppBatteryComBean>();
	// 开关
	private List<AppSwitchBean> appSwitchList = new ArrayList<AppSwitchBean>();
	private List<AppSwitchComBean> appSwitchComList = new ArrayList<AppSwitchComBean>();
	// 配电箱
	private List<AppPowerBean> appPowerList = new ArrayList<AppPowerBean>();
	private List<AppPowerComBean> appPowerComList = new ArrayList<AppPowerComBean>();

	//动环
	private List<DongHuanBean> guards = new ArrayList<DongHuanBean>();
	//天线
	private List<AppAntennaBean> appAntennaBeans = new ArrayList<AppAntennaBean>();
	//其他设备
	private List<AppOtherDepartment> others = new ArrayList<AppOtherDepartment>();

	public List<AppMastComBean> getAppMastComList() {
		return appMastComList;
	}

	public void setAppMastComList(List<AppMastComBean> appMastComList) {
		this.appMastComList = appMastComList;
	}

	public List<AppRoomBean> getAppRoomList() {
		return appRoomList;
	}

	public void setAppRoomList(List<AppRoomBean> appRoomList) {
		this.appRoomList = appRoomList;
	}

	public List<AppRoomComBean> getAppRoomComList() {
		return appRoomComList;
	}

	public void setAppRoomComList(List<AppRoomComBean> appRoomComList) {
		this.appRoomComList = appRoomComList;
	}

	public List<AppAirBean> getAppAirList() {
		return appAirList;
	}

	public void setAppAirList(List<AppAirBean> appAirList) {
		this.appAirList = appAirList;
	}

	public List<AppAirComBean> getAppAirComList() {
		return appAirComList;
	}

	public void setAppAirComList(List<AppAirComBean> appAirComList) {
		this.appAirComList = appAirComList;
	}

	public List<AppBatteryBean> getAppBatteryList() {
		return appBatteryList;
	}

	public void setAppBatteryList(List<AppBatteryBean> appBatteryList) {
		this.appBatteryList = appBatteryList;
	}

	public List<AppBatteryComBean> getAppBatteryComList() {
		return appBatteryComList;
	}

	public void setAppBatteryComList(List<AppBatteryComBean> appBatteryComList) {
		this.appBatteryComList = appBatteryComList;
	}

	public List<AppSwitchBean> getAppSwitchList() {
		return appSwitchList;
	}

	public void setAppSwitchList(List<AppSwitchBean> appSwitchList) {
		this.appSwitchList = appSwitchList;
	}

	public List<AppSwitchComBean> getAppSwitchComList() {
		return appSwitchComList;
	}

	public void setAppSwitchComList(List<AppSwitchComBean> appSwitchComList) {
		this.appSwitchComList = appSwitchComList;
	}

	public List<AppPowerBean> getAppPowerList() {
		return appPowerList;
	}

	public void setAppPowerList(List<AppPowerBean> appPowerList) {
		this.appPowerList = appPowerList;
	}

	public List<AppPowerComBean> getAppPowerComList() {
		return appPowerComList;
	}

	public void setAppPowerComList(List<AppPowerComBean> appPowerComList) {
		this.appPowerComList = appPowerComList;
	}

	public List<AppMastBean> getAppMastList() {
		return appMastList;
	}

	public void setAppMastList(List<AppMastBean> appMastList) {
		this.appMastList = appMastList;
	}

	public List<AppProblemBean> getAllProblemList() {
		return allProblemList;
	}

	public void setAllProblemList(List<AppProblemBean> allProblemList) {
		this.allProblemList = allProblemList;
	}

	public List<DongHuanBean> getGuards() {
		return guards;
	}

	public void setGuards(List<DongHuanBean> guards) {
		this.guards = guards;
	}

	public List<AppAntennaBean> getAppAntennaBeans() {
		return appAntennaBeans;
	}

	public void setAppAntennaBeans(List<AppAntennaBean> appAntennaBeans) {
		this.appAntennaBeans = appAntennaBeans;
	}

	public List<AppOtherDepartment> getOthers() {
		return others;
	}

	public void setOthers(List<AppOtherDepartment> others) {
		this.others = others;
	}

}
