package com.brown.kyle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Dashboard")
public class UiController {
	
	@GetMapping("/Stats")
	public String stats(Model model) {
		
		model.addAttribute("Hello", "Boo Bear");		
		
		return "Stats";
	}

}
