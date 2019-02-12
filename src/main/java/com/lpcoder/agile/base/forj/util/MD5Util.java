package com.lpcoder.agile.base.forj.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	private static final Logger LOG = LoggerFactory.getLogger(MD5Util.class);

	public MD5Util()
	{
	}

	public static String encode(String source)
	{
		try {
			char result[];
			MessageDigest digester = MessageDigest.getInstance("MD5");
			byte sbs[] = source.getBytes("UTF8");
			digester.update(sbs);
			byte rbs[] = digester.digest();
			int j = rbs.length;
			result = new char[j * 2];
			int k = 0;
			for(int i = 0; i < j; i++)
			{
				byte b = rbs[i];
				result[k++] = hexs[b >>> 4 & 15];
				result[k++] = hexs[b & 15];
			}

			return new String(result);
		} catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage(),e);
		} catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(),e);
		}
		return null;
	}

	/**
	 * 签名字符串
	 * @param text 需要签名的字符串
	 * @param key 密钥
	 * @param input_charset 编码格式
	 * @return 签名结果
	 */
	public static String sign(String text, String key, String input_charset) {
		text = text + key;
		return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	}

	/**
	 * 签名字符串
	 * @param text 需要签名的字符串
	 * @param sign 签名结果
	 * @param key 密钥
	 * @param input_charset 编码格式
	 * @return 签名结果
	 */
	public static boolean verify(String text, String sign, String key, String input_charset) {
		text = text + key;
		String mySign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
		if(mySign.equals(sign)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws java.security.SignatureException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}

	public static void main(String args[])
	{
		System.out.println(encode("705214"));

	}

	private static char hexs[] = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f'
	};
}
