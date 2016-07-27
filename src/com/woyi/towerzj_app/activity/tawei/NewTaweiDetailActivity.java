package com.woyi.towerzj_app.activity.tawei;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.QuestionDetailActivity;
import com.woyi.towerzj_app.activity.QuestionListActivity;
import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.activity.RoomPhotoActivity;
import com.woyi.towerzj_app.activity.jifang.NewJifangDetailActivity;
import com.woyi.towerzj_app.bean.Option;
import com.woyi.towerzj_app.bean.SpinnerUtilBean;
import com.woyi.towerzj_app.bean.quedtion.AppProblemBean;
import com.woyi.towerzj_app.bean.tawei.AppMastBean;
import com.woyi.towerzj_app.bean.tawei.AppMastComBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.PackageUtil;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;

/**
 * 
 * 此类描述的是：塔桅
 * 
 * @author: 罗然
 * @version: 2015-7-18 上午8:47:59
 * @ClassName: TaweiDetailActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity.tawei
 */
public class NewTaweiDetailActivity extends ForwardActivity {
	private TextView lxresult1, lxresult2, lxresult3, tgresult1, tgresult2,
	tgresult3;
	private TextView title, add, next, save;
	private Button back, question1, del1, question2, del2, question3, del3;
	private TextView code, address;// 对比结果

	private TextView mc1, mc2, mc3, tv1, tv2, tv3, tv4, tv5, tv6;
	private LinearLayout linear1, linear2, linear3, linearyys1, linearyys2,
	linearyys3, linearyys4, linearyys5, linearyys6, linearylt_newadd,
	linearyyd_newadd, linearydx_newadd;

	private EditText tg1, qrcode, tg2, qrcode2, tg3, qrcode3;
	private TextView yd_ttxs1, yd_ttlx1, yd_tg1, yd_ttxs2, yd_ttlx2, yd_tg2,
	lt_ttxs1, lt_ttlx1, lt_tg1, lt_ttxs2, lt_ttlx2, lt_tg2, dx_ttxs1,
	dx_ttlx1, dx_tg1, dx_ttxs2, dx_ttlx2, dx_tg2, yd_tv, lt_tv, dx_tv,
	yd_ttxs3, lt_ttxs3, dx_ttxs3, yd_ttlx3, lt_ttlx3, dx_ttlx3, lt_tg3,
	yd_tg3, dx_tg3;

	private Spinner ttpt1, ttpt2, ttpt3, ttlx1, ttlx2, ttlx3, yd_sblx1,
	yd_sblx2, lt_sblx1, lt_sblx2, dx_sblx1, dx_sblx2, yd_ppsj1,
	yd_ppsj2, lt_ppsj1, lt_ppsj2, dx_ppsj1, dx_ppsj2, yd_ppsj3,
	lt_ppsj3, dx_ppsj3, yd_sblx3, lt_sblx3, dx_sblx3;
	private ImageView qrcodeimg1, qrcodeimg2, qrcodeimg3;

	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;

	// 二维码
	private int code1 = 2013;
	private int code2 = 2014;
	private int code3 = 2015;
	private String res_text = "text";
	private String code_str = "code";

	private List<AppMastBean> list;
	private List<AppMastComBean> comlist;
	private AppPhyInfoBean bean;

	private AppMastBean mastBean1;
	private AppMastBean mastBean2;
	private AppMastBean mastBean3;

	private List<Option> ppsjList;// 匹配数据

	// 运营商实体类
	private AppMastComBean ydcomBean1;
	private AppMastComBean ydcomBean2;
	private AppMastComBean ydcomBean3;
	private AppMastComBean ltcomBean1;
	private AppMastComBean ltcomBean2;
	private AppMastComBean ltcomBean3;
	private AppMastComBean dxcomBean1;
	private AppMastComBean dxcomBean2;
	private AppMastComBean dxcomBean3;

	private Bundle bundle;
	private DatabaseHelper mOpenHelper;
	private SQLiteDatabase db;

	private String tgStr = "", lxStr = "";
	private boolean ISPP = false;
	private String ydppflag1 = "塔桅01", ydppflag2 = "塔桅01", ydppflag3 = "塔桅01",
			ltppflag1 = "塔桅01", ltppflag2 = "塔桅01", ltppflag3 = "塔桅01",
			dxppflag1 = "塔桅01", dxppflag2 = "塔桅01", dxppflag3 = "塔桅01";
	private String ydsbflag1 = "", ydsbflag2 = "", ydsbflag3 = "",
			ltsbflag1 = "", ltsbflag2 = "", ltsbflag3 = "", dxsbflag1 = "",
			dxsbflag2 = "", dxsbflag3 = "";

	// 删除标记
	private int delflag = 0;
	private DelPopupWindow delWindow;

	private boolean comFlag = false;
	private int flags = 0;

	private AppProblemBean probean;

	public static final int SUCCESS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		flags = Integer
				.parseInt(this.getIntent().getExtras().getString("flag"));
		Reflex.loadViewForActivityOnCreate(this,
				R.layout.activity_tawei_new_detail);
		SysExitUtil.activityList.add(this);
		title.setText("塔桅");
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		relative_layout.setVisibility(View.VISIBLE);
		loading.startAnimation(animation);
		loadList();
		init();
	}

	/**
	 * 
	 * 此方法描述的是：初始化页面
	 * 
	 * @Title: init
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-19 下午3:29:21
	 */
	private void init() {
		if (bean.getStatus().equals("3") || bean.getStatus().equals("4")) {
			add.setVisibility(View.GONE);
			save.setVisibility(View.GONE);
			question1.setVisibility(View.GONE);
			del1.setVisibility(View.GONE);
			question2.setVisibility(View.GONE);
			del2.setVisibility(View.GONE);
			question3.setVisibility(View.GONE);
			del3.setVisibility(View.GONE);
		}
		code.setText(bean.getPhysicAlias());
		address.setText(bean.getPhysicName());
		back.setOnClickListener(btnLis);
		question1.setOnClickListener(btnLis);
		question2.setOnClickListener(btnLis);
		question3.setOnClickListener(btnLis);
		del1.setOnClickListener(btnLis);
		del2.setOnClickListener(btnLis);
		del3.setOnClickListener(btnLis);
		next.setOnClickListener(btnLis);
		add.setOnClickListener(btnLis);
		save.setOnClickListener(btnLis);
		qrcodeimg1.setOnClickListener(btnLis);
		qrcodeimg2.setOnClickListener(btnLis);
		qrcodeimg3.setOnClickListener(btnLis);
		lxresult1.setOnClickListener(btnLis);
		lxresult2.setOnClickListener(btnLis);
		lxresult3.setOnClickListener(btnLis);
		tgresult1.setOnClickListener(btnLis);
		tgresult2.setOnClickListener(btnLis);
		tgresult3.setOnClickListener(btnLis);

		tg1.addTextChangedListener(new mTextWatcher1());
		tg2.addTextChangedListener(new mTextWatcher2());
		tg3.addTextChangedListener(new mTextWatcher3());
		ttlx1.setOnItemSelectedListener(listener1);
		ttlx2.setOnItemSelectedListener(listener2);
		ttlx3.setOnItemSelectedListener(listener3);

		yd_ppsj1.setOnItemSelectedListener(ppsjlistener1);
		yd_ppsj2.setOnItemSelectedListener(ppsjlistener2);
		yd_ppsj3.setOnItemSelectedListener(ppsjlistener3);
		lt_ppsj1.setOnItemSelectedListener(ppsjlistener4);
		lt_ppsj2.setOnItemSelectedListener(ppsjlistener5);
		lt_ppsj3.setOnItemSelectedListener(ppsjlistener6);
		dx_ppsj1.setOnItemSelectedListener(ppsjlistener7);
		dx_ppsj2.setOnItemSelectedListener(ppsjlistener8);
		dx_ppsj3.setOnItemSelectedListener(ppsjlistener9);
		yd_sblx1.setOnItemSelectedListener(sblxlistener1);
		yd_sblx2.setOnItemSelectedListener(sblxlistener2);
		yd_sblx3.setOnItemSelectedListener(sblxlistener3);
		lt_sblx1.setOnItemSelectedListener(sblxlistener4);
		lt_sblx2.setOnItemSelectedListener(sblxlistener5);
		lt_sblx3.setOnItemSelectedListener(sblxlistener6);
		dx_sblx1.setOnItemSelectedListener(sblxlistener7);
		dx_sblx2.setOnItemSelectedListener(sblxlistener8);
		dx_sblx3.setOnItemSelectedListener(sblxlistener9);

	}

	/**
	 * 按钮监听事件
	 */
	private View.OnClickListener btnLis = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			int id = arg0.getId();
			switch (id) {
			case R.id.back:
				finish();
				break;
				// 问题
			case R.id.question1:
				bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_MAST);
				bundle.putSerializable("bean", bean);
				bundle.putString("code", mastBean1.getCode());
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.question2:
				bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_MAST);
				bundle.putSerializable("bean", bean);
				bundle.putString("code", mastBean2.getCode());
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.question3:
				bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_MAST);
				bundle.putSerializable("bean", bean);
				bundle.putString("code", mastBean3.getCode());
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.add:// 添加
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags + "");
				forwardIntent(getApplicationContext(), TaweiAddActivity.class,
						bundle);
				finish();
				break;
			case R.id.save:// 保存
				saveData();
				break;
			case R.id.next:// 下一步
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags + "");
				if (flags == 1) {
					forwardIntent(getApplicationContext(),
							RoomPhotoActivity.class, bundle);
				} else {
					forwardIntent(getApplicationContext(),
							NewJifangDetailActivity.class, bundle);
				}
				finish();
				break;
			case R.id.del:
				delData();
				delWindow.dismiss();
				break;
			case R.id.query2:
				delWindow.dismiss();
				break;
			case R.id.del1:// 删除
				delflag = 1;
				delWindow = new DelPopupWindow(NewTaweiDetailActivity.this,
						null);
				delWindow.showAtLocation(
						NewTaweiDetailActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

				break;
			case R.id.del2:// 删除
				delflag = 2;
				delWindow = new DelPopupWindow(NewTaweiDetailActivity.this,
						null);
				delWindow.showAtLocation(
						NewTaweiDetailActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

				break;
			case R.id.del3:// 删除
				delflag = 3;
				delWindow = new DelPopupWindow(NewTaweiDetailActivity.this,
						null);
				delWindow.showAtLocation(
						NewTaweiDetailActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				break;
				// 二维码扫描
			case R.id.qrcodeimg1:
				scan(code1);
				break;
			case R.id.qrcodeimg2:
				scan(code2);
				break;
			case R.id.qrcodeimg3:
				scan(code3);
				break;
			case R.id.lxresult1:// 类型：1
				if (lxresult1.getText().toString() != null
				&& !lxresult1.equals("")) {
					loadProm(mastBean1.getCode(), 1);
				}

				break;
			case R.id.lxresult2:
				if (lxresult2.getText().toString() != null
				&& !lxresult2.equals("")) {
					loadProm(mastBean2.getCode(), 1);
				}
				break;
			case R.id.lxresult3:
				if (lxresult3.getText().toString() != null
				&& !lxresult3.equals("")) {
					loadProm(mastBean3.getCode(), 1);
				}
				break;
			case R.id.tgresult1:
				if (tgresult1.getText().toString() != null
				&& !tgresult1.equals("")) {
					loadProm(mastBean1.getCode(), 2);
				}
				break;
			case R.id.tgresult2:
				if (tgresult2.getText().toString() != null
				&& !tgresult2.equals("")) {
					loadProm(mastBean2.getCode(), 2);
				}
				break;
			case R.id.tgresult3:
				if (tgresult3.getText().toString() != null
				&& !tgresult3.equals("")) {
					loadProm(mastBean3.getCode(), 2);
				}
				break;
			}
		}

	};

	/**
	 * 
	 * 此方法描述的是：匹配问题跳转
	 * 
	 * @Title: loadProm
	 * @author: 罗然
	 * @param code
	 * @return void 返回类型
	 * @version: 2015-8-11 下午7:55:58
	 */
	private void loadProm(String code, int status) {
		Bundle bundle = new Bundle();
		probean = new AppProblemBean();
		probean.setType(ApplicationData.PROBLEM_TYPE_MAST);
		if (status == 1) {
			probean.setCode(ApplicationData.PROBLEM_LXCODE_MAST);
		} else {
			probean.setCode(ApplicationData.PROBLEM_TGCODE_MAST);
		}
		bundle.putSerializable("probean", probean);
		bundle.putSerializable("bean", bean);
		bundle.putString("flag", ApplicationData.PROBLEM_TYPE_MAST);
		bundle.putString("code", code);
		bundle.putString("protype", ApplicationData.PROBLEM_TYPE_MAST);
		Intent it = new Intent(getApplicationContext(),
				QuestionDetailActivity.class);
		it.putExtras(bundle);
		NewTaweiDetailActivity.this.startActivityForResult(it, SUCCESS);
	}

	/**
	 * 
	 * 此方法描述的是：加载列表
	 * 
	 * @Title: loadList
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-21 上午10:02:02
	 */
	private void loadList() {
		list = new ArrayList<AppMastBean>();
		comlist = new ArrayList<AppMastComBean>();
		String sql = "select * from tb_mast where physicCode='"
				+ bean.getPhysicCode() + "' and status='1'";
		try {
			list = TowerSQliteDbBean.queryMastData(mOpenHelper, sql);
			sql = "select * from ts_mast where physicCode='"
					+ bean.getPhysicCode() + "'";
			comlist = TowerSQliteDbBean.queryMastComData(mOpenHelper, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}
	}

	/**
	 * 
	 * 此方法描述的是：删除操作
	 * 
	 * @Title: delData
	 * @author: 罗然
	 * @param mastBean12
	 * @return void 返回类型
	 * @version: 2015-7-24 下午2:10:47
	 */
	protected void delData() {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		try {
			// 开始事务
			db.beginTransaction();
			if (delflag == 1) {
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),
						mastBean1.getCode(),null, "01");
			} else if (delflag == 2) {
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),
						mastBean2.getCode(),null, "01");
			} else if (delflag == 3) {
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),
						mastBean3.getCode(),null, "01");
			}
			db.setTransactionSuccessful();
			linear1.setVisibility(View.GONE);
			linear2.setVisibility(View.GONE);
			linear3.setVisibility(View.GONE);
			handler.obtainMessage(2).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-2).sendToTarget();
		} finally {
			db.endTransaction();
			db.close();
		}

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
				if (bean.getStatus().equals("0")
						|| bean.getStatus().equals("1")
						|| bean.getStatus().equals("2")) {
					add.setVisibility(View.VISIBLE);
					save.setVisibility(View.VISIBLE);
				}
				if (list != null && list.size() > 0) {
					if (list.size() > 2) {
						add.setVisibility(View.GONE);
					}
				}
				loadData();
				break;
			case -1:
				toastMessage("请求失败！");
				finish();
				break;
			case 2:
				add.setVisibility(View.VISIBLE);
				loadList();
				break;
			case -2:
				// toastMessage("删除失败");
				break;
			case 4:
				// toastMessage("更新成功");
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags + "");
				if (flags == 1) {
					forwardIntent(getApplicationContext(),
							RoomPhotoActivity.class, bundle);
				} else {
					forwardIntent(getApplicationContext(),
							NewJifangDetailActivity.class, bundle);
				}
				break;
			case -4:
				// toastMessage("更新失败");
				break;
			}
		}
	};

	/*************************************************** 保存数据 ************************************************/
	/**
	 * 
	 * 此方法描述的是：封装更新对象
	 * 
	 * @Title: saveData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-22 下午2:58:12
	 */
	protected void saveData() {
		// 更新数据库信息
		try {
			int beansize1 = 0, beansize2 = 0, beansize3 = 0;// 塔桅数量
			int zzccomsize1 = 0, zzccomsize2 = 0, zzccomsize3 = 0;// 匹配的主设备数量
			AppMastBean mastBean = new AppMastBean();

			// 封装塔桅信息
			List<AppMastBean> newlist = new ArrayList<AppMastBean>();
			if (linear1.getVisibility() == View.VISIBLE) {
				if (tg1.getText().toString().trim() == null
						|| tg1.getText().toString().trim().equals("")) {
					toastMessage("请输入塔桅01的塔高值！");
					return;
				}
				beansize1++;
				mastBean.setAssetNum(mastBean1.getAssetNum());
				mastBean.setCheckUserId(ApplicationData.user.getId() + "");
				mastBean.setCode(mastBean1.getCode());
				mastBean.setLx(((Option) ttlx1.getSelectedItem()).getValue());
				mastBean.setPhysicCode(mastBean1.getPhysicCode());
				mastBean.setTg(Double.parseDouble(tg1.getText().toString()));
				mastBean.setXs(((Option) ttpt1.getSelectedItem()).getValue());
				mastBean.setEwm(qrcode.getText().toString());
				newlist.add(mastBean);
			}
			if (linear2.getVisibility() == View.VISIBLE) {
				if (tg2.getText().toString().trim() == null
						|| tg2.getText().toString().trim().equals("")) {
					toastMessage("请输入塔桅02的塔高值！");
					return;
				}
				beansize2++;
				mastBean = new AppMastBean();
				mastBean.setAssetNum(mastBean2.getAssetNum());
				mastBean.setCheckUserId(ApplicationData.user.getId() + "");
				mastBean.setCode(mastBean2.getCode());
				mastBean.setLx(((Option) ttlx2.getSelectedItem()).getValue());
				mastBean.setPhysicCode(mastBean2.getPhysicCode());
				mastBean.setTg(Double.parseDouble(tg2.getText().toString()));
				mastBean.setXs(((Option) ttpt2.getSelectedItem()).getValue());
				mastBean.setEwm(qrcode2.getText().toString());
				newlist.add(mastBean);
			}
			if (linear3.getVisibility() == View.VISIBLE) {
				if (tg3.getText().toString().trim() == null
						|| tg3.getText().toString().trim().equals("")) {
					toastMessage("请输入塔桅03的塔高值！");
					return;
				}
				beansize3++;
				mastBean = new AppMastBean();
				mastBean.setAssetNum(mastBean3.getAssetNum());
				mastBean.setCheckUserId(ApplicationData.user.getId() + "");
				mastBean.setCode(mastBean3.getCode());
				mastBean.setLx(((Option) ttlx3.getSelectedItem()).getValue());
				mastBean.setPhysicCode(mastBean3.getPhysicCode());
				mastBean.setTg(Double.parseDouble(tg3.getText().toString()));
				mastBean.setXs(((Option) ttpt3.getSelectedItem()).getValue());
				mastBean.setEwm(qrcode3.getText().toString());
				newlist.add(mastBean);
			}
			// 运营商
			List<AppMastComBean> newcomlist = new ArrayList<AppMastComBean>();
			AppMastComBean comBean = new AppMastComBean();
			if (linearyys1.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) yd_ppsj1.getSelectedItem())
						.getText();
				String sblxStr = ((Option) yd_sblx1.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("塔桅01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("塔桅02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("塔桅03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(ydcomBean1.getCode());
				comBean.setLinkCode(((Option) yd_ppsj1.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ydcomBean1.getPhysicCode());
				// comBean.setXs(((Option)
				// ttpt1.getSelectedItem()).getText());
				newcomlist.add(comBean);
			}
			if (linearyys2.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) yd_ppsj2.getSelectedItem())
						.getText();
				String sblxStr = ((Option) yd_sblx2.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("塔桅01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("塔桅02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("塔桅03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppMastComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(ydcomBean2.getCode());
				comBean.setLinkCode(((Option) yd_ppsj2.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ydcomBean2.getPhysicCode());
				// comBean.setXs(((Option)
				// ttpt2.getSelectedItem()).getText());
				newcomlist.add(comBean);
			}
			if (linearyys3.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) lt_ppsj1.getSelectedItem())
						.getText();
				String sblxStr = ((Option) lt_sblx1.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("塔桅01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("塔桅02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("塔桅03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppMastComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(ltcomBean1.getCode());
				comBean.setLinkCode(((Option) lt_ppsj1.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ltcomBean1.getPhysicCode());
				// comBean.setXs(((Option)
				// ttpt3.getSelectedItem()).getText());
				newcomlist.add(comBean);
			}
			if (linearyys4.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) lt_ppsj1.getSelectedItem())
						.getText();
				String sblxStr = ((Option) lt_sblx2.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("塔桅01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("塔桅02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("塔桅03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppMastComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(ltcomBean2.getCode());
				comBean.setLinkCode(((Option) lt_ppsj2.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ltcomBean2.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (linearyys5.getVisibility() == View.VISIBLE) {
				String sblxStr = ((Option) dx_sblx1.getSelectedItem())
						.getText();
				String linkcodeStr = ((Option) dx_ppsj1.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("塔桅01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("塔桅02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("塔桅03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppMastComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(dxcomBean1.getCode());
				comBean.setLinkCode(((Option) dx_ppsj1.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(dxcomBean1.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (linearyys6.getVisibility() == View.VISIBLE) {
				String sblxStr = ((Option) dx_sblx2.getSelectedItem())
						.getText();
				String linkcodeStr = ((Option) dx_ppsj2.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("塔桅01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("塔桅02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("塔桅03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppMastComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(dxcomBean2.getCode());
				comBean.setLinkCode(((Option) dx_ppsj2.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(dxcomBean2.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (linearyyd_newadd.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) yd_ppsj3.getSelectedItem())
						.getText();
				String sblxStr = ((Option) yd_sblx3.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("塔桅01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("塔桅02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("塔桅03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(ydcomBean3.getCode());
				comBean.setLinkCode(((Option) yd_ppsj3.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ydcomBean3.getPhysicCode());
				// comBean.setXs(((Option)
				// ttpt3.getSelectedItem()).getText());
				newcomlist.add(comBean);
			}

			if (linearylt_newadd.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) lt_ppsj3.getSelectedItem())
						.getText();
				String sblxStr = ((Option) lt_sblx3.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("塔桅01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("塔桅02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("塔桅03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppMastComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(ltcomBean3.getCode());
				comBean.setLinkCode(((Option) lt_ppsj3.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ltcomBean3.getPhysicCode());
				newcomlist.add(comBean);
			}

			if (linearydx_newadd.getVisibility() == View.VISIBLE) {
				String sblxStr = ((Option) dx_sblx3.getSelectedItem())
						.getText();
				String linkcodeStr = ((Option) dx_ppsj3.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("塔桅01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("塔桅02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("塔桅03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppMastComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(dxcomBean3.getCode());
				comBean.setLinkCode(((Option) dx_ppsj3.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(dxcomBean3.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (beansize1 != 0) {
				if (zzccomsize1 > beansize1) {
					toastMessage("塔桅01的设备类型重复设置为主资产");
					return;
				}
			}
			if (beansize2 != 0) {
				if (zzccomsize2 > beansize2) {
					toastMessage("塔桅02的设备类型重复设置为主资产");
					return;
				}
			}
			if (beansize3 != 0) {
				if (zzccomsize3 > beansize3) {
					toastMessage("塔桅03的设备类型重复设置为主资产");
					return;
				}
			}
			relative_layout.setVisibility(View.VISIBLE);
			loading.startAnimation(animation);
			// 开始事务
			db = mOpenHelper.getReadableDatabase();
			db.beginTransaction();
			if (newlist != null && newlist.size() > 0) {
				for (AppMastBean o : newlist) {
					TowerSQliteDbBean.updateMastData(db, o);
				}
			}
			if (newcomlist != null && newcomlist.size() > 0) {
				for (AppMastComBean o : newcomlist) {
					TowerSQliteDbBean.updateMastComData(db, o);
				}
			}
			db.setTransactionSuccessful();
			handler.obtainMessage(4).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-4).sendToTarget();
		} finally {
			if (db != null) {
				db.endTransaction();
				db.close();
			}
		}

	}

	/*********************************************************** 页面数据显示及变化结果 ******************************************************************/

	/**
	 * 
	 * 此类描述的是：塔高输入值变化
	 * 
	 * @author: 罗然
	 * @version: 2015-7-22 下午2:20:10
	 * @ClassName: mTextWatcher
	 * @项目： towerzj_app
	 * @包：com.woyi.towerzj_app.activity.tawei
	 */
	class mTextWatcher1 implements TextWatcher {

		// 文字变化前
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		// 文字变化时
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// 判断小数点后输入数字的位数
			if (s.toString().startsWith(".")) {
				tg1.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					tg1.setText(s);
					tg1.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
			String str = tg1.getText().toString();
			changeTgAndLx("1", str, tgresult1, mastBean1);
		}
	}

	/**
	 * 
	 * 此类描述的是：塔高输入值变化
	 * 
	 * @author: 罗然
	 * @version: 2015-7-22 下午2:20:10
	 * @ClassName: mTextWatcher
	 * @项目： towerzj_app
	 * @包：com.woyi.towerzj_app.activity.tawei
	 */
	class mTextWatcher2 implements TextWatcher {

		// 文字变化前
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		// 文字变化时
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// 判断小数点后输入数字的位数
			if (s.toString().startsWith(".")) {
				tg2.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					tg2.setText(s);
					tg2.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
			String str = tg2.getText().toString();
			changeTgAndLx("1", str, tgresult2, mastBean2);
		}
	}

	/**
	 * 
	 * 此类描述的是：塔高输入值变化
	 * 
	 * @author: 罗然
	 * @version: 2015-7-22 下午2:20:10
	 * @ClassName: mTextWatcher
	 * @项目： towerzj_app
	 * @包：com.woyi.towerzj_app.activity.tawei
	 */
	class mTextWatcher3 implements TextWatcher {

		// 文字变化前
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		// 文字变化时
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// 判断小数点后输入数字的位数
			if (s.toString().startsWith(".")) {
				tg3.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					tg3.setText(s);
					tg3.setSelection(s.length());
				}
			}
		}

		// 文字变化后0.
		@Override
		public void afterTextChanged(Editable s) {
			String str = tg3.getText().toString();
			changeTgAndLx("1", str, tgresult3, mastBean3);
		}
	}

	/**
	 * 铁塔类型点击事件
	 */
	private AdapterView.OnItemSelectedListener listener1 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) ttlx1.getSelectedItem()).getText();
			str = ApplicationData.reMap.get(str);
			if (str != null && !str.equals("")) {
				changeTgAndLx("2", str, lxresult1, mastBean1);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 铁塔类型点击事件
	 */
	private AdapterView.OnItemSelectedListener listener2 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) ttlx2.getSelectedItem()).getText();
			str = ApplicationData.reMap.get(str);
			if (str != null && !str.equals("")) {
				changeTgAndLx("2", str, lxresult2, mastBean2);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 铁塔类型点击事件
	 */
	private AdapterView.OnItemSelectedListener listener3 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) ttlx3.getSelectedItem()).getText();
			str = ApplicationData.reMap.get(str);
			if (str != null && !str.equals("")) {
				changeTgAndLx("2", str, lxresult3, mastBean3);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 
	 * 此方法描述的是：判断塔高、类型变化
	 * 
	 * @Title: changeTgAndLx
	 * @author: 罗然
	 * @param type
	 *            (塔高：1 类型：2)
	 * @param str
	 * @param result12
	 * @return void 返回类型
	 * @version: 2015-7-24 下午3:20:44
	 */
	public void changeTgAndLx(String type, String mcstr, TextView result,
			AppMastBean appMastBean) {
		if (mcstr != null && !mcstr.equals("")) {
			mcstr = mcstr.trim();
		}
		if (result == tgresult1 || result == tgresult2 || result == tgresult3) {
			tgStr = "";
		}
		if (result == lxresult1 || result == lxresult2 || result == lxresult3) {
			lxStr = "";
		}
		ISPP = false;
		if (type.equals("1")) {
			if (mcstr != null && !mcstr.equals("")) {
				Double str = Double.parseDouble(mcstr);
				if (linearyys1.getVisibility() == View.VISIBLE) {
					if (appMastBean.getCode().equals(
							((Option) yd_ppsj1.getSelectedItem()).getValue())) {
						if (((Option) yd_sblx1.getSelectedItem()).getText()
								.equals("主资产")) {
							tgStr = loadTgData(str, ydcomBean1);
						}
					}
				}
				if (!ISPP) {
					if (linearyys2.getVisibility() == View.VISIBLE) {
						if (appMastBean.getCode().equals(
								((Option) yd_ppsj2.getSelectedItem())
								.getValue())) {
							if (((Option) yd_sblx2.getSelectedItem()).getText()
									.equals("主资产")) {
								tgStr = loadTgData(str, ydcomBean2);
							}
						}
					}
				}
				if (!ISPP) {
					if (linearyyd_newadd.getVisibility() == View.VISIBLE) {
						if (appMastBean.getCode().equals(
								((Option) yd_ppsj3.getSelectedItem())
								.getValue())) {
							if (((Option) yd_sblx3.getSelectedItem()).getText()
									.equals("主资产")) {
								tgStr = loadTgData(str, ydcomBean3);
							}
						}
					}
				}
				if (!ISPP) {
					if (linearyys3.getVisibility() == View.VISIBLE) {
						if (appMastBean.getCode().equals(
								((Option) lt_ppsj1.getSelectedItem())
								.getValue())) {
							if (((Option) lt_sblx1.getSelectedItem()).getText()
									.equals("主资产")) {
								tgStr = loadTgData(str, ltcomBean1);
							}
						}
					}
				}
				if (!ISPP) {
					if (linearyys4.getVisibility() == View.VISIBLE) {
						if (appMastBean.getCode().equals(
								((Option) lt_ppsj2.getSelectedItem())
								.getValue())) {
							if (((Option) lt_sblx2.getSelectedItem()).getText()
									.equals("主资产")) {
								tgStr = loadTgData(str, ltcomBean2);
							}
						}
					}
				}
				if (!ISPP) {
					if (linearylt_newadd.getVisibility() == View.VISIBLE) {
						if (appMastBean.getCode().equals(
								((Option) lt_ppsj3.getSelectedItem())
								.getValue())) {
							if (((Option) lt_sblx3.getSelectedItem()).getText()
									.equals("主资产")) {
								tgStr = loadTgData(str, ltcomBean3);
							}
						}
					}
				}
				if (!ISPP) {
					if (linearyys5.getVisibility() == View.VISIBLE) {
						if (appMastBean.getCode().equals(
								((Option) dx_ppsj1.getSelectedItem())
								.getValue())) {
							if (((Option) dx_sblx1.getSelectedItem()).getText()
									.equals("主资产")) {
								tgStr = loadTgData(str, dxcomBean1);
							}
						}
					}
				}
				if (!ISPP) {
					if (linearyys6.getVisibility() == View.VISIBLE) {
						if (appMastBean.getCode().equals(
								((Option) dx_ppsj2.getSelectedItem())
								.getValue())) {
							if (((Option) dx_sblx2.getSelectedItem()).getText()
									.equals("主资产")) {
								tgStr = loadTgData(str, dxcomBean2);
							}
						}
					}
				}
				if (!ISPP) {
					if (linearydx_newadd.getVisibility() == View.VISIBLE) {
						if (appMastBean.getCode().equals(
								((Option) dx_ppsj3.getSelectedItem())
								.getValue())) {
							if (((Option) dx_sblx3.getSelectedItem()).getText()
									.equals("主资产")) {
								tgStr = loadTgData(str, dxcomBean3);
							}
						}
					}
				}
			}
		} else if (type.equals("2")) {
			if (linearyys1.getVisibility() == View.VISIBLE) {
				if (appMastBean.getCode().equals(
						((Option) yd_ppsj1.getSelectedItem()).getValue())) {
					if (((Option) yd_sblx1.getSelectedItem()).getText().equals(
							"主资产")) {
						lxStr = loadLxData(mcstr, ydcomBean1);
					}
				}
			}
			if (!ISPP) {
				if (linearyys2.getVisibility() == View.VISIBLE) {
					if (appMastBean.getCode().equals(
							((Option) yd_ppsj2.getSelectedItem()).getValue())) {
						if (((Option) yd_sblx2.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, ydcomBean2);
						}
					}
				}
			}
			if (!ISPP) {
				if (linearyyd_newadd.getVisibility() == View.VISIBLE) {
					if (appMastBean.getCode().equals(
							((Option) yd_ppsj3.getSelectedItem()).getValue())) {
						if (((Option) yd_sblx3.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, ydcomBean3);
						}
					}
				}
			}
			if (!ISPP) {
				if (linearyys3.getVisibility() == View.VISIBLE) {
					if (appMastBean.getCode().equals(
							((Option) lt_ppsj1.getSelectedItem()).getValue())) {
						if (((Option) lt_sblx1.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, ltcomBean1);
						}
					}
				}
			}
			if (!ISPP) {
				if (linearyys4.getVisibility() == View.VISIBLE) {
					if (appMastBean.getCode().equals(
							((Option) lt_ppsj2.getSelectedItem()).getValue())) {
						if (((Option) lt_sblx2.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, ltcomBean2);
						}
					}
				}
			}
			if (!ISPP) {
				if (linearylt_newadd.getVisibility() == View.VISIBLE) {
					if (appMastBean.getCode().equals(
							((Option) lt_ppsj3.getSelectedItem()).getValue())) {
						if (((Option) lt_sblx3.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, ltcomBean3);
						}
					}
				}
			}
			if (!ISPP) {
				if (linearyys5.getVisibility() == View.VISIBLE) {
					if (appMastBean.getCode().equals(
							((Option) dx_ppsj1.getSelectedItem()).getValue())) {
						if (((Option) dx_sblx1.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, dxcomBean1);
						}
					}
				}
			}
			if (!ISPP) {
				if (linearyys6.getVisibility() == View.VISIBLE) {
					if (appMastBean.getCode().equals(
							((Option) dx_ppsj2.getSelectedItem()).getValue())) {
						if (((Option) dx_sblx2.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, dxcomBean2);
						}
					}
				}
			}
			if (!ISPP) {
				if (linearydx_newadd.getVisibility() == View.VISIBLE) {
					if (appMastBean.getCode().equals(
							((Option) dx_ppsj3.getSelectedItem()).getValue())) {
						if (((Option) dx_sblx3.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, dxcomBean3);
						}
					}
				}
			}
		}
		if (result == tgresult1 || result == tgresult2 || result == tgresult3) {
			result.setText(tgStr);
		}
		if (result == lxresult1 || result == lxresult2 || result == lxresult3) {
			result.setText(lxStr);
		}
	}

	/**
	 * 
	 * 此方法描述的是：判断塔高是否匹配
	 * 
	 * @Title: loadTgData
	 * @author: 罗然
	 * @param str
	 * @param comBean
	 * @return
	 * @return String 返回类型
	 * @version: 2015-8-11 下午2:05:47
	 */
	private String loadTgData(Double str, AppMastComBean comBean) {
		String tgstr = "";
		if (Math.abs(str - comBean.getTg()) > 10) {
			ISPP = true;
			tgstr = "塔高不匹配";
		} else {
			tgstr = "";
		}
		return tgstr;
	}

	/**
	 * 
	 * 此方法描述的是：判断类型是否匹配
	 * 
	 * @Title: loadLxData
	 * @author: 罗然
	 * @param lxstr
	 * @param comBean
	 * @return
	 * @return String 返回类型
	 * @version: 2015-8-11 下午2:11:06
	 */
	private String loadLxData(String lxstr, AppMastComBean comBean) {
		String str = "";
		if (lxstr != null && !lxstr.equals("")) {
			if (!lxstr.equals(comBean.getLx())) {
				ISPP = true;
				str = "铁塔类型不匹配";
			} else {
				str = "";
			}
		} else {
			str = "铁塔类型不匹配";
		}
		return str;
	}

	/***************************************** 运营商匹配数据判断 *******************************************/

	private AdapterView.OnItemSelectedListener sblxlistener1 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) yd_ppsj1.getSelectedItem()).getText().trim();
			if (ydsbflag1 != null && !ydsbflag1.equals("")) {
				if (ydsbflag1.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ydsbflag1.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ydsbflag1.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
				ydsbflag1 = "";
			}
			if (((Option) yd_sblx1.getSelectedItem()).getText().equals("主资产")) {
				if (!str.equals("请选择")) {
					ydsbflag1 = str;
					changeMastCom(str, ydcomBean1);
				}
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener sblxlistener2 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) yd_ppsj2.getSelectedItem()).getText().trim();
			if (ydsbflag2 != null && !ydsbflag2.equals("")) {
				if (ydsbflag2.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ydsbflag2.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ydsbflag2.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
				ydsbflag2 = "";
			}
			if (((Option) yd_sblx2.getSelectedItem()).getText().equals("主资产")) {
				if (!str.equals("请选择")) {
					ydsbflag2 = str;
					changeMastCom(str, ydcomBean2);
				}
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener sblxlistener3 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) yd_ppsj3.getSelectedItem()).getText().trim();
			if (ydsbflag3 != null && !ydsbflag3.equals("")) {
				if (ydsbflag3.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ydsbflag3.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ydsbflag3.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
				ydsbflag3 = "";
			}
			if (((Option) yd_sblx3.getSelectedItem()).getText().equals("主资产")) {
				if (!str.equals("请选择")) {
					ydsbflag3 = str;
					changeMastCom(str, ydcomBean3);
				}
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener sblxlistener4 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) lt_ppsj1.getSelectedItem()).getText().trim();
			if (ltsbflag1 != null && !ltsbflag1.equals("")) {
				if (ltsbflag1.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ltsbflag1.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ltsbflag1.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
				ltsbflag1 = "";
			}
			if (((Option) lt_sblx1.getSelectedItem()).getText().equals("主资产")) {
				if (!str.equals("请选择")) {
					ltsbflag1 = str;
					changeMastCom(str, ltcomBean1);
				}
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener sblxlistener5 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) lt_ppsj2.getSelectedItem()).getText().trim();
			if (ltsbflag2 != null && !ltsbflag2.equals("")) {
				if (ltsbflag2.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ltsbflag2.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ltsbflag2.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
				ltsbflag2 = "";
			}
			if (((Option) lt_sblx2.getSelectedItem()).getText().equals("主资产")) {
				if (!str.equals("请选择")) {
					ltsbflag2 = str;
					changeMastCom(str, ltcomBean2);
				}

			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener sblxlistener6 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) lt_ppsj3.getSelectedItem()).getText().trim();
			if (ltsbflag3 != null && !ltsbflag3.equals("")) {
				if (ltsbflag3.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ltsbflag3.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ltsbflag3.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
				ltsbflag3 = "";
			}
			if (((Option) lt_sblx3.getSelectedItem()).getText().equals("主资产")) {
				if (!str.equals("请选择")) {
					ltsbflag3 = str;
					changeMastCom(str, ltcomBean3);
				}
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener sblxlistener7 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) dx_ppsj1.getSelectedItem()).getText().trim();
			if (dxsbflag1 != null && !dxsbflag1.equals("")) {
				if (dxsbflag1.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (dxsbflag1.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (dxsbflag1.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
				dxsbflag1 = "";
			}
			if (((Option) dx_sblx1.getSelectedItem()).getText().equals("主资产")) {
				if (!str.equals("请选择")) {
					dxsbflag1 = str;
					changeMastCom(str, dxcomBean1);
				}
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener sblxlistener8 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) dx_ppsj2.getSelectedItem()).getText().trim();
			if (dxsbflag2 != null && !dxsbflag2.equals("")) {
				if (dxsbflag2.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (dxsbflag2.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (dxsbflag2.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
				dxsbflag2 = "";
			}
			if (((Option) dx_sblx2.getSelectedItem()).getText().equals("主资产")) {
				if (!str.equals("请选择")) {
					dxsbflag2 = str;
					changeMastCom(str, dxcomBean2);
				}
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener sblxlistener9 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) dx_ppsj3.getSelectedItem()).getText().trim();
			if (dxsbflag3 != null && !dxsbflag3.equals("")) {
				if (dxsbflag3.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (dxsbflag3.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (dxsbflag3.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
				dxsbflag3 = "";
			}
			if (((Option) dx_sblx3.getSelectedItem()).getText().equals("主资产")) {
				if (!str.equals("请选择")) {
					dxsbflag3 = str;
					changeMastCom(str, dxcomBean3);
				}
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener ppsjlistener1 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) yd_ppsj1.getSelectedItem()).getText().trim();
			if (ydppflag1 != null && !ydppflag1.equals("")) {
				if (ydppflag1.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ydppflag1.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ydppflag1.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
			}
			if (((Option) yd_sblx1.getSelectedItem()).getText().equals("主资产")) {
				ydppflag1 = str;
				changeMastCom(str, ydcomBean1);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener ppsjlistener2 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) yd_ppsj2.getSelectedItem()).getText().trim();
			if (ydppflag2 != null && !ydppflag2.equals("")) {
				if (ydppflag2.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ydppflag2.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ydppflag2.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
			}
			if (((Option) yd_sblx2.getSelectedItem()).getText().equals("主资产")) {
				ydppflag2 = str;
				changeMastCom(str, ydcomBean2);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener ppsjlistener3 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) yd_ppsj3.getSelectedItem()).getText().trim();
			if (ydppflag3 != null && !ydppflag3.equals("")) {
				if (ydppflag3.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ydppflag3.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ydppflag3.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
			}
			if (((Option) yd_sblx3.getSelectedItem()).getText().equals("主资产")) {
				ydppflag3 = str;
				changeMastCom(str, ydcomBean3);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener ppsjlistener4 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) lt_ppsj1.getSelectedItem()).getText().trim();
			if (ltppflag1 != null && !ltppflag1.equals("")) {
				if (ltppflag1.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ltppflag1.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ltppflag1.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
			}
			if (((Option) lt_sblx1.getSelectedItem()).getText().equals("主资产")) {
				ltppflag1 = str;
				changeMastCom(str, ltcomBean1);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener ppsjlistener5 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) lt_ppsj2.getSelectedItem()).getText().trim();
			if (ltppflag2 != null && !ltppflag2.equals("")) {
				if (ltppflag2.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ltppflag2.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ltppflag2.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
			}
			if (((Option) lt_sblx2.getSelectedItem()).getText().equals("主资产")) {
				ltppflag2 = str;
				changeMastCom(str, ltcomBean2);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener ppsjlistener6 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) lt_ppsj3.getSelectedItem()).getText().trim();
			if (ltppflag3 != null && !ltppflag3.equals("")) {
				if (ltppflag3.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (ltppflag3.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (ltppflag3.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
			}
			if (((Option) lt_sblx3.getSelectedItem()).getText().equals("主资产")) {
				ltppflag3 = str;
				changeMastCom(str, ltcomBean3);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener ppsjlistener7 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) dx_ppsj1.getSelectedItem()).getText().trim();
			if (dxppflag1 != null && !dxppflag1.equals("")) {
				if (dxppflag1.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (dxppflag1.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (dxppflag1.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
			}
			if (((Option) dx_sblx1.getSelectedItem()).getText().equals("主资产")) {
				dxppflag1 = str;
				changeMastCom(str, dxcomBean1);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener ppsjlistener8 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) dx_ppsj2.getSelectedItem()).getText().trim();
			if (dxppflag2 != null && !dxppflag2.equals("")) {
				if (dxppflag2.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (dxppflag2.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (dxppflag2.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
			}
			if (((Option) dx_sblx2.getSelectedItem()).getText().equals("主资产")) {
				dxppflag2 = str;
				changeMastCom(str, dxcomBean2);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private AdapterView.OnItemSelectedListener ppsjlistener9 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) dx_ppsj3.getSelectedItem()).getText().trim();
			if (dxppflag3 != null && !dxppflag3.equals("")) {
				if (dxppflag3.equals("塔桅01")) {
					lxresult1.setText("");
					tgresult1.setText("");
				} else if (dxppflag3.equals("塔桅02")) {
					lxresult2.setText("");
					tgresult2.setText("");
				} else if (dxppflag3.equals("塔桅03")) {
					lxresult3.setText("");
					tgresult3.setText("");
				}
			}
			if (((Option) dx_sblx3.getSelectedItem()).getText().equals("主资产")) {
				dxppflag3 = str;
				changeMastCom(str, dxcomBean3);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private void changeMastCom(String str, AppMastComBean comBean) {
		if (!str.equals("请选择")) {
			if (str.equals("塔桅01")) {
				String tgstr = tg1.getText().toString();
				if (tgstr != null && !tgstr.equals("")) {
					Double rlstr = Double.parseDouble(tgstr);
					tgStr = loadTgData(rlstr, comBean);
				} else {
					tgStr = "塔高不匹配";
				}
				String lxstr = ((Option) ttlx1.getSelectedItem()).getText();
				lxstr = ApplicationData.reMap.get(lxstr);
				lxStr = loadLxData(lxstr, comBean);
				lxresult1.setText(lxStr);
				tgresult1.setText(tgStr);
			} else if (str.equals("塔桅02")) {
				String tgstr = tg2.getText().toString();
				if (tgstr != null && !tgstr.equals("")) {
					Double rlstr = Double.parseDouble(tgstr);
					tgStr = loadTgData(rlstr, comBean);
				} else {
					tgStr = "塔高不匹配";
				}
				String lxstr = ((Option) ttlx2.getSelectedItem()).getText();
				lxstr = ApplicationData.reMap.get(lxstr);
				lxStr = loadLxData(lxstr, comBean);
				lxresult2.setText(lxStr);
				tgresult2.setText(tgStr);
			} else if (str.equals("塔桅03")) {
				String tgstr = tg3.getText().toString();
				if (tgstr != null && !tgstr.equals("")) {
					Double rlstr = Double.parseDouble(tgstr);
					tgStr = loadTgData(rlstr, comBean);
				} else {
					tgStr = "塔高不匹配";
				}
				String lxstr = ((Option) ttlx3.getSelectedItem()).getText();
				lxstr = ApplicationData.reMap.get(lxstr);
				lxStr = loadLxData(lxstr, comBean);
				lxresult3.setText(lxStr);
				tgresult3.setText(tgStr);
			}
		}
	}

	/**
	 * 
	 * 此方法描述的是：封装页面数据
	 * 
	 * @Title: loadData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-24 下午2:22:18
	 */
	protected void loadData() {
		ppsjList = new ArrayList<Option>();
		Option option = null;
		// if (list != null && list.size() > 1) {
		// option = new Option();
		// option.setText("请选择");
		// option.setValue("0");
		// ppsjList.add(option);
		// } else if (list != null && comlist != null
		// && list.size() < comlist.size()) {
		// option = new Option();
		// option.setText("请选择");
		// option.setValue("0");
		// ppsjList.add(option);
		// }
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					option = new Option();
					linear1.setVisibility(View.VISIBLE);
					mastBean1 = list.get(i);
					mc1.setText("塔桅01");
					tg1.setText(mastBean1.getTg() + "");
					fillSpinnerAdapter(SpinnerUtilBean.getTtlx(), ttlx1, "请选择铁塔类型");
					if (mastBean1.getLx() != null && !mastBean1.getLx().equals("")) {
						selectedSpinner(SpinnerUtilBean.getTtlx(),
								mastBean1.getLx(), ttlx1);
					}
					fillSpinnerAdapter(SpinnerUtilBean.getTtpt(), ttpt1, "请选择铁塔平台");
					if (mastBean1.getXs() != null && !mastBean1.getXs().equals("")) {
						selectedSpinner(SpinnerUtilBean.getTtpt(),
								mastBean1.getXs(), ttpt1);
					}
					if (mastBean1.getEwm() != null
							&& !mastBean1.getEwm().equals("")
							&& !mastBean1.getEwm().equals("null")) {
						qrcode.setText(mastBean1.getEwm());
						qrcodeimg1.setVisibility(View.GONE);
					} else {
						// qrcode.setText("");
					}
					option.setValue(mastBean1.getCode());
					option.setText("塔桅01");
					ppsjList.add(option);
				}
				if (i == 1) {
					option = new Option();
					linear2.setVisibility(View.VISIBLE);
					mastBean2 = list.get(i);
					mc2.setText("塔桅02");
					tg2.setText(mastBean2.getTg() + "");
					fillSpinnerAdapter(SpinnerUtilBean.getTtlx(), ttlx2, "请选择铁塔类型");
					if (mastBean2.getLx() != null && !mastBean2.getLx().equals("")) {
						selectedSpinner(SpinnerUtilBean.getTtlx(),
								mastBean2.getLx(), ttlx2);
					}
					fillSpinnerAdapter(SpinnerUtilBean.getTtpt(), ttpt2, "请选择铁塔平台");
					if (mastBean2.getXs() != null && !mastBean2.getXs().equals("")) {
						selectedSpinner(SpinnerUtilBean.getTtpt(),
								mastBean2.getXs(), ttpt2);
					}
					if (mastBean2.getEwm() != null
							&& !mastBean2.getEwm().equals("")
							&& !mastBean2.getEwm().equals("null")) {
						qrcode2.setText(mastBean2.getEwm());
						qrcodeimg2.setVisibility(View.GONE);
					} else {
						// qrcode2.setText("");
					}
					option.setValue(mastBean2.getCode());
					option.setText("塔桅02");
					ppsjList.add(option);
				}
				if (i == 2) {
					option = new Option();
					linear3.setVisibility(View.VISIBLE);
					mastBean3 = list.get(i);
					mc3.setText("塔桅03");
					tg3.setText(mastBean3.getTg() + "");
					fillSpinnerAdapter(SpinnerUtilBean.getTtlx(), ttlx3, "请选择铁塔类型");
					if (mastBean3.getLx() != null && !mastBean3.getLx().equals("")) {
						selectedSpinner(SpinnerUtilBean.getTtlx(),
								mastBean3.getLx(), ttlx3);
					}
					fillSpinnerAdapter(SpinnerUtilBean.getTtpt(), ttpt3, "请选择铁塔平台");
					if (mastBean3.getXs() != null && !mastBean3.getXs().equals("")) {
						selectedSpinner(SpinnerUtilBean.getTtpt(),
								mastBean3.getXs(), ttpt3);
					}
					if (mastBean3.getEwm() != null
							&& !mastBean3.getEwm().equals("")
							&& !mastBean3.getEwm().equals("null")) {
						qrcode3.setText(mastBean3.getEwm());
						qrcodeimg3.setVisibility(View.GONE);
					} else {
						// qrcode3.setText("");
					}
					option.setValue(mastBean3.getCode());
					option.setText("塔桅03");
					ppsjList.add(option);
				}
			}
		}else{
			option = new Option();
			option.setText("请选择");
			option.setValue("0");
			ppsjList.add(option);
		}
		fillSpinnerAdapter(ppsjList, yd_ppsj1, "请选择匹配数据");
		fillSpinnerAdapter(ppsjList, yd_ppsj2, "请选择匹配数据");
		fillSpinnerAdapter(ppsjList, yd_ppsj3, "请选择匹配数据");
		fillSpinnerAdapter(ppsjList, lt_ppsj2, "请选择匹配数据");
		fillSpinnerAdapter(ppsjList, lt_ppsj1, "请选择匹配数据");
		fillSpinnerAdapter(ppsjList, lt_ppsj3, "请选择匹配数据");
		fillSpinnerAdapter(ppsjList, dx_ppsj2, "请选择匹配数据");
		fillSpinnerAdapter(ppsjList, dx_ppsj1, "请选择匹配数据");
		fillSpinnerAdapter(ppsjList, dx_ppsj3, "请选择匹配数据");

		fillSpinnerAdapter(SpinnerUtilBean.getSblx(), yd_sblx2, "请选择设备类型");
		fillSpinnerAdapter(SpinnerUtilBean.getSblx(), yd_sblx1, "请选择设备类型");
		fillSpinnerAdapter(SpinnerUtilBean.getSblx(), yd_sblx3, "请选择设备类型");
		fillSpinnerAdapter(SpinnerUtilBean.getSblx(), lt_sblx2, "请选择设备类型");
		fillSpinnerAdapter(SpinnerUtilBean.getSblx(), lt_sblx1, "请选择设备类型");
		fillSpinnerAdapter(SpinnerUtilBean.getSblx(), lt_sblx3, "请选择设备类型");
		fillSpinnerAdapter(SpinnerUtilBean.getSblx(), dx_sblx2, "请选择设备类型");
		fillSpinnerAdapter(SpinnerUtilBean.getSblx(), dx_sblx1, "请选择设备类型");
		fillSpinnerAdapter(SpinnerUtilBean.getSblx(), dx_sblx3, "请选择设备类型");
		if (comlist != null && comlist.size() > 0) {
//			if (comlist.size() == 1) {
//				comFlag = true;
//			}
			List<AppMastComBean> ydlist = new ArrayList<AppMastComBean>();
			List<AppMastComBean> ltlist = new ArrayList<AppMastComBean>();
			List<AppMastComBean> dxlist = new ArrayList<AppMastComBean>();
			for (AppMastComBean o : comlist) {
				if (o.getType().equals("01")) {// 移动
					ydlist.add(o);
				} else if (o.getType().equals("02")) {// 联通
					ltlist.add(o);
				} else if (o.getType().equals("03")) {// 电信
					dxlist.add(o);
				}
			}
			if (ydlist != null && ydlist.size() > 0) {
				for (int i = 0; i < ydlist.size(); i++) {
					if (i == 0) {
						linearyys1.setVisibility(View.VISIBLE);
						ydcomBean1 = ydlist.get(i);
						tv1.setText("中国移动");
						if (ydcomBean1.getXs() != null
								&& !ydcomBean1.getXs().equals("")
								&& !ydcomBean1.getXs().equals("null")) {
							yd_ttxs1.setText(ydcomBean1.getXs());
						} else {
							yd_ttxs1.setText("无");
						}
						if (ydcomBean1.getLx() != null
								&& !ydcomBean1.getLx().equals("")
								&& !ydcomBean1.getLx().equals("null")) {
							yd_ttlx1.setText(ydcomBean1.getLx());
						} else {
							yd_ttlx1.setText("无");
						}
						if (String.valueOf(ydcomBean1.getTg()) != null
								&& !String.valueOf(ydcomBean1.getTg()).equals(
										"")
										&& !String.valueOf(ydcomBean1.getTg()).equals(
												"null")) {
							yd_tg1.setText(ydcomBean1.getTg() + "");
						} else {
							yd_tg1.setText("无");
						}

//						if (comFlag) {
//							ydcomBean1.setCheckValue("主资产");
//							yd_sblx1.setClickable(false);
//							yd_sblx1.setSelection(1);
//						} else{

							if (ydcomBean1.getCheckValue() != null
									&& !ydcomBean1.getCheckValue().equals("")) {
								selectedSpinner(SpinnerUtilBean.getSblx(),
										ydcomBean1.getCheckValue(), yd_sblx1);
							}
//							yd_sblx1.setClickable(true);
//						} 

						selectedvaleSpinner(ppsjList, ydcomBean1.getLinkCode(),
								yd_ppsj1);
					}
					if (i == 1) {
						linearyys2.setVisibility(View.VISIBLE);
						ydcomBean2 = ydlist.get(i);
						tv2.setText("中国移动");
						if (ydcomBean2.getXs() != null
								&& !ydcomBean2.getXs().equals("")
								&& !ydcomBean2.getXs().equals("null")) {
							yd_ttxs2.setText(ydcomBean2.getXs());
						} else {
							yd_ttxs2.setText("无");
						}
						if (ydcomBean2.getLx() != null
								&& !ydcomBean2.getLx().equals("")
								&& !ydcomBean2.getLx().equals("null")) {
							yd_ttlx2.setText(ydcomBean2.getLx());
						} else {
							yd_ttlx2.setText("无");
						}
						if (String.valueOf(ydcomBean2.getTg()) != null
								&& !String.valueOf(ydcomBean2.getTg()).equals(
										"")
										&& !String.valueOf(ydcomBean2.getTg()).equals(
												"null")) {
							yd_tg2.setText(ydcomBean2.getTg() + "");
						} else {
							yd_tg2.setText("无");
						}
//						if (comFlag) {
//							ydcomBean2.setCheckValue("主资产");
//							yd_sblx2.setClickable(false);
//							yd_sblx2.setSelection(1);
//						} else{
							if (ydcomBean2.getCheckValue() != null
									&& !ydcomBean2.getCheckValue().equals("")) {
								selectedSpinner(SpinnerUtilBean.getSblx(),
										ydcomBean2.getCheckValue(), yd_sblx2);
							}
//							yd_sblx2.setClickable(true);
//						} 
						selectedvaleSpinner(ppsjList, ydcomBean2.getLinkCode(),
								yd_ppsj2);
					}
					if (i == 2) {
						linearyyd_newadd.setVisibility(View.VISIBLE);
						ydcomBean3 = ydlist.get(i);
						yd_tv.setText("中国移动");
						if (ydcomBean3.getXs() != null
								&& !ydcomBean3.getXs().equals("")
								&& !ydcomBean3.getXs().equals("null")) {
							yd_ttxs3.setText(ydcomBean3.getXs());
						} else {
							yd_ttxs3.setText("无");
						}
						if (ydcomBean3.getLx() != null
								&& !ydcomBean3.getLx().equals("")
								&& !ydcomBean3.getLx().equals("null")) {
							yd_ttlx3.setText(ydcomBean3.getLx());
						} else {
							yd_ttlx3.setText("无");
						}
						if (String.valueOf(ydcomBean3.getTg()) != null
								&& !String.valueOf(ydcomBean3.getTg()).equals(
										"")
										&& !String.valueOf(ydcomBean3.getTg()).equals(
												"null")) {
							yd_tg3.setText(ydcomBean3.getTg() + "");
						} else {
							yd_tg3.setText("无");
						}
//						if (comFlag) {
//							ydcomBean3.setCheckValue("主资产");
//							yd_sblx3.setClickable(false);
//							yd_sblx3.setSelection(1);
//						} else{
							if (ydcomBean3.getCheckValue() != null
									&& !ydcomBean3.getCheckValue().equals("")) {
								selectedSpinner(SpinnerUtilBean.getSblx(),
										ydcomBean3.getCheckValue(), yd_sblx3);
							}
//							yd_sblx3.setClickable(true);
//						} 
						selectedvaleSpinner(ppsjList, ydcomBean3.getLinkCode(),
								yd_ppsj3);

					}
				}
			}

			if (ltlist != null && ltlist.size() > 0) {
				for (int i = 0; i < ltlist.size(); i++) {
					if (i == 0) {
						linearyys3.setVisibility(View.VISIBLE);
						ltcomBean1 = ltlist.get(i);
						tv3.setText("中国联通");
						if (ltcomBean1.getXs() != null
								&& !ltcomBean1.getXs().equals("")
								&& !ltcomBean1.getXs().equals("null")) {
							lt_ttxs1.setText(ltcomBean1.getXs());
						} else {
							lt_ttxs1.setText("无");
						}
						if (ltcomBean1.getLx() != null
								&& !ltcomBean1.getLx().equals("")
								&& !ltcomBean1.getLx().equals("null")) {
							lt_ttlx1.setText(ltcomBean1.getLx());
						} else {
							lt_ttlx1.setText("无 ");
						}
						if (String.valueOf(ltcomBean1.getTg()) != null
								&& !String.valueOf(ltcomBean1.getTg()).equals(
										"")
										&& !String.valueOf(ltcomBean1.getTg()).equals(
												"null")) {
							lt_tg1.setText(ltcomBean1.getTg() + "");
						} else {
							lt_tg1.setText("无 ");
						}

//						if (comFlag) {
//							ltcomBean1.setCheckValue("主资产");
//							lt_sblx1.setClickable(false);
//							lt_sblx1.setSelection(1);
//						} else{
							if (ltcomBean1.getCheckValue() != null
									&& !ltcomBean1.getCheckValue().equals("")) {
								selectedSpinner(SpinnerUtilBean.getSblx(),
										ltcomBean1.getCheckValue(), lt_sblx1);
							}
//							lt_sblx1.setClickable(true);
//						} 
						selectedvaleSpinner(ppsjList, ltcomBean1.getLinkCode(),
								lt_ppsj1);
					}
					if (i == 1) {
						linearyys4.setVisibility(View.VISIBLE);
						ltcomBean2 = ltlist.get(i);
						tv4.setText("中国联通");
						if (ltcomBean2.getXs() != null
								&& !ltcomBean2.getXs().equals("")
								&& !ltcomBean2.getXs().equals("null")) {
							lt_ttxs2.setText(ltcomBean2.getXs());
						} else {
							lt_ttxs2.setText("无");
						}
						if (ltcomBean2.getLx() != null
								&& !ltcomBean2.getLx().equals("")
								&& !ltcomBean2.getLx().equals("null")) {
							lt_ttlx2.setText(ltcomBean2.getLx());
						} else {
							lt_ttlx2.setText("无 ");
						}
						if (String.valueOf(ltcomBean2.getTg()) != null
								&& !String.valueOf(ltcomBean2.getTg()).equals(
										"")
										&& !String.valueOf(ltcomBean2.getTg()).equals(
												"null")) {
							lt_tg2.setText(ltcomBean2.getTg() + "");
						} else {
							lt_tg2.setText("无 ");
						}
//						if (comFlag) {
//							ltcomBean2.setCheckValue("主资产");
//							lt_sblx2.setClickable(false);
//							lt_sblx2.setSelection(1);
//						} else{
							if (ltcomBean2.getCheckValue() != null
									&& !ltcomBean2.getCheckValue().equals("")) {
								selectedSpinner(SpinnerUtilBean.getSblx(),
										ltcomBean2.getCheckValue(), lt_sblx2);
							}
//							lt_sblx2.setClickable(true);
//						} 
						selectedvaleSpinner(ppsjList, ltcomBean2.getLinkCode(),
								lt_ppsj2);
					}
					if (i == 2) {
						linearylt_newadd.setVisibility(View.VISIBLE);
						ltcomBean3 = ltlist.get(i);
						lt_tv.setText("中国联通");
						if (ltcomBean3.getXs() != null
								&& !ltcomBean3.getXs().equals("")
								&& !ltcomBean3.getXs().equals("null")) {
							lt_ttxs3.setText(ltcomBean3.getXs());
						} else {
							lt_ttxs3.setText("无");
						}
						if (ltcomBean3.getLx() != null
								&& !ltcomBean3.getLx().equals("")
								&& !ltcomBean3.getLx().equals("null")) {
							lt_ttlx3.setText(ltcomBean3.getLx());
						} else {
							lt_ttlx3.setText("无 ");
						}
						if (String.valueOf(ltcomBean3.getTg()) != null
								&& !String.valueOf(ltcomBean3.getTg()).equals(
										"")
										&& !String.valueOf(ltcomBean3.getTg()).equals(
												"null")) {
							lt_tg3.setText(ltcomBean3.getTg() + "");
						} else {
							lt_tg3.setText("无 ");
						}
//						if (comFlag) {
//							ltcomBean3.setCheckValue("主资产");
//							lt_sblx3.setClickable(false);
//							lt_sblx3.setSelection(1);
//						} else{
							if (ltcomBean3.getCheckValue() != null
									&& !ltcomBean3.getCheckValue().equals("")) {
								selectedSpinner(SpinnerUtilBean.getSblx(),
										ltcomBean3.getCheckValue(), lt_sblx3);
							}
//							lt_sblx3.setClickable(true);
//						} 
						selectedvaleSpinner(ppsjList, ltcomBean3.getLinkCode(),
								lt_ppsj3);
					}
				}
			}

			if (dxlist != null && dxlist.size() > 0) {
				for (int i = 0; i < dxlist.size(); i++) {
					if (i == 0) {
						linearyys5.setVisibility(View.VISIBLE);
						dxcomBean1 = dxlist.get(i);
						tv5.setText("中国电信");
						if (dxcomBean1.getXs() != null
								&& !dxcomBean1.getXs().equals("")
								&& !dxcomBean1.getXs().equals("null")) {
							dx_ttxs1.setText(dxcomBean1.getXs());
						} else {
							dx_ttxs1.setText("无");
						}
						if (dxcomBean1.getLx() != null
								&& !dxcomBean1.getLx().equals("")
								&& !dxcomBean1.getLx().equals("null")) {
							dx_ttlx1.setText(dxcomBean1.getLx());
						} else {
							dx_ttlx1.setText("无");
						}
						if (String.valueOf(dxcomBean1.getTg()) != null
								&& !String.valueOf(dxcomBean1.getTg()).equals(
										"")
										&& !String.valueOf(dxcomBean1.getTg()).equals(
												"null")) {
							dx_tg1.setText(dxcomBean1.getTg() + "");
						} else {
							dx_tg1.setText("无");
						}

//						if (comFlag) {
//							dxcomBean1.setCheckValue("主资产");
//							dx_sblx1.setClickable(false);
//							dx_sblx1.setSelection(1);
//						} else{
							if (dxcomBean1.getCheckValue() != null
									&& !dxcomBean1.getCheckValue().equals("")) {
								selectedSpinner(SpinnerUtilBean.getSblx(),
										dxcomBean1.getCheckValue(), dx_sblx1);
							}
//							dx_sblx1.setClickable(true);
//						} 
						selectedvaleSpinner(ppsjList, dxcomBean1.getLinkCode(),
								dx_ppsj1);
					}
					if (i == 1) {
						linearyys6.setVisibility(View.VISIBLE);
						dxcomBean2 = dxlist.get(i);
						tv6.setText("中国电信");
						if (dxcomBean2.getXs() != null
								&& !dxcomBean2.getXs().equals("")
								&& !dxcomBean2.getXs().equals("null")) {
							dx_ttxs2.setText(dxcomBean2.getXs());
						} else {
							dx_ttxs2.setText("无");
						}
						if (dxcomBean2.getLx() != null
								&& !dxcomBean2.getLx().equals("")
								&& !dxcomBean2.getLx().equals("null")) {
							dx_ttlx2.setText(dxcomBean2.getLx());
						} else {
							dx_ttlx2.setText("无");
						}
						if (String.valueOf(dxcomBean2.getTg()) != null
								&& !String.valueOf(dxcomBean2.getTg()).equals(
										"")
										&& !String.valueOf(dxcomBean2.getTg()).equals(
												"null")) {
							dx_tg2.setText(dxcomBean2.getTg() + "");
						} else {
							dx_tg2.setText("无");
						}
//						if (comFlag) {
//							dxcomBean2.setCheckValue("主资产");
//							dx_sblx2.setClickable(false);
//							dx_sblx2.setSelection(1);
//						} else{
							if (dxcomBean2.getCheckValue() != null
									&& !dxcomBean2.getCheckValue().equals("")) {
								selectedSpinner(SpinnerUtilBean.getSblx(),
										dxcomBean2.getCheckValue(), dx_sblx2);
							}
//							dx_sblx2.setClickable(true);
//						} 
						selectedvaleSpinner(ppsjList, dxcomBean2.getLinkCode(),
								dx_ppsj2);
					}
					if (i == 2) {
						linearydx_newadd.setVisibility(View.VISIBLE);
						dxcomBean3 = dxlist.get(i);
						dx_tv.setText("中国电信");
						if (dxcomBean3.getXs() != null
								&& !dxcomBean3.getXs().equals("")
								&& !dxcomBean3.getXs().equals("null")) {
							dx_ttxs3.setText(dxcomBean3.getXs());
						} else {
							dx_ttxs3.setText("无");
						}
						if (dxcomBean3.getLx() != null
								&& !dxcomBean3.getLx().equals("")
								&& !dxcomBean3.getLx().equals("null")) {
							dx_ttlx3.setText(dxcomBean3.getLx());
						} else {
							dx_ttlx3.setText("无");
						}
						if (String.valueOf(dxcomBean3.getTg()) != null
								&& !String.valueOf(dxcomBean3.getTg()).equals(
										"")
										&& !String.valueOf(dxcomBean3.getTg()).equals(
												"null")) {
							dx_tg3.setText(dxcomBean3.getTg() + "");
						} else {
							dx_tg3.setText("无");
						}
//						if (comFlag) {
//							dxcomBean3.setCheckValue("主资产");
//							dx_sblx3.setClickable(false);
//							dx_sblx3.setSelection(1);
//						} else{
							if (dxcomBean3.getCheckValue() != null
									&& !dxcomBean3.getCheckValue().equals("")) {
								selectedSpinner(SpinnerUtilBean.getSblx(),
										dxcomBean3.getCheckValue(), dx_sblx3);
							}
//							dx_sblx3.setClickable(true);
//						} 
						selectedvaleSpinner(ppsjList, dxcomBean3.getLinkCode(),
								dx_ppsj3);
					}
				}
			}
		}
	}

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
			if (o.getText().equals(str)) {
				spinner.setSelection(fillAdapter(option).getPosition(o), true);
				break;
			}
		}
	}

	/**
	 * 默认选中spinner的值(匹配数据)
	 * 
	 * @param option
	 * @param str
	 * @param spinner
	 */
	private void selectedvaleSpinner(List<Option> option, String str,
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

	/**
	 * 二维码扫描返回信息
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String text = "";
		if (data != null && requestCode == code1) {
			text = data.getExtras().getString(res_text);
			qrcode.setText(text);
		} else if (data != null && requestCode == code2) {
			text = data.getExtras().getString(res_text);
			qrcode2.setText(text);
		} else if (data != null && requestCode == code3) {
			text = data.getExtras().getString(res_text);
			qrcode3.setText(text);
		} else if (data != null && requestCode == SUCCESS) {

		}
	}

	private void scan(int codes) {
		if (checkApkExist(getApplicationContext(), "com.zxing.activity")) {
			ComponentName componentName = new ComponentName(
					"com.zxing.activity", "com.zxing.activity.CaptureActivity");
			Intent intent = new Intent();
			intent.setComponent(componentName);
			Bundle bundlea = new Bundle();
			bundlea.putInt(code_str, codes);
			intent.putExtras(bundlea);
			startActivityForResult(intent, codes);
		} else {
			checkApp();
		}
	}

	private void checkApp() {
		new PackageUtil(this, ApplicationData.appNames1,
				ApplicationData.appFiles1).install();
	}

	public boolean checkApkExist(Context context, String packageName) {
		if (packageName == null || "".equals(packageName))
			return false;
		try {
			ApplicationInfo info = context.getPackageManager()
					.getApplicationInfo(packageName,
							PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

	class DelPopupWindow extends PopupWindow {

		private Button del, query2;
		private View mMenuView;
		private TextView del_tv;

		public DelPopupWindow(Activity context, OnClickListener itemsOnClick) {
			super(context);
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mMenuView = inflater.inflate(R.layout.dialog_del, null);
			del_tv = (TextView) mMenuView.findViewById(R.id.del_tv);
			del_tv.setText("将删除此塔桅信息");
			del = (Button) mMenuView.findViewById(R.id.del);
			query2 = (Button) mMenuView.findViewById(R.id.query2);
			del.setOnClickListener(btnLis);
			query2.setOnClickListener(btnLis);
			query2.setText("取消");
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

	// @Override
	// protected void onResume() {
	// super.onResume();
	// relative_layout.setVisibility(View.VISIBLE);
	// loading.startAnimation(animation);
	// loadList();
	// }

}
