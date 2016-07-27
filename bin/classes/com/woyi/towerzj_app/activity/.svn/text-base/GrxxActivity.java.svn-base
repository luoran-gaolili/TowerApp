package com.woyi.towerzj_app.activity;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;

/**
 * 
* 此类描述的是：个人信息
* @author: 罗然
* @version: 2015-7-23 下午3:23:19
* @ClassName: GrxxActivity 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.activity
 */
public class GrxxActivity extends ForwardActivity{

	private Button back;
	private TextView title,cz;
	private TextView account,xm,dwmc,dlsj;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_grxx);
		SysExitUtil.activityList.add(this);
		title.setText("个人信息");
		cz.setVisibility(View.GONE);
		account.setText(ApplicationData.user.getUsername());
		xm.setText(ApplicationData.user.getTruename());
		dwmc.setText(ApplicationData.user.getDepartment());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		String str=sdf.format(ApplicationData.user.getLoginDate());
		dlsj.setText(str);
		back.setOnClickListener(lisBtn);
	}

	private OnClickListener lisBtn=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			finish();
		}
	};
}
