package www.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	/**
	 * ��ȡ��ǰϵͳ����ʱ��
	 * @return
	 */
	public static String getDateTime(){
		SimpleDateFormat showTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//Ҫ��ʾ��ʱ���ʽ
		String showTime = showTimeFormat.format(new Date());
		return showTime;	
	}
	/**
	 * ��ȡ��ǰϵͳ����
	 * @return
	 */
	public static String getDate(){
		SimpleDateFormat showTimeFormat = new SimpleDateFormat("yyyy/MM/dd");//Ҫ��ʾ��ʱ���ʽ
		String showTime = showTimeFormat.format(new Date());
		return showTime;	
	}
	/**
	 * ��ȡ��ǰϵͳʱ��
	 * @return
	 */
	public static String getTime(){
		SimpleDateFormat showTimeFormat = new SimpleDateFormat("HH:mm:ss");//Ҫ��ʾ��ʱ���ʽ
		String showTime = showTimeFormat.format(new Date());
		return showTime;	
	}
}
