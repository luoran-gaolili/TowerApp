package com.woyi.towerzj_app.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.woyi.towerzj_app.activity.R;

/**
 * ͨ�������activity�е�view��ͼ����
 */
public class Reflex {
	public static Map<String, Integer> idMap = new HashMap<String, Integer>();
	public final static String logName = "Reflex";
	public final static Class view = View.class;
	static {
		Class idClazz = R.id.class;
		Field[] ids = idClazz.getDeclaredFields();
		Field.setAccessible(ids, true);
		for (Field id : ids) {
			try {
				idMap.put(id.getName().toLowerCase(), id.getInt(R.id.class));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void loadViewForActivityOnCreate(Activity activity, int xml) {
		activity.setContentView(xml);
		Class c = activity.getClass();
		Field[] f = c.getDeclaredFields();
		Field.setAccessible(f, true);
		int i = 0;
		for (Field f1 : f) {
			try {
				if (view.isAssignableFrom(f1.getType())) {
					// Log.d("pro", f1.getName()+"��view����");
					if (idMap.containsKey(f1.getName().toLowerCase())) {
						f1.set(activity, activity.findViewById(idMap.get(f1
								.getName().toLowerCase())));
					} else {
						Log.d(logName, f1.getName()
								+ "����ʵ������xml�ļ����޸�id�����޸� " + c.getName()
								+ "�������" + f1.getName() + "���");
						i++;
					}
				} else {
					// Log.d("pro", f1.getName()+"����view����");
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if (i > 0) {
			// Toast.makeText(activity, "��ǰactivity����ͼ����δװ�سɹ����벶����־" + logName,
			// Toast.LENGTH_LONG).show();
		}
	}

	
	public static void loadViewForAdapterGetView(Object holder, View convertView) {
		Class c = holder.getClass();
		Field[] fields = c.getDeclaredFields();
		Field.setAccessible(fields, true);
		for (Field field : fields) {
			if (view.isAssignableFrom(field.getType())) {
				if (idMap.containsKey(field.getName().toLowerCase())) {
					try {
						field.set(holder, convertView.findViewById(idMap
								.get(field.getName().toLowerCase())));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	
	public static void loadDetail(Object activity, Object res) {
		Class resClazz = res.getClass();
		Class activityClazz = activity.getClass();
		Field[] fileds = resClazz.getDeclaredFields();
		Field.setAccessible(fileds, true);
		try {
			for (int i = 0; i < fileds.length; i++) {
				String filedName = fileds[i].getName();
				Field activityField = activityClazz.getDeclaredField(filedName);
				Field.setAccessible(new Field[] { activityField }, true);
				if (activityField != null
						&& (activityField.getType().getName()
								.equals(TextView.class.getName()) || activityField
								.getType().getName()
								.equals(EditText.class.getName()))) {
					Method resMethod = resClazz.getMethod("get"
							+ filedName.substring(0, 1).toUpperCase()
							+ filedName.substring(1));
					TextView tv = (TextView) activityField.get(activity);
					Object resFieldValue = resMethod.invoke(res);
					if (tv != null && resFieldValue != null) {
						tv.setText((String) resFieldValue);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
    /*public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        final String key = preference.getKey();
        Intent intent=new Intent();
	 ComponentName componentName = new ComponentName(
				"com.reverie.lockscreen", "com.reverie.lockscreen.LockService");
	 intent.setComponent(componentName);*/
	//System.out.println(key);
       /* if (KEY_UNLOCK_SET_OR_CHANGE.equals(key)) {
            startFragment(this, "com.android.settings.ChooseLockGeneric$ChooseLockGenericFragment",
                    R.string.lock_settings_picker_title, SET_OR_CHANGE_LOCK_METHOD_REQUEST, null);
        } else if (KEY_BIOMETRIC_WEAK_IMPROVE_MATCHING.equals(key)) {
            ChooseLockSettingsHelper helper =
                    new ChooseLockSettingsHelper(this.getActivity(), this);
            if (!helper.launchConfirmationActivity(
                    CONFIRM_EXISTING_FOR_BIOMETRIC_WEAK_IMPROVE_REQUEST, null, null)) {
                // If this returns false, it means no password confirmation is required, so
                // go ahead and start improve.
                // Note: currently a backup is required for biometric_weak so this code path
                // can't be reached, but is here in case things change in the future
                startBiometricWeakImprove();
            }
        } else if (KEY_TRUST_AGENT.equals(key)) {
            ChooseLockSettingsHelper helper =
                    new ChooseLockSettingsHelper(this.getActivity(), this);
            mTrustAgentClickIntent = preference.getIntent();
            if (!helper.launchConfirmationActivity(CHANGE_TRUST_AGENT_SETTINGS, null, null) &&
                    mTrustAgentClickIntent != null) {
                // If this returns false, it means no password confirmation is required.
                startActivity(mTrustAgentClickIntent);
                mTrustAgentClickIntent = null;
            }
        } else if(getActivity().getResources().getBoolean(R.bool.rgks_config)){

		if(KEY_UNLOCK_SET_OR_REVERIELOCK.equals(key)){
        //start lock
        if (mLockPatternUtils.isLockScreenDisabled()) {
             if(!isStart){
                   isStart=true;
                   // Settings.Secure.putInt(getContentResolver(),RGKS_REVISE_LOCK_STATE, 1);
                   getActivity().startService(intent);
                   Toast.makeText(getActivity(),R.string.reverielockstart,Toast.LENGTH_SHORT).show();
             }else{
                   isStart=false;
                   //	Settings.Secure.putInt(getContentResolver(),RGKS_REVISE_LOCK_STATE, 0);
                   getActivity().stopService(intent);
                   Toast.makeText(getActivity(),R.string.reverielockstop,Toast.LENGTH_SHORT).show();
             }
        } else {
         //startDialog
         //getActivity().stopService(intent);
		mReverse.setChecked(isStart);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final AlertDialog dialog = builder.create();
		builder.setTitle(R.string.tiptitle);
		builder.setMessage(R.string.tipcontent);
		builder.setNegativeButton(R.string.canceltext, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				dialog.dismiss();

			}
		});

		builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			
				// 
		    startFragment(SecuritySettings.this, "com.android.settings.ChooseLockGeneric$ChooseLockGenericFragment",
                    R.string.lock_settings_picker_title, SET_OR_CHANGE_LOCK_METHOD_REQUEST, null);
				

			}
		});
		builder.show();
		

			}
           
        	tent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setAction(Intent.ACTION_MAIN);
		ComponentName componentName = new ComponentName("com.reverie.lockscreen",
								"com.reverie.lockscreen.SettingsActivity");
		intent.setComponent(componentName);
		startActivity(intent);
		
		

        	}}  else{
            // If we didn't handle it, let preferences handle it.
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        return true;
    }}00000000000000000000000000000000000000000*/
/*}
}
*/