package com.woyi.towerzj_app.activity.tawei;

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
public class TaweiDetailActivity extends ForwardActivity {

	private TextView title, cz;
	private Button back, question, save;
	private TextView code, address, result;

	private TextView zc_bh, zc_tv1, zc_tv2, zc_tv3, zc_tv4, zc_tv5, zc_tv6;
	private TableLayout table_yd1, table_yd2, table_lt1, table_lt2, table_dx1,
	table_dx2;

	private EditText zc_tg, zc_qrcode;
	private TextView zc_yd_bh1, zc_yd_ttxs1, zc_yd_ttlx1, zc_yd_tg1, zc_yd_bh2,
	zc_yd_ttxs2, zc_yd_ttlx2, zc_yd_tg2, zc_lt_bh1, zc_lt_ttxs1,
	zc_lt_ttlx1, zc_lt_tg1, zc_lt_bh2, zc_lt_ttxs2, zc_lt_ttlx2,
	zc_lt_tg2, zc_dx_bh1, zc_dx_ttxs1, zc_dx_ttlx1, zc_dx_tg1,
	zc_dx_bh2, zc_dx_ttxs2, zc_dx_ttlx2, zc_dx_tg2;
	private Spinner zc_ttxs, zc_ttlx, zc_yd_sblx1, zc_yd_sblx2, zc_lt_sblx1,
	zc_lt_sblx2, zc_dx_sblx1, zc_dx_sblx2;
	private ImageView zc_qrcodeimg;

	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;

	private int code1 = 2015;
	private String res_text = "text";
	private String code_str = "code";
	private AppMastBean mastbean;
	private List<AppMastComBean> list;
	private AppPhyInfoBean bean;

	private Bundle bundle;
	private DatabaseHelper mOpenHelper;
	private SQLiteDatabase db;
	private String lxStr, tgStr, reslutLx = "铁塔类型无变化", reslutTg = "塔高无变化";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		mastbean = (AppMastBean) this.getIntent().getExtras()
				.getSerializable("mastbean");
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_tawei_detail);
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
		loadComList();
		cz.setText("删除");
		if (bean.getStatus().equals("2") || bean.getStatus().equals("3")
				|| bean.getStatus().equals("4")) {
			question.setVisibility(View.GONE);
			save.setVisibility(View.GONE);
			cz.setVisibility(View.GONE);
		}
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
		code.setText(bean.getPhysicAlias());
		address.setText(bean.getPhysicAddr());
		zc_bh.setText(mastbean.getCode());
		if(mastbean.getLxCheck()!=null &&!mastbean.getLxCheck().equals("")&&!mastbean.getLxCheck().equals("null")){
			lxStr=mastbean.getLxCheck();
		}else{
			lxStr=mastbean.getLx();
		}
		if(mastbean.getTgCheck()!=0.0){
			tgStr=mastbean.getTgCheck() + "";
		}else{
			tgStr=mastbean.getTg() + "";
		}
		zc_tg.setText(tgStr);
		if (mastbean.getEwm() != null && !mastbean.getEwm().equals("")
				&& !mastbean.getEwm().equals("null")) {
			zc_qrcode.setText(mastbean.getEwm());
		} else {
			zc_qrcode.setText("");
		}
		fillSpinnerAdapter(SpinnerUtilBean.getTtlx(), zc_ttlx, "请选择铁塔类型");
		if (lxStr != null && !lxStr.equals("")) {
			selectedSpinner(SpinnerUtilBean.getTtlx(), lxStr, zc_ttlx);
		}
		result.setText(reslutLx + "\n" + reslutTg);
		back.setOnClickListener(btnLis);
		question.setOnClickListener(btnLis);
		save.setOnClickListener(btnLis);
		cz.setOnClickListener(btnLis);
		zc_qrcodeimg.setOnClickListener(btnLis);
		zc_ttlx.setOnItemSelectedListener(listener);
		zc_tg.addTextChangedListener(new mTextWatcher());
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
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_MAST);
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.save:// 保存
				saveData();
				break;
			case R.id.cz:// 删除
				SQLiteDatabase db = mOpenHelper.getReadableDatabase();
				try {
					// 开始事务
					db.beginTransaction();
					TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),mastbean.getCode(),null, "01");
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
	};

	/**
	 * 铁塔类型点击事件
	 */
	private AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) zc_ttlx.getSelectedItem()).getText();
			if (!str.equals(lxStr)) {
				reslutLx = "铁塔类型有变化";
			} else {
				reslutLx = "铁塔类型无变化";
			}
			result.setText(reslutLx + "\n" + reslutTg);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

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
			String str = zc_tg.getText().toString();
			if (str != null && !str.equals("")) {
				if (!tgStr.equals(str)) {
					reslutTg = "塔高信息有变化";
				} else {
					reslutTg = "塔高信息无变化";
				}
			} else {
				toastMessage("请输入塔高");
			}
			result.setText(reslutLx + "\n" + reslutTg);
		}
	}

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
		list = new ArrayList<AppMastComBean>();
		String sql = "select * from ts_mast where physicCode='"
				+ bean.getPhysicCode() + "'";// and code='"+mastbean.getCode()+"'
		try {
			list = TowerSQliteDbBean.queryMastComData(mOpenHelper, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}
	}

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
		if (zc_tg.getText().toString() == null
				&& zc_tg.getText().toString().equals("")) {
			toastMessage("请输入塔高");
			return;
		}

		String yd_sblx1 = ((Option) zc_yd_sblx1.getSelectedItem()).getValue();
		String yd_sblx2 = ((Option) zc_yd_sblx2.getSelectedItem()).getValue();
		String lt_sblx1 = ((Option) zc_lt_sblx1.getSelectedItem()).getValue();
		String lt_sblx2 = ((Option) zc_lt_sblx2.getSelectedItem()).getValue();
		String dx_sblx1 = ((Option) zc_dx_sblx1.getSelectedItem()).getValue();
		String dx_sblx2 = ((Option) zc_dx_sblx2.getSelectedItem()).getValue();
		int y = 0;

		if (yd_sblx1.equals("2")) {

			y++;
		}
		if (yd_sblx2.equals("2")) {
			y++;
		}
		if (lt_sblx1.equals("2")) {
			y++;
		}
		if (lt_sblx2.equals("2")) {
			y++;
		}
		if (dx_sblx1.equals("2")) {
			y++;
		}
		if (dx_sblx2.equals("2")) {
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
		// 更新数据库信息
		try {
			// 封装塔桅信息
			AppMastBean mastBean = new AppMastBean();
			mastBean.setPhysicCode(bean.getPhysicCode());
			mastBean.setCode(mastbean.getCode());

			mastBean.setLxCheck(((Option) zc_ttlx.getSelectedItem())
					.getValue());
			mastBean.setTgCheck(Double.parseDouble(zc_tg.getText()
					.toString()));
			mastBean.setCheckUserId(ApplicationData.user.getId() + "");

			mastBean.setLxCheck(((Option) zc_ttlx.getSelectedItem())
					.getValue());
			mastBean.setTgCheck(Double.parseDouble(zc_tg.getText()
					.toString()));
			mastBean.setCheckUserId(ApplicationData.user.getId() + "");

			mastBean.setEwm(zc_qrcode.getText().toString());

			// 封装塔桅运营商信息
			String yd_bh1 = zc_yd_bh1.getText().toString();
			String yd_bh2 = zc_yd_bh2.getText().toString();
			String lt_bh1 = zc_lt_bh1.getText().toString();
			String lt_bh2 = zc_lt_bh2.getText().toString();
			String dx_bh1 = zc_dx_bh1.getText().toString();
			String dx_bh2 = zc_dx_bh2.getText().toString();

			AppMastComBean comBean = null;
			relative_layout.setVisibility(View.VISIBLE);
			loading.startAnimation(animation);
			// 开始事务

			db = mOpenHelper.getReadableDatabase();
			db.beginTransaction();

			if (yd_bh1 != null && !yd_bh1.equals("")) {
				comBean = new AppMastComBean();

				comBean.setCode(yd_bh1);
				comBean.setPhysicCode(bean.getPhysicCode());
				comBean.setCheckValue(((Option) zc_yd_sblx1
						.getSelectedItem()).getText());
				TowerSQliteDbBean.updateMastComData(db, comBean);
			}
			if (yd_bh2 != null && !yd_bh2.equals("")) {
				comBean = new AppMastComBean();
				comBean.setCode(yd_bh2);
				comBean.setPhysicCode(bean.getPhysicCode());
				comBean.setCheckValue(((Option) zc_yd_sblx2
						.getSelectedItem()).getText());
				TowerSQliteDbBean.updateMastComData(db, comBean);
			}
			if (lt_bh1 != null && !lt_bh1.equals("")) {
				comBean = new AppMastComBean();
				comBean.setCode(lt_bh1);
				comBean.setPhysicCode(bean.getPhysicCode());
				comBean.setCheckValue(((Option) zc_lt_sblx1
						.getSelectedItem()).getText());
				TowerSQliteDbBean.updateMastComData(db, comBean);
			}
			if (lt_bh2 != null && !lt_bh2.equals("")) {
				comBean = new AppMastComBean();
				comBean.setCode(lt_bh2);
				comBean.setPhysicCode(bean.getPhysicCode());
				comBean.setCheckValue(((Option) zc_lt_sblx2
						.getSelectedItem()).getText());
				TowerSQliteDbBean.updateMastComData(db, comBean);
			}
			if (dx_bh1 != null && !dx_bh1.equals("")) {
				comBean = new AppMastComBean();
				comBean.setCode(dx_bh1);
				comBean.setPhysicCode(bean.getPhysicCode());
				comBean.setCheckValue(((Option) zc_dx_sblx1
						.getSelectedItem()).getText());
				TowerSQliteDbBean.updateMastComData(db, comBean);
			}
			if (dx_bh2 != null && !dx_bh2.equals("")) {
				comBean = new AppMastComBean();
				comBean.setCode(dx_bh2);
				comBean.setPhysicCode(bean.getPhysicCode());
				comBean.setCheckValue(((Option) zc_dx_sblx2
						.getSelectedItem()).getText());
				TowerSQliteDbBean.updateMastComData(db, comBean);
			}
			TowerSQliteDbBean.updateMastData(db, mastBean);
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
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), zc_yd_sblx2, "请选择设备类型");
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), zc_yd_sblx1, "请选择设备类型");
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), zc_lt_sblx2, "请选择设备类型");
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), zc_lt_sblx1, "请选择设备类型");
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), zc_dx_sblx2, "请选择设备类型");
				fillSpinnerAdapter(SpinnerUtilBean.getSblx(), zc_dx_sblx1, "请选择设备类型");
				if (list != null && list.size() > 0) {
					loadMastData();
				} else {
			//		toastMessage("没有运营商数据！");
				}
				break;
			case -1:
				toastMessage("请求失败！");
				finish();
				break;
			case 2:
			//	toastMessage("删除成功");
				finish();
				break;
			case -2:
			//	toastMessage("删除失败");
				break;
			case 4:
			//	toastMessage("更新成功");
				finish();
				break;
			case -4:
			//	toastMessage("更新失败");
				break;
			}
		}
	};

	/**
	 * 
	 * 此方法描述的是：填充运营商数据
	 * 
	 * @Title: loadMastData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-19 下午4:41:44
	 */
	private void loadMastData() {
		for (AppMastComBean o : list) {
			if (o.getType().equals("01")) {// 移动
				if (zc_yd_bh1.getText().toString() != null
						&& !zc_yd_bh1.getText().toString().equals("")) {
					zc_tv2.setVisibility(View.VISIBLE);
					table_yd2.setVisibility(View.VISIBLE);
					zc_yd_bh2.setText(o.getCode());
					zc_yd_ttxs2.setText(o.getXs());
					zc_yd_ttlx2.setText(o.getLx());
					zc_yd_tg2.setText(o.getTg() + "");
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), zc_yd_sblx2);
					}
				} else {
					zc_tv1.setVisibility(View.VISIBLE);
					table_yd1.setVisibility(View.VISIBLE);
					zc_yd_bh1.setText(o.getCode());
					zc_yd_ttxs1.setText(o.getXs());
					zc_yd_ttlx1.setText(o.getLx());
					zc_yd_tg1.setText(o.getTg() + "");
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), zc_yd_sblx1);
					}
				}
			} else if (o.getType().equals("02")) {// 联通
				if (zc_lt_bh1.getText().toString() != null
						&& !zc_lt_bh1.getText().toString().equals("")) {
					zc_tv4.setVisibility(View.VISIBLE);
					table_lt2.setVisibility(View.VISIBLE);
					zc_lt_bh2.setText(o.getCode());
					zc_lt_ttxs2.setText(o.getXs());
					zc_lt_ttlx2.setText(o.getLx());
					zc_lt_tg2.setText(o.getTg() + "");
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), zc_lt_sblx2);
					}
				} else {
					zc_tv3.setVisibility(View.VISIBLE);
					table_lt1.setVisibility(View.VISIBLE);
					zc_lt_bh1.setText(o.getCode());
					zc_lt_ttxs1.setText(o.getXs());
					zc_lt_ttlx1.setText(o.getLx());
					zc_lt_tg1.setText(o.getTg() + "");
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), zc_lt_sblx1);
					}
				}
			} else if (o.getType().equals("03")) {// 电信
				if (zc_dx_bh1.getText().toString() != null
						&& !zc_dx_bh1.getText().toString().equals("")) {
					zc_tv6.setVisibility(View.VISIBLE);
					table_dx2.setVisibility(View.VISIBLE);
					zc_dx_bh2.setText(o.getCode());
					zc_dx_ttxs2.setText(o.getXs());
					zc_dx_ttlx2.setText(o.getLx());
					zc_dx_tg2.setText(o.getTg() + "");
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), zc_dx_sblx2);
					}
				} else {
					zc_tv5.setVisibility(View.VISIBLE);
					table_dx1.setVisibility(View.VISIBLE);
					zc_dx_bh1.setText(o.getCode());
					zc_dx_ttxs1.setText(o.getXs());
					zc_dx_ttlx1.setText(o.getLx());
					zc_dx_tg1.setText(o.getTg() + "");
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), zc_dx_sblx1);
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
		String text = "";
		if (data != null && requestCode == code1) {
			text = data.getExtras().getString(res_text);
			zc_qrcode.setText(text);
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

}
