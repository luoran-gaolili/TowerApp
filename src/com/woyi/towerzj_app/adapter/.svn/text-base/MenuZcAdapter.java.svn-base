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
* 此类描述的是：暂存
* @author: 罗然
* @version: 2015-7-17 下午4:27:12
* @ClassName: MenuZcAdapter 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.adapter
 */
public class MenuZcAdapter extends BaseAdapter{

	public class ViewHolder {
		public TextView mc;
		public TextView code;
		public TextView flag;
		public ImageView upload;
	}

	private Context context;
	private List<AppPhyInfoBean> list;
	private LayoutInflater mInflater;

	public MenuZcAdapter(Context context, List<AppPhyInfoBean> list) {
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
			convertView = mInflater.inflate(R.xml.item_menu_zc, null);
			holder = new ViewHolder();
			Reflex.loadViewForAdapterGetView(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		AppPhyInfoBean info = list.get(position);
		if (info != null) {
			holder.mc.setText(info.getPhysicAlias()+"--"+info.getPhysicName());
			holder.code.setVisibility(View.GONE);
			if(info.getStatus().equals("0")){
				holder.flag.setText("未盘点");
				holder.flag.setTextColor(android.graphics.Color.rgb(0x66, 0xB4, 0x48));
				holder.upload.setVisibility(View.GONE);
			}else if(info.getStatus().equals("1")){
				holder.flag.setText("盘点中");
				holder.flag.setTextColor(android.graphics.Color.rgb(0xE5, 0xB3, 0x5E));
				holder.upload.setVisibility(View.GONE);
			}else if(info.getStatus().equals("2")){
				holder.flag.setText("盘点完毕");
				holder.flag.setTextColor(android.graphics.Color.rgb(0x10, 0x61, 0xAB));
			}else if(info.getStatus().equals("3")){
				holder.flag.setText("上传中");
				holder.flag.setTextColor(android.graphics.Color.rgb(0xF4, 0x33, 0x35));
			}
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