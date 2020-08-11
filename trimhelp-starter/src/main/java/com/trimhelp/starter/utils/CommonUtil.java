package com.trimhelp.starter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonUtil {

	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	private static double EARTH_RADIUS = 6378.137;

	/**
	 * 校验身份证
	 * @param cardNo 身份证号
	 * @return
	 */
	public static boolean checkCardNo(String cardNo) {

		if (StringUtils.isEmpty(cardNo) || cardNo.length() < 15) {
			return false;
		}

		// 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
		String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
				"(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

		boolean matches = cardNo.matches(regularExpression);

		if (matches) {
			if (cardNo.length() == 18) {
				try {
					char[] charArray = cardNo.toCharArray();
					//前十七位加权因子
					int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
					//这是除以11后，可能产生的11位余数对应的验证码
					String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
					int sum = 0;
					for (int i = 0; i < idCardWi.length; i++) {
						int current = Integer.parseInt(String.valueOf(charArray[i]));
						int count = current * idCardWi[i];
						sum += count;
					}
					char idCardLast = charArray[17];
					int idCardMod = sum % 11;
					if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
						return true;
					} else {
						log.error("身份证最后一位:" + String.valueOf(idCardLast).toUpperCase() +
								"错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
						return false;
					}

				} catch (Exception e) {
					log.error(cardNo +" 异常:" + e.getMessage());
					e.printStackTrace();
					return false;
				}

			}

		}
		return matches;

	}

	/**
	 * 校验电话号码
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		if (StringUtils.isEmpty(mobile)) {
			return false;
		}
		if (!CommonUtil.regexPhone(mobile)&&!CommonUtil.regexMobile(mobile)) {
			return false;
		}
		return true;
	}

	/**
	 * 校验手机号码
	 * @param phoneNum
	 * @return
	 */
	public static boolean regexMobile(String phoneNum){
		if (StringUtils.isEmpty(phoneNum)) {
			return false;
		}

		String regex = "^1[3|4|5|6|7|8|9][0-9]\\d{8}$";
		return Pattern.matches(regex, phoneNum);
	}

	/**
	 * 校验固话号码
	 * @param phoneNum
	 * @return
	 */
	public static boolean regexPhone(String phoneNum){
		if (StringUtils.isEmpty(phoneNum)) {
			return false;
		}
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		// 验证带区号的
		String regex1 = "0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}";
//				"0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}";
		p1 = Pattern.compile(regex1);
		// 验证没有区号的
		String regex2 = "^[1-9]{1}[0-9]{5,8}$";
		p2 = Pattern.compile(regex2);
		if (phoneNum.length() > 9) {
			m = p1.matcher(phoneNum);
			b = m.matches();
		} else {
			m = p2.matcher(phoneNum);
			b = m.matches();
		}
		return b;
	}


	/**
	 * 对身份证进行替换
	 * @param cardNo
	 * @return
	 */
	public static String replaceCardNo(String cardNo) {

		boolean flag = checkCardNo(cardNo);

		if (flag) {
			return cardNo.replace(cardNo.substring(cardNo.length() - 6, cardNo.length()), "******");
		}

		return "";
	}

	/**
	 * 对手机号进行替换
	 * @param mobile
	 * @return
	 */
	public static String replaceMobile(String mobile) {

		boolean flag = checkMobile(mobile);

		if (flag) {
			if(mobile.length() > 7){
				return mobile.replace(mobile.substring(3, 7), "****");
			}else{
				return  mobile;
			}
		}

		return "";
	}


	/**
	 * 通过经纬度获取距离(单位：米)
	 *
	 * @param lat1(纬度)
	 * @param lng1(经度)
	 * @param lat2
	 * @param lng2
	 * @return 距离
	 */
	public static double getDistance(double lat1, double lng1, double lat2,
									 double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		s = s * 1000;
		return s;
	}
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}



	/**
	 * 根据身份证获取年龄
	 * @param idCard
	 * @return
	 * @throws Exception
	 */
	public static int getAgeByIdCard(String idCard) throws Exception{
		//获取身份证上的年月日
		String dates = idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14);
		Date nowDate = new Date();
		//获取当前时间
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = df.parse(dates);
		//格式化出生日期
		long diff = nowDate.getTime() - birthDate.getTime();
		long ages = diff / (1000 * 60 * 60 * 24) / 365;
		return (int)ages;
	}



}
