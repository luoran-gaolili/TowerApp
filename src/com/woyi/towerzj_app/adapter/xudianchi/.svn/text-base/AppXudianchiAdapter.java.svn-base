package com.woyi.towerzj_app.adapter.xudianchi;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.bean.xudianchi.AppBatteryBean;
import com.woyi.towerzj_app.util.Reflex;

/**
 * 
* 此类描述的是：蓄电池
* @author: 罗然
* @version: 2015-7-19 下午6:47:36
* @ClassName: AppXudianchiAdapter 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.adapter.xudianchi
 */
public class AppXudianchiAdapter extends BaseAdapter{

	public class ViewHolder {
		public TextView bh;
		public TextView pp;
		public TextView rl;
	}

	private Context context;
	private List<AppBatteryBean> list;
	private LayoutInflater mInflater;

	public AppXudianchiAdapter(Context context, List<AppBatteryBean> list) {
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
			convertView = mInflater.inflate(R.xml.item_xudianchi, null);
			holder = new ViewHolder();
			Reflex.loadViewForAdapterGetView(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		AppBatteryBean info = list.get(position);
		if (info != null) {
			holder.bh.setText(info.getCode());
			if(info.getPpCheck()!=null &&!info.getPpCheck().equals("")&& !info.getPpCheck().equals("null")){
				holder.bh.setTextColor(android.graphics.Color.rgb(0x66, 0xB4, 0x48));
				holder.pp.setText(info.getPpCheck());
			}else{
				holder.pp.setText(info.getPp());
			}
			if(info.getRlCheck()!=0.0){
				holder.bh.setTextColor(android.graphics.Color.rgb(0x66, 0xB4, 0x48));
				holder.rl.setText(info.getRlCheck()+"");
			}else{
				holder.rl.setText(info.getRl()+"");
			}
		}
		return convertView;
	}
}