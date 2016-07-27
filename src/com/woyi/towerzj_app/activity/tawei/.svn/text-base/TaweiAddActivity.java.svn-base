package com.woyi.towerzj_app.activity.tawei;

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
import com.woyi.towerzj_app.bean.tawei.AppMastBean;
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
 * 此类描述的是：添加塔桅
 * 
 * @author: 罗然
 * @version: 2015-7-18 上午9:59:37
 * @ClassName: TaweiAddActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity.tawei
 */
public class TaweiAddActivity extends ForwardActivity {

	private TextView title, cz;
	private Button back, save, question;
	private TextView code, address;

	private EditText zc_tg, zc_qrcode, zc_mc;
	private Spinner ttpt, zc_ttlx;
	private ImageView zc_qrcodeimg;

	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;

	private int code1 = 2015;
	private String res_text = "text";
	private String code_str = "code";
	private AppPhyInfoBean bean;
	private DatabaseHelper mOpenHelper;
	
	private int flags = 0;
	
	private String codeStr="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		bean = (AppPhyInfoBean) this.getIntent().getExtras().getSerializable("bean");
		flags = Integer.parseInt(this.getIntent().getExtras().getString("flag"));
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_tawei_add);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		title.setText("添加塔桅");
		cz.setVisibility(View.GONE);
		if (bean != null) {
			code.setText(bean.getPhysicAlias());
			address.setText(bean.getPhysicName());
		}
		UUID uuid = UUID.randomUUID();
		codeStr=uuid.toString();
		fillSpinnerAdapter(SpinnerUtilBean.getTtlx(), zc_ttlx, "请选择铁塔类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTtpt(), ttpt, "请选择铁塔平台");
		back.setOnClickListener(btnLis);
		save.setOnClickListener(btnLis);
		zc_qrcodeimg.setOnClickListener(btnLis);
		zc_tg.addTextChangedListener(new MWatcher());
		question.setOnClickListener(btnLis);

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
				zc_tg.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					zc_tg.setText(s);
					zc_tg.setSelection(s.length());
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
			case R.id.save:
//				if (TextUtils.isEmpty(zc_tg.getText().toString())) {
//					toastMessage("请输入塔高");
//					return;
//				}
//				relative_layout.setVisibility(View.VISIBLE);
//				loading.startAnimation(animation);
				// 保存塔桅数据
				saveData();
				
				break;
			case R.id.zc_qrcodeimg:
				scan();
				break;
			case R.id.question:
				Bundle bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_MAST);
				bundle.putString("code", codeStr);
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(),QuestionListActivity.class, bundle);
				break;
			}
		}

		/**
		 * 
		 * 此方法描述的是：保存数据
		 * 
		 * @Title: saveData
		 * @author: 罗然
		 * @return void 返回类型
		 * @version: 2015-7-21 上午10:27:59
		 */
		public void saveData() {
			AppMastBean taWeiBean = new AppMastBean();

			UUID uuid1 = UUID.randomUUID();
			taWeiBean.setId(uuid1.toString());
			taWeiBean.setCode(codeStr);
			taWeiBean.setPhysicCode(bean.getPhysicCode());
			taWeiBean.setLx(((Option) zc_ttlx.getSelectedItem())
					.getValue());
			if (TextUtils.isEmpty(zc_tg.getText().toString())) {
				toastMessage("塔高不能为空");
				return;
			}
			taWeiBean.setTg(Double.parseDouble(zc_tg.getText().toString()));
			taWeiBean.setCheckUserId(ApplicationData.user.getId()+"");
			taWeiBean.setEwm(zc_qrcode.getText().toString());
			taWeiBean.setXs(((Option)ttpt.getSelectedItem()).getText());
			SQLiteDatabase db = mOpenHelper.getReadableDatabase();
			try {
				TowerSQliteDbBean.insertMast(db, taWeiBean,2);
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
			//	toastMessage("添加成功！");
				back();
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
		if (data != null) {
			text = data.getExtras().getString(res_text);
		}
		if (data != null && requestCode == code1) {
			zc_qrcode.setText(text);
		}
	}

	private void scan() {
		if (checkApkExist(getApplicationContext(), "com.zxing.activity")) {
			ComponentName componentName = new ComponentName(
					"com.zxing.activity", "com.zxing.activity.CaptureActivity");
			Intent intent = new Intent();
			intent.setComponent(componentName);
			Bundle bundlea = new Bundle();
			bundlea.putInt(code_str, code1);
			intent.putExtras(bundlea);
			startActivityForResult(intent, code1);
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
		forwardIntent(getApplicationContext(), NewTaweiDetailActivity.class,
				bundle);
		finish();
	}
}
