package com.woyi.towerzj_app.activity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.woyi.towerzj_app.activity.kongtiao.NewKtDetailActivity;
import com.woyi.towerzj_app.activity.tianxian.TxDetailActivity;
import com.woyi.towerzj_app.bean.RoomItemBean;
import com.woyi.towerzj_app.bean.RoomPicBean;
import com.woyi.towerzj_app.bean.quedtion.AppProblemPicBean;
import com.woyi.towerzj_app.bean.zcqc.AppPhyInfoBean;
import com.woyi.towerzj_app.util.ApplicationData;
import com.woyi.towerzj_app.util.Bimp;
import com.woyi.towerzj_app.util.DatabaseHelper;
import com.woyi.towerzj_app.util.ForwardActivity;
import com.woyi.towerzj_app.util.MD5Check;
import com.woyi.towerzj_app.util.PhotoFileUtils;
import com.woyi.towerzj_app.util.Reflex;
import com.woyi.towerzj_app.util.SysExitUtil;
import com.woyi.towerzj_app.util.TowerSQliteDbBean;

/**
 * 
 * 此类描述的是：在flag为1的塔桅与天线之间新增一个界面，选择：划定区域是否满足需求
 * 
 * @author: 罗然
 * @version: 2015-8-14 下午5:42:52
 * @ClassName: RoomPhotoActivity
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.activity
 */
public class RoomPhotoActivity extends ForwardActivity {
	private Button back;
	private TextView title, save, next, code, address, add, del_tv;
	private GridAdapter adapter;
	private GridView scrollgridview;
	private float dp;
	private ImageAdapter imageAdapter;
	private ImageLoader imageLoader;
	private List<RoomItemBean> itemList;
	private RoomPicBean picBean;
	public List<Bitmap> bmp = new ArrayList<Bitmap>();
	public List<String> drr = new ArrayList<String>();
	private Uri photoUri;
	private String imgPath;
	private AppPhyInfoBean bean;
	private static final int TAKE_PICTURE = 0;
	private DisplayImageOptions options;
	private final int minWidth = 400;
	private int flags = 0;
	private DatabaseHelper mOpenHelper;
	private RadioGroup radioGroup;
	private RadioButton radioButton1, radioButton2;

	// 显示图片
	private GridView gridView;

	private DelPopupWindow delWindow;
	
	//图片表调整
	private List<AppProblemPicBean> proList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Reflex.loadViewForActivityOnCreate(this, R.layout.activity_photodetail);
		SysExitUtil.activityList.add(this);
		mOpenHelper = new DatabaseHelper(RoomPhotoActivity.this);
		bean = (AppPhyInfoBean) getIntent().getExtras().getSerializable("bean");
		flags = Integer
				.parseInt(this.getIntent().getExtras().getString("flag"));
		title.setText("机房照片");
		save.setText("保存");
		next.setText("跳过");
		add.setVisibility(View.GONE);
		code.setText(bean.getPhysicAlias());
		address.setText(bean.getPhysicName());
		imageLoader = ImageLoader.getInstance();
		ApplicationData.initImageLoader(this);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();
		dp = getResources().getDimension(R.dimen.dp);
		loadPic();
		if (bean.getStatus().equals("3") || bean.getStatus().equals("4")) {
			save.setVisibility(View.GONE);
		} else {// 修改问题
			save.setVisibility(View.VISIBLE);
			dp = getResources().getDimension(R.dimen.dp);
			scrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
			gridviewInit();
		}
		back.setOnClickListener(new MyClickListener());
		save.setOnClickListener(new MyClickListener());
		next.setOnClickListener(new MyClickListener());
		gridView.setOnItemClickListener(new MyListener());
	}

	private class MyListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			picBean = (RoomPicBean) parent.getItemAtPosition(position);
			delWindow = new DelPopupWindow(RoomPhotoActivity.this, null);
			delWindow.showAtLocation(parent, Gravity.BOTTOM
					| Gravity.CENTER_HORIZONTAL, 0, 0);

		}
	}

	/**
	 * 
	 * 此方法描述的是：加载本地图片
	 * 
	 * @Title: loadPic
	 * @author: 罗然
	 * @return void 返回类型
	 * @version: 2015-7-22 上午10:31:19
	 */
	private void loadPic() {
		proList = new ArrayList<AppProblemPicBean>();
		String sql = "select * from t_problem_pic where physicCode='"
				+ bean.getPhysicCode() + "' and type='" + ApplicationData.PROBLEM_TYPE_ROOMPIC + "' and code='"+ApplicationData.PROBLEM_CODE_ROOMPIC+"' ";
		try {
			proList = TowerSQliteDbBean.queryProblemPicData(mOpenHelper, sql);
			sql = "select * from tb_room_item where physicCode='"
					+ bean.getPhysicCode() + "'";
			itemList = TowerSQliteDbBean.queryRoomItemData(mOpenHelper, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}
	}

	/**
	 * 按钮监听事件
	 * 
	 * @author Administrator
	 * 
	 */
	class MyClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.back:// 返回
				finish();
				break;
			case R.id.save:// 保存
				saveData();
				break;
			case R.id.next:// 跳过
				Bundle bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags + "");
				if (flags == 1) {
					forwardIntent(getApplicationContext(),
							TxDetailActivity.class, bundle);
				} else {
					forwardIntent(getApplicationContext(),
							NewKtDetailActivity.class, bundle);
				}

				break;
			case R.id.query2:
				delWindow.dismiss();
				break;
			case R.id.del:// 删除本地图片信息
				delWindow.dismiss();
				try {
					SQLiteDatabase db = mOpenHelper.getReadableDatabase();
					TowerSQliteDbBean.deleData(db, null, picBean.getPicAddr(),null,
							"09");
					handler.obtainMessage(3).sendToTarget();
				} catch (Exception e) {
					handler.obtainMessage(-1).sendToTarget();
				}
				break;
			}

		}

	}

	// 保存图片信息
	public void saveData() {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		db.beginTransaction();
		RoomItemBean roomItemBean = new RoomItemBean();
		roomItemBean.setIsok(radioButton1.isChecked() ? "1" : "2");
		roomItemBean.setPhysicCode(bean.getPhysicCode());
		TowerSQliteDbBean.insertRoomItem(db, roomItemBean);
		try {
			if (drr != null && drr.size() > 0) {
				MD5Check md5Check = new MD5Check();
				int i=0;
				for (String o : drr) {
					i++;
					AppProblemPicBean databean = new AppProblemPicBean();
					UUID uuid = UUID.randomUUID();
					databean.setId(uuid.toString());
					databean.setType(ApplicationData.PROBLEM_TYPE_ROOMPIC);
					databean.setCode(ApplicationData.PROBLEM_CODE_ROOMPIC);
					databean.setPhysicCode(bean.getPhysicCode());
					databean.setPicSeq(i);
					File f = new File(o);
					String filename = f.getName();
					databean.setPicAddr(filename);
					databean.setCheckCode(md5Check.getFileMD5String(f));
					databean.setCreateUserId(ApplicationData.user.getId() + "");
					databean.setCheckUserId(ApplicationData.user.getId() + "");
					TowerSQliteDbBean.insertProblemPic(db, databean);
				}

			}
			db.setTransactionSuccessful();
			handler.obtainMessage(2).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		} finally {
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
			switch (msg.what) {
			case 1:
				if (null != proList && proList.size() > 0) {
					imageAdapter = new ImageAdapter(getApplicationContext(),
							proList);
					gridView.setAdapter(imageAdapter);
				} else {
					// toastMessage("本地没有图片信息！");
					imageAdapter = new ImageAdapter(getApplicationContext(),
							proList);
					gridView.setAdapter(imageAdapter);
				}
				if (itemList != null && itemList.size() > 0) {
					if (itemList.get(0).getIsok().equals("1")) {
						radioButton1.setChecked(true);
					}
				}
				break;
			case -1:
				toastMessage("请求失败！");
				finish();
				break;
			case 2:
				Bundle bundle = new Bundle();
				bundle.putSerializable("bean", bean);
				bundle.putString("flag", flags + "");
				forwardIntent(getApplicationContext(), TxDetailActivity.class,
						bundle);
				finish();
				// toastMessage("图片保存成功！");
				break;
			case -2:
				toastMessage("图片保存失败！");
				break;
			case 3:
				PhotoFileUtils.deleteDir(picBean.getPicAddr());
				loadPic();
				break;
			}
		}
	};

	/*************************************************** 图片 **************************************************/
	public void gridviewInit() {
		adapter = new GridAdapter(this);
		adapter.setSelectedPosition(0);
		int size = 0;
		if (bmp.size() < 5) {
			size = bmp.size() + 1;
		} else {
			size = bmp.size();
		}
		LayoutParams params = scrollgridview.getLayoutParams();
		final int width = size * (int) (dp * 9.4f);
		params.width = width;
		scrollgridview.setLayoutParams(params);
		scrollgridview.setColumnWidth((int) (dp * 9.4f));
		scrollgridview.setStretchMode(GridView.NO_STRETCH);
		scrollgridview.setNumColumns(size);
		scrollgridview.setAdapter(adapter);
		scrollgridview.setOnItemClickListener(gridClick);
	}

	/**
	 * 
	 * 此类描述的是：图片适配
	 */
	public class GridAdapter extends BaseAdapter {
		private LayoutInflater listContainer;
		private int selectedPosition = -1;
		private boolean shape;

		public boolean isShape() {
			return shape;
		}

		public void setShape(boolean shape) {
			this.shape = shape;
		}

		public class ViewHolder {
			public ImageView image;
			public Button bt;
		}

		public GridAdapter(Context context) {
			listContainer = LayoutInflater.from(context);
		}

		public int getCount() {
			if (bmp.size() < 5) {
				return bmp.size() + 1;
			} else {
				return bmp.size();
			}
		}

		public Object getItem(int arg0) {

			return null;
		}

		public long getItemId(int arg0) {

			return 0;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		public int getSelectedPosition() {
			return selectedPosition;
		}

		/**
		 * ListView Item设置
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			final int sign = position;
			// 自定义视图
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				// 获取list_item布局文件的视图
				convertView = listContainer.inflate(
						R.layout.item_published_grida, null);
				// 获取控件对象
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_grida_image);
				holder.bt = (Button) convertView
						.findViewById(R.id.item_grida_bt);
				// 设置控件集到convertView
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (position == bmp.size()) {
				holder.image.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.drawable.icon_addpic_unfocused));
				holder.bt.setVisibility(View.GONE);
				if (position == 4) {
					holder.image.setVisibility(View.GONE);
				}
			} else {
				holder.image.setImageBitmap(bmp.get(position));
				holder.bt.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						PhotoActivity.bitmap.remove(sign);
						bmp.get(sign).recycle();
						bmp.remove(sign);
						drr.remove(sign);
						gridviewInit();
					}
				});
			}
			return convertView;
		}
	}

	/**
	 * 图片点击事件
	 */
	private GridView.OnItemClickListener gridClick = new GridView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(RoomPhotoActivity.this
							.getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
			if (arg2 == bmp.size()) {
				String sdcardState = Environment.getExternalStorageState();
				if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
					photo();
				} else {
					Toast.makeText(getApplicationContext(), "sdcard已拔出，不能选择照片",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Intent intent = new Intent(RoomPhotoActivity.this,
						PhotoActivity.class);
				intent.putExtra("ID", arg2);
				startActivity(intent);
			}
		}
	};

	public void photo() {
		try {
			Intent openCameraIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			String sdcardState = Environment.getExternalStorageState();
			String sdcardPathDir = android.os.Environment
					.getExternalStorageDirectory().getPath() + "/tower/";
			File file = null;
			if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
				// 有sd卡，是否有myImage文件夹
				File fileDir = new File(sdcardPathDir);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				String address = bean.getPhysicCode() + "_"+ApplicationData.PROBLEM_TYPE_ROOMPIC+ApplicationData.PROBLEM_CODE_ROOMPIC+System.currentTimeMillis();
				// 是否有headImg文件
				imgPath = sdcardPathDir + address + ".JPEG";
				file = new File(imgPath);
			}
			if (file != null) {
				photoUri = Uri.fromFile(file);
				openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
				startActivityForResult(openCameraIntent, TAKE_PICTURE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TAKE_PICTURE) {
				// Bitmap bitmap = Bimp.getLoacalBitmap(drr.get(drr.size() -
				// 1));
				Bitmap bitmap=compressBySize(imgPath, 480, 600);
//				Bitmap bitmap = revitionImageSizeFromFile(new File(imgPath));
				if(bitmap!=null){
					drr.add(imgPath);
					try {
						saveFile(bitmap, imgPath);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					PhotoActivity.bitmap.add(bitmap);
					bitmap = Bimp.createFramedPhoto(480, 480, bitmap,
							(int) (dp * 1.6f));
					bmp.add(bitmap);
					gridviewInit();
				}
				// startPhotoZoom(photoUri);
		}
	}

	public class ImageAdapter extends BaseAdapter {

		List<AppProblemPicBean> mList;
		LayoutInflater mInflater;
		Context mContext;

		public ImageAdapter(Context context, List<AppProblemPicBean> imageList) {
			mContext = context;
			mInflater = LayoutInflater.from(mContext);
			this.mList = imageList;
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(R.xml.item_question_list, null);
			}
			final ImageView itemImage = (ImageView) convertView
					.findViewById(R.id.itemImage);
			// final TextView itemText = (TextView) convertView
			// .findViewById(R.id.itemText);
			final AppProblemPicBean entity = (AppProblemPicBean) getItem(position);
			// itemText.setText(entity.getPicAddr());
			String path = "file:///mnt/sdcard/tower/" + entity.getPicAddr();
			imageLoader.displayImage(path, itemImage, options,
					new SimpleImageLoadingListener() {

						public void onLoadingComplete(Bitmap loadedImage) {
							Animation anim = AnimationUtils.loadAnimation(
									RoomPhotoActivity.this, R.anim.fade_in);
							itemImage.setAnimation(anim);
							anim.start();
						}
					});
			return convertView;
		}
	}
	
	//压缩图片尺寸 
		public Bitmap compressBySize(String pathName, int targetWidth,   
				int targetHeight) {   
			BitmapFactory.Options opts = new BitmapFactory.Options();   
			opts.inJustDecodeBounds = true;// 不去真的解析图片，只是获取图片的头部信息，包含宽高等；   
			Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts); 
			// 得到图片的宽度、高度；   
			float imgWidth = opts.outWidth;   
			float imgHeight = opts.outHeight;   
			// 分别计算图片宽度、高度与目标宽度、高度的比例；取大于等于该比例的最小整数；   
			int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);   
			int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);  
			opts.inSampleSize = 1; 
			if (widthRatio > 1 || widthRatio > 1) {   
				if (widthRatio > heightRatio) {   
					opts.inSampleSize = widthRatio;   
				} else {   
					opts.inSampleSize = heightRatio;   
				}   
			}   
			//设置好缩放比例后，加载图片进内容；   
			opts.inJustDecodeBounds = false;   
			bitmap = BitmapFactory.decodeFile(pathName, opts);   
			return bitmap;   
		}
		
		public void saveFile(Bitmap bm, String fileName) throws Exception { 
			File dirFile = new File(fileName);   
			//检测图片是否存在 
			if(dirFile.exists()){   
			dirFile.delete();  //删除原图片 
			}   
			File myCaptureFile = new File(fileName);   
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));   
			//100表示不进行压缩，70表示压缩率为30% 
			bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);   
			bos.flush();   
			bos.close();   
			}

	/**
	 * 压缩提取bitmap
	 * 
	 * @param bitmap
	 * @param is
	 */
	private Bitmap revitionImageSizeFromFile(File file) {
		Bitmap temp = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;// 读取结构信息
			BitmapFactory.decodeStream(is, null, options);
			is.close();
			int width = options.outWidth;
			int zoom = (width <= minWidth ? 1 : (int) (1.0 * width / minWidth));// 缩放
			options.inSampleSize = zoom;
			options.inJustDecodeBounds = false;// 还原原始状态 读取全部信息
			is = new FileInputStream(file);
			temp = BitmapFactory.decodeStream(is, null, options);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return temp;
	}

	/**
	 * 
	 * @author Administrator
	 * 
	 */
	class DelPopupWindow extends PopupWindow {

		private Button del, query2;
		private View mMenuView;

		public DelPopupWindow(Activity context, OnClickListener itemsOnClick) {
			super(context);
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mMenuView = inflater.inflate(R.layout.dialog_del, null);
			del_tv = (TextView) mMenuView.findViewById(R.id.del_tv);
			del_tv.setText("将删除你所选择的图片信息");
			del = (Button) mMenuView.findViewById(R.id.del);
			query2 = (Button) mMenuView.findViewById(R.id.query2);
			query2.setText("取消");
			del.setOnClickListener(new MyClickListener());
			query2.setOnClickListener(new MyClickListener());
			this.setContentView(mMenuView);
			this.setWidth(LayoutParams.FILL_PARENT);
			this.setHeight(LayoutParams.WRAP_CONTENT);
			this.setFocusable(true);
			this.setAnimationStyle(R.style.AnimBottom);
			ColorDrawable dw = new ColorDrawable(0xb0000000);
			this.setBackgroundDrawable(dw);
			mMenuView.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int height = mMenuView.findViewById(R.id.pop_layout)
							.getTop();
					int y = (int) event.getY();
					if (event.getAction() == MotionEvent.ACTION_UP) {
						if (y < height) {
							dismiss();
						}
					}
					return true;
				}
			});
		}
	}

	@Override
    public void onConfigurationChanged(Configuration newConfig) {

//      其实这里什么都不要做
    super.onConfigurationChanged(newConfig);
    }
}
