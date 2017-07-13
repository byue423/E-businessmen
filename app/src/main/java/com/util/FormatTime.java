/************************* 版权声明 *********************************
 * 
 * @版权所有：太原优联科技有限公司
 * 
 ************************* 项目信息 *********************************
 *
 * @所属项目：wxpt_android
 *
 ************************* 变更记录 *********************************
 *
 * @创建者：wjj   创建日期：2014年12月2日 上午8:58:57
 * @创建记录：创建类结构。
 * 
 * @修改者：       修改日期：
 * @修改记录：
 *
 ************************* To  Do *********************************
 *
 * @
 * 
 ************************* 随   笔 *********************************
 *
 * @
 * 
 ******************************************************************
 */
package com.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@SuppressLint("SimpleDateFormat")
public class FormatTime {

	/**
	 * 
	 * @throws ParseException
	 * @功 能：日期转换为long型时间:日期格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @创建者：wjj 创建日期： 2014年12月2日 上午9:39:24
	 * @return：
	 */
	public static long getLongTime(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		long millionSeconds = 0l;
		try {
			millionSeconds = format.parse(date).getTime();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}// 毫秒
		return millionSeconds;
	}

	/**
	 * 
	 * @功 能：获取给定时间和当前时间的时间差: 日期格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @创建者：wjj 创建日期： 2014年12月3日 上午9:24:22
	 * @return：
	 */
	public static long getBetweenTime(String date) {
		//Log.d("FormatTime", "-----------" + date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date curDate = new Date(); // 当前日期
		Date myDate = null;// 我的日期
		long diffInSec = 0l;
		try {
			myDate = (Date) format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (myDate != null) {
			long curTime = curDate.getTime();
			long myTime = myDate.getTime();
			// if (curTime >= myTime) {
			// diffInSec = curTime - myTime;
			// } else {
			diffInSec = myTime - curTime;
			// }
		}
		return diffInSec;
		// 转换为分钟数
		// return TimeUnit.MILLISECONDS.toSeconds(diffInSec);
	}

	/**
	 * 
	 * @功 能：获取俩个日期的时间差: 日期格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @创建者：wjj 创建日期： 2014年12月3日 上午9:24:22
	 * @return：
	 */
	public static long getBetweenTime(String startDate, String overDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date sDate = null; // 开始日期
		Date oDate = null;// 结束日期
		long diffInSec = 0l;
		try {
			sDate = (Date) format.parse(startDate);
			oDate = (Date) format.parse(overDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (sDate != null && oDate != null) {
			long sTime = sDate.getTime();
			long oTime = oDate.getTime();
			// if (sTime >= oTime) {
			diffInSec = oTime - sTime;
			// } else {
			// diffInSec = oTime - sTime;
			// }
		}
		return diffInSec;
		// 转换为分钟数
		// return TimeUnit.MILLISECONDS.toSeconds(diffInSec);
	}

	/**
	 * 
	 * @功 能：long型时间转换为日期，日期格式为HH:mm:ss
	 * 
	 * @创建者：wjj 创建日期： 2014年12月2日 上午9:39:32
	 * @return：
	 */
	public static String getTimeDate(long value) {
		// Date date = new Date(value);
		// GregorianCalendar gc = new GregorianCalendar();
		// gc.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String time = format.format(new Date(value));
		return time;
	}

	/**
	 * 
	 * @功 能：获取时间的时分秒
	 * 
	 * @创建者：wjj 创建日期： 2015年1月27日 下午7:57:00
	 * @return：
	 */
	public static String getDivDate(long value) {
		String hStr = "";
		String mStr = "";
		String sStr = "";
		long seconds = TimeUnit.MILLISECONDS.toSeconds(value);
		int hours = (int) (seconds / 3600);
		long xx = seconds % 3600;
		int mm = (int) (xx / 60);
		int ss = (int) (xx % 60);
		if (hours < 10) {
			hStr = "0" + hours;
		} else {
			hStr = "" + hours;
			hStr.trim();
		}
		if (mm < 10) {
			mStr = "0" + mm;
		} else {
			mStr = "" + mm;
			mStr.trim();
		}
		if (ss < 10) {
			sStr = "0" + ss;
		} else {
			sStr = "" + ss;
			sStr.trim();
		}
		return hStr + ":" + mStr + ":" + sStr;
	}

	/**
	 * 
	 * @功 能：获取时间的分钟数和秒数
	 * 
	 * @创建者：wjj 创建日期： 2015年1月27日 下午7:58:09
	 * @return：
	 */
	public static String getMMAndSS(long value) {
		String mStr = "";
		String sStr = "";
		long seconds = TimeUnit.MILLISECONDS.toSeconds(value);
		int mm = (int) (seconds / 60);
		int ss = (int) (seconds % 60);
		mStr = "" + mm;
		mStr.trim();
		if (ss < 10) {
			sStr = "0" + ss;
		} else {
			sStr = "" + ss;
			sStr.trim();
		}
		return mStr + ":" + sStr;
	}

	/**
	 * 
	 * @功 能：long型时间转换为日期，日期格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @创建者：wjj 创建日期： 2014年12月3日 上午9:45:27
	 * @return：
	 */
	public static String getFormatDate(long value) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String time = format.format(new Date(value));
		return time;
	}
	
	/**
	 * 
	 * @功 能：long型时间转换为日期，日期格式为yyyy-MM-dd
	 * 
	 * @创建者：wjj 创建日期： 2014年12月3日 上午9:45:27
	 * @return：
	 */
	public static String getFormatDateYMD(long value) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String time = format.format(new Date(value));
		return time;
	}
}
