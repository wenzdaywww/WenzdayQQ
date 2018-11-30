package www.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	/**
	 * 获取当前系统日期时间
	 * @return
	 */
	public static String getDateTime(){
		SimpleDateFormat showTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//要显示的时间格式
		String showTime = showTimeFormat.format(new Date());
		return showTime;	
	}
	/**
	 * 获取当前系统日期
	 * @return
	 */
	public static String getDate(){
		SimpleDateFormat showTimeFormat = new SimpleDateFormat("yyyy/MM/dd");//要显示的时间格式
		String showTime = showTimeFormat.format(new Date());
		return showTime;	
	}
	/**
	 * 获取当前系统时间
	 * @return
	 */
	public static String getTime(){
		SimpleDateFormat showTimeFormat = new SimpleDateFormat("HH:mm:ss");//要显示的时间格式
		String showTime = showTimeFormat.format(new Date());
		return showTime;	
	}
}
