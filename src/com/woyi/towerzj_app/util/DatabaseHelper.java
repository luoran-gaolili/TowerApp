package com.woyi.towerzj_app.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private Context context;
	public static final String DATABASE_NAME = "tower.db";
	public static final int DATABASE_VERSION = 3;
	public final static String TABLE_NAME = "t_user";// 保存登录用户的信息表

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION); // 必须调用此构造函数
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 机房图片表
		String sql = "create table tb_room_item (" + "id text, "
				+ "physicCode text, " + "isok  text, " + "demo text, "
				+ "status text, " + "createDate date, " + "createUserId text)";
		db.execSQL(sql);
		// 机房图片表
		sql = "create table tb_room_pic (" + "id text, " + "physicCode text, "
				+ "picAddr  text, " + "demo text, " + "status text, "
				+ "createDate date, " + "createUserId text)";
		db.execSQL(sql);

		// 物理站信息
		sql = "create table t_physic_info (" + "id text, "
				+ "physicCode text, " + "physicAlias  text, "
				+ "physicName text, " + "physicAddr text, "
				+ "checkUserId text, " + "checkDate DATE, " + "status text, "
				+ "physicType text, " + "longitude text, " + "latitude text, "
				+ "isPass text, " + "uploadDate DATE, " + "createDate date, "
				+ "createUserId text,gpslongitude text,gpslatitude text,bdlongitude text,bdlatitude text)";
		db.execSQL(sql);
		// 塔桅
		sql = "create table tb_mast (" + "id text, " + "code text, "
				+ "physicCode text, " + "xs text, " + "lx text, "
				+ "tg DEC(12,2), " + "xsCheck text, " + "lxCheck text, "
				+ "tgCheck DEC(12,2), " + "ewm text, " + "status text, "
				+ "checkUserId text, " + "checkDate DATE)";
		db.execSQL(sql);
		// 机房
		sql = "create table tb_room (" + "physicCode text, " + "id text, "
				+ "code text, " + "name text, " + "lx text, " + "jg text, "
				+ "mj DEC(12,2), " + "lxCheck text, " + "jgCheck text, "
				+ "mjCheck DEC(12,2), " + "ewm text, " + "status text, "
				+ "checkUserId text, " + "checkDate DATE,dbBh text,dbDs DEC(12,2),jfYs text,jfYsJj text)";
		db.execSQL(sql);
		// 空调
		sql = "create table tb_air_c (" + "id text, " + "physicCode text, "
				+ "code text, " + "name text, " + "cjCheck text, "
				+ "cj text, " + "ewm text, " + "status text, "
				+ "checkUserId text, " + "checkDate DATE)";
		db.execSQL(sql);
		// 蓄电池
		sql = "create table tb_battery (" + "physicCode text, " + "id text, "
				+ "code text, " + "name text, " + "pp text, "
				+ "rl DEC(12,2), " + "ppCheck text, " + "rlCheck DEC(12,2), "
				+ "ewm text, " + "status text, " + "checkUserId text, "
				+ "checkDate DATE)";
		db.execSQL(sql);
		// 开关电源
		sql = "create table tb_switch (" + "id text, " + "physicCode text, "
				+ "code text, " + "name text, " + "pp text, "
				+ "rl DEC(12,2), " + "ppCheck text, " + "rlCheck DEC(12,2), "
				+ "ewm text, " + "status text, " + "checkUserId text, "
				+ "checkDate DATE)";
		db.execSQL(sql);
		// 配电箱
		sql = "create table tb_power_b (" + "id text, " + "physicCode text, "
				+ "code text, " + "name text, " + "pp text, "
				+ "rl DEC(12,2), " + "ppCheck text, " + "rlCheck DEC(12,2), "
				+ "ewm text, " + "status text, " + "checkUserId text, "
				+ "checkDate DATE)";
		db.execSQL(sql);
		// 动环
		sql = "create table t_guard (" + "id text, " + "physicCode text, "
				+ "makeCode  text, " + "fsuType text, "
				+ "tempSensor INTEGER, " + "smokeSensor INTEGER, "
				+ "readSensor INTEGER, " + "waterSensor INTEGER, "
				+ "lightSensor INTEGER, " + "doorSystem INTEGER, "
				+ "ipCamer INTEGER, " + "eMeter INTEGER, "
				+ "checkUserId text, " + "checkDate DATE,mjzjCode text,mjzjType text,jkzjCode text,jkzjType text)";
		db.execSQL(sql);

		// 天线
		sql = "create table t_antenna (" + "id text, " + "physicCode text, "
				+ "dx2g text, " + "dx2gHeight text, " + "dx2gPt text, "
				+ "dx3g text, " + "dx3gHeight text, " + "dx3gPt text, "
				+ "dx4g text, " + "dx4gHeight text, " + "dx4gPt text, "
				+ "dxjjs text, "
				+ "yd2g900 text, " + "yd2g900Height text, "
				+ "yd2g900Pt text, " + "yd2g1800 text, "
				+ "yd2g1800Height text, " + "yd2g1800Pt text, " + "yd3g text, "
				+ "yd3gHeight text," + "yd3gPt text," + "yd4g text,"
				+ "yd4gHeight text," + "yd4gPt text," + "ydjjs text,"
				+ "lt2g900 text," + "lt2g900Height text," + "lt2g900Pt text,"
				+ "lt2g1800 text," + "lt2g1800Height text,"
				+ "lt2g1800Pt text," + "lt3g text," + "lt3gHeight text,"
				+ "lt3gPt text," + "lt4g text," + "lt4gHeight text,"
				+ "lt4gPt text," + "ltjjs text,"
				+ "checkUserId text, " + "checkDate DATE," + "status text,"
				+ "operator text,yd4gD text,yd4gHeightD text,yd4gPtD text)";
		db.execSQL(sql);

		// 问题附件
		sql = "create table t_problem_pic (" + "id text, " + "type text, "
				+ "code text, " + "physicCode text," + "codeLink text,"
				+ "pic_id text, " + "picSeq INTEGER, " + "picAddr text, "
				+ "checkCode text,uploadStatus text, " + "checkUserId text, "
				+ "checkDate DATE, " + "createDate DATE, "
				+ "createUserId text,linkCode text)";
		db.execSQL(sql);
		// 塔桅运营商
		sql = "create table ts_mast (" + "id text, " + "physicCode text, "
				+ "type text, " + "code text," + "xs text, " + "lx text, "
				+ "tg DEC(12,2), " + "checkValue text, " + "checkUserId text, "
				+ "checkDate DATE," + "linkCode text, " + "assetNum text)";
		db.execSQL(sql);
		// 机房运营商
		sql = "create table ts_room (" + "physicCode text, " + "id text, "
				+ "type text, " + "code text," + "name text, " + "lx text, "
				+ "jg text, " + "mj DEC(12,2), " + "checkValue text, "
				+ "checkUserId text, " + "checkDate DATE, " + "linkCode text, "
				+ "assetNum text)";
		db.execSQL(sql);
		// 配电箱运营商
		sql = "create table ts_power_b (" + "id text, " + "physicCode text, "
				+ "type text, " + "code text," + "name text, " + "pp text, "
				+ "rl DEC(12,2), " + "checkValue text, " + "checkUserId text, "
				+ "checkDate DATE, " + "linkCode text, " + "assetNum text)";
		db.execSQL(sql);
		// 空调运营商
		sql = "create table ts_air_c (" + "physicCode text, " + "id text, "
				+ "type text, " + "code text," + "name text, " + "cj text, "
				+ "checkValue text, " + "checkUserId text, "
				+ "checkDate DATE, " + "linkCode text, " + "assetNum text)";
		db.execSQL(sql);
		// 蓄电池运营商
		sql = "create table ts_battery (" + "id text, " + "physicCode text, "
				+ "type text, " + "code text," + "name text, " + "pp text, "
				+ "rl DEC(12,2), " + "checkValue text, " + "checkUserId text, "
				+ "checkDate DATE, " + "linkCode text, " + "assetNum text)";
		db.execSQL(sql);
		// 开关电源运营商
		sql = "create table ts_switch (" + "physicCode text, " + "id text, "
				+ "type text, " + "code text," + "name text, " + "pp text, "
				+ "rl DEC(12,2), " + "checkValue text, " + "checkUserId text, "
				+ "checkDate DATE, " + "linkCode text, " + "assetNum text)";
		db.execSQL(sql);

		// 问题
		sql = "create table t_problem (" + "type text, " + "code text, "
				+ "name text, " + "desc text)";
		db.execSQL(sql);
		// 关系表
		sql = "create table t_oldnew (" + "oldType text, " + "newType text)";
		db.execSQL(sql);
		// 其他设备表
		sql = "create table t_other_department (id text,physicCode text,physicIals text,name text,isHave text,createUserId text,createDate DATE,demo text,status text)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		String sql="";
//		// 第3个数据库版本
//		if ( newVersion == 6) {
//			sql="alter table t_guard add ceshi varchar(32);";
//		}
//		db.execSQL(sql);
	}

}
