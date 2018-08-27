package com.mihailstefanov.photoalbum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mihailstefanov.photoalbum.bindingModels.UserBindingModel;
import com.mihailstefanov.photoalbum.services.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String registerGet(Model model) {
		model.addAttribute("view", "user/register");
		return "base-layout";
	}
	
	@PostMapping("/register")
	public String registerPost(Model model, @ModelAttribute UserBindingModel userBindingModel) {
		
		if (!userBindingModel.getPassword().equals(userBindingModel.getConfirmPassword())) {
			// TODO: Add error message to be displayed
			model.addAttribute("view", "user/register");
			return "base-layout";
		}
		
		this.userService.createUser(userBindingModel);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginGet(Model model) {
		model.addAttribute("view", "user/login");
		return "base-layout";
	}
	
	@PostMapping("/login")
	public String loginPost(Model model) {
		return null;
	}
	
}
