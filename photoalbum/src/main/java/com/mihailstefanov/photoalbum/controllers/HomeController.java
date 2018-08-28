package com.mihailstefanov.photoalbum.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mihailstefanov.photoalbum.entities.File;
import com.mihailstefanov.photoalbum.repositories.FileRepository;
import com.mihailstefanov.photoalbum.repositories.PhotoRepository;

@Controller
public class HomeController {
	
	private final PhotoRepository photoRepository;
	private final FileRepository fileRepository;
	
	@Autowired
	public HomeController(PhotoRepository photoRepository, FileRepository fileRepository) {
		this.photoRepository = photoRepository;
		this.fileRepository = fileRepository;
	}
	
	@GetMapping("/")
	public String album(Model model) {
		List<File> allFiles = this.fileRepository.findAll();
		model.addAttribute("view", "photo/album");
		model.addAttribute("files", allFiles);
		return "base-layout";
	}
	
	@GetMapping("/home")
	public String home(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("view", "user/home");
		model.addAttribute("name", name);
		return "base-layout";
	}
	
	@GetMapping("/test")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String test(Model model, Principal principal) {
		model.addAttribute("view", "user/test");
		return "base-layout";
	}
}
