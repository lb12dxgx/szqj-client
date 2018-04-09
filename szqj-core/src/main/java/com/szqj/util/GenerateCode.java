package com.szqj.util;

public class GenerateCode {

	/**
	 * 产生邀请码
	 * @return
	 */
	public static String genInviteCode() {
		return generateRandomStr(6);
	}
	
	/**
	 * 产生验证码
	 * @return
	 */
	public static String genSms(){
		return generateRandomStr(4);
	}
	
	

	private static String generateRandomStr(int len) {
		// 字符源，可以根据需要删减
		String generateSource = "23456789abcdefghgklmnpqrstuvwxyz";// 去掉1和i ，0和o
		String rtnStr = "";
		for (int i = 0; i < len; i++) {
			// 循环随机获得当次字符，并移走选出的字符
			String nowStr = String
					.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
			rtnStr += nowStr;
			generateSource = generateSource.replaceAll(nowStr, "");
		}
		return rtnStr;
	}
	

}
