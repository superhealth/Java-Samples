package com.sh.file.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * 读取网络文件
 * Created by lkn on 2018/2/28.
 */
public class NetFileReader {

	public static void main(String[] args) throws IOException {
		String urlStr = "https://growing-insights.s3.cn-north-1.amazonaws.com.cn/4b25_90950cbd19f86c22_action_tag_20180226/part-00000-3a88be7a-5acc-41c6-9cbc-6a72998134c5.csv.gz?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAP2ISDXVVNV53ECSQ/20180228/cn-north-1/s3/aws4_request&X-Amz-Date=20180228T063827Z&X-Amz-Expires=300&X-Amz-SignedHeaders=host&X-Amz-Signature=a0e06ef34c2e189b3ae20e4b098029ce08c4ca5f28cadbc950eca2158233de53";
		parseFileFromUrl(urlStr);

	}

	public static void parseFileFromUrl(String urlStr) throws IOException {

		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置超时间为3秒
		conn.setConnectTimeout(3 * 1000);
		// 防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestProperty("contentType", "utf-8");
		// 得到输入流  解压网络GZIP文件
		InputStream inputStream =  new GZIPInputStream(conn.getInputStream());

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

		reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
		String line = null;
		while ((line = reader.readLine()) != null) {
			String item[] = line.split(",");// CSV格式文件为逗号分隔符文件，这里根据逗号切分

			String last = item[item.length - 1];// 这就是你要的数据了
			// int value = Integer.parseInt(last);//如果是数值，可以转化为数值
			System.out.println(item[0] + "|" + item[1] + "|" + last);
		}
	}

}
