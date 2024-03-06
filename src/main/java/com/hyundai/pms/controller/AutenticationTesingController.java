package com.hyundai.pms.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/authTest")
@RestController
public class AutenticationTesingController {
	
	@GetMapping("/test")
	public String testingApi() {
		return "This method is available";
	}
	
//	@PostMapping("/logout")
//	public String logoutApi() {
//		return "logout success";
//	}

}
