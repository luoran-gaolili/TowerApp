package com.woyi.towerzj_app.adapter.kongtiao;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.bean.kongtiao.AppAirBean;
import com.woyi.towerzj_app.util.Reflex;

/**
 * 
* 此类描述的是：资产清查
* @author: 罗然
* @version: 2015-7-19 上午9:16:59
* @ClassName: AppPhyInfoAdapter 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.adapter
 */
public class AppKongtiaoAdapter extends BaseAdapter{

	public class TaweiViewHolder {
		public TextView bh;
		public TextView cj;
	}

	private Context context;
	private List<AppAirBean> list;
	private LayoutInflater mInflater;

	public AppKongtiaoAdapter(Context context, List<AppAirBean> list) {
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
		final TaweiViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.xml.item_kongtiao, null);
			holder = new TaweiViewHolder();
			Reflex.loadViewForAdapterGetView(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (TaweiViewHolder) convertView.getTag();
		}
		AppAirBean info = list.get(position);
		if (info != null) {
			holder.bh.setText(info.getCode());
			if(info.getCjCheck()!=null &&!info.getCjCheck().equals("") && !info.getCjCheck().equals("null")){
				holder.bh.setTextColor(android.graphics.Color.rgb(0x66, 0xB4, 0x48));
				holder.cj.setText(info.getCjCheck());
			}else{
				holder.cj.setText(info.getCj());
			}
		}
		return convertView;
	}
}