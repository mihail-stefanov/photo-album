package com.mihailstefanov.photoalbum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mihailstefanov.photoalbum.bindingModels.UserRegisterBindingModel;
import com.mihailstefanov.photoalbum.services.UserService;

@Controller
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/register")
	@PreAuthorize("isAnonymous()")
	public String registerGet(Model model) {
		model.addAttribute("view", "user/register");
		return "base-layout";
	}
	
	@PostMapping("/register")
	@PreAuthorize("isAnonymous()")
	public String registerPost(Model model, @ModelAttribute UserRegisterBindingModel userRegisterBindingModel) {
		
		if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
			// TODO: Add error message to be displayed
			model.addAttribute("view", "user/register");
			return "base-layout";
		}
		
		this.userService.createUser(userRegisterBindingModel);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	@PreAuthorize("isAnonymous()")
	public String loginGet(@RequestParam(required = false, name = "error") String error, Model model) {
		
		if (error != null) {
			// TODO: Implement error handling
			model.addAttribute("error", "error to implement");
		}
		
		model.addAttribute("view", "user/login");
		return "base-layout";
	}
	
//	@PostMapping("/login")
//	public String loginPost(Model model) {
//		return null;
//	}
//	
}
