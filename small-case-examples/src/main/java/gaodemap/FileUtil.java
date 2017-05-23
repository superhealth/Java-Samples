package gaodemap;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FileUtil {
	public static final String CHARSET = "UTF-8";

	/** 获得某个类下的文本文件内容 */
	public static String getText(Class<?> clazz, String name) {
		InputStream is = clazz.getResourceAsStream(name);
		return readText(is, CHARSET);
	}

	public static String getText(String path) {
		try {
			return readText(new FileInputStream(path), CHARSET);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(" 读取文件失败:" + path, e);
		}
	}

	public static String getResource(Class<?> clazz, String path) {
		try {

			InputStream ips = clazz.getResourceAsStream(path);
			if (ips == null) {
				URL root = clazz.getResource("/");
//				log.info("找不到资源:" + root + "-->" + path);
				return null;
			}
			return readText(ips, CHARSET);
		} catch (Exception e) {
			throw new RuntimeException("读取资源失败:" + path, e);
		}
	}

	public static String readText(InputStream in, String charsetname) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, charsetname))) {
			StrBuilder buffer = new StrBuilder();
			String line;
			line = reader.readLine();
			while (line != null) {
				buffer.append(line);
				buffer.append(StringUtil.line());
				line = reader.readLine();
			}
			String text = buffer.toString();
			reader.close();
			return text;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	/** 获得某个目录下的所有文件 */
	public static List<File> getAllFile(String root) {
		File rootFile = new File(root);
		final List<File> list = new ArrayList<>();
		scanFile(rootFile, file -> {
			if (file.isFile()) {
				list.add(file);
			}
		});
		return list;
	}

	/** 扫描某个目录 */
	public static void scanFile(File root, FileScanner scaner) {
		scaner.doFile(root);
		if (root.isDirectory()) {
			File[] fList = root.listFiles();
			if (fList == null) {
				return;
			}
			for (int i = 0; i < fList.length; i++) {
				scanFile(fList[i], scaner);
			}
		}
	}

	/** 获得某个包类所在dir */
	public static String getDir(Class<?> clazz) {
		String path = clazz.getPackage().getName().replaceAll("\\.", "/");
		String dir = clazz.getResource("/" + path).getPath();
		if (dir.endsWith("/")) {
			return dir.substring(0, dir.length() - 1);
		} else {
			return dir;
		}
	}

	/** 获得文件扩展名 */
	public static String getExtName(String fileName) {
		int start = fileName.lastIndexOf('.');
		return fileName.substring(start + 1);
	}

	public static String getShortName(String fileName) {
		int index = fileName.lastIndexOf("/");
		if (index == -1) {
			index = fileName.lastIndexOf("\\");
		}
		if (index == -1) {
			return "";
		}
		return fileName.substring(index + 1);
	}

	/** 获得文件所在目录名称 */
	public static String getDir(String fileName) {
		int index = fileName.lastIndexOf("/");
		if (index == -1) {
			index = fileName.lastIndexOf("\\");
		}
		if (index == -1) {
			return "";
		}
		return fileName.substring(0, index);
	}

	public static String readFile(String filePath) {
		return readFile(filePath, CHARSET);
	}

	/** 读取文本文件内容 */
	public static String readFile(String filePath, String charsetname) {

		filePath = filePath.replaceAll("\\%20", " ");
		try {

			FileInputStream fileInputStream = new FileInputStream(filePath);
			return readText(fileInputStream, charsetname);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/** 读取文本文件内容 */
	public static byte[] getByte(String filePath) {
		filePath = filePath.replaceAll("\\%20", " ");
		try {
			File file = new File(filePath);
			byte[] bs = new byte[(int) file.length()];
			FileInputStream fileInputStream = new FileInputStream(filePath);
			try (BufferedInputStream bi = new BufferedInputStream(fileInputStream)) {
				bi.read(bs);
				return bs;
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String createFile(String fileName, String content) {
		return createFile(fileName, content, CHARSET);
	}

	public static void append(String fileName, String content) {
		append(fileName, content);
	}

	public static void append(String fileName, String content, String toEncode) {
		try {
			try (RandomAccessFile aFile = new RandomAccessFile(fileName, "rw")) {
				aFile.seek(aFile.length());
				aFile.write(content.getBytes(toEncode));
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	/** 创建文本文件 */
	public static String createFile(String fileName, String content, String toEncode) {
		try {
			File file = new File(fileName);
			if (file.isFile()) {
//				log.warn("文件" + file.getAbsolutePath() + "已经存在,先删除");
				file.delete();
			}
			try (RandomAccessFile aFile = new RandomAccessFile(fileName, "rw")) {
				aFile.write(content.getBytes(toEncode));
			}
			return file.getAbsolutePath();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	/** 去掉路径 */
	public static String removePath(String fileName) {
		if (StringUtil.isEmpty(fileName)) {
			return fileName;
		}
		int idx = fileName.lastIndexOf('\\');
		if (idx == -1) {
			idx = fileName.lastIndexOf('/');
		}
		if (idx == -1) {
			return fileName;
		}
		return fileName.substring(idx + 1);
	}

	public interface FileScanner {
		public void doFile(File file);
	}

	/** 获得上级目录 */
	public static String parent(String dir) {
		int index = dir.lastIndexOf("/");
		if (index == -1) {
			index = dir.lastIndexOf("\\");
		}
		return dir.substring(0, index);
	}

	/**
	 * UTF-8编码规范 及如何判断文本是UTF-8编码的 UTF-8的编码规则很简单，只有二条：
	 * 1）对于单字节的符号，字节的第一位设为0，后面7位为这个符号的unicode码。因此对于英语字母，UTF-8编码和ASCII码是相同的。
	 * 2）对于n字节的符号（n>1），第一个字节的前n位都设为1，第n+1位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，
	 * 全部为这个符号的unicode码。 根据以上说明 下面给出一段java代码判断UTF-8格式
	 */
	public static boolean isUTF8(byte[] rawtext) {
		int score = 0;
		int i, rawtextlen = 0;
		int goodbytes = 0, asciibytes = 0;
		// Maybe also use UTF8 Byte Order Mark: EF BB BF
		// Check to see if characters fit into acceptable ranges
		rawtextlen = rawtext.length;
		for (i = 0; i < rawtextlen; i++) {
			if ((rawtext[i] & (byte) 0x7F) == rawtext[i]) {
				// 最高位是0的ASCII字符
				asciibytes++;
				// Ignore ASCII, can throw off count
			} else if (-64 <= rawtext[i] && rawtext[i] <= -33
			// -0x40~-0x21
					&& // Two bytes
					i + 1 < rawtextlen && -128 <= rawtext[i + 1] && rawtext[i + 1] <= -65) {
				goodbytes += 2;
				i++;
			} else if (-32 <= rawtext[i] && rawtext[i] <= -17 && // Three bytes
					i + 2 < rawtextlen && -128 <= rawtext[i + 1] && rawtext[i + 1] <= -65 && -128 <= rawtext[i + 2]
					&& rawtext[i + 2] <= -65) {
				goodbytes += 3;
				i += 2;
			}
		}
		if (asciibytes == rawtextlen) {
			return false;
		}
		score = 100 * goodbytes / (rawtextlen - asciibytes);
		// If not above 98, reduce to zero to prevent coincidental matches
		// Allows for some (few) bad formed sequences
		if (score > 98) {
			return true;
		} else if (score > 95 && goodbytes > 30) {
			return true;
		} else {
			return false;
		}
	}

}
