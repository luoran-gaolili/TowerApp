package com.woyi.towerzj_app.activity.menu;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.bean.form.ZcqcQueryForm;
import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;

/**
 * 
* 此类描述的是：查询
* @author: 罗然
* @version: 2015-7-17 下午4:40:37
* @ClassName: QueryAvtivity 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.activity.menu
 */
public class QueryActivity extends ForwardActivity{

	private TextView title,cz;
	private Button back,query;
	private EditText wlzd_code,wlzd_name;
	
	private ZcqcQueryForm formBean;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_main_query);
		SysExitUtil.activityList.add(this);
		title.setText("资产数据");
		cz.setVisibility(View.GONE);
		query.setOnClickListener(btnLis);
		back.setOnClickListener(btnLis);
	}
	
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
			case R.id.query:
				String codeStr=wlzd_code.getText().toString().trim();
				String nameStr=wlzd_name.getText().toString().trim();
				if((codeStr==null || codeStr.equals("")) && (nameStr==null || nameStr.equals(""))){
					toastMessage("站址编码和站址名称必须输入一项！");
					return;
				}
				if(formBean==null){
					formBean=new ZcqcQueryForm();
				}
				formBean.setUserid(ApplicationData.user.getId());
				formBean.setName(nameStr);
				formBean.setCode(codeStr);
				Bundle bundle=new Bundle();
				bundle.putSerializable("bean", formBean);
				forwardIntent(getApplicationContext(), QueryListActivity.class,bundle);
				break;
			}
		}
	};

}
