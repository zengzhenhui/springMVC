package com.mxd.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/**
	 * 32位MD5加密
	 * 
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getGeneral32BitMD5(String key)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("MD5");
		md.reset();
		md.update(key.getBytes("UTF-8"));
		StringBuffer buf = new StringBuffer();
		for (byte b : md.digest())
			buf.append(String.format("%02x", b & 0xff));
		return buf.toString().toLowerCase();
	}

}
