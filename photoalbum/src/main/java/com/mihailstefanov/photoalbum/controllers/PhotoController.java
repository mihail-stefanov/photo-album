package com.mihailstefanov.photoalbum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mihailstefanov.photoalbum.bindingModels.PhotoBindingModel;
import com.mihailstefanov.photoalbum.repositories.PhotoRepository;

@Controller
public class PhotoController {
	
	private final PhotoRepository photoRepository;
	
	@Autowired
	public PhotoController(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}
	
	// TODO: Convert end-points to include user names or IDs
	
	@GetMapping("/photo/add")
	public String addPhotoGet(Model model) {
		model.addAttribute("view", "photo/add");
		return "base-layout";
	}
	
	@PostMapping("/photo/add")
	public String addPhotoPost(Model model, PhotoBindingModel photoBindingModel) {
		// TODO: Implement
		return "redirect:/";
	}

}
