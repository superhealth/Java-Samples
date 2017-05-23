package gaodemap;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author wangym
 * @date 2016-08-01
 */
public class StringUtil {
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	/** 非法字符 */
	public static final String REG_INVALID_WORD = ".*[\\\\<>\"=\\';].*";
	// html标签
	public static final String REG_HTML_TAG = "<([^<]|[^>]*)>";
	// 匹配一行
	public static final String REG_LINE = "[\\S ]+[\\S| ]*";
	// 匹配非空的一行
	public static final String REG_LLINETRIM = "[\\S| ]+";
	/** 匹配空白 */
	public static final String REG_BLANK = "\\s+";
	// 匹配整形
	public static final String REG_INTEGER = "\\d+";
	// java注释
	public static final String REG_JAVACOMMENT = "/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/";
	// 字母
	public static final String REG_LETTER = "([a-z]|[A-Z])*";
	// 数字字母或者下划线 ,以字母开头(java命名规范)
	public static final String REG_NAMING_CONVENTION = "^([a-z]|[A-Z])+([\\_]|\\d|[a-z]|[A-Z])*$";
	// 匹配数字
	public static final String REG_NUMBER = "^\\d+[.]?\\d+$";
	// 字母数字或汉字
	public static final String REG_WORD = "^([\u4e00-\u9fa5]|[a-z]|[A-Z]|[0-9])*$";
	// email
	public static final String REG_EMAIL = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";

	// 是否是整数
	public static boolean isInteger(String str) {
		return str.matches(REG_INTEGER);
	}

	/** 将首字母转为大写，其他不变 */
	public static String firstUp(String str) {
		char ch[];
		ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		String newString = new String(ch);
		return newString;
	}

	/** 按照大写分割,并且用split连接 */
	public static String camelConvert(String str, String split) {
		char ch[] = str.toCharArray();
		StrBuilder sb = new StrBuilder();
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] >= 'A' && ch[i] <= 'Z') {
				ch[i] = (char) (ch[i] + 32);
				if (sb.length() > 0) {
					sb.append("_");
				}
				sb.append(ch[i]);
				continue;
			}
			sb.append(ch[i]);
		}
		return sb.toString();
	}

	/** 按照骆驼命名法分割字符串 */
	public static List<String> splitWithCamel(String str) {
		char ch[];
		ch = str.toCharArray();
		List<String> list = new ArrayList<>();
		StrBuilder sb = new StrBuilder();
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] >= 'A' && ch[i] <= 'Z') {
				ch[i] = (char) (ch[i] + 32);
				if (sb.length() > 0) {
					list.add(sb.toString());
				}
				sb = new StrBuilder();
				sb.append(ch[i]);
				continue;
			}
			sb.append(ch[i]);
		}
		if (sb.length() > 0) {
			list.add(sb.toString());
		}
		return list;
	}

	/** 将首字母转为小写，其他不变 */
	public static String firstLower(String str) {
		String first = str.substring(0, 1);
		String orther = str.substring(1);
		return first.toLowerCase() + orther;
	}

	/** 截取特定字符以前的内容 */
	public static String getFirstBefore(String str, String pos) {
		int index = str.indexOf(pos);
		if (index != -1) {
			return str.substring(0, index);
		} else {
			return str;
		}
	}

	/** 如果字符串是以某个字段串结尾的就截掉 */
	public static String getLastBefore(String str, String tail) {
		int index = str.lastIndexOf(tail);
		if (index != -1) {
			return str.substring(0, index);
		} else {
			return str;
		}
	}

	public static String line() {
		return "\n";
	}

	/** 获得最有一个字符以后的字符串 */
	public static String getLastAfter(String str, String pos) {
		int index = str.lastIndexOf(pos);
		if (index != -1) {
			return str.substring(index + pos.length());
		} else {
			return str;
		}
	}

	/** 保留2位小数 */
	public static double round(double no) {
		BigDecimal b = new BigDecimal(no + "");
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/** 保留2位小数 */
	public static double round(double no, int digit) {
		BigDecimal b = new BigDecimal(no);
		return b.setScale(digit, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static boolean equal(Object a, Object b) {
		return equal(a, b, true);
	}

	/**
	 * 判断equal,主要为了防止空指针异常
	 * 
	 * @param nullToEmpty
	 *            如果此参数为true,则在判断是认为null和""是equal的
	 */
	public static boolean equal(Object a, Object b, boolean nullToEmpty) {
		if (a == b) {
			return true;
		}

		if (nullToEmpty) {
			if (StringUtil.isEmpty(a) && StringUtil.isEmpty(b)) {
				return true;
			}
		}
		if (a == null) {
			return false;
		}
		return a.equals(b);
	}

	/** 在某个字符串后面填充字符串 */
	public static String appendStr(String source, String append, int no) {
		StrBuilder sb = new StrBuilder(source);
		for (int i = 0; i < no; i++) {
			sb.append(append);
		}
		return sb.toString();
	}

	/** 将16级制转为字符串 */
	public static String hexToStr(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes, DEFAULT_CHARSET);
	}

	/** 转换为16进制 */
	public static String toHex(String str) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StrBuilder sb = new StrBuilder("");
		byte[] bs = str.getBytes(DEFAULT_CHARSET);
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
		}
		return sb.toString();
	}

	public static boolean isNotEmpty(Object obj) {
		return !StringUtil.isEmpty(obj);
	}

	public static boolean equal(String[] a, String[] b) {
		if (a == null && b == null) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		if (a.length != b.length) {
			return false;
		}
		for (int i = 0; i < a.length; i++) {
			if (!StringUtil.equal(a[i], b[i])) {
				return false;
			}
		}
		return true;
	}

	/** 是否包含字母汉字或者数字 */
	public static boolean hasWord(String str) {
		if (StringUtil.isEmpty(str)) {
			return false;
		}
		if (str.replaceAll("[\u4e00-\u9fa5]|[a-z]|[A-Z]|[0-9]", "").length() == str.length()) {
			return false;
		}
		return true;
	}

	/** 将首字母转为大写，其他变小写 */
	public static String firstUpOnly(String str) {
		String first = str.substring(0, 1);
		String orther = str.substring(1);
		return first.toUpperCase() + orther.toLowerCase();
	}

	/** 将字段格式化成传入的长度,不够用空格代替 */
	public static String format(int length, String... str) {
		StrBuilder sb = new StrBuilder();
		for (String s : str) {
			sb.append(s);
			for (int i = s.length(); i < length; i++) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}

	/** 提取符合正则表达式的字符串 */
	public static String pickUpFirst(String str, String regx) {
		if (regx == null) {
//			log.error("传入的正则为null", new Exception());
			return null;
		}
		// 忽略大小写.包括换行
		regx = "(?s)(?i)" + regx;
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	/** 字符串是否为空,null或"" */
	public static boolean isEmpty(Object object) {
		if (object instanceof String) {
			return "".equals(object);
		}
		return object == null;
	}

	/**
	 * 提取出所有匹配的字符串 默认忽略大小写,忽略换行
	 */
	public static List<String> pickUp(String str, String regx) {
		regx = "(?s)(?i)" + regx;
		List<String> list = new ArrayList<>();
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			list.add(matcher.group());
		}
		return list;
	}

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 通过某个字符拆分字符串,忽略字符两遍的空白
	 */
	public static String[] split(String data, char ch) {
		return data.split("\\s*" + ch + "\\s*");
	}

	// 是否包含
	public static boolean isContentIgnoreCase(String str, String filter) {
		if (str == null) {
			return false;
		}
		return str.toLowerCase().indexOf(filter.toLowerCase()) != -1;
	}

	// 是否包含
	public static boolean isContain(String str, String filter) {
		if (str == null) {
			return StringUtil.isEmpty(filter);
		}
		if (StringUtil.isEmpty(filter)) {
			return true;
		}
		return str.indexOf(filter) != -1;
	}
}
