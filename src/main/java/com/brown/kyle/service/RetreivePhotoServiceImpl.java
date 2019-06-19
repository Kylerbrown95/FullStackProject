package com.brown.kyle.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.brown.kyle.pojo.PhotoAttributes;
import com.brown.kyle.utils.FileConverter;

/**
 * 
 * @author Kyle Brown
 *
 */

@Service
public class RetreivePhotoServiceImpl implements RetreivePhotoService {

	@Value("${list.of.photo.file.location}")
	private String[] listOfLocations;
	
	@Value("${photo.destination.path}")
	private String destinationPath;

	@Autowired
	private EncryptService encyptService;

	public List<PhotoAttributes> retreiveAndEncyptAllPhotos() {

		List<PhotoAttributes> listOfPhotos = new ArrayList<>();

		try {

			listOfPhotos = getEncyptedPhotos();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfPhotos;

	}

	public void decryptPhotos(List<PhotoAttributes> encryptedPhotos) {

		try {

			List<PhotoAttributes> decryptedPhotos = getDecryptedPhotos(encryptedPhotos);
			FileConverter.byteToFile(decryptedPhotos, destinationPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private List<PhotoAttributes> getDecryptedPhotos(List<PhotoAttributes> encryptedPhotos) {
		List<PhotoAttributes> decryptedPhotos = new ArrayList<>();

		//TODO: Check if Lambda works
		encryptedPhotos.forEach(attributes -> {

			PhotoAttributes decryptedAttributes = new PhotoAttributes();

			byte[] decryptedPhoto = null;
			try {
				decryptedPhoto = encyptService.decrypt(attributes.getPhoto());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (decryptedPhoto != null)
				decryptedAttributes.setPhoto(decryptedPhoto);
			decryptedAttributes.setLocation(attributes.getLocation());
			decryptedAttributes.setTitle(attributes.getTitle());

			decryptedPhotos.add(decryptedAttributes);
		});

		return decryptedPhotos;
	}

	/**
	 * This method will search all of the directories where images are stored and
	 * convert them into byte[] to be stored inside a DB.
	 * 
	 * @return HashMap<String, byte[]>
	 */

	private List<PhotoAttributes> getEncyptedPhotos() {

		List<PhotoAttributes> listOfConvertedPhotos = new ArrayList<>();

		try {

			for (String path : listOfLocations) {

				File folder = new File(path);
				File[] listOfFiles = folder.listFiles();

				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						PhotoAttributes attributes = new PhotoAttributes();
						BufferedImage bImage = ImageIO.read(new File(listOfFiles[i].getAbsoluteFile().toString()));

						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						ImageIO.write(bImage, FilenameUtils.getExtension(listOfFiles[i].getName()), bos);
						byte[] data = bos.toByteArray();

						attributes.setTitle(listOfFiles[i].getName());
						attributes.setPhoto(encyptService.encrypt(data));
						attributes.setLocation(getPhotoLocation(path + listOfFiles[i].getName()));

						listOfConvertedPhotos.add(attributes);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: log and handle exception.
		}

		return listOfConvertedPhotos;
	}

	private String getPhotoLocation(String path) {

		javaxt.io.Image image = new javaxt.io.Image(path);
		double[] gps = image.getGPSCoordinate();

		if (gps != null)
			return gps.toString();

		return "No-Location";
	}

}
