package com.woyi.towerzj_app.activity.tianxian;

import java.util.List;
import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.EndActivity;
import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.activity.RoomPhotoActivity;
import com.woyi.towerzj_app.activity.donghuan.DhDetailActivity;
import com.woyi.towerzj_app.bean.Option;
import com.woyi.towerzj_app.bean.SpinnerUtilBean;
import com.woyi.towerzj_app.bean.tianxian.AppAntennaBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;

/**
 * 
 * 此类描述的是：天线
 * 
 * @author: 罗然
 * @version: 2015-7-18 下午3:01:12
 * @ClassName: TxDetailActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity.tianxian
 */
public class TxDetailActivity extends ForwardActivity implements
		OnClickListener {

	private EditText yd2g900height, yd2g1800height, yd3gheight, yd4gheight,
			ltjjs, dx2gheight, dx3gheight, dx4gheight, lt2g900height,
			lt2g1800height, lt3gheight, lt4gheight, ydjjs, dxjjs, yd4gheightD;
	private Spinner yd2g900, yd2g900pt, yd2g1800, yd2g1800pt, yd3g, yd3gpt,
			yd4g, yd4gpt, dx2g, dx2gpt, dx3g, dx3gpt, dx4g, dx4gpt, lt2g900,
			lt2g900pt, lt2g1800, lt2g1800pt, lt3g, lt3gpt, lt4g, lt4gpt, yd4gD,
			yd4gptD;
	private TextView title, next, add, save;
	private Button back;
	private TextView code, address;

	private LinearLayout linear1, linear2, linear3;
	private ImageView del1, del2, del3;

	private Animation animation;

	private ImageView loading;
	private RelativeLayout relative_layout;

	private AppPhyInfoBean bean;
	private SQLiteDatabase db;
	private DatabaseHelper mOpenHelper;

	private List<AppAntennaBean> list;
	private AppAntennaBean txbean;

	// 删除标记
	private int delflag = 0;
	private DelPopupWindow delWindow;

	private String operStr = "";
	private AppAntennaBean antennaBean;
	private PopupWindow popupWindow;
	// 定义一个标记
	private boolean flag = false;
	private int flags = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		flags = Integer
				.parseInt(this.getIntent().getExtras().getString("flag"));
		mOpenHelper = new DatabaseHelper(this);
		Reflex.loadViewForActivityOnCreate(this,
				R.layout.activity_tianxian_detail);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		title.setText("天线挂高");
		if (bean != null) {
			code.setText(bean.getPhysicAlias());
			address.setText(bean.getPhysicName());
		}
		if (bean.getStatus().equals("3") || bean.getStatus().equals("4")) {
			save.setVisibility(View.GONE);
			add.setVisibility(View.GONE);
		}
		next.setText("跳过");
		loadList();
		init();
	}

	/**
	 * 
	 * 此方法描述的是：初始化页面
	 * 
	 * @Title: init
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-8-3 下午3:42:07
	 */
	private void init() {
		back.setOnClickListener(this);
		next.setOnClickListener(this);
		save.setOnClickListener(this);
		add.setOnClickListener(this);

		del1.setOnClickListener(this);
		del2.setOnClickListener(this);
		del3.setOnClickListener(this);

		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), yd2g900, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), yd2g1800, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), yd3g, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), yd4g, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), yd2g900pt, "请选择天线平台");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), yd2g1800pt, "请选择天线平台");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), yd3gpt, "请选择天线平台");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), yd4gpt, "请选择天线平台");

		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), yd4gD, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), yd4gptD, "请选择天线平台");

		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), dx2g, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), dx3g, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), dx4g, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), dx2gpt, "请选择天线平台");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), dx3gpt, "请选择天线平台");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), dx4gpt, "请选择天线平台");
		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), lt2g900, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), lt2g1800, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), lt3g, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxzt(), lt4g, "请选择天线类型");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), lt2g900pt, "请选择天线平台");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), lt2g1800pt, "请选择天线平台");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), lt3gpt, "请选择天线平台");
		fillSpinnerAdapter(SpinnerUtilBean.getTxpt(), lt4gpt, "请选择天线平台");

		yd2g900height.addTextChangedListener(new yd2g900TextWatcher());
		yd2g1800height.addTextChangedListener(new yd2g1800TextWatcher());
		yd3gheight.addTextChangedListener(new yd3gTextWatcher());
		yd4gheight.addTextChangedListener(new yd4gTextWatcher());

		yd4gheightD.addTextChangedListener(new yd4gDTextWatcher());

		dx2gheight.addTextChangedListener(new dx2gTextWatcher());
		dx3gheight.addTextChangedListener(new dx3gTextWatcher());
		dx4gheight.addTextChangedListener(new dx4gTextWatcher());
		lt2g900height.addTextChangedListener(new lt2g900TextWatcher());
		lt2g1800height.addTextChangedListener(new lt2g1800TextWatcher());
		lt3gheight.addTextChangedListener(new lt3gTextWatcher());
		lt4gheight.addTextChangedListener(new lt4gTextWatcher());
		yd2g900.setOnItemSelectedListener(yd2g900listener);
		yd2g1800.setOnItemSelectedListener(yd2g1800listener);
		yd3g.setOnItemSelectedListener(yd3glistener);
		yd4g.setOnItemSelectedListener(yd4glistener);

		yd4gD.setOnItemSelectedListener(yd4gDlistener);

		dx2g.setOnItemSelectedListener(dx2glistener);
		dx3g.setOnItemSelectedListener(dx3glistener);
		dx4g.setOnItemSelectedListener(dx4glistener);
		lt2g900.setOnItemSelectedListener(lt2g900listener);
		lt2g1800.setOnItemSelectedListener(lt2g1800listener);
		lt3g.setOnItemSelectedListener(lt3glistener);
		lt4g.setOnItemSelectedListener(lt4glistener);
	}

	// 加载本地数据
	private void loadList() {
		try {
			String sql = "select * from t_antenna  where physicCode='"
					+ bean.getPhysicCode() + "' ";
			list = TowerSQliteDbBean.getAntenaData(mOpenHelper, sql);
			if (list != null && list.size() > 0) {
				flag = true;
			}
			handler.obtainMessage(2).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:

			// 返回
			if (flags == 1) {
				Bundle bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags + "");
				forwardIntent(TxDetailActivity.this, RoomPhotoActivity.class,
						bundle);

			} else {
				Bundle bundle1 = new Bundle();
				bundle1.putSerializable("bean", bean);
				bundle1.putString("flag", flags + "");
				forwardIntent(TxDetailActivity.this, DhDetailActivity.class,
						bundle1);
			}
			finish();
			break;
		case R.id.next:// 下一步
			Bundle bundle = new Bundle();
			bundle.putSerializable("bean", bean);
			forwardIntent(getApplicationContext(), EndActivity.class, bundle);
			break;
		case R.id.add:// 添加
			int i = 0;
			if (linear1.getVisibility() == View.VISIBLE) {
				i++;
			}
			if (linear2.getVisibility() == View.VISIBLE) {
				i++;
			}
			if (linear3.getVisibility() == View.VISIBLE) {
				i++;
			}
			if (i == 1 || i == 0) {
				showPopUp(v);
			}
			if (i == 2) {
				linear1.setVisibility(View.VISIBLE);
				linear2.setVisibility(View.VISIBLE);
				linear3.setVisibility(View.VISIBLE);
				save.setVisibility(View.VISIBLE);
				add.setVisibility(View.GONE);
			}
			break;
		case R.id.save:// 保存
			saveData();
			break;
		case R.id.del:
			delData();
			delWindow.dismiss();
			break;
		case R.id.query2:
			delWindow.dismiss();
			break;
		case R.id.del1:
			delflag = 1;
			delWindow = new DelPopupWindow(TxDetailActivity.this, null);
			delWindow.showAtLocation(
					TxDetailActivity.this.findViewById(R.id.main),
					Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
			break;
		case R.id.del2:
			delflag = 2;
			delWindow = new DelPopupWindow(TxDetailActivity.this, null);
			delWindow.showAtLocation(
					TxDetailActivity.this.findViewById(R.id.main),
					Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
			break;
		case R.id.del3:
			delflag = 3;
			delWindow = new DelPopupWindow(TxDetailActivity.this, null);
			delWindow.showAtLocation(
					TxDetailActivity.this.findViewById(R.id.main),
					Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
			break;
		}

	}

	/**
	 * 
	 * 此方法描述的是：删除
	 * 
	 * @Title: delData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-8-3 下午1:34:59
	 */
	protected void delData() {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		try {
			// 开始事务
			db.beginTransaction();
			String str = "";
			if (delflag == 1) {
				str = "1";
				operStr = operStr.replace("1", "").trim();
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(), str,
						operStr, "10");
			} else if (delflag == 2) {
				str = "2";
				operStr = operStr.replace("2", "").trim();
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(), str,
						operStr, "10");
			} else if (delflag == 3) {
				str = "3";
				operStr = operStr.replace("3", "").trim();
				TowerSQliteDbBean.deleData(db, bean.getPhysicCode(), str,
						operStr, "10");
			}
			db.setTransactionSuccessful();
			if (delflag == 1) {
				linear1.setVisibility(View.GONE);
			} else if (delflag == 2) {
				linear2.setVisibility(View.GONE);
			} else if (delflag == 3) {
				linear3.setVisibility(View.GONE);
			}
			add.setVisibility(View.VISIBLE);
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		} finally {
			db.endTransaction();
			db.close();
		}

	}

	/**
	 * 
	 * 此方法描述的是：保存
	 * 
	 * @Title: saveData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-8-3 下午1:35:55
	 */
	public void saveData() {
		antennaBean = new AppAntennaBean();
		antennaBean.setPhysicCode(bean.getPhysicCode());
		StringBuffer sbf = new StringBuffer();
		if (linear1.getVisibility() == View.VISIBLE) {
			String yd2gstr = ((Option) yd2g900.getSelectedItem()).getText();
			String yd2g900heightstr = yd2g900height.getText().toString();
			String yd2g900PtStr = ((Option) yd2g900pt.getSelectedItem())
					.getText();
			if (yd2gstr.equals("有")) {
				if (yd2gstr == null || yd2gstr.equals("")) {
					toastMessage("请输入移动2g900的挂高！");
					return;
				}
				if (yd2g900PtStr.equals("0")) {
					toastMessage("请选择移动2g900的平台！");
					return;
				}
			}

			String yd2g18str = ((Option) yd2g1800.getSelectedItem()).getText();
			String yd2g18heightstr = yd2g1800height.getText().toString();
			String yd2g1800PtStr = ((Option) yd2g1800pt.getSelectedItem())
					.getText();
			if (yd2g18str.equals("有")) {
				if (yd2g18heightstr == null || yd2g18heightstr.equals("")) {
					toastMessage("请输入移动yd2g1800的挂高！");
					return;
				}
				if (yd2g1800PtStr.equals("0")) {
					toastMessage("请选择移动yd2g1800的平台！");
					return;
				}
			}

			String yd3gstr = ((Option) yd3g.getSelectedItem()).getText();
			String yd3gheightstr = yd3gheight.getText().toString();
			String yd3gptstr = ((Option) yd3gpt.getSelectedItem()).getText();
			if (yd3gstr.equals("有")) {
				if (yd3gheightstr == null || yd3gheightstr.equals("")) {
					toastMessage("请输入移动3g的挂高！");
					return;
				}
				if (yd3gptstr.equals("0")) {
					toastMessage("请选择移动3g的平台！");
					return;
				}
			}

			// 移动4g_f
			String yd4gstr = ((Option) yd4g.getSelectedItem()).getText();
			String yd4gheightstr = yd4gheight.getText().toString();
			String yd4gptstr = ((Option) yd4gpt.getSelectedItem()).getText();
			if (yd4gstr.equals("有")) {
				if (yd4gheightstr == null || yd4gheightstr.equals("")) {
					toastMessage("请输入移动4g_F的挂高！");
					return;
				}
				if (yd4gptstr.equals("0")) {
					toastMessage("请选择移动4g_F的平台！");
					return;
				}
			}

			// 移动4g_d
			String yd4gdstr = ((Option) yd4gD.getSelectedItem()).getText();
			String yd4gdheightstr = yd4gheightD.getText().toString();
			String yd4gdptstr = ((Option) yd4gptD.getSelectedItem()).getText();
			if (yd4gdstr.equals("有")) {
				if (yd4gdheightstr == null || yd4gdheightstr.equals("")) {
					toastMessage("请输入移动4g_D的挂高！");
					return;
				}
				if (yd4gdptstr.equals("0")) {
					toastMessage("请选择移动4g_D的平台！");
					return;
				}
			}
			// 判断天线平台与塔高规律
			// 2g900与2g1800
			if (yd2gstr.equals("有") && yd2g18str.equals("有")) {
				if (Integer.parseInt(yd2g900PtStr) > Integer
						.parseInt(yd2g1800PtStr)) {
					if (Double.parseDouble(yd2g900heightstr) > Double
							.parseDouble(yd2g18heightstr)) {
						toastMessage("移动2g900与移动2g1800的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(yd2g900PtStr) < Integer
						.parseInt(yd2g1800PtStr)) {
					if (Double.parseDouble(yd2g900heightstr) < Double
							.parseDouble(yd2g18heightstr)) {
						toastMessage("移动2g900与移动2g1800的平台数与塔高值有出入！");
						return;
					}
				}

			}

			// 2g900与3g
			if (yd2gstr.equals("有") && yd3gstr.equals("有")) {
				if (Integer.parseInt(yd2g900PtStr) > Integer
						.parseInt(yd3gptstr)) {
					if (Double.parseDouble(yd2g900heightstr) > Double
							.parseDouble(yd3gheightstr)) {
						toastMessage("移动2g900与移动3g的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(yd2g900PtStr) < Integer
						.parseInt(yd3gptstr)) {
					if (Double.parseDouble(yd2g900heightstr) < Double
							.parseDouble(yd3gheightstr)) {
						toastMessage("移动2g900与移动3g的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 2g900与4g_f
			if (yd2gstr.equals("有") && yd4gstr.equals("有")) {
				if (Integer.parseInt(yd2g900PtStr) > Integer
						.parseInt(yd4gptstr)) {
					if (Double.parseDouble(yd2g900heightstr) > Double
							.parseDouble(yd4gheightstr)) {
						toastMessage("移动2g900与移动4g_f的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(yd2g900PtStr) < Integer
						.parseInt(yd4gptstr)) {
					if (Double.parseDouble(yd2g900heightstr) < Double
							.parseDouble(yd4gheightstr)) {
						toastMessage("移动2g900与移动4g_f的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 2g900与4g_d
			if (yd2gstr.equals("有") && yd4gdstr.equals("有")) {
				if (Integer.parseInt(yd2g900PtStr) > Integer
						.parseInt(yd4gdptstr)) {
					if (Double.parseDouble(yd2g900heightstr) > Double
							.parseDouble(yd4gdheightstr)) {
						toastMessage("移动2g900与移动4g_d的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(yd2g900PtStr) < Integer
						.parseInt(yd4gdptstr)) {
					if (Double.parseDouble(yd2g900heightstr) < Double
							.parseDouble(yd4gdheightstr)) {
						toastMessage("移动2g900与移动4g_d的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 2g1800与3g
			if (yd2g18str.equals("有") && yd3gstr.equals("有")) {
				if (Integer.parseInt(yd2g1800PtStr) > Integer
						.parseInt(yd3gptstr)) {
					if (Double.parseDouble(yd2g18heightstr) > Double
							.parseDouble(yd3gheightstr)) {
						toastMessage("移动2g1800与移动3g的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(yd2g1800PtStr) < Integer
						.parseInt(yd3gptstr)) {
					if (Double.parseDouble(yd2g18heightstr) < Double
							.parseDouble(yd3gheightstr)) {
						toastMessage("移动2g1800与移动3g的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 2g1800与4g_f
			if (yd2g18str.equals("有") && yd4gstr.equals("有")) {
				if (Integer.parseInt(yd2g1800PtStr) > Integer
						.parseInt(yd4gptstr)) {
					if (Double.parseDouble(yd2g18heightstr) > Double
							.parseDouble(yd4gheightstr)) {
						toastMessage("移动2g1800与移动4g_f的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(yd2g1800PtStr) < Integer
						.parseInt(yd4gptstr)) {
					if (Double.parseDouble(yd2g18heightstr) < Double
							.parseDouble(yd4gheightstr)) {
						toastMessage("移动2g1800与移动4g_f的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 2g1800与4g_d
			if (yd2g18str.equals("有") && yd4gdstr.equals("有")) {
				if (Integer.parseInt(yd2g1800PtStr) > Integer
						.parseInt(yd4gdptstr)) {
					if (Double.parseDouble(yd2g18heightstr) > Double
							.parseDouble(yd4gdheightstr)) {
						toastMessage("移动2g1800与移动4g_d的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(yd2g1800PtStr) < Integer
						.parseInt(yd4gdptstr)) {
					if (Double.parseDouble(yd2g18heightstr) < Double
							.parseDouble(yd4gdheightstr)) {
						toastMessage("移动2g1800与移动4g_d的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 3g与4g_f
			if (yd3gstr.equals("有") && yd4gstr.equals("有")) {
				if (Integer.parseInt(yd3gptstr) > Integer.parseInt(yd4gptstr)) {
					if (Double.parseDouble(yd3gheightstr) > Double
							.parseDouble(yd4gheightstr)) {
						toastMessage("移动3g与移动4g_f的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(yd3gptstr) < Integer.parseInt(yd4gptstr)) {
					if (Double.parseDouble(yd3gheightstr) < Double
							.parseDouble(yd4gheightstr)) {
						toastMessage("移动3g与移动4g_f的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 3g与4g_d
			if (yd3gstr.equals("有") && yd4gdstr.equals("有")) {
				if (Integer.parseInt(yd3gptstr) > Integer.parseInt(yd4gdptstr)) {
					if (Double.parseDouble(yd3gheightstr) > Double
							.parseDouble(yd4gdheightstr)) {
						toastMessage("移动3g与移动4g_d的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(yd3gptstr) < Integer.parseInt(yd4gdptstr)) {
					if (Double.parseDouble(yd3gheightstr) < Double
							.parseDouble(yd4gdheightstr)) {
						toastMessage("移动3g与移动4g_d的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 4g_f与4g_d
			if (yd4gstr.equals("有") && yd4gdstr.equals("有")) {
				if (Integer.parseInt(yd4gptstr) > Integer.parseInt(yd4gdptstr)) {
					if (Double.parseDouble(yd4gheightstr) > Double
							.parseDouble(yd4gdheightstr)) {
						toastMessage("移动4g_d与移动4g_f的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(yd4gptstr) < Integer.parseInt(yd4gdptstr)) {
					if (Double.parseDouble(yd4gheightstr) < Double
							.parseDouble(yd4gdheightstr)) {
						toastMessage("移动4g_d与移动4g_f的平台数与塔高值有出入！");
						return;
					}
				}
			}
			sbf.append("1");

			antennaBean.setYd2g900(yd2gstr);
			antennaBean.setYd2g900Height(yd2g900heightstr);
			antennaBean.setYd2g900Pt(yd2g900PtStr);

			antennaBean.setYd2g1800(yd2g18str);
			antennaBean.setYd2g1800Height(yd2g18heightstr);
			antennaBean.setYd2g1800Pt(yd2g1800PtStr);

			antennaBean.setYd3g(yd3gstr);
			antennaBean.setYd3gHeight(yd3gheightstr);
			antennaBean.setYd3gPt(yd3gptstr);

			antennaBean.setYd4g(yd4gstr);
			antennaBean.setYd4gHeight(yd4gheightstr);
			antennaBean.setYd4gPt(yd4gptstr);

			antennaBean.setYd4gD(yd4gdstr);
			antennaBean.setYd4gHeightD(yd4gdheightstr);
			antennaBean.setYd4gPtD(yd4gdptstr);
			antennaBean.setYdjjs(ydjjs.getText().toString());
		}

		if (linear2.getVisibility() == View.VISIBLE) {
			String dx2gstr = ((Option) dx2g.getSelectedItem()).getText();
			String dx2gHeightstr = dx2gheight.getText().toString();
			String dx2gptstr = ((Option) dx2gpt.getSelectedItem()).getText();
			if (dx2gstr.equals("有")) {
				if (dx2gHeightstr == null || dx2gHeightstr.equals("")) {
					toastMessage("请输入电信2g的挂高！");
					return;
				}
				if (dx2gptstr.equals("0")) {
					toastMessage("请选择电信2g的平台！");
					return;
				}
			}

			String dx3gstr = ((Option) dx3g.getSelectedItem()).getText();
			String dx3gHeightstr = dx3gheight.getText().toString();
			String dx3gptstr = ((Option) dx3gpt.getSelectedItem()).getText();
			if (dx3gstr.equals("有")) {
				if (dx3gHeightstr == null || dx3gHeightstr.equals("")) {
					toastMessage("请输入电信3g的挂高！");
					return;
				}
				if (dx3gptstr.equals("0")) {
					toastMessage("请选择电信3g的平台！");
					return;
				}
			}

			String dx4gstr = ((Option) dx4g.getSelectedItem()).getText();
			String dx4gHeightstr = dx4gheight.getText().toString();
			String dx4gptstr = ((Option) dx4gpt.getSelectedItem()).getText();
			if (dx4gstr.equals("有")) {
				if (dx4gHeightstr == null || dx4gHeightstr.equals("")) {
					toastMessage("请输入电信4g的挂高！");
					return;
				}
				if (dx4gptstr.equals("0")) {
					toastMessage("请选择电信4g的平台！");
					return;
				}
			}
			// 判断天线平台与塔高规律
			// 2g与3g
			if (dx2gstr.equals("有") && dx3gstr.equals("有")) {
				if (Integer.parseInt(dx2gptstr) > Integer.parseInt(dx3gptstr)) {
					if (Double.parseDouble(dx2gHeightstr) > Double
							.parseDouble(dx3gHeightstr)) {
						toastMessage("电信2g与电信3g的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(dx2gptstr) < Integer.parseInt(dx3gptstr)) {
					if (Double.parseDouble(dx2gHeightstr) < Double
							.parseDouble(dx3gHeightstr)) {
						toastMessage("电信2g与电信3g的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 2g与4g
			if (dx2gstr.equals("有") && dx4gstr.equals("有")) {
				if (Integer.parseInt(dx2gptstr) > Integer.parseInt(dx4gptstr)) {
					if (Double.parseDouble(dx2gHeightstr) > Double
							.parseDouble(dx4gHeightstr)) {
						toastMessage("电信2g与电信4g的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(dx2gptstr) < Integer.parseInt(dx4gptstr)) {
					if (Double.parseDouble(dx2gHeightstr) < Double
							.parseDouble(dx4gHeightstr)) {
						toastMessage("电信2g与电信4g的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 3g与4g
			if (dx3gstr.equals("有") && dx4gstr.equals("有")) {
				if (Integer.parseInt(dx3gptstr) > Integer.parseInt(dx4gptstr)) {
					if (Double.parseDouble(dx3gHeightstr) > Double
							.parseDouble(dx4gHeightstr)) {
						toastMessage("电信3g与电信4g的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(dx3gptstr) < Integer.parseInt(dx4gptstr)) {
					if (Double.parseDouble(dx3gHeightstr) < Double
							.parseDouble(dx4gHeightstr)) {
						toastMessage("电信3g与电信4g的平台数与塔高值有出入！");
						return;
					}
				}
			}
			sbf.append("2");
			antennaBean.setDx2g(dx2gstr);
			antennaBean.setDx2gHeight(dx2gHeightstr);
			antennaBean.setDx2gPt(dx2gptstr);

			antennaBean.setDx3g(dx3gstr);
			antennaBean.setDx3gHeight(dx3gHeightstr);
			antennaBean.setDx3gPt(dx3gptstr);

			antennaBean.setDx4g(dx4gstr);
			antennaBean.setDx4gHeight(dx4gHeightstr);
			antennaBean.setDx4gPt(dx4gptstr);

			antennaBean.setDxjjs(dxjjs.getText().toString());
		}
		if (linear3.getVisibility() == View.VISIBLE) {
			String lt2g900str = ((Option) lt2g900.getSelectedItem()).getText();
			String lt2g900Heightstr = lt2g900height.getText().toString();
			String lt2g900ptstr = ((Option) lt2g900pt.getSelectedItem())
					.getText();
			if (lt2g900str.equals("有")) {
				if (lt2g900Heightstr == null || lt2g900Heightstr.equals("")) {
					toastMessage("请输入联通2g900的挂高！");
					return;
				}
				if (lt2g900ptstr.equals("0")) {
					toastMessage("请选择联通2g900的平台！");
					return;
				}
			}

			String lt2g1800str = ((Option) lt2g1800.getSelectedItem())
					.getText();
			String lt2g1800Heightstr = lt2g1800height.getText().toString();
			String lt2g1800ptstr = ((Option) lt2g1800pt.getSelectedItem())
					.getText();
			if (lt2g1800str.equals("有")) {
				if (lt2g1800Heightstr == null || lt2g1800Heightstr.equals("")) {
					toastMessage("请输入联通2g1800的挂高！");
					return;
				}
				if (lt2g1800ptstr.equals("0")) {
					toastMessage("请选择联通2g1800的平台！");
					return;
				}
			}

			String lt3gstr = ((Option) lt3g.getSelectedItem()).getText();
			String lt3gHeightstr = lt3gheight.getText().toString();
			String lt3gptstr = ((Option) lt3gpt.getSelectedItem()).getText();
			if (lt3gstr.equals("有")) {
				if (lt3gHeightstr == null || lt3gHeightstr.equals("")) {
					toastMessage("请输入联通3g的挂高！");
					return;
				}
				if (lt3gptstr.equals("0")) {
					toastMessage("请选择联通3g的平台！");
					return;
				}
			}

			String lt4gstr = ((Option) lt4g.getSelectedItem()).getText();
			String lt4gHeightstr = lt4gheight.getText().toString();
			String lt4gptstr = ((Option) lt4gpt.getSelectedItem()).getText();
			if (lt4gstr.equals("有")) {
				if (lt4gHeightstr == null || lt4gHeightstr.equals("")) {
					toastMessage("请输入联通4g的挂高！");
					return;
				}
				if (lt4gptstr.equals("0")) {
					toastMessage("请选择联通4g的平台！");
					return;
				}
			}
			// 判断天线平台与塔高规律
			// 2g900与2g1800
			if (lt2g900str.equals("有") && lt2g1800str.equals("有")) {
				if (Integer.parseInt(lt2g900ptstr) > Integer
						.parseInt(lt2g1800ptstr)) {
					if (Double.parseDouble(lt2g900Heightstr) > Double
							.parseDouble(lt2g1800Heightstr)) {
						toastMessage("联通2g900与联通2g1800的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(lt2g900ptstr) < Integer
						.parseInt(lt2g1800ptstr)) {
					if (Double.parseDouble(lt2g900Heightstr) < Double
							.parseDouble(lt2g1800Heightstr)) {
						toastMessage("联通2g900与联通2g1800的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 2g900与3g
			if (lt2g900str.equals("有") && lt3gstr.equals("有")) {
				if (Integer.parseInt(lt2g900ptstr) > Integer
						.parseInt(lt3gptstr)) {
					if (Double.parseDouble(lt2g900Heightstr) > Double
							.parseDouble(lt3gHeightstr)) {
						toastMessage("联通2g900与联通3g的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(lt2g900ptstr) < Integer
						.parseInt(lt3gptstr)) {
					if (Double.parseDouble(lt2g900Heightstr) < Double
							.parseDouble(lt3gHeightstr)) {
						toastMessage("联通2g900与联通3g的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 2g900与4g
			if (lt2g900str.equals("有") && lt4gstr.equals("有")) {
				if (Integer.parseInt(lt2g900ptstr) > Integer
						.parseInt(lt4gptstr)) {
					if (Double.parseDouble(lt2g900Heightstr) > Double
							.parseDouble(lt4gHeightstr)) {
						toastMessage("联通2g900与联通4g的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(lt2g900ptstr) < Integer
						.parseInt(lt4gptstr)) {
					if (Double.parseDouble(lt2g900Heightstr) < Double
							.parseDouble(lt4gHeightstr)) {
						toastMessage("联通2g900与联通4g的平台数与塔高值有出入！");
						return;
					}
				}
			}

			// 2g1800与3g
			if (lt2g1800str.equals("有") && lt3gstr.equals("有")) {
				if (Integer.parseInt(lt2g1800ptstr) > Integer
						.parseInt(lt3gptstr)) {
					if (Double.parseDouble(lt2g1800Heightstr) > Double
							.parseDouble(lt3gHeightstr)) {
						toastMessage("联通2g1800与联通3g的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(lt2g1800ptstr) < Integer
						.parseInt(lt3gptstr)) {
					if (Double.parseDouble(lt2g1800Heightstr) < Double
							.parseDouble(lt3gHeightstr)) {
						toastMessage("联通2g1800与联通3g的平台数与塔高值有出入！");
						return;
					}
				}
			}
			// 2g1800与4g
			if (lt2g1800str.equals("有") && lt4gstr.equals("有")) {
				if (Integer.parseInt(lt2g1800ptstr) > Integer
						.parseInt(lt4gptstr)) {
					if (Double.parseDouble(lt2g1800Heightstr) > Double
							.parseDouble(lt4gHeightstr)) {
						toastMessage("联通2g900与联通4g的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(lt2g1800ptstr) < Integer
						.parseInt(lt4gptstr)) {
					if (Double.parseDouble(lt2g1800Heightstr) < Double
							.parseDouble(lt4gHeightstr)) {
						toastMessage("联通2g1800与联通4g的平台数与塔高值有出入！");
						return;
					}
				}
			}

			// 3g与4g
			if (lt3gstr.equals("有") && lt4gstr.equals("有")) {
				if (Integer.parseInt(lt3gptstr) > Integer.parseInt(lt4gptstr)) {
					if (Double.parseDouble(lt3gHeightstr) > Double
							.parseDouble(lt4gHeightstr)) {
						toastMessage("联通3g与联通4g的平台数与塔高值有出入！");
						return;
					}
				}
				if (Integer.parseInt(lt3gptstr) < Integer.parseInt(lt4gptstr)) {
					if (Double.parseDouble(lt3gHeightstr) < Double
							.parseDouble(lt4gHeightstr)) {
						toastMessage("联通3g与联通4g的平台数与塔高值有出入！");
						return;
					}
				}
			}
			sbf.append("3");

			antennaBean.setLt2g900(lt2g900str);
			antennaBean.setLt2g900Height(lt2g900Heightstr);
			antennaBean.setLt2g900Pt(lt2g900ptstr);

			antennaBean.setLt2g1800(lt2g1800str);
			antennaBean.setLt2g1800Height(lt2g1800Heightstr);
			antennaBean.setLt2g1800Pt(lt2g1800ptstr);

			antennaBean.setLt3g(lt3gstr);
			antennaBean.setLt3gHeight(lt3gHeightstr);
			antennaBean.setLt3gPt(lt3gptstr);

			antennaBean.setLt4g(lt4gstr);
			antennaBean.setLt4gHeight(lt4gHeightstr);
			antennaBean.setLt4gPt(lt4gptstr);

			antennaBean.setLtjjs(ltjjs.getText().toString());
		}
		if (sbf != null && !sbf.toString().equals("")) {
			relative_layout.setVisibility(View.VISIBLE);
			loading.startAnimation(animation);
			operStr = sbf.toString();
			antennaBean.setOperator(operStr);

			// 插入数据
			db = mOpenHelper.getReadableDatabase();
			try {
				if (flag == true) {
					// 直接更新
					TowerSQliteDbBean.updateTxData(db, antennaBean);
				} else {
					// 直接插入
					UUID uuid = UUID.randomUUID();
					antennaBean.setId(uuid.toString());
					antennaBean.setPhysicCode(bean.getPhysicCode());
					TowerSQliteDbBean.insertTx(db, antennaBean);
				}

				handler.obtainMessage(1).sendToTarget();
			} catch (Exception e) {
				handler.obtainMessage(-1).sendToTarget();
			} finally {
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
				Bundle bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(), EndActivity.class,
						bundle);
				break;
			case -1:
				toastMessage("请求失败！");
				break;
			case 2:
				if (list != null && list.size() > 0) {
					loadData();
				} else {
					if (bean.getStatus().equals("3")
							|| bean.getStatus().equals("4")) {
						add.setVisibility(View.GONE);
					} else {
						add.setVisibility(View.VISIBLE);
					}

					save.setVisibility(View.GONE);
				}
				break;
			}
		}
	};

	/**
	 * 
	 * 此方法描述的是：封装页面数据
	 * 
	 * @Title: loadData
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-24 下午2:22:18
	 */
	protected void loadData() {
		txbean = list.get(0);
		operStr = txbean.getOperator();
		if (operStr != null && !operStr.equals("") && !operStr.equals("null")) {
			if (operStr.contains("123")) {
				add.setVisibility(View.GONE);
			}
			// 移动4G-F
			if (operStr.contains("1")) {
				linear1.setVisibility(View.VISIBLE);
				String ydjjstr = txbean.getYdjjs();
				String yd2g900Str = txbean.getYd2g900();
				if (yd2g900Str != null && !yd2g900Str.equals("")
						&& !yd2g900Str.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), yd2g900Str,
							yd2g900);
				}

				String yd2g900ptstr = txbean.getYd2g900Pt();
				if (yd2g900ptstr != null && !yd2g900ptstr.equals("")
						&& !yd2g900ptstr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), yd2g900ptstr,
							yd2g900pt);
				}

				String yd2g1800Str = txbean.getYd2g1800();
				if (yd2g1800Str != null && !yd2g1800Str.equals("")
						&& !yd2g1800Str.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), yd2g1800Str,
							yd2g1800);
				}

				String yd2g1800ptstr = txbean.getYd2g1800Pt();
				if (yd2g1800ptstr != null && !yd2g1800ptstr.equals("")
						&& !yd2g1800ptstr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), yd2g1800ptstr,
							yd2g1800pt);
				}

				String yd3gstr = txbean.getYd3g();
				if (yd3gstr != null && !yd3gstr.equals("")
						&& !yd3gstr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), yd3gstr, yd3g);
				}
				String yd3gptStr = txbean.getYd3gPt();
				if (yd3gptStr != null && !yd3gptStr.equals("")
						&& !yd3gptStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), yd3gptStr,
							yd3gpt);
				}

				String yd4gstr = txbean.getYd4g();
				if (yd4gstr != null && !yd4gstr.equals("")
						&& !yd4gstr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), yd4gstr, yd4g);
				}
				String yd4gptStr = txbean.getYd4gPt();
				if (yd4gptStr != null && !yd4gptStr.equals("")
						&& !yd4gptStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), yd4gptStr,
							yd4gpt);
				}
				// 移动4G-D
				String yd4gstrD = txbean.getYd4gD();
				if (yd4gstrD != null && !yd4gstrD.equals("")
						&& !yd4gstrD.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), yd4gstrD, yd4gD);
				}
				String yd4gptStrD = txbean.getYd4gPtD();
				if (yd4gptStrD != null && !yd4gptStrD.equals("")
						&& !yd4gptStrD.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), yd4gptStrD,
							yd4gptD);
				}

				String yd2g900HeightStr = txbean.getYd2g900Height();
				String yd2g1800HeightStr = txbean.getYd2g1800Height();
				String yd3gHeight = txbean.getYd3gHeight();
				String yd4gHeight = txbean.getYd4gHeight();
				String yd4gHeightD = txbean.getYd4gHeightD();
				if (yd2g900HeightStr != null && !yd2g900HeightStr.equals("")
						&& !yd2g900HeightStr.equals("null")) {
					yd2g900height.setText(yd2g900HeightStr);
				}
				if (yd2g1800HeightStr != null && !yd2g1800HeightStr.equals("")
						&& !yd2g1800HeightStr.equals("null")) {
					yd2g1800height.setText(yd2g1800HeightStr);
				}
				if (yd3gHeight != null && !yd3gHeight.equals("")
						&& !yd3gHeight.equals("null")) {
					yd3gheight.setText(yd3gHeight);
				}
				if (yd4gHeight != null && !yd4gHeight.equals("")
						&& !yd4gHeight.equals("null")) {
					yd4gheight.setText(yd4gHeight);
				}
				if (yd4gHeightD != null && !yd4gHeightD.equals("")
						&& !yd4gHeightD.equals("null")) {
					yd4gheightD.setText(yd4gHeightD);
				}
				if (ydjjstr != null && !ydjjstr.equals("")
						&& !ydjjstr.equals("null")) {
					ydjjs.setText(ydjjstr);
				}

			}

			if (operStr.contains("2")) {
				linear2.setVisibility(View.VISIBLE);

				String dxjjstr = txbean.getDxjjs();

				String dx2gStr = txbean.getDx2g();
				if (dx2gStr != null && !dx2gStr.equals("")
						&& !dx2gStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), dx2gStr, dx2g);
				}
				String dx2gptStr = txbean.getDx2gPt();
				if (dx2gptStr != null && !dx2gptStr.equals("")
						&& !dx2gptStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), dx2gptStr,
							dx2gpt);
				}

				String dx3gStr = txbean.getDx3g();
				if (dx3gStr != null && !dx3gStr.equals("")
						&& !dx3gStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), dx3gStr, dx3g);
				}
				String dx3gptStr = txbean.getDx3gPt();
				if (dx3gptStr != null && !dx3gptStr.equals("")
						&& !dx3gptStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), dx3gptStr,
							dx3gpt);
				}

				String dx4gStr = txbean.getDx4g();
				if (dx4gStr != null && !dx4gStr.equals("")
						&& !dx4gStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), dx4gStr, dx4g);
				}
				String dx4gptStr = txbean.getDx4gPt();
				if (dx4gptStr != null && !dx4gptStr.equals("")
						&& !dx4gptStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), dx4gptStr,
							dx4gpt);
				}

				String dx2gHeight = txbean.getDx2gHeight();
				String dx3gHeight = txbean.getDx3gHeight();
				String dx4gHeight = txbean.getDx4gHeight();
				if (dx2gHeight != null && !dx2gHeight.equals("")
						&& !dx2gHeight.equals("null")) {
					dx2gheight.setText(dx2gHeight);
				}
				if (dx3gHeight != null && !dx3gHeight.equals("")
						&& !dx3gHeight.equals("null")) {
					dx3gheight.setText(dx3gHeight);
				}
				if (dx4gHeight != null && !dx4gHeight.equals("")
						&& !dx4gHeight.equals("null")) {
					dx4gheight.setText(dx4gHeight);
				}
				if (dxjjstr != null && !dxjjstr.equals("")
						&& !dxjjstr.equals("null")) {
					dxjjs.setText(dxjjstr);
				}
			}
			if (operStr.contains("3")) {
				linear3.setVisibility(View.VISIBLE);
				String ltjjstr = txbean.getLtjjs();

				String lt2g900Str = txbean.getLt2g900();
				if (lt2g900Str != null && !lt2g900Str.equals("")
						&& !lt2g900Str.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), lt2g900Str,
							lt2g900);
				}
				String lt2g1800Str = txbean.getLt2g1800();
				if (lt2g1800Str != null && !lt2g1800Str.equals("")
						&& !lt2g1800Str.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), lt2g1800Str,
							lt2g1800);
				}
				String lt3gStr = txbean.getLt3g();
				if (lt3gStr != null && !lt3gStr.equals("")
						&& !lt3gStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), lt3gStr, lt3g);
				}
				String lt4gStr = txbean.getLt4g();
				if (lt4gStr != null && !lt4gStr.equals("")
						&& !lt4gStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxzt(), lt4gStr, lt4g);
				}

				String lt2g900ptStr = txbean.getLt2g900Pt();
				if (lt2g900ptStr != null && !lt2g900ptStr.equals("")
						&& !lt2g900ptStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), lt2g900ptStr,
							lt2g900pt);
				}
				String lt2g1800ptStr = txbean.getLt2g1800Pt();
				if (lt2g1800ptStr != null && !lt2g1800ptStr.equals("")
						&& !lt2g1800ptStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), lt2g1800ptStr,
							lt2g1800pt);
				}
				String lt3gptStr = txbean.getLt3gPt();
				if (lt3gptStr != null && !lt3gptStr.equals("")
						&& !lt3gptStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), lt3gptStr,
							lt3gpt);
				}
				String lt4gptStr = txbean.getLt4gPt();
				if (lt4gptStr != null && !lt4gptStr.equals("")
						&& !lt4gptStr.equals("null")) {
					selectedSpinner(SpinnerUtilBean.getTxpt(), lt4gptStr,
							lt4gpt);
				}
				String lt2g900Height = txbean.getLt2g900Height();
				String lt2g1800Height = txbean.getLt2g1800Height();
				String lt3gHeight = txbean.getLt3gHeight();
				String lt4gHeight = txbean.getLt4gHeight();
				if (lt2g900Height != null && !lt2g900Height.equals("")
						&& !lt2g900Height.equals("null")) {
					lt2g900height.setText(lt2g900Height);
				}
				if (lt2g1800Height != null && !lt2g1800Height.equals("")
						&& !lt2g1800Height.equals("null")) {
					lt2g1800height.setText(lt2g1800Height);
				}
				if (lt3gHeight != null && !lt3gHeight.equals("")
						&& !lt3gHeight.equals("null")) {
					lt3gheight.setText(lt3gHeight);
				}
				if (lt4gHeight != null && !lt4gHeight.equals("")
						&& !lt4gHeight.equals("null")) {
					lt4gheight.setText(lt4gHeight);
				}
				if (ltjjstr != null && !ltjjstr.equals("")
						&& !ltjjstr.equals("null")) {
					ltjjs.setText(ltjjstr);
				}
			}
		}
	}

	private OnItemSelectedListener yd2g900listener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) yd2g900.getSelectedItem()).getText();
			if (str.equals("有")) {
				yd2g900pt.setClickable(true);
			} else {
				yd2g900height.setText("");
				yd2g900pt.setClickable(false);
				yd2g900pt.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private OnItemSelectedListener yd2g1800listener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) yd2g1800.getSelectedItem()).getText();
			if (str.equals("有")) {
				yd2g1800pt.setClickable(true);
			} else {
				yd2g1800height.setText("");
				yd2g1800pt.setClickable(false);
				yd2g1800pt.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private OnItemSelectedListener yd3glistener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) yd3g.getSelectedItem()).getText();
			if (str.equals("有")) {
				yd3gpt.setClickable(true);
			} else {
				yd3gheight.setText("");
				yd3gpt.setClickable(false);
				yd3gpt.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private OnItemSelectedListener yd4glistener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) yd4g.getSelectedItem()).getText();
			if (str.equals("有")) {
				yd4gpt.setClickable(true);
			} else {
				yd4gheight.setText("");
				yd4gpt.setClickable(false);
				yd4gpt.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private OnItemSelectedListener yd4gDlistener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) yd4gD.getSelectedItem()).getText();
			if (str.equals("有")) {
				yd4gptD.setClickable(true);
			} else {
				yd4gheightD.setText("");
				yd4gptD.setClickable(false);
				yd4gptD.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private OnItemSelectedListener dx2glistener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) dx2g.getSelectedItem()).getText();
			if (str.equals("有")) {
				dx2gpt.setClickable(true);
			} else {
				dx2gheight.setText("");
				dx2gpt.setClickable(false);
				dx2gpt.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private OnItemSelectedListener dx4glistener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) dx4g.getSelectedItem()).getText();
			if (str.equals("有")) {
				dx4gpt.setClickable(true);
			} else {
				dx4gheight.setText("");
				dx4gpt.setClickable(false);
				dx4gpt.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};
	private OnItemSelectedListener dx3glistener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) dx3g.getSelectedItem()).getText();
			if (str.equals("有")) {
				dx3gpt.setClickable(true);
			} else {
				dx3gheight.setText("");
				dx3gpt.setClickable(false);
				dx3gpt.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};
	private OnItemSelectedListener lt4glistener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) lt4g.getSelectedItem()).getText();
			if (str.equals("有")) {
				lt4gpt.setClickable(true);
			} else {
				lt4gheight.setText("");
				lt4gpt.setClickable(false);
				lt4gpt.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private OnItemSelectedListener lt2g900listener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) lt2g900.getSelectedItem()).getText();
			if (str.equals("有")) {
				lt2g900pt.setClickable(true);
			} else {
				lt2g900height.setText("");
				lt2g900pt.setClickable(false);
				lt2g900pt.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private OnItemSelectedListener lt2g1800listener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) lt2g1800.getSelectedItem()).getText();
			if (str.equals("有")) {
				lt2g1800pt.setClickable(true);
			} else {
				lt2g1800height.setText("");
				lt2g1800pt.setClickable(false);
				lt2g1800pt.setSelection(0);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private OnItemSelectedListener lt3glistener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String str = ((Option) lt3g.getSelectedItem()).getText();
			if (str.equals("有")) {
				lt3gpt.setClickable(true);
			} else {
				lt3gheight.setText("");
				lt3gpt.setClickable(false);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	class yd2g900TextWatcher implements TextWatcher {

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
				yd2g900height.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					yd2g900height.setText(s);
					yd2g900height.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	class yd2g1800TextWatcher implements TextWatcher {

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
				yd2g1800height.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					yd2g1800height.setText(s);
					yd2g1800height.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	class yd3gTextWatcher implements TextWatcher {

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
				yd3gheight.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					yd3gheight.setText(s);
					yd3gheight.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	class yd4gTextWatcher implements TextWatcher {

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
				yd4gheight.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					yd4gheight.setText(s);
					yd4gheight.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	class yd4gDTextWatcher implements TextWatcher {

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
				yd4gheightD.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					yd4gheightD.setText(s);
					yd4gheightD.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	class dx2gTextWatcher implements TextWatcher {

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
				dx2gheight.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					dx2gheight.setText(s);
					dx2gheight.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	class lt4gTextWatcher implements TextWatcher {

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
				lt4gheight.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					lt4gheight.setText(s);
					lt4gheight.setSelection(s.length());
				}
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	}

	class lt3gTextWatcher implements TextWatcher {

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
				lt3gheight.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					lt3gheight.setText(s);
					lt3gheight.setSelection(s.length());
				}
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

	}

	class dx3gTextWatcher implements TextWatcher {

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
				dx3gheight.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					dx3gheight.setText(s);
					dx3gheight.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	class dx4gTextWatcher implements TextWatcher {

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
				dx4gheight.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					dx4gheight.setText(s);
					dx4gheight.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	class lt2g900TextWatcher implements TextWatcher {

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
				lt2g900height.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					lt2g900height.setText(s);
					lt2g900height.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
		}
	}

	class lt2g1800TextWatcher implements TextWatcher {

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
				lt2g1800height.setText("");
			}
			if (s.toString().contains(".")) {
				if (s.length() - 1 - s.toString().indexOf(".") > 2) {
					s = s.toString().subSequence(0,
							s.toString().indexOf(".") + 3);
					lt2g1800height.setText(s);
					lt2g1800height.setSelection(s.length());
				}
			}
		}

		// 文字变化后
		@Override
		public void afterTextChanged(Editable s) {
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
			del_tv = (TextView) mMenuView.findViewById(R.id.del_tv);
			query2 = (Button) mMenuView.findViewById(R.id.query2);
			del.setOnClickListener(TxDetailActivity.this);
			query2.setOnClickListener(TxDetailActivity.this);
			del_tv.setText("将删除此运营商平台数据");
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

	/**
	 * 操作按钮弹出框
	 * 
	 * @param v
	 * 
	 */
	private void showPopUp(View v) {
		int i = 0;
		popupWindow = new PopupWindow();
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp1.setMargins(10, 5, 10, 5);
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp2.setMargins(10, 5, 10, 5);
		if (linear1.getVisibility() == View.GONE) {
			i++;
			TextView tv = new TextView(this);
			tv.setLayoutParams(lp1);
			tv.setText("中国移动");
			tv.setTextSize(18);
			tv.setTextColor(Color.BLUE);
			layout.addView(tv);
			tv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					popupWindow.dismiss();
					save.setVisibility(View.VISIBLE);
					linear1.setVisibility(View.VISIBLE);
				}
			});
		}
		if (linear2.getVisibility() == View.GONE) {
			i++;
			TextView tv2 = new TextView(this);
			tv2.setLayoutParams(lp2);
			tv2.setText("中国电信");
			tv2.setTextSize(18);
			tv2.setTextColor(Color.BLUE);
			layout.addView(tv2);
			tv2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					popupWindow.dismiss();
					save.setVisibility(View.VISIBLE);
					linear2.setVisibility(View.VISIBLE);
				}

			});
		}

		if (linear3.getVisibility() == View.GONE) {
			i++;
			TextView tv3 = new TextView(this);
			tv3.setLayoutParams(lp2);
			tv3.setText("中国联通");
			tv3.setTextSize(18);
			tv3.setTextColor(Color.BLUE);
			layout.addView(tv3);
			tv3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					popupWindow.dismiss();
					save.setVisibility(View.VISIBLE);
					linear3.setVisibility(View.VISIBLE);
				}
			});
		}
		popupWindow = new PopupWindow(layout, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.pop));
		int[] location = new int[i];
		v.getLocationOnScreen(location);
		int width = v.getWidth();
		int height = v.getHeight();
		popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0] + width
				- popupWindow.getWidth(), location[1] + height);
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

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if (flags == 1) {
			Bundle bundle = new Bundle();
			bundle.putSerializable("bean", bean);
			bundle.putString("flag", flags + "");
			forwardIntent(TxDetailActivity.this, RoomPhotoActivity.class,
					bundle);

		} else {
			Bundle bundle1 = new Bundle();
			bundle1.putSerializable("bean", bean);
			bundle1.putString("flag", flags + "");
			forwardIntent(TxDetailActivity.this, DhDetailActivity.class,
					bundle1);
		}
		finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadList();
	}
	
}
