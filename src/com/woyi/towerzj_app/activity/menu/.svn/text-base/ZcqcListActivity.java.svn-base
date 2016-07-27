package com.woyi.towerzj_app.activity.menu;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

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
import com.woyi.towerzj_app.adapter.AppPhyInfoAdapter;
import com.woyi.towerzj_app.bean.form.ZcqcQueryForm;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoList;
import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Function;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;

/**
 * 
* 此类描述的是：资产清查
* @author: 罗然
* @version: 2015-7-17 下午4:59:59
* @ClassName: ZcqcListActivity 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.activity.menu
 */
public class ZcqcListActivity extends ForwardActivity{

	private Button back;
	private TextView title,cz;
	private ListView listview;
	
	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	
	private AppPhyInfoAdapter adapter;
	private ZcqcQueryForm formBean;
	private List<AppPhyInfoBean> list;
	private AppPhyInfoList data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		formBean=(ZcqcQueryForm) this.getIntent().getExtras().getSerializable("bean");
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_zcqc_list);
		SysExitUtil.activityList.add(this);
		title.setText("资产清查");
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		relative_layout.setVisibility(View.VISIBLE);
		loading.startAnimation(animation);
		ApplicationData.getExecutorService().submit(run);
		cz.setVisibility(View.GONE);
		back.setOnClickListener(btnLis);
		listview.setOnItemClickListener(itemLis);
	}
	
	/**
	 * 按钮监控事件
	 */
	private OnClickListener btnLis=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			finish();
		}
	};
	

	/**
	 * 列表点击事件
	 */
	private OnItemClickListener itemLis = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			AppPhyInfoBean bean = (AppPhyInfoBean) arg0.getItemAtPosition(arg2);
			Bundle bundle = new Bundle();
			bundle.putSerializable("bean", bean);
			forwardIntent(getApplicationContext(), ZcqcDetailActivity.class, bundle);
		}
	};
	
	private Runnable run = new Runnable() {

		@Override
		public void run() {
			try {
				String result = Function.getPhyList(formBean);
				ObjectMapper om = new ObjectMapper();
				data = om.readValue(result, TypeFactory.fromTypeReference(new TypeReference<AppPhyInfoList>() {}));
				if(data.getExcuteStatue()!=-1){
					if(data.getExcuteStatue()==1){
						handler.obtainMessage(1).sendToTarget();
					}else if(data.getExcuteStatue()==0){
						handler.obtainMessage(0).sendToTarget();
					}
				}else{
					handler.obtainMessage(-1).sendToTarget();
				}
			} catch (Exception e) {
				handler.obtainMessage(-1).sendToTarget();
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
				list = data.getAppPhyInfoList();
				if(null != list && list.size() > 0){
					adapter = new AppPhyInfoAdapter(getApplicationContext(), list);
					listview.setAdapter(adapter);
				}
				break;
			case 0:
			//	toastMessage("没有相关数据！");
				finish();
				break;
			case -1:
				toastMessage("请求失败！");
				finish();
				break;
			}
		}
	};

}
