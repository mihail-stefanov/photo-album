package com.mihailstefanov.photoalbum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mihailstefanov.photoalbum.entities.File;
import com.mihailstefanov.photoalbum.entities.Photo;
import com.mihailstefanov.photoalbum.repositories.FileRepository;
import com.mihailstefanov.photoalbum.repositories.PhotoRepository;

@Controller
public class PhotoController {
	
	private final PhotoRepository photoRepository;
	private final FileRepository fileRepository;
	
	@Autowired
	public PhotoController(PhotoRepository photoRepository, FileRepository fileRepository) {
		this.photoRepository = photoRepository;
		this.fileRepository = fileRepository;
	}
	
	// TODO: Convert end-points to include user names or IDs
	
	@GetMapping("/")
	public String album(Model model) {
		List<File> allFiles = this.fileRepository.findAll();
		model.addAttribute("view", "photo/album");
		model.addAttribute("files", allFiles);
		return "base-layout";
	}
	
	@GetMapping("/photo/add")
	public String addPhotoGet(Model model) {
		model.addAttribute("view", "photo/add");
		return "base-layout";
	}
	
	@PostMapping("/photo/add")
	public String addPhotoPost(
			@RequestParam("name") String name, 
			@RequestParam("description") String description,
			@RequestParam("file") MultipartFile file) throws Exception {
		
		// TODO: Handle Exceptions
		String contentType = file.getContentType();
		if (!(contentType.equals(MediaType.IMAGE_GIF_VALUE) || contentType.equals(MediaType.IMAGE_JPEG_VALUE) || contentType.equals(MediaType.IMAGE_PNG_VALUE))) {
			throw new Exception();
			// TODO: Handle exception
		}
		
		File fileEntity = new File();
		fileEntity.setFile(file.getBytes());
		fileEntity.setContentType(contentType);
		
		Photo photo = new Photo(name, description, fileEntity);
		fileEntity.setPhoto(photo);
		fileRepository.saveAndFlush(fileEntity);
		return "redirect:/";
	}
}
