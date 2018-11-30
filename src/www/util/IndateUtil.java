package www.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ^  为限制开头  <br/>
 * $  为限制结尾  <br/>
 * .  为条件限制除/n以外任意一个单独字符  <br/>
 * +  为至少出现一次  <br/>
 * *  很多次  <br/>
 */
public class IndateUtil {
	/**
	 * 判断是否是数字
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
	 * 判断是否是字母
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
	 * 判断是否是字母或汉字组合
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
	 * 判断是否是汉字
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		// 编码
		Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5]*$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
}
