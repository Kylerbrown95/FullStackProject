package com.brown.kyle.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.brown.kyle.pojo.PhotoAttributes;

/**
 * 
 * @author Kyle Brown
 *
 */

public class FileConverter {

	/**
	 * Converts byte[] to photo
	 * 
	 * @param photoAttributes
	 */

	public static void byteToFile(final List<PhotoAttributes> photoAttributes, final String destinationPath) {

		for (PhotoAttributes attribute : photoAttributes) {

			try {
				FileUtils.writeByteArrayToFile(new File(destinationPath + attribute.getTitle()), attribute.getPhoto());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
