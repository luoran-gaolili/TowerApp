package com.woyi.towerzj_app.activity.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.PhysicalActivity;
import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.adapter.AppPhyInfoAdapter;
import com.woyi.towerzj_app.bean.AppOtherDepartment;
import com.woyi.towerzj_app.bean.ResultList;
import com.woyi.towerzj_app.bean.donghuan.DongHuanBean;
import com.woyi.towerzj_app.bean.form.ZcqcQueryForm;
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
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoList;
import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Function;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;

/**
 * 
 * 此类描述的是：查询后的列表
 * 
 * @author: 罗然
 * @version: 2015-7-19 上午9:22:25
 * @ClassName: QueryListActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity.menu
 */
public class QueryListActivity extends ForwardActivity {

	private Button back;
	private TextView title, cz;
	private ListView listview;
	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	private AppPhyInfoAdapter adapter;
	private ZcqcQueryForm formBean;
	private List<AppPhyInfoBean> list;
	private AppPhyInfoList data;
	private AppPhyInfoBean bean;

	private ResultList databean;

	private List<AppProblemBean> appProblemList = new ArrayList<AppProblemBean>();
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
	private List<AppOtherDepartment> others=new ArrayList<AppOtherDepartment>();

	private SQLiteDatabase db;
	private DatabaseHelper mOpenHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		formBean = (ZcqcQueryForm) this.getIntent().getExtras()
				.getSerializable("bean");
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_zcqc_list);
		mOpenHelper = new DatabaseHelper(this);
		SysExitUtil.activityList.add(this);
		title.setText("资产清查");
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		relative_layout.setVisibility(View.VISIBLE);
		loading.startAnimation(animation);
		ApplicationData.getExecutorService().submit(run);
		cz.setVisibility(View.GONE);
		back.setOnClickListener(btnLis);
		listview.setOnItemClickListener(itemLis);
	}

	/**
	 * 按钮监控事件
	 */
	private OnClickListener btnLis = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			finish();
		}
	};

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
			relative_layout.setVisibility(View.VISIBLE);
			loading.startAnimation(animation);
			ApplicationData.getExecutorService().submit(listrun);
		}
	};

	/**
	 * 获取物理站信息
	 */
	private Runnable run = new Runnable() {

		@Override
		public void run() {
			try {
				String result = Function.getPhyList(formBean);
				ObjectMapper om = new ObjectMapper();
				data = om.readValue(result, TypeFactory
						.fromTypeReference(new TypeReference<AppPhyInfoList>() {
						}));
				if (data.getExcuteStatue() != -1) {
					if (data.getExcuteStatue() == 1) {
						handler.obtainMessage(1).sendToTarget();
					} else if (data.getExcuteStatue() == 0) {
						handler.obtainMessage(0).sendToTarget();
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
	 * 获取服务器所有列表数据
	 */
	private Runnable listrun = new Runnable() {

		@Override
		public void run() {
			try {
				String result = Function.getResultList(bean.getPhysicCode());
				ObjectMapper om = new ObjectMapper();
				databean = om.readValue(result, TypeFactory
						.fromTypeReference(new TypeReference<ResultList>() {
						}));
				if (data.getExcuteStatue() != -1) {
					if (data.getExcuteStatue() == 1) {
						handler.obtainMessage(2).sendToTarget();
					} else if (data.getExcuteStatue() == 0) {
						handler.obtainMessage(0).sendToTarget();
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
				list = data.getAppPhyInfoList();
				if (null != list && list.size() > 0) {
					adapter = new AppPhyInfoAdapter(getApplicationContext(),
							list);
					listview.setAdapter(adapter);
				}
				break;
			case 0:
				// toastMessage("没有相关数据！");
				break;
			case -1:
				toastMessage("请求失败！");
				break;
			case 2:
				// 问题
				appProblemList = databean.getAllProblemList();
				// 塔桅
				appMastList = databean.getAppMastList();
				appMastComList = databean.getAppMastComList();
				// 机房
				appRoomList = databean.getAppRoomList();
				appRoomComList = databean.getAppRoomComList();
				// 空调
				appAirList = databean.getAppAirList();
				appAirComList = databean.getAppAirComList();
				// 蓄电池
				appBatteryList = databean.getAppBatteryList();
				appBatteryComList = databean.getAppBatteryComList();
				// 开关
				appSwitchList = databean.getAppSwitchList();
				appSwitchComList = databean.getAppSwitchComList();
				// 配电箱
				appPowerList = databean.getAppPowerList();
				appPowerComList = databean.getAppPowerComList();

				//动环
				guards =databean.getGuards();
				//天线
				appAntennaBeans =databean.getAppAntennaBeans();
				//其他设备
				others=bean.getOthers();
				// 保存数据
				saveData();
				break;
			case 3:
				// toastMessage("此物理站信息更新完成，请转到首页操作");
				
				AppPhyInfoBean beans=new AppPhyInfoBean();
				beans.setPhysicAddr(bean.getPhysicAddr());
				beans.setPhysicAlias(bean.getPhysicAlias());
				beans.setPhysicCode(bean.getPhysicCode());
				beans.setPhysicName(bean.getPhysicName());
				beans.setPhysicType(bean.getPhysicType());
				beans.setLatitude(bean.getLatitude());
				beans.setLongitude(bean.getLongitude());
				beans.setStatus("0");
				beans.setIsPass(bean.getIsPass());
				Bundle bundle=new Bundle();
				bundle.putSerializable("bean", beans);
				forwardIntent(getApplicationContext(), PhysicalActivity.class,bundle);
				break;
			}
		}
	};

	/**
	 * 
	 * 此方法描述的是：将数据保存到本地
	 * 
	 * @Title: saveData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-21 下午2:01:52
	 */
	private void saveData() {
		db = mOpenHelper.getWritableDatabase();
		String sql = "select * from t_physic_info where physicCode='"
				+ bean.getPhysicCode() + "'";
		if (TowerSQliteDbBean.ifPhysicalData(db, sql)) {
			toastMessage("此物理站信息已更新过！");
			return;
		} else {// 保存方法
			try {
				// 开始事务
				db.beginTransaction();
				// 插入物理站
				UUID uuid = UUID.randomUUID();
				bean.setId(uuid.toString());
				bean.setCreateUserId(ApplicationData.user.getId() + "");
				TowerSQliteDbBean.insertPhysical(db, bean);
				sql = "select * from t_problem";
				if (TowerSQliteDbBean.ifProblemData(db, sql)) {
//					TowerSQliteDbBean.deleData(db, "", "", "08");
//					// 问题
//					if (appProblemList != null && appProblemList.size() > 0) {
//						for (AppProblemBean o : appProblemList) {
//							TowerSQliteDbBean.insertProblem(db, o);
//						}
//					}
				} else {
					// 问题
					if (appProblemList != null && appProblemList.size() > 0) {
						for (AppProblemBean o : appProblemList) {
							TowerSQliteDbBean.insertProblem(db, o);
						}
					}
				}
				// 塔桅
				if (appMastList != null && appMastList.size() > 0) {
					for (AppMastBean o : appMastList) {
						TowerSQliteDbBean.insertMast(db, o, 1);
					}
				}
				// 机房
				if (appRoomList != null && appRoomList.size() > 0) {
					for (AppRoomBean o : appRoomList) {
						o.setJgCheck("1");
						TowerSQliteDbBean.insertRoom(db, o, 1);
					}
				}
				// 空调
				if (appAirList != null && appAirList.size() > 0) {
					for (AppAirBean o : appAirList) {
						TowerSQliteDbBean.insertAir(db, o, 1);
					}
				}
				// 蓄电池
				if (appBatteryList != null && appBatteryList.size() > 0) {
					for (AppBatteryBean o : appBatteryList) {
						TowerSQliteDbBean.insertBatterry(db, o, 1);
					}
				}
				// 开关
				if (appSwitchList != null && appSwitchList.size() > 0) {
					for (AppSwitchBean o : appSwitchList) {
						TowerSQliteDbBean.insertSwitch(db, o, 1);
					}
				}
				// 配电箱
				if (appPowerList != null && appPowerList.size() > 0) {
					for (AppPowerBean o : appPowerList) {
						TowerSQliteDbBean.insertPower(db, o, 1);
					}
				}
				// 塔桅运营商
				if (appMastComList != null && appMastComList.size() > 0) {
					for (AppMastComBean o : appMastComList) {
						TowerSQliteDbBean.insertMastCom(db, o);
					}
				}
				// 机房运营商
				if (appRoomComList != null && appRoomComList.size() > 0) {
					for (AppRoomComBean o : appRoomComList) {
						TowerSQliteDbBean.insertRoomCom(db, o);
					}
				}
				// 空调运营商
				if (appAirComList != null && appAirComList.size() > 0) {
					for (AppAirComBean o : appAirComList) {
						TowerSQliteDbBean.insertAirCom(db, o);
					}
				}
				// 蓄电池运营商
				if (appBatteryComList != null && appBatteryComList.size() > 0) {
					for (AppBatteryComBean o : appBatteryComList) {
						TowerSQliteDbBean.insertBatteryCom(db, o);
					}
				}
				// 开关运营商
				if (appSwitchComList != null && appSwitchList.size() > 0) {
					for (AppSwitchComBean o : appSwitchComList) {
						TowerSQliteDbBean.insertSwitchCom(db, o);
					}
				}
				// 配电箱运营商
				if (appPowerComList != null && appPowerComList.size() > 0) {
					for (AppPowerComBean o : appPowerComList) {
						TowerSQliteDbBean.insertPowerCom(db, o);
					}
				}
				//动环
				if(guards!=null && guards.size()>0){
					for (DongHuanBean o : guards) {
						o.setPhysicCode(bean.getPhysicCode());
						TowerSQliteDbBean.insertDh(db, o);
					}
				}
				//天线
				if(appAntennaBeans!=null && appAntennaBeans.size()>0){
					for (AppAntennaBean o : appAntennaBeans) {
						TowerSQliteDbBean.insertTx(db, o);
					}
				}
				sql = "select * from t_other_department where physicCode='"+bean.getPhysicCode()+"'";
				if (TowerSQliteDbBean.ifProblemData(db, sql)) {
//					TowerSQliteDbBean.deleData(db, "", "", "11");
//					//其他设备
//					if(others!=null && others.size()>0){
//						for (AppOtherDepartment o : others) {
//							UUID uuid1  =  UUID.randomUUID(); 
//							String s = uuid1.toString();//
//							o.setId(s);
//							o.setPhysicCode(bean.getPhysicCode());
//							TowerSQliteDbBean.insertOtherDepartment(db, o);
//						}
//					}
				}else{
					//其他设备
					if(others!=null && others.size()>0){
						for (AppOtherDepartment o : others) {
							o.setPhysicCode(bean.getPhysicCode());
							TowerSQliteDbBean.insertOtherDepartment(db, o);
						}
					}
				}
				db.setTransactionSuccessful();
				handler.obtainMessage(3).sendToTarget();
			} catch (Exception e) {
				handler.obtainMessage(-1).sendToTarget();
			} finally {
				db.endTransaction();
				db.close();
			}

		}
	}

}
