package com.brown.kyle.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Kyle Brown
 *
 */

public class RestTemplateHelper {

	/**
	 * 
	 * Method to make a REST call via RestTemplate
	 * 
	 * @param url
	 * @param method
	 * @param requestEntity
	 * @param clazz
	 * @return ResponseEntity<?>
	 */

	public static ResponseEntity<?> excecute(String url, HttpMethod method, RequestEntity<?> requestEntity,
			Class<?> clazz) {

		RestTemplate restTemp = new RestTemplate();

		ResponseEntity<?> response = restTemp.exchange(url, method, requestEntity, clazz);

		return response;

	}

}
