package com.woyi.towerzj_app.activity.jifang;

import java.io.File;
import java.io.IOException;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.QuestionListActivity;
import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.bean.Option;
import com.woyi.towerzj_app.bean.SpinnerUtilBean;
import com.woyi.towerzj_app.bean.jifang.AppRoomBean;
import com.woyi.towerzj_app.bean.quedtion.AppProblemPicBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.MD5Check;
import com.woyi.towerzj_app.util.PackageUtil;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;

/**
 * 
 * �����������ǣ�����
 * 
 * @author: ��Ȼ
 * @version: 2015-7-18 ����11:48:44
 * @ClassName: JiFangAddActivity
 * @��Ŀ�� towerzj_app
 * @����com.woyi.towerzj_app.activity.jifang
 */
public class JiFangAddActivity extends ForwardActivity {

	private TextView title, cz;
	private Button back, question, save;
	private TextView bh, code, address;

	private EditText jfmj, qrcode, zc_mc,dbbh,dbs;
	private Spinner jflx, jfjg,ysjj;
	private CheckBox jfys_tyys,jfys_mjk,jfys_zyys,jfys_wyys;

	private ImageView qrcodeimg,dbbhimg;

	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;

	private int code1 = 2015;
	private String res_text = "text";
	private String code_str = "code";
	private AppPhyInfoBean bean;
	private DatabaseHelper mOpenHelper;
	private String codeStr = "";
	
	private int flags = 0;
	//���������
	public static final int SUCCESS = 1;
	private String dbbhName = "";
	private AppProblemPicBean proBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		flags = Integer.parseInt(this.getIntent().getExtras().getString("flag"));
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_jifang_add);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		SysExitUtil.activityList.add(this);
		init();
	}

	/**
	 * 
	* �˷����������ǣ���ʼ��ҳ������
	* @Title: init 
	* @author: ��Ȼ
	* @return void    ��������
	* @version: 2015-8-22
	����9:37:03
	 */
	private void init() {
		title.setText("��ӻ���");
		cz.setVisibility(View.GONE);
		if (bean != null) {
			code.setText(bean.getPhysicAlias());
			address.setText(bean.getPhysicName());
		}
		UUID uuid1 = UUID.randomUUID();
		codeStr = uuid1.toString();
		fillSpinnerAdapter(SpinnerUtilBean.getJflx(), jflx, "��ѡ���������");
		fillSpinnerAdapter(SpinnerUtilBean.getJfjg(), jfjg, "��ѡ������ṹ");
		fillSpinnerAdapter(SpinnerUtilBean.getRoomysjj(), ysjj, "��ѡ�����Կ���Ƿ񽻽�");
		jfmj.setText("0.0");
		jfmj.addTextChangedListener(new MWatcher());
		back.setOnClickListener(btnLis);
		question.setOnClickListener(btnLis);
		save.setOnClickListener(btnLis);
		qrcodeimg.setOnClickListener(btnLis);
		dbbhimg.setOnClickListener(btnLis);
		dbs.addTextChangedListener(new mTextWatcher());
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
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_ROOM);
				bundle.putString("code", codeStr);
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.save:// �����������
				saveData();
				break;
			case R.id.qrcodeimg:
				scan(code1);
				break;
			case R.id.dbbhimg://�����
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", "0");//0������ҳ�� 1�� 2��3����ϸҳ��
				Intent it = new Intent(getApplicationContext(), JiFangDbbhActivity.class);
				it.putExtras(bundle);
				JiFangAddActivity.this.startActivityForResult(it, SUCCESS);
				break;
			}
		}

		/**
		 * 
		 * �˷����������ǣ��������
		 * 
		 * @Title: saveData
		 * @author: ��Ȼ
		 * @return void ��������
		 * @version: 2015-7-21 ����10:11:23
		 */
		public void saveData() {
			AppRoomBean jiFangBean = new AppRoomBean();
			jiFangBean.setPhysicCode(bean.getPhysicCode());
			UUID uuid = UUID.randomUUID();
			jiFangBean.setId(uuid.toString());
			jiFangBean.setCode(codeStr);
			jiFangBean.setName(bean.getPhysicName());
			jiFangBean.setLx(((Option) jflx.getSelectedItem()).getValue());
			jiFangBean.setJg(((Option) jfjg.getSelectedItem()).getValue());
			if (TextUtils.isEmpty(jfmj.getText().toString())) {
				toastMessage("�������Ϊ��");
				return;
			}
			jiFangBean.setMj(Double.parseDouble(jfmj.getText().toString()));
			jiFangBean.setDbBh(dbbh.getText().toString().trim());
			if(dbs.getText().toString()!=null && !dbs.getText().toString().equals("")){
				jiFangBean.setDbDs(Double.parseDouble(dbs.getText().toString()));
			}
			jiFangBean.setJfYsJj(((Option) ysjj.getSelectedItem()).getValue());
			StringBuffer jfyssbf=new StringBuffer();
			if(jfys_mjk.isChecked()){
				jfyssbf.append("1");
			}
			if(jfys_tyys.isChecked()){
				jfyssbf.append("2");
			}
			if(jfys_zyys.isChecked()){
				jfyssbf.append("3");
			}
			if(jfys_wyys.isChecked()){
				jfyssbf.append("4");
			}
			if(jfyssbf!=null){
				jiFangBean.setJfYs(jfyssbf.toString());
			}else{
				jiFangBean.setJfYs("0");
			}
			jiFangBean.setCheckUserId(ApplicationData.user.getId()+"");
			jiFangBean.setEwm(qrcode.getText().toString());
			if(dbbhName!=null && !dbbhName.equals("")){
				MD5Check md5Check = new MD5Check();
				proBean=new AppProblemPicBean();
				uuid = UUID.randomUUID();
				proBean.setId(uuid.toString());
				proBean.setType(ApplicationData.PROBLEM_TYPE_ROOMDBBHPIC);
				proBean.setCode(ApplicationData.PROBLEM_CODE_ROOMDBBHPIC);
				proBean.setPhysicCode(bean.getPhysicCode());
				proBean.setPicSeq(1);
				proBean.setLinkCode(codeStr);
				try {
					File f = new File(dbbhName);
					String filename = f.getName();
					proBean.setPicAddr(filename);
					proBean.setCheckCode(md5Check.getFileMD5String(f));
				} catch (IOException e) {
					e.printStackTrace();
				}
				proBean.setCreateUserId(ApplicationData.user.getId() + "");
				proBean.setCheckUserId(ApplicationData.user.getId() + "");
			}
			SQLiteDatabase db = mOpenHelper.getReadableDatabase();
			try {
				TowerSQliteDbBean.insertRoom(db, jiFangBean, 2);
				if(dbbhName!=null && !dbbhName.equals("")){
					TowerSQliteDbBean.insertProblemPic(db, proBean);
				}
				handler.obtainMessage(1).sendToTarget();
			} catch (Exception e) {
				handler.obtainMessage(-1).sendToTarget();
			} finally {
				db.close();
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
				back();
				// toastMessage("��ӳɹ���");
				break;
			case -1:
				toastMessage("����ʧ�ܣ�");
				break;
			}
		}
	};
	
	/**
	 * ���������仯���ӷ���
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
			// �ж�С������������ֵ�λ��
			if (s.toString().startsWith(".")) {
				jfmj.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					jfmj.setText(s);
					jfmj.setSelection(s.length());
				}
			}

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

	}
	
	/**
	 * 
	* �����������ǣ������
	* @author: ��Ȼ
	* @version: 2015-8-14 ����1:57:18
	* @ClassName: mTextWatcher4 
	* @��Ŀ�� towerzj_app
	* @����com.woyi.towerzj_app.activity.jifang
	 */
	class mTextWatcher implements TextWatcher {

		// ���ֱ仯ǰ
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		// ���ֱ仯ʱ
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// �ж�С������������ֵ�λ��
			if (s.toString().startsWith(".")) {
				dbs.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					dbs.setText(s);
				}
			}
		}

		// ���ֱ仯��
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	/**
	 * ���spinner��adapter
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String text = "";
		if (data != null && requestCode == code1) {
			text = data.getExtras().getString(res_text);
			qrcode.setText(text);
		}else if(data!=null && requestCode==SUCCESS){
			dbbhName=data.getExtras().getString("name");
			if(dbbhName!=null && !dbbhName.equals("")){
				dbbhimg.setVisibility(View.GONE);
			}
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
	
	/**
	 * ϵͳ���ؼ�
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
		forwardIntent(getApplicationContext(), NewJifangDetailActivity.class,
				bundle);
		finish();
	}

}
