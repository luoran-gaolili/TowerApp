package com.woyi.towerzj_app.activity.kaiguandy;

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
import com.woyi.towerzj_app.bean.kgdy.AppSwitchBean;
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
 * @version: 2015-7-18 上午11:50:56
 * @ClassName: KgdyAddActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity.kaiguandy
 */
public class KgdyAddActivity extends ForwardActivity {

	private TextView title, cz;
	private Button back, question, save;
	private TextView code, address;

	private EditText bh, rongl, qrcode, edittext;
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
	private String codeStr = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_kgdy_add);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		fillSpinnerAdapter(SpinnerUtilBean.kgdypp(), pinp, "请选择品牌类型");
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		title.setText("添加开关电源");
		cz.setVisibility(View.GONE);
		if (bean != null) {
			code.setText(bean.getPhysicAlias());
			address.setText(bean.getPhysicName());
		}
		UUID uuid = UUID.randomUUID();
		codeStr = uuid.toString();
		rongl.addTextChangedListener(new MWatcher());
		back.setOnClickListener(btnLis);
		question.setOnClickListener(btnLis);
		save.setOnClickListener(btnLis);
		qrcodeimg.setOnClickListener(btnLis);
		pinp.setOnItemSelectedListener(listener);
	}

	/**
	 * 下拉框选择监视
	 */
	private AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String str = ((Option) pinp.getItemAtPosition(position)).getValue();
			if (str.equals("其他")) {
				edittext.setVisibility(View.VISIBLE);
				edittext.setText("其他");
			} else {
				edittext.setVisibility(View.GONE);
				edittext.setText("");
			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	};

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
				finish();
				break;
			case R.id.question:
				Bundle bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_SWITCH);
				bundle.putString("code", codeStr);
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.save:
				// 保存数据
				// relative_layout.setVisibility(View.VISIBLE);
				// loading.startAnimation(animation);
				saveData();
				break;
			case R.id.qrcodeimg:
				scan(code1);
				break;
			}
		}

		public void saveData() {
			AppSwitchBean switchBean = new AppSwitchBean();
			switchBean.setPhysicCode(bean.getPhysicCode());
			switchBean.setCode(codeStr);
			UUID uuid1 = UUID.randomUUID();
			switchBean.setId(uuid1.toString());
			switchBean.setName(bean.getPhysicName());
			if (edittext.getText().toString().trim() != null
					&& !edittext.getText().toString().trim().equals("")) {
				switchBean.setPpCheck(edittext.getText().toString().trim());
			}
			switchBean.setPp(((Option) pinp.getSelectedItem()).getValue());

			if (TextUtils.isEmpty(rongl.getText().toString())) {
				toastMessage("容量不能为空");
				return;
			}
			switchBean.setRl(Double.parseDouble(rongl.getText().toString()));
			switchBean.setEwm(qrcode.getText().toString());
			switchBean.setCheckUserId(ApplicationData.user.getId() + "");
			SQLiteDatabase db = mOpenHelper.getReadableDatabase();
			try {
				TowerSQliteDbBean.insertSwitch(db, switchBean, 2);
				handler.obtainMessage(1).sendToTarget();
			} catch (Exception e) {
				handler.obtainMessage(-1).sendToTarget();
			} finally {
				db.close();
				finish();
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

}
