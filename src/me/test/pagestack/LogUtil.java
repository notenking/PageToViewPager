package me.test.pagestack;

import android.util.Log;

public class LogUtil {

	public static void Log(String log){
		if(log!=null&&log.length()>2){
			Log.e("aa",log);
		}else{
			Log.e("null","log is null");
		}
	}
}
