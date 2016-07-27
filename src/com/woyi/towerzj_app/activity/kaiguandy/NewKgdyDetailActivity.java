package com.woyi.towerzj_app.activity.kaiguandy;

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
import android.text.TextUtils;
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

import com.woyi.towerzj_app.activity.QuestionListActivity;
import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.activity.peidianx.NewPdxDetailActivity;
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
 * �����������ǣ����ص�Դ
 * 
 * @author: ��Ȼ
 * @version: 2015-7-18 ����11:50:08
 * @ClassName: KgdyDetailActivity
 * @��Ŀ�� towerzj_app
 * @����com.woyi.towerzj_app.activity.kaiguandy
 */
public class NewKgdyDetailActivity extends ForwardActivity {
	private TextView
	pinpResult1,pinpResult2,pinpResult3,rlResult1,rlResult2,rlResult3;
	private TextView title, add, next, save;
	private Button back, question1, del1, question2, del2, question3, del3;
	private TextView code, address;// �ԱȽ��

	private TextView mc1, mc2, mc3, tv1, tv2, tv3, tv4, tv5, tv6;
	private LinearLayout linear1, linear2, linear3, linearyys1, linearyys2,
	linearyys3, linearyys4, linearyys5, linearyys6;

	private EditText rongl1, qrcode1, rongl2, qrcode2, rongl3, qrcode3,
	addtext1, addtext2, addtext3;
	private TextView yd_pp1, yd_rl1, yd_pp2, yd_rl2, lt_pp1, lt_rl1, lt_pp2,
	lt_rl2, dx_pp1, dx_rl1, dx_pp2, dx_rl2;

	private Spinner pinp1, pinp2, pinp3, yd_ppsj1, yd_ppsj2, lt_ppsj1,
	lt_ppsj2, dx_ppsj1, dx_ppsj2,yd_sblx1, yd_sblx2, lt_sblx1,lt_sblx2, dx_sblx1,dx_sblx2; 
	private ImageView qrcodeimg1, qrcodeimg2, qrcodeimg3;

	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	private int code1 = 2013;
	private int code2 = 2014;
	private int code3 = 2015;
	private String res_text = "text";
	private String code_str = "code";

	private AppPhyInfoBean bean;
	private List<AppSwitchBean> list;
	private List<AppSwitchComBean> comlist;

	private AppSwitchBean switchBean1;
	private AppSwitchBean switchBean2;
	private AppSwitchBean switchBean3;

	private List<Option> ppsjList;// ƥ������

	// ��Ӫ��ʵ����
	private AppSwitchComBean ydcomBean1;
	private AppSwitchComBean ydcomBean2;
	private AppSwitchComBean ltcomBean1;
	private AppSwitchComBean ltcomBean2;
	private AppSwitchComBean dxcomBean1;
	private AppSwitchComBean dxcomBean2;

	private Bundle bundle;
	private DatabaseHelper mOpenHelper;
	private SQLiteDatabase db;
	private String rlStr = "";
	private String ppStr = "";
	private boolean ISPP = false;
	private int flags = 0;
	// ɾ�����
	private int delflag = 0;
	private DelPopupWindow delWindow;
	
	private String ydppflag1 = "", ydppflag2 = "", ltppflag1 = "",
			ltppflag2 = "", dxppflag1 = "", dxppflag2 = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		flags = Integer.parseInt(this.getIntent().getExtras().getString("flag"));
		Reflex.loadViewForActivityOnCreate(this,
				R.layout.activity_kgdy_new_detail);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		title.setText("���ص�Դ");
		relative_layout.setVisibility(View.VISIBLE);
		loading.startAnimation(animation);
		loadList();
		init();
	}

	/**
	 * 
	 * �˷����������ǣ���ʼ������
	 * 
	 * @Title: init
	 * @author: ��Ȼ
	 * @return void ��������
	 * @version: 2015-7-20 ����9:37:03
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
		rongl1.addTextChangedListener(new mTextWatcher1());
		rongl2.addTextChangedListener(new mTextWatcher2());
		rongl3.addTextChangedListener(new mTextWatcher3());
		addtext1.addTextChangedListener(new mTextWatcher4());
		addtext2.addTextChangedListener(new mTextWatcher5());
		addtext3.addTextChangedListener(new mTextWatcher6());
		pinp1.setOnItemSelectedListener(listener1);
		pinp2.setOnItemSelectedListener(listener2);
		pinp3.setOnItemSelectedListener(listener3);
		yd_ppsj1.setOnItemSelectedListener(ppsjlistener1);
		yd_ppsj2.setOnItemSelectedListener(ppsjlistener2);
		lt_ppsj1.setOnItemSelectedListener(ppsjlistener3);
		lt_ppsj2.setOnItemSelectedListener(ppsjlistener4);
		dx_ppsj1.setOnItemSelectedListener(ppsjlistener5);
		dx_ppsj2.setOnItemSelectedListener(ppsjlistener6);
	}

	private View.OnClickListener btnLis = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			int id = arg0.getId();
			switch (id) {
			case R.id.back:
				finish();
				break;
			case R.id.question1:
				bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_SWITCH);
				bundle.putSerializable("bean", bean);
				bundle.putString("code", switchBean1.getCode());
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.question2:
				bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_SWITCH);
				bundle.putSerializable("bean", bean);
				bundle.putString("code", switchBean2.getCode());
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.question3:
				bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_SWITCH);
				bundle.putSerializable("bean", bean);
				bundle.putString("code", switchBean3.getCode());
				forwardIntent(getApplicationContext(),
						QuestionListActivity.class, bundle);
				break;
			case R.id.save:// ����
				saveData();
				break;
			case R.id.add:
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(), KgdyAddActivity.class,
						bundle);
				break;
			case R.id.next:// ��һ��
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags+"");
				forwardIntent(getApplicationContext(),
						NewPdxDetailActivity.class, bundle);
				break;
			case R.id.del:
				delData();
				delWindow.dismiss();
				break;
			case R.id.query2:
				delWindow.dismiss();
				break;
			case R.id.del1:// ɾ��
				delflag = 1;
				delWindow = new DelPopupWindow(NewKgdyDetailActivity.this, null);
				delWindow.showAtLocation(
						NewKgdyDetailActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				break;
			case R.id.del2:// ɾ��
				delflag = 2;
				delWindow = new DelPopupWindow(NewKgdyDetailActivity.this, null);
				delWindow.showAtLocation(
						NewKgdyDetailActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				break;
			case R.id.del3:// ɾ��
				delflag = 3;
				delWindow = new DelPopupWindow(NewKgdyDetailActivity.this, null);
				delWindow.showAtLocation(
						NewKgdyDetailActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				break;
				// ��ά��ɨ��
			case R.id.qrcodeimg1:
				scan(code1);
				break;
			case R.id.qrcodeimg2:
				scan(code2);
				break;
			case R.id.qrcodeimg3:
				scan(code3);
				break;
			}
		}
	};

	/**
	 * 
	 * �˷����������ǣ������б�
	 * 
	 * @Title: loadList
	 * @author: ��Ȼ
	 * @return void ��������
	 * @version: 2015-7-21 ����10:02:02
	 */
	private void loadList() {
		list = new ArrayList<AppSwitchBean>();
		comlist = new ArrayList<AppSwitchComBean>();
		String sql = "select * from tb_switch where physicCode='"
				+ bean.getPhysicCode() + "' and status='1'";
		try {
			list = TowerSQliteDbBean.querySwitchData(mOpenHelper, sql);
			sql = "select * from ts_switch where physicCode='"
					+ bean.getPhysicCode() + "' ";
			comlist = TowerSQliteDbBean.querySwitchComData(mOpenHelper, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}
	}

	/**
	 * 
	 * �˷����������ǣ�ɾ��
	 * 
	 * @Title: delData
	 * @author: ��Ȼ
	 * @param switchBean12
	 * @return void ��������
	 * @version: 2015-7-27 ����11:28:00
	 */
	protected void delData() {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		try {
			// ��ʼ����
			db.beginTransaction();
			if (delflag == 1) {
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),
						switchBean1.getCode(),null, "05");
			} else if (delflag == 2) {
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),
						switchBean2.getCode(),null, "05");
			} else if (delflag == 3) {
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),
						switchBean3.getCode(),null, "05");
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
				toastMessage("����ʧ�ܣ�");
				break;
			case 2:
				add.setVisibility(View.VISIBLE);
				loadList();
				break;
			case -2:
				// toastMessage("ɾ��ʧ��");
				break;
			case 4:
				// toastMessage("���³ɹ�");
				bundle = new Bundle();
				bundle.putString("flag", flags+"");
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(),
						NewPdxDetailActivity.class, bundle);
				break;
			case -4:
				// toastMessage("����ʧ��");
				break;
			}
		}
	};

	/********************************************* ��װ������� ********************************************************/
	// ��װ���¶���
	public void saveData() {
		try {
			int beansize1 = 0, beansize2 = 0, beansize3 = 0;// ���ص�Դ����
			int comsize1 = 0, comsize2 = 0, comsize3 = 0;// ƥ�����Ӫ������
			// ���ص�Դ
			List<AppSwitchBean> newlist = new ArrayList<AppSwitchBean>();
			AppSwitchBean switchBean = new AppSwitchBean();

			if (linear1.getVisibility() == View.VISIBLE) {
				if (rongl1.getText().toString() == null
						|| rongl1.getText().toString().equals("")) {
					toastMessage("�����뿪�ص�Դ01��������");
					return;
				}
				beansize1++;
				switchBean.setAssetNum(switchBean1.getAssetNum());
				switchBean.setCheckUserId(ApplicationData.user.getId() + "");
				switchBean.setCode(switchBean1.getCode());
				String pp = ((Option) pinp1.getSelectedItem()).getValue();
				switchBean.setPp(pp);
				if (pp.equals("����")) {
					if (addtext1.getText().toString().trim() != null
							&& !addtext1.getText().toString().trim().equals("")) {
						switchBean.setPpCheck(addtext1.getText().toString()
								.trim());
					}
				}
				switchBean.setPhysicCode(switchBean1.getPhysicCode());
				switchBean.setRl(Double
						.parseDouble(rongl1.getText().toString()));
				switchBean.setEwm(qrcode1.getText().toString().trim());
				newlist.add(switchBean);
			}
			if (linear2.getVisibility() == View.VISIBLE) {
				if (rongl2.getText().toString() == null
						|| rongl2.getText().toString().equals("")) {
					toastMessage("�����뿪�ص�Դ02��������");
					return;
				}
				beansize2++;
				switchBean = new AppSwitchBean();
				switchBean.setAssetNum(switchBean2.getAssetNum());
				switchBean.setCheckUserId(ApplicationData.user.getId() + "");
				switchBean.setCode(switchBean2.getCode());
				String pp = ((Option) pinp2.getSelectedItem()).getValue();
				switchBean.setPp(pp);
				if (pp.equals("����")) {
					if (addtext2.getText().toString().trim() != null
							&& !addtext2.getText().toString().trim().equals("")) {
						switchBean.setPpCheck(addtext2.getText().toString()
								.trim());
					}
				}

				switchBean.setPhysicCode(switchBean2.getPhysicCode());
				switchBean.setRl(Double
						.parseDouble(rongl2.getText().toString()));
				switchBean.setEwm(qrcode2.getText().toString().trim());
				newlist.add(switchBean);
			}
			if (linear3.getVisibility() == View.VISIBLE) {
				if (rongl3.getText().toString() == null
						|| rongl3.getText().toString().equals("")) {
					toastMessage("�����뿪�ص�Դ03��������");
					return;
				}
				beansize3++;
				switchBean = new AppSwitchBean();
				switchBean.setAssetNum(switchBean3.getAssetNum());
				switchBean.setCheckUserId(ApplicationData.user.getId() + "");
				switchBean.setCode(switchBean3.getCode());
				String pp = ((Option) pinp3.getSelectedItem()).getValue();
				switchBean.setPp(pp);
				if (pp.equals("����")) {
					if (addtext3.getText().toString().trim() != null
							&& !addtext3.getText().toString().trim().equals("")) {
						switchBean.setPpCheck(addtext3.getText().toString()
								.trim());
					}
				}

				switchBean.setPhysicCode(switchBean3.getPhysicCode());
				switchBean.setRl(Double
						.parseDouble(rongl3.getText().toString()));
				switchBean.setEwm(qrcode3.getText().toString().trim());
				newlist.add(switchBean);
			}

			// ��Ӫ��
			List<AppSwitchComBean> newcomlist = new ArrayList<AppSwitchComBean>();
			AppSwitchComBean comBean = new AppSwitchComBean();
			if (linearyys1.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) yd_ppsj1.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("��ѡ��")) {
					if (linkcodeStr.equals("���ص�Դ01")) {
						comsize1++;
					} else if (linkcodeStr.equals("���ص�Դ02")) {
						comsize2++;
					} else if (linkcodeStr.equals("���ص�Դ03")) {
						comsize3++;
					}
				}
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(((Option)
						yd_sblx1.getSelectedItem()).getText());
				comBean.setCode(ydcomBean1.getCode());
				comBean.setLinkCode(((Option) yd_ppsj1.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ydcomBean1.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (linearyys2.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) yd_ppsj2.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("��ѡ��")) {
					if (linkcodeStr.equals("���ص�Դ01")) {
						comsize1++;
					} else if (linkcodeStr.equals("���ص�Դ02")) {
						comsize2++;
					} else if (linkcodeStr.equals("���ص�Դ03")) {
						comsize3++;
					}
				}
				comBean = new AppSwitchComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(((Option)
						yd_sblx2.getSelectedItem()).getText());
				comBean.setCode(ydcomBean2.getCode());
				comBean.setLinkCode(((Option) yd_ppsj2.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ydcomBean2.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (linearyys3.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) lt_ppsj1.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("��ѡ��")) {
					if (linkcodeStr.equals("���ص�Դ01")) {
						comsize1++;
					} else if (linkcodeStr.equals("���ص�Դ02")) {
						comsize2++;
					} else if (linkcodeStr.equals("���ص�Դ03")) {
						comsize3++;
					}
				}
				comBean = new AppSwitchComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(((Option)
						lt_sblx1.getSelectedItem()).getText());
				comBean.setCode(ltcomBean1.getCode());
				comBean.setLinkCode(((Option) lt_ppsj1.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ltcomBean1.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (linearyys4.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) lt_ppsj1.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("��ѡ��")) {
					if (linkcodeStr.equals("���ص�Դ01")) {
						comsize1++;
					} else if (linkcodeStr.equals("���ص�Դ02")) {
						comsize2++;
					} else if (linkcodeStr.equals("���ص�Դ03")) {
						comsize3++;
					}
				}
				comBean = new AppSwitchComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(((Option)
						lt_sblx2.getSelectedItem()).getText());
				comBean.setCode(ltcomBean2.getCode());
				comBean.setLinkCode(((Option) lt_ppsj2.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ltcomBean2.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (linearyys5.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) dx_ppsj1.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("��ѡ��")) {
					if (linkcodeStr.equals("���ص�Դ01")) {
						comsize1++;
					} else if (linkcodeStr.equals("���ص�Դ02")) {
						comsize2++;
					} else if (linkcodeStr.equals("���ص�Դ03")) {
						comsize3++;
					}
				}
				comBean = new AppSwitchComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(((Option)
						dx_sblx1.getSelectedItem()).getText());
				comBean.setCode(dxcomBean1.getCode());
				comBean.setLinkCode(((Option) dx_ppsj1.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(dxcomBean1.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (linearyys6.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) dx_ppsj2.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("��ѡ��")) {
					if (linkcodeStr.equals("���ص�Դ01")) {
						comsize1++;
					} else if (linkcodeStr.equals("���ص�Դ02")) {
						comsize2++;
					} else if (linkcodeStr.equals("���ص�Դ03")) {
						comsize3++;
					}
				}
				comBean = new AppSwitchComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(((Option)
						dx_sblx2.getSelectedItem()).getText());
				comBean.setCode(dxcomBean2.getCode());
				comBean.setLinkCode(((Option) dx_ppsj2.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(dxcomBean2.getPhysicCode());
				newcomlist.add(comBean);
			}

			//			if (beansize1 != 0) {
			//				if (comsize1 > beansize1) {
			//					toastMessage("���ص�Դ01ֻ��ƥ��1����Ӫ������");
			//					return;
			//				}
			//			}
			//			if (beansize2 != 0) {
			//				if (comsize2 > beansize2) {
			//					toastMessage("���ص�Դ02ֻ��ƥ��1����Ӫ������");
			//					return;
			//				}
			//			}
			//			if (beansize3 != 0) {
			//				if (comsize3 > beansize3) {
			//					toastMessage("���ص�Դ03ֻ��ƥ��1����Ӫ������");
			//					return;
			//				}
			//			}
			relative_layout.setVisibility(View.VISIBLE);
			loading.startAnimation(animation);
			// ��ʼ����
			db = mOpenHelper.getReadableDatabase();
			db.beginTransaction();
			if (newlist != null && newlist.size() > 0) {
				for (AppSwitchBean o : newlist) {
					TowerSQliteDbBean.updateSwitchData(db, o);
				}
			}
			if (newcomlist != null && newcomlist.size() > 0) {
				for (AppSwitchComBean o : newcomlist) {
					TowerSQliteDbBean.updateSwitchComData(db, o);
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

	/********************************************* ��װҳ������ ***********************************************/
	/**
	 * 
	 * �����������ǣ�����
	 * 
	 * @author: ��Ȼ
	 * @version: 2015-7-27 ����11:00:30
	 * @ClassName: mTextWatcher1
	 * @��Ŀ�� towerzj_app
	 * @����com.woyi.towerzj_app.activity.xudianchi
	 */
	class mTextWatcher1 implements TextWatcher {

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
				rongl1.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					rongl1.setText(s);
					rongl1.setSelection(s.length());
				}
			}
		}

		// ���ֱ仯��
		@Override
		public void afterTextChanged(Editable s) {
			String str = rongl1.getText().toString();
			changeSwitch("1", str, rlResult1, switchBean1);
		}
	}

	/**
	 * 
	 * �����������ǣ�����
	 * 
	 * @author: ��Ȼ
	 * @version: 2015-7-27 ����11:00:42
	 * @ClassName: mTextWatcher2
	 * @��Ŀ�� towerzj_app
	 * @����com.woyi.towerzj_app.activity.xudianchi
	 */
	class mTextWatcher2 implements TextWatcher {

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
				rongl2.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					rongl2.setText(s);
					rongl2.setSelection(s.length());
				}
			}
		}

		// ���ֱ仯��
		@Override
		public void afterTextChanged(Editable s) {
			String str = rongl2.getText().toString();
			changeSwitch("1", str, rlResult2, switchBean2);
		}
	}

	/**
	 * 
	 * �����������ǣ�����
	 * 
	 * @author: ��Ȼ
	 * @version: 2015-7-27 ����11:00:48
	 * @ClassName: mTextWatcher3
	 * @��Ŀ�� towerzj_app
	 * @����com.woyi.towerzj_app.activity.xudianchi
	 */
	class mTextWatcher3 implements TextWatcher {

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
				rongl3.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					rongl3.setText(s);
					rongl3.setSelection(s.length());
				}
			}
		}

		// ���ֱ仯��
		@Override
		public void afterTextChanged(Editable s) {
			String str = rongl3.getText().toString();
			changeSwitch("1", str, rlResult3, switchBean3);
		}
	}

	/**
	 * Ʒ��
	 */
	private AdapterView.OnItemSelectedListener listener1 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) pinp1.getSelectedItem()).getText();
			if (str.equals("����")) {
				addtext1.setVisibility(View.VISIBLE);
				// addtext1.setText("");
			} else {
				addtext1.setVisibility(View.GONE);
				// addtext1.setText("");
			}
			changeSwitch("2", str, pinpResult1, switchBean1);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * Ʒ��
	 */
	private AdapterView.OnItemSelectedListener listener2 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) pinp2.getSelectedItem()).getText();
			if (str.equals("����")) {
				addtext2.setVisibility(View.VISIBLE);
				// addtext2.setText("");
			} else {
				addtext2.setVisibility(View.GONE);
				// addtext2.setText("");
			}
			changeSwitch("2", str, pinpResult2, switchBean2);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * Ʒ��
	 */
	private AdapterView.OnItemSelectedListener listener3 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) pinp3.getSelectedItem()).getText();
			if (str.equals("����")) {
				addtext3.setVisibility(View.VISIBLE);
				// addtext3.setText("");
			} else {
				addtext3.setVisibility(View.GONE);
				// addtext3.setText("");
			}
			changeSwitch("2", str, pinpResult3, switchBean3);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 
	 * �����������ǣ�Ʒ��
	 * 
	 * @author: ��Ȼ
	 * @version: 2015-7-27 ����11:01:09
	 * @ClassName: mTextWatcher4
	 * @��Ŀ�� towerzj_app
	 * @����com.woyi.towerzj_app.activity.xudianchi
	 */
	class mTextWatcher4 implements TextWatcher {

		// ���ֱ仯ǰ
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		// ���ֱ仯��
		@Override
		public void afterTextChanged(Editable s) {
			String str = addtext1.getText().toString();
			if (str != null && !str.equals("")) {
				changeSwitch("2", str, pinpResult1, switchBean1);
			}
		}
	}

	/**
	 * 
	 * �����������ǣ�Ʒ��
	 * 
	 * @author: ��Ȼ
	 * @version: 2015-7-27 ����11:02:40
	 * @ClassName: mTextWatcher5
	 * @��Ŀ�� towerzj_app
	 * @����com.woyi.towerzj_app.activity.xudianchi
	 */
	class mTextWatcher5 implements TextWatcher {

		// ���ֱ仯ǰ
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		// ���ֱ仯��
		@Override
		public void afterTextChanged(Editable s) {
			String str = addtext2.getText().toString();
			if (str != null && !str.equals("")) {
				changeSwitch("2", str, pinpResult2, switchBean2);
			}
		}
	}

	/**
	 * 
	 * �����������ǣ�Ʒ��
	 * 
	 * @author: ��Ȼ
	 * @version: 2015-7-27 ����11:03:08
	 * @ClassName: mTextWatcher6
	 * @��Ŀ�� towerzj_app
	 * @����com.woyi.towerzj_app.activity.xudianchi
	 */
	class mTextWatcher6 implements TextWatcher {

		// ���ֱ仯ǰ
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		// ���ֱ仯��
		@Override
		public void afterTextChanged(Editable s) {
			String str = addtext3.getText().toString();
			if (str != null && !str.equals("")) {
				changeSwitch("2", str, pinpResult3, switchBean3);
			}
		}
	}

	/**
	 * 
	 * �˷����������ǣ�
	 * 
	 * @Title: changeSwitch
	 * @author: ��Ȼ
	 * @param type
	 * @param mcstr
	 * @param result
	 * @param appBean
	 * @return void ��������
	 * @version: 2015-7-27 ����11:34:43
	 */
	public void changeSwitch(String type, String mcstr, TextView result,
			AppSwitchBean appBean) {
		if (mcstr != null && !mcstr.equals("")) {
			mcstr = mcstr.trim();
		}
		ISPP = false;
		rlStr="";
		ppStr="";
		if (type.equals("1")) {
			if (mcstr != null && !mcstr.equals("")) {
				Double str = Double.parseDouble(mcstr);
				if (linearyys1.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) yd_ppsj1.getSelectedItem()).getValue())) {
						rlStr = loadRonglData(str, ydcomBean1);
					}
				}
				if (!ISPP) {
					if (linearyys2.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) yd_ppsj2.getSelectedItem())
								.getValue())) {
							rlStr = loadRonglData(str, ydcomBean2);
						}
					}
				}
				if (!ISPP) {
					if (linearyys3.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) lt_ppsj1.getSelectedItem())
								.getValue())) {
							rlStr = loadRonglData(str, ltcomBean1);
						}
					}
				}
				if (!ISPP) {
					if (linearyys4.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) lt_ppsj2.getSelectedItem())
								.getValue())) {
							rlStr = loadRonglData(str, ltcomBean2);
						}
					}
				}
				if (!ISPP) {
					if (linearyys5.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) dx_ppsj1.getSelectedItem())
								.getValue())) {
							rlStr = loadRonglData(str, dxcomBean1);
						}
					}
				}
				if (!ISPP) {
					if (linearyys6.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) dx_ppsj2.getSelectedItem())
								.getValue())) {
							rlStr = loadRonglData(str, dxcomBean2);
						}
					}
				}
			}
		} else if (type.equals("2")) {
			if (linearyys1.getVisibility() == View.VISIBLE) {
				if (appBean.getCode().equals(
						((Option) yd_ppsj1.getSelectedItem()).getValue())) {
					ppStr = loadPpData(mcstr, ydcomBean1);
				}
			}
			if (!ISPP) {
				if (linearyys2.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) yd_ppsj2.getSelectedItem()).getValue())) {
						ppStr = loadPpData(mcstr, ydcomBean2);
					}
				}
			}
			if (!ISPP) {
				if (linearyys3.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) lt_ppsj1.getSelectedItem()).getValue())) {
						ppStr = loadPpData(mcstr, ltcomBean1);
					}
				}
			}
			if (!ISPP) {
				if (linearyys4.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) lt_ppsj2.getSelectedItem()).getValue())) {
						ppStr = loadPpData(mcstr, ltcomBean2);
					}
				}
			}
			if (!ISPP) {
				if (linearyys5.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) dx_ppsj1.getSelectedItem()).getValue())) {
						ppStr = loadPpData(mcstr, dxcomBean1);
					}
				}
			}
			if (!ISPP) {
				if (linearyys6.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) dx_ppsj2.getSelectedItem()).getValue())) {
						ppStr = loadPpData(mcstr, dxcomBean2);
					}
				}
			}
		}
		if (result == pinpResult1 || result == pinpResult2 || result == pinpResult3) {
			result.setText(ppStr);
		}
		if (result == rlResult1 || result == rlResult2 || result == rlResult3) {
			result.setText(rlStr);
		}
	}

	/**
	 * 
	 * �˷����������ǣ��ж������Ƿ�ƥ��
	 * 
	 * @Title: loadRonglData
	 * @author: ��Ȼ
	 * @param str
	 * @param ydcomBean12
	 * @return
	 * @return String ��������
	 * @version: 2015-8-11 ����9:17:50
	 */
	private String loadRonglData(Double str, AppSwitchComBean comBean) {
		String rlstr = "";
		if (Math.abs(str - comBean.getRl()) > 0) {
			ISPP = true;
			rlstr = "������ƥ��";
		} else {
			rlstr = "";
		}
		return rlstr;
	}

	/**
	 * 
	 * �˷����������ǣ��ж�Ʒ���Ƿ�ƥ��
	 * 
	 * @Title: loadPpData
	 * @author: ��Ȼ
	 * @param mcstr
	 * @param ydcomBean12
	 * @return
	 * @return String ��������
	 * @version: 2015-8-11 ����9:12:10
	 */
	private String loadPpData(String ppstr, AppSwitchComBean comBean) {
		String str = "";
		if (ppstr.length() >= comBean.getPp().length()) {
			if (!ppstr.contains(comBean.getPp())) {
				ISPP = true;
				str = "Ʒ�Ʋ�ƥ��";
			} else {
				str = "";
			}
		}
		if (ppstr.length() < comBean.getPp().length()) {
			if (!comBean.getPp().contains(ppstr)) {
				ISPP = true;
				str = "Ʒ�Ʋ�ƥ��";
			} else {
				str = "";
			}
		}
		return str;
	}

	/***************************************** ��Ӫ��ƥ�������ж� *******************************************/
	private AdapterView.OnItemSelectedListener ppsjlistener1 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			if (ydppflag1 != null && !ydppflag1.equals("")) {
				if (ydppflag1.equals("���ص�Դ01")) {
					pinpResult1.setText("");
					rlResult1.setText("");
				} else if (ydppflag1.equals("���ص�Դ02")) {
					pinpResult2.setText("");
					rlResult2.setText("");
				} else if (ydppflag1.equals("���ص�Դ03")) {
					pinpResult3.setText("");
					rlResult3.setText("");
				}
			}
			String str = ((Option) yd_ppsj1.getSelectedItem()).getText().trim();
			ydppflag1=str;
			changeSwitchCom(str, ydcomBean1);
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
			if (ydppflag2 != null && !ydppflag2.equals("")) {
				if (ydppflag2.equals("���ص�Դ01")) {
					pinpResult1.setText("");
					rlResult1.setText("");
				} else if (ydppflag2.equals("���ص�Դ02")) {
					pinpResult2.setText("");
					rlResult2.setText("");
				} else if (ydppflag2.equals("���ص�Դ03")) {
					pinpResult3.setText("");
					rlResult3.setText("");
				}
			}
			String str = ((Option) yd_ppsj2.getSelectedItem()).getText().trim();
			ydppflag2=str;
			changeSwitchCom(str, ydcomBean2);
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
			if (ltppflag1 != null && !ltppflag1.equals("")) {
				if (ltppflag1.equals("���ص�Դ01")) {
					pinpResult1.setText("");
					rlResult1.setText("");
				} else if (ltppflag1.equals("���ص�Դ02")) {
					pinpResult2.setText("");
					rlResult2.setText("");
				} else if (ltppflag1.equals("���ص�Դ03")) {
					pinpResult3.setText("");
					rlResult3.setText("");
				}
			}
			String str = ((Option) lt_ppsj1.getSelectedItem()).getText().trim();
			ltppflag1=str;
			changeSwitchCom(str, ltcomBean1);
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
			if (ltppflag2 != null && !ltppflag2.equals("")) {
				if (ltppflag2.equals("���ص�Դ01")) {
					pinpResult1.setText("");
					rlResult1.setText("");
				} else if (ltppflag2.equals("���ص�Դ02")) {
					pinpResult2.setText("");
					rlResult2.setText("");
				} else if (ltppflag2.equals("���ص�Դ03")) {
					pinpResult3.setText("");
					rlResult3.setText("");
				}
			}
			String str = ((Option) lt_ppsj2.getSelectedItem()).getText().trim();
			ltppflag2=str;
			changeSwitchCom(str, ltcomBean2);
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
			if (dxppflag1 != null && !dxppflag1.equals("")) {
				if (dxppflag1.equals("���ص�Դ01")) {
					pinpResult1.setText("");
					rlResult1.setText("");
				} else if (dxppflag1.equals("���ص�Դ02")) {
					pinpResult2.setText("");
					rlResult2.setText("");
				} else if (dxppflag1.equals("���ص�Դ03")) {
					pinpResult3.setText("");
					rlResult3.setText("");
				}
			}
			String str = ((Option) dx_ppsj1.getSelectedItem()).getText().trim();
			dxppflag1=str;
			changeSwitchCom(str, dxcomBean1);
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
			if (dxppflag2 != null && !dxppflag2.equals("")) {
				if (dxppflag2.equals("���ص�Դ01")) {
					pinpResult1.setText("");
					rlResult1.setText("");
				} else if (dxppflag2.equals("���ص�Դ02")) {
					pinpResult2.setText("");
					rlResult2.setText("");
				} else if (dxppflag2.equals("���ص�Դ03")) {
					pinpResult3.setText("");
					rlResult3.setText("");
				}
			}
			String str = ((Option) dx_ppsj2.getSelectedItem()).getText().trim();
			dxppflag2=str;
			changeSwitchCom(str, dxcomBean2);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private void changeSwitchCom(String str, AppSwitchComBean comBean) {
		if (!str.equals("��ѡ��")) {
			if (str.equals("���ص�Դ01")) {
				String ronglstr = rongl1.getText().toString();
				if (ronglstr != null && !ronglstr.equals("")) {
					Double rlstr = Double.parseDouble(ronglstr);
					rlStr = loadRonglData(rlstr, comBean);
				} else {
					rlStr = "������ƥ��";
				}
				String pinpstr = ((Option) pinp1.getSelectedItem()).getText();
				ppStr = loadPpData(pinpstr, comBean);
				pinpResult1.setText(ppStr);
				rlResult1.setText(rlStr);
			} else if (str.equals("���ص�Դ02")) {
				String ronglstr = rongl2.getText().toString();
				if (ronglstr != null && !ronglstr.equals("")) {
					Double rlstr = Double.parseDouble(ronglstr);
					rlStr = loadRonglData(rlstr, comBean);
				} else {
					rlStr = "������ƥ��";
				}
				String pinpstr = ((Option) pinp2.getSelectedItem()).getText();
				ppStr = loadPpData(pinpstr, comBean);
				pinpResult2.setText(ppStr);
				rlResult2.setText(rlStr);
			} else if (str.equals("���ص�Դ03")) {
				String ronglstr = rongl3.getText().toString();
				if (ronglstr != null && !ronglstr.equals("")) {
					Double rlstr = Double.parseDouble(ronglstr);
					rlStr = loadRonglData(rlstr, comBean);
				} else {
					rlStr = "������ƥ��";
				}
				String pinpstr = ((Option) pinp3.getSelectedItem()).getText();
				ppStr = loadPpData(pinpstr, comBean);
				pinpResult3.setText(ppStr);
				rlResult3.setText(rlStr);
			}
		}
	}

	/**
	 * 
	 * �˷����������ǣ�����ҳ������
	 * 
	 * @Title: loadData
	 * @author: ��Ȼ
	 * @return void ��������
	 * @version: 2015-7-27 ����9:19:02
	 */
	protected void loadData() {
		ppsjList = new ArrayList<Option>();
		Option option = null;
		if (list != null && list.size() > 1) {
			option = new Option();
			option.setText("��ѡ��");
			option.setValue("0");
			ppsjList.add(option);
		} else if (list != null && comlist != null
				&& list.size() < comlist.size()) {
			option = new Option();
			option.setText("��ѡ��");
			option.setValue("0");
			ppsjList.add(option);
		}
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					option = new Option();
					linear1.setVisibility(View.VISIBLE);
					switchBean1 = list.get(i);
					mc1.setText("���ص�Դ01");
					rongl1.setText(switchBean1.getRl() + "");
					fillSpinnerAdapter(SpinnerUtilBean.kgdypp(), pinp1, "��ѡ�񿪹ص�ԴƷ��");
					if (switchBean1.getPp() != null
							&& !switchBean1.getPp().equals("")) {
						selectedSpinner(SpinnerUtilBean.kgdypp(),
								switchBean1.getPp(), pinp1);
					}
					if (switchBean1.getPpCheck() != null
							&& !switchBean1.getPpCheck().equals("")
							&& !switchBean1.getPpCheck().equals("null")) {
						addtext1.setText(switchBean1.getPpCheck());
						addtext1.setVisibility(View.VISIBLE);
						
					}
					if (switchBean1.getEwm() != null
							&& !switchBean1.getEwm().equals("")
							&& !switchBean1.getEwm().equals("null")) {
						qrcode1.setText(switchBean1.getEwm());
						qrcodeimg1.setVisibility(View.GONE);
					} else {
						// qrcode1.setText("");
					}
					option.setValue(switchBean1.getCode());
					option.setText("���ص�Դ01");
					ppsjList.add(option);
				}
				if (i == 1) {
					option = new Option();
					linear2.setVisibility(View.VISIBLE);
					switchBean2 = list.get(i);
					mc2.setText("���ص�Դ02");
					rongl2.setText(switchBean2.getRl() + "");
					fillSpinnerAdapter(SpinnerUtilBean.kgdypp(), pinp2, "��ѡ�񿪹ص�ԴƷ��");
					if (switchBean2.getPp() != null
							&& !switchBean2.getPp().equals("")) {
						selectedSpinner(SpinnerUtilBean.kgdypp(),
								switchBean2.getPp(), pinp2);
					}
					if (switchBean2.getPpCheck() != null
							&& !switchBean2.getPpCheck().equals("")
							&& !switchBean2.getPpCheck().equals("null")) {
						addtext2.setText(switchBean2.getPpCheck());
						addtext2.setVisibility(View.VISIBLE);
						
					}
					if (switchBean2.getEwm() != null
							&& !switchBean2.getEwm().equals("")
							&& !switchBean2.getEwm().equals("null")) {
						qrcode2.setText(switchBean2.getEwm());
						qrcodeimg2.setVisibility(View.GONE);
					} else {
						// qrcode2.setText("");
					}
					option.setValue(switchBean2.getCode());
					option.setText("���ص�Դ02");
					ppsjList.add(option);
				}
				if (i == 2) {
					option = new Option();
					linear3.setVisibility(View.VISIBLE);
					switchBean3 = list.get(i);
					mc3.setText("���ص�Դ03");
					rongl3.setText(switchBean3.getRl() + "");
					fillSpinnerAdapter(SpinnerUtilBean.kgdypp(), pinp3, "��ѡ������Ʒ��");
					if (switchBean3.getPp() != null
							&& !switchBean3.getPp().equals("")) {
						selectedSpinner(SpinnerUtilBean.kgdypp(),
								switchBean3.getPp(), pinp3);
					}
					if (switchBean3.getPpCheck() != null
							&& !switchBean3.getPpCheck().equals("")
							&& !switchBean3.getPpCheck().equals("null")) {
						addtext3.setText(switchBean3.getPpCheck());
						addtext3.setVisibility(View.VISIBLE);
						
					}
					if (switchBean3.getEwm() != null
							&& !switchBean3.getEwm().equals("")
							&& !switchBean3.getEwm().equals("null")) {
						qrcode3.setText(switchBean3.getEwm());
						qrcodeimg3.setVisibility(View.GONE);
					} else {
						// qrcode3.setText("");
					}
					option.setValue(switchBean3.getCode());
					option.setText("���ص�Դ03");
					ppsjList.add(option);
				}
			}
		}
		fillSpinnerAdapter(ppsjList, yd_ppsj1, "��ѡ��ƥ������");
		fillSpinnerAdapter(ppsjList, yd_ppsj2, "��ѡ��ƥ������");
		fillSpinnerAdapter(ppsjList, lt_ppsj2, "��ѡ��ƥ������");
		fillSpinnerAdapter(ppsjList, lt_ppsj1, "��ѡ��ƥ������");
		fillSpinnerAdapter(ppsjList, dx_ppsj2, "��ѡ��ƥ������");
		fillSpinnerAdapter(ppsjList, dx_ppsj1, "��ѡ��ƥ������");

		fillSpinnerAdapter(SpinnerUtilBean.getSz(), yd_sblx2,"��ѡ���豸����");
		fillSpinnerAdapter(SpinnerUtilBean.getSz(), yd_sblx1,"��ѡ���豸����");
		fillSpinnerAdapter(SpinnerUtilBean.getSz(), lt_sblx2,"��ѡ���豸����");
		fillSpinnerAdapter(SpinnerUtilBean.getSz(), lt_sblx1,"��ѡ���豸����");
		fillSpinnerAdapter(SpinnerUtilBean.getSz(), dx_sblx2,"��ѡ���豸����");
		fillSpinnerAdapter(SpinnerUtilBean.getSz(), dx_sblx1,"��ѡ���豸����");

		if (comlist != null && comlist.size() > 0) {
			List<AppSwitchComBean> ydlist = new ArrayList<AppSwitchComBean>();
			List<AppSwitchComBean> ltlist = new ArrayList<AppSwitchComBean>();
			List<AppSwitchComBean> dxlist = new ArrayList<AppSwitchComBean>();
			for (AppSwitchComBean o : comlist) {
				if (o.getType().equals("01")) {// �ƶ�
					ydlist.add(o);
				} else if (o.getType().equals("02")) {// ��ͨ
					ltlist.add(o);
				} else if (o.getType().equals("03")) {// ����
					dxlist.add(o);
				}
			}
			if (ydlist != null && ydlist.size() > 0) {
				for (int i = 0; i < ydlist.size(); i++) {
					if (i == 0) {
						linearyys1.setVisibility(View.VISIBLE);
						ydcomBean1 = ydlist.get(i);
						tv1.setText("�й��ƶ�");
						if (!TextUtils.isEmpty(ydcomBean1.getPp())) {
							yd_pp1.setText(ydcomBean1.getPp());
						} else {
							yd_pp1.setText("��");
						}
						if (!TextUtils.isEmpty(ydcomBean1.getRl() + "")) {
							yd_rl1.setText(ydcomBean1.getRl() + "");
						} else {
							yd_rl1.setText("��");
						}
						if (ydcomBean1.getCheckValue() != null&&
								!ydcomBean1.getCheckValue().equals("")&&!ydcomBean1.getCheckValue().equals("null")) {
							selectedSpinner(SpinnerUtilBean.getSz(),ydcomBean1.getCheckValue(),
									yd_sblx1);
						}
						selectedvaleSpinner(ppsjList, ydcomBean1.getLinkCode(),
								yd_ppsj1);
					}
					if (i == 1) {
						linearyys2.setVisibility(View.VISIBLE);
						ydcomBean2 = ydlist.get(i);
						tv2.setText("�й��ƶ�");
						if (!TextUtils.isEmpty(ydcomBean2.getPp())) {
							yd_pp2.setText(ydcomBean2.getPp());
						} else {
							yd_pp2.setText("��");
						}
						if (!TextUtils.isEmpty(ydcomBean2.getRl() + "")) {
							yd_rl2.setText(ydcomBean2.getRl() + "");
						} else {
							yd_rl2.setText("��");
						}
						if (ydcomBean2.getCheckValue() != null&&
								!ydcomBean2.getCheckValue().equals("")&&!ydcomBean2.getCheckValue().equals("null")) {
							selectedSpinner(SpinnerUtilBean.getSz(),ydcomBean2.getCheckValue(),
									yd_sblx2);
						}
						selectedvaleSpinner(ppsjList, ydcomBean2.getLinkCode(),
								yd_ppsj2);
					}
				}
			}

			if (ltlist != null && ltlist.size() > 0) {
				for (int i = 0; i < ltlist.size(); i++) {
					if (i == 0) {
						linearyys3.setVisibility(View.VISIBLE);
						ltcomBean1 = ltlist.get(i);
						tv3.setText("�й���ͨ");
						if (!TextUtils.isEmpty(ltcomBean1.getPp())) {
							lt_pp1.setText(ltcomBean1.getPp());
						} else {
							lt_pp1.setText("��");
						}
						if (!TextUtils.isEmpty(ltcomBean1.getRl() + "")) {
							lt_rl1.setText(ltcomBean1.getRl() + "");
						} else {
							lt_rl1.setText("��");
						}
						if (ltcomBean1.getCheckValue() != null&&
								!ltcomBean1.getCheckValue().equals("")&&!ltcomBean1.getCheckValue().equals("null")) {
							selectedSpinner(SpinnerUtilBean.getSz(),ltcomBean1.getCheckValue(),
									lt_sblx1);
						}
						selectedvaleSpinner(ppsjList, ltcomBean1.getLinkCode(),
								lt_ppsj1);
					}
					if (i == 1) {
						linearyys4.setVisibility(View.VISIBLE);
						ltcomBean2 = ltlist.get(i);
						tv4.setText("�й���ͨ");
						if (!TextUtils.isEmpty(ltcomBean2.getPp())) {
							lt_pp2.setText(ltcomBean2.getPp());
						} else {
							lt_pp2.setText("��");
						}
						if (!TextUtils.isEmpty(ltcomBean2.getRl() + "")) {
							lt_rl2.setText(ltcomBean2.getRl() + "");
						} else {
							lt_rl2.setText("��");
						}
						if (ltcomBean2.getCheckValue() != null&&
								!ltcomBean2.getCheckValue().equals("")&&!ltcomBean2.getCheckValue().equals("null")) {
							selectedSpinner(SpinnerUtilBean.getSz(),ltcomBean2.getCheckValue(),
									lt_sblx2);
						}
						selectedvaleSpinner(ppsjList, ltcomBean2.getLinkCode(),
								lt_ppsj2);
					}
				}
			}

			if (dxlist != null && dxlist.size() > 0) {
				for (int i = 0; i < dxlist.size(); i++) {
					if (i == 0) {
						linearyys5.setVisibility(View.VISIBLE);
						dxcomBean1 = dxlist.get(i);
						tv5.setText("�й�����");
						if (!TextUtils.isEmpty(dxcomBean1.getPp())) {
							dx_pp1.setText(dxcomBean1.getPp());
						} else {
							dx_pp1.setText("��");
						}
						if (!TextUtils.isEmpty(dxcomBean1.getRl() + "")) {
							dx_rl1.setText(dxcomBean1.getRl() + "");
						} else {
							dx_rl1.setText("��");
						}
						if (dxcomBean1.getCheckValue() != null&&
								!dxcomBean1.getCheckValue().equals("")&&!dxcomBean1.getCheckValue().equals("null")) {
							selectedSpinner(SpinnerUtilBean.getSz(),dxcomBean1.getCheckValue(),
									dx_sblx1);
						}
						selectedvaleSpinner(ppsjList, dxcomBean1.getLinkCode(),
								dx_ppsj1);
					}
					if (i == 1) {
						linearyys6.setVisibility(View.VISIBLE);
						dxcomBean2 = dxlist.get(i);
						tv6.setText("�й�����");
						if (!TextUtils.isEmpty(dxcomBean2.getPp())) {
							dx_pp2.setText(dxcomBean2.getPp());
						} else {
							dx_pp2.setText("��");
						}
						if (!TextUtils.isEmpty(dxcomBean2.getRl() + "")) {
							dx_rl2.setText(dxcomBean2.getRl() + "");
						} else {
							dx_rl2.setText("��");
						}
						if (dxcomBean2.getCheckValue() != null&&
								!dxcomBean2.getCheckValue().equals("")&&!dxcomBean2.getCheckValue().equals("null")) {
							selectedSpinner(SpinnerUtilBean.getSz(),dxcomBean2.getCheckValue(),
									dx_sblx2);
						}
						selectedvaleSpinner(ppsjList, dxcomBean2.getLinkCode(),
								dx_ppsj2);
					}
				}
			}
		}

	}

	/**
	 * ��ά��ɨ�践����Ϣ
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String text = "";
		if (data != null && requestCode == code1) {
			text = data.getExtras().getString(res_text);
			qrcode1.setText(text);
		} else if (data != null && requestCode == code2) {
			text = data.getExtras().getString(res_text);
			qrcode2.setText(text);
		} else if (data != null && requestCode == code3) {
			text = data.getExtras().getString(res_text);
			qrcode3.setText(text);
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

	/**
	 * Ĭ��ѡ��spinner��ֵ
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
	 * Ĭ��ѡ��spinner��ֵ(ƥ������)
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
	 * ���spinner��adapter
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
			del_tv.setText("��ɾ���˿��ص�Դ��Ϣ");
			del = (Button) mMenuView.findViewById(R.id.del);
			query2 = (Button) mMenuView.findViewById(R.id.query2);
			del.setOnClickListener(btnLis);
			query2.setOnClickListener(btnLis);
			query2.setText("ȡ��");
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

	@Override
	protected void onResume() {
		super.onResume();
		relative_layout.setVisibility(View.VISIBLE);
		loading.startAnimation(animation);
		loadList();
	}

}
