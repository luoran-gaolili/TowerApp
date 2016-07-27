package com.woyi.towerzj_app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.woyi.towerzj_app.bean.AppMastReBean;
import com.woyi.towerzj_app.bean.AppOtherDepartment;
import com.woyi.towerzj_app.bean.RoomItemBean;
import com.woyi.towerzj_app.bean.RoomPicBean;
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
import com.woyi.towerzj_app.bean.quedtion.AppProblemPicBean;
import com.woyi.towerzj_app.bean.tawei.AppMastBean;
import com.woyi.towerzj_app.bean.tawei.AppMastComBean;
import com.woyi.towerzj_app.bean.tianxian.AppAntennaBean;
import com.woyi.towerzj_app.bean.xudianchi.AppBatteryBean;
import com.woyi.towerzj_app.bean.xudianchi.AppBatteryComBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;

public class TowerSQliteDbBean {

	static SimpleDateFormat sim = new SimpleDateFormat("yyy-mm-dd HH:MM:SS");

	/**
	 * 
	 * 此方法描述的是：查询物理站信息
	 * 
	 * @Title: queryPhysicalData
	 * @author: 罗然
	 * @param db
	 * @param sql
	 * @return
	 * @return List<PhysicalBean> 返回类型
	 * @version: 2015-7-21 上午9:39:32
	 */
	public static List<AppPhyInfoBean> queryPhysicalData(SQLiteDatabase db,
			String sql) {
		List<AppPhyInfoBean> list = new ArrayList<AppPhyInfoBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppPhyInfoBean bean = new AppPhyInfoBean();
			int idColumnIndex = cur.getColumnIndex("physicCode");
			String physicCode = cur.getString(idColumnIndex);
			int nameColumnIndex = cur.getColumnIndex("physicAlias");
			String physicAlias = cur.getString(nameColumnIndex);
			int urlColumnIndex = cur.getColumnIndex("physicName");
			String physicName = cur.getString(urlColumnIndex);
			int timeColumnIndex = cur.getColumnIndex("physicAddr");
			String physicAddr = cur.getString(timeColumnIndex);
			String physicType = cur.getString(cur.getColumnIndex("physicType"));
			String longitude = cur.getString(cur.getColumnIndex("longitude"));
			String latitude = cur.getString(cur.getColumnIndex("latitude"));
			String bdlongitude = cur.getString(cur.getColumnIndex("bdlongitude"));
			String bdlatitude = cur.getString(cur.getColumnIndex("bdlatitude"));
			String gpslongitude = cur.getString(cur.getColumnIndex("gpslongitude"));
			String gpslatitude = cur.getString(cur.getColumnIndex("gpslatitude"));
			String isPass = cur.getString(cur.getColumnIndex("isPass"));
			int statusColumnIndex = cur.getColumnIndex("status");
			String status = cur.getString(statusColumnIndex);
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			bean.setPhysicCode(physicCode);
			bean.setPhysicAddr(physicAddr);
			bean.setPhysicAlias(physicAlias);
			bean.setPhysicName(physicName);
			bean.setPhysicType(physicType);
			bean.setLongitude(longitude);
			bean.setLatitude(latitude);
			bean.setIsPass(isPass);
			bean.setStatus(status);
			bean.setCheckUserId(checkUserId);
			bean.setBdlatitude(bdlatitude);
			bean.setBdlongitude(bdlongitude);
			bean.setGpslatitude(gpslatitude);
			bean.setGpslongitude(gpslongitude);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：判断物理站信息是否已同步
	 * 
	 * @Title: ifPhysicalData
	 * @author: 罗然
	 * @param db
	 * @param sql
	 * @return
	 * @return boolean 返回类型
	 * @version: 2015-7-21 上午9:39:48
	 */
	public static boolean ifPhysicalData(SQLiteDatabase db, String sql) {
		boolean flag = false;
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 获取天线数据
	 */
	public static List<AppAntennaBean> getAntenaData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppAntennaBean> list = new ArrayList<AppAntennaBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {

			AppAntennaBean bean = new AppAntennaBean();
			String id = cur.getString(cur.getColumnIndex("id"));
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String dx2g = cur.getString(cur.getColumnIndex("dx2g"));
			String dx2gHeight = cur.getString(cur.getColumnIndex("dx2gHeight"));
			String dx2gPt = cur.getString(cur.getColumnIndex("dx2gPt"));
			String dx3g = cur.getString(cur.getColumnIndex("dx3g"));
			String dx3gHeight = cur.getString(cur.getColumnIndex("dx3gHeight"));
			String dx3gPt = cur.getString(cur.getColumnIndex("dx3gPt"));
			String dx4g = cur.getString(cur.getColumnIndex("dx4g"));
			String dx4gHeight = cur.getString(cur.getColumnIndex("dx4gHeight"));
			String dx4gPt = cur.getString(cur.getColumnIndex("dx4gPt"));
			String dxjjs = cur.getString(cur.getColumnIndex("dxjjs"));
			String yd2g900 = cur.getString(cur.getColumnIndex("yd2g900"));
			String yd2g900Height = cur.getString(cur
					.getColumnIndex("yd2g900Height"));
			String yd2g900Pt = cur.getString(cur.getColumnIndex("yd2g900Pt"));
			String yd2g1800 = cur.getString(cur.getColumnIndex("yd2g1800"));
			String yd2g1800Height = cur.getString(cur
					.getColumnIndex("yd2g1800Height"));
			String yd2g1800Pt = cur.getString(cur.getColumnIndex("yd2g1800Pt"));
			String yd3g = cur.getString(cur.getColumnIndex("yd3g"));
			String yd3gHeight = cur.getString(cur.getColumnIndex("yd3gHeight"));
			String yd3gPt = cur.getString(cur.getColumnIndex("yd3gPt"));
			String yd4g = cur.getString(cur.getColumnIndex("yd4g"));
			String yd4gHeight = cur.getString(cur.getColumnIndex("yd4gHeight"));
			String yd4gPt = cur.getString(cur.getColumnIndex("yd4gPt"));
			String ydjjs = cur.getString(cur.getColumnIndex("ydjjs"));
			String lt2g900 = cur.getString(cur.getColumnIndex("lt2g900"));
			String lt2g900Height = cur.getString(cur
					.getColumnIndex("lt2g900Height"));
			String lt2g900Pt = cur.getString(cur.getColumnIndex("lt2g900Pt"));
			String lt2g1800 = cur.getString(cur.getColumnIndex("lt2g1800"));
			String lt2g1800Height = cur.getString(cur
					.getColumnIndex("lt2g1800Height"));
			String lt2g1800Pt = cur.getString(cur.getColumnIndex("lt2g1800Pt"));
			String lt3g = cur.getString(cur.getColumnIndex("lt3g"));
			String lt3gHeight = cur.getString(cur.getColumnIndex("lt3gHeight"));
			String lt3gPt = cur.getString(cur.getColumnIndex("lt3gPt"));
			String lt4g = cur.getString(cur.getColumnIndex("lt4g"));
			String lt4gHeight = cur.getString(cur.getColumnIndex("lt4gHeight"));
			String lt4gPt = cur.getString(cur.getColumnIndex("lt4gPt"));
			String ltjjs = cur.getString(cur.getColumnIndex("ltjjs"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			String operator = cur.getString(cur.getColumnIndex("operator"));
			String yd4gD = cur.getString(cur.getColumnIndex("yd4gD"));
			String yd4gHeightD = cur.getString(cur.getColumnIndex("yd4gHeightD"));
			String yd4gPtD = cur.getString(cur.getColumnIndex("yd4gPtD"));

			bean.setId(id);
			bean.setPhysicCode(physicCode);
			bean.setDx2g(dx2g);
			bean.setDx2gHeight(dx2gHeight);
			bean.setDx2gPt(dx2gPt);
			bean.setDx3g(dx3g);
			bean.setDx3gHeight(dx3gHeight);
			bean.setDx3gPt(dx3gPt);
			bean.setDx4g(dx4g);
			bean.setDx4gHeight(dx4gHeight);
			bean.setDx4gPt(dx4gPt);
			bean.setDxjjs(dxjjs);
			bean.setYd2g900(yd2g900);
			bean.setYd2g900Height(yd2g900Height);
			bean.setYd2g900Pt(yd2g900Pt);
			bean.setYd2g1800(yd2g1800);
			bean.setYd2g1800Height(yd2g1800Height);
			bean.setYd2g1800Pt(yd2g1800Pt);
			bean.setYd3g(yd3g);
			bean.setYd3gHeight(yd3gHeight);
			bean.setYd3gPt(yd3gPt);
			bean.setYd4g(yd4g);
			bean.setYd4gHeight(yd4gHeight);
			bean.setYd4gPt(yd4gPt);
			bean.setYdjjs(ydjjs);
			bean.setLt2g900(lt2g900);
			bean.setLt2g900Height(lt2g900Height);
			bean.setLt2g900Pt(lt2g900Pt);
			bean.setLt2g1800(lt2g1800);
			bean.setLt2g1800Height(lt2g1800Height);
			bean.setLt2g1800Pt(lt2g1800Pt);
			bean.setLt3g(lt3g);
			bean.setLt3gHeight(lt3gHeight);
			bean.setLt3gPt(lt3gPt);
			bean.setLt4g(lt4g);
			bean.setLt4gPt(lt4gPt);
			bean.setLt4gHeight(lt4gHeight);
			bean.setLtjjs(ltjjs);
			bean.setOperator(operator);

			bean.setYd4gD(yd4gD);
			bean.setYd4gHeightD(yd4gHeightD);
			bean.setYd4gPtD(yd4gPtD);

			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 获取动环数据
	 */
	public static List<DongHuanBean> getGuardData(DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<DongHuanBean> list = new ArrayList<DongHuanBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			DongHuanBean bean = new DongHuanBean();
			String id = cur.getString(cur.getColumnIndex("id"));
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String makeCode = cur.getString(cur.getColumnIndex("makeCode"));
			String fsuType = cur.getString(cur.getColumnIndex("fsuType"));
			String tempSensor = cur.getString(cur.getColumnIndex("tempSensor"));
			String smokeSensor = cur.getString(cur
					.getColumnIndex("smokeSensor"));
			String readSensor = cur.getString(cur.getColumnIndex("readSensor"));
			String waterSensor = cur.getString(cur
					.getColumnIndex("waterSensor"));
			String lightSensor = cur.getString(cur
					.getColumnIndex("lightSensor"));
			String doorSystem = cur.getString(cur.getColumnIndex("doorSystem"));
			String ipCamer = cur.getString(cur.getColumnIndex("ipCamer"));
			String eMeter = cur.getString(cur.getColumnIndex("eMeter"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			String mjzjCode = cur.getString(cur.getColumnIndex("mjzjCode"));
			String mjzjType = cur.getString(cur.getColumnIndex("mjzjType"));
			String jkzjCode = cur.getString(cur.getColumnIndex("jkzjCode"));
			String jkzjType = cur.getString(cur.getColumnIndex("jkzjType"));
			bean.setId(id);
			bean.setPhysicCode(physicCode);
			bean.setMakeCode(makeCode);
			bean.setFsuType(fsuType);
			bean.setTempSensor(Integer.parseInt(tempSensor));
			bean.setSmokeSensor(Integer.parseInt(smokeSensor));
			bean.setReadSensor(Integer.parseInt(readSensor));
			bean.setWaterSensor(Integer.parseInt(waterSensor));
			bean.setLightSensor(Integer.parseInt(lightSensor));
			bean.setDoorSystem(Integer.parseInt(doorSystem));
			bean.setIpCamer(Integer.parseInt(ipCamer));
			bean.seteMeter(Integer.parseInt(eMeter));
			bean.setCheckUserId(checkUserId);
			bean.setJkzjCode(jkzjCode);
			bean.setJkzjType(jkzjType);
			bean.setMjzjCode(mjzjCode);
			bean.setMjzjType(mjzjType);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}

		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：获取塔桅信息
	 * 
	 * @Title: queryMastData
	 * @author: 罗然
	 * @param db
	 * @param sql
	 * @return
	 * @return List<PhysicalBean> 返回类型
	 * @version: 2015-7-21 上午9:40:35
	 */
	public static List<AppMastBean> queryMastData(DatabaseHelper mOpenHelper,
			String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppMastBean> list = new ArrayList<AppMastBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppMastBean bean = new AppMastBean();
			String id = cur.getString(cur.getColumnIndex("id"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String xs = cur.getString(cur.getColumnIndex("xs"));
			String lx = cur.getString(cur.getColumnIndex("lx"));
			Double tg = cur.getDouble(cur.getColumnIndex("tg"));
			String xsCheck = cur.getString(cur.getColumnIndex("xsCheck"));
			String lxCheck = cur.getString(cur.getColumnIndex("lxCheck"));
			Double tgCheck = cur.getDouble(cur.getColumnIndex("tgCheck"));
			String status = cur.getString(cur.getColumnIndex("status"));
			String ewm = cur.getString(cur.getColumnIndex("ewm"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));

			bean.setId(id);
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setXs(xs);
			bean.setLx(lx);
			bean.setTg(tg);
			bean.setXsCheck(xsCheck);
			bean.setLxCheck(lxCheck);
			bean.setTgCheck(tgCheck);
			bean.setEwm(ewm);
			bean.setStatus(status);
			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：获取塔桅运营商信息
	 * 
	 * @Title: queryMastComData
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param sql
	 * @return
	 * @return List<AppMastComBean> 返回类型
	 * @version: 2015-7-21 下午1:17:31
	 */
	public static List<AppMastComBean> queryMastComData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppMastComBean> list = new ArrayList<AppMastComBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppMastComBean bean = new AppMastComBean();
			String code = cur.getString(cur.getColumnIndex("code"));
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String xs = cur.getString(cur.getColumnIndex("xs"));
			String lx = cur.getString(cur.getColumnIndex("lx"));
			Double tg = cur.getDouble(cur.getColumnIndex("tg"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String type = cur.getString(cur.getColumnIndex("type"));
			String checkValue = cur.getString(cur.getColumnIndex("checkValue"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			String linkCode = cur.getString(cur.getColumnIndex("linkCode"));
			bean.setLinkCode(linkCode);
			bean.setCheckValue(checkValue);
			bean.setType(type);
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setXs(xs);
			bean.setLx(lx);
			bean.setTg(tg);
			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：获取机房信息
	 * 
	 * @Title: queryRoomData
	 * @author: 罗然
	 * @param db
	 * @param sql
	 * @return
	 * @return List<AppRoomBean> 返回类型
	 * @version: 2015-7-21 上午9:41:23
	 */
	public static List<AppRoomBean> queryRoomData(DatabaseHelper mOpenHelper,
			String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppRoomBean> list = new ArrayList<AppRoomBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppRoomBean bean = new AppRoomBean();
			String id = cur.getString(cur.getColumnIndex("id"));

			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String lx = cur.getString(cur.getColumnIndex("lx"));
			String jg = cur.getString(cur.getColumnIndex("jg"));
			Double mj = cur.getDouble(cur.getColumnIndex("mj"));
			String lxCheck = cur.getString(cur.getColumnIndex("lxCheck"));
			String jgCheck = cur.getString(cur.getColumnIndex("jgCheck"));
			Double mjCheck = cur.getDouble(cur.getColumnIndex("mjCheck"));
			String status = cur.getString(cur.getColumnIndex("status"));
			String ewm = cur.getString(cur.getColumnIndex("ewm"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			String dbBh = cur.getString(cur.getColumnIndex("dbBh"));
			Double dbDs = cur.getDouble(cur.getColumnIndex("dbDs"));
			String jfYs = cur.getString(cur.getColumnIndex("jfYs"));
			String jfYsJj = cur.getString(cur.getColumnIndex("jfYsJj"));

			bean.setId(id);
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setName(name);
			bean.setLx(lx);
			bean.setMj(mj);
			bean.setJg(jg);
			bean.setLxCheck(lxCheck);
			bean.setJgCheck(jgCheck);
			bean.setMjCheck(mjCheck);
			bean.setEwm(ewm);
			bean.setStatus(status);
			bean.setCheckUserId(checkUserId);
			bean.setDbBh(dbBh);
			bean.setDbDs(dbDs);
			bean.setJfYs(jfYs);
			bean.setJfYsJj(jfYsJj);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：机房运营商
	 * 
	 * @Title: queryRoomComData
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param sql
	 * @return
	 * @return List<AppRoomBean> 返回类型
	 * @version: 2015-7-21 下午1:17:19
	 */
	public static List<AppRoomComBean> queryRoomComData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppRoomComBean> list = new ArrayList<AppRoomComBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppRoomComBean bean = new AppRoomComBean();
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String lx = cur.getString(cur.getColumnIndex("lx"));
			String jg = cur.getString(cur.getColumnIndex("jg"));
			Double mj = cur.getDouble(cur.getColumnIndex("mj"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String type = cur.getString(cur.getColumnIndex("type"));
			String checkValue = cur.getString(cur.getColumnIndex("checkValue"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			String linkCode = cur.getString(cur.getColumnIndex("linkCode"));
			bean.setLinkCode(linkCode);
			bean.setCheckValue(checkValue);
			bean.setType(type);
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setName(name);
			bean.setLx(lx);
			bean.setMj(mj);
			bean.setJg(jg);
			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：获取空调信息
	 * 
	 * @Title: queryAirData
	 * @author: 罗然
	 * @param db
	 * @param sql
	 * @return
	 * @return List<AppRoomBean> 返回类型
	 * @version: 2015-7-21 上午9:41:49
	 */
	public static List<AppAirBean> queryAirData(DatabaseHelper mOpenHelper,
			String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppAirBean> list = new ArrayList<AppAirBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppAirBean bean = new AppAirBean();
			String id = cur.getString(cur.getColumnIndex("id"));
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String cjCheck = cur.getString(cur.getColumnIndex("cjCheck"));
			String cj = cur.getString(cur.getColumnIndex("cj"));
			String ewm = cur.getString(cur.getColumnIndex("ewm"));
			String status = cur.getString(cur.getColumnIndex("status"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			bean.setId(id);
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setName(name);
			bean.setCjCheck(cjCheck);
			bean.setCj(cj);
			bean.setEwm(ewm);
			bean.setStatus(status);
			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：空调运营商
	 * 
	 * @Title: queryAirComData
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param sql
	 * @return
	 * @return List<AppAirComBean> 返回类型
	 * @version: 2015-7-21 下午1:19:40
	 */
	public static List<AppAirComBean> queryAirComData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppAirComBean> list = new ArrayList<AppAirComBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppAirComBean bean = new AppAirComBean();
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String cj = cur.getString(cur.getColumnIndex("cj"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String type = cur.getString(cur.getColumnIndex("type"));
			String checkValue =
					cur.getString(cur.getColumnIndex("checkValue"));
			bean.setCheckValue(checkValue);
			bean.setType(type);
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			String linkCode = cur.getString(cur.getColumnIndex("linkCode"));
			bean.setLinkCode(linkCode);
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setName(name);
			bean.setCj(cj);
			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：获取蓄电池信息
	 * 
	 * @Title: queryBatteryData
	 * @author: 罗然
	 * @param db
	 * @param sql
	 * @return
	 * @return List<AppBatteryBean> 返回类型
	 * @version: 2015-7-21 上午9:42:47
	 */
	public static List<AppBatteryBean> queryBatteryData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppBatteryBean> list = new ArrayList<AppBatteryBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppBatteryBean bean = new AppBatteryBean();
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String pp = cur.getString(cur.getColumnIndex("pp"));
			Double rl = cur.getDouble(cur.getColumnIndex("rl"));
			String ppCheck = cur.getString(cur.getColumnIndex("ppCheck"));
			Double rlCheck = cur.getDouble(cur.getColumnIndex("rlCheck"));
			String ewm = cur.getString(cur.getColumnIndex("ewm"));
			String status = cur.getString(cur.getColumnIndex("status"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setName(name);
			bean.setPp(pp);
			bean.setRl(rl);
			bean.setPpCheck(ppCheck);
			bean.setRlCheck(rlCheck);
			bean.setEwm(ewm);
			bean.setStatus(status);
			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：蓄电池运营商
	 * 
	 * @Title: queryBatteryComData
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param sql
	 * @return
	 * @return List<AppBatteryComBean> 返回类型
	 * @version: 2015-7-21 下午1:20:35
	 */
	public static List<AppBatteryComBean> queryBatteryComData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppBatteryComBean> list = new ArrayList<AppBatteryComBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppBatteryComBean bean = new AppBatteryComBean();
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String pp = cur.getString(cur.getColumnIndex("pp"));
			Double rl = cur.getDouble(cur.getColumnIndex("rl"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String type = cur.getString(cur.getColumnIndex("type"));
			String checkValue =
					cur.getString(cur.getColumnIndex("checkValue"));
			bean.setCheckValue(checkValue);
			bean.setType(type);
			String linkCode = cur.getString(cur.getColumnIndex("linkCode"));
			bean.setLinkCode(linkCode);
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setName(name);
			bean.setPp(pp);
			bean.setRl(rl);
			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：获取配电箱信息
	 * 
	 * @Title: queryPowerData
	 * @author: 罗然
	 * @param db
	 * @param sql
	 * @return
	 * @return List<AppPowerBean> 返回类型
	 * @version: 2015-7-21 上午9:43:20
	 */
	public static List<AppPowerBean> queryPowerData(DatabaseHelper mOpenHelper,
			String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppPowerBean> list = new ArrayList<AppPowerBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppPowerBean bean = new AppPowerBean();
			String id = cur.getString(cur.getColumnIndex("id"));
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String pp = cur.getString(cur.getColumnIndex("pp"));
			Double rl = cur.getDouble(cur.getColumnIndex("rl"));
			String ppCheck = cur.getString(cur.getColumnIndex("ppCheck"));
			Double rlCheck = cur.getDouble(cur.getColumnIndex("rlCheck"));
			String ewm = cur.getString(cur.getColumnIndex("ewm"));
			String status = cur.getString(cur.getColumnIndex("status"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			bean.setId(id);
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setName(name);
			bean.setPp(pp);
			bean.setRl(rl);
			bean.setPpCheck(ppCheck);
			bean.setRlCheck(rlCheck);
			bean.setEwm(ewm);
			bean.setStatus(status);
			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：配电箱运营商
	 * 
	 * @Title: queryPowerComData
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param sql
	 * @return
	 * @return List<AppPowerComBean> 返回类型
	 * @version: 2015-7-21 下午1:22:54
	 */
	public static List<AppPowerComBean> queryPowerComData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppPowerComBean> list = new ArrayList<AppPowerComBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppPowerComBean bean = new AppPowerComBean();
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String pp = cur.getString(cur.getColumnIndex("pp"));
			Double rl = cur.getDouble(cur.getColumnIndex("rl"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String type = cur.getString(cur.getColumnIndex("type"));
			String checkValue =
					cur.getString(cur.getColumnIndex("checkValue"));
			bean.setCheckValue(checkValue);
			bean.setType(type);
			String linkCode = cur.getString(cur.getColumnIndex("linkCode"));
			bean.setLinkCode(linkCode);
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setName(name);
			bean.setPp(pp);
			bean.setRl(rl);
			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：获取开关电源
	 * 
	 * @Title: querySwitchData
	 * @author: 罗然
	 * @param db
	 * @param sql
	 * @return
	 * @return List<AppSwitchBean> 返回类型
	 * @version: 2015-7-21 上午9:43:52
	 */
	public static List<AppSwitchBean> querySwitchData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppSwitchBean> list = new ArrayList<AppSwitchBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppSwitchBean bean = new AppSwitchBean();
			String id = cur.getString(cur.getColumnIndex("id"));
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String pp = cur.getString(cur.getColumnIndex("pp"));
			Double rl = cur.getDouble(cur.getColumnIndex("rl"));
			String ppCheck = cur.getString(cur.getColumnIndex("ppCheck"));
			Double rlCheck = cur.getDouble(cur.getColumnIndex("rlCheck"));
			String ewm = cur.getString(cur.getColumnIndex("ewm"));
			String status = cur.getString(cur.getColumnIndex("status"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			bean.setId(id);
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setName(name);
			bean.setPp(pp);
			bean.setRl(rl);
			bean.setPpCheck(ppCheck);
			bean.setRlCheck(rlCheck);
			bean.setEwm(ewm);
			bean.setStatus(status);
			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：开关电源运营商
	 * 
	 * @Title: querySwitchComData
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param sql
	 * @return
	 * @return List<AppSwitchComBean> 返回类型
	 * @version: 2015-7-21 下午1:21:22
	 */
	public static List<AppSwitchComBean> querySwitchComData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppSwitchComBean> list = new ArrayList<AppSwitchComBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppSwitchComBean bean = new AppSwitchComBean();
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String pp = cur.getString(cur.getColumnIndex("pp"));
			Double rl = cur.getDouble(cur.getColumnIndex("rl"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String type = cur.getString(cur.getColumnIndex("type"));
			String checkValue =
					cur.getString(cur.getColumnIndex("checkValue"));
			bean.setCheckValue(checkValue);
			bean.setType(type);
			String linkCode = cur.getString(cur.getColumnIndex("linkCode"));
			bean.setLinkCode(linkCode);
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			bean.setPhysicCode(physicCode);
			bean.setCode(code);
			bean.setName(name);
			bean.setPp(pp);
			bean.setRl(rl);
			bean.setCheckUserId(checkUserId);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：问题列表
	 * 
	 * @Title: queryProblemData
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param sql
	 * @return
	 * @return List<AppProblemBean> 返回类型
	 * @version: 2015-7-21 下午1:26:45
	 */
	public static List<AppProblemBean> queryProblemData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppProblemBean> list = new ArrayList<AppProblemBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppProblemBean bean = new AppProblemBean();
			String type = cur.getString(cur.getColumnIndex("type"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String desc = cur.getString(cur.getColumnIndex("desc"));
			bean.setType(type);
			bean.setCode(code);
			bean.setName(name);
			bean.setDesc(desc);
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：获取图片列表
	 * 
	 * @Title: queryProblemPicData
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param sql
	 * @return
	 * @return List<AppProblemPicBean> 返回类型
	 * @version: 2015-7-22 上午10:28:37
	 */
	public static List<AppProblemPicBean> queryProblemPicData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppProblemPicBean> list = new ArrayList<AppProblemPicBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppProblemPicBean bean = new AppProblemPicBean();
			String picAddr = cur.getString(cur.getColumnIndex("picAddr"));
			String id = cur.getString(cur.getColumnIndex("id"));
			String type = cur.getString(cur.getColumnIndex("type"));
			String code = cur.getString(cur.getColumnIndex("code"));
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String codeLink = cur.getString(cur.getColumnIndex("codeLink"));
			String pic_id = cur.getString(cur.getColumnIndex("pic_id"));
			int picSeq = cur.getInt(cur.getColumnIndex("picSeq"));
			String checkCode = cur.getString(cur.getColumnIndex("checkCode"));
			String uploadStatus = cur.getString(cur
					.getColumnIndex("uploadStatus"));
			String checkUserId = cur.getString(cur
					.getColumnIndex("checkUserId"));
			String checkDate = cur.getString(cur.getColumnIndex("checkDate"));
			String createDate = cur.getString(cur.getColumnIndex("createDate"));
			String createUserId = cur.getString(cur
					.getColumnIndex("createUserId"));
			String linkCode = cur.getString(cur.getColumnIndex("linkCode"));
			bean.setPicAddr(picAddr);
			bean.setCheckCode(checkCode);
			if (checkDate != null && !checkDate.equals("")
					&& !checkDate.equals("null")) {
				try {
					Date d = sim.parse(checkDate);
					bean.setCheckDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			bean.setCheckUserId(checkUserId);
			bean.setCode(code);
			bean.setCodeLink(codeLink);
			bean.setCreateUserId(createUserId);
			bean.setId(id);
			bean.setLinkCode(linkCode);
			bean.setPhysicCode(physicCode);
			bean.setPic_id(pic_id);
			bean.setPicSeq(picSeq);
			bean.setType(type);
			bean.setUploadStatus(uploadStatus);
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * 此方法描述的是：添加物理站信息
	 * 
	 * @Title: insertPhysical
	 * @author: 罗然
	 * @param db
	 * @param physicalBean
	 * @return void 返回类型
	 * @version: 2015-7-21 下午1:41:37
	 */
	public static void insertPhysical(SQLiteDatabase db,
			AppPhyInfoBean physicalBean) {
		String sql = "insert into t_physic_info(id,physicCode,physicAlias,physicName,physicType,longitude,latitude,isPass,physicAddr,status,createUserId,checkDate) "
				+ "values ('"
				+ physicalBean.getId()
				+ "','"
				+ physicalBean.getPhysicCode()
				+ "','"
				+ physicalBean.getPhysicAlias()
				+ "','"
				+ physicalBean.getPhysicName()
				+ "','"
				+ physicalBean.getPhysicType()
				+ "','"
				+ physicalBean.getLongitude()
				+ "','"
				+ physicalBean.getLatitude()
				+ "','"
				+ physicalBean.getIsPass()
				+ "','"
				+ physicalBean.getPhysicAddr()
				+ "','0','"
				+ ApplicationData.user.getId()
				+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'));";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：添加机房
	 * 
	 * @Title: insertRoom
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param jiFangBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午10:09:21
	 */
	public static void insertRoom(SQLiteDatabase db, AppRoomBean jiFangBean,
			int flag) {
		String sql = "insert into tb_room(physicCode,id,code,name,lx,jg,mj,lxCheck,jgCheck,mjCheck,ewm,checkUserId,checkDate,status,dbBh,dbDs,jfYs,jfYsJj) "
				+ "values ('"
				+ jiFangBean.getPhysicCode()
				+ "','"
				+ jiFangBean.getId()
				+ "','"
				+ jiFangBean.getCode()
				+ "','"
				+ jiFangBean.getName()
				+ "','"
				+ jiFangBean.getLx()
				+ "','"
				+ jiFangBean.getJg()
				+ "','"
				+ jiFangBean.getMj()
				+ "','"
				+ jiFangBean.getLxCheck()
				+ "','"+jiFangBean.getJgCheck()+"','"
				+ jiFangBean.getMjCheck()
				+ "','"
				+ jiFangBean.getEwm()
				+ "','"
				+ ApplicationData.user.getId()
				+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'1','"
				+jiFangBean.getDbBh()+"','"+jiFangBean.getDbDs()+"','"
				+jiFangBean.getJfYs()+"','"+jiFangBean.getJfYsJj()+"');";
		try {
			db.execSQL(sql);
			if (flag == 2) {
				sql = " update t_physic_info set status='1' where physicCode='"
						+ jiFangBean.getPhysicCode() + "'";
				db.execSQL(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：添加开关电源
	 * 
	 * @Title: insertSwitch
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param switchBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午10:12:28
	 */
	public static void insertSwitch(SQLiteDatabase db,
			AppSwitchBean switchBean, int flag) {
		String sql = "insert into tb_switch(id,physicCode,code,name,pp,rl,ppCheck,rlCheck,ewm,checkUserId,checkDate,status) "
				+ "values ('"
				+ switchBean.getId()
				+ "','"
				+ switchBean.getPhysicCode()
				+ "','"
				+ switchBean.getCode()
				+ "','"
				+ switchBean.getName()
				+ "','"
				+ switchBean.getPp()
				+ "','"
				+ switchBean.getRl()
				+ "','"
				+ switchBean.getPpCheck()
				+ "','"
				+ switchBean.getRlCheck()
				+ "','"
				+ switchBean.getEwm()
				+ "','"
				+ ApplicationData.user.getId()
				+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'1');";
		try {
			db.execSQL(sql);
			if (flag == 2) {
				sql = " update t_physic_info set status='1' where physicCode='"
						+ switchBean.getPhysicCode() + "'";
				db.execSQL(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：添加空调
	 * 
	 * @Title: insertAir
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param kongTiaoBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午10:18:33
	 */
	public static void insertAir(SQLiteDatabase db, AppAirBean kongTiaoBean,
			int flag) {
		String sql = "insert into tb_air_c(id,physicCode,code,name,cjCheck,cj,ewm,checkUserId,checkDate,status) "
				+ "values ('"
				+ kongTiaoBean.getId()
				+ "','"
				+ kongTiaoBean.getPhysicCode()
				+ "','"
				+ kongTiaoBean.getCode()
				+ "','"
				+ kongTiaoBean.getName()
				+ "','"
				+ kongTiaoBean.getCjCheck()
				+ "','"
				+ kongTiaoBean.getCj()
				+ "','"
				+ kongTiaoBean.getEwm()
				+ "','"
				+ ApplicationData.user.getId()
				+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'1');";
		try {
			db.execSQL(sql);
			if (flag == 2) {
				sql = " update t_physic_info set status='1' where physicCode='"
						+ kongTiaoBean.getPhysicCode() + "'";
				db.execSQL(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：添加配电箱
	 * 
	 * @Title: insertPower
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param dbBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午10:20:54
	 */
	public static void insertPower(SQLiteDatabase db, AppPowerBean dbBean,
			int flag) {
		String sql = "insert into tb_power_b(id,physicCode,code,name,pp,rl,ppCheck,rlCheck,ewm,checkUserId,checkDate,status) "
				+ "values ('"
				+ dbBean.getId()
				+ "','"
				+ dbBean.getPhysicCode()
				+ "','"
				+ dbBean.getCode()
				+ "','"
				+ dbBean.getName()
				+ "','"
				+ dbBean.getPp()
				+ "','"
				+ dbBean.getRl()
				+ "','"
				+ dbBean.getPpCheck()
				+ "','"
				+ dbBean.getRlCheck()
				+ "','"
				+ dbBean.getEwm()
				+ "','"
				+ ApplicationData.user.getId()
				+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'1');";
		try {
			db.execSQL(sql);
			if (flag == 2) {
				sql = " update t_physic_info set status='1' where physicCode='"
						+ dbBean.getPhysicCode() + "'";
				db.execSQL(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：添加塔桅
	 * 
	 * @Title: insertMast
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param taWeiBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午10:30:29
	 */
	public static void insertMast(SQLiteDatabase db, AppMastBean taWeiBean,
			int flag) {//
		String sql = "insert into tb_mast(id,code,physicCode,lx,tg,xs,xsCheck,lxCheck,tgCheck,ewm,checkUserId,checkDate,status) "
				+ "values ('"
				+ taWeiBean.getId()
				+ "','"
				+ taWeiBean.getCode()
				+ "','"
				+ taWeiBean.getPhysicCode()
				+ "','"
				+ taWeiBean.getLx()
				+ "','"
				+ taWeiBean.getTg()
				+ "','"
				+ taWeiBean.getXs()
				+ "','"
				+ taWeiBean.getXsCheck()
				+ "','"
				+ taWeiBean.getLxCheck()
				+ "','"
				+ taWeiBean.getTgCheck()
				+ "','"
				+ taWeiBean.getEwm()
				+ "','"
				+ ApplicationData.user.getId()
				+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'1');";
		try {
			db.execSQL(sql);
			if (flag == 2) {
				sql = " update t_physic_info set status='1' where physicCode='"
						+ taWeiBean.getPhysicCode() + "'";
				db.execSQL(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：添加蓄电池
	 * 
	 * @Title: insertBatterry
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param batterryBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午10:53:00
	 */
	public static void insertBatterry(SQLiteDatabase db,
			AppBatteryBean batterryBean, int flag) {
		String sql = "insert into tb_battery(physicCode,id,code,name,pp,rl,ppCheck,rlCheck,ewm,checkUserId,checkDate,status) "
				+ "values ('"
				+ batterryBean.getPhysicCode()
				+ "','"
				+ batterryBean.getId()
				+ "','"
				+ batterryBean.getCode()
				+ "','"
				+ batterryBean.getName()
				+ "','"
				+ batterryBean.getPp()
				+ "','"
				+ batterryBean.getRl()
				+ "','"
				+ batterryBean.getPpCheck()
				+ "','"
				+ batterryBean.getRlCheck()
				+ "','"
				+ batterryBean.getEwm()
				+ "','"
				+ ApplicationData.user.getId()
				+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'1');";
		try {
			db.execSQL(sql);
			if (flag == 2) {
				sql = " update t_physic_info set status='1' where physicCode='"
						+ batterryBean.getPhysicCode() + "'";
				db.execSQL(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：添加天线
	 * 
	 * @Title: insertTx
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param xianBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午10:57:01
	 */
	public static void insertTx(SQLiteDatabase db, AppAntennaBean xianBean) {

		String sql = "insert into t_antenna(id,physicCode," +
				"dx2g,dx2gHeight,dx2gPt,dx3g,dx3gHeight,dx3gPt,dx4g,dx4gHeight,dx4gPt,dxjjs," +
				"yd2g900,yd2g900Height,yd2g900Pt,yd2g1800,yd2g1800Height,yd2g1800Pt,yd3g,yd3gHeight,yd3gPt,yd4g,yd4gHeight,yd4gPt,ydjjs," +
				"checkUserId,checkDate," +
				"lt2g900,lt2g900Height,lt2g900Pt,lt2g1800,lt2g1800Height,lt2g1800Pt,lt3g,lt3gHeight,lt3gPt,lt4g," +
				"lt4gHeight,lt4gPt,ltjjs,operator,yd4gD,yd4gHeightD,yd4gPtD) "
				+ "values ('"+ xianBean.getId()+ "','"+ xianBean.getPhysicCode()+ "','"
				+ xianBean.getDx2g()+ "','"
				+ xianBean.getDx2gHeight()+ "','"
				+ xianBean.getDx2gPt()+ "','"
				+ xianBean.getDx3g()+ "','"
				+ xianBean.getDx3gHeight()+ "','"
				+ xianBean.getDx3gPt()+ "','"
				+ xianBean.getDx4g()+ "','"
				+ xianBean.getDx4gHeight()+ "','"
				+ xianBean.getDx4gPt()+ "','"
				+ xianBean.getDxjjs()+ "','"
				+ xianBean.getYd2g900()+ "','"
				+ xianBean.getYd2g900Height()+ "','"
				+ xianBean.getYd2g900Pt()+ "','"
				+ xianBean.getYd2g1800()+ "','"
				+ xianBean.getYd2g1800Height()+ "','"
				+ xianBean.getYd2g1800Pt()+ "','"
				+ xianBean.getYd3g()+ "','"
				+ xianBean.getYd3gHeight()+ "','"
				+ xianBean.getYd3gPt()+ "','"
				+ xianBean.getYd4g()+ "','"
				+ xianBean.getYd4gHeight()+ "','"
				+ xianBean.getYd4gPt()+ "','"
				+ xianBean.getYdjjs()+ "','"
				+ ApplicationData.user.getId()+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'"
				+ xianBean.getLt2g900()+ "','"
				+ xianBean.getLt2g900Height()+ "','"
				+ xianBean.getLt2g900Pt()+ "','"
				+ xianBean.getLt2g1800()+ "','"
				+ xianBean.getLt2g1800Height()+ "','"
				+ xianBean.getLt2g1800Pt()+ "','"
				+ xianBean.getLt3g()+ "','"
				+ xianBean.getLt3gHeight()+ "','"
				+ xianBean.getLt3gPt()+ "','"
				+ xianBean.getLt4g()+ "','"
				+ xianBean.getLt4gHeight()+ "','"
				+ xianBean.getLt4gPt()+ "','"
				+ xianBean.getLtjjs()+ "','"
				+ xianBean.getOperator()+ "','"
				+xianBean.getYd4gD()+"','"+xianBean.getYd4gHeightD()+"','"+xianBean.getYd4gPtD()+"');";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：添加动环
	 * 
	 * @Title: insertDh
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param huanBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午11:01:50
	 */
	public static void insertDh(SQLiteDatabase db, DongHuanBean huanBean) {
		String sql = "insert into t_guard(id,physicCode,makeCode,fsuType,tempSensor,smokeSensor,readSensor,waterSensor,lightSensor,doorSystem,ipCamer,eMeter,checkUserId,checkDate," +
				"mjzjCode,mjzjType,jkzjCode,jkzjType) "
				+ "values ('"
				+ huanBean.getId()
				+ "','"
				+ huanBean.getPhysicCode()
				+ "','"
				+ huanBean.getMakeCode()
				+ "','"
				+ huanBean.getFsuType()
				+ "','"
				+ huanBean.getTempSensor()
				+ "','"
				+ huanBean.getSmokeSensor()
				+ "','"
				+ huanBean.getReadSensor()
				+ "','"
				+ huanBean.getWaterSensor()
				+ "','"
				+ huanBean.getLightSensor()
				+ "','"
				+ huanBean.getDoorSystem()
				+ "','"
				+ huanBean.getIpCamer()
				+ "','"
				+ huanBean.geteMeter()
				+ "','"
				+ ApplicationData.user.getId()
				+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'"
				+huanBean.getMjzjCode()+"','"+huanBean.getMjzjType()+"','"
				+huanBean.getJkzjCode()+"','"+huanBean.getJkzjType()+"');";
		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ huanBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：添加问题
	 * 
	 * @Title: insertProblem
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param proBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午11:13:08
	 */
	public static void insertProblem(SQLiteDatabase db, AppProblemBean proBean) {
		String sql = "insert into t_problem(type,code,name,desc) "
				+ "values ('" + proBean.getType() + "','" + proBean.getCode()
				+ "','" + proBean.getName() + "','" + proBean.getDesc() + "');";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：添加问题图片
	 * 
	 * @Title: insertProblemPic
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param proBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午11:26:40
	 */
	public static void insertProblemPic(SQLiteDatabase db,
			AppProblemPicBean proBean) {
		String sql = "insert into t_problem_pic(id,physicCode,type,code,pic_id,picAddr,checkCode,checkUserId,createDate,checkDate,uploadStatus,linkCode) "
				+ "values ('"
				+ proBean.getId()
				+ "','"
				+ proBean.getPhysicCode()
				+ "','"
				+ proBean.getType()
				+ "','"
				+ proBean.getCode()
				+ "','"
				+ proBean.getPic_id()
				+ "','"
				+ proBean.getPicAddr()
				+ "','"
				+ proBean.getCheckCode()
				+ "','"
				+ ApplicationData.user.getId()
				+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'0','"
				+ proBean.getLinkCode() + "');";
		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ proBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：塔桅运营商
	 * 
	 * @Title: insertMastCom
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param proBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午11:36:26
	 */
	public static void insertMastCom(SQLiteDatabase db, AppMastComBean proBean) {
		String sql = "insert into ts_mast(id,physicCode,type,code,xs,lx,tg,checkValue,checkUserId,linkCode) "
				+ "values ('"
				+ proBean.getId()
				+ "','"
				+ proBean.getPhysicCode()
				+ "','"
				+ proBean.getType()
				+ "','"
				+ proBean.getCode()
				+ "','"
				+ proBean.getXs()
				+ "','"
				+ proBean.getLx()
				+ "','"
				+ proBean.getTg()
				+ "','"
				+ proBean.getCheckValue()
				+ "','"
				+ ApplicationData.user.getId()
				+ "','"
				+ proBean.getLinkCode()
				+ "');";

		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：机房运营商
	 * 
	 * @Title: insertMastCom
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param proBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午11:38:41
	 */
	public static void insertRoomCom(SQLiteDatabase db, AppRoomComBean proBean) {
		String sql = "insert into ts_room(id,physicCode,type,code,name,lx,jg,mj,checkValue,checkUserId,linkCode) "
				+ "values ('"
				+ proBean.getId()
				+ "','"
				+ proBean.getPhysicCode()
				+ "','"
				+ proBean.getType()
				+ "','"
				+ proBean.getCode()
				+ "','"
				+ proBean.getName()
				+ "','"
				+ proBean.getLx()
				+ "','"
				+ proBean.getJg()
				+ "','"
				+ proBean.getMj()
				+ "','"
				+ proBean.getCheckValue()
				+ "','"
				+ ApplicationData.user.getId()
				+ "','"
				+ proBean.getLinkCode()
				+ "');";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：空调运营商
	 * 
	 * @Title: insertAirCom
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param proBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午11:43:14
	 */
	public static void insertAirCom(SQLiteDatabase db, AppAirComBean proBean) {
		String sql = "insert into ts_air_c(id,physicCode,type,code,name,cj,checkValue,checkUserId,linkCode) "
				+ "values ('"
				+ proBean.getId()
				+ "','"
				+ proBean.getPhysicCode()
				+ "','"
				+ proBean.getType()
				+ "','"
				+ proBean.getCode()
				+ "','"
				+ proBean.getName()
				+ "','"
				+ proBean.getCj()
				+ "','"
				+ proBean.getCheckValue()
				+ "','"
				+ ApplicationData.user.getId()
				+ "','"
				+ proBean.getLinkCode()
				+ "');";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：配电箱运营商
	 * 
	 * @Title: insertPowerCom
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param proBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午11:46:04
	 */
	public static void insertPowerCom(SQLiteDatabase db, AppPowerComBean proBean) {
		String sql = "insert into ts_power_b(id,physicCode,type,code,name,pp,rl,checkValue,checkUserId,linkCode) "
				+ "values ('"
				+ proBean.getId()
				+ "','"
				+ proBean.getPhysicCode()
				+ "','"
				+ proBean.getType()
				+ "','"
				+ proBean.getCode()
				+ "','"
				+ proBean.getName()
				+ "','"
				+ proBean.getPp()
				+ "','"
				+ proBean.getRl()
				+ "','"
				+ proBean.getCheckValue()
				+ "','"
				+ ApplicationData.user.getId()
				+ "','"
				+ proBean.getLinkCode()
				+ "');";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：蓄电池运营商
	 * 
	 * @Title: insertBatteryCom
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param proBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午11:48:51
	 */
	public static void insertBatteryCom(SQLiteDatabase db,
			AppBatteryComBean proBean) {
		String sql = "insert into ts_battery(id,physicCode,type,code,name,pp,rl,checkValue,checkUserId,linkCode) "
				+ "values ('"
				+ proBean.getId()
				+ "','"
				+ proBean.getPhysicCode()
				+ "','"
				+ proBean.getType()
				+ "','"
				+ proBean.getCode()
				+ "','"
				+ proBean.getName()
				+ "','"
				+ proBean.getPp()
				+ "','"
				+ proBean.getRl()
				+ "','"
				+ proBean.getCheckValue()
				+ "','"
				+ ApplicationData.user.getId()
				+ "','"
				+ proBean.getLinkCode()
				+ "');";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：开关电源运营商
	 * 
	 * @Title: insertSwitchCom
	 * @author: 罗然
	 * @param mOpenHelper
	 * @param proBean
	 * @return void 返回类型
	 * @version: 2015-7-21 上午11:49:34
	 */
	public static void insertSwitchCom(SQLiteDatabase db,
			AppSwitchComBean proBean) {
		String sql = "insert into ts_switch(id,physicCode,type,code,name,pp,rl,checkValue,checkUserId,linkCode) "
				+ "values ('"
				+ proBean.getId()
				+ "','"
				+ proBean.getPhysicCode()
				+ "','"
				+ proBean.getType()
				+ "','"
				+ proBean.getCode()
				+ "','"
				+ proBean.getName()
				+ "','"
				+ proBean.getPp()
				+ "','"
				+ proBean.getRl()
				+ "','"
				+ proBean.getCheckValue()
				+ "','"
				+ ApplicationData.user.getId()
				+ "','"
				+ proBean.getLinkCode()
				+ "');";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 此方法描述的是关系表数据插入
	 */
	public static void insertMastRoomData(SQLiteDatabase db,
			AppMastReBean proBean) {
		String sql = "insert into t_oldnew(oldType,newType) " + "values ('"

		+ proBean.getOldType() + "','" + proBean.getNewType() + "');";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 此方法描述的是关系表数据的查询
	 */
	public static List<AppMastReBean> queryMastRoomData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppMastReBean> list = new ArrayList<AppMastReBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppMastReBean bean = new AppMastReBean();
			String oldType = cur.getString(cur.getColumnIndex("oldType"));
			String newType = cur.getString(cur.getColumnIndex("newType"));
			bean.setOldType(oldType);
			bean.setNewType(newType);
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;

	}

	/**
	 * 
	 * 此方法描述的是：删除表数据
	 * 
	 * @Title: deleMast
	 * @author: 罗然
	 * @param db
	 * @param code
	 * @return void 返回类型
	 * @version: 2015-7-21 下午3:03:09
	 */
	public static void deleData(SQLiteDatabase db, String physicCode,
			String code,String operStr, String type) {
		String sql = "";
		try {
			if (type.equals("01")) { // 塔桅
				sql = "update  tb_mast set status='0' where physicCode='"
						+ physicCode + "' and code='" + code + "'";
				db.execSQL(sql);
			} else if (type.equals("02")) {// 机房
				sql = "update tb_room set status='0' where physicCode='"
						+ physicCode + "' and code='" + code + "'";
				db.execSQL(sql);
			} else if (type.equals("03")) {// 空调
				sql = "update tb_air_c set status='0' where physicCode='"
						+ physicCode + "' and code='" + code + "'";
				db.execSQL(sql);
			} else if (type.equals("04")) {// 蓄电池
				sql = "update tb_battery set status='0' where physicCode='"
						+ physicCode + "' and code='" + code + "'";
				db.execSQL(sql);
			} else if (type.equals("05")) {// 开关电源
				sql = "update tb_switch set status='0' where physicCode='"
						+ physicCode + "' and code='" + code + "'";
				db.execSQL(sql);
			} else if (type.equals("06")) {// 配电箱
				sql = "update tb_power_b set status='0' where physicCode='"
						+ physicCode + "' and code='" + code + "'";
				db.execSQL(sql);
			} else if (type.equals("07")) {// 物理站
				// 物理站
				sql = "delete from t_physic_info where physicCode='"
						+ physicCode + "'";
				db.execSQL(sql);

				sql = "delete from tb_mast where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				sql = "delete from ts_mast where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				sql = "delete from tb_room where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				sql = "delete from ts_room where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				sql = "delete from tb_air_c where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				sql = "delete from ts_air_c where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				sql = "delete from tb_battery where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				sql = "delete from ts_battery where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				sql = "delete from tb_switch where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				sql = "delete from ts_switch where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				sql = "delete from tb_power_b where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				sql = "delete from ts_power_b where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				// 动环
				sql = "delete from t_guard where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				// 天线
				sql = "delete from t_antenna where physicCode='" + physicCode
						+ "'";
				// 其他设备
				sql = "delete from t_other_department where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				// 机房图片表
				sql = "delete from tb_room_item where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
				// 机房图片表
				sql = "delete from tb_room_pic where physicCode='" + physicCode
						+ "'";
				db.execSQL(sql);
			} else if (type.equals("08")) {// 更新问题表
				sql = "delete from t_problem";
				db.execSQL(sql);
			} else if (type.equals("09")) {
				sql = "delete from t_problem_pic where picAddr='" + code + "'";
				db.execSQL(sql);
			} else if (type.equals("10")) {
				if(code.equals("1")){
					sql = "update  t_antenna set yd2g900='无',yd2g900Height='',yd2g900Pt='0',yd2g1800='无',yd2g1800Height='',yd2g1800Pt='0',"
					+"yd3g='无',yd3gHeight='',yd3gPt='0',yd4g='无',yd4gHeight='',yd4gPt='0',ydjjs='',yd4gD='无',yd4gHeightD='',yd4gPtD='0',operator='" + operStr
							+ "' where physicCode='" + physicCode + "'";
				}else if(code.equals("3")){
					sql = "update  t_antenna set lt2g900='无',lt2g900Height='',lt2g900Pt='0',lt2g1800='无',lt2g1800Height='',lt2g1800Pt='0',"
					+"lt3g='无',lt3gHeight='',lt3gPt='0',lt4g='无',lt4gHeight='',lt4gPt='0',ltjjs='',operator='" + operStr
							+ "' where physicCode='" + physicCode + "'";
				}else if(code.equals("2")){
					sql = "update  t_antenna set dx2g='无',dx2gHeight='',dx2gPt='0',dx3g='无',dx3gHeight='',dx3gPt='0',dx4g='无',dx4gHeight='',"
							+"dx4gPt='0',dxjjs='',operator='" + operStr
							+ "' where physicCode='" + physicCode + "'";
				}
				db.execSQL(sql);
			} else if (type.equals("11")) {
				sql = "delete from t_other_department";
				db.execSQL(sql);
			} else if (type.equals("12")) {
				sql = "delete from tb_room_pic where picAddr='" + code + "'";
				db.execSQL(sql);
			}  else if (type.equals("13")) {
				sql = "update tb_room set lxCheck='',jgCheck='1' where physicCode='" + physicCode + "'";
				db.execSQL(sql);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新机房表数据
	 */
	public static void updateRoomData(SQLiteDatabase db, AppRoomBean jiFangBean) {
		try {
			String sql = "update tb_room set dbBh='"
					+jiFangBean.getDbBh()+"',jfYsJj='"
					+jiFangBean.getJfYsJj()+"',jfYs='"
					+jiFangBean.getJfYs()+"',dbDs='"
					+jiFangBean.getDbDs()+"',lx='"
					+ jiFangBean.getLx()
					+ "',jg='"
					+ jiFangBean.getJg()
					+ "',mj='"
					+ jiFangBean.getMj()
					+ "',ewm='"
					+ jiFangBean.getEwm()
					+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),checkUserId='"
					+ jiFangBean.getCheckUserId() + "' where physicCode='"
					+ jiFangBean.getPhysicCode() + "'  and code='"
					+ jiFangBean.getCode() + "' ";
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ jiFangBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 更新物理站信息
	public static void updatePhysicalData(SQLiteDatabase db,
			AppPhyInfoBean phyInfoBean) {
		try {
			String sql = "update t_physic_info set physicType='"
					+ phyInfoBean.getPhysicType() + "',gpslongitude='"
					+ phyInfoBean.getGpslongitude() + "',checkUserId='"
					+ phyInfoBean.getCheckUserId()+ "',gpslatitude='"
					+ phyInfoBean.getGpslatitude()+ "',bdlongitude='"
					+phyInfoBean.getBdlongitude()+"',bdlatitude='"+phyInfoBean.getBdlatitude()
					+"' where physicCode='"+ phyInfoBean.getPhysicCode() + "' ";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新开关电源数据
	 */
	public static void updateSwitchData(SQLiteDatabase db,
			AppSwitchBean switchBean) {
		try {
			String sql = "update tb_switch set ppCheck='"
					+ switchBean.getPpCheck()
					+ "',pp='"
					+ switchBean.getPp()
					+ "',rl='"
					+ switchBean.getRl()
					+ "',ewm='"
					+ switchBean.getEwm()
					+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),checkUserId='"
					+ switchBean.getCheckUserId() + "' where physicCode='"
					+ switchBean.getPhysicCode() + "' and code='"
					+ switchBean.getCode() + "' ";
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ switchBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新空调数据
	 */
	public static void updateAirData(SQLiteDatabase db, AppAirBean kongTiaoBean) {
		String sql = "update tb_air_c set cjCheck='"
				+ kongTiaoBean.getCjCheck()
				+ "',cj='"
				+ kongTiaoBean.getCj()
				+ "',ewm='"
				+ kongTiaoBean.getEwm()
				+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),checkUserId='"
				+ kongTiaoBean.getCheckUserId() + "' where physicCode='"
				+ kongTiaoBean.getPhysicCode() + "' and code='"
				+ kongTiaoBean.getCode() + "' ";

		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ kongTiaoBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新配电箱数据
	 */
	public static void updatePowerData(SQLiteDatabase db, AppPowerBean dbBean) {
		String sql = "update tb_power_b set ppCheck='"
				+ dbBean.getPpCheck()
				+ "',pp='"
				+ dbBean.getPp()
				+ "',rl='"
				+ dbBean.getRl()
				+ "',ewm='"
				+ dbBean.getEwm()
				+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),checkUserId='"
				+ dbBean.getCheckUserId() + "' where physicCode='"
				+ dbBean.getPhysicCode() + "' and code='" + dbBean.getCode()
				+ "' ";

		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ dbBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新塔桅数据
	 */
	public static void updateMastData(SQLiteDatabase db, AppMastBean dbBean) {

		String sql = "update tb_mast set lx='"
				+ dbBean.getLx()
				+ "',tg='"
				+ dbBean.getTg()
				+ "',ewm='"
				+ dbBean.getEwm()
				+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),checkUserId='"
				+ dbBean.getCheckUserId() + "',xs='" + dbBean.getXs()
				+ "' where physicCode='" + dbBean.getPhysicCode()
				+ "' and code='" + dbBean.getCode() + "' ";
		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ dbBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 更新蓄电池数据
	 */
	public static void updateBatteryData(SQLiteDatabase db,
			AppBatteryBean dbBean) {
		String sql = "update tb_battery set ppCheck='"
				+ dbBean.getPpCheck()
				+ "',pp='"
				+ dbBean.getPp()
				+ "',rl='"
				+ dbBean.getRl()
				+ "',ewm='"
				+ dbBean.getEwm()
				+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),checkUserId='"
				+ dbBean.getCheckUserId() + "' where physicCode='"
				+ dbBean.getPhysicCode() + "' and code='" + dbBean.getCode()
				+ "' ";
		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ dbBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新天线数据
	 */
	public static void updateTxData(SQLiteDatabase db, AppAntennaBean dbBean) {
		String sql = "update t_antenna set yd4gD='"
				+dbBean.getYd4gD()+"',yd4gHeightD='"+dbBean.getYd4gHeightD()+"',yd4gPtD='"+dbBean.getYd4gPtD()+"',dx2g='"
				+ dbBean.getDx2g()
				+ "',dx2gHeight='"
				+ dbBean.getDx2gHeight()
				+ "',dx2gPt='"
				+ dbBean.getDx2gPt()
				+ "',dx3g='"
				+ dbBean.getDx3g()
				+ "',dx3gHeight='"
				+ dbBean.getDx3gHeight()
				+ "',dx3gPt='"
				+ dbBean.getDx3gPt()
				+ "',dx4g='"
				+ dbBean.getDx4g()
				+ "',dx4gHeight='"
				+ dbBean.getDx4gHeight()
				+ "',dx4gPt='"
				+ dbBean.getDx4gPt()
				+ "',dxjjs='"
				+ dbBean.getDxjjs()
				+ "',yd2g900='"
				+ dbBean.getYd2g900()
				+ "',yd2g900Height='"
				+ dbBean.getYd2g900Height()
				+ "',yd2g900Pt='"
				+ dbBean.getYd2g900Pt()
				+ "',yd2g1800='"
				+ dbBean.getYd2g1800()
				+ "',yd2g1800Height='"
				+ dbBean.getYd2g1800Height()
				+ "',yd2g1800Pt='"
				+ dbBean.getYd2g1800Pt()
				+ "',yd3g='"
				+ dbBean.getYd3g()
				+ "',yd3gHeight='"
				+ dbBean.getYd3gHeight()
				+ "',yd3gPt='"
				+ dbBean.getYd3gPt()
				+ "',yd4g='"
				+ dbBean.getYd4g()
				+ "',yd4gHeight='"
				+ dbBean.getYd4gHeight()
				+ "',yd4gpt='"
				+ dbBean.getYd4gPt()
				+ "',ydjjs='"
				+ dbBean.getYdjjs()
				+ "',lt2g900='"
				+ dbBean.getLt2g900()
				+ "',lt2g900Height='"
				+ dbBean.getLt2g900Height()
				+ "',lt2g900Pt='"
				+ dbBean.getLt2g900Pt()
				+ "',lt2g1800='"
				+ dbBean.getLt2g1800()
				+ "',lt2g1800Height='"
				+ dbBean.getLt2g1800Height()
				+ "',lt2g1800Pt='"
				+ dbBean.getLt2g1800Pt()
				+ "',lt3g='"
				+ dbBean.getLt3g()
				+ "',lt3gHeight='"
				+ dbBean.getLt3gHeight()
				+ "',lt3gPt='"
				+ dbBean.getLt3gPt()
				+ "',lt4g='"
				+ dbBean.getLt4g()
				+ "',lt4gHeight='"
				+ dbBean.getLt4gHeight()
				+ "',lt4gPt='"
				+ dbBean.getLt4gPt()
				+ "',ltjjs='"
				+ dbBean.getLtjjs()
				+ "',operator='"
				+ dbBean.getOperator()
				+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),checkUserId='"
				+ ApplicationData.user.getId() + "' where physicCode='"
				+ dbBean.getPhysicCode() + "'";

		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ dbBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新动环数据
	 */
	public static void updateGuardData(SQLiteDatabase db, DongHuanBean dbBean) {
		String sql = "update t_guard set mjzjCode='"+dbBean.getMjzjCode()+"',mjzjType='"
				+dbBean.getMjzjType()+"',jkzjCode='"+dbBean.getJkzjCode()+"',jkzjType='"
				+dbBean.getJkzjType()+"', makeCode='"
				+ dbBean.getMakeCode()
				+ "',fsuType='"
				+ dbBean.getFsuType()
				+ "',tempSensor='"
				+ dbBean.getTempSensor()
				+ "',smokeSensor='"
				+ dbBean.getSmokeSensor()
				+ "',readSensor='"
				+ dbBean.getReadSensor()
				+ "',waterSensor='"
				+ dbBean.getWaterSensor()
				+ "',lightSensor='"
				+ dbBean.getLightSensor()
				+ "',doorSystem='"
				+ dbBean.getDoorSystem()
				+ "',ipCamer='"
				+ dbBean.getIpCamer()
				+ "',eMeter='"
				+ dbBean.geteMeter()
				+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),checkUserId='"
				+ dbBean.getCheckUserId() + "' where physicCode='"
				+ dbBean.getPhysicCode() + "'";

		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ dbBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新机房运营商数据
	 */
	public static void updateRoomComData(SQLiteDatabase db,
			AppRoomComBean proBean) {
		String sql = "update ts_room set checkValue='"
				+ proBean.getCheckValue()
				+ "',checkUserId='"
				+ proBean.getCheckUserId()
				+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),linkCode='"
				+ proBean.getLinkCode() + "' where physicCode='"
				+ proBean.getPhysicCode() + "' and code='" + proBean.getCode()
				+ "' ";

		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ proBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新塔桅运营商数据
	 */
	public static void updateMastComData(SQLiteDatabase db,
			AppMastComBean proBean) {
		String sql = "update ts_mast set checkValue='"
				+ proBean.getCheckValue()
				+ "',checkUserId='"
				+ proBean.getCheckUserId()
				+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),linkCode='"
				+ proBean.getLinkCode() + "' where code='" + proBean.getCode()
				+ "' ";
		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ proBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新开关电源运营商数据
	 */
	public static void updateSwitchComData(SQLiteDatabase db,
			AppSwitchComBean proBean) {
		try {
			String sql = "update ts_switch set checkValue='"+ proBean.getCheckValue() + "',checkUserId='"
					+ proBean.getCheckUserId()
					+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),linkCode='"
					+ proBean.getLinkCode() + "' where physicCode='"
					+ proBean.getPhysicCode() + "' and code='"
					+ proBean.getCode() + "' ";

			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ proBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新空调运营商
	 */
	public static void updateAirComData(SQLiteDatabase db, AppAirComBean proBean) {
		String sql = "update ts_air_c set checkValue='"+ proBean.getCheckValue() + "',checkUserId='"
				+ proBean.getCheckUserId()
				+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),linkCode='"
				+ proBean.getLinkCode() + "' where physicCode='"
				+ proBean.getPhysicCode() + "' and code='" + proBean.getCode()
				+ "' ";

		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ proBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新蓄电池运营商数据
	 */
	public static void updateBatteryComData(SQLiteDatabase db,
			AppBatteryComBean proBean) {
		// 
		String sql = "update ts_battery set checkValue='"+ proBean.getCheckValue() + "',checkUserId='"
				+ proBean.getCheckUserId()
				+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),linkCode='"
				+ proBean.getLinkCode() + "' where physicCode='"
				+ proBean.getPhysicCode() + "' and code='" + proBean.getCode()
				+ "' ";
		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ proBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新配电箱运营商
	 */
	public static void updatePowerComData(SQLiteDatabase db,
			AppPowerComBean proBean) {
		// 
		String sql = "update ts_power_b set checkValue='"+ proBean.getCheckValue() + "',checkUserId='"
				+ proBean.getCheckUserId()
				+ "',checkDate=strftime('%Y-%m-%d %H:%M:%S','now','localtime'),linkCode='"
				+ proBean.getLinkCode() + "' where physicCode='"
				+ proBean.getPhysicCode() + "' and code='" + proBean.getCode()
				+ "' ";
		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ proBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：判断问题表是否有数据
	 * 
	 * @Title: ifProblemData
	 * @author: 罗然
	 * @param db
	 * @param sql
	 * @return
	 * @return boolean 返回类型
	 * @version: 2015-7-21 下午6:08:26
	 */
	public static boolean ifProblemData(SQLiteDatabase db, String sql) {
		boolean flag = false;
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 完成保存
	 */
	public static void changeStatus(SQLiteDatabase db, String physicCode) {
		String sql = " update t_physic_info set status='2' where physicCode='"
				+ physicCode + "'";
		db.execSQL(sql);
	}

	/**
	 * 
	 * 此方法描述的是：修改图片上传状态
	 * 
	 * @Title: updatePicbean
	 * @author: 罗然
	 * @param db
	 * @param o
	 * @param i
	 * @return void 返回类型
	 * @version: 2015-7-27 下午6:54:23
	 */
	public static void updatePicbean(SQLiteDatabase db, String o, int i) {
		String sql = " update t_problem_pic set uploadStatus='" + i
				+ "' where picAddr='" + o + "'";
		db.execSQL(sql);
	}

	/**
	 * 
	 * 此方法描述的是：更改状体
	 * 
	 * @Title: updatePhysicalStatus
	 * @author: 罗然
	 * @param db
	 * @param i
	 * @return void 返回类型
	 * @version: 2015-7-27 下午7:09:36
	 */
	public static void updatePhysicalStatus(SQLiteDatabase db, int i,
			String code) {
		String sql = " update t_physic_info set status='" + i
				+ "' where physicCode='" + code + "'";
		db.execSQL(sql);
	}

	/**
	 * 
	 * 此方法描述的是：插入其他设备
	 * @Title: insertOtherDepartment 
	 * @author: 罗然
	 * @param db
	 * @param bean
	 * @return void    返回类型
	 * @version: 2015-8-14
	下午5:18:18
	 */
	public static void insertOtherDepartment(SQLiteDatabase db,
			AppOtherDepartment bean) {
		String sql = "insert into t_other_department(id,physicCode,physicIals,name,isHave,createUserId,createDate,demo,status) "
				+ "values ('"
				+ bean.getId()
				+ "','"
				+ bean.getPhysicCode()
				+ "','"
				+ bean.getPhysicIals()
				+ "','"
				+ bean.getName()
				+ "','2','"
				+ bean.getCreateUserId()
				+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'"
				+ bean.getDemo()
				+ "','"
				+ bean.getStatus()
				+ "');";
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：h恢复未选中状态
	 * @Title: updateAllOtherDepartment 
	 * @author: 罗然
	 * @param db
	 * @param code
	 * @param name
	 * @return void    返回类型
	 * @version: 2015-8-15
	下午8:28:29
	 */
	public static void updateAllOtherDepartment(SQLiteDatabase db,String code) {
		try{
			String sql = " update t_other_department set isHave='2' where physicCode='" + code + "'";
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ code+ "'";
			db.execSQL(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 此方法描述的是：更改其他设备
	 * @Title: updateOtherDepartment 
	 * @author: 罗然
	 * @param db
	 * @param code
	 * @param ids
	 * @return void    返回类型
	 * @version: 2015-8-14
	下午5:22:52
	 */
	public static void updateOtherDepartment(SQLiteDatabase db,String code,String name) {
		try{
			String sql = " update t_other_department set isHave='1' where physicCode='" + code + "' and id='"+name+"'";
			db.execSQL(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 此方法描述的是：查询其他设备
	 * @Title: queryDepartment 
	 * @author: 罗然
	 * @param db
	 * @param sql
	 * @return
	 * @return List<AppOtherDepartment>    返回类型
	 * @version: 2015-8-14
	下午5:09:38
	 */
	public static List<AppOtherDepartment> queryDepartment(DatabaseHelper mOpenHelper,
			String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<AppOtherDepartment> list = new ArrayList<AppOtherDepartment>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			AppOtherDepartment bean = new AppOtherDepartment();
			String id = cur.getString(cur.getColumnIndex("id"));
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String physicIals = cur.getString(cur.getColumnIndex("physicIals"));
			String name = cur.getString(cur.getColumnIndex("name"));
			String isHave = cur.getString(cur.getColumnIndex("isHave"));
			String status = cur.getString(cur.getColumnIndex("status"));
			String demo = cur.getString(cur.getColumnIndex("demo"));
			String createUserId = cur.getString(cur.getColumnIndex("createUserId"));
			String createDate = cur.getString(cur.getColumnIndex("createDate"));
			bean.setPhysicCode(physicCode);
			bean.setId(id);
			bean.setIsHave(isHave);
			bean.setName(name);
			bean.setPhysicIals(physicIals);
			bean.setDemo(demo);
			bean.setStatus(status);
			bean.setCreateUserId(createUserId);
			if (createDate != null && !createDate.equals("")
					&& !createDate.equals("null")) {
				try {
					Date d = sim.parse(createDate);
					bean.setCreateDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(bean);
		}
		db.close();
		return list;
	}

	/**
	 * 查询机房图片信息
	 * 
	 * @param mOpenHelper
	 * @param sql
	 * @return
	 */
	public static List<RoomPicBean> queryRoomPicData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<RoomPicBean> list = new ArrayList<RoomPicBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			RoomPicBean bean = new RoomPicBean();
			String id = cur.getString(cur.getColumnIndex("id"));
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String picAddr = cur.getString(cur.getColumnIndex("picAddr"));
			String createUserId = cur.getString(cur
					.getColumnIndex("createUserId"));
			String demo = cur.getString(cur.getColumnIndex("demo"));
			String status = cur.getString(cur.getColumnIndex("status"));
			String createDate = cur.getString(cur.getColumnIndex("createDate"));
			if (createDate != null && !createDate.equals("")
					&& !createDate.equals("null")) {
				try {
					Date d = sim.parse(createDate);
					bean.setCreateDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			bean.setPicAddr(picAddr);
			bean.setId(id);
			bean.setStatus(status);
			bean.setDemo(demo);
			bean.setPhysicCode(physicCode);
			bean.setCreateUserId(createUserId);
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 查询机房图片信息
	 * 
	 * @param mOpenHelper
	 * @param sql
	 * @return
	 */
	public static List<RoomItemBean> queryRoomItemData(
			DatabaseHelper mOpenHelper, String sql) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		List<RoomItemBean> list = new ArrayList<RoomItemBean>();
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			RoomItemBean bean = new RoomItemBean();
			String id = cur.getString(cur.getColumnIndex("id"));
			String physicCode = cur.getString(cur.getColumnIndex("physicCode"));
			String isok = cur.getString(cur.getColumnIndex("isok"));
			String createUserId = cur.getString(cur
					.getColumnIndex("createUserId"));
			String demo = cur.getString(cur.getColumnIndex("demo"));
			String status = cur.getString(cur.getColumnIndex("status"));
			String createDate = cur.getString(cur.getColumnIndex("createDate"));
			bean.setIsok(isok);
			if (createDate != null && !createDate.equals("")
					&& !createDate.equals("null")) {
				try {
					Date d = sim.parse(createDate);
					bean.setCreateDate(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			bean.setId(id);
			bean.setStatus(status);
			bean.setDemo(demo);
			bean.setPhysicCode(physicCode);
			bean.setCreateUserId(createUserId);
			list.add(bean);
		}
		cur.close();
		db.close();
		return list;
	}

	/**
	 * 添加机房图片
	 * 
	 * @param db
	 * @param proBean
	 */
	public static void insertRoomPic(SQLiteDatabase db, RoomPicBean proBean) {
		try {
			String sql = "insert into tb_room_pic(id,physicCode,picAddr,demo,status,createDate,createUserId) "
					+ "values ('"
					+ proBean.getId()+ "','"
					+ proBean.getPhysicCode()+ "','"
					+ proBean.getPicAddr()+ "','"
					+ proBean.getDemo()+ "','"
					+ proBean.getStatus()+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'"
					+ ApplicationData.user.getId() + "');";
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ proBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 此方法描述的是：删除机房图片
	 * @Title: deleteRoomPic 
	 * @author: 罗然
	 * @param db
	 * @param proBean
	 * @return void    返回类型
	 * @version: 2015-8-14
	下午5:58:55
	 */
	public static void deleteRoomPic(SQLiteDatabase db, RoomPicBean proBean) {
		String sql="";
		try {
			sql="delete from tb_room_pic where physicCode='" + proBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加机房图片状态
	 * 
	 * @param db
	 * @param proBean
	 */
	public static void insertRoomItem(SQLiteDatabase db, RoomItemBean proBean) {
		// itemflag(是否满足):1添加  2修改
		String sql="select * from tb_room_item where physicCode='"+proBean.getPhysicCode()+"'";
		boolean flag = false;
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			flag = true;
		}
		if(!flag){
			sql = "insert into tb_room_item(id,physicCode,isok,demo,status,createDate,createUserId) "
					+ "values ('"
					+ proBean.getId()
					+ "','"
					+ proBean.getPhysicCode()
					+ "','"
					+ proBean.getIsok()
					+ "','"
					+ proBean.getDemo()
					+ "','"
					+ proBean.getStatus()
					+ "',strftime('%Y-%m-%d %H:%M:%S','now','localtime'),'"
					+ ApplicationData.user.getId() + "');";
		}else{
			sql="update tb_room_item set isok='"+proBean.getIsok()+"' where physicCode='"+proBean.getPhysicCode()+"'";
		}
		try {
			db.execSQL(sql);
			sql = " update t_physic_info set status='1' where physicCode='"
					+ proBean.getPhysicCode() + "'";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateRoomPicbean(SQLiteDatabase db, String o, int i) {
		String sql = " update tb_room_pic set status='" + i
				+ "' where picAddr='" + o + "'";
		db.execSQL(sql);
	}

	/**
	 * 
	 * 此方法描述的是：其他设备是否有数据
	 * @Title: ifOtehersData 
	 * @author: 罗然
	 * @param db
	 * @param sql
	 * @return
	 * @return boolean    返回类型
	 * @version: 2015-8-15
	上午11:10:54
	 */
	public static boolean ifOtehersData(SQLiteDatabase db, String sql) {
		boolean flag = false;
		Cursor cur = null;
		cur = db.rawQuery(sql, null);
		while (cur.moveToNext()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * 此方法描述的是：修改机房电表编号图片
	 * @Title: updateRoomDbbh 
	 * @author: 罗然
	 * @param db
	 * @param code
	 * @param name
	 * @return void    返回类型
	 * @version: 2015-8-24
	下午2:48:53
	 */
	public static void updateRoomDbbh(SQLiteDatabase db,String code,String name) {
		try{
			String sql = " update tb_room set lxCheck='"+name+"',jgCheck='0' where code='" + code + "'";
			db.execSQL(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 此方法描述的是：修改电表编号上传成功的状态
	 * @Title: updateDbbhImg 
	 * @author: 罗然
	 * @param db
	 * @param name
	 * @return void    返回类型
	 * @version: 2015-8-24
	下午5:08:40
	 */
	public static void updateDbbhImg(SQLiteDatabase db,String name) {
		try{
			String sql = " update tb_room set jgCheck='1' where lxCheck='" + name + "'";
			db.execSQL(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	* 此方法描述的是：图片校验修改上传失败的图片状态
	* @Title: updatePicStatus 
	* @author: 罗然
	* @param db
	* @param o
	* @return void    返回类型
	* @version: 2015-8-28
	下午6:47:25
	 */
	public static void updatePicStatus(SQLiteDatabase db, String o) {
		try{
			String sql = " update t_problem_pic set uploadStatus='2' where id='" + o + "'";
			db.execSQL(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	* 此方法描述的是：重新盘点修改图片状态
	* @Title: updatePicFailStatus 
	* @author: 罗然
	* @param db
	* @param o
	* @return void    返回类型
	* @version: 2015-8-28
	下午9:54:09
	 */
	public static void updatePicFailStatus(SQLiteDatabase db, String o) {
		try{
			String sql = " update t_problem_pic set uploadStatus='2' where physicCode='"
					+ o+ "'";
			db.execSQL(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
