package com.ego.apps.commonshare.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ego.apps.commonshare.exceptions.CSBusinessException;

public class SecurityUtils {
	/**
	 * Blank private constructor to disallow any instantiation of this class.
	 */
	private SecurityUtils() {

	}

	/**
	 * Encrypts the input String into MD5 encryption format. The encrypted
	 * string is called a digest.
	 * 
	 * MD5 is a hash - and can not be decrypted.
	 * 
	 * @param string
	 *            The string to be encrypted. The string must not be spaces.
	 * @return The encryption of the string (digest). Returns null if the string
	 *         is empty.
	 * @throws CSBusinessException
	 *             thrown when there is a problem with the algorithm.
	 */
	public static String md5encrypt(String string) throws CSBusinessException {
		if (!StringUtils.isEmpty(string)) {
			MessageDigest messageDigest;
			try {
				messageDigest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				throw new CSBusinessException(noSuchAlgorithmException);
			}
			messageDigest.reset();
			messageDigest.update(string.getBytes());
			return new String(messageDigest.digest());
		}
		return null;
	}

	public static void main(String args[]) throws CSBusinessException {
		System.out.println(md5encrypt("admin"));
	}
}
