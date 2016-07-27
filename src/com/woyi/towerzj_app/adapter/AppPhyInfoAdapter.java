package com.woyi.towerzj_app.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
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
public class AppPhyInfoAdapter extends BaseAdapter{

	public class PhyInfoViewHolder {
		public TextView code;
		public TextView tv1,tv2,tv3,codename1,codename2,codename3,codeaddress1,codeaddress2,codeaddress3;
		private RelativeLayout relative1,relative2,relative3;
		private LinearLayout linear1;
	}

	private Context context;
	private List<AppPhyInfoBean> list;
	private LayoutInflater mInflater;

	public AppPhyInfoAdapter(Context context, List<AppPhyInfoBean> list) {
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
		final PhyInfoViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.xml.item_phyinfo, null);
			holder = new PhyInfoViewHolder();
			Reflex.loadViewForAdapterGetView(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (PhyInfoViewHolder) convertView.getTag();
		}
		AppPhyInfoBean info = list.get(position);
		if (info != null) {
			String address="";
			if(info.getPhysicAddr()!=null &&!info.getPhysicAddr().equals("")){
				address=info.getPhysicAddr();
			}else{
				address="无";
			}
			if(info.getPhysicName()!=null && !info.getPhysicName().equals("")){
				holder.code.setText(info.getPhysicAlias()+" - "+info.getPhysicName());//+"\n"+address
			}else{
				holder.code.setText(info.getPhysicAlias());
			}
			List<AppPhyInfoBean> itemlist=info.getBeans();
			if(itemlist!=null && itemlist.size()>0){
				holder.linear1.setVisibility(View.VISIBLE);
				for(int i=0;i<itemlist.size();i++){
					if(i==0){
						holder.tv1.setVisibility(View.VISIBLE);
						holder.relative1.setVisibility(View.VISIBLE);
						holder.codename1.setText(itemlist.get(i).getPhysicAlias());
						if(itemlist.get(i).getPhysicAddr()!=null &&!itemlist.get(i).getPhysicAddr().equals("")){
							address=itemlist.get(i).getPhysicAddr();
						}else{
							address="无";
						}
						holder.codeaddress1.setText(address);
					}
					if(i==1){
						holder.tv2.setVisibility(View.VISIBLE);
						holder.relative2.setVisibility(View.VISIBLE);
						holder.codename2.setText(itemlist.get(i).getPhysicAlias());
						if(itemlist.get(i).getPhysicAddr()!=null &&!itemlist.get(i).getPhysicAddr().equals("")){
							address=itemlist.get(i).getPhysicAddr();
						}else{
							address="无";
						}
						holder.codeaddress2.setText(address);
					}
					if(i==2){
						holder.tv3.setVisibility(View.VISIBLE);
						holder.relative3.setVisibility(View.VISIBLE);
						holder.codename3.setText(itemlist.get(i).getPhysicAlias());
						if(itemlist.get(i).getPhysicAddr()!=null &&!itemlist.get(i).getPhysicAddr().equals("")){
							address=itemlist.get(i).getPhysicAddr();
						}else{
							address="无";
						}
						holder.codeaddress3.setText(address);
					}
				}
			}else{
				holder.linear1.setVisibility(View.GONE);
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