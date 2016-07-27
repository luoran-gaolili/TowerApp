package com.woyi.towerzj_app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.MapApplication;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;

public class MapActivity extends ForwardActivity {
	private Button back;
	private TextView title, add,next,save,lattv,lontv;
	private String lat = "";
	private String lon = "";
	private String bdlon="";
	private String bdlat="";
	private BaiduMap mBaiduMap;
	private MapView mMapView = null; // 地图View
	private SDKReceiver mReceiver;
//	private MKSearch mSearch = null; // 搜索模块，也可去掉地图模块独立使用
	private BitmapDescriptor bd;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_map);
		SysExitUtil.activityList.add(this);
		lat = getIntent().getExtras().getString("lat");
		lon = getIntent().getExtras().getString("lon");
		next.setText("确定");
		add.setText("平面图");
		save.setText("卫星图");
		add.setVisibility(View.INVISIBLE);
		save.setVisibility(View.INVISIBLE);
		back.setOnClickListener(new MyListener());
		next.setOnClickListener(new MyListener());
		save.setOnClickListener(new MyListener());
		add.setOnClickListener(new MyListener());
		title.setText("地图展示");
		LatLng sourceLatLng=new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
		CoordinateConverter converter  = new CoordinateConverter();  
		converter.from(CoordType.GPS);  
		// sourceLatLng待转换坐标  
		converter.coord(sourceLatLng);  
		LatLng desLatLng = converter.convert();
		bdlon=desLatLng.longitude+"";
		bdlat=desLatLng.latitude+"";
		lontv.setText("经度："+bdlon);
		lattv.setText("纬度："+bdlat);
		initMap();
	}

	/**
	 * 监听事件
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.back:
				finish();
				break;
			case R.id.next:
				if(bdlon!=null && !bdlon.equals("")){
					Bundle bundle = new Bundle();
					bundle.putString("bdlon", bdlon);
					bundle.putString("bdlat", bdlat);
					Intent it = new Intent();
					it.putExtras(bundle);
					setResult(PhysicalActivity.SUCCESS, it);
				}
				finish();
				break;
//			case R.id.add://平面图
//				initPmMap(); 
//				break;
//			case R.id.save://卫星图
//				initMap(); 
//				break;
			}
		}

	}

	private void initMap() {
		// 注册 SDK 广播监听者
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new SDKReceiver();
		this.registerReceiver(mReceiver, iFilter);

		mBaiduMap = mMapView.getMap();
		//设定中心点坐标 

//        LatLng cenpt = new LatLng(30.10711,118.875427); 
        LatLng cenpt = new LatLng(Double.parseDouble(bdlat),Double.parseDouble(bdlon)); 
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
        .target(cenpt)
        .zoom(18)
        .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);

//		//普通地图    
//		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); 
		
		//设置显示为卫星地图：
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);       
		
		mBaiduMap.setOnMapStatusChangeListener( new BaiduMap.OnMapStatusChangeListener() {
            /**
             * 手势操作地图，设置地图状态等操作导致地图状态开始改变。
             * @param status 地图状态改变开始时的地图状态
             */
            public void onMapStatusChangeStart(MapStatus status){
            }
            /**
             * 地图状态变化中
             * @param status 当前地图状态
             */
            public void onMapStatusChange(MapStatus status){
            }
            /**
             * 地图状态改变结束
             * @param status 地图状态改变结束后的地图状态
             */
            public void onMapStatusChangeFinish(MapStatus status){
                LatLng ll=status.target;
                Double my_lat=ll.latitude;
                Double my_log=ll.longitude;
                bdlat=my_lat+"";
                bdlon=my_log+"";
                lattv.setText(bdlat);
                lontv.setText(bdlon);
            }
        });
	}
	
	private void initPmMap() {
		// 注册 SDK 广播监听者
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new SDKReceiver();
		this.registerReceiver(mReceiver, iFilter);

		mBaiduMap = mMapView.getMap();
		//设定中心点坐标 

//        LatLng cenpt = new LatLng(30.10711,118.875427); 
        LatLng cenpt = new LatLng(Double.parseDouble(lat),Double.parseDouble(lon)); 
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
        .target(cenpt)
        .zoom(18)
        .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);

//		//普通地图    
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); 
		
		//设置显示为卫星地图：
//		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);       
		
		mBaiduMap.setOnMapStatusChangeListener( new BaiduMap.OnMapStatusChangeListener() {
            /**
             * 手势操作地图，设置地图状态等操作导致地图状态开始改变。
             * @param status 地图状态改变开始时的地图状态
             */
            public void onMapStatusChangeStart(MapStatus status){
            }
            /**
             * 地图状态变化中
             * @param status 当前地图状态
             */
            public void onMapStatusChange(MapStatus status){
            }
            /**
             * 地图状态改变结束
             * @param status 地图状态改变结束后的地图状态
             */
            public void onMapStatusChangeFinish(MapStatus status){
                LatLng ll=status.target;
                Double my_lat=ll.latitude;
                Double my_lng=ll.longitude;
                lattv.setText(my_lat+"");
                lontv.setText(my_lng+"");
            }
        });
	}

	@Override
	public void onPause() {
		// MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		mMapView.onPause();
		super.onPause();
	}

	@Override
	public void onResume() {
		// MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
		mMapView.onResume();
		super.onResume();
	}

	@Override
	public void onDestroy() {
		// MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
		mBaiduMap.clear();
		mMapView.onDestroy();
		super.onDestroy();
		// 取消监听 SDK 广播
		super.unregisterReceiver(mReceiver);
	}
	
	/**
	 * 监听 SDK key 验证以及网络异常广播
	 */
	public class SDKReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent)

		{
			String s = intent.getAction();
			if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
				Toast.makeText(
						MapApplication.getInstance().getApplicationContext(),
						"key验证出错", Toast.LENGTH_LONG).show();
			} else if (s
					.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
				Toast.makeText(
						MapApplication.getInstance().getApplicationContext(),
						"网络出错", Toast.LENGTH_LONG).show();
			}
		}
	}
}
