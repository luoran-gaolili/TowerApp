package com.woyi.towerzj_app.activity;

import android.database.sqlite.SQLiteDatabase;
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

import com.woyi.towerzj_app.activity.menu.MainActivity;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;

/**
 * 
 * �����������ǣ���������
 * 
 * @author: ��Ȼ
 * @version: 2015-7-22 ����8:52:34
 * @ClassName: EndActivity
 * @��Ŀ�� towerzj_app
 * @����com.woyi.towerzj_app.activity
 */
public class EndActivity extends ForwardActivity {

	private Button back, save;
	private TextView title, cz;
	private TextView code, address;
	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	private DatabaseHelper mOpenHelper;
	private AppPhyInfoBean bean;
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		db = mOpenHelper.getReadableDatabase();
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_end);
		SysExitUtil.activityList.add(this);
		title.setText("�ʲ����");
		save.setText("���");
		// cz.setVisibility(View.VISIBLE);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});

		if (bean.getStatus().equals("3")
				|| bean.getStatus().equals("4")) {
			save.setText("������ҳ");
		}
		code.setText(bean.getPhysicAlias());
		address.setText(bean.getPhysicName());
		back.setOnClickListener(btnLis);
		save.setOnClickListener(btnLis);
		
	}

	/**
	 * ��ť����¼�
	 */
	private OnClickListener btnLis = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.back:
				finish();
				break;
			case R.id.save:// �����������
				if (bean.getStatus().equals("3")
						|| bean.getStatus().equals("4")) {
					forwardIntent(getApplicationContext(), MainActivity.class);
				}else{
					try{
						db= mOpenHelper.getReadableDatabase();
						TowerSQliteDbBean.changeStatus(db,bean.getPhysicCode());
						forwardIntent(getApplicationContext(), MainActivity.class);
					//	toastMessage(bean.getPhysicAlias() + "���ʲ������ɣ�");
					}catch (Exception e) {
						e.printStackTrace();
					}finally {
						if(db!=null){
							db.close();
						}
					}
				}
				break;
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

			switch (msg.what) {
			case 1:
				loading.clearAnimation();
		//		toastMessage(bean.getPhysicAlias() + "���ʲ������ɣ�");
				// save.setVisibility(View.GONE);
				break;
			case -1:
				toastMessage("����ʧ�ܣ�");
				break;
			}
		}
	};

}
