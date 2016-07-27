package com.woyi.towerzj_app.activity.kaiguandy;

import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.QuestionListActivity;
import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.bean.Option;
import com.woyi.towerzj_app.bean.SpinnerUtilBean;
import com.woyi.towerzj_app.bean.kgdy.AppSwitchBean;
import com.woyi.towerzj_app.bean.kgdy.AppSwitchComBean;
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
 * 此类描述的是：开关电源
 * 
 * @author: 罗然
 * @version: 2015-7-18 上午11:50:08
 * @ClassName: KgdyDetailActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity.kaiguandy
 */
public class KgdyDetailActivity extends ForwardActivity {

	private TextView title, cz,add;
	private Button back, question, save;
	private TextView code, address, result;

	private TextView zc_tv1, zc_tv2, zc_tv3, zc_tv4, zc_tv5, zc_tv6;
	private TableLayout table_yd1, table_yd2, table_lt1, table_lt2, table_dx1,
			table_dx2;

	private EditText bh, rongl, qrcode;
	private TextView yd_bh1, yd_pp1, yd_rl1, yd_bh2, yd_pp2, yd_rl2, lt_bh1,
			lt_pp1, lt_rl1, lt_bh2, lt_pp2, lt_rl2, dx_bh1, dx_pp1, dx_rl1,
			dx_bh2, dx_pp2, dx_rl2;

	private Spinner pinp, yd_sblx1, yd_sblx2, lt_sblx1, lt_sblx2, dx_sblx1,
			dx_sblx2;
	private ImageView qrcodeimg;

	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	private int code1 = 2015;
	private String res_text = "text";
	private String code_str = "code";

	private AppPhyInfoBean bean;
	private AppSwitchBean kgdybean;
	private List<AppSwitchComBean> list;

	private Bundle bundle;
	private DatabaseHelper mOpenHelper;
	private SQLiteDatabase db;
	private String rlTg = "容量信息无变化";
	private String reslutPp = "设备品牌无变化";
	private String rlStr;
	private String ppStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		kgdybean = (AppSwitchBean) this.getIntent().getExtras()
				.getSerializable("kgdybean");
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_kgdy_detail);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		cz.setText("删除");
		title.setText("开关电源");
		relative_layout.setVisibility(View.VISIBLE);
		loading.startAnimation(animation);
		loadComList();
		if (bean.getStatus().equals("2") || bean.getStatus().equals("3")
				|| bean.getStatus().equals("4")) {
			question.setVisibility(View.GONE);
			save.setVisibility(View.GONE);
			cz.setVisibility(View.GONE);
			add.setVisibility(View.GONE);
		}
		init();
		rongl.addTextChangedListener(new mTextWatcher());
		pinp.setOnItemSelectedListener(listener);
	}

	/**
	 * 品牌点击事件
	 */
	private AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) pinp.getSelectedItem()).getText();
			if (!str.equals(ppStr)) {
				reslutPp = "设备品牌已变化";
			} else {
				reslutPp = "设备品牌无变化";
			}
			result.setText(reslutPp + "\n" + rlTg);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 容量输入变化
	 * 
	 * @author Administrator
	 * 
	 */
	class mTextWatcher implements TextWatcher {

		// 文字变化前
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		// 文字变化时
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
			String str = rongl.getText().toString();
			if (str != null && !str.equals("")) {
				if (!rlStr.equals(str)) {
					rlTg = "容量信息有变化";
				} else {
					rlTg = "容量信息无变化";
				}
			} else {
				toastMessage("请输入容量");
			}
			result.setText(reslutPp + "\n" + rlTg);
		}
	}

	/**
	 * 
	 * 此方法描述的是：初始化数据
	 * 
	 * @Title: init
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-20 上午9:37:03
	 */
	private void init() {
		code.setText(bean.getPhysicAlias());
		address.setText(bean.getPhysicAddr());
		bh.setText(kgdybean.getCode());
		rlStr = kgdybean.getRl() + "";
		ppStr = kgdybean.getPp();
		rongl.setText(kgdybean.getRl() + "");
		fillSpinnerAdapter(SpinnerUtilBean.kgdypp(), pinp, "请选择品牌类型");
		if (kgdybean.getEwm() != null && !kgdybean.getEwm().equals("")) {
			qrcode.setText(kgdybean.getEwm());
		}
		back.setOnClickListener(btnLis);
		question.setOnClickListener(btnLis);
		save.setOnClickListener(btnLis);
		cz.setOnClickListener(btnLis);
		qrcodeimg.setOnClickListener(btnLis);
	}

	private View.OnClickListener btnLis = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			int id = arg0.getId();
			switch (id) {
			case R.id.back:
				finish();
				break;
			case R.id.question:
				bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_SWITCH);
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.save:// 保存
				savaData();
				break;
			case R.id.cz:// 删除
				SQLiteDatabase db = mOpenHelper.getReadableDatabase();
				try {
					// 开始事务
					db.beginTransaction();
					TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),
							kgdybean.getCode(),null, "05");
					db.setTransactionSuccessful();
					handler.obtainMessage(2).sendToTarget();
				} catch (Exception e) {
					handler.obtainMessage(-2).sendToTarget();
				} finally {
					db.endTransaction();
					db.close();
				}
				break;
			case R.id.zc_qrcodeimg:
				scan(code1);
				break;
			}
		}

		public void savaData() {
			if (rongl.getText().toString() == null
					&& rongl.getText().toString().equals("")) {
				toastMessage("请输入容量");
				return;
			}
			String yd_sblx1Dtr = ((Option) yd_sblx1.getSelectedItem())
					.getValue();
			String yd_sblx2Dtr = ((Option) yd_sblx2.getSelectedItem())
					.getValue();
			String lt_sblx1Dtr = ((Option) lt_sblx1.getSelectedItem())
					.getValue();
			String lt_sblx2Dtr = ((Option) lt_sblx2.getSelectedItem())
					.getValue();
			String dx_sblx1Dtr = ((Option) dx_sblx1.getSelectedItem())
					.getValue();
			String dx_sblx2Dtr = ((Option) dx_sblx2.getSelectedItem())
					.getValue();
			int y = 0;
			if (yd_sblx1Dtr.equals("2")) {
				y++;
			}
			if (yd_sblx2Dtr.equals("2")) {
				y++;
			}
			if (lt_sblx1Dtr.equals("2")) {
				y++;
			}
			if (lt_sblx2Dtr.equals("2")) {
				y++;
			}
			if (dx_sblx1Dtr.equals("2")) {
				y++;
			}
			if (dx_sblx2Dtr.equals("2")) {
				y++;
			}
			if (y == 0) {
				toastMessage("请选择一个运营商中设置主设备");
				return;
			}
			if (y > 1) {
				toastMessage("运营商中只能有一个作为主设备");
				return;
			}

			try {
				db.beginTransaction();
				AppSwitchBean switchBean = new AppSwitchBean();
				switchBean.setPhysicCode(bean.getPhysicCode());
				switchBean.setCode(kgdybean.getCode());
				switchBean.setPpCheck(((Option) pinp.getSelectedItem())
						.getValue());
				switchBean.setRlCheck(Double.parseDouble(rongl.getText()
						.toString()));

				switchBean.setCheckUserId(ApplicationData.user.getId() + "");
				switchBean.setEwm(qrcode.getText().toString());
				String yd_bh1Dtr = yd_bh1.getText().toString();
				String yd_bh2Dtr = yd_bh2.getText().toString();
				String lt_bh1Dtr = lt_bh1.getText().toString();
				String lt_bh2Dtr = lt_bh2.getText().toString();
				String dx_bh1Dtr = dx_bh1.getText().toString();
				String dx_bh2Dtr = dx_bh2.getText().toString();
				AppSwitchComBean comBean = null;
				db = mOpenHelper.getReadableDatabase();
				db.beginTransaction();

				if (yd_bh1Dtr != null && !yd_bh1Dtr.equals("")) {
					comBean = new AppSwitchComBean();
					comBean.setCode(yd_bh1Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) yd_sblx1.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateSwitchComData(db, comBean);
				}
				if (yd_bh2Dtr != null && !yd_bh2Dtr.equals("")) {
					comBean = new AppSwitchComBean();
					comBean.setCode(yd_bh2Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) yd_sblx2.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateSwitchComData(db, comBean);
				}
				if (lt_bh1Dtr != null && !lt_bh1Dtr.equals("")) {
					comBean = new AppSwitchComBean();
					comBean.setCode(lt_bh1Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) lt_sblx1.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateSwitchComData(db, comBean);
				}
				if (lt_bh2Dtr != null && !lt_bh2Dtr.equals("")) {
					comBean = new AppSwitchComBean();
					comBean.setCode(lt_bh2Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) lt_sblx2.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateSwitchComData(db, comBean);
				}
				if (dx_bh1Dtr != null && !dx_bh1Dtr.equals("")) {
					comBean = new AppSwitchComBean();
					comBean.setCode(dx_bh1Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) dx_sblx1.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateSwitchComData(db, comBean);
				}
				if (dx_bh2Dtr != null && !dx_bh2Dtr.equals("")) {
					comBean = new AppSwitchComBean();
					comBean.setCode(dx_bh2Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) dx_sblx2.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateSwitchComData(db, comBean);
				}
				TowerSQliteDbBean.updateSwitchData(db, switchBean);
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
	};

	/**
	 * 
	 * 此方法描述的是：运营商列表
	 * 
	 * @Title: loadComList
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-21 下午2:09:40
	 */
	private void loadComList() {
		list = new ArrayList<AppSwitchComBean>();
		String sql = "select * from ts_switch where physicCode='"
				+ bean.getPhysicCode() + "' and code='" + kgdybean.getCode()
				+ "'";
		try {
			list = TowerSQliteDbBean.querySwitchComData(mOpenHelper, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
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
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), yd_sblx2,
						"请选择设备类型");
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), yd_sblx1,
						"请选择设备类型");
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), lt_sblx2,
						"请选择设备类型");
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), lt_sblx1,
						"请选择设备类型");
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), dx_sblx2,
						"请选择设备类型");
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), dx_sblx1,
						"请选择设备类型");
				if (list != null && list.size() > 0) {
					loadSwitchData();
				} else {
					// toastMessage("没有相关数据！");
				}
				break;
			case -1:
				toastMessage("请求失败！");
				finish();
				break;
			case 2:
				// toastMessage("删除成功");
				finish();
				break;
			case -2:
				// toastMessage("删除失败");
				break;
			case 4:
				// toastMessage("更新成功");
				finish();
				break;
			case -4:
				toastMessage("更新失败");
				// break;
			}
		}
	};

	/**
	 * 
	 * 此方法描述的是：填充运营商数据
	 * 
	 * @Title: loadBatteryData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-19 下午7:01:07
	 */
	private void loadSwitchData() {

		for (AppSwitchComBean o : list) {
			if (o.getType().equals("01")) {// 移动
				if (yd_bh1.getText().toString() != null
						&& !yd_bh1.getText().toString().equals("")) {
					zc_tv2.setVisibility(View.VISIBLE);
					table_yd2.setVisibility(View.VISIBLE);
					yd_bh2.setText(o.getCode());
					yd_rl2.setText(o.getRl() + "");
					yd_pp2.setText(o.getPp());
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), yd_sblx2);
					}
				} else {
					zc_tv1.setVisibility(View.VISIBLE);
					table_yd1.setVisibility(View.VISIBLE);
					yd_bh1.setText(o.getCode());
					yd_rl1.setText(o.getRl() + "");
					yd_pp1.setText(o.getPp());
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), yd_sblx1);
					}
				}
			} else if (o.getType().equals("02")) {// 联通
				if (lt_bh1.getText().toString() != null
						&& !lt_bh1.getText().toString().equals("")) {
					zc_tv4.setVisibility(View.VISIBLE);
					table_lt2.setVisibility(View.VISIBLE);
					lt_bh2.setText(o.getCode());
					lt_rl2.setText(o.getRl() + "");
					lt_pp2.setText(o.getPp());
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), lt_sblx2);
					}
				} else {
					zc_tv3.setVisibility(View.VISIBLE);
					table_lt1.setVisibility(View.VISIBLE);
					lt_bh1.setText(o.getCode());
					lt_rl1.setText(o.getRl() + "");
					lt_pp1.setText(o.getPp());
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), lt_sblx1);
					}
				}
			} else if (o.getType().equals("03")) {// 电信
				if (dx_bh1.getText().toString() != null
						&& !dx_bh1.getText().toString().equals("")) {
					zc_tv6.setVisibility(View.VISIBLE);
					table_dx2.setVisibility(View.VISIBLE);
					dx_bh2.setText(o.getCode());
					dx_rl2.setText(o.getRl() + "");
					dx_pp2.setText(o.getPp());
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), dx_sblx2);
					}
				} else {
					zc_tv5.setVisibility(View.VISIBLE);
					table_dx1.setVisibility(View.VISIBLE);
					dx_bh1.setText(o.getCode());
					dx_bh1.setText(o.getCode());
					dx_rl1.setText(o.getRl() + "");
					dx_pp1.setText(o.getPp());
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), dx_sblx1);
					}
				}
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String text = "";
		if (data != null && requestCode == code1) {
			text = data.getExtras().getString(res_text);
			qrcode.setText(text);
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

}
