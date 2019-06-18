package com.brown.kyle.service;

import java.util.List;

import com.brown.kyle.pojo.PhotoAttributes;

/**
 * 
 * @author Kyle Brown
 *
 */

public interface RetreivePhotoService {
	
	List<PhotoAttributes> retreiveAndEncyptAllPhotos();

}
