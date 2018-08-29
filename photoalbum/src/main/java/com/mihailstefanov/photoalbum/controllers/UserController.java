package com.mihailstefanov.photoalbum.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public String registerGet(Model model, @ModelAttribute UserRegisterBindingModel userRegisterBindingModel) {
		model.addAttribute("view", "user/register");
		return "base-layout";
	}
	
	@PostMapping("/register")
	@PreAuthorize("isAnonymous()")
	public String registerPost(Model model, @Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("view", "user/register");
			return "base-layout";
		}
		
		if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
			bindingResult.addError(new FieldError("userRegisterBindingModel", "confirmPassword", "The password must be the same in both inputs."));
			model.addAttribute("view", "user/register");
			return "base-layout";
		}
		
		if (this.userService.checkAlreadyExists(userRegisterBindingModel)) {
			bindingResult.addError(new FieldError("userRegisterBindingModel", "username", "The user already exists."));
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
			model.addAttribute("error", "The username and the password must be correct.");
		}
		
		model.addAttribute("view", "user/login");
		return "base-layout";
	}
}
