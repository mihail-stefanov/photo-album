package com.mihailstefanov.photoalbum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mihailstefanov.photoalbum.entities.User;
import com.mihailstefanov.photoalbum.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final UserService userService;
	
	@Autowired
	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/home")
	public String home(Model model) {
		List<User> users = this.userService.getAllUsers();
		model.addAttribute("view", "user/admin-home");
		model.addAttribute("users", users);
		return "base-layout";
	}
	
	// TODO: Add all users list with the ability to delete users, convert to administrators, etc.
	
}
