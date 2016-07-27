package com.woyi.towerzj_app.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.Reflex;

/**
 * 
 * 此类描述的是：已上传
 * 
 * @author: 罗然
 * @version: 2015-7-17 下午4:26:55
 * @ClassName: MenuYscAdapter
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.adapter
 */
public class MenuYscAdapter extends BaseAdapter {

	public class ViewHolder {
		public TextView mc;
		public ImageView del;

	}

	private Context context;
	private List<AppPhyInfoBean> list;
	private LayoutInflater mInflater;

	public MenuYscAdapter(Context context, List<AppPhyInfoBean> list) {
		super();
		this.context = context;
		this.list = list;
		mInflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// dbBean = new TowerSQliteDbBean();
		// mOpenHelper = new DatabaseHelper(context);
		// database = mOpenHelper.getReadableDatabase();
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
	public View getView(final int position, View convertView,
			ViewGroup viewGroup) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.xml.item_menu_ysc, null);
			holder = new ViewHolder();
			Reflex.loadViewForAdapterGetView(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.del.setVisibility(View.INVISIBLE);
		AppPhyInfoBean info = list.get(position);
		if (info != null) {
			holder.mc.setText(info.getPhysicName());
			holder.mc
					.setTextColor(android.graphics.Color.rgb(0xE5, 0xB3, 0x5E));
		}
		return convertView;
	}

	/**
	 * 增加数据
	 * 
	 * @param bean
	 */
	public void addItem(AppPhyInfoBean bean) {
		list.add(bean);
		this.notifyDataSetChanged();
	}
}