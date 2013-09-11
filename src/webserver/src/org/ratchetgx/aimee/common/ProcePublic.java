package org.ratchetgx.aimee.common;


import java.text.SimpleDateFormat;
import java.util.Map;


public class ProcePublic {
	//日期格式化
	public static SimpleDateFormat format_real = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat format_minute = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat format_hour = new SimpleDateFormat("yyyy-MM-dd HH");
	public static SimpleDateFormat format_day = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat format_month = new SimpleDateFormat("yyyy-MM");
	public static SimpleDateFormat format_year = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat format_SSS = new SimpleDateFormat("yyyyMMddHHmmss_SSS");
	public static SimpleDateFormat formatSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public static SimpleDateFormat formatsort = new SimpleDateFormat("mmssSSS");
	public static String hexString ;

	public static Map<String,Object> userinfoMap;
}
		

