package com.woyi.towerzj_app.activity.menu;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.GrxxActivity;
import com.woyi.towerzj_app.activity.MmxgActivity;
import com.woyi.towerzj_app.activity.PhysicalActivity;
import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.adapter.MenuYscAdapter;
import com.woyi.towerzj_app.adapter.MenuZcAdapter;
import com.woyi.towerzj_app.bean.AppOtherDepartment;
import com.woyi.towerzj_app.bean.AppVersionBean;
import com.woyi.towerzj_app.bean.ResultUpList;
import com.woyi.towerzj_app.bean.RoomItemBean;
import com.woyi.towerzj_app.bean.donghuan.DongHuanBean;
import com.woyi.towerzj_app.bean.jifang.AppRoomBean;
import com.woyi.towerzj_app.bean.jifang.AppRoomComBean;
import com.woyi.towerzj_app.bean.kgdy.AppSwitchBean;
import com.woyi.towerzj_app.bean.kgdy.AppSwitchComBean;
import com.woyi.towerzj_app.bean.kongtiao.AppAirBean;
import com.woyi.towerzj_app.bean.kongtiao.AppAirComBean;
import com.woyi.towerzj_app.bean.peidianx.AppPowerBean;
import com.woyi.towerzj_app.bean.peidianx.AppPowerComBean;
import com.woyi.towerzj_app.bean.quedtion.AppProblemPicBean;
import com.woyi.towerzj_app.bean.tawei.AppMastBean;
import com.woyi.towerzj_app.bean.tawei.AppMastComBean;
import com.woyi.towerzj_app.bean.tianxian.AppAntennaBean;
import com.woyi.towerzj_app.bean.xudianchi.AppBatteryBean;
import com.woyi.towerzj_app.bean.xudianchi.AppBatteryComBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.FileUtils;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.FtpUtil;
import com.woyi.towerzj_app.util.Function;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;

/**
 * 
 * 此类描述的是：主页
 * 
 * @author: 罗然
 * @version: 2015-7-17 下午2:47:27
 * @ClassName: MainActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity
 */
public class MainActivity extends ForwardActivity {

	private Button back, delete;
	private TextView title, cz, handler_tv, del_tv;
	private Button tab_zc, tab_ysc;

	private ListView listview_zc, listview_ysc;
	private MenuZcAdapter zcAdapter;
	private MenuYscAdapter yscAdapter;
	private List<AppPhyInfoBean> list;
	private List<AppPhyInfoBean> zclist;
	private List<AppPhyInfoBean> ysclist;
	private DatabaseHelper mOpenHelper;
	private AppVersionBean versionBean;
	private Dialog dialog;
	private SelectPicPopupWindow exitWindow;
	private UpPicPopupWindow upWindow;
	private HandlePopupWindow handleWindow;
	private DelPopupWindow delWindow;
	private String lat;
	private String lon;
	private ImageView image_sy, image_mmxg, image_grxx;
	private TextView text_sy, text_mmxg, text_grxx;
	private Drawable syPress, syNormal, mmxgPress, mmxgNormal, grxxPress,
			grxxNormal;
	private Resources res;
	private FrameLayout layout_sy, layout_mmxg, layout_grxx;
	private AppPhyInfoBean bean;

	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	// 上传数据
	private List<String> drr = new ArrayList<String>();
	public List<String> sucessdrr = new ArrayList<String>();
	public List<String> faildrr = new ArrayList<String>();

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
	// 天线
	List<AppAntennaBean> antennalist = new ArrayList<AppAntennaBean>();
	// 动环
	List<DongHuanBean> dhlist = new ArrayList<DongHuanBean>();

	// 图片
	private List<AppProblemPicBean> picList = new ArrayList<AppProblemPicBean>();

	// 机房item
	private RoomItemBean roomItem;

	private List<AppOtherDepartment> others = new ArrayList<AppOtherDepartment>();

	private ResultUpList upBean;

	private SQLiteDatabase db;

	// 图片校验（存取上传失败的图片id）
	private List<String> strlist;
	private String picName = "";
	FTPClient ftp = null;
	FtpUtil a = new FtpUtil();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_main);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		title.setText("资产清查");
		cz.setText("查询");
		loadList();
		//ApplicationData.getExecutorService().submit(getMaxVersionRun);
		back.setText("退出应用");
		res = getResources();
		syPress = res.getDrawable(R.drawable.icon_sy_sel);
		syNormal = res.getDrawable(R.drawable.icon_sy);
		mmxgPress = res.getDrawable(R.drawable.icon_mmxg_sel);
		mmxgNormal = res.getDrawable(R.drawable.icon_mmxg);
		grxxPress = res.getDrawable(R.drawable.icon_grxx_sel);
		grxxNormal = res.getDrawable(R.drawable.icon_grxx);
		layout_sy.setOnClickListener(btnLis);
		layout_mmxg.setOnClickListener(btnLis);
		layout_grxx.setOnClickListener(btnLis);
		cz.setOnClickListener(btnLis);
		back.setOnClickListener(btnLis);
		tab_zc.setOnClickListener(btnLis);
		tab_ysc.setOnClickListener(btnLis);
		listview_zc.setOnItemClickListener(itemLis);
		listview_ysc.setOnItemClickListener(itemLis);
	//	isGpsEnable();
	}

	/**
	 * 列表点击事件
	 */
	private OnItemClickListener itemLis = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			bean = (AppPhyInfoBean) arg0.getItemAtPosition(arg2);
			Bundle bundle = new Bundle();
			bundle.putSerializable("bean", bean);
			if (bean.getStatus().equals("2") || bean.getStatus().equals("3")) {
				upWindow = new UpPicPopupWindow(MainActivity.this, null);
				upWindow.showAtLocation(
						MainActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
			} else if (bean.getStatus().equals("4")) {
				handleWindow = new HandlePopupWindow(MainActivity.this, null);
				handleWindow.showAtLocation(
						MainActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
			} else {
				delWindow = new DelPopupWindow(MainActivity.this, null);
				delWindow.showAtLocation(
						MainActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
			}
		}
	};

	/**
	 * 
	 * 此方法描述的是：加载列表
	 * 
	 * @Title: loadList
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-20 下午5:35:04
	 */
	private void loadList() {
		list = new ArrayList<AppPhyInfoBean>();
		String sql = "select * from t_physic_info ";
		try {
			SQLiteDatabase db = mOpenHelper.getReadableDatabase();
			list = TowerSQliteDbBean.queryPhysicalData(db, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}
	}

	/**
	 * 获取最新版本
	 */
	private Runnable getMaxVersionRun = new Runnable() {

		@Override
		public void run() {
			try {
				String result = Function.getMaxVersion();
				ObjectMapper om = new ObjectMapper();
				versionBean = om.readValue(result, TypeFactory
						.fromTypeReference(new TypeReference<AppVersionBean>() {
						}));
				versionHandler.obtainMessage(1).sendToTarget();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * 更改
	 */
	private Runnable upstatsrun = new Runnable() {

		@Override
		public void run() {
			try {
				String result = Function.upstats(bean.getPhysicCode());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	};

	/**
	 * 按钮监听事件
	 */
	private View.OnClickListener btnLis = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.back:
				exitWindow = new SelectPicPopupWindow(MainActivity.this, null);
				exitWindow.showAtLocation(
						MainActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				break;
			case R.id.tab_zc:// 暂存
				selZc();
				break;
			case R.id.tab_ysc:
				selYsc();
				break;
			case R.id.cz:
				forwardIntent(getApplicationContext(), QueryActivity.class);
				break;
			case R.id.exit:
				exit();
				exitWindow.dismiss();
				break;
			case R.id.cancle:
				exitWindow.dismiss();
				break;
			case R.id.up:
				loadAllData();
				upWindow.dismiss();

				break;
			case R.id.query:
				upWindow.dismiss();
				Bundle bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(), PhysicalActivity.class,
						bundle);
				break;
			case R.id.handler:
				handleWindow.dismiss();
				try {
					db = mOpenHelper.getReadableDatabase();
					TowerSQliteDbBean.updatePhysicalStatus(db, 0,
							bean.getPhysicCode());
					TowerSQliteDbBean.updatePicFailStatus(db,
							bean.getPhysicCode());
					handler.obtainMessage(3).sendToTarget();
				} catch (Exception e) {
					handler.obtainMessage(-1).sendToTarget();
				}
				break;
			case R.id.handler_query:
				handleWindow.dismiss();
				bundle = new Bundle();
				bundle.putString("lat", lat);
				bundle.putString("lon", lon);
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(), PhysicalActivity.class,
						bundle);
				break;

			case R.id.layout_sy:
				image_sy.setImageDrawable(syPress);
				image_mmxg.setImageDrawable(mmxgNormal);
				image_grxx.setImageDrawable(grxxNormal);
				text_sy.setTextColor(android.graphics.Color.rgb(0xff, 0x00,
						0x00));
				text_mmxg.setTextColor(android.graphics.Color.rgb(0x82, 0x82,
						0x82));
				text_grxx.setTextColor(android.graphics.Color.rgb(0x82, 0x82,
						0x82));
				break;
			case R.id.layout_mmxg:
				image_sy.setImageDrawable(syNormal);
				image_mmxg.setImageDrawable(mmxgPress);
				image_grxx.setImageDrawable(grxxNormal);
				text_sy.setTextColor(android.graphics.Color.rgb(0x82, 0x82,
						0x82));
				text_mmxg.setTextColor(android.graphics.Color.rgb(0xff, 0x00,
						0x00));
				text_grxx.setTextColor(android.graphics.Color.rgb(0x82, 0x82,
						0x82));
				forwardIntent(getApplicationContext(), MmxgActivity.class);
				break;
			case R.id.layout_grxx:
				image_sy.setImageDrawable(syNormal);
				image_mmxg.setImageDrawable(mmxgNormal);
				image_grxx.setImageDrawable(grxxPress);
				text_sy.setTextColor(android.graphics.Color.rgb(0x82, 0x82,
						0x82));
				text_mmxg.setTextColor(android.graphics.Color.rgb(0x82, 0x82,
						0x82));
				text_grxx.setTextColor(android.graphics.Color.rgb(0xff, 0x00,
						0x00));
				forwardIntent(getApplicationContext(), GrxxActivity.class);
				break;
			case R.id.delete:// 暂存站点的删除
				upWindow.dismiss();
				try {
					db = mOpenHelper.getReadableDatabase();
					TowerSQliteDbBean.deleData(db, bean.getPhysicCode(), "",
							null, "07");
					handler.obtainMessage(8).sendToTarget();
				} catch (Exception e) {
					handler.obtainMessage(-1).sendToTarget();
				}
				break;
			case R.id.del:// 删除
				delWindow.dismiss();
				try {
					db = mOpenHelper.getReadableDatabase();
					TowerSQliteDbBean.deleData(db, bean.getPhysicCode(), "",
							null, "07");
					handler.obtainMessage(8).sendToTarget();
				} catch (Exception e) {
					handler.obtainMessage(-1).sendToTarget();
				}
				break;
			case R.id.query2:
				delWindow.dismiss();
				bundle = new Bundle();
				bundle.putString("lat", lat);
				bundle.putString("lon", lon);
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(), PhysicalActivity.class,
						bundle);
				break;
			}
		}
	};

	/**
	 * 监测是否开启GPS定位
	 */
	public void isGpsEnable() {
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			// toastMessage("GPS可用");
			// GPS可用
		} else {
			// GPS不可用
			toastMessage("GPS不可用,请手动设置打开");
		}

	}

	/**
	 * 
	 * 此方法描述的是：加载所有本地数据
	 * 
	 * @Title: loadAllData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-27 下午4:52:54
	 */
	protected void loadAllData() {
		// 塔桅
		try {
			String sql = "";
			upBean = new ResultUpList();
			if (bean.getStatus().equals("2")) {
				sql = "select * from tb_mast where physicCode='"
						+ bean.getPhysicCode() + "'";
				appMastList = TowerSQliteDbBean.queryMastData(mOpenHelper, sql);
				sql = "select * from ts_mast where physicCode='"
						+ bean.getPhysicCode() + "'";
				appMastComList = TowerSQliteDbBean.queryMastComData(
						mOpenHelper, sql);
				// 机房
				sql = "select * from tb_room where physicCode='"
						+ bean.getPhysicCode() + "'";
				appRoomList = TowerSQliteDbBean.queryRoomData(mOpenHelper, sql);
				sql = "select * from ts_room where physicCode='"
						+ bean.getPhysicCode() + "' ";
				appRoomComList = TowerSQliteDbBean.queryRoomComData(
						mOpenHelper, sql);
				// 空调
				sql = "select * from tb_air_c where physicCode='"
						+ bean.getPhysicCode() + "'";
				appAirList = TowerSQliteDbBean.queryAirData(mOpenHelper, sql);
				sql = "select * from ts_air_c where physicCode='"
						+ bean.getPhysicCode() + "'";
				appAirComList = TowerSQliteDbBean.queryAirComData(mOpenHelper,
						sql);
				// 蓄电池
				sql = "select * from tb_battery where physicCode='"
						+ bean.getPhysicCode() + "'";
				appBatteryList = TowerSQliteDbBean.queryBatteryData(
						mOpenHelper, sql);
				sql = "select * from ts_battery where physicCode='"
						+ bean.getPhysicCode() + "'";
				appBatteryComList = TowerSQliteDbBean.queryBatteryComData(
						mOpenHelper, sql);
				// 开关电源
				sql = "select * from tb_switch where physicCode='"
						+ bean.getPhysicCode() + "'";
				appSwitchList = TowerSQliteDbBean.querySwitchData(mOpenHelper,
						sql);
				sql = "select * from ts_switch where physicCode='"
						+ bean.getPhysicCode() + "' ";
				appSwitchComList = TowerSQliteDbBean.querySwitchComData(
						mOpenHelper, sql);

				// 配电箱
				sql = "select * from tb_power_b where physicCode='"
						+ bean.getPhysicCode() + "'";
				appPowerList = TowerSQliteDbBean.queryPowerData(mOpenHelper,
						sql);
				sql = "select * from ts_power_b where physicCode='"
						+ bean.getPhysicCode() + "' ";
				appPowerComList = TowerSQliteDbBean.queryPowerComData(
						mOpenHelper, sql);

				// 天线
				sql = "select * from t_antenna where physicCode='"
						+ bean.getPhysicCode() + "' ";
				antennalist = TowerSQliteDbBean.getAntenaData(mOpenHelper, sql);

				// 动环
				sql = "select * from t_guard where physicCode='"
						+ bean.getPhysicCode() + "' ";
				dhlist = TowerSQliteDbBean.getGuardData(mOpenHelper, sql);
				upBean.setBean(bean);

				// 其他设备
				others = new ArrayList<AppOtherDepartment>();
				sql = "select * from t_other_department where physicCode='"
						+ bean.getPhysicCode() + "'";
				others = TowerSQliteDbBean.queryDepartment(mOpenHelper, sql);

				// 机房是否满足
				sql = "select * from tb_room_item where physicCode='"
						+ bean.getPhysicCode() + "'";
				List<RoomItemBean> itemList = TowerSQliteDbBean
						.queryRoomItemData(mOpenHelper, sql);
				roomItem = new RoomItemBean();
				if (itemList != null && itemList.size() > 0) {
					roomItem = itemList.get(0);
				}
				upBean.setRoomItem(roomItem);
				upBean.setAppAirComList(appAirComList);
				upBean.setAppAirList(appAirList);
				upBean.setAppBatteryComList(appBatteryComList);
				upBean.setAppBatteryList(appBatteryList);
				upBean.setAppMastComList(appMastComList);
				upBean.setAppMastList(appMastList);
				upBean.setAppPowerComList(appPowerComList);
				upBean.setAppPowerList(appPowerList);
				upBean.setAppRoomComList(appRoomComList);
				upBean.setAppRoomList(appRoomList);
				upBean.setAppSwitchComList(appSwitchComList);
				upBean.setAppSwitchList(appSwitchList);
				upBean.setAntennalist(antennalist);
				upBean.setOthers(others);
				upBean.setDhlist(dhlist);
			}
			// 图片
			sql = "select * from t_problem_pic where physicCode='"
					+ bean.getPhysicCode() + "' and uploadStatus  in (0,2)";
			picList = TowerSQliteDbBean.queryProblemPicData(mOpenHelper, sql);
			upBean.setAllProblemPicList(picList);
			if (bean.getStatus().equals("3")) {
				if (picList != null && picList.size() > 0) {// 问题图片
					relative_layout.setVisibility(View.VISIBLE);
					loading.startAnimation(animation);
					drr = new ArrayList<String>();
					for (AppProblemPicBean o : picList) {
						drr.add(o.getPicAddr());
					}
					uploadData();
					upWindow.dismiss();
				} else {
					upPicture();
				}
			} else {
				handler.obtainMessage(5).sendToTarget();
			}
		} catch (Exception e) {
			handler.obtainMessage(-5).sendToTarget();
		}

	}

	/**
	 * 
	 * 此方法描述的是：改变伤处啊状态
	 * 
	 * @Title: changeStatus
	 * @author: 罗然
	 * @param i
	 * @return void 返回类型
	 * @version: 2015-8-17 下午1:41:37
	 */
	private void changeStatus(int i) {
		try {
			db = mOpenHelper.getReadableDatabase();
			TowerSQliteDbBean.updatePhysicalStatus(db, i, bean.getPhysicCode());
			loadList();
			selYsc();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	/**
	 * 
	 * 此方法描述的是：删除本地所有数据
	 * 
	 * @Title: delAllData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-27 下午10:09:15
	 */
	protected void delAllData() {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		try {
			// 开始事务
			db.beginTransaction();
			TowerSQliteDbBean
					.deleData(db, bean.getPhysicCode(), "", null, "07");
			db.setTransactionSuccessful();
			handler.obtainMessage(8).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		} finally {
			if (db != null) {
				db.endTransaction();
				db.close();
			}
		}
	}

	/**
	 * 上传数据
	 */
	private Runnable upRun = new Runnable() {

		@Override
		public void run() {
			try {
				String result = Function.upLoad(upBean);
				ObjectMapper om = new ObjectMapper();
				String info = om.readValue(result, TypeFactory
						.fromTypeReference(new TypeReference<String>() {
						}));
				if (!info.equals("-1")) {
					if (info.equals("1")) {
						handler.obtainMessage(6).sendToTarget();
					} else if (info.equals("-2")) {
						handler.obtainMessage(-1).sendToTarget();
					}
				} else {
					handler.obtainMessage(-1).sendToTarget();
				}
			} catch (Exception e) {
				handler.obtainMessage(-1).sendToTarget();
			}
		}
	};

	/**
	 * 
	 * 此方法描述的是：上传图片
	 * 
	 * @Title: uploadData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-27 下午4:51:07
	 */
	private void uploadData() {
		new Thread() {

			@Override
			public void run() {
				super.run();
				// 服务器的访问路径
				String end = "\r\n";
				String twoHyphens = "--";
				String boundary = "******";
				try {
					sucessdrr = new ArrayList<String>();
					faildrr = new ArrayList<String>();
					// URL url = new URL(Function.UPLOAD_URL);
					HttpURLConnection httpURLConnection = null;
					DataOutputStream dos = null;
					FileInputStream fis = null;
					String fileResult = "";
					InputStream is = null;
					// String filename="";
					// File f = null;
					for (String o : drr) {
						// httpURLConnection = (HttpURLConnection)
						// url.openConnection();
						httpURLConnection.setDoInput(true);
						httpURLConnection.setDoOutput(true);
						httpURLConnection.setUseCaches(false);
						httpURLConnection.setRequestMethod("POST");
						httpURLConnection.setRequestProperty("Connection",
								"Keep-Alive");
						httpURLConnection
								.setRequestProperty("Charset", "UTF-8");
						httpURLConnection.setRequestProperty("Content-Type",
								"multipart/form-data;boundary=" + boundary);
						dos = new DataOutputStream(
								httpURLConnection.getOutputStream());
						dos.writeBytes(twoHyphens + boundary + end);
						// f = new File(FileUtils.getSDPATH()
						// + ApplicationData.TOWER + "/" + o);
						// filename = f.getName();
						dos.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\""
								+ o + "\"" + end);
						dos.writeBytes(end);
						// 图片路径
						fis = new FileInputStream(FileUtils.getSDPATH()
								+ ApplicationData.TOWER + "/" + o);
						byte[] buffer = new byte[8192]; // 8k
						int count = 0;
						while ((count = fis.read(buffer)) != -1) {
							dos.write(buffer, 0, count);
						}
						fis.close();
						dos.writeBytes(end);
						dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
						dos.flush();
						is = httpURLConnection.getInputStream();
						InputStreamReader isr = new InputStreamReader(is,
								"utf-8");
						BufferedReader br = new BufferedReader(isr);
						fileResult = br.readLine();
						if (fileResult != null && fileResult.equals("true")) {
							sucessdrr.add(o);
						} else {
							faildrr.add(o);
						}
						is.close();
						dos.close();
					}

					handler.obtainMessage(7).sendToTarget();
				} catch (Exception e) {
					e.printStackTrace();
					handler.obtainMessage(-1).sendToTarget();
				}
			}

		}.start();
	}
	/**
	 * 线程加载数据后返回的消息
	 */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			relative_layout.setVisibility(View.GONE);
			loading.clearAnimation();
			switch (msg.what) {
			case 1:
				zclist = new ArrayList<AppPhyInfoBean>();
				ysclist = new ArrayList<AppPhyInfoBean>();
				if (list != null && list.size() > 0) {
					for (AppPhyInfoBean o : list) {
						if (o.getStatus().equals("4")) {
							ysclist.add(o);
						} else {
							zclist.add(o);
						}
					}
					if (zclist != null && zclist.size() > 0) {
						zcAdapter = new MenuZcAdapter(getApplicationContext(),
								zclist);
						listview_zc.setAdapter(zcAdapter);
						listview_zc.setVisibility(View.VISIBLE);
					} else {
						zcAdapter = new MenuZcAdapter(getApplicationContext(),
								zclist);
						listview_zc.setAdapter(zcAdapter);
						listview_zc.setVisibility(View.GONE);

						// toastMessage("没有暂存数据！");
					}
					if (ysclist != null && ysclist.size() > 0) {
						yscAdapter = new MenuYscAdapter(
								getApplicationContext(), ysclist);
						listview_ysc.setAdapter(yscAdapter);

						// listview_ysc.setVisibility(View.VISIBLE);

					} else {
						yscAdapter = new MenuYscAdapter(
								getApplicationContext(), ysclist);
						listview_ysc.setAdapter(yscAdapter);
						listview_ysc.setVisibility(View.GONE);
					}
				} else {
					// toastMessage("本地没有需要清查的资产数据！");
					zcAdapter = new MenuZcAdapter(getApplicationContext(),
							zclist);
					listview_zc.setAdapter(zcAdapter);
					yscAdapter = new MenuYscAdapter(getApplicationContext(),
							ysclist);
					listview_ysc.setAdapter(yscAdapter);
				}

				break;
			case 0:
				// toastMessage("没有相关数据！");
				finish();
				break;
			case -1:
				toastMessage("请求失败！");
				break;
			case 3:// 重新盘点
				Bundle bundle = new Bundle();
				bean.setStatus("0");
				bundle.putString("lat", lat);
				bundle.putString("lon", lon);
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(), PhysicalActivity.class,
						bundle);
				// loadList();
				// selZc();
				break;
			case 5:// 上传数据
				relative_layout.setVisibility(View.VISIBLE);
				loading.startAnimation(animation);
				ApplicationData.getExecutorService().submit(upRun);
				break;
			case 6:
				upPicture();
				break;
			case 7:// 更改问题图片状态
				if (sucessdrr != null && sucessdrr.size() > 0) {
					handler.obtainMessage(9).sendToTarget();
				} else {
					db = mOpenHelper.getReadableDatabase();
					try {
						if (faildrr != null && faildrr.size() > 0) {
							for (String o : faildrr) {
								TowerSQliteDbBean.updatePicbean(db, o, 2);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) {
							db.close();
						}

					}
					loadList();
					selZc();
				}
				break;
			case 8:
				loadList();
				selZc();
				break;
			case 9:// 和后台校验图片是否上传完成
				StringBuffer str = new StringBuffer();
				if (sucessdrr != null && sucessdrr.size() > 0) {
					for (String o : sucessdrr) {
						str.append(o);
						str.append(",");
					}
				}
				if (str != null && str.length() > 0) {
					picName = str.toString();
				}
				relative_layout.setVisibility(View.VISIBLE);
				loading.startAnimation(animation);
				ApplicationData.getExecutorService().submit(checkrun);
				break;
			case 11:
				loadList();
				selYsc();
				break;
			case 12:// 验证存在失败
				db = mOpenHelper.getReadableDatabase();
				if (sucessdrr != null && sucessdrr.size() > 0) {
					for (String o : sucessdrr) {
						TowerSQliteDbBean.updatePicbean(db, o, 1);
					}
				}
				if (strlist != null && strlist.size() > 0) {
					for (String o : strlist) {
						TowerSQliteDbBean.updatePicStatus(db, o);
					}
				}
				if (db != null) {
					db.close();
				}
				loadList();
				selZc();
				break;
			case 13:// 验证通过
				ApplicationData.getExecutorService().submit(upstatsrun);
				db = mOpenHelper.getReadableDatabase();
				TowerSQliteDbBean.updatePhysicalStatus(db, 4,
						bean.getPhysicCode());
				if (sucessdrr != null && sucessdrr.size() > 0) {
					for (String o : sucessdrr) {
						TowerSQliteDbBean.updatePicbean(db, o, 1);
					}
				}
				if (db != null) {
					db.close();
				}
				loadList();
				selYsc();
				break;
			case -13:

				break;
			}
		}
	};

	/** 暂存 */
	private void selZc() {
		listview_zc.setVisibility(View.VISIBLE);
		listview_ysc.setVisibility(View.GONE);
		tab_zc.setBackgroundResource(R.drawable.btn_tab_left_blue_s);
		tab_ysc.setBackgroundResource(R.drawable.btn_tab_right_blue);
		tab_zc.setTextColor(android.graphics.Color.rgb(0x48, 0x6b, 0xa2));
		tab_ysc.setTextColor(android.graphics.Color.rgb(0xff, 0xff, 0xff));
	}

	/**
	 * 
	 * 此方法描述的是：上传图片
	 * 
	 * @Title: upPicture
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-8-28 上午10:25:50
	 */
	protected void upPicture() {
		try {
			db = mOpenHelper.getReadableDatabase();
			// 问题图片
			picList = new ArrayList<AppProblemPicBean>();
			String sql = "select * from t_problem_pic where physicCode='"
					+ bean.getPhysicCode() + "' and uploadStatus  in (0,2)";
			picList = TowerSQliteDbBean.queryProblemPicData(mOpenHelper, sql);
			db = mOpenHelper.getReadableDatabase();
			if (picList != null && picList.size() > 0) {
				TowerSQliteDbBean.updatePhysicalStatus(db, 3,
						bean.getPhysicCode());
				relative_layout.setVisibility(View.VISIBLE);
				loading.startAnimation(animation);
				drr = new ArrayList<String>();
				for (AppProblemPicBean o : picList) {
					drr.add(o.getPicAddr());
				}
				uploadData();
			} else {
				TowerSQliteDbBean.updatePhysicalStatus(db, 4,
						bean.getPhysicCode());
				loadList();
				selYsc();
				ApplicationData.getExecutorService().submit(upstatsrun);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}

	}

	/** 已上传 */
	private void selYsc() {
		listview_zc.setVisibility(View.GONE);
		listview_ysc.setVisibility(View.VISIBLE);
		tab_zc.setBackgroundResource(R.drawable.btn_tab_left_blue);
		tab_ysc.setBackgroundResource(R.drawable.btn_tab_right_blue_s);
		tab_zc.setTextColor(android.graphics.Color.rgb(0xff, 0xff, 0xff));
		tab_ysc.setTextColor(android.graphics.Color.rgb(0x48, 0x6b, 0xa2));
	}

	private Handler versionHandler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				if (versionBean != null) {
					if (getVersionCode() < versionBean.getVersioncode()) {
						showDialog();
					}
				}
				break;
			}
		}
	};

	private void showDialog() {
		dialog = new Dialog(MainActivity.this, R.style.MyDialog);
		dialog.setContentView(R.layout.dialog_update);
		final TextView version = (TextView) dialog.findViewById(R.id.version);
		version.setText("发现新版本" + versionBean.getVersionname());
		final TextView msg = (TextView) dialog.findViewById(R.id.msg);
		msg.setText(versionBean.getVersionmemo());
		final TextView cancle = (TextView) dialog.findViewById(R.id.cancle);
		final TextView update = (TextView) dialog.findViewById(R.id.update);
		if (1 == versionBean.getForcedupgrade()) {
			cancle.setText("退出应用");
		}
		cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				if (1 == versionBean.getForcedupgrade()) {
					exit();
				}
			}
		});
		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent startintent = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse(Function.URL
								+ "/appVersion.xp?action=downloadNewVersionApk&userid="
								+ ApplicationData.user.getId()));
				startintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(startintent);
			}
		});
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		dialog.show();
	}

	private int getVersionCode() {
		int versionCode = 1;
		PackageManager pm = this.getPackageManager();
		PackageInfo pi;
		try {
			pi = pm.getPackageInfo(this.getPackageName(), 0);
			versionCode = pi.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 
	 * 此类描述的是：退出
	 * 
	 * @author: 罗然
	 * @version: 2015-7-23 下午2:40:23
	 * @ClassName: SelectPicPopupWindow
	 * @项目： towerzj_app
	 * @包：com.woyi.towerzj_app.activity.menu
	 */
	class SelectPicPopupWindow extends PopupWindow {

		private Button exit, cancle;
		private View mMenuView;

		public SelectPicPopupWindow(Activity context,
				OnClickListener itemsOnClick) {
			super(context);
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mMenuView = inflater.inflate(R.layout.dialog_exit, null);
			exit = (Button) mMenuView.findViewById(R.id.exit);
			cancle = (Button) mMenuView.findViewById(R.id.cancle);
			exit.setOnClickListener(btnLis);
			cancle.setOnClickListener(btnLis);
			this.setContentView(mMenuView);
			this.setWidth(LayoutParams.FILL_PARENT);
			this.setHeight(LayoutParams.WRAP_CONTENT);
			this.setFocusable(true);
			this.setAnimationStyle(R.style.AnimBottom);
			ColorDrawable dw = new ColorDrawable(0xb0000000);
			this.setBackgroundDrawable(dw);
			mMenuView.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int height = mMenuView.findViewById(R.id.pop_layout)
							.getTop();
					int y = (int) event.getY();
					if (event.getAction() == MotionEvent.ACTION_UP) {
						if (y < height) {
							dismiss();
						}
					}
					return true;
				}
			});
		}
	}

	/**
	 * 
	 * 此类描述的是：上传
	 * 
	 * @author: 罗然
	 * @version: 2015-7-27 下午4:45:45
	 * @ClassName: SelectPicPopupWindow
	 * @项目： towerzj_app
	 * @包：com.woyi.towerzj_app.activity.menu
	 */
	class UpPicPopupWindow extends PopupWindow {

		private Button up, query;
		private View mMenuView;

		public UpPicPopupWindow(Activity context, OnClickListener itemsOnClick) {
			super(context);
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mMenuView = inflater.inflate(R.layout.dialog_up, null);
			up = (Button) mMenuView.findViewById(R.id.up);
			query = (Button) mMenuView.findViewById(R.id.query);
			delete = (Button) mMenuView.findViewById(R.id.delete);
			up.setOnClickListener(btnLis);
			query.setOnClickListener(btnLis);
			delete.setOnClickListener(btnLis);
			this.setContentView(mMenuView);
			this.setWidth(LayoutParams.FILL_PARENT);
			this.setHeight(LayoutParams.WRAP_CONTENT);
			this.setFocusable(true);
			this.setAnimationStyle(R.style.AnimBottom);
			ColorDrawable dw = new ColorDrawable(0xb0000000);
			this.setBackgroundDrawable(dw);
			mMenuView.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int height = mMenuView.findViewById(R.id.pop_layout)
							.getTop();
					int y = (int) event.getY();
					if (event.getAction() == MotionEvent.ACTION_UP) {
						if (y < height) {
							dismiss();
						}
					}
					return true;
				}
			});
		}
	}

	/**
	 * 
	 * 此类描述的是：重新盘点
	 * 
	 * @author: 罗然
	 * @version: 2015-8-22 下午3:47:31
	 * @ClassName: HandlePopupWindow
	 * @项目： towerzj_app
	 * @包：com.woyi.towerzj_app.activity.menu
	 */
	class HandlePopupWindow extends PopupWindow {

		private Button handler, handler_query;
		private View mMenuView;

		public HandlePopupWindow(Activity context, OnClickListener itemsOnClick) {
			super(context);
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mMenuView = inflater.inflate(R.layout.dialog_new_handler, null);
			handler_tv = (TextView) mMenuView.findViewById(R.id.handler_tv);
			handler_tv.setVisibility(View.GONE);
			handler = (Button) mMenuView.findViewById(R.id.handler);
			handler.setText("重新盘点");
			handler_query = (Button) mMenuView.findViewById(R.id.handler_query);
			handler.setOnClickListener(btnLis);
			handler_query.setOnClickListener(btnLis);
			this.setContentView(mMenuView);
			this.setWidth(LayoutParams.FILL_PARENT);
			this.setHeight(LayoutParams.WRAP_CONTENT);
			this.setFocusable(true);
			this.setAnimationStyle(R.style.AnimBottom);
			ColorDrawable dw = new ColorDrawable(0xb0000000);
			this.setBackgroundDrawable(dw);
			mMenuView.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int height = mMenuView.findViewById(R.id.pop_layout)
							.getTop();
					int y = (int) event.getY();
					if (event.getAction() == MotionEvent.ACTION_UP) {
						if (y < height) {
							dismiss();
						}
					}
					return true;
				}
			});
		}
	}

	/**
	 * 
	 * 此类描述的是：删除物理站信息
	 * 
	 * @author: 罗然
	 * @version: 2015-8-22 下午3:47:46
	 * @ClassName: DelPopupWindow
	 * @项目： towerzj_app
	 * @包：com.woyi.towerzj_app.activity.menu
	 */
	class DelPopupWindow extends PopupWindow {

		private Button del, query2;
		private View mMenuView;

		public DelPopupWindow(Activity context, OnClickListener itemsOnClick) {
			super(context);
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mMenuView = inflater.inflate(R.layout.dialog_del, null);
			del_tv = (TextView) mMenuView.findViewById(R.id.del_tv);
			del_tv.setVisibility(View.GONE);
			del = (Button) mMenuView.findViewById(R.id.del);
			del.setText("删除");
			query2 = (Button) mMenuView.findViewById(R.id.query2);
			del.setOnClickListener(btnLis);
			query2.setOnClickListener(btnLis);
			this.setContentView(mMenuView);
			this.setWidth(LayoutParams.FILL_PARENT);
			this.setHeight(LayoutParams.WRAP_CONTENT);
			this.setFocusable(true);
			this.setAnimationStyle(R.style.AnimBottom);
			ColorDrawable dw = new ColorDrawable(0xb0000000);
			this.setBackgroundDrawable(dw);
			mMenuView.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int height = mMenuView.findViewById(R.id.pop_layout)
							.getTop();
					int y = (int) event.getY();
					if (event.getAction() == MotionEvent.ACTION_UP) {
						if (y < height) {
							dismiss();
						}
					}
					return true;
				}
			});
		}
	}

	/**
	 * 退出程序
	 */
	private void exit() {
		SysExitUtil.exit();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadList();
		selZc();
		image_sy.setImageDrawable(syPress);
		image_mmxg.setImageDrawable(mmxgNormal);
		image_grxx.setImageDrawable(grxxNormal);
		text_sy.setTextColor(android.graphics.Color.rgb(0xff, 0x00, 0x00));
		text_mmxg.setTextColor(android.graphics.Color.rgb(0x82, 0x82, 0x82));
		text_grxx.setTextColor(android.graphics.Color.rgb(0x82, 0x82, 0x82));
	}

	/**
	 * 监听返回按键
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			exitWindow = new SelectPicPopupWindow(MainActivity.this, null);
			exitWindow.showAtLocation(
					MainActivity.this.findViewById(R.id.main), Gravity.BOTTOM
							| Gravity.CENTER_HORIZONTAL, 0, 0);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/***************************************** 检查图片是否上传完成 ******************************************************/
	private Runnable checkrun = new Runnable() {

		@Override
		public void run() {
			try {
				strlist = new ArrayList<String>();
				String result = Function.checkPic(picName);
				result = result.substring(1, result.length() - 1);
				if (result != null && result.length() > 0) {
					String[] str = result.split(",");
					if (str != null && str.length > 0) {
						String status = str[0];
						if (status.equals("1")) {// 验证成功
							handler.obtainMessage(13).sendToTarget();
						} else {
							for (int i = 1; i < str.length; i++) {
								strlist.add(str[i]);
							}
							handler.obtainMessage(12).sendToTarget();
						}
					} else {
						handler.obtainMessage(-13).sendToTarget();
					}

				} else {
					handler.obtainMessage(-1).sendToTarget();
				}
			} catch (Exception e) {
				handler.obtainMessage(-1).sendToTarget();
			}
		}
	};

}
