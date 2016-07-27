package com.woyi.towerzj_app.activity.donghuan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.activity.tianxian.TxDetailActivity;
import com.woyi.towerzj_app.bean.Option;
import com.woyi.towerzj_app.bean.SpinnerUtilBean;
import com.woyi.towerzj_app.bean.donghuan.DongHuanBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;

/**
 * 
 * �����������ǣ�����
 * 
 * @author: ��Ȼ
 * @version: 2015-7-19 ����10:25
 * @ClassName: DxDetailActivity
 * @��Ŀ�� towerzj_app
 * @����com.woyi.towerzj_app.activity.donghuan
 */
public class DhDetailActivity extends ForwardActivity {
	private TextView title, next, code, address, add, save;
	private Spinner jfjg, fsu;
	private Spinner tem, yg, hw, sq, dk, mj, ip, zndb;
	//	������������ҡ�����������ͺţ��Ž��������ң��Ž������ͺ�
	private Spinner mjzjcj,mjzjxh,jkzjcj,jkzjxh;
	Button back;

	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;

	private List<DongHuanBean> list;

	private DongHuanBean dhbean=new DongHuanBean();
	private AppPhyInfoBean bean;
	private SQLiteDatabase db;
	private DatabaseHelper mOpenHelper;

	private boolean newflag=false;
	private int flags = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		flags = Integer.parseInt(this.getIntent().getExtras().getString("flag"));
		Reflex.loadViewForActivityOnCreate(this,
				R.layout.activity_donghuan_detail);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		if (bean != null) {
			code.setText(bean.getPhysicAlias());
			address.setText(bean.getPhysicName());
		}
		next.setText("����");
		if (bean.getStatus().equals("3") || bean.getStatus().equals("4")) {
			save.setVisibility(View.GONE);
		}
		losdList();
		fillSpinnerAdapterCj(SpinnerUtilBean.getDhFsyCjType(), jfjg, "��ѡ�񶯻�����");
		fillSpinnerAdapterCj(SpinnerUtilBean.getDhMjCjType(), mjzjcj, "��ѡ�񶯻��Ž�����");
		fillSpinnerAdapterCj(SpinnerUtilBean.getDhZjCjType(), jkzjcj, "��ѡ�񶯻������������");

		fillSpinnerAdapter(SpinnerUtilBean.getDh(), tem, "��ѡ����ʪ�ȴ�����");
		fillSpinnerAdapter(SpinnerUtilBean.getDh(), yg, "��ѡ���̸д�����");
		fillSpinnerAdapter(SpinnerUtilBean.getDh(), hw, "��ѡ����⴫����");
		fillSpinnerAdapter(SpinnerUtilBean.getDh(), sq, "��ѡ��ˮ��������");
		fillSpinnerAdapter(SpinnerUtilBean.getDh(), dk, "��ѡ��ƿش�����");
		fillSpinnerAdapter(SpinnerUtilBean.getDh(), mj, "��ѡ���Ŵ�ϵͳ");
		fillSpinnerAdapter(SpinnerUtilBean.getDh(), ip, "��ѡ����������ͷ");
		fillSpinnerAdapter(SpinnerUtilBean.getDh(), zndb, "��ѡ�����ܵ��");
		title.setText("����");
		save.setOnClickListener(btnLis);
		back.setOnClickListener(btnLis);
		next.setOnClickListener(btnLis);
		add.setVisibility(View.INVISIBLE);
		jfjg.setOnItemSelectedListener(listener1);
		mjzjcj.setOnItemSelectedListener(listener2);
		jkzjcj.setOnItemSelectedListener(listener3);
		
	}

	/**
	 * ���ر�������
	 */
	private void losdList() {
		list=new ArrayList<DongHuanBean>();
		String sql = "select * from t_guard where physicCode='"
				+ bean.getPhysicCode() + "' ";
		try {
			list = TowerSQliteDbBean.getGuardData(mOpenHelper, sql);
			handler.obtainMessage(2).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
			
		}
	}

	/**
	 * fsu����
	 */
	private AdapterView.OnItemSelectedListener listener1 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String str = jfjg.getItemAtPosition(position).toString().trim();
			List<String> strlist=new ArrayList<String>();
			for(Option o:SpinnerUtilBean.getDhFsyCjType()){
				if(o.getText().equals(str)){
					strlist.add(o.getValue());
				}
			}
			if(strlist!=null && strlist.size()>0){
				fillAdapterType(strlist,fsu,"��ѡ��fsu�ͺ�");
			}
			if(dhbean!=null){
				selectedSpinnerType(strlist, dhbean.getFsuType(), fsu);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}

	};

	/**
	 * �Ž���������
	 */
	private AdapterView.OnItemSelectedListener listener2 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String str = mjzjcj.getItemAtPosition(position).toString().trim();
			List<String> strlist=new ArrayList<String>();
			for(Option o:SpinnerUtilBean.getDhMjCjType()){
				if(o.getText().equals(str)){
					strlist.add(o.getValue());
				}
			}
			if(strlist!=null && strlist.size()>0){
				fillAdapterType(strlist,mjzjxh,"��ѡ���Ž������ͺ�");
			}
			if(dhbean!=null){
				selectedSpinnerType(strlist, dhbean.getMjzjType(), mjzjxh);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}

	};

	/**
	 * �����������
	 */
	private AdapterView.OnItemSelectedListener listener3 = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String str = jkzjcj.getItemAtPosition(position).toString().trim();
			List<String> strlist=new ArrayList<String>();
			for(Option o:SpinnerUtilBean.getDhZjCjType()){
				if(o.getText().equals(str)){
					strlist.add(o.getValue());
				}
			}
			if(strlist!=null && strlist.size()>0){
				fillAdapterType(strlist,jkzjxh,"��ѡ���������ͺ�");
			}
			if(dhbean!=null){
				selectedSpinnerType(strlist, dhbean.getJkzjType(), jkzjxh);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}

	};

	private OnClickListener btnLis = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.back:
				finish();
				break;
			case R.id.save:// ��������
				relative_layout.setVisibility(View.VISIBLE);
				loading.startAnimation(animation);
				saveData();
				break;
			case R.id.next://
				Bundle bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags+"");
				forwardIntent(getApplicationContext(), TxDetailActivity.class,
						bundle);
				finish();
				break;
			}
		}
	};

	/**
	 * 
	 * �˷����������ǣ���������
	 * 
	 * @Title: saveData
	 * @author: ��Ȼ
	 * @return void ��������
	 * @version: 2015-7-21 ����11:05:38
	 */
	public void saveData() {
		db = mOpenHelper.getReadableDatabase();
		DongHuanBean huanBean = new DongHuanBean();
		UUID uuid = UUID.randomUUID();
		huanBean.setId(uuid.toString());
		huanBean.setPhysicCode(bean.getPhysicCode());
		huanBean.setMakeCode(jfjg.getSelectedItem().toString());
		huanBean.setFsuType(fsu.getSelectedItem().toString());
		huanBean.setTempSensor(Integer.parseInt(((Option) tem.getSelectedItem())
				.getValue()));
		huanBean.setSmokeSensor(Integer.parseInt(((Option) yg.getSelectedItem())
				.getValue()));
		huanBean.setReadSensor(Integer.parseInt(((Option) hw.getSelectedItem())
				.getValue()));
		huanBean.setWaterSensor(Integer.parseInt(((Option) sq.getSelectedItem())
				.getValue()));
		huanBean.setLightSensor(Integer.parseInt(((Option) dk.getSelectedItem())
				.getValue()));
		huanBean.setDoorSystem(Integer.parseInt(((Option) mj.getSelectedItem())
				.getValue()));
		huanBean.setIpCamer(Integer.parseInt(((Option) ip.getSelectedItem())
				.getValue()));
		huanBean.seteMeter(Integer.parseInt(((Option) zndb.getSelectedItem())
				.getValue()));
		//�����������ĸ����Һ��ͺ�
		huanBean.setMjzjCode(mjzjcj.getSelectedItem().toString());
		huanBean.setMjzjType(mjzjxh.getSelectedItem().toString());
		huanBean.setJkzjCode(jkzjcj.getSelectedItem().toString());
		huanBean.setJkzjType(jkzjxh.getSelectedItem().toString());
		huanBean.setCheckUserId(ApplicationData.user.getId() + "");
		try {
			if(newflag){
				TowerSQliteDbBean.insertDh(db, huanBean);
			}else{
				TowerSQliteDbBean.updateGuardData(db, huanBean);
			}
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		} finally {
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
				Bundle bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags+"");
				forwardIntent(getApplicationContext(), TxDetailActivity.class,bundle);
				finish();
				break;
			case -1:
				toastMessage("����ʧ�ܣ�");
				break;
			case 2:
				if(list!=null && list.size()>0){
					newflag=false;
					dhbean=list.get(0);
					String cjStr = dhbean.getMakeCode();
					String mjcjStr=dhbean.getMjzjCode();
					String jkzjStr=dhbean.getJkzjCode();
					String temStr = dhbean.getTempSensor() + "";
					String ygStr = dhbean.getSmokeSensor() + "";
					String redStr = dhbean.getReadSensor() + "";
					String waterStr = dhbean.getWaterSensor() + "";
					String dkStr = dhbean.getLightSensor() + "";
					String ipStr = dhbean.getIpCamer() + "";
					String doorStr = dhbean.getDoorSystem() + "";
					String autoStr = dhbean.geteMeter() + "";
					selectedSpinner(SpinnerUtilBean.getDh(), temStr, tem);
					selectedSpinner(SpinnerUtilBean.getDh(), ygStr, yg);
					selectedSpinner(SpinnerUtilBean.getDh(), redStr, hw);
					selectedSpinner(SpinnerUtilBean.getDh(), waterStr, sq);
					selectedSpinner(SpinnerUtilBean.getDh(), dkStr, dk);
					selectedSpinner(SpinnerUtilBean.getDh(), ipStr, ip);
					selectedSpinner(SpinnerUtilBean.getDh(), doorStr, mj);
					selectedSpinner(SpinnerUtilBean.getDh(), autoStr, zndb);
					//�����������ĸ����Һ��ͺ�
					selectedSpinnerCj(SpinnerUtilBean.getDhFsyCjType(), cjStr, jfjg);
					selectedSpinnerCj(SpinnerUtilBean.getDhMjCjType(), mjcjStr, mjzjcj);
					selectedSpinnerCj(SpinnerUtilBean.getDhZjCjType(), jkzjStr, jkzjcj);
				}else{
					newflag=true;
				}
				break;
			}
		}
	};

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
	 * 
	 * �˷����������ǣ���������
	 * @Title: fillSpinnerAdapter 
	 * @author: ��Ȼ
	 * @param list
	 * @param spinner
	 * @param prompt
	 * @return void    ��������
	 * @version: 2015-8-24
	����9:51:46
	 */
	private void fillSpinnerAdapterCj(List<Option> list, Spinner spinner,
			CharSequence prompt) {
		List<String> newlist=new ArrayList<String>();
		Set<String> strSet=new HashSet<String>();
		for(Option o:list){
			strSet.add(o.getText());
		}
		Iterator<String> it=strSet.iterator();
		while(it.hasNext()){
			newlist.add(it.next());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.xml.custom_spinner_item, newlist);
		adapter.setDropDownViewResource(R.xml.spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setPrompt(prompt);
	}

	/**
	 * 
	 * �˷����������ǣ������ͺ�
	 * @Title: fillAdapterType 
	 * @author: ��Ȼ
	 * @param options
	 * @return
	 * @return ArrayAdapter<String>    ��������
	 * @version: 2015-8-24
	����10:02:46
	 */
	private void fillAdapterType(List<String> options,Spinner spinner,
			CharSequence prompt) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.xml.custom_spinner_item, options);
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
	 * 
	 * �˷����������ǣ�����
	 * @Title: selectedSpinnerCj 
	 * @author: ��Ȼ
	 * @param option
	 * @param str
	 * @param spinner
	 * @return void    ��������
	 * @version: 2015-8-24
	����11:22:24
	 */
	private void selectedSpinnerCj(List<Option> option, String str,
			Spinner spinner) {
		List<String> newlist=new ArrayList<String>();
		Set<String> strSet=new HashSet<String>();
		for(Option o:option){
			strSet.add(o.getText());
		}
		Iterator<String> it=strSet.iterator();
		while(it.hasNext()){
			newlist.add(it.next());
		}
		for (String o : newlist) {
			if (o.equals(str)) {
				spinner.setSelection(fillAdapterCj(newlist).getPosition(o), true);
				break;
			}
		}
	}

	/**
	 * 
	 * �˷����������ǣ��ͺ�
	 * @Title: selectedSpinnerType 
	 * @author: ��Ȼ
	 * @param option
	 * @param str
	 * @param spinner
	 * @return void    ��������
	 * @version: 2015-8-24
	����3:58:54
	 */
	private void selectedSpinnerType(List<String> option, String str,
			Spinner spinner) {
		for (String o : option) {
			if (o.equals(str)) {
				spinner.setSelection(fillAdapterCj(option).getPosition(str), true);
				break;
			}
		}
	}

	/**
	 * 
	 * �˷����������ǣ�
	 * @Title: fillAdapterCj 
	 * @author: ��Ȼ
	 * @param options
	 * @return
	 * @return ArrayAdapter<Option>    ��������
	 * @version: 2015-8-24
	����3:55:37
	 */
	private ArrayAdapter<String> fillAdapterCj(List<String> options) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.xml.custom_spinner_item, options);
		adapter.setDropDownViewResource(R.xml.spinner_dropdown_item);
		return adapter;
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
	@Override
	protected void onResume() {
		super.onResume();
		losdList();
	}
}