package com.brown.kyle.service;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Kyle Brown
 *
 */
@Service
public class EncryptServiceImpl implements EncryptService {

	private static final String ALGORITHM = "AES";
	// TODO: Create real key
	private static byte[] KEY_VAL = new byte[] { 'a' };

	/**
	 * @return encrypted data
	 */
	public byte[] encrypt(byte[] data) throws Exception {

		return encryptDecrypt(Cipher.ENCRYPT_MODE, data);

	}

	/**
	 * @return decrypted data
	 */

	public byte[] decrypt(byte[] encryptedData) throws Exception {

		return encryptDecrypt(Cipher.DECRYPT_MODE, encryptedData);
	}

	/**
	 * 
	 * @param mode
	 * @param data
	 * @return encrypted or decrypted data
	 * @throws Exception
	 */

	private static byte[] encryptDecrypt(int mode, byte[] data) throws Exception {

		final Key key = genKey();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(mode, key);

		return cipher.doFinal(data);
	}

	/**
	 * 
	 * @return {@link Key}
	 * @throws Exception
	 */

	private static Key genKey() throws Exception {
		Key key = new SecretKeySpec(KEY_VAL, ALGORITHM);
		return key;
	}

}
