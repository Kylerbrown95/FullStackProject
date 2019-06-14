package com.brown.kyle.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.brown.kyle.pojo.PhotoAttributes;

/**
 * 
 * @author Kyle Brown
 *
 */

@Service
public class RetreivePhotoServiceImpl implements RetreivePhotoService {

	@Value("${list.of.photo.file.location}")
	private String[] listOfLocations;

	public List<PhotoAttributes> retreiveAllPhotos() {

		List<PhotoAttributes> listOfPhotos = new ArrayList<>();

		try {
			// TODO: Call database for photos
		} catch (Exception e) {

		}

		return listOfPhotos;

	}

	/**
	 * This method will search all of the directories where images are stored and
	 * convert them into byte[] to be stored inside a DB.
	 * 
	 * @return HashMap<String, byte[]>
	 */

	private List<PhotoAttributes> covertPhotoToBytes() {

		List<PhotoAttributes> listOfConvertedPhotos = new ArrayList<>();

		try {

			for (String path : listOfLocations) {

				File folder = new File(path);
				File[] listOfFiles = folder.listFiles();

				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						PhotoAttributes attributes = new PhotoAttributes();
						BufferedImage bImage = ImageIO.read(new File(listOfFiles[i].getName()));

						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						ImageIO.write(bImage, FilenameUtils.getExtension(listOfFiles[i].getName()), bos);
						byte[] data = bos.toByteArray();

						attributes.setTitle(listOfFiles[i].getName());
						attributes.setPhoto(data);
						attributes.setLocation(getPhotoLocation(path + listOfFiles[i].getName()));

						listOfConvertedPhotos.add(attributes);
					}
				}
			}
		} catch (Exception e) {
			// TODO: log and handle exception.
		}

		return listOfConvertedPhotos;
	}

	private String getPhotoLocation(String path) {

		javaxt.io.Image image = new javaxt.io.Image(path);
		double[] gps = image.getGPSCoordinate();

		// TODO: use google API to translate GPS location to country
		// https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&sensor=true
		return null;
	}

}
