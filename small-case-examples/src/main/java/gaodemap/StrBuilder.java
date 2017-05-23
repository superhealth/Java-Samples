package gaodemap;

/**
 * 
 * @author wangym
 * @date 2016-08-01
 */
public class StrBuilder implements java.io.Serializable {
	private static final long serialVersionUID = 186512729602069901L;
	private java.lang.StringBuilder sb = null;

	public StrBuilder() {
		sb = new java.lang.StringBuilder(128);
	}

	public StrBuilder(int capacity) {
		sb = new java.lang.StringBuilder(capacity);
	}

	public StrBuilder(String str) {
		sb = new java.lang.StringBuilder(str);
	}

	public int length() {
		return sb.length();
	}

	public char charAt(int index) {
		return sb.charAt(index);
	}

	public CharSequence subSequence(int start, int end) {
		return sb.subSequence(start, end);
	}

	public StrBuilder append(String str) {
		sb.append(str);
		return this;
	}

	public StrBuilder append(char c) {
		sb.append(c);
		return this;
	}

	public StrBuilder appendLine(String str) {
		sb.append(str);
		sb.append(StringUtil.line());
		return this;
	}

	public StrBuilder appendLine(StringBuilder str) {
		sb.append(str);
		sb.append(StringUtil.line());
		return this;
	}

	public String toString() {
		return sb.toString();
	}

	public StrBuilder deleteCharAt(int index) {
		sb.deleteCharAt(index);
		return this;
	}

	public String substring(int i, int j) {
		return sb.substring(i, j);
	}

	public String substring(int i) {
		return sb.substring(i);
	}

}
