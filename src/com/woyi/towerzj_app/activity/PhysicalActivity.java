package com.woyi.towerzj_app.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.woyi.towerzj_app.activity.tawei.NewTaweiDetailActivity;
import com.woyi.towerzj_app.bean.Option;
import com.woyi.towerzj_app.bean.SpinnerUtilBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.service.GpsService;
import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;
import com.woyi.towerzj_app.util.UtilTool;

/**
 * 
 * 此类描述的是：物理站信息
 * 
 * @author: 罗然
 * @version: 2015-8-17 上午10:40:46
 * @ClassName: PhysicalActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity
 */
public class PhysicalActivity extends ForwardActivity {
	private TextView back, add, title, next, save;
	private TextView code, name, address, latlon, currentAddress;
	private TextView status;
	private Spinner type;
	private String lat = "";
	private String lon = "";
	private String gpslat = "";
	private String gpslon = "";
	private String bdlat = "";
	private String bdlon = "";
	private AppPhyInfoBean bean;
	private Button start_map;
	private int flag = 0;
	private String physicalType;
	private DatabaseHelper mOpenHelper;
	private NetBroadcastReceiver broadcastReceiver;
	private MyReceiver receiver = null;
	public static final int SUCCESS = 1;
	private boolean nextflag=false;
	
	private List<AppPhyInfoBean> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_physicaldata);
		SysExitUtil.activityList.add(this);
		mOpenHelper = new DatabaseHelper(this);
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");

		loadList();
		if (!UtilTool
				.isGpsEnabled((LocationManager) getSystemService(Context.LOCATION_SERVICE))) {
			Toast.makeText(this, "GPS当前已禁用，请在您的系统设置屏幕启动。", Toast.LENGTH_LONG)
			.show();
			Intent callGPSSettingIntent = new Intent(
					android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(callGPSSettingIntent);
			return;
		}
		// 启动服务
		startService(new Intent(this, GpsService.class));
		// 注册广播
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.woyi.towerzj_app.service.GpsService");
		registerReceiver(receiver, filter);
	}
	
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
		String sql = "select * from t_physic_info where physicCode='"+bean.getPhysicCode()+"'";
		try {
			SQLiteDatabase db = mOpenHelper.getReadableDatabase();
			list = TowerSQliteDbBean.queryPhysicalData(db, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}
	}

	// 获取广播数据
	private class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			gpslon = bundle.getString("lon");
			gpslat = bundle.getString("lat");
			if (gpslon != null && !"".equals(gpslon) && gpslat != null
					&& !"".equals(gpslat)) {
				currentAddress
				.setText("经度:" + gpslon + "\r\n" + "纬度:" + gpslat);
			} else {
				currentAddress.setText("");
			}
		}
	}

	// 注册网络监测广播
	private class NetBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isAvailable()) {
				if (currentAddress.getText().toString().trim() != null
						&& !currentAddress.getText().toString().trim()
						.equals("")) {
					// 网络可用
					start_map.setVisibility(View.VISIBLE);
				}
			} else {
				// 网络不可用
				start_map.setVisibility(View.GONE);
			}
		}

	}

	/**
	 * 初始化界面
	 */
	public void init() {
		
		title.setText("站点信息");
		add.setVisibility(View.INVISIBLE);
		fillSpinnerAdapter(SpinnerUtilBean.getPhysucalType(), type, "请选择站点类型");
		back.setOnClickListener(new MyListener());
		//add.setVisibility(View.INVISIBLE);
		save.setOnClickListener(new MyListener());
		next.setOnClickListener(new MyListener());
		start_map.setOnClickListener(new MyListener());
		code.setText(bean.getPhysicAlias());
		selectedSpinner(SpinnerUtilBean.getPhysucalType(),
				bean.getPhysicType(), type);
		broadcastReceiver = new NetBroadcastReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		registerReceiver(broadcastReceiver, filter);
		if (bean.getPhysicName() != null && !bean.getPhysicName().equals("")
				&& !bean.getPhysicName().equals("null")) {
			name.setText(bean.getPhysicName());
		} else {
			name.setText("无");
		}

		if (bean.getPhysicAddr() != null && !bean.getPhysicAddr().equals("")
				&& !bean.getPhysicAddr().equals("null")) {
			address.setText(bean.getPhysicAddr());
		} else {
			address.setText("无");
		}
		if (bean.getLongitude() != null && !bean.getLongitude().equals("")
				&& !bean.getLongitude().equals("")) {
			latlon.setText("经度:" + bean.getLongitude() + "\r\n" + "纬度:"
					+ bean.getLatitude());
		}

		if (bean.getGpslatitude() != null && !bean.getGpslatitude().equals("")
				&& !bean.getGpslatitude().equals("null")) {
			currentAddress.setText("");
			nextflag=true;
		}

		if (bean.getIsPass() != null && !bean.getIsPass().equals("")
				&& !bean.getIsPass().equals("null")) {
			if (bean.getIsPass().equals("1")) {
				flag = 1;
			}
		}
		if(nextflag){
			next.setVisibility(View.VISIBLE);
		}else{
			next.setVisibility(View.INVISIBLE);
		}
		currentAddress.addTextChangedListener(new mTextWatcher());
	}

	/**
	 * 
	 * 此类描述的是：当前位置获取经纬度值改变
	 * 
	 * @author: 罗然
	 * @version: 2015-8-13 下午2:10:33
	 * @ClassName: mTextWatcher
	 * @项目： towerzj_app
	 * @包：com.woyi.towerzj_app.activity
	 */
	class mTextWatcher implements TextWatcher {

		// 文字变化前
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
			String str = currentAddress.getText().toString();
			if (str != null && !str.equals("")) {
				start_map.setVisibility(View.VISIBLE);
				status.setVisibility(View.GONE);
			} else {
				start_map.setVisibility(View.GONE);
				status.setVisibility(View.VISIBLE);
			}
		}
	}

	/**
	 * 按键监听
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.back:// 返回
				finish();
				break;
			case R.id.next:// 跳过
				Bundle bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flag + "");
				forwardIntent(PhysicalActivity.this,
						NewTaweiDetailActivity.class, bundle);
				break;
			case R.id.start_map:// 地图展示

				if (gpslon != null && !gpslon.equals("")
				&& !gpslon.equals("null")) {
					Bundle bundle1 = new Bundle();
					bundle1.putString("lon", gpslon);
					bundle1.putString("lat", gpslat);
					Intent it = new Intent(getApplicationContext(),
							MapActivity.class);
					it.putExtras(bundle1);
					PhysicalActivity.this.startActivityForResult(it, SUCCESS);
				}
				break;
			case R.id.save:// 保存信息
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flag + "");
//				if (gpslon != null && !gpslon.equals("") &&
//						!gpslon.equals("null")) {
					saveData();
					forwardIntent(getApplicationContext(),NewTaweiDetailActivity.class, bundle);
//				} else {
//					toastMessage("无法获取经纬度信息,请到开阔位置重试");
//				}
				break;
			}

		}

	}

	/**
	 * 保存物理站信息
	 */
	public void saveData() {
		physicalType = ((Option) type.getSelectedItem()).getValue();
		SQLiteDatabase database = mOpenHelper.getWritableDatabase();
		AppPhyInfoBean physicalBean = new AppPhyInfoBean();
		physicalBean.setGpslatitude(gpslat);
		physicalBean.setGpslongitude(gpslon);
		physicalBean.setBdlatitude(bdlat);
		physicalBean.setBdlongitude(bdlon);
		physicalBean
		.setCheckUserId(String.valueOf(ApplicationData.user.getId()));
		physicalBean.setPhysicType(physicalType);
		physicalBean.setPhysicCode(bean.getPhysicCode());
		TowerSQliteDbBean.updatePhysicalData(database, physicalBean);
	}

	/********************************************* 百度经纬度和gprs转换 ***************************************************/

	/**
	 * 填充spinner的adapter
	 * 
	 * @param list
	 * @return
	 */
	private void fillSpinnerAdapter(List<Option> list, Spinner spinner,
			CharSequence prompt) {
		ArrayAdapter<Option> adapter = new ArrayAdapter<Option>(
				getApplicationContext(), R.xml.custom_spinner_item, list);
		adapter.setDropDownViewResource(R.xml.spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setPrompt(prompt);
	}

	/**
	 * 默认选中spinner的值
	 * 
	 * @param option
	 * @param str
	 * @param spinner
	 */
	private void selectedSpinner(List<Option> option, String str,
			Spinner spinner) {
		for (Option o : option) {
			if (o.getValue().equals(str)) {
				spinner.setSelection(fillAdapter(option).getPosition(o), true);
				break;
			}
		}
	}

	/**
	 * 填充spinner的adapter
	 * 
	 * @param options
	 * @return
	 */
	private ArrayAdapter<Option> fillAdapter(List<Option> options) {
		ArrayAdapter<Option> adapter = new ArrayAdapter<Option>(
				getApplicationContext(), R.xml.custom_spinner_item, options);
		adapter.setDropDownViewResource(R.xml.spinner_dropdown_item);
		return adapter;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null && resultCode == SUCCESS) {
			// 注销服务
			// if(receiver!=null){
			// unregisterReceiver(receiver);
			// // 结束服务，如果想让服务一直运行就注销此句
			// stopService(new Intent(this, GpsService.class));
			// }
			bdlon = data.getStringExtra("bdlon");
			bdlat = data.getStringExtra("bdlat");
			currentAddress.setText("经度:" + bdlon + "\r\n" + "纬度:" + bdlat);
		}
	}

	@Override
	protected void onDestroy() {
		// 销毁广播
		unregisterReceiver(broadcastReceiver);
		// 注销服务
		// unregisterReceiver(receiver);
		// 结束服务，如果想让服务一直运行就注销此句
		stopService(new Intent(this, GpsService.class));
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadList();
		if (!UtilTool
				.isGpsEnabled((LocationManager) getSystemService(Context.LOCATION_SERVICE))) {
			Toast.makeText(this, "GPS当前已禁用，请在您的系统设置屏幕启动。", Toast.LENGTH_LONG)
			.show();
			Intent callGPSSettingIntent = new Intent(
					android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(callGPSSettingIntent);
			return;
		}
		// 启动服务
		startService(new Intent(this, GpsService.class));
		// 注册广播
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.woyi.towerzj_app.service.GpsService");
		registerReceiver(receiver, filter);
	}
	
	/**
	 * 线程加载数据后返回的消息
	 */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				if(list!=null && list.size()>0){
					bean=list.get(0);
				}
				init();
				break;
			case -1:
				toastMessage("请求失败！");
				break;
			}
		}
	};
	
}
