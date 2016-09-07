package com.dgsy.moblieguard.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpTools {
public static void putString(Context context,String key,String value){
	SharedPreferences sp = context.getSharedPreferences(MyConstants.SPFILE, context.MODE_PRIVATE);
	sp.edit().putString(key, value).commit();
}
public static String getString(Context context,String key,String defValue){
	SharedPreferences sp = context.getSharedPreferences(MyConstants.SPFILE, context.MODE_PRIVATE);
	return sp.getString(key, defValue);
}
}
