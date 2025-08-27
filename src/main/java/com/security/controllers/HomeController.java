package com.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HomeController {
	@GetMapping("/home")
	public String getHome() {
		return "This is the Home Page";
	}
	@GetMapping("/register")
	public String getRegisterPage() {
		return "register";
	}
	@GetMapping("/login")
	public String getloginPage() {
		return "login";
	}

}
