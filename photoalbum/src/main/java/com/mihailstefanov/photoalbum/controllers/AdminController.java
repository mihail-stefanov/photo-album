package com.mihailstefanov.photoalbum.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/home")
	public String home(Model model, Authentication authentication) {
		model.addAttribute("view", "user/admin-home");
		model.addAttribute("name", authentication.getName());
		model.addAttribute("details", authentication.getDetails());
		model.addAttribute("credentials", authentication.getCredentials());
		model.addAttribute("authorities", authentication.getAuthorities());
		return "base-layout";
	}
	
	
	// TODO: Add all users list with the ability to delete users, convert to administrators, etc.
	
}
