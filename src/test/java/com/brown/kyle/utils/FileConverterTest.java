package com.brown.kyle.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.junit.Assert;
import org.junit.Test;

import com.brown.kyle.pojo.PhotoAttributes;

/**
 * 
 * @author Kyle Brown
 *
 */

public class FileConverterTest {

	private static final String TITLE = "recent-images-11.jpg";
	private static final String PATH = "C:\\Users\\kyler\\OneDrive\\Desktop\\photo1\\" + TITLE;
	private static final String DESTINATION_PATH = "C:\\Users\\kyler\\OneDrive\\Desktop\\photo2\\";

	@Test
	public void testByteToFile() {

		PhotoAttributes attributes = new PhotoAttributes();
		List<PhotoAttributes> photoAttributes = new ArrayList<>();
		try {
		BufferedImage bImage = ImageIO.read(new File(PATH));

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, FilenameUtils.getExtension(TITLE), bos);
		byte[] data = bos.toByteArray();

		attributes.setPhoto(data);
		attributes.setLocation("No-Location");
		attributes.setTitle(TITLE);
		
		photoAttributes.add(attributes);

		FileConverter.byteToFile(photoAttributes, DESTINATION_PATH);
		}catch (Exception e) {
			Assert.fail();
		}

	}

}
