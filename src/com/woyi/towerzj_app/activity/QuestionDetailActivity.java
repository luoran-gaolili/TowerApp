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
import android.view.KeyEvent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.woyi.towerzj_app.activity.jifang.NewJifangDetailActivity;
import com.woyi.towerzj_app.activity.kongtiao.NewKtDetailActivity;
import com.woyi.towerzj_app.activity.tawei.NewTaweiDetailActivity;
import com.woyi.towerzj_app.bean.quedtion.AppProblemBean;
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
 * �����������ǣ�����
 * 
 * @author: ��Ȼ
 * @version: 2015-7-18 ����3:55:57
 * @ClassName: QuestionDetailActivity
 * @��Ŀ�� towerzj_app
 * @����com.woyi.towerzj_app.activity
 */
public class QuestionDetailActivity extends ForwardActivity {

	private Button back;
	private TextView title, cz, del_tv;
	private GridView scrollgridview;
	private GridAdapter adapter;

	private Animation animation;
	private ImageView loading;
	private RelativeLayout relative_layout;

	private float dp;
	public List<Bitmap> bmp = new ArrayList<Bitmap>();
	public List<String> drr = new ArrayList<String>();
	private static final int TAKE_PICTURE = 0;
	private final int minWidth = 400;
	private Uri photoUri;
	private String imgPath;

	private AppPhyInfoBean bean;
	private AppProblemBean probean;
	// private int index = 0;
	private DelPopupWindow delWindow;
	public List<String> sucessdrr = new ArrayList<String>();
	public List<String> faildrr = new ArrayList<String>();

	// ��ʾͼƬ
	private GridView gridView;

	private DisplayImageOptions options;
	private ImageAdapter imageAdapter;
	private ImageLoader imageLoader;
	private List<AppProblemPicBean> picList;
	private String flag, code;
	private DatabaseHelper mOpenHelper;
	private AppProblemPicBean picBean;
	private String protype;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOpenHelper = new DatabaseHelper(this);
		probean = (AppProblemBean) this.getIntent().getExtras()
				.getSerializable("probean");
		bean = (AppPhyInfoBean) this.getIntent().getExtras()
				.getSerializable("bean");
		flag = this.getIntent().getExtras().getString("flag");
		code = this.getIntent().getExtras().getString("code");
		protype = this.getIntent().getExtras().getString("protype");
		Reflex.loadViewForActivityOnCreate(this,
				R.layout.activity_question_detail);
		SysExitUtil.activityList.add(this);
		title.setText("����ͼƬ");
		animation = AnimationUtils.loadAnimation(this, R.anim.loading);
		relative_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		//����ImageLoader
		imageLoader = ImageLoader.getInstance();
		ApplicationData.initImageLoader(this);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();
		relative_layout.setVisibility(View.VISIBLE);
		loading.startAnimation(animation);
		loadPic();
		cz.setText("����");
		if (bean.getStatus().equals("3") || bean.getStatus().equals("4")) {
			cz.setVisibility(View.GONE);
		} else {// �޸�����
			cz.setVisibility(View.VISIBLE);
			dp = getResources().getDimension(R.dimen.dp);
			scrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
			gridviewInit();
		}
		back.setOnClickListener(btnLis);
		cz.setOnClickListener(btnLis);
		gridView.setOnItemClickListener(new MyListener());
	}

	/**
	 * 
	 * �˷����������ǣ����ر���ͼƬ
	 * 
	 * @Title: loadPic
	 * @author: ��Ȼ
	 * @return void ��������
	 * @version: 2015-7-22 ����10:31:19
	 */
	private void loadPic() {
		picList = new ArrayList<AppProblemPicBean>();
		String sql = "select * from t_problem_pic where physicCode='"
				+ bean.getPhysicCode() + "' and type='" + flag + "' and code='"
				+ probean.getCode() + "' and linkCode='" + code + "'";
		try {
			picList = TowerSQliteDbBean.queryProblemPicData(mOpenHelper, sql);
			handler.obtainMessage(1).sendToTarget();
		} catch (Exception e) {
			handler.obtainMessage(-1).sendToTarget();
		}
	}

	private class MyListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			picBean = (AppProblemPicBean) parent.getItemAtPosition(position);
			delWindow = new DelPopupWindow(QuestionDetailActivity.this, null);
			delWindow.showAtLocation(parent, Gravity.BOTTOM
					| Gravity.CENTER_HORIZONTAL, 0, 0);

		}
	}

	/**
	 * ��ť�����¼�
	 */
	private OnClickListener btnLis = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.back:
				back();
				break;
			case R.id.cz:
				if (drr == null || drr.size() == 0) {
					toastMessage("������/��ѡ����Ƭ");
					return;
				}
				saveData();
				break;
			case R.id.query2:
				delWindow.dismiss();
				break;
			case R.id.del:// ɾ������ͼƬ��Ϣ
				delWindow.dismiss();
				try {
					SQLiteDatabase db = mOpenHelper.getReadableDatabase();
					TowerSQliteDbBean.deleData(db, null, picBean.getPicAddr(),
							null, "09");
					handler.obtainMessage(3).sendToTarget();
				} catch (Exception e) {
					handler.obtainMessage(-1).sendToTarget();
				}
				break;
			}
		}
	};

	/**
	 * 
	 * �˷����������ǣ��������ݵ�����
	 * 
	 * @Title: uploadData
	 * @author: ��Ȼ
	 * @return void ��������
	 * @version: 2015-7-22 ����5:10:21
	 */
	protected void saveData() {
		relative_layout.setVisibility(View.VISIBLE);
		loading.startAnimation(animation);
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		MD5Check md5Check = new MD5Check();
		try {
			if (drr != null && drr.size() > 0) {
				db.beginTransaction();
				int i = 0;
				for (String o : drr) {
					i++;
					AppProblemPicBean databean = new AppProblemPicBean();
					UUID uuid = UUID.randomUUID();
					databean.setId(uuid.toString());
					databean.setType(probean.getType());
					databean.setCode(probean.getCode());
					databean.setPhysicCode(bean.getPhysicCode());
					databean.setPicSeq(i);
					databean.setLinkCode(code);
					File f = new File(o);
					String filename = f.getName();
					databean.setPicAddr(filename);
					databean.setCheckCode(md5Check.getFileMD5String(f));
					databean.setCreateUserId(ApplicationData.user.getId() + "");
					databean.setCheckUserId(ApplicationData.user.getId() + "");
					TowerSQliteDbBean.insertProblemPic(db, databean);
				}
				db.setTransactionSuccessful();
			}
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
	 * �̼߳������ݺ󷵻ص���Ϣ
	 */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			relative_layout.setVisibility(View.GONE);
			loading.clearAnimation();
			switch (msg.what) {
			case 1:
				if (null != picList && picList.size() > 0) {
					imageAdapter = new ImageAdapter(getApplicationContext(),
							picList);
					gridView.setAdapter(imageAdapter);
					// index = picList.size();
				} else {
					// toastMessage("����û��ͼƬ��Ϣ��");
					imageAdapter = new ImageAdapter(getApplicationContext(),
							picList);
					gridView.setAdapter(imageAdapter);
				}
				break;
			case -1:
				toastMessage("����ʧ�ܣ�");
				finish();
				break;
			case 2:
				finish();
				// toastMessage("ͼƬ����ɹ���");
				break;
			case -2:
				toastMessage("ͼƬ����ʧ�ܣ�");
				break;
			case 3:
				PhotoFileUtils.deleteDir(picBean.getPicAddr());
				loadPic();
				break;
			}
		}
	};

	/*************************************************** ͼƬ **************************************************/
	public void gridviewInit() {
		adapter = new GridAdapter(this);
		adapter.setSelectedPosition(0);
		int size = 0;
		if (bmp.size() < 4) {
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

	protected void onPause() {
		super.onPause();
	}

	public void photo() {
		try {
			Intent openCameraIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			// openCameraIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			String sdcardState = Environment.getExternalStorageState();
			String sdcardPathDir = android.os.Environment
					.getExternalStorageDirectory().getPath() + "/tower/";
			File file = null;
			if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
				// ��sd�����Ƿ���myImage�ļ���
				File fileDir = new File(sdcardPathDir);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				String address = bean.getPhysicCode() + "_" + flag
						+ probean.getCode() + System.currentTimeMillis();
				// �Ƿ���headImg�ļ�
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

	/**
	 * ͼƬ����¼�
	 */
	private GridView.OnItemClickListener gridClick = new GridView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(QuestionDetailActivity.this
							.getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
			if (arg2 == bmp.size()) {
				String sdcardState = Environment.getExternalStorageState();
				if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
					photo();
				} else {
					Toast.makeText(getApplicationContext(), "sdcard�Ѱγ�������ѡ����Ƭ",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Intent intent = new Intent(QuestionDetailActivity.this,
						PhotoActivity.class);
				intent.putExtra("ID", arg2);
				startActivity(intent);
			}
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TAKE_PICTURE) {
			if (drr.size() < 5 && resultCode == -1) {// ����
				// Bitmap bitmap = Bimp.getLoacalBitmap(drr.get(drr.size() -
				// 1));
				Bitmap bitmap = compressBySize(imgPath, 480, 600);
				// Bitmap bitmap = revitionImageSizeFromFile(new File(imgPath));
				if (bitmap != null) {
					try {
						saveFile(bitmap, imgPath);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					drr.add(imgPath);
					PhotoActivity.bitmap.add(bitmap);
					bitmap = Bimp.createFramedPhoto(480, 480, bitmap,
							(int) (dp * 1.6f));
					bmp.add(bitmap);
					gridviewInit();
				}
				// startPhotoZoom(photoUri);
			}
		}
	}

	protected void onDestroy() {
		// PhotoFileUtils.deleteDir(PhotoFileUtils.SDPATH);
		// ����ͼƬ����
		for (int i = 0; i < bmp.size(); i++) {
			bmp.get(i).recycle();
		}
		for (int i = 0; i < PhotoActivity.bitmap.size(); i++) {
			PhotoActivity.bitmap.get(i).recycle();
		}
		PhotoActivity.bitmap.clear();
		bmp.clear();
		drr.clear();
		super.onDestroy();
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
			del_tv.setText("��ɾ������ѡ���ͼƬ��Ϣ");
			del = (Button) mMenuView.findViewById(R.id.del);
			query2 = (Button) mMenuView.findViewById(R.id.query2);
			query2.setText("ȡ��");
			del.setOnClickListener(btnLis);
			query2.setOnClickListener(btnLis);
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

	/**
	 * 
	 * �����������ǣ�ͼƬ����
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
			if (bmp.size() < 4) {
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
		 * ListView Item����
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			final int sign = position;
			// �Զ�����ͼ
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				// ��ȡlist_item�����ļ�����ͼ
				convertView = listContainer.inflate(
						R.layout.item_published_grida, null);
				// ��ȡ�ؼ�����
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_grida_image);
				holder.bt = (Button) convertView
						.findViewById(R.id.item_grida_bt);
				// ���ÿؼ�����convertView
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
							Animation anim = AnimationUtils
									.loadAnimation(QuestionDetailActivity.this,
											R.anim.fade_in);
							itemImage.setAnimation(anim);
							anim.start();
						}
					});
			return convertView;
		}
	}

	// ѹ��ͼƬ�ߴ�
	public Bitmap compressBySize(String pathName, int targetWidth,
			int targetHeight) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;// ��ȥ��Ľ���ͼƬ��ֻ�ǻ�ȡͼƬ��ͷ����Ϣ��������ߵȣ�
		Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
		// �õ�ͼƬ�Ŀ�ȡ��߶ȣ�
		float imgWidth = opts.outWidth;
		float imgHeight = opts.outHeight;
		// �ֱ����ͼƬ��ȡ��߶���Ŀ���ȡ��߶ȵı�����ȡ���ڵ��ڸñ�������С������
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
		// ���ú����ű����󣬼���ͼƬ�����ݣ�
		opts.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(pathName, opts);
		return bitmap;
	}

	// ����ͼƬ
	public void saveFile(Bitmap bm, String fileName) throws Exception {
		File dirFile = new File(fileName);
		// ���ͼƬ�Ƿ����
		if (dirFile.exists()) {
			dirFile.delete(); // ɾ��ԭͼƬ
		}
		File myCaptureFile = new File(fileName);
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(myCaptureFile));
		// 100��ʾ������ѹ����70��ʾѹ����Ϊ30%
		bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
		bos.flush();
		bos.close();
	}

	/**
	 * ѹ����ȡbitmap
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
			options.inJustDecodeBounds = true;// ��ȡ�ṹ��Ϣ
			BitmapFactory.decodeStream(is, null, options);
			is.close();
			int width = options.outWidth;
			int zoom = (width <= minWidth ? 1 : (int) (1.0 * width / minWidth));// ����
			options.inSampleSize = zoom;
			options.inJustDecodeBounds = false;// ��ԭԭʼ״̬ ��ȡȫ����Ϣ
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
	 * ϵͳ���ؼ�
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			back();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void back() {
		if (protype.equals("1")) {
			finish();
		} else if (protype.equals(ApplicationData.PROBLEM_TYPE_ROOM)) {
			Intent it = new Intent();
			setResult(NewJifangDetailActivity.SUCCESS, it);
			finish();
		} else if (protype.equals(ApplicationData.PROBLEM_TYPE_AIR)) {
			Intent it = new Intent();
			setResult(NewKtDetailActivity.SUCCESS, it);
			finish();
		} else if (protype.equals(ApplicationData.PROBLEM_TYPE_MAST)) {
			Intent it = new Intent();
			setResult(NewTaweiDetailActivity.SUCCESS, it);
			finish();
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		// ��ʵ����ʲô����Ҫ��
		super.onConfigurationChanged(newConfig);
	}

}