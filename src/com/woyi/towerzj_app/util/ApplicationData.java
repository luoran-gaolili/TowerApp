package com.woyi.towerzj_app.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.woyi.towerzj_app.activity.LoginActivity;
import com.woyi.towerzj_app.bean.AppUserBean;

public class ApplicationData extends Application {

	public static AppUserBean user;
	public static final String TOWER = "tower";
	private static ExecutorService executorService;// �̳߳�
	protected static String CH;
	
	/**
	 * ��������
	 */
	public static String PROBLEM_TYPE_MAST="01";	//��Φ
	public static String PROBLEM_LXCODE_MAST="28";	//��Φ
	public static String PROBLEM_TGCODE_MAST="29";	//��Φ
	public static String PROBLEM_TYPE_ROOM="02";	//����
	public static String PROBLEM_MJCODE_ROOM="16";	//����
	public static String PROBLEM_JGCODE_ROOM="15";	//����
	public static String PROBLEM_LXCODE_ROOM="14";	//����
	public static String PROBLEM_TYPE_AIR="03";		//�յ�
	public static String PROBLEM_CODE_AIR="06";
	public static String PROBLEM_TYPE_BATTERY="04";	//���
	public static String PROBLEM_TYPE_SWITCH="05";	//����
	public static String PROBLEM_TYPE_POWER="06";	//�����
	
	public static String PROBLEM_TYPE_ROOMPIC="09";	//����ͼƬ
	public static String PROBLEM_CODE_ROOMPIC="01";	//����ͼƬ
	
	public static String PROBLEM_TYPE_ROOMDBBHPIC="10";	//���ͼƬ
	public static String PROBLEM_CODE_ROOMDBBHPIC="01";	//���ͼƬ

	public static List<String> appNames1;
	public static List<String> appFiles1;
	public static Map<String,String> reMap;

	public static ExecutorService getExecutorService() {
		if (executorService == null) {
			executorService = Executors.newFixedThreadPool(5);
		}
		return executorService;
	}
	
	static {
		ApplicationData.appNames1 = new ArrayList<String>();
		ApplicationData.appNames1.add("QRCode");
		ApplicationData.appFiles1 = new ArrayList<String>();
		ApplicationData.appFiles1.add("QRCode.apk");
	}
	
	/**
	 * ��ʼ��ͼƬ����
	 * @param context
	 */
	public static void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "/tower/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .threadPoolSize(3)//�̳߳��ڼ��ص�����
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheExtraOptions(480, 320, null)
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .memoryCache(new WeakMemoryCache())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }

	public static void setMinHeapSize(long size) {
		try {
			Class<?> cls = Class.forName("dalvik.system.VMRuntime");
			Method getRuntime = cls.getMethod("getRuntime");
			Object obj = getRuntime.invoke(null);// obj����Runtime
			if (obj == null) {
				System.err.println("obj is null");
			} else {
				System.out.println(obj.getClass().getName());
				Class<?> runtimeClass = obj.getClass();
				Method setMinimumHeapSize = runtimeClass.getMethod(
						"setMinimumHeapSize", long.class);
				setMinimumHeapSize.invoke(obj, size);
				setMinimumHeapSize = runtimeClass.getMethod(
						"setTargetHeapUtilization", float.class);
				setMinimumHeapSize.invoke(obj, 0.75f);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkApkExist(Context context, String packageName) {
		if (packageName == null || "".equals(packageName))
			return false;
		try {
			ApplicationInfo info = context.getPackageManager()
					.getApplicationInfo(packageName,
							PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		Toast.makeText(getApplicationContext(), "�ֻ��ڴ�̫���ţ�����ϵͳ���޷�����",
				Toast.LENGTH_LONG);
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		for (ForwardActivity activicy : SysExitUtil.activityList) {
			activicy.finish();
		}
		System.exit(0);
	}

	@SuppressLint("NewApi")
	@Override
	public void onTrimMemory(int level) {
		// TODO Auto-generated method stub
		super.onTrimMemory(level);
		if (TRIM_MEMORY_UI_HIDDEN == level || TRIM_MEMORY_COMPLETE == level) {
			if (SysExitUtil.activityList != null) {
				for (ForwardActivity activicy : SysExitUtil.activityList) {
					if (activicy.getClass().getName()
							.equals(LoginActivity.class.getName())) {
						break;
					} else {
						activicy.finish();
					}
				}
			}
		} else {
			Toast.makeText(getApplicationContext(), "�ֻ��ڴ���ţ��챻���գ��������ڴ��",
					Toast.LENGTH_LONG);
		}
	}

	public String getCH() {
		if (CH == null) {
			checkApplicationData();
		}
		return CH;
	}

	public void setCH(String cH) {
		CH = cH;
	}

	public void checkApplicationData() {
		// if(CH==null || CH.length()==0){
		// setCH(PhoneUtil.getPhoneDeviceId(this));
		// }
		// SharedPreferences sp=getSharedPreferences("userInfo",
		// Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
		// if(user==null){
		// String result=sp.getString("USER",null);
		// if(result!=null){
		// ObjectMapper om = new ObjectMapper();
		// try {
		// setUser(om.readValue(result, AppUserBean.class));
		// ApplicationData.user = om.readValue(result,
		// TypeFactory.fromTypeReference(new TypeReference<AppUserBean>() {}));
		// }catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }
	}
}