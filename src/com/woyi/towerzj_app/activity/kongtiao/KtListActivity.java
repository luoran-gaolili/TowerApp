package com.woyi.towerzj_app.activity.kongtiao;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.activity.xudianchi.XdcListActivity;
import com.woyi.towerzj_app.adapter.kongtiao.AppKongtiaoAdapter;
import com.woyi.towerzj_app.bean.kongtiao.AppAirBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;

/**
 * 
* �����������ǣ��յ�
* @author: ��Ȼ
* @version: 2015-7-18 ����11:51:29
* @ClassName: KtListActivity 
* @��Ŀ�� towerzj_app
* @����com.woyi.towerzj_app.activity.kongtiao
 */
public class KtListActivity extends ForwardActivity{

	private Button back;
	private TextView title,add,next,code,address;
	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	private ListView listview;
	private AppKongtiaoAdapter adapter;
	private List<AppAirBean> list;
	private AppPhyInfoBean bean;
	
	private boolean first=true;
	private DatabaseHelper mOpenHelper;
	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		bean=(AppPhyInfoBean) this.getIntent().getExtras().getSerializable("bean");
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_jifang_list);
		SysExitUtil.activityList.add(this);
		title.setText("�յ�");
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		relative_layout.setVisibility(View.VISIBLE);
		loading.startAnimation(animation);
		loadList();
		code.setText(bean.getPhysicAlias());
		address.setText(bean.getPhysicName());
		back.setOnClickListener(btnLis);
		add.setOnClickListener(btnLis);
		next.setOnClickListener(btnLis);
		listview.setOnItemClickListener(itemLis);
	}

	/**
	 * ��ť�����¼�
	 */
	private OnClickListener btnLis=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.back:
				finish();
				break;
			case R.id.add:
				bundle=new Bundle();
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(), KtAddActivity.class, bundle);
				break;
			case R.id.next:
				bundle=new Bundle();
				bundle.putSerializable("bean", bean);
				forwardIntent(getApplicationContext(), XdcListActivity.class, bundle);
				break;
			}
		}
	};
	
	/**
	 * �б�����¼�
	 */
	private OnItemClickListener itemLis = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			AppAirBean ktbean = (AppAirBean) arg0.getItemAtPosition(arg2);
			Bundle bundle = new Bundle();
			bundle.putSerializable("ktbean", ktbean);
			bundle.putSerializable("bean", bean);
			forwardIntent(getApplicationContext(), KtDetailActivity.class, bundle);
		}
	};
	
	/**
	 * 
	* �˷����������ǣ����ر����б�
	* @Title: loadList 
	* @author: ��Ȼ
	* @return void    ��������
	* @version: 2015-7-21
	����10:00:11
	 */
	private void loadList() {
		list = new ArrayList<AppAirBean>();
		String sql = "select id,physicCode,code,name,cjCheck,cj,ewm,checkUserId,checkDate from tb_air_c ";
		try {
			list=TowerSQliteDbBean.queryAirData(mOpenHelper, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
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
				if(null != list && list.size() > 0){
					adapter = new AppKongtiaoAdapter(getApplicationContext(), list);
					listview.setAdapter(adapter);
				}else{
				//	toastMessage("û��������ݣ�");
				}
				
				break;
			case -1:
				toastMessage("����ʧ�ܣ�");
				finish();
				break;
			}
			first=false;
		}
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		if (!first) {
			relative_layout.setVisibility(View.VISIBLE);
			loading.startAnimation(animation);
			loadList();
		}
	}
}
