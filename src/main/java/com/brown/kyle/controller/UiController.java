package com.brown.kyle.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brown.kyle.pojo.PhotoAttributes;
import com.brown.kyle.service.RetreivePhotoService;

@Controller
@RequestMapping("/Dashboard")
public class UiController {

	@Autowired
	private RetreivePhotoService photoService;

	@GetMapping("/Stats")
	public String stats(Model model) {

		model.addAttribute("Hello", "Hello World");

		return "Stats";
	}

	/**
	 * Retrieves all photos and encrypts them ready for storage.
	 * 
	 * @return {@link List<PhotoAttributes>}
	 */

	@GetMapping("/backup/encrypt/photos")
	public ResponseEntity<List<PhotoAttributes>> backupAndEncyptPhotos() {

		List<PhotoAttributes> response = new ArrayList<>();

		try {
			response = photoService.retreiveAndEncyptAllPhotos();
			return new ResponseEntity<List<PhotoAttributes>>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<PhotoAttributes>>(HttpStatus.BAD_GATEWAY);
		}

	}
	
	@PostMapping("/decrypt/photos")
	public void decryptPhotos(@RequestBody List<PhotoAttributes> listOfEncryptedPhotos) {
		
	}

}
