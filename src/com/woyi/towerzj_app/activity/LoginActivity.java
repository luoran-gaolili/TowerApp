package com.woyi.towerzj_app.activity;

import java.io.File;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.menu.MainActivity;
import com.woyi.towerzj_app.bean.AppUserBean;
import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.ClearEditText;
import com.woyi.towerzj_app.util.FileUtils;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Function;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;

/**
 * 
 * �����������ǣ���½
 * 
 * @author: ��Ȼ
 * @version: 2015-7-17 ����11:52:01
 * @ClassName: LoginActivity
 * @��Ŀ�� towerzj_app
 * @����com.woyi.towerzj_app.activity
 */
public class LoginActivity extends ForwardActivity {

	private Button login;
	private TextView version;// , foot
	private ClearEditText account, pass;
	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	private String accountStr;
	private String passStr;
	private CheckBox remember;
	public static SharedPreferences sp;
	private String versionname;
	private int versioncode;

	private AppUserBean appUser;
	private Map<String,String> reMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_login);
		SysExitUtil.activityList.add(this);
		sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE
				| Context.MODE_MULTI_PROCESS);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		createDir();
		getVersionInfo();
		if (sp.getBoolean("ISCHECK", false)) {
			account.setText(sp.getString("USER_NAME", ""));
			pass.setText(sp.getString("USER_PASSWORD", ""));
			if ("".equals(sp.getString("USER_NAME", ""))
					&& "".equals(sp.getString("PASSWORD", ""))) {
				remember.setChecked(false);
			} else {
				remember.setChecked(true);
			}
		}
		version.setText("V " + versionname);
		login.setOnClickListener(listener);

	}

	/**
	 * �����ļ�Ŀ¼
	 */
	private void createDir() {
		File tower = new File(FileUtils.getSDPATH() + ApplicationData.TOWER);// �����ļ����ŵ�Ŀ¼
		if (!tower.exists()) {
			tower.mkdirs();
		}
	}

	/**
	 * ��¼����
	 */
	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login:
				forwardIntent(getApplicationContext(), MainActivity.class);
				accountStr = account.getText().toString();
				passStr = pass.getText().toString();
				if (accountStr == null || ("").equals(accountStr)) {
					toastMessage("�������û���");
					return;
				}
				if (passStr == null || ("").equals(passStr)) {
					toastMessage("����������");
					return;
				}
				relative_layout.setVisibility(View.VISIBLE);
				loading.startAnimation(animation);
			//	ApplicationData.getExecutorService().submit(loginRun);
				break;
			}

		}
	};

	// 9 �û��������ڣ�0 ���벻��ȷ�� 1 �ɹ�

	private Runnable loginRun = new Runnable() {

		@Override
		public void run() {
			try {
				String result = Function.login(accountStr, passStr);
				ObjectMapper om = new ObjectMapper();
				appUser = om.readValue(result, TypeFactory
						.fromTypeReference(new TypeReference<AppUserBean>() {
						}));
				if (appUser.getExcuteStatue() != -1) {
					if (appUser.getExcuteStatue() == 1) {
						handler.obtainMessage(1).sendToTarget();
					} else if (appUser.getExcuteStatue() == 9) {
						handler.obtainMessage(0).sendToTarget();
					} else if (appUser.getExcuteStatue() == 0) {
						handler.obtainMessage(2).sendToTarget();
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
	 * �̼߳������ݺ󷵻ص���Ϣ
	 */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			relative_layout.setVisibility(View.GONE);
			loading.clearAnimation();
			switch (msg.what) {
			case 1:
				reMap=appUser.getReMap();
				if(reMap!=null && reMap.size()>0){
					ApplicationData.reMap=reMap;
				}
				Editor editor = sp.edit();
				editor.putBoolean("ISCHECK", true).commit();
				if (remember.isChecked()) {
					editor.putString("USER_NAME", account.getText().toString());

					editor.putString("USER_PASSWORD", pass.getText().toString());

					editor.putString("PASSWORD", pass.getText().toString());

				} else {
					editor.putString("USER_NAME", "");

					editor.putString("USER_PASSWORD", "");

					editor.putString("PASSWORD", "");

				}
				editor.commit();
				ApplicationData.user = appUser;
				forwardIntent(getApplicationContext(), MainActivity.class);
				finish();
				break;
			case 0:
				toastMessage("���û������ڣ�");
				break;
			case 2:
				toastMessage("�������");
				break;
			case -1:
				toastMessage("����ʧ�ܣ�");
				break;
			}
		}
	};

	/**
	 * ��ð汾��Ϣ
	 */
	private void getVersionInfo() {
		PackageManager pm = this.getPackageManager();
		PackageInfo pi;
		try {
			pi = pm.getPackageInfo(this.getPackageName(), 0);
			versionname = pi.versionName;
			versioncode = pi.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}
	

}
