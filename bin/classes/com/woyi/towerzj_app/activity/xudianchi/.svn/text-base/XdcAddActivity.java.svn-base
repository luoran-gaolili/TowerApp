package com.woyi.towerzj_app.activity.xudianchi;

import java.util.List;
import java.util.UUID;

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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
import android.widget.TextView;

import com.woyi.towerzj_app.activity.QuestionListActivity;
import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.bean.Option;
import com.woyi.towerzj_app.bean.SpinnerUtilBean;
import com.woyi.towerzj_app.bean.xudianchi.AppBatteryBean;
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
 * 此类描述的是：蓄电池
 * 
 * @author: 罗然
 * @version: 2015-7-18 上午11:55:49
 * @ClassName: XdcAddActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity.xudianchi
 */
public class XdcAddActivity extends ForwardActivity {

	private TextView title, cz;
	private Button back, question, save;
	private TextView bh, code, address;

	private EditText addtext1, rongl, qrcode, zc_mc;
	private Spinner pinp;
	private ImageView qrcodeimg;

	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;

	private int code1 = 2015;
	private String res_text = "text";
	private String code_str = "code";
	private AppPhyInfoBean bean;
	private DatabaseHelper mOpenHelper;
	private int flags = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		flags = Integer.parseInt(this.getIntent().getExtras().getString("flag"));
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_xdc_add);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		title.setText("添加蓄电池");
		cz.setVisibility(View.GONE);
		if (bean != null) {
			code.setText(bean.getPhysicAlias());
			address.setText(bean.getPhysicName());
		}
		fillSpinnerAdapter(SpinnerUtilBean.getXdcpp(), pinp, "请选择蓄电池品牌");
		rongl.setText("0.0");
		rongl.addTextChangedListener(new MWatcher());
		back.setOnClickListener(btnLis);
		question.setOnClickListener(btnLis);
		save.setOnClickListener(btnLis);
		qrcodeimg.setOnClickListener(btnLis);
		pinp.setOnItemSelectedListener(listener);
	}

	/**
	 * 输入框输入变化监视方法
	 * 
	 * @author Administrator
	 * 
	 */
	private class MWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// 判断小数点后输入数字的位数
			if (s.toString().startsWith(".")) {
				rongl.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					rongl.setText(s);
					rongl.setSelection(s.length());
				}
			}

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

	}

	private View.OnClickListener btnLis = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			int id = arg0.getId();
			switch (id) {
			case R.id.back:
				back();
				break;
			case R.id.question:
				Bundle bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_BATTERY);
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.save:
				// 保存数据
				savaData();

				break;
			case R.id.qrcodeimg:
				scan(code1);
				break;
			}
		}
	};

	/**
	 * 蓄电池品牌点击事件
	 */
	private AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) pinp.getSelectedItem()).getText();
			if (str.equals("其他")) {
				addtext1.setVisibility(View.VISIBLE);
				addtext1.setText("其他");
			} else {
				addtext1.setVisibility(View.GONE);
				addtext1.setText("");
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	public void savaData() {
		// String str = ((Option) pinp.getSelectedItem()).getText();
		// if (str.equals("其他")) {
		// str = addtext1.getText().toString().trim();
		// if (str == null || str.equals("")) {
		// toastMessage("请输入蓄电池品牌");
		// return;
		// }
		// }
		// relative_layout.setVisibility(View.VISIBLE);
		// loading.startAnimation(animation);
		AppBatteryBean batterryBean = new AppBatteryBean();
		UUID uuid = UUID.randomUUID();
		batterryBean.setId(uuid.toString());
		UUID uuid1 = UUID.randomUUID();
		batterryBean.setCode(uuid1.toString());
		batterryBean.setPhysicCode(bean.getPhysicCode());
		batterryBean.setName(bean.getPhysicName());
		if (addtext1.getText().toString().trim() != null
				&& !addtext1.getText().toString().equals("")) {
			batterryBean.setPpCheck(addtext1.getText().toString().trim());
		}
		batterryBean.setPp(((Option) pinp.getSelectedItem()).getValue());

		if (TextUtils.isEmpty(rongl.getText().toString())) {
			toastMessage("容量不能为空");
			return;
		}
		batterryBean.setRl(Double.parseDouble(rongl.getText().toString()));
		batterryBean.setCheckUserId(ApplicationData.user.getId() + "");
		batterryBean.setEwm(qrcode.getText().toString());
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		try {
			TowerSQliteDbBean.insertBatterry(db, batterryBean, 2);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		} finally {
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
				back();
				// toastMessage("添加成功！");
				break;
			case -1:
				toastMessage("请求失败！");
				break;
			}
		}
	};

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
	
	/**
	 * 系统返回键
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			back();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	protected void back() {
		Bundle bundle = new Bundle();
		bundle.putSerializable("bean", bean);
		bundle.putString("flag", flags + "");
		forwardIntent(getApplicationContext(), NewXdcDetailActivity.class,
				bundle);
		finish();
	}

}
