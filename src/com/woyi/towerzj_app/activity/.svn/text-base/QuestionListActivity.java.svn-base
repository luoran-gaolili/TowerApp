package com.woyi.towerzj_app.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woyi.towerzj_app.adapter.AppQuestionAdapter;
import com.woyi.towerzj_app.bean.quedtion.AppProblemBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;

/**
 * 
 * 此类描述的是：问题
 * @author: 罗然
 * @version: 2015-7-18 下午3:55:15
 * @ClassName: QuestionListActivity 
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity
 */
public class QuestionListActivity extends ForwardActivity{

	private Button back;
	private TextView title,cz;

	private ListView listview;
	private RelativeLayout relative_layout;
	private ImageView loading;
	private Animation animation;

	private AppQuestionAdapter adapter;
	private List<AppProblemBean> list;
	private AppPhyInfoBean bean;

	private String flag,code;
	private DatabaseHelper mOpenHelper;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		flag=this.getIntent().getExtras().getString("flag");
		code=this.getIntent().getExtras().getString("code");
		bean=(AppPhyInfoBean) this.getIntent().getExtras().getSerializable("bean");
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_question_list);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(layLis);
		title.setText("问题列表");
		relative_layout.setVisibility(View.VISIBLE);
		loading.startAnimation(animation);
		loadList();
		back.setOnClickListener(btnLis);
		cz.setVisibility(View.GONE);
		listview.setOnItemClickListener(itemLis);
	}

	private View.OnTouchListener layLis = new OnTouchListener() {

		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			return true;
		}
	};

	/**
	 * 按钮监听事件
	 */
	private OnClickListener btnLis=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.back:
				finish();
				break;
			}
		}
	};

	/**
	 * 列表点击事件
	 */
	private OnItemClickListener itemLis = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			AppProblemBean probean = (AppProblemBean) arg0.getItemAtPosition(arg2);
			Bundle bundle = new Bundle();
			bundle.putSerializable("probean", probean);
			bundle.putSerializable("bean", bean);
			bundle.putString("flag", flag);
			bundle.putString("code", code);
			bundle.putString("protype", "1");
			forwardIntent(getApplicationContext(), QuestionDetailActivity.class, bundle);
		}
	};
	
	/**
	 * 
	* 此方法描述的是：加载列表
	* @Title: loadList 
	* @author: 罗然
	* @return void    返回类型
	* @version: 2015-7-21
	上午10:02:02
	 */
	private void loadList() {
		list = new ArrayList<AppProblemBean>();
		String sql = "select * from t_problem where type='"+flag+"'";
		try {
			list=TowerSQliteDbBean.queryProblemData(mOpenHelper, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
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
				if(null != list && list.size() > 0){
					adapter = new AppQuestionAdapter(getApplicationContext(), list);
					listview.setAdapter(adapter);
				}else{
				//	toastMessage("没有相关数据！");
					finish();
				}
				break;
			case -1:
				toastMessage("请求失败！");
				finish();
				break;
			}
		}
	};


}


