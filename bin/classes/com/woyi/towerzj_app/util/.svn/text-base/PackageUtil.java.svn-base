package com.woyi.towerzj_app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

/**
 * 用于捆绑安装APK
 */
public class PackageUtil {
	private Context context;
	private List<String> packagNameList;// 保存已安装的应用
	private List<String> needPackagNameList;// 保存需要调用的应用
	private List<String> fileNameList;// 保存需要调用的应用

	public PackageUtil(Context context, List<String> needPackagNameList,
			List<String> fileNameList) {
		super();
		if (needPackagNameList == null || fileNameList == null
				|| (needPackagNameList.size() != fileNameList.size())) {
			Toast.makeText(context, "自动安装工具加载失败", Toast.LENGTH_SHORT).show();
			return;
		}
		this.context = context;
		this.needPackagNameList = needPackagNameList;
		this.fileNameList = fileNameList;
		initpackagNameList();
	}

	/**
	 * 捆绑安装需要以下权限
	 * 
	 * <uses-permission
	 * android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
	 * <uses-permission
	 * android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
	 */
	public void install() {
		for (int i = 0; i < fileNameList.size(); i++) {
			boolean installed = detectApk(needPackagNameList.get(i)
					.toLowerCase());
			if (!installed) {// 未安装
				final File file = new File(
						Environment.getExternalStorageDirectory(),
						fileNameList.get(i));
				retrieveApkFromAssets(context, fileNameList.get(i), file);
				androidInstall(file);
			} else {
				Toast.makeText(context, "您已安装过此软件", Toast.LENGTH_SHORT).show();
			}
		}
	}

	/**
	 * android安装器安装
	 * 
	 * @param file
	 */
	private void androidInstall(final File file) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + file.toString()),
				"application/vnd.android.package-archive");
		context.startActivity(i);
	}

	// 捆绑安装
	private boolean retrieveApkFromAssets(Context context, String fileName,
			File file) {
		boolean bRet = false;
		try {
			if (file.exists()) {
				return true;
			} else {
				file.createNewFile();
				InputStream is = context.getAssets().open(fileName);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] temp = new byte[1024];
				int i = 0;
				while ((i = is.read(temp)) != -1) {
					fos.write(temp, 0, i);
				}
				fos.flush();
				fos.close();
				is.close();
				bRet = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bRet;
	}

	/**
	 * 判断某个应用是否已经安装
	 * 
	 * @param packageName
	 * @return
	 */
	private boolean detectApk(String packageName) {
		return packagNameList.contains(packageName.toLowerCase());
	}

	/**
	 * 获得已安装的应用
	 */
	private void initpackagNameList() {
		packagNameList = new ArrayList<String>();
		PackageManager manager = context.getPackageManager();
		List<PackageInfo> pkgList = manager.getInstalledPackages(0);
		for (int i = 0; i < pkgList.size(); i++) {
			PackageInfo pI = pkgList.get(i);
			packagNameList.add(pI.packageName.toLowerCase());
		}
	}

}
