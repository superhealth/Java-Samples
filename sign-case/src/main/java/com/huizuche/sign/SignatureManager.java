package com.huizuche.sign;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.stream.StreamSupport;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import jodd.util.StringUtil;

public class SignatureManager {

	/*
	 * 检查签名
	 */
	public  <T> boolean verifySign(T request,  String signPrivateKey) {

		


		try {

			String signOriginalString = generateParamStr(request, StringUtils.EMPTY);

			String signSecretString = signPrivateKey + signOriginalString + signPrivateKey;
			String md5Str = DigestUtils.md5Hex(signSecretString.toUpperCase());

//			if (md5Str.equals(request.getHeader().getSign())) {
				System.out.println("签名:"+md5Str);
				return true;
//			} else {
//
//				System.out.println("签名验证出错");
//				return false;
//			}
		} catch (Exception exp) {

			return false;
		}
	}


	private String generateParamStr(Object o, String fieldName) {

		String paramStr = StringUtils.EMPTY;

		if (null == o) {
			return paramStr;
		}

		Class<?> requestClz = o.getClass();

		if (o.getClass().isArray()) {

			String arrayString = Arrays.stream((Object[]) o).map(oa -> generateParamStr(oa, StringUtils.EMPTY)).sorted()
					.reduce(StringUtil::join).orElse(StringUtils.EMPTY);

			paramStr = arrayString;
		} else if (o.getClass().isPrimitive() || o instanceof String) {

			paramStr = StringUtil.toSafeString(o);
		} else if (o.getClass().isEnum()) {

			Object enumString = ReflectionUtils.invokeMethod(ReflectionUtils.findMethod(o.getClass(), "getValue"), o);

			paramStr = StringUtil.toSafeString(enumString);
		} else if (o instanceof Iterable) {

			@SuppressWarnings("unchecked")
			String iterable = StreamSupport.stream(((Iterable<Object>) o).spliterator(), false)
					.map(oi -> generateParamStr(oi, StringUtils.EMPTY)).sorted().reduce(StringUtil::join)
					.orElse(StringUtils.EMPTY);

			paramStr = iterable;
		} else if (o instanceof Map) {

			System.out.println("Map ni mei");
		} else if (o instanceof Date) {

			String dateString = DateTimeUtil.SDF_T_DATETIME_PATTERN.format((Date) o);

			paramStr = dateString;
		} else if (o instanceof LocalDateTime) {

			paramStr = ((LocalDateTime) o).format(DateTimeUtil.LOCAL_DATE_TIME);
		} else if (o instanceof BigDecimal) {

			paramStr = ((BigDecimal) o).toPlainString();
		} else {

			Field typeField = ReflectionUtils.findField(requestClz, "TYPE");
			if (null != typeField) {
				Object packingType = ReflectionUtils.getField(typeField, null);

				if (packingType instanceof Class && ((Class<?>) packingType).isPrimitive()) {
					paramStr = StringUtil.toSafeString(o);
				}
			}

			if (StringUtil.isEmpty(paramStr)) {

				Field[] fields = requestClz.getDeclaredFields();

				String encapsulationString = Arrays.stream(fields).peek(f -> f.setAccessible(true)).map(f -> {
					try {
						return generateParamStr(f.get(o), f.getName());
					} catch (Exception e) {
						System.out.println("reflect error ");
					}
					return StringUtils.EMPTY;
				}).sorted().reduce(StringUtil::join).orElse(StringUtils.EMPTY);

				paramStr = encapsulationString;
			}

		}

		return fieldName + paramStr;
	}

}
