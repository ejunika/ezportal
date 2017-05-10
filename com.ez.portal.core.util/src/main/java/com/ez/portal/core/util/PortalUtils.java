package com.ez.portal.core.util;

import java.security.MessageDigest;

public class PortalUtils {
	public static String getHashedString(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] bytes = str.getBytes();
		messageDigest.reset();
		messageDigest.update(bytes);
		byte md[] = messageDigest.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < md.length; i++) {
			String hex = Integer.toHexString(0xFF & md[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString().toUpperCase();
	}
}
