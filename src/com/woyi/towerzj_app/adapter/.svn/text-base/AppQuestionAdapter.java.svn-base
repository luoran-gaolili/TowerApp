package com.woyi.towerzj_app.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.woyi.towerzj_app.activity.R;
import com.woyi.towerzj_app.bean.quedtion.AppProblemBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.Reflex;

/**
 * 
* 此类描述的是：问题
* @author: 罗然
* @version: 2015-7-20 上午10:31:28
* @ClassName: AppQuestionAdapter 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.adapter
 */
public class AppQuestionAdapter extends BaseAdapter{

	public class ViewHolder {
		public TextView name,xh;
	}

	private Context context;
	private List<AppProblemBean> list;
	private LayoutInflater mInflater;

	public AppQuestionAdapter(Context context, List<AppProblemBean> list) {
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
			convertView = mInflater.inflate(R.xml.item_question, null);
			holder = new ViewHolder();
			Reflex.loadViewForAdapterGetView(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		AppProblemBean info = list.get(position);
		if (info != null) {
			int str=position+1;
			holder.xh.setText(str+"、");
			holder.xh.setTextColor(android.graphics.Color.rgb(0xff, 0x00, 0x00));
			holder.name.setText(info.getName());
		}
		return convertView;
	}
}