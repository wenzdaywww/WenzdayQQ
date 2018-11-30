package www.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ^  Ϊ���ƿ�ͷ  <br/>
 * $  Ϊ���ƽ�β  <br/>
 * .  Ϊ�������Ƴ�/n��������һ�������ַ�  <br/>
 * +  Ϊ���ٳ���һ��  <br/>
 * *  �ܶ��  <br/>
 */
public class IndateUtil {
	/**
	 * �ж��Ƿ�������
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	/**
	 * �ж��Ƿ�����ĸ
	 * @param str
	 * @return
	 */
	public static boolean isString(String str) {
		Pattern pattern = Pattern.compile("^[a-zA-Z]*$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	/**
	 * �ж��Ƿ�����ĸ�������
	 * @param str
	 * @return
	 */
	public static boolean isNumStr(String str) {
		Pattern pattern = Pattern.compile("^[a-zA-Z[0-9]]*$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	/**
	 * �ж��Ƿ��Ǻ���
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		// ����
		Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5]*$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
}
