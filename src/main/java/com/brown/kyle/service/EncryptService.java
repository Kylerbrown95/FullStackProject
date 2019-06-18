package com.brown.kyle.service;

/**
 * 
 * @author Kyle Brown
 *
 */

public interface EncryptService {

	byte[] encrypt(byte[] data) throws Exception;

	byte[] decrypt(byte[] encryptedData) throws Exception;

}
