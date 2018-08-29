package com.mihailstefanov.photoalbum.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mihailstefanov.photoalbum.entities.File;
import com.mihailstefanov.photoalbum.entities.Photo;
import com.mihailstefanov.photoalbum.entities.User;
import com.mihailstefanov.photoalbum.repositories.FileRepository;
import com.mihailstefanov.photoalbum.repositories.PhotoRepository;
import com.mihailstefanov.photoalbum.repositories.UserRepository;

@Controller
public class HomeController {
	
	private final PhotoRepository photoRepository;
	private final FileRepository fileRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public HomeController(PhotoRepository photoRepository, FileRepository fileRepository, UserRepository userRepository) {
		this.photoRepository = photoRepository;
		this.fileRepository = fileRepository;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/home")
	public String home(Model model, Principal principal) {
		String username = principal.getName();
		User user = this.userRepository.findByUsername(username).orElse(null);
		List<Photo> photos = this.photoRepository.findByUser_Id(user.getId());
		model.addAttribute("view", "user/home");
		model.addAttribute("username", username);
		model.addAttribute("photos", photos);
		return "base-layout";
	}
	
	@GetMapping("/test")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String test(Model model, Principal principal) {
		model.addAttribute("view", "user/test");
		return "base-layout";
	}
}
