package com.woyi.towerzj_app.activity.jifang;

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
import android.view.KeyEvent;
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
import android.widget.CheckBox;
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
import com.woyi.towerzj_app.activity.kongtiao.NewKtDetailActivity;
import com.woyi.towerzj_app.activity.tawei.NewTaweiDetailActivity;
import com.woyi.towerzj_app.bean.Option;
import com.woyi.towerzj_app.bean.SpinnerUtilBean;
import com.woyi.towerzj_app.bean.jifang.AppRoomBean;
import com.woyi.towerzj_app.bean.jifang.AppRoomComBean;
import com.woyi.towerzj_app.bean.quedtion.AppProblemBean;
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
public class NewJifangDetailActivity extends ForwardActivity {

	private TextView mjresult1, mjresult2, mjresult3, jgresult1, jgresult2,
	jgresult3, lxresult1, lxresult2, lxresult3;
	private TextView title, add, next, save;
	private Button back, question1, del1, question2, del2, question3, del3;
	private TextView code, address;// 对比结果
	private AppProblemBean probean;
	private TextView mc1, mc2, mc3, tv1, tv2, tv3, tv4, tv5, tv6, dx_tv, yd_tv,
	lt_tv;
	private LinearLayout linear1, linear2, linear3, linearyys1, linearyys2,
	linearyys3, linearyys4, linearyys5, linearyys6, lineary_dxnewadd,
	lineary_ydnewadd, lineary_ltnewadd;

	private EditText jfmj1, qrcode1, jfmj2, qrcode2, jfmj3, qrcode3;
	private TextView yd_jfmj1, yd_jflx1, yd_jfjg1, yd_jfmj2, yd_jflx2,
	yd_jfjg2, lt_jflx1, lt_jfjg1, lt_jfmj1, lt_jflx2, lt_jfjg2,
	lt_jfmj2, dx_jflx1, dx_jfjg1, dx_jfmj1, dx_jflx2, dx_jfjg2,
	dx_jfmj2, dx_jflx3, yd_jflx3, lt_jflx3, dx_jfjg3, yd_jfjg3,
	lt_jfjg3, dx_jfmj3, yd_jfmj3, lt_jfmj3;

	private Spinner jflx1, jfjg1, jflx2, jfjg2, jflx3, jfjg3, yd_sblx1,
	yd_sblx2, lt_sblx1, lt_sblx2, dx_sblx1, dx_sblx2, yd_ppsj1,
	yd_ppsj2, lt_ppsj1, lt_ppsj2, dx_ppsj1, dx_ppsj2, dx_ppsj3,
	yd_ppsj3, lt_ppsj3, dx_sblx3, yd_sblx3, lt_sblx3;
	private ImageView qrcodeimg1, qrcodeimg2, qrcodeimg3;
	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	private int code1 = 2013;
	private int code2 = 2014;
	private int code3 = 2015;
	private String res_text = "text";
	private String code_str = "code";
	public List<String> drr = new ArrayList<String>();
	private AppPhyInfoBean bean;
	private int flags = 0;
	private List<AppRoomBean> list;
	private List<AppRoomComBean> comlist;

	private Bundle bundle;
	private DatabaseHelper mOpenHelper;
	private SQLiteDatabase db;

	private AppRoomBean roomBean1;
	private AppRoomBean roomBean2;
	private AppRoomBean roomBean3;

	private List<Option> ppsjList;// 匹配数据

	// 运营商实体类
	private AppRoomComBean ydcomBean1;
	private AppRoomComBean ydcomBean2;
	private AppRoomComBean ydcomBean3;
	private AppRoomComBean ltcomBean1;
	private AppRoomComBean ltcomBean2;
	private AppRoomComBean ltcomBean3;
	private AppRoomComBean dxcomBean1;
	private AppRoomComBean dxcomBean2;
	private AppRoomComBean dxcomBean3;

	private String lxStr = "";
	private String mjStr = "";
	private String jgStr = "";
	private boolean ISPP = false;
	private String ydppflag1 = "机房01", ydppflag2 = "机房01", ydppflag3 = "机房01",
			ltppflag1 = "机房01", ltppflag2 = "机房01", ltppflag3 = "机房01", dxppflag1 = "机房01",
			dxppflag2 = "机房01", dxppflag3 = "机房01";
	private String ydsbflag1 = "", ydsbflag2 = "", ydsbflag3 = "",
			ltsbflag1 = "", ltsbflag2 = "", ltsbflag3 = "", dxsbflag1 = "",
			dxsbflag2 = "", dxsbflag3 = "";
	// 删除标记
	private int delflag = 0;
	private DelPopupWindow delWindow;

	private boolean comFlag = false;

	public static final int SUCCESS = 1;

	//新加四个属性
	private TextView dbbh1,dbs1,dbbh2,dbs2,dbbh3,dbs3;
	private Spinner ysjj1,ysjj2,ysjj3;//jfys1,jfys2,jfys3,
	private CheckBox jfys_tyys1,jfys_mjk1,jfys_zyys1,jfys_wyys1,
	jfys_tyys2,jfys_mjk2,jfys_zyys2,jfys_wyys2,jfys_tyys3,jfys_mjk3,jfys_zyys3,jfys_wyys3;

	//电表编号拍照
	private ImageView dbbhimg1,dbbhimg2,dbbhimg3;
	public static final int DBBHSUCCESS1 = 1;
	public static final int DBBHSUCCESS2 = 2;
	public static final int DBBHSUCCESS3 = 3;
	private int bhflag=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		flags = Integer.parseInt(this.getIntent().getExtras().getString("flag"));
		Reflex.loadViewForActivityOnCreate(this,
				R.layout.activity_jifang_new_detail);
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
		loadList();
		init();
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
		list = new ArrayList<AppRoomBean>();
		comlist = new ArrayList<AppRoomComBean>();
		String sql = "select * from tb_room where physicCode='"
				+ bean.getPhysicCode() + "' and status='1'";
		try {
			list = TowerSQliteDbBean.queryRoomData(mOpenHelper, sql);
			sql = "select * from ts_room where physicCode='"
					+ bean.getPhysicCode() + "' ";
			comlist = TowerSQliteDbBean.queryRoomComData(mOpenHelper, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}
	}

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
		dbbhimg1.setOnClickListener(btnLis);
		dbbhimg2.setOnClickListener(btnLis);
		dbbhimg3.setOnClickListener(btnLis);
		jfmj1.addTextChangedListener(new mTextWatcher1());
		jfmj2.addTextChangedListener(new mTextWatcher2());
		jfmj3.addTextChangedListener(new mTextWatcher3());
		dbs1.addTextChangedListener(new mTextWatcher4());
		dbs2.addTextChangedListener(new mTextWatcher5());
		dbs3.addTextChangedListener(new mTextWatcher6());
		jflx1.setOnItemSelectedListener(lxlistener1);
		jflx2.setOnItemSelectedListener(lxlistener2);
		jflx3.setOnItemSelectedListener(lxlistener3);
		jfjg1.setOnItemSelectedListener(jglistener1);
		jfjg2.setOnItemSelectedListener(jglistener2);
		jfjg3.setOnItemSelectedListener(jglistener3);
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
		back.setOnClickListener(btnLis);
		next.setOnClickListener(btnLis);
		mjresult1.setOnClickListener(btnLis);
		mjresult2.setOnClickListener(btnLis);
		mjresult3.setOnClickListener(btnLis);
		jgresult1.setOnClickListener(btnLis);
		jgresult2.setOnClickListener(btnLis);
		jgresult3.setOnClickListener(btnLis);
		lxresult1.setOnClickListener(btnLis);
		lxresult2.setOnClickListener(btnLis);
		lxresult3.setOnClickListener(btnLis);
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
				saveData();
				break;
			case R.id.add:
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags+"");
				forwardIntent(getApplicationContext(), JiFangAddActivity.class,
						bundle);
				finish();
				break;
			case R.id.next:// 下一步
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags+"");
				forwardIntent(getApplicationContext(),
						NewKtDetailActivity.class, bundle);
				finish();
				break;
			case R.id.question1:
				bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_ROOM);
				bundle.putSerializable("bean", bean);
				bundle.putString("code", roomBean1.getCode());
				forwardIntent(getApplicationContext(),QuestionListActivity.class, bundle);
				break;
			case R.id.question2:
				bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_ROOM);
				bundle.putSerializable("bean", bean);
				bundle.putString("code", roomBean2.getCode());
				forwardIntent(getApplicationContext(),QuestionListActivity.class, bundle);
				break;
			case R.id.question3:
				bundle = new Bundle();
				bundle.putString("flag", ApplicationData.PROBLEM_TYPE_ROOM);
				bundle.putSerializable("bean", bean);
				bundle.putString("code", roomBean3.getCode());
				forwardIntent(getApplicationContext(),QuestionListActivity.class, bundle);
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
				delWindow = new DelPopupWindow(NewJifangDetailActivity.this,
						null);
				delWindow.showAtLocation(
						NewJifangDetailActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				break;
			case R.id.del2:// 删除
				delflag = 2;
				delWindow = new DelPopupWindow(NewJifangDetailActivity.this,
						null);
				delWindow.showAtLocation(
						NewJifangDetailActivity.this.findViewById(R.id.main),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				break;
			case R.id.del3:// 删除
				delflag = 3;
				delWindow = new DelPopupWindow(NewJifangDetailActivity.this,
						null);
				delWindow.showAtLocation(
						NewJifangDetailActivity.this.findViewById(R.id.main),
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
			case R.id.mjresult1:
				if (mjresult1.getText().toString() != null
				&& !mjresult1.equals("")) {
					loadProm(roomBean1.getCode(), 1);
				}
				break;
			case R.id.mjresult2:
				if (mjresult2.getText().toString() != null
				&& !mjresult2.equals("")) {
					loadProm(roomBean2.getCode(), 1);
				}
				break;
			case R.id.mjresult3:
				if (mjresult3.getText().toString() != null
				&& !mjresult3.equals("")) {
					loadProm(roomBean3.getCode(), 1);
				}
				break;
			case R.id.jgresult1:
				if (jgresult1.getText().toString() != null
				&& !jgresult1.equals("")) {
					loadProm(roomBean1.getCode(), 2);
				}
				break;
			case R.id.jgresult2:
				if (jgresult2.getText().toString() != null
				&& !jgresult2.equals("")) {
					loadProm(roomBean2.getCode(), 2);
				}
				break;
			case R.id.jgresult3:
				if (jgresult3.getText().toString() != null
				&& !jgresult3.equals("")) {
					loadProm(roomBean3.getCode(), 2);
				}
				break;
			case R.id.lxresult1:
				if (lxresult1.getText().toString() != null
				&& !lxresult1.equals("")) {
					loadProm(roomBean1.getCode(), 3);
				}
				break;
			case R.id.lxresult2:
				if (lxresult2.getText().toString() != null
				&& !lxresult2.equals("")) {
					loadProm(roomBean2.getCode(), 3);
				}
				break;
			case R.id.lxresult3:
				if (lxresult3.getText().toString() != null
				&& !lxresult3.equals("")) {
					loadProm(roomBean3.getCode(), 3);
				}
				break;
			case R.id.dbbhimg1:
				bhflag=1;
				forWard(roomBean1,bhflag);
				break;
			case R.id.dbbhimg2:
				bhflag=2;
				forWard(roomBean2,bhflag);
				break;
			case R.id.dbbhimg3:
				bhflag=3;
				forWard(roomBean3,bhflag);
				break;
			}
		}

	};

	/**
	 * 
	 * 此方法描述的是：不匹配问题跳转
	 * 
	 * @Title: loadProm
	 * @author: 罗然
	 * @param code
	 * @param i
	 * @return void 返回类型
	 * @version: 2015-8-11 下午8:07:14
	 */
	private void loadProm(String code, int i) {
		Bundle bundle = new Bundle();
		probean = new AppProblemBean();
		probean.setType(ApplicationData.PROBLEM_TYPE_ROOM);
		if (i == 1) {// 面积
			probean.setCode(ApplicationData.PROBLEM_MJCODE_ROOM);
		} else if (i == 2) {// 结构
			probean.setCode(ApplicationData.PROBLEM_JGCODE_ROOM);
		} else {// 类型
			probean.setCode(ApplicationData.PROBLEM_LXCODE_ROOM);
		}
		bundle.putSerializable("probean", probean);
		bundle.putSerializable("bean", bean);
		bundle.putString("flag", ApplicationData.PROBLEM_TYPE_ROOM);
		bundle.putString("code", code);
		bundle.putString("protype", ApplicationData.PROBLEM_TYPE_ROOM);
		Intent it = new Intent(getApplicationContext(), QuestionDetailActivity.class);
		it.putExtras(bundle);
		NewJifangDetailActivity.this.startActivityForResult(it, SUCCESS);
	}

	/**
	 * 
	 * 此方法描述的是：编号拍照跳转
	 * @Title: forWard 
	 * @author: 罗然
	 * @return void    返回类型
	 * @version: 2015-8-24
	下午2:33:28
	 */
	protected void forWard(AppRoomBean room,int flag) {
		bundle = new Bundle();
		bundle.putSerializable("bean", bean);
		bundle.putString("flag", flag+"");//0：新增页面 1、 2、3：明细页面
		if(flag==1){
			bundle.putString("code", roomBean1.getCode());
		}else if(flag==2){
			bundle.putString("code", roomBean2.getCode());
		}else if(flag==3){
			bundle.putString("code", roomBean3.getCode());
		}
		Intent it = new Intent(getApplicationContext(), JiFangDbbhActivity.class);
		it.putExtras(bundle);
		NewJifangDetailActivity.this.startActivityForResult(it, flag);
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
				break;

			case 4:
				// toastMessage("更新成功！");
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags + "");
				forwardIntent(getApplicationContext(),
						NewKtDetailActivity.class, bundle);
				break;
			case -4:
				// toastMessage("更新失败！");
				break;
			case 2:
				add.setVisibility(View.VISIBLE);
				loadList();
				// toastMessage("删除成功");
				// finish();
				break;
			case -2:
				// toastMessage("删除失败");
				break;

			}
		}
	};

	/**
	 * 
	 * 此方法描述的是：删除
	 * 
	 * @Title: delData
	 * @author: 罗然
	 * @param roomBean
	 * @return void 返回类型
	 * @version: 2015-7-27 上午9:17:31
	 */
	protected void delData() {
		db = mOpenHelper.getReadableDatabase();
		try {
			// 开始事务
			db.beginTransaction();
			if (delflag == 1) {
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),
						roomBean1.getCode(),null, "02");
			} else if (delflag == 2) {
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),
						roomBean2.getCode(),null, "02");
			} else if (delflag == 3) {
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(),
						roomBean3.getCode(),null, "02");
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

	/********************************************* 封装保存对象 ********************************************************/
	// 封装更新对象
	public void saveData() {
		// 更新数据库信息
		try {
			int beansize1 = 0, beansize2 = 0, beansize3 = 0;// 机房数量
			// int comsize1 = 0, comsize2 = 0, comsize3 = 0;// 匹配的运营商数量
			int zzccomsize1 = 0, zzccomsize2 = 0, zzccomsize3 = 0;// 匹配的主设备数量

			// 封装机房信息
			List<AppRoomBean> newlist = new ArrayList<AppRoomBean>();
			AppRoomBean roomBean = new AppRoomBean();
			if (linear1.getVisibility() == View.VISIBLE) {
				if (jfmj1.getText().toString() == null
						|| jfmj1.getText().toString().equals("")) {
					toastMessage("请输入机房01的机房面积！");
					return;
				}
				beansize1++;
				roomBean.setAssetNum(roomBean1.getAssetNum());
				roomBean.setCheckUserId(ApplicationData.user.getId() + "");
				roomBean.setCode(roomBean1.getCode());
				roomBean.setLx(((Option) jflx1.getSelectedItem()).getValue());
				roomBean.setJg(((Option) jfjg1.getSelectedItem()).getValue());
				roomBean.setPhysicCode(roomBean1.getPhysicCode());
				roomBean.setMj(Double.parseDouble(jfmj1.getText().toString()));
				roomBean.setEwm(qrcode1.getText().toString().trim());
				roomBean.setDbBh(dbbh1.getText().toString().trim());
				if(dbs1.getText().toString().trim()!=null && !dbs1.getText().toString().trim().equals("")){
					roomBean.setDbDs(Double.parseDouble(dbs1.getText().toString().trim()));
				}
				//				roomBean.setJfYs(((Option) jfys1.getSelectedItem()).getValue());
				StringBuffer jfyssbf=new StringBuffer();
				if(jfys_mjk1.isChecked()){
					jfyssbf.append("1");
				}
				if(jfys_tyys1.isChecked()){
					jfyssbf.append("2");
				}
				if(jfys_zyys1.isChecked()){
					jfyssbf.append("3");
				}
				if(jfys_wyys1.isChecked()){
					jfyssbf.append("4");
				}
				if(jfyssbf!=null){
					roomBean.setJfYs(jfyssbf.toString());
				}else{
					roomBean.setJfYs("0");
				}
				roomBean.setJfYsJj(((Option) ysjj1.getSelectedItem()).getValue());
				newlist.add(roomBean);
			}
			if (linear2.getVisibility() == View.VISIBLE) {
				if (jfmj2.getText().toString() == null
						|| jfmj2.getText().toString().equals("")) {
					toastMessage("请输入机房02的机房面积！");
					return;
				}
				beansize2++;
				roomBean = new AppRoomBean();
				roomBean.setAssetNum(roomBean2.getAssetNum());
				roomBean.setCheckUserId(ApplicationData.user.getId() + "");
				roomBean.setCode(roomBean2.getCode());
				roomBean.setLx(((Option) jflx2.getSelectedItem()).getValue());
				roomBean.setJg(((Option) jfjg2.getSelectedItem()).getValue());
				roomBean.setPhysicCode(roomBean2.getPhysicCode());
				roomBean.setMj(Double.parseDouble(jfmj2.getText().toString()));
				roomBean.setEwm(qrcode2.getText().toString().trim());
				roomBean.setDbBh(dbbh2.getText().toString().trim());
				if(dbs2.getText().toString().trim()!=null && !dbs2.getText().toString().trim().equals("")){
					roomBean.setDbDs(Double.parseDouble(dbs2.getText().toString().trim()));
				}
				//				roomBean.setJfYs(((Option) jfys2.getSelectedItem()).getValue());
				StringBuffer jfyssbf=new StringBuffer();
				if(jfys_mjk2.isChecked()){
					jfyssbf.append("1");
				}
				if(jfys_tyys2.isChecked()){
					jfyssbf.append("2");
				}
				if(jfys_zyys2.isChecked()){
					jfyssbf.append("3");
				}
				if(jfys_wyys2.isChecked()){
					jfyssbf.append("4");
				}
				if(jfyssbf!=null){
					roomBean.setJfYs(jfyssbf.toString());
				}else{
					roomBean.setJfYs("0");
				}
				roomBean.setJfYsJj(((Option) ysjj2.getSelectedItem()).getValue());
				newlist.add(roomBean);
			}
			if (linear3.getVisibility() == View.VISIBLE) {
				if (jfmj3.getText().toString() == null
						|| jfmj3.getText().toString().equals("")) {
					toastMessage("请输入机房03的机房面积！");
					return;
				}
				beansize3++;
				roomBean = new AppRoomBean();
				roomBean.setAssetNum(roomBean3.getAssetNum());
				roomBean.setCheckUserId(ApplicationData.user.getId() + "");
				roomBean.setCode(roomBean3.getCode());
				roomBean.setLx(((Option) jflx3.getSelectedItem()).getValue());
				roomBean.setJg(((Option) jfjg3.getSelectedItem()).getValue());
				roomBean.setPhysicCode(roomBean3.getPhysicCode());
				roomBean.setMj(Double.parseDouble(jfmj3.getText().toString()));
				roomBean.setEwm(qrcode3.getText().toString().trim());
				roomBean.setDbBh(dbbh3.getText().toString().trim());
				if(dbs3.getText().toString().trim()!=null && !dbs3.getText().toString().trim().equals("")){
					roomBean.setDbDs(Double.parseDouble(dbs3.getText().toString().trim()));
				}
				//				roomBean.setJfYs(((Option) jfys3.getSelectedItem()).getValue());
				StringBuffer jfyssbf=new StringBuffer();
				if(jfys_mjk3.isChecked()){
					jfyssbf.append("1");
				}
				if(jfys_tyys3.isChecked()){
					jfyssbf.append("2");
				}
				if(jfys_zyys3.isChecked()){
					jfyssbf.append("3");
				}
				if(jfys_wyys3.isChecked()){
					jfyssbf.append("4");
				}
				if(jfyssbf!=null){
					roomBean.setJfYs(jfyssbf.toString());
				}else{
					roomBean.setJfYs("0");
				}
				roomBean.setJfYsJj(((Option) ysjj3.getSelectedItem()).getValue());
				newlist.add(roomBean);
			}
			// 封装运营商数据
			AppRoomComBean comBean = new AppRoomComBean();
			List<AppRoomComBean> newcomlist = new ArrayList<AppRoomComBean>();
			if (linearyys1.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) yd_ppsj1.getSelectedItem())
						.getText();
				String sblxStr = ((Option) yd_sblx1.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("机房01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("机房02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("机房03")) {
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
				newcomlist.add(comBean);
			}
			if (linearyys2.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) yd_ppsj2.getSelectedItem())
						.getText();
				String sblxStr = ((Option) yd_sblx2.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("机房01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("机房02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("机房03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppRoomComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(ydcomBean2.getCode());
				comBean.setLinkCode(((Option) yd_ppsj2.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ydcomBean2.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (linearyys3.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) lt_ppsj1.getSelectedItem())
						.getText();
				String sblxStr = ((Option) lt_sblx1.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("机房01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("机房02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("机房03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppRoomComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(ltcomBean1.getCode());
				comBean.setLinkCode(((Option) lt_ppsj1.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ltcomBean1.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (linearyys4.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) lt_ppsj1.getSelectedItem())
						.getText();
				String sblxStr = ((Option) lt_sblx2.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("机房01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("机房02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("机房03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppRoomComBean();
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
					if (linkcodeStr.equals("机房01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("机房02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("机房03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppRoomComBean();
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
					if (linkcodeStr.equals("机房01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("机房02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("机房03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppRoomComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(dxcomBean2.getCode());
				comBean.setLinkCode(((Option) dx_ppsj2.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(dxcomBean2.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (lineary_ydnewadd.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) yd_ppsj3.getSelectedItem())
						.getText();
				String sblxStr = ((Option) yd_sblx3.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("机房01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("机房02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("机房03")) {
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
				newcomlist.add(comBean);
			}
			if (lineary_ltnewadd.getVisibility() == View.VISIBLE) {
				String linkcodeStr = ((Option) lt_ppsj3.getSelectedItem())
						.getText();
				String sblxStr = ((Option) lt_sblx3.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("机房01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("机房02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("机房03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppRoomComBean();
				comBean.setCheckUserId(ApplicationData.user.getId() + "");
				comBean.setCheckValue(sblxStr);
				comBean.setCode(ltcomBean3.getCode());
				comBean.setLinkCode(((Option) lt_ppsj3.getSelectedItem())
						.getValue());
				comBean.setPhysicCode(ltcomBean3.getPhysicCode());
				newcomlist.add(comBean);
			}
			if (lineary_ydnewadd.getVisibility() == View.VISIBLE) {
				String sblxStr = ((Option) dx_sblx3.getSelectedItem())
						.getText();
				String linkcodeStr = ((Option) dx_ppsj3.getSelectedItem())
						.getText();
				if (!linkcodeStr.equals("请选择")) {
					if (linkcodeStr.equals("机房01")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize1++;
						}
					} else if (linkcodeStr.equals("机房02")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize2++;
						}
					} else if (linkcodeStr.equals("机房03")) {
						if (sblxStr.equals("主资产")) {
							zzccomsize3++;
						}
					}
				}
				comBean = new AppRoomComBean();
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
					toastMessage("机房01的设备类型重复设置为主资产");
					return;
				}
			}
			if (beansize2 != 0) {
				if (zzccomsize2 > beansize2) {
					toastMessage("机房02的设备类型重复设置为主资产");
					return;
				}
			}
			if (beansize3 != 0) {
				if (zzccomsize3 > beansize3) {
					toastMessage("机房03的设备类型重复设置为主资产");
					return;
				}
			}

			relative_layout.setVisibility(View.VISIBLE);
			loading.startAnimation(animation);
			// 开始事务
			db = mOpenHelper.getReadableDatabase();
			db.beginTransaction();
			if (newlist != null && newlist.size() > 0) {
				for (AppRoomBean o : newlist) {
					TowerSQliteDbBean.updateRoomData(db, o);
				}
			}
			if (newcomlist != null && newcomlist.size() > 0) {
				for (AppRoomComBean o : newcomlist) {
					TowerSQliteDbBean.updateRoomComData(db, o);
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

	/********************************************* 封装页面数据 ***********************************************/
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
				jfmj1.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					jfmj1.setText(s);
					jfmj1.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
			String str = jfmj1.getText().toString();
			changeRoom("1", str, mjresult1, roomBean1);
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
				jfmj2.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					jfmj2.setText(s);
					jfmj2.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
			String str = jfmj2.getText().toString();
			changeRoom("1", str, mjresult2, roomBean2);
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
				jfmj3.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					jfmj3.setText(s);
					jfmj3.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
			String str = jfmj3.getText().toString();
			changeRoom("1", str, mjresult3, roomBean3);
		}
	}

	/**
	 * 
	 * 此类描述的是：电表数
	 * @author: 罗然
	 * @version: 2015-8-14 下午1:57:18
	 * @ClassName: mTextWatcher4 
	 * @项目： towerzj_app
	 * @包：com.woyi.towerzj_app.activity.jifang
	 */
	class mTextWatcher4 implements TextWatcher {

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
				dbs1.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					dbs1.setText(s);
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	/**
	 * 
	 * 此类描述的是：电表数
	 * @author: 罗然
	 * @version: 2015-8-14 下午1:58:41
	 * @ClassName: mTextWatcher5 
	 * @项目： towerzj_app
	 * @包：com.woyi.towerzj_app.activity.jifang
	 */
	class mTextWatcher5 implements TextWatcher {

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
				dbs2.setText("");
			}else if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					dbs2.setText(s);
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	/**
	 * 
	 * 此类描述的是：电表数
	 * @author: 罗然
	 * @version: 2015-8-14 下午1:58:41
	 * @ClassName: mTextWatcher5 
	 * @项目： towerzj_app
	 * @包：com.woyi.towerzj_app.activity.jifang
	 */
	class mTextWatcher6 implements TextWatcher {

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
				dbs3.setText("");
			}else if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					dbs3.setText(s);
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	/**
	 * 类型
	 */
	private AdapterView.OnItemSelectedListener lxlistener1 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) jflx1.getSelectedItem()).getText();
			changeRoom("2", str, lxresult1, roomBean1);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 类型
	 */
	private AdapterView.OnItemSelectedListener lxlistener2 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) jflx2.getSelectedItem()).getText();
			changeRoom("2", str, lxresult2, roomBean2);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 类型
	 */
	private AdapterView.OnItemSelectedListener lxlistener3 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) jflx3.getSelectedItem()).getText();
			changeRoom("2", str, lxresult3, roomBean3);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 机构
	 */
	private AdapterView.OnItemSelectedListener jglistener1 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) jfjg1.getSelectedItem()).getText();
			str = ApplicationData.reMap.get(str);
			if (str != null && !str.equals("")) {
				changeRoom("3", str, jgresult1, roomBean1);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 机构
	 */
	private AdapterView.OnItemSelectedListener jglistener2 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) jfjg2.getSelectedItem()).getText();
			str = ApplicationData.reMap.get(str);
			if (str != null && !str.equals("")) {
				changeRoom("3", str, jgresult2, roomBean2);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 机构
	 */
	private AdapterView.OnItemSelectedListener jglistener3 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) jfjg3.getSelectedItem()).getText();
			str = ApplicationData.reMap.get(str);
			if (str != null && !str.equals("")) {
				changeRoom("3", str, jgresult3, roomBean3);
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
	public void changeRoom(String type, String mcstr, TextView result,
			AppRoomBean appBean) {
		ISPP = false;
		if (mcstr != null && !mcstr.equals("")) {
			mcstr = mcstr.trim();
		}
		if (result == lxresult1 || result == lxresult2 || result == lxresult3) {
			lxStr="";
		}
		if (result == jgresult1 || result == jgresult2 || result == jgresult3) {
			jgStr="";
		}
		if (result == mjresult1 || result == mjresult2 || result == mjresult3) {
			mjStr="";
		}
		if (type.equals("1")) {
			if (mcstr != null && !mcstr.equals("")) {
				Double str = Double.parseDouble(mcstr);
				if (linearyys1.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) yd_ppsj1.getSelectedItem()).getValue())) {
						if (((Option) yd_sblx1.getSelectedItem()).getText()
								.equals("主资产")) {
							mjStr = loadMjData(str, ydcomBean1);
						}
					}
				}
				if (!ISPP) {
					if (linearyys2.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) yd_ppsj2.getSelectedItem())
								.getValue())) {
							if (((Option) yd_sblx2.getSelectedItem()).getText()
									.equals("主资产")) {
								mjStr = loadMjData(str, ydcomBean2);
							}
						}
					}
				}
				if (!ISPP) {
					if (lineary_ydnewadd.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) yd_ppsj3.getSelectedItem())
								.getValue())) {
							if (((Option) yd_sblx3.getSelectedItem()).getText()
									.equals("主资产")) {
								mjStr = loadMjData(str, ydcomBean3);
							}
						}
					}
				}
				if (!ISPP) {
					if (linearyys3.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) lt_ppsj1.getSelectedItem())
								.getValue())) {
							if (((Option) lt_sblx1.getSelectedItem()).getText()
									.equals("主资产")) {
								mjStr = loadMjData(str, ltcomBean1);
							}
						}
					}
				}
				if (!ISPP) {
					if (linearyys4.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) lt_ppsj2.getSelectedItem())
								.getValue())) {
							if (((Option) lt_sblx2.getSelectedItem()).getText()
									.equals("主资产")) {
								mjStr = loadMjData(str, ltcomBean2);
							}
						}
					}
				}
				if (!ISPP) {
					if (lineary_ltnewadd.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) lt_ppsj3.getSelectedItem())
								.getValue())) {
							if (((Option) lt_sblx3.getSelectedItem()).getText()
									.equals("主资产")) {
								mjStr = loadMjData(str, ltcomBean3);
							}
						}
					}
				}
				if (!ISPP) {
					if (linearyys5.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) dx_ppsj1.getSelectedItem())
								.getValue())) {
							if (((Option) dx_sblx1.getSelectedItem()).getText()
									.equals("主资产")) {
								mjStr = loadMjData(str, dxcomBean1);
							}
						}
					}
				}
				if (!ISPP) {
					if (linearyys6.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) dx_ppsj2.getSelectedItem())
								.getValue())) {
							if (((Option) dx_sblx2.getSelectedItem()).getText()
									.equals("主资产")) {
								mjStr = loadMjData(str, dxcomBean2);
							}
						}
					}
				}
				if (!ISPP) {
					if (lineary_dxnewadd.getVisibility() == View.VISIBLE) {
						if (appBean.getCode().equals(
								((Option) dx_ppsj3.getSelectedItem())
								.getValue())) {
							if (((Option) dx_sblx3.getSelectedItem()).getText()
									.equals("主资产")) {
								mjStr = loadMjData(str, dxcomBean3);
							}
						}
					}
				}
			}
		} else if (type.equals("2")) {
			if (linearyys1.getVisibility() == View.VISIBLE) {
				if (appBean.getCode().equals(
						((Option) yd_ppsj1.getSelectedItem()).getValue())) {
					if (((Option) yd_sblx1.getSelectedItem()).getText().equals(
							"主资产")) {
						lxStr = loadLxData(mcstr, ydcomBean1);
					}
				}
			}
			if (!ISPP) {
				if (linearyys2.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) yd_ppsj2.getSelectedItem()).getValue())) {
						if (((Option) yd_sblx2.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, ydcomBean2);
						}
					}
				}
			}
			if (!ISPP) {
				if (lineary_ydnewadd.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
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
					if (appBean.getCode().equals(
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
					if (appBean.getCode().equals(
							((Option) lt_ppsj2.getSelectedItem()).getValue())) {
						if (((Option) lt_sblx2.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, ltcomBean2);
						}
					}
				}
			}
			if (!ISPP) {
				if (lineary_ltnewadd.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
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
					if (appBean.getCode().equals(
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
					if (appBean.getCode().equals(
							((Option) dx_ppsj2.getSelectedItem()).getValue())) {
						if (((Option) dx_sblx2.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, dxcomBean2);
						}
					}
				}
			}
			if (!ISPP) {
				if (lineary_dxnewadd.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) dx_ppsj3.getSelectedItem()).getValue())) {
						if (((Option) dx_sblx3.getSelectedItem()).getText()
								.equals("主资产")) {
							lxStr = loadLxData(mcstr, dxcomBean3);
						}
					}
				}
			}
		} else if (type.equals("3")) {
			if (linearyys1.getVisibility() == View.VISIBLE) {
				if (appBean.getCode().equals(
						((Option) yd_ppsj1.getSelectedItem()).getValue())) {
					if (((Option) yd_sblx1.getSelectedItem()).getText().equals(
							"主资产")) {
						jgStr = loadJgData(mcstr, ydcomBean1);
					}
				}
			}
			if (!ISPP) {
				if (linearyys2.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) yd_ppsj2.getSelectedItem()).getValue())) {
						if (((Option) yd_sblx2.getSelectedItem()).getText()
								.equals("主资产")) {
							jgStr = loadJgData(mcstr, ydcomBean2);
						}
					}
				}
			}
			if (!ISPP) {
				if (lineary_ydnewadd.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) yd_ppsj3.getSelectedItem()).getValue())) {
						if (((Option) yd_sblx3.getSelectedItem()).getText()
								.equals("主资产")) {
							jgStr = loadJgData(mcstr, ydcomBean3);
						}
					}
				}
			}
			if (!ISPP) {
				if (linearyys3.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) lt_ppsj1.getSelectedItem()).getValue())) {
						if (((Option) lt_sblx1.getSelectedItem()).getText()
								.equals("主资产")) {
							jgStr = loadJgData(mcstr, ltcomBean1);
						}
					}
				}
			}
			if (!ISPP) {
				if (linearyys4.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) lt_ppsj2.getSelectedItem()).getValue())) {
						if (((Option) lt_sblx2.getSelectedItem()).getText()
								.equals("主资产")) {
							jgStr = loadJgData(mcstr, ltcomBean2);
						}
					}
				}
			}
			if (!ISPP) {
				if (lineary_ltnewadd.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) lt_ppsj3.getSelectedItem()).getValue())) {
						if (((Option) lt_sblx3.getSelectedItem()).getText()
								.equals("主资产")) {
							jgStr = loadJgData(mcstr, ltcomBean3);
						}
					}
				}
			}
			if (!ISPP) {
				if (linearyys5.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) dx_ppsj1.getSelectedItem()).getValue())) {
						if (((Option) dx_sblx1.getSelectedItem()).getText()
								.equals("主资产")) {
							jgStr = loadJgData(mcstr, dxcomBean1);
						}
					}
				}
			}
			if (!ISPP) {
				if (linearyys6.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) dx_ppsj2.getSelectedItem()).getValue())) {
						if (((Option) dx_sblx2.getSelectedItem()).getText()
								.equals("主资产")) {
							jgStr = loadJgData(mcstr, dxcomBean2);
						}
					}
				}
			}
			if (!ISPP) {
				if (lineary_dxnewadd.getVisibility() == View.VISIBLE) {
					if (appBean.getCode().equals(
							((Option) dx_ppsj3.getSelectedItem()).getValue())) {
						if (((Option) dx_sblx3.getSelectedItem()).getText()
								.equals("主资产")) {
							jgStr = loadJgData(mcstr, dxcomBean3);
						}
					}
				}
			}
		}
		if (result == lxresult1 || result == lxresult2 || result == lxresult3) {
			result.setText(lxStr);
		}
		if (result == jgresult1 || result == jgresult2 || result == jgresult3) {
			result.setText(jgStr);
		}
		if (result == mjresult1 || result == mjresult2 || result == mjresult3) {
			result.setText(mjStr);
		}
	}

	/**
	 * 
	 * 此方法描述的是：判断机房面积是否匹配
	 * 
	 * @Title: loadMjData
	 * @author: 罗然
	 * @param str
	 * @param comBean
	 * @return
	 * @return String 返回类型
	 * @version: 2015-8-11 上午11:46:37
	 */
	private String loadMjData(Double str, AppRoomComBean comBean) {
		String mjstr = "";
		if (Math.abs(str - comBean.getMj()) > 2) {
			ISPP = true;
			mjstr = "机房面积不匹配";
		} else {
			mjstr = "";
		}
		return mjstr;
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
	 * @version: 2015-8-11 下午1:34:08
	 */
	private String loadLxData(String lxstr, AppRoomComBean comBean) {
		String str = "";
		if (!lxstr.equals(comBean.getLx())) {
			ISPP = true;
			str = "机房类型不匹配";
		} else {
			str = "";
		}
		return str;
	}

	/**
	 * 
	 * 此方法描述的是：判断机房结构是否匹配
	 * 
	 * @Title: loadLXData
	 * @author: 罗然
	 * @param ppstr
	 * @param comBean
	 * @return
	 * @return String 返回类型
	 * @version: 2015-8-11 上午11:55:07
	 */
	private String loadJgData(String jgstr, AppRoomComBean comBean) {
		String str = "";
		if (jgstr != null && !jgstr.equals("")) {
			if (!jgstr.equals(comBean.getJg())) {
				ISPP = true;
				str = "机房结构不匹配";
			} else {
				str = "";
			}
		} else {
			str = "机房结构不匹配";
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
				if (ydsbflag1.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ydsbflag1.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ydsbflag1.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
				ydsbflag1 = "";
			}
			if (((Option) yd_sblx1.getSelectedItem()).getText().equals("主资产")) {
				if(!str.equals("请选择")){
					ydsbflag1 = str;
					changeRoomCom(str, ydcomBean1);
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
				if (ydsbflag2.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ydsbflag2.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ydsbflag2.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
				ydsbflag2 = "";
			}
			if (((Option) yd_sblx2.getSelectedItem()).getText().equals("主资产")) {
				if(!str.equals("请选择")){
					ydsbflag2 = str;
					changeRoomCom(str, ydcomBean2);
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
				if (ydsbflag3.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ydsbflag3.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ydsbflag3.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
				ydsbflag3 = "";
			}
			if (((Option) yd_sblx3.getSelectedItem()).getText().equals("主资产")) {
				if(!str.equals("请选择")){
					ydsbflag3 = str;
					changeRoomCom(str, ydcomBean3);
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
				if (ltsbflag1.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ltsbflag1.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ltsbflag1.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
				ltsbflag1 = "";
			}
			if (((Option) lt_sblx1.getSelectedItem()).getText().equals("主资产")) {
				if(!str.equals("请选择")){
					ltsbflag1 = str;
					changeRoomCom(str, ltcomBean1);
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
				if (ltsbflag2.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ltsbflag2.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ltsbflag2.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
				ltsbflag2 = "";
			}
			if (((Option) lt_sblx2.getSelectedItem()).getText().equals("主资产")) {
				if(!str.equals("请选择")){
					ltsbflag2 = str;
					changeRoomCom(str, ltcomBean2);
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
				if (ltsbflag3.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ltsbflag3.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ltsbflag3.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
				ltsbflag3 = "";
			}
			if (((Option) lt_sblx3.getSelectedItem()).getText().equals("主资产")) {
				if(!str.equals("请选择")){
					ltsbflag3 = str;
					changeRoomCom(str, ltcomBean3);
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
				if (dxsbflag1.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (dxsbflag1.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (dxsbflag1.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
				dxsbflag1 = "";
			}
			if (((Option) dx_sblx1.getSelectedItem()).getText().equals("主资产")) {
				if(!str.equals("请选择")){
					dxsbflag1 = str;
					changeRoomCom(str, dxcomBean1);
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
				if (dxsbflag2.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (dxsbflag2.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (dxsbflag2.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
				dxsbflag2 = "";
			}
			if (((Option) dx_sblx2.getSelectedItem()).getText().equals("主资产")) {
				if(!str.equals("请选择")){
					dxsbflag2 = str;
					changeRoomCom(str, dxcomBean2);
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
				if (dxsbflag3.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (dxsbflag3.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (dxsbflag3.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
				dxsbflag3 = "";
			}
			if (((Option) dx_sblx3.getSelectedItem()).getText().equals("主资产")) {
				if(!str.equals("请选择")){
					dxsbflag3 = str;
					changeRoomCom(str, dxcomBean3);
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
				if (ydppflag1.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ydppflag1.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ydppflag1.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
			}
			if (((Option) yd_sblx1.getSelectedItem()).getText().equals("主资产")) {
				ydppflag1 = str;
				changeRoomCom(str, ydcomBean1);
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
				if (ydppflag2.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ydppflag2.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ydppflag2.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
			}
			if (((Option) yd_sblx2.getSelectedItem()).getText().equals("主资产")) {
				ydppflag2 = str;
				changeRoomCom(str, ydcomBean2);
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
				if (ydppflag3.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ydppflag3.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ydppflag3.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
			}
			if (((Option) yd_sblx3.getSelectedItem()).getText().equals("主资产")) {
				ydppflag3 = str;
				changeRoomCom(str, ydcomBean3);
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
				if (ltppflag1.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ltppflag1.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ltppflag1.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
			}
			if (((Option) lt_sblx1.getSelectedItem()).getText().equals("主资产")) {
				ltppflag1 = str;
				changeRoomCom(str, ltcomBean1);
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
				if (ltppflag2.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ltppflag2.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ltppflag2.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
			}
			if (((Option) lt_sblx2.getSelectedItem()).getText().equals("主资产")) {
				ltppflag2 = str;
				changeRoomCom(str, ltcomBean2);
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
				if (ltppflag3.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (ltppflag3.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (ltppflag3.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
			}
			if (((Option) lt_sblx3.getSelectedItem()).getText().equals("主资产")) {
				ltppflag3 = str;
				changeRoomCom(str, ltcomBean3);
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
				if (dxppflag1.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (dxppflag1.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (dxppflag1.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
			}
			if (((Option) dx_sblx1.getSelectedItem()).getText().equals("主资产")) {
				dxppflag1 = str;
				changeRoomCom(str, dxcomBean1);
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
				if (dxppflag2.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (dxppflag2.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (dxppflag2.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
			}
			if (((Option) dx_sblx2.getSelectedItem()).getText().equals("主资产")) {
				dxppflag2 = str;
				changeRoomCom(str, dxcomBean2);
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
				if (dxppflag3.equals("机房01")) {
					mjresult1.setText("");
					jgresult1.setText("");
					lxresult1.setText("");
				} else if (dxppflag3.equals("机房02")) {
					mjresult2.setText("");
					jgresult2.setText("");
					lxresult2.setText("");
				} else if (dxppflag3.equals("机房03")) {
					mjresult3.setText("");
					jgresult3.setText("");
					lxresult3.setText("");
				}
			}
			if (((Option) dx_sblx3.getSelectedItem()).getText().equals("主资产")) {
				dxppflag3 = str;
				changeRoomCom(str, dxcomBean3);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private void changeRoomCom(String str, AppRoomComBean comBean) {
		if (!str.equals("请选择")) {
			if (str.equals("机房01")) {
				String mjstr = jfmj1.getText().toString();
				if (mjstr != null && !mjstr.equals("")) {
					Double rlstr = Double.parseDouble(mjstr);
					mjStr = loadMjData(rlstr, comBean);
				} else {
					mjStr = "面积不匹配";
				}
				String jgstr = ((Option) jfjg1.getSelectedItem()).getText();
				jgstr = ApplicationData.reMap.get(jgstr);
				jgStr = loadJgData(jgstr, comBean);
				String lxstr = ((Option) jflx1.getSelectedItem()).getText();
				lxStr = loadLxData(lxstr, comBean);
				mjresult1.setText(mjStr);
				jgresult1.setText(jgStr);
				lxresult1.setText(lxStr);
			} else if (str.equals("机房02")) {
				String mjstr = jfmj2.getText().toString();
				if (mjstr != null && !mjstr.equals("")) {
					Double rlstr = Double.parseDouble(mjstr);
					mjStr = loadMjData(rlstr, comBean);
				} else {
					mjStr = "面积不匹配";
				}
				String jgstr = ((Option) jfjg2.getSelectedItem()).getText();
				jgstr = ApplicationData.reMap.get(jgstr);
				jgStr = loadJgData(jgstr, comBean);
				String lxstr = ((Option) jflx2.getSelectedItem()).getText();
				lxStr = loadLxData(lxstr, comBean);
				mjresult2.setText(mjStr);
				jgresult2.setText(jgStr);
				lxresult2.setText(lxStr);
			} else if (str.equals("机房03")) {
				String mjstr = jfmj3.getText().toString();
				if (mjstr != null && !mjstr.equals("")) {
					Double rlstr = Double.parseDouble(mjstr);
					mjStr = loadMjData(rlstr, comBean);
				} else {
					mjStr = "面积不匹配";
				}
				String jgstr = ((Option) jfjg3.getSelectedItem()).getText();
				jgstr = ApplicationData.reMap.get(jgstr);
				jgStr = loadJgData(jgstr, comBean);
				String lxstr = ((Option) jflx3.getSelectedItem()).getText();
				lxStr = loadLxData(lxstr, comBean);
				mjresult3.setText(mjStr);
				jgresult3.setText(jgStr);
				lxresult3.setText(lxStr);
			}
		}
	}

	/**
	 * 
	 * 此方法描述的是：加载页面数据
	 * 
	 * @Title: loadData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-27 上午9:19:02
	 */
	protected void loadData() {
		ppsjList = new ArrayList<Option>();
		Option option = null;
		//		if (list != null && list.size() > 1) {
		//			option = new Option();
		//			option.setText("请选择");
		//			option.setValue("0");
		//			ppsjList.add(option);
		//		} else if (list != null && comlist != null
		//				&& list.size() < comlist.size()) {
		//			option = new Option();
		//			option.setText("请选择");
		//			option.setValue("0");
		//			ppsjList.add(option);
		//		}
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					option = new Option();
					linear1.setVisibility(View.VISIBLE);
					roomBean1 = list.get(i);
					mc1.setText("机房01");
					jfmj1.setText(roomBean1.getMj() + "");
					dbs1.setText(roomBean1.getDbDs()+"");
					if(roomBean1.getDbBh()!=null && !roomBean1.getDbBh().equals("")&& !roomBean1.getDbBh().equals("null")){
						dbbh1.setText(roomBean1.getDbBh());
					}
					String jfysStr=roomBean1.getJfYs();
					if (jfysStr != null && !jfysStr.equals("")&&!jfysStr.equals("null")){
						if(jfysStr.contains("1")){
							jfys_mjk1.setChecked(true);
						}
						if(jfysStr.contains("2")){
							jfys_tyys1.setChecked(true);
						}
						if(jfysStr.contains("3")){
							jfys_zyys1.setChecked(true);
						}
						if(jfysStr.contains("4")){
							jfys_wyys1.setChecked(true);
						}
					}
					//				fillSpinnerAdapter(SpinnerUtilBean.getRoomys(), jfys1, "请选择机房钥匙");
					//				if (roomBean1.getJfYs() != null && !roomBean1.getJfYs().equals("")) {
					//					selectedSpinner(SpinnerUtilBean.getRoomys(),
					//							roomBean1.getJfYs(), jfys1);
					//				}
					fillSpinnerAdapter(SpinnerUtilBean.getRoomysjj(), ysjj1, "请选择机房钥匙是否交接");
					if (roomBean1.getJfYsJj() != null && !roomBean1.getJfYsJj().equals("")) {
						selectedSpinner(SpinnerUtilBean.getRoomysjj(),
								roomBean1.getJfYsJj(), ysjj1);
					}

					fillSpinnerAdapter(SpinnerUtilBean.getJflx(), jflx1, "请选择机房类型");
					if (roomBean1.getLx() != null && !roomBean1.getLx().equals("")) {
						selectedSpinner(SpinnerUtilBean.getJflx(),
								roomBean1.getLx(), jflx1);
					}
					fillSpinnerAdapter(SpinnerUtilBean.getJfjg(), jfjg1, "请选择机房结构");
					if (roomBean1.getJg() != null && !roomBean1.getJg().equals("")) {
						selectedSpinner(SpinnerUtilBean.getJfjg(),
								roomBean1.getJg(), jfjg1);
					}
					if (roomBean1.getEwm() != null
							&& !roomBean1.getEwm().equals("")
							&& !roomBean1.getEwm().equals("null")) {
						qrcode1.setText(roomBean1.getEwm());
						qrcodeimg1.setVisibility(View.GONE);
					} else {
						// qrcode1.setText("");
					}
					option.setValue(roomBean1.getCode());
					option.setText("机房01");
					ppsjList.add(option);
				}
				if (i == 1) {
					option = new Option();
					linear2.setVisibility(View.VISIBLE);
					roomBean2 = list.get(i);
					mc2.setText("机房02");
					jfmj2.setText(roomBean2.getMj() + "");
					dbs2.setText(roomBean2.getDbDs()+"");
					if(roomBean2.getDbBh()!=null && !roomBean2.getDbBh().equals("")&& !roomBean2.getDbBh().equals("null")){
						dbbh2.setText(roomBean2.getDbBh());
					}
					String jfysStr=roomBean2.getJfYs();
					if (jfysStr != null && !jfysStr.equals("")&&!jfysStr.equals("null")){
						if(jfysStr.contains("1")){
							jfys_mjk2.setChecked(true);
						}
						if(jfysStr.contains("2")){
							jfys_tyys2.setChecked(true);
						}
						if(jfysStr.contains("3")){
							jfys_zyys2.setChecked(true);
						}
						if(jfysStr.contains("4")){
							jfys_wyys2.setChecked(true);
						}
					}
					//				fillSpinnerAdapter(SpinnerUtilBean.getRoomys(), jfys2, "请选择机房钥匙");
					//				if (roomBean2.getJfYs() != null && !roomBean2.getJfYs().equals("")) {
					//					selectedSpinner(SpinnerUtilBean.getRoomys(),
					//							roomBean2.getJfYs(), jfys2);
					//				}
					fillSpinnerAdapter(SpinnerUtilBean.getRoomysjj(), ysjj2, "请选择机房钥匙是否交接");
					if (roomBean2.getJfYsJj() != null && !roomBean2.getJfYsJj().equals("")) {
						selectedSpinner(SpinnerUtilBean.getRoomysjj(),
								roomBean2.getJfYsJj(), ysjj2);
					}

					fillSpinnerAdapter(SpinnerUtilBean.getJflx(), jflx2, "请选择机房类型");
					if (roomBean2.getLx() != null && !roomBean2.getLx().equals("")) {
						selectedSpinner(SpinnerUtilBean.getJflx(),
								roomBean2.getLx(), jflx2);
					}
					fillSpinnerAdapter(SpinnerUtilBean.getJfjg(), jfjg2, "请选择机房结构");
					if (roomBean2.getJg() != null && !roomBean2.getJg().equals("")) {
						selectedSpinner(SpinnerUtilBean.getJfjg(),
								roomBean2.getJg(), jfjg2);
					}
					if (roomBean2.getEwm() != null
							&& !roomBean2.getEwm().equals("")
							&& !roomBean2.getEwm().equals("null")) {
						qrcode2.setText(roomBean2.getEwm());
						qrcodeimg2.setVisibility(View.GONE);
					} else {
						// qrcode2.setText("");
					}
					option.setValue(roomBean2.getCode());
					option.setText("机房02");
					ppsjList.add(option);
				}
				if (i == 2) {
					option = new Option();
					linear3.setVisibility(View.VISIBLE);
					roomBean3 = list.get(i);
					mc3.setText("机房03");
					jfmj3.setText(roomBean3.getMj() + "");
					dbs3.setText(roomBean3.getDbDs()+"");
					if(roomBean3.getDbBh()!=null && !roomBean3.getDbBh().equals("")&& !roomBean3.getDbBh().equals("null")){
						dbbh3.setText(roomBean3.getDbBh());
					}
					String jfysStr=roomBean3.getJfYs();
					if (jfysStr != null && !jfysStr.equals("")&&!jfysStr.equals("null")){
						if(jfysStr.contains("1")){
							jfys_mjk3.setChecked(true);
						}
						if(jfysStr.contains("2")){
							jfys_tyys3.setChecked(true);
						}
						if(jfysStr.contains("3")){
							jfys_zyys3.setChecked(true);
						}
						if(jfysStr.contains("4")){
							jfys_wyys3.setChecked(true);
						}
					}
					//				fillSpinnerAdapter(SpinnerUtilBean.getRoomys(), jfys3, "请选择机房钥匙");
					//				if (roomBean3.getJfYs() != null && !roomBean3.getJfYs().equals("")) {
					//					selectedSpinner(SpinnerUtilBean.getRoomys(),
					//							roomBean3.getJfYs(), jfys3);
					//				}
					fillSpinnerAdapter(SpinnerUtilBean.getRoomysjj(), ysjj3, "请选择机房钥匙是否交接");
					if (roomBean3.getJfYsJj() != null && !roomBean3.getJfYsJj().equals("")) {
						selectedSpinner(SpinnerUtilBean.getRoomysjj(),
								roomBean3.getJfYsJj(), ysjj3);
					}

					fillSpinnerAdapter(SpinnerUtilBean.getJflx(), jflx3, "请选择机房类型");
					if (roomBean3.getLx() != null && !roomBean3.getLx().equals("")) {
						selectedSpinner(SpinnerUtilBean.getJflx(),
								roomBean3.getLx(), jflx3);
					}
					fillSpinnerAdapter(SpinnerUtilBean.getJfjg(), jfjg3, "请选择机房结构");
					if (roomBean3.getJg() != null && !roomBean3.getJg().equals("")) {
						selectedSpinner(SpinnerUtilBean.getJfjg(),
								roomBean3.getJg(), jfjg3);
					}
					if (roomBean3.getEwm() != null
							&& !roomBean3.getEwm().equals("")
							&& !roomBean3.getEwm().equals("null")) {
						qrcode3.setText(roomBean3.getEwm());
						qrcodeimg3.setVisibility(View.GONE);
					} else {
						// qrcode3.setText("");
					}
					option.setValue(roomBean3.getCode());
					option.setText("机房03");
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
			List<AppRoomComBean> ydlist = new ArrayList<AppRoomComBean>();
			List<AppRoomComBean> ltlist = new ArrayList<AppRoomComBean>();
			List<AppRoomComBean> dxlist = new ArrayList<AppRoomComBean>();
			for (AppRoomComBean o : comlist) {
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
						if (ydcomBean1.getLx() != null
								&& !ydcomBean1.getLx().equals("")
								&& !ydcomBean1.getLx().equals("null")) {
							yd_jflx1.setText(ydcomBean1.getLx());
						} else {
							yd_jflx1.setText("无");
						}
						if (ydcomBean1.getJg() != null
								&& !ydcomBean1.getJg().equals("")
								&& !ydcomBean1.getJg().equals("null")) {
							yd_jfjg1.setText(ydcomBean1.getJg());
						} else {
							yd_jfjg1.setText("无");
						}
						if (!TextUtils.isEmpty(ydcomBean1.getMj() + "")) {
							yd_jfmj1.setText(ydcomBean1.getMj() + "");
						} else {
							yd_jfmj1.setText("无");
						}

						//						if (comFlag) {
						//							ydcomBean1.setCheckValue("主资产");
						//							yd_sblx1.setClickable(false);
						//							yd_sblx1.setSelection(1);
						//						}else{
						if (ydcomBean1.getCheckValue() != null
								&& !ydcomBean1.getCheckValue().equals("")) {
							selectedSblxSpinner(SpinnerUtilBean.getSblx(),
									ydcomBean1.getCheckValue(), yd_sblx1);
						}
						//								yd_sblx1.setClickable(true);
						//						}

						selectedSpinner(ppsjList, ydcomBean1.getLinkCode(),
								yd_ppsj1);
					}
					if (i == 1) {
						linearyys2.setVisibility(View.VISIBLE);
						ydcomBean2 = ydlist.get(i);
						tv2.setText("中国移动");
						if (ydcomBean2.getLx() != null
								&& !ydcomBean2.getLx().equals("")
								&& !ydcomBean2.getLx().equals("null")) {
							yd_jflx2.setText(ydcomBean2.getLx());
						} else {
							yd_jflx2.setText("无");
						}
						if (ydcomBean2.getJg() != null
								&& !ydcomBean2.getJg().equals("")
								&& !ydcomBean2.getJg().equals("null")) {
							yd_jfjg2.setText(ydcomBean2.getJg());
						} else {
							yd_jfjg2.setText("无");
						}
						if (!TextUtils.isEmpty(ydcomBean2.getMj() + "")) {
							yd_jfmj2.setText(ydcomBean2.getMj() + "");
						} else {
							yd_jfmj2.setText("无");
						}
						//						if (comFlag) {
						//							ydcomBean2.setCheckValue("主资产");
						//							yd_sblx2.setClickable(false);
						//							yd_sblx2.setSelection(1);
						//						}else{
						if (ydcomBean2.getCheckValue() != null
								&& !ydcomBean2.getCheckValue().equals("")) {
							selectedSblxSpinner(SpinnerUtilBean.getSblx(),
									ydcomBean2.getCheckValue(), yd_sblx2);
						}
						//								yd_sblx2.setClickable(true);
						//						}

						selectedSpinner(ppsjList, ydcomBean2.getLinkCode(),
								yd_ppsj2);
					}
					if (i == 2) {
						lineary_ydnewadd.setVisibility(View.VISIBLE);
						ydcomBean3 = ydlist.get(i);
						yd_tv.setText("中国移动");
						if (ydcomBean3.getLx() != null
								&& !ydcomBean3.getLx().equals("")
								&& !ydcomBean3.getLx().equals("null")) {
							yd_jflx3.setText(ydcomBean3.getLx());
						} else {
							yd_jflx3.setText("无");
						}
						if (ydcomBean3.getJg() != null
								&& !ydcomBean3.getJg().equals("")
								&& !ydcomBean3.getJg().equals("null")) {
							yd_jfjg3.setText(ydcomBean3.getJg());
						} else {
							yd_jfjg3.setText("无");
						}
						if (!TextUtils.isEmpty(ydcomBean3.getMj() + "")) {
							yd_jfmj3.setText(ydcomBean3.getMj() + "");
						} else {
							yd_jfmj3.setText("无");
						}
						//						if (comFlag) {
						//							ydcomBean3.setCheckValue("主资产");
						//							yd_sblx3.setClickable(false);
						//							yd_sblx3.setSelection(1);
						//						}else{
						if (ydcomBean3.getCheckValue() != null
								&& !ydcomBean3.getCheckValue().equals("")) {
							selectedSblxSpinner(SpinnerUtilBean.getSblx(),
									ydcomBean3.getCheckValue(), yd_sblx3);
						}
						//								yd_sblx3.setClickable(true);
						//						}

						selectedSpinner(ppsjList, ydcomBean3.getLinkCode(),
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
						if (ltcomBean1.getJg() != null
								&& !ltcomBean1.getJg().equals("")
								&& !ltcomBean1.getJg().equals("null")) {
							lt_jfjg1.setText(ltcomBean1.getJg());
						} else {
							lt_jfjg1.setText("无");
						}
						if (ltcomBean1.getLx() != null
								&& !ltcomBean1.getLx().equals("")
								&& !ltcomBean1.getLx().equals("null")) {
							lt_jflx1.setText(ltcomBean1.getLx());
						} else {
							lt_jflx1.setText("无");
						}
						if (!TextUtils.isEmpty(ltcomBean1.getMj() + "")) {
							lt_jfmj1.setText(ltcomBean1.getMj() + "");
						} else {
							lt_jfmj1.setText("无");
						}

						//						if (comFlag) {
						//							ltcomBean1.setCheckValue("主资产");
						//							lt_sblx1.setClickable(false);
						//							lt_sblx1.setSelection(1);
						//						}else{
						if (ltcomBean1.getCheckValue() != null
								&& !ltcomBean1.getCheckValue().equals("")) {
							selectedSblxSpinner(SpinnerUtilBean.getSblx(),
									ltcomBean1.getCheckValue(), lt_sblx1);
						}
						//								lt_sblx1.setClickable(true);
						//						}
						selectedSpinner(ppsjList, ltcomBean1.getLinkCode(),
								lt_ppsj1);
					}
					if (i == 1) {
						linearyys4.setVisibility(View.VISIBLE);
						ltcomBean2 = ltlist.get(i);
						tv4.setText("中国联通");
						if (ltcomBean2.getJg() != null
								&& !ltcomBean2.getJg().equals("")
								&& !ltcomBean2.getJg().equals("null")) {
							lt_jfjg2.setText(ltcomBean2.getJg());
						} else {
							lt_jfjg2.setText("无");
						}
						if (ltcomBean2.getLx() != null
								&& !ltcomBean2.getLx().equals("")
								&& !ltcomBean2.getLx().equals("null")) {
							lt_jflx2.setText(ltcomBean2.getLx());
						} else {
							lt_jflx2.setText("无");
						}
						if (!TextUtils.isEmpty(ltcomBean2.getMj() + "")) {
							lt_jfmj2.setText(ltcomBean2.getMj() + "");
						} else {
							lt_jfmj2.setText("无");
						}

						//						if (comFlag) {
						//							ltcomBean2.setCheckValue("主资产");
						//							lt_sblx2.setClickable(false);
						//							lt_sblx2.setSelection(1);
						//						}else{
						if (ltcomBean2.getCheckValue() != null
								&& !ltcomBean2.getCheckValue().equals("")) {
							selectedSblxSpinner(SpinnerUtilBean.getSblx(),
									ltcomBean2.getCheckValue(), lt_sblx2);
						}
						//								lt_sblx2.setClickable(true);
						//						}

						selectedSpinner(ppsjList, ltcomBean2.getLinkCode(),
								lt_ppsj2);
					}
					if (i == 2) {
						lineary_ltnewadd.setVisibility(View.VISIBLE);
						ltcomBean3 = ltlist.get(i);
						lt_tv.setText("中国联通");
						if (ltcomBean3.getJg() != null
								&& !ltcomBean3.getJg().equals("")
								&& !ltcomBean3.getJg().equals("null")) {
							lt_jfjg3.setText(ltcomBean3.getJg());
						} else {
							lt_jfjg3.setText("无");
						}
						if (ltcomBean3.getLx() != null
								&& !ltcomBean3.getLx().equals("")
								&& !ltcomBean3.getLx().equals("null")) {
							lt_jflx3.setText(ltcomBean3.getLx());
						} else {
							lt_jflx3.setText("无");
						}
						if (!TextUtils.isEmpty(ltcomBean3.getMj() + "")) {
							lt_jfmj3.setText(ltcomBean3.getMj() + "");
						} else {
							lt_jfmj3.setText("无");
						}

						//						if (comFlag) {
						//							ltcomBean3.setCheckValue("主资产");
						//							lt_sblx3.setClickable(false);
						//							lt_sblx3.setSelection(1);
						//						}else{
						if (ltcomBean3.getCheckValue() != null
								&& !ltcomBean3.getCheckValue().equals("")) {
							selectedSblxSpinner(SpinnerUtilBean.getSblx(),
									ltcomBean3.getCheckValue(), lt_sblx3);
						}
						//								lt_sblx3.setClickable(true);
						//						}

						selectedSpinner(ppsjList, ltcomBean3.getLinkCode(),
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
						if (dxcomBean1.getJg() != null
								&& !dxcomBean1.getJg().equals("")
								&& !dxcomBean1.getJg().equals("null")) {
							dx_jfjg1.setText(dxcomBean1.getJg());
						} else {
							dx_jfjg1.setText("无");
						}
						if (dxcomBean1.getLx() != null
								&& !dxcomBean1.getLx().equals("")
								&& !dxcomBean1.getLx().equals("null")) {
							dx_jflx1.setText(dxcomBean1.getLx());
						} else {
							dx_jflx1.setText("无");
						}
						if (!TextUtils.isEmpty(dxcomBean1.getMj() + "")) {
							dx_jfmj1.setText(dxcomBean1.getMj() + "");
						} else {
							dx_jfmj1.setText("无");
						}

						//						if (comFlag) {
						//							dxcomBean1.setCheckValue("主资产");
						//							dx_sblx1.setClickable(false);
						//							dx_sblx1.setSelection(1);
						//						}else{
						if (dxcomBean1.getCheckValue() != null
								&& !dxcomBean1.getCheckValue().equals("")) {
							selectedSblxSpinner(SpinnerUtilBean.getSblx(),
									dxcomBean1.getCheckValue(), dx_sblx1);
						}
						//								dx_sblx1.setClickable(true);
						//						}

						selectedSpinner(ppsjList, dxcomBean1.getLinkCode(),
								dx_ppsj1);
					}
					if (i == 1) {
						linearyys6.setVisibility(View.VISIBLE);
						dxcomBean2 = dxlist.get(i);
						tv6.setText("中国电信");
						if (dxcomBean2.getJg() != null
								&& !dxcomBean2.getJg().equals("")
								&& !dxcomBean2.getJg().equals("null")) {
							dx_jfjg2.setText(dxcomBean2.getJg());
						} else {
							dx_jfjg2.setText("无");
						}
						if (dxcomBean2.getLx() != null
								&& !dxcomBean2.getLx().equals("")
								&& !dxcomBean2.getLx().equals("null")) {
							dx_jflx2.setText(dxcomBean2.getLx());
						} else {
							dx_jflx2.setText("无");
						}
						if (!TextUtils.isEmpty(dxcomBean2.getMj() + "")) {
							dx_jfmj2.setText(dxcomBean2.getMj() + "");
						} else {
							dx_jfmj2.setText("无");
						}
						//						if (comFlag) {
						//							dxcomBean2.setCheckValue("主资产");
						//							dx_sblx2.setClickable(false);
						//							dx_sblx2.setSelection(1);
						//						}else {
						if (dxcomBean2.getCheckValue() != null
								&& !dxcomBean2.getCheckValue().equals("")) {
							selectedSblxSpinner(SpinnerUtilBean.getSblx(),
									dxcomBean2.getCheckValue(), dx_sblx2);
						}
						//								dx_sblx2.setClickable(true);
						//						}
						selectedSpinner(ppsjList, dxcomBean2.getLinkCode(),
								dx_ppsj2);
					}
					if (i == 2) {
						lineary_dxnewadd.setVisibility(View.VISIBLE);
						dxcomBean3 = dxlist.get(i);
						dx_tv.setText("中国电信");
						if (dxcomBean3.getJg() != null
								&& !dxcomBean3.getJg().equals("")
								&& !dxcomBean3.getJg().equals("null")) {
							dx_jfjg3.setText(dxcomBean3.getJg());
						} else {
							dx_jfjg3.setText("无");
						}
						if (dxcomBean3.getLx() != null
								&& !dxcomBean3.getLx().equals("")
								&& !dxcomBean3.getLx().equals("null")) {
							dx_jflx3.setText(dxcomBean3.getLx());
						} else {
							dx_jflx3.setText("无");
						}
						if (!TextUtils.isEmpty(dxcomBean3.getMj() + "")) {
							dx_jfmj3.setText(dxcomBean3.getMj() + "");
						} else {
							dx_jfmj3.setText("无");
						}
						//						if (comFlag) {
						//							dxcomBean3.setCheckValue("主资产");
						//							dx_sblx3.setClickable(false);
						//							dx_sblx3.setSelection(1);
						//						}else{
						if (dxcomBean3.getCheckValue() != null
								&& !dxcomBean3.getCheckValue().equals("")) {
							selectedSblxSpinner(SpinnerUtilBean.getSblx(),
									dxcomBean3.getCheckValue(), dx_sblx2);
						}
						//							dx_sblx3.setClickable(true);
						//						}
						selectedSpinner(ppsjList, dxcomBean3.getLinkCode(),
								dx_ppsj3);
					}
				}
			}
		}

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
			qrcode1.setText(text);
		} else if (data != null && requestCode == code2) {
			text = data.getExtras().getString(res_text);
			qrcode2.setText(text);
		} else if (data != null && requestCode == code3) {
			text = data.getExtras().getString(res_text);
			qrcode3.setText(text);
		}else if( data != null && requestCode == SUCCESS){

		}else if(data!=null && requestCode==DBBHSUCCESS1){

		}else if(data!=null && requestCode==DBBHSUCCESS2){

		}else if(data!=null && requestCode==DBBHSUCCESS3){

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
	 * 
	 * 此方法描述的是：设备类型
	 * @Title: selectedSblxSpinner 
	 * @author: 罗然
	 * @param option
	 * @param str
	 * @param spinner
	 * @return void    返回类型
	 * @version: 2015-9-1
	下午2:37:31
	 */
	private void selectedSblxSpinner(List<Option> option, String str,
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
			del = (Button) mMenuView.findViewById(R.id.del);
			query2 = (Button) mMenuView.findViewById(R.id.query2);
			del_tv = (TextView) mMenuView.findViewById(R.id.del_tv);
			del_tv.setText("将删除此机房信息");
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

	//	@Override
	//	protected void onResume() {
	//		super.onResume();
	//		relative_layout.setVisibility(View.VISIBLE);
	//		loading.startAnimation(animation);
	//		loadList();
	//	}

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
		bundle = new Bundle();
		bundle.putSerializable("bean", bean);
		bundle.putString("flag", flags+"");
		forwardIntent(getApplicationContext(), NewTaweiDetailActivity.class,
				bundle);
		finish();
	}

}
