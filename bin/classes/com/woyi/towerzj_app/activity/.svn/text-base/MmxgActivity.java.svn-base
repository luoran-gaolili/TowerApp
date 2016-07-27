package com.woyi.towerzj_app.activity;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Function;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;

/**
 * 
 * 此类描述的是：密码修改
 * 
 * @author: 罗然
 * @version: 2015-7-23 下午3:16:58
 * @ClassName: MmxgActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity
 */
public class MmxgActivity extends ForwardActivity {

	private TextView title, cz;
	private Button back, xsmm;
	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	private EditText ymm, xmm, qrxmm;
	private String ymmStr, xmmStr, qrxmmStr;
	private boolean flag = true;
	public static SharedPreferences sp;
	private String passWord;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_mmxg);
		SysExitUtil.activityList.add(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE
				| Context.MODE_MULTI_PROCESS);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		passWord = sp.getString("USER_PASSWORD", "");
		title.setText("密码修改");
		back.setVisibility(View.VISIBLE);
		cz.setText("修改");
		back.setOnClickListener(btnLis);
		cz.setOnClickListener(btnLis);
		xsmm.setOnClickListener(btnLis);
	}

	/**
	 * 按钮点击事件
	 */
	private View.OnClickListener btnLis = new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			int id = arg0.getId();
			switch (id) {
			case R.id.back:
				finish();
				break;
			case R.id.cz:
				ymmStr = ymm.getText().toString();
				xmmStr = xmm.getText().toString();
				qrxmmStr = qrxmm.getText().toString();
				if (ymmStr == null || ymmStr.equals("")) {
					toastMessage("请输入原密码！");
					return;
				}
			
				if (!ymmStr.equals(passWord)) {
					toastMessage("原密码不正确！");
					return;
				}
				if (xmmStr == null || xmmStr.equals("")) {
					toastMessage("请输入新密码！");
					return;
				}
				if (qrxmmStr == null || qrxmmStr.equals("")) {
					toastMessage("请确认新密码！");
					return;
				}
				if (!xmmStr.equals(qrxmmStr)) {
					toastMessage("两次密码不一致！");
					return;
				}
				relative_layout.setVisibility(View.VISIBLE);
				loading.startAnimation(animation);
				ApplicationData.getExecutorService().submit(updatePassRun);
				break;
			case R.id.xsmm:
				if (flag) {
					// 显示密码
					ymm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					xmm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					qrxmm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					xsmm.setBackgroundResource(R.drawable.pass_true);
				} else {
					// 隐藏密码
					ymm.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
					xmm.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
					qrxmm.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
					xsmm.setBackgroundResource(R.drawable.pass_false);
				}
				flag = !flag;
				break;
			}
		}
	};

	/**
	 * 密码修改
	 */
	private Runnable updatePassRun = new Runnable() {

		@Override
		public void run() {
			try {
				String result = Function.updatePassByUserid(xmmStr);
				ObjectMapper om = new ObjectMapper();
				String info = om.readValue(result, TypeFactory
						.fromTypeReference(new TypeReference<String>() {
						}));
				if (info != null && info.equals("1")) {
					handler.obtainMessage(1).sendToTarget();
				} else {
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
			case -1:
				toastMessage("密码修改失败！");
				break;
			case 1:
				toastMessage("密码修改成功！");
				finish();
				SysExitUtil.exit();
				forwardIntent(getApplicationContext(), LoginActivity.class);
				break;
			}
		}
	};
}