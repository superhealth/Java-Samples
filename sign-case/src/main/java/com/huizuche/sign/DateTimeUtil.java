package com.huizuche.sign;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import jodd.util.StringUtil;

/**
 * @author liaokn
 *
 */
public class DateTimeUtil {

	private DateTimeUtil() {
	}

	
	private static final String DATETIME_T_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
	public static final FastDateFormat SDF_T_DATETIME_PATTERN = FastDateFormat.getInstance(DATETIME_T_PATTERN);
	public static final DateTimeFormatter LOCAL_DATE_TIME;
	static {
		LOCAL_DATE_TIME = DateTimeFormatter.ofPattern(DATETIME_T_PATTERN);
	}

	public static LocalDateTime conver(Date date) {
		if (null != date) {
			return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		}
		return null;
	}

	public static Date conver(LocalDateTime date) {
		if (null != date) {
			return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
		}
		return null;
	}

	/**
	 * 是否在区间内，包含关系
	 * 
	 */
	public static boolean isBetween(LocalTime in, LocalTime beginTime, LocalTime endTime) {
		boolean isBetween = false;
		if (null == in) {
			return isBetween;
		}
		if (null != beginTime) {
			isBetween = in.isAfter(beginTime);
			isBetween |= in.equals(beginTime);
		}
		if (isBetween && null != endTime) {
			isBetween = in.isBefore(endTime);
			isBetween |= in.equals(endTime);
		}
		return isBetween;
	}

	public static Optional<LocalTime> parse2Time(String originTimeStr) {
		LocalTime time = null;
		if (StringUtil.isNotEmpty(originTimeStr)) {
			String timeStr = originTimeStr;
			if (originTimeStr.length() < 5) {
				timeStr = StringUtils.leftPad(originTimeStr, 5, '0');
			}
			try {
				time = LocalTime.parse(timeStr, DateTimeFormatter.ISO_TIME);
			} catch (Exception e) {
				System.out.println("parse [{}] to time occurs error");
			}
		}
		return Optional.ofNullable(time);
	}

}
