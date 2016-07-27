package com.woyi.towerzj_app.activity.menu;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.activity.tawei.TaweiListActivity;
import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;

/**
 * 
* 此类描述的是：资产清查
* @author: 罗然
* @version: 2015-7-18 上午8:36:44
* @ClassName: ZcqcDetailActivity 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.activity.menu
 */
public class ZcqcDetailActivity extends ForwardActivity{

	private Button back,next;
	private TextView title,cz;
	private TextView wlzd_code,wlzd_name,address;
	
	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_zcqc_detail);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		cz.setVisibility(View.GONE);
		title.setText("");
		relative_layout.setVisibility(View.VISIBLE);
		loading.startAnimation(animation);
		ApplicationData.getExecutorService().submit(run);
		back.setOnClickListener(btnLis);
		next.setOnClickListener(btnLis);
	}

	/**
	 * 按钮监控事件
	 */
	private OnClickListener btnLis=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.back:
				finish();
				break;
			case R.id.next:
				forwardIntent(getApplicationContext(), TaweiListActivity.class);
				break;
			}
		}
	};
	
	private Runnable run=new Runnable() {
		
		@Override
		public void run() {
			try {
				handler.obtainMessage(1).sendToTarget();
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
				wlzd_code.setText("");
				wlzd_name.setText("");
				address.setText("");
				break;
			case 0:
			//	toastMessage("没有相关数据！");
				break;
			case -1:
				toastMessage("请求失败！");
				break;
			}
		}
	};
}
