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
* �����������ǣ���ѯ
* @author: ��Ȼ
* @version: 2015-7-17 ����4:40:37
* @ClassName: QueryAvtivity 
* @��Ŀ�� towerzj_app
* @����com.woyi.towerzj_app.activity.menu
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
		title.setText("�ʲ�����");
		cz.setVisibility(View.GONE);
		query.setOnClickListener(btnLis);
		back.setOnClickListener(btnLis);
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
			case R.id.query:
				String codeStr=wlzd_code.getText().toString().trim();
				String nameStr=wlzd_name.getText().toString().trim();
				if((codeStr==null || codeStr.equals("")) && (nameStr==null || nameStr.equals(""))){
					toastMessage("վַ�����վַ���Ʊ�������һ�");
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
