package com.sh;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
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

	public static void main1(String[] args) {
		char[] cnArr = new char[]{'一', '二', '三', '四', '五', '六', '七', '八', '九'};
		char[] numArr = new char[]{'1', '2', '3', '4', '五', '六', '七', '八', '九'};

//        String y = "去年次2016年-2019年经济增长速度二零一九年近5年近五年";
		String y = "去年次16-19年经济增长速度二零一九年近1年近五十六年近5000年";
		String regex = "\\d+年";
		String regex1 = "[零|一|二|三|四|五|六|七|八|九]+年";
		String regex2 = "近+\\d+年";
		String regex21 = "前+\\d+年";
		String regex3 = "近+[零|一|二|三|四|五|六|七|八|九]+年ø";
		String regex4 = "[近|去|前|后|明|]+年";
		String regex5 = "\\d+-+\\d+年";
		String regex6 = "\\d+年-+\\d+年";
		String regex7 = "([0-9一两〇二三四五六七八九十零]{1,4})年*(至|到|-)([0-9一两〇二三四五六七八九十零]{1,4})年";
		String regex8 = "(后|近|前)+([0-9一两〇二三四五六七八九十零]){1}+年";
		Matcher m = Pattern.compile(regex8, Pattern.MULTILINE).matcher(y);
		List<String> list = new ArrayList<String>();
		while (m.find()) {
			list.add(m.group());
			System.out.println(m.group());
		}

		System.out.println(String.join(" ","123", "456"));

	}

}
