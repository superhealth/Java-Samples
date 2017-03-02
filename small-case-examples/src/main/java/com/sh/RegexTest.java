package com.sh;

import java.util.regex.Pattern;

public class RegexTest {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Pattern p = Pattern.compile("[《]+"); 
		//用Pattern的split()方法把字符串按"/"分割 
		String[] result = p.split( 
		"Kevin has seen《LEON》seveal times,because it is a good film." 
		+"/ 凯文已经看过《这个杀手不太冷》几次了，因为它是一部" 
		+"好电影。/名词:凯文。"); 
		for (int i=0; i<result.length; i++) 
		System.out.println(result[i]); 

		System.out.println(result[1].format("%s,,,,,,%s", "12","22"));
		
		split();
	}

	public static void split() {
		String vendorshop="YZRO01|YXUO01|YQGO01";
		Pattern p = Pattern.compile("[|]+"); 
		String[] shopArray=p.split(vendorshop);
		for (String string : shopArray) {
			System.out.println(string);
		}
	}
}
