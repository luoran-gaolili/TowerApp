package com.woyi.towerzj_app.adapter.jifang;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.bean.jifang.AppRoomBean;
import com.woyi.towerzj_app.util.Reflex;

/**
 * 
* 此类描述的是：机房
* @author: 罗然
* @version: 2015-7-19 下午5:13:48
* @ClassName: AppJiFangAdapter 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.adapter.jifang
 */
public class AppJiFangAdapter extends BaseAdapter{

	public class TaweiViewHolder {
		public TextView bh;
		public TextView lx;
		public TextView jg;
	}

	private Context context;
	private List<AppRoomBean> list;
	private LayoutInflater mInflater;

	public AppJiFangAdapter(Context context, List<AppRoomBean> list) {
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
			convertView = mInflater.inflate(R.xml.item_jifang, null);
			holder = new TaweiViewHolder();
			Reflex.loadViewForAdapterGetView(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (TaweiViewHolder) convertView.getTag();
		}
		AppRoomBean info = list.get(position);
		if (info != null) {
			holder.bh.setText(info.getCode());
			if(info.getLxCheck()!=null &&!info.getLxCheck().equals("")&& !info.getLxCheck().equals("null")){
				holder.bh.setTextColor(android.graphics.Color.rgb(0x66, 0xB4, 0x48));
				holder.lx.setText(info.getLxCheck());
			}else{
				holder.lx.setText(info.getLx());
			}
			if(info.getJgCheck()!=null &&!info.getJgCheck().equals("")&& !info.getJgCheck().equals("null")){
				holder.bh.setTextColor(android.graphics.Color.rgb(0x66, 0xB4, 0x48));
				holder.jg.setText(info.getJgCheck());
			}else{
				holder.jg.setText(info.getJg());
			}
			if(info.getMjCheck()!=0.0){
				holder.bh.setTextColor(android.graphics.Color.rgb(0x66, 0xB4, 0x48));
			}
		}
		return convertView;
	}
}