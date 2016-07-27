package com.woyi.towerzj_app.activity.jifang;

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
import com.woyi.towerzj_app.bean.jifang.AppRoomBean;
import com.woyi.towerzj_app.bean.jifang.AppRoomComBean;
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
 * 此类描述的是：机房
 * 
 * @author: 罗然
 * @version: 2015-7-18 上午11:47:59
 * @ClassName: JifangDetailActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity.jifang
 */
public class JifangDetailActivity extends ForwardActivity {

	private TextView title, cz;
	private Button back, question, save;
	private TextView code, address, result;

	private TextView zc_tv1, zc_tv2, zc_tv3, zc_tv4, zc_tv5, zc_tv6;
	private TableLayout table_yd1, table_yd2, table_lt1, table_lt2, table_dx1,
			table_dx2;

	private EditText bh, mj, qrcode;
	private TextView yd_bh1, yd_jfmj1, yd_jflx1, yd_jfjg1, yd_bh2, yd_jfmj2,
			yd_jflx2, yd_jfjg2, lt_bh1, lt_jflx1, lt_jfjg1, lt_jfmj1, lt_bh2,
			lt_jflx2, lt_jfjg2, lt_jfmj2, dx_bh1, dx_jflx1, dx_jfjg1, dx_jfmj1,
			dx_bh2, dx_jflx2, dx_jfjg2, dx_jfmj2;

	private Spinner jflx, jfjg, yd_sblx1, yd_sblx2, lt_sblx1, lt_sblx2,
			dx_sblx1, dx_sblx2;
	private ImageView qrcodeimg;

	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;

	private int code1 = 2015;
	private String res_text = "text";
	private String code_str = "code";

	private AppPhyInfoBean bean;
	private AppRoomBean roombean;
	private List<AppRoomComBean> list;

	private Bundle bundle;
	private DatabaseHelper mOpenHelper;
	private SQLiteDatabase db;
	
	private String lxStr;
	private String reslutLx = "机房类型无变化";
	private String mjStr;
	private String reslutMg = "面积无变化";
	private String jgStr;
	private String reslutJg = "机房结构无变化";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		roombean = (AppRoomBean) this.getIntent().getExtras()
				.getSerializable("roombean");
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		Reflex.loadViewForActivityOnCreate(this,
				R.layout.activity_jifang_detail);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		title.setText("机房");
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
		result.setText(reslutLx+"\n" +reslutJg+ "\n" + reslutMg);
		mj.addTextChangedListener(new mTextWatcher());
		jflx.setOnItemSelectedListener(listener);
		jfjg.setOnItemSelectedListener(listener1);
		init();
	}

	/**
	 * 机房类型点击事件
	 */
	private AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) jflx.getSelectedItem()).getText();
			if (!str.equals(lxStr)) {
				reslutLx = "机房类型已变化";
			} else {
				reslutLx = "机房类型无变化";
			}
			result.setText(reslutLx+"\n" +reslutJg+ "\n" + reslutMg);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};
	/**
	 * 机房结构点击事件
	 */
	private AdapterView.OnItemSelectedListener listener1 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) jfjg.getSelectedItem()).getText();
			if (!str.equals(jgStr)) {
				reslutJg = "机房结构已变化";
			} else {
				reslutJg = "机房结构无变化";
			}
			result.setText(reslutLx+"\n" +reslutJg+ "\n" + reslutMg);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 面积输入值变化
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
			String str = mj.getText().toString();
			if (str != null && !str.equals("")) {
				if (!mjStr.equals(str)) {
					reslutMg = "面积信息有变化";
				} else {
					reslutMg = "面积信息无变化";
				}
			} else {
				toastMessage("请输入机房面积");
			}
			result.setText(reslutLx+"\n" +reslutJg+ "\n" + reslutMg);
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
		list = new ArrayList<AppRoomComBean>();
		String sql = "select * from ts_room where physicCode='"
				+ bean.getPhysicCode() + "' ";//and code='"+roombean.getCode()+"'
		try {
			list = TowerSQliteDbBean.queryRoomComData(mOpenHelper, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}
	}

	private void init() {
		code.setText(bean.getPhysicAlias());
		address.setText(bean.getPhysicName());
		bh.setText(roombean.getCode());
		if(roombean.getLxCheck()!=null &&!roombean.getLxCheck().equals("") && !roombean.getLxCheck().equals("null")){
			lxStr=roombean.getLxCheck();
		}else{
			lxStr=roombean.getLx();
		}
		if(roombean.getJgCheck()!=null &&!roombean.getJgCheck().equals("") && !roombean.getJgCheck().equals("null")){
			jgStr=roombean.getJgCheck();
		}else{
			jgStr=roombean.getJg();
		}
		if(roombean.getMjCheck()!=0.0){
			mjStr=roombean.getMjCheck() + "";
		}else{
			mjStr=roombean.getMj() + "";
		}
		mj.setText(mjStr);
		if (roombean.getEwm() != null && !roombean.getEwm().equals("")
				&& !roombean.getEwm().equals("null")) {
			qrcode.setText(roombean.getEwm());
		} else {
			qrcode.setText("");
		}
		fillSpinnerAdapter(SpinnerUtilBean.getJfjg(), jfjg, "请选择机房结构");
		fillSpinnerAdapter(SpinnerUtilBean.getJflx(), jflx, "请选择机房类型");
		if (jgStr != null && !jgStr.equals("")) {
			selectedSpinner(SpinnerUtilBean.getJfjg(), jgStr, jfjg);
		}
		if (lxStr != null && !lxStr.equals("")) {
			selectedSpinner(SpinnerUtilBean.getJflx(), lxStr, jflx);
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
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_ROOM);
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.save:
				saveData();
				break;
			case R.id.cz:// 删除
				db = mOpenHelper.getReadableDatabase();
				try {
					// 开始事务
					db.beginTransaction();
					TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),roombean.getCode(),null, "02");
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

		// 封装更新对象
		public void saveData() {
			if (mj.getText().toString() == null
					&& mj.getText().toString().equals("")) {
				toastMessage("请输入面积");
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
			// 更新数据库信息
			try {
				AppRoomBean jiFangBean = new AppRoomBean();
				jiFangBean.setPhysicCode(bean.getPhysicCode());
				jiFangBean.setCode(roombean.getCode());
				jiFangBean.setLxCheck(((Option) jflx.getSelectedItem())
						.getValue());
				jiFangBean.setJgCheck(((Option) jfjg.getSelectedItem())
						.getValue());
				jiFangBean.setMjCheck(Double.parseDouble(mj.getText()
						.toString()));
				jiFangBean.setCheckUserId(ApplicationData.user.getId() + "");
				jiFangBean.setEwm(qrcode.getText().toString());
				// 封装运营商数据
				String yd_bh1Dtr = yd_bh1.getText().toString();
				String yd_bh2Dtr = yd_bh2.getText().toString();
				String lt_bh1Dtr = lt_bh1.getText().toString();
				String lt_bh2Dtr = lt_bh2.getText().toString();
				String dx_bh1Dtr = dx_bh1.getText().toString();
				String dx_bh2Dtr = dx_bh2.getText().toString();
				AppRoomComBean comBean = null;
				db = mOpenHelper.getReadableDatabase();
				db.beginTransaction();
				if (yd_bh1Dtr != null && !yd_bh1Dtr.equals("")) {
					comBean = new AppRoomComBean();
					comBean.setCode(yd_bh1Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) yd_sblx1.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateRoomComData(db, comBean);
				}
				if (yd_bh2Dtr != null && !yd_bh2Dtr.equals("")) {
					comBean = new AppRoomComBean();
					comBean.setCode(yd_bh2Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) yd_sblx2.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateRoomComData(db, comBean);
				}
				if (lt_bh1Dtr != null && !lt_bh1Dtr.equals("")) {
					comBean = new AppRoomComBean();
					comBean.setCode(lt_bh1Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) lt_sblx1.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateRoomComData(db, comBean);
				}
				if (lt_bh2Dtr != null && !lt_bh2Dtr.equals("")) {
					comBean = new AppRoomComBean();
					comBean.setCode(lt_bh2Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) lt_sblx2.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateRoomComData(db, comBean);
				}
				if (dx_bh1Dtr != null && !dx_bh1Dtr.equals("")) {
					comBean = new AppRoomComBean();
					comBean.setCode(dx_bh1Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) dx_sblx1.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateRoomComData(db, comBean);
				}
				if (dx_bh2Dtr != null && !dx_bh2Dtr.equals("")) {
					comBean = new AppRoomComBean();
					comBean.setCode(dx_bh2Dtr);
					comBean.setPhysicCode(bean.getPhysicCode());
					comBean.setCheckValue(((Option) dx_sblx2.getSelectedItem())
							.getText());
					TowerSQliteDbBean.updateRoomComData(db, comBean);
				}
				TowerSQliteDbBean.updateRoomData(db, jiFangBean);
				db.setTransactionSuccessful();
				handler.obtainMessage(4).sendToTarget();
			} catch (Exception e) {
				handler.obtainMessage(-4).sendToTarget();
			} finally {
				if(db!=null){
					db.endTransaction();
					db.close();
				}
			}
		}
	};

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
					loadRoomData();
				} else {
			//		toastMessage("没有相关运营商数据！");
				}
				break;
			case -1:
				toastMessage("请求失败！");
				finish();
				break;

			case 4:
			//	toastMessage("更新成功！");
				finish();
				break;
			case -4:
				//toastMessage("更新失败！");
				break;
			case 2:
				//toastMessage("删除成功");
				finish();
				break;
			case -2:
		//		toastMessage("删除失败");
				break;

			}
		}
	};

	/**
	 * 
	 * 此方法描述的是：填充运营商数据
	 * 
	 * @Title: loadRoomData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-19 下午5:42:30
	 */
	private void loadRoomData() {
		for (AppRoomComBean o : list) {
			if (o.getType().equals("01")) {// 移动
				if (yd_bh1.getText().toString() != null
						&& !yd_bh1.getText().toString().equals("")) {
					zc_tv2.setVisibility(View.VISIBLE);
					table_yd2.setVisibility(View.VISIBLE);
					yd_bh2.setText(o.getCode());
					yd_jfjg2.setText(o.getJg());
					yd_jflx2.setText(o.getLx());
					yd_jfmj2.setText(o.getMj() + "");
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), yd_sblx2);
					} 
				} else {
					zc_tv1.setVisibility(View.VISIBLE);
					table_yd1.setVisibility(View.VISIBLE);
					yd_bh1.setText(o.getCode());
					yd_jfjg1.setText(o.getJg());
					yd_jflx1.setText(o.getLx());
					yd_jfmj1.setText(o.getMj() + "");
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
					lt_jfjg2.setText(o.getJg());
					lt_jflx2.setText(o.getLx());
					lt_jfmj2.setText(o.getMj() + "");
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), lt_sblx2);
					}
				} else {
					zc_tv3.setVisibility(View.VISIBLE);
					table_lt1.setVisibility(View.VISIBLE);
					lt_bh1.setText(o.getCode());
					lt_jfjg1.setText(o.getJg());
					lt_jflx1.setText(o.getLx());
					lt_jfmj1.setText(o.getMj() + "");
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
					dx_jfjg2.setText(o.getJg());
					dx_jflx2.setText(o.getLx());
					dx_jfmj2.setText(o.getMj() + "");
					if (o.getCheckValue() != null
							&& !o.getCheckValue().equals("")) {
						selectedSpinner(SpinnerUtilBean.getSblx(),
								o.getCheckValue(), dx_sblx2);
					} 
				} else {
					zc_tv5.setVisibility(View.VISIBLE);
					table_dx1.setVisibility(View.VISIBLE);
					dx_bh1.setText(o.getCode());
					dx_jfjg1.setText(o.getJg());
					dx_jflx1.setText(o.getLx());
					dx_jfmj1.setText(o.getMj() + "");
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
