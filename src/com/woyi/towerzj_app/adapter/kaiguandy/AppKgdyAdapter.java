package com.woyi.towerzj_app.adapter.kaiguandy;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.bean.kgdy.AppSwitchBean;
import com.woyi.towerzj_app.util.Reflex;

/**
 * 
* 此类描述的是：开关电源
* @author: 罗然
* @version: 2015-7-20 上午9:30:36
* @ClassName: AppKgdyAdapter 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.adapter.kaiguandy
 */
public class AppKgdyAdapter extends BaseAdapter{

	public class ViewHolder {
		public TextView bh;
		public TextView pp;
		public TextView rl;
	}

	private Context context;
	private List<AppSwitchBean> list;
	private LayoutInflater mInflater;

	public AppKgdyAdapter(Context context, List<AppSwitchBean> list) {
		super();
		this.context = context;
		this.list = list;
		mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
	public View getView(final int position, View convertView, ViewGroup viewGroup) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.xml.item_kgdy, null);
			holder = new ViewHolder();
			Reflex.loadViewForAdapterGetView(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		AppSwitchBean info = list.get(position);
		if (info != null) {
			holder.bh.setText(info.getCode());
			holder.pp.setText(info.getPp());
			holder.rl.setText(info.getRl()+"");
		}
		return convertView;
	}
}