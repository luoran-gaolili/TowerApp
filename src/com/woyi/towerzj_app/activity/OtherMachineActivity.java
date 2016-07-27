package com.woyi.towerzj_app.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.donghuan.DhDetailActivity;
import com.woyi.towerzj_app.bean.AppOtherDepartment;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;

/**
 * 
 * 此类描述的是：其他设备
 * @author: 罗然
 * @version: 2015-8-14 下午2:36:34
 * @ClassName: OtherMachineActivity 
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity
 */
public class OtherMachineActivity extends ForwardActivity {

	private Button back;
	private TextView title,add,next,save;
	private ListView listview;
	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;
	private QtsbAdapter adapter;

	private List<AppOtherDepartment> list=new ArrayList<AppOtherDepartment>();
	private Map<String, AppOtherDepartment> map = new LinkedHashMap<String, AppOtherDepartment>();
	private int flags = 0;
	private SQLiteDatabase db;
	private DatabaseHelper mOpenHelper;
	private AppPhyInfoBean bean;
	private Bundle bundle;
	private List<String> idlist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_qisb_list);
		SysExitUtil.activityList.add(this);
		mOpenHelper = new DatabaseHelper(this);
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		flags = Integer.parseInt(this.getIntent().getExtras().getString("flag"));
		title.setText("其他设备");
		loadList();
		add.setVisibility(View.GONE);
//		listview.setOnItemClickListener(itemLis);
		back.setOnClickListener(btnLis);
		add.setOnClickListener(btnLis);
		next.setOnClickListener(btnLis);
		save.setOnClickListener(btnLis);
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
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags+"");
				forwardIntent(getApplicationContext(), DhDetailActivity.class,
						bundle);
				break;
			case R.id.save:
				idlist=new ArrayList<String>();
				if(map!=null && !map.isEmpty()){
					Iterator<Entry<String, AppOtherDepartment>> it = map.entrySet().iterator();
					Entry<String, AppOtherDepartment> entry = null;
					while (it.hasNext()) {
						entry = it.next();
						idlist.add(entry.getValue().getId());
					}
				}
//				if(idlist==null || idlist.size()==0){
//					db = mOpenHelper.getReadableDatabase();
//					TowerSQliteDbBean.updateAllOtherDepartment(db, bean.getPhysicCode());
//					db.close();
//				}
				saveData();
				break;
			}
		}
	};

	/**
	 * 
	 * 此方法描述的是：加载设备
	 * @Title: loadList 
	 * @author: 罗然
	 * @return void    返回类型
	 * @version: 2015-8-14
	下午4:06:08
	 */
	private void loadList() {
		list = new ArrayList<AppOtherDepartment>();
		String sql = "select * from t_other_department where physicCode='"+bean.getPhysicCode()+"'";
		try {
			list = TowerSQliteDbBean.queryDepartment(mOpenHelper, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}

	}

	/**
	 * 
	 * 此方法描述的是：保存设备信息
	 * @Title: saveData 
	 * @author: 罗然
	 * @return void    返回类型
	 * @version: 2015-8-14
	下午5:30:58
	 */
	protected void saveData() {
		try{
			db = mOpenHelper.getReadableDatabase();
			db.beginTransaction();
			TowerSQliteDbBean.updateAllOtherDepartment(db, bean.getPhysicCode());
			for(String o:idlist){
				TowerSQliteDbBean.updateOtherDepartment(db, bean.getPhysicCode(), o);
			}
			db.setTransactionSuccessful();
			handler.obtainMessage(2).sendToTarget();
		}catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}finally {
			if (db != null) {
				db.endTransaction();
				db.close();
			}
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
			case -1:
				toastMessage("请求失败！");
				break;
			case 0:
				//				toastMessage("没有相关数据！");
				break;
			case 1:
				if (list != null && list.size() > 0) {
					adapter = new QtsbAdapter(
							OtherMachineActivity.this, list, map);
					listview.setAdapter(adapter);
				}
				break;
			case 2:
				bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags+"");
				forwardIntent(getApplicationContext(), DhDetailActivity.class,
						bundle);
				break;
			}
		}
	};

	public class QtsbAdapter extends BaseAdapter{

		public class ItemHolder {
			public TextView name;
			public CheckBox cb;
		}

		public  HashMap<Integer, Boolean> isSelected=new HashMap<Integer, Boolean>();
		private List<AppOtherDepartment> list;
		private Context context;
		private LayoutInflater mInflater;
		private Map<String,AppOtherDepartment> map;

		public QtsbAdapter(Context context, List<AppOtherDepartment> list,Map<String ,AppOtherDepartment>map) {
			this.list = list;
			this.context = context;
			this.map=map;
			mInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			init();
		}

		// 初始化 设置所有checkbox都为未选择
		private void init() {
			isSelected = new HashMap<Integer, Boolean>();
			for (int i = 0; i < list.size(); i++) {
				isSelected.put(i, false);
			}
		}

		@Override
		public int getCount() {
			return this.list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ItemHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.xml.qtsb_select_list_item, null);
				holder = new ItemHolder();
				Reflex.loadViewForAdapterGetView(holder, convertView);
				holder.cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if(isChecked){
							map.put(list.get(position).getId(), list.get(position));
						}else{
							map.remove(list.get(position).getId());
						}
					}
				});
				convertView.setTag(holder);
			} else {
				holder = (ItemHolder) convertView.getTag();
				holder.cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if(isChecked){
							map.put(list.get(position).getId(), list.get(position));
						}else{
							map.remove(list.get(position).getId());
						}
					}
				});
			}
			final AppOtherDepartment info = list.get(position);
			if (info != null) {
				holder.name.setText(info.getName());
				if(info.getIsHave().equals("1")){
					holder.cb.setChecked(true);
				}else{
					holder.cb.setChecked(false);
				}
			}
			return convertView;
		}


		class ColorView extends View {
			public ColorView(Context context) {
				super(context);
			}

			public ColorStateList getColor() {
				int[][] states = { View.PRESSED_ENABLED_STATE_SET,
						View.FOCUSED_STATE_SET, View.EMPTY_STATE_SET, };
				int[] color = { Color.WHITE, Color.BLUE,
						android.graphics.Color.rgb(51, 51, 51) };
				return new ColorStateList(states, color);
			}
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		loadList();
	}
}
