package com.woyi.towerzj_app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TowerSQLiteDb extends SQLiteOpenHelper {
	public static final String CREATE_TBMAST = "create table tb_mast ("
			+ "id VARCHAR2(60), " + "code VARCHAR2(60), "
			+ "physicCode VARCHAR2(30), " + "xs VARCHAR2(60), "
			+ "lx VARCHAR2(60), " + "tg DEC(12,2), " + "xsCheck VARCHAR2(60), "
			+ "lxCheck VARCHAR2(60), " + "tgCheck DEC(12,2), "
			+ "ewm VARCHAR2(60), " + "checkUserId VARCHAR2(30), "
			+ "checkDate DATE)";
	public static final String CREATE_TBROOM = "create table tb_room ("
			+ "physicCode VARCHAR2(30), " + "id VARCHAR2(60), "
			+ "code VARCHAR2(60), " + "name VARCHAR2(60), "
			+ "lx VARCHAR2(60), " + "jg VARCHAR2(60), " + "mj DEC(12,2), "
			+ "lxCheck VARCHAR2(60), " + "jgCheck VARCHAR2(60), "
			+ "mjCheck DEC(12,2), " + "ewm VARCHAR2(60), "
			+ "checkUserId VARCHAR2(30), " + "checkDate DATE)";
	public static final String CREATE_AIR = "create table tb_air_c ("
			+ "id VARCHAR2(60), " + "physicCode VARCHAR2(30), "
			+ "code VARCHAR2(60), " + "name VARCHAR2(60), "
			+ "cjCheck VARCHAR2(60), " + "cj VARCHAR2(60), "
			+ "ewm VARCHAR2(60), " + "checkUserId VARCHAR2(30), "
			+ "checkDate DATE)";
	public static final String CREATE_BATTERY = "create table tb_battery ("
			+ "physicCode VARCHAR2(30), " + "id VARCHAR2(60), "
			+ "code VARCHAR2(60), " + "name VARCHAR2(60), "
			+ "pp VARCHAR2(60), " + "rl DEC(12,2), " + "ppCheck VARCHAR2(60), "
			+ "rlCheck DEC(12,2), " + "ewm VARCHAR2(60), "
			+ "checkUserId VARCHAR2(30), " + "checkDate DATE)";
	public static final String CREATE_SWITCH = "create table tb_switch ("
			+ "id VARCHAR2(60), " + "physicCode VARCHAR2(30), "
			+ "code VARCHAR2(60), " + "name VARCHAR2(60), "
			+ "pp VARCHAR2(60), " + "rl DEC(12,2), " + "ppCheck VARCHAR2(60), "
			+ "rlCheck DEC(12,2), " + "ewm VARCHAR2(60), "
			+ "checkUserId VARCHAR2(30), " + "checkDate DATE)";
	public static final String CREATE_POWER = "create table tb_power_b ("
			+ "id VARCHAR2(60), " + "physicCode VARCHAR2(30), "
			+ "code VARCHAR2(60), " + "name VARCHAR2(60), "
			+ "pp VARCHAR2(60), " + "rl DEC(12,2), " + "ppCheck VARCHAR2(60), "
			+ "rlCheck DEC(12,2), " + "ewm VARCHAR2(60), "
			+ "checkUserId VARCHAR2(30), " + "checkDate DATE)";
	public static final String CREATE_GUARD = "create table t_guard ("
			+ "id VARCHAR2(60), " + "physicCode VARCHAR2(30), "
			+ "makeCode  VARCHAR2(60), " + "fsuType VARCHAR2(60), "
			+ "tempSensor INTEGER, " + "smokeSensor INTEGER, "
			+ "readSensor INTEGER, " + "waterSensor INTEGER, "
			+ "lightSensor INTEGER" + "doorSystem INTEGER, "
			+ "ipCamer INTEGER, " + "eMeter INTEGER, "
			+ "checkUserId VARCHAR2(30), " + "checkDate DATE)";
	public static final String CREATE_ANTENNA = "create table t_problem ("
			+ "id VARCHAR2(60), " + "physicCode VARCHAR2(60), "
			+ "antennaType VARCHAR2(30), " + "antennahigh VARCHAR2(30),"
			+ "qty INTEGER, " + "checkUserId VARCHAR2(30), "
			+ "checkDate DATE)";
	public static final String CREATE_PHYSICAL = "create table t_physic_info ("
			+ "id VARCHAR2(60), " + "physicCode VARCHAR2(60), "
			+ "physicAlias  VARCHAR2(100), " + "physicName VARCHAR2(100), "
			+ "physicAddr VARCHAR2(200), " + "checkUserId VARCHAR2(30), "
			+ "checkDate DATE, " + "status VARCHAR2(30), " + "uploadDate DATE, "
			+ "createDate DATE, " + "createUserId VARCHAR2(30))";

	public static final String CREATE_TPROBLEM = "create table t_antenna ("
			+ "type VARCHAR2(30), " + "code VARCHAR2(30), "
			+ "name VARCHAR2(100), " + "desc VARCHAR2(100))";

	public TowerSQLiteDb(Context context) {
		super(context, "towerzjDB", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TPROBLEM);
		db.execSQL(CREATE_TBMAST);
		db.execSQL(CREATE_TBROOM);
		db.execSQL(CREATE_AIR);
		db.execSQL(CREATE_BATTERY);
		db.execSQL(CREATE_SWITCH);
		db.execSQL(CREATE_POWER);
		db.execSQL(CREATE_GUARD);
		db.execSQL(CREATE_ANTENNA);
		db.execSQL(CREATE_PHYSICAL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
