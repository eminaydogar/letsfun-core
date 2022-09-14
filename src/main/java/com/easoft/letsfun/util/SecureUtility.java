package com.easoft.letsfun.util;

import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecureUtility {

	private final String configFileName = "secure.xml";

	private final String configHeader = "secureUtility";

	private static SecureUtility instance = null;

	private static SecretKey key = null;

	private static byte[] byteArray;

	private static String transformation;

	private static String algo;

	private static String pwd;

	private static String salt;

	private static String algorithm;

	private SecureUtility() {

		/*
		 * HashMap<String, String> map = new
		 * FileReader().createResourceReader().readXMLFile(configFileName,
		 * configHeader); transformation = map.get("transformation"); algo =
		 * map.get("algo"); pwd = map.get("pwd"); salt = map.get("salt"); algorithm =
		 * map.get("algorithm"); byteArray =
		 * byteStringToByteArray(map.get("byteArray"));
		 */

	}

	public static void init() {
		instance = new SecureUtility();
	}

	private byte[] byteStringToByteArray(String byteString) {

		String[] byteStringArray = byteString.split(",");

		byte[] array = new byte[byteStringArray.length];

		int c = 0;

		for (String b : byteStringArray) {
			array[c] = (byte) b.charAt(0);
			c++;
		}

		return array;

	}

	public static SecureUtility getInstance() {
		if (instance == null) {
			instance = new SecureUtility();
		}
		return instance;
	}

	public String generateVertifyCode(int length) {
		if (length > 36) {
			return null;
		}
		String code = null;
		String randomKey = UUID.randomUUID().toString();
		randomKey = randomKey.toUpperCase();
		randomKey = randomKey.replace("-", "");
		code = randomKey.substring(randomKey.length() - length - 1, randomKey.length() - 1);
		return code;

	}

	/*
	 * 
	 * 5 hane sağa kaydır, sayıyı 6 ile çarp, sonucu 3 hane sola kaydır.
	 * 
	 * 
	 */
	public Long phoneNumberCrypter(Long phoneNumber) {

		if (phoneNumber == null) {
			return phoneNumber;
		}

		char[] numberChars = phoneNumber.toString().toCharArray();
		int length = numberChars.length;
		char[] coppyNumberChars = new char[length];

		if (length != 10) {
			return null;
		}
		for (int i = 0; i < length; i++) {
			if (i + 5 >= length) {
				int point = (i + 5) - length;
				coppyNumberChars[point] = numberChars[i];
			} else {
				coppyNumberChars[i + 5] = numberChars[i];
			}

		}
		Long firstResult = Long.valueOf(String.valueOf(coppyNumberChars)) * 6;
		String firstResultString = firstResult.toString();
		int resultLength = firstResultString.length();
		char[] resultNumberChars = firstResultString.toCharArray();
		char[] coppyResultNumberChars = new char[resultLength];
		for (int i = resultLength - 1; i >= 0; i--) {

			if (i - 3 < 0) {
				int point = (resultLength - 3) + i;
				coppyResultNumberChars[point] = resultNumberChars[i];
			} else {
				coppyResultNumberChars[i - 3] = resultNumberChars[i];
			}
		}

		Long result = Long.valueOf(String.valueOf(coppyResultNumberChars).trim());
		return result;

	}

	/*
	 * 
	 * 
	 * 3 hane saga kaydır, sayıyı 6 ile böl, 5 hane sola kaydır.
	 * 
	 * 
	 */
	public Long phoneNumberDecrypter(Long phoneNumber) {

		char[] numberChars = phoneNumber.toString().toCharArray();
		int length = numberChars.length;
		char[] coppyNumberChars = new char[length];

		for (int i = 0; i < length; i++) {
			if (i + 3 >= length) {
				int point = (i + 3) - length;
				coppyNumberChars[point] = numberChars[i];
			} else {
				coppyNumberChars[i + 3] = numberChars[i];
			}

		}

		Long firstResult = Long.valueOf(String.valueOf(coppyNumberChars)) / 6;
		String firstResultString = firstResult.toString();
		int resultLength = firstResultString.length();
		char[] resultNumberChars = firstResultString.toCharArray();
		char[] coppyResultNumberChars = new char[resultLength];
		for (int i = resultLength - 1; i >= 0; i--) {

			if (i - 5 < 0) {
				int point = (resultLength - 5) + i;
				coppyResultNumberChars[point] = resultNumberChars[i];
			} else {
				coppyResultNumberChars[i - 5] = resultNumberChars[i];
			}
		}

		Long result = Long.valueOf(String.valueOf(coppyResultNumberChars).trim());
		return result;
	}

	public String encrypt(String input) {

		Cipher cipher;
		byte[] cipherText = null;
		try {
			cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.ENCRYPT_MODE, getKeyFromPassword(), new IvParameterSpec(byteArray));
			cipherText = cipher.doFinal(input.getBytes());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return Base64.getEncoder().encodeToString(cipherText);
	}

	private static SecretKey getKeyFromPassword() {
		if (key != null) {
			return key;
		}
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
			KeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt.getBytes(), 65536, 256);
			key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), algo);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return key;
	}

	public String decrypt(String cipherText) {
		byte[] plainText = null;
		try {
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.DECRYPT_MODE, getKeyFromPassword(), new IvParameterSpec(byteArray));
			plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return new String(plainText);
	}

	public String hideAddress(String address, int first, int last) {
		String result = null;
		if (address != null) {
			char[] addressChar = address.toCharArray();
			for (int i = first; i < addressChar.length - last; i++) {
				addressChar[i] = '*';
			}
			result = new String(addressChar);
		}

		return result;
	}

	public static void main(String[] args) {

		String input = "Batı Mahallesi Öksüz çolak Sıkıntı olucak No:5 & 15/51";

		String cipherText = SecureUtility.getInstance().encrypt(input);
		String plainText = SecureUtility.getInstance().decrypt(cipherText);
		SecureUtility.init();
		// String cipherText = encrypt(input);
		// String plainText = decrypt(cipherText);

		System.out.println(cipherText);
		System.out.println(plainText);

		String cipherText1 = SecureUtility.getInstance().encrypt(input);
		SecureUtility.init();
		String plainText2 = SecureUtility.getInstance().decrypt(cipherText);
		System.out.println(cipherText1);
		System.out.println(plainText2);

	}

}

/*
 * 
 * 
 * 
 * 
 * 
 * private static String addressCrypter(String address) { String result = null;
 * char[] addressChars = address.toCharArray(); int length =
 * addressChars.length; int[] cyrptChar = new int[length]; int privVal = 1;
 * 
 * for (int i = 0; i < length; i++) { cyrptChar[i] = ((int) addressChars[i]) +
 * 10; // System.out.println(cyrptChar[i]); }
 * 
 * for (int i = 1;; i = i * 7) { int index = privVal + i; if (index > length) {
 * break; } int tempValue = cyrptChar[index]; System.out.println(i + ".deger : "
 * + cyrptChar[i]); System.out.println(index + ".deger : " + cyrptChar[index]);
 * cyrptChar[index] = cyrptChar[i]; cyrptChar[i] = tempValue; //
 * System.out.println(i + "[" + cyrptChar[i] + "]" + "--> <--" + index + "[" +
 * // cyrptChar[index] + "]"); System.out.println( "Yer degistirildi .... " + i
 * + "==" + cyrptChar[i] + "  &  " + index + "==" + cyrptChar[index]); privVal =
 * index; }
 * 
 * for (int i = 0; i < length; i++) { addressChars[i] = (char) cyrptChar[i]; }
 * result = String.valueOf(addressChars);
 * 
 * return result; }
 * 
 * private static String addressDecrypter(String address) { String result =
 * null; int privVal = 1; int length = address.length(); char[] addressChars =
 * address.toCharArray(); int[] decyrptChar = new int[length];
 * 
 * for (int i = 0; i < length; i++) { decyrptChar[i] = ((int) addressChars[i]) -
 * 10; // System.out.println(decyrptChar[i]); }
 * 
 * for (int i = 1;; i = i * 7) { int index = privVal + i; if (index > length) {
 * break; } int tempValue = decyrptChar[i];
 * 
 * System.out.println(i + ".deger : " + decyrptChar[i]);
 * System.out.println(index + ".deger : " + decyrptChar[index]); decyrptChar[i]
 * = decyrptChar[index]; decyrptChar[index] = tempValue; System.out.println(
 * "Yer degistirildi .... " + i + "==" + decyrptChar[i] + "  &  " + index + "=="
 * + decyrptChar[index]); // System.out.println(index + "[" + decyrptChar[index]
 * + "]" + "--> <--" + i + // "[" + decyrptChar[i] + "]"); privVal = index; }
 * 
 * for (int i = 0; i < length; i++) { addressChars[i] = (char) decyrptChar[i]; }
 * result = String.copyValueOf(addressChars);
 * 
 * return result; }
 * 
 * 
 * 
 * 
 * 
 * 
 */
