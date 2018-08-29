package com.mihailstefanov.photoalbum.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mihailstefanov.photoalbum.entities.File;
import com.mihailstefanov.photoalbum.entities.Photo;
import com.mihailstefanov.photoalbum.entities.User;
import com.mihailstefanov.photoalbum.repositories.FileRepository;
import com.mihailstefanov.photoalbum.repositories.PhotoRepository;
import com.mihailstefanov.photoalbum.repositories.UserRepository;

@Controller
@RequestMapping("/photo")
public class PhotoController {
	
	private final PhotoRepository photoRepository;
	private final FileRepository fileRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public PhotoController(PhotoRepository photoRepository, FileRepository fileRepository, UserRepository userRepository) {
		this.photoRepository = photoRepository;
		this.fileRepository = fileRepository;
		this.userRepository = userRepository;
	}
	
	// TODO: Convert end-points to include user names or IDs
	
	@GetMapping("/add")
	public String addPhotoGet(Model model) {
		model.addAttribute("view", "photo/add");
		return "base-layout";
	}
	
	@PostMapping("/add")
	public String addPhotoPost(
			@RequestParam("name") String name, 
			@RequestParam("description") String description,
			@RequestParam("file") MultipartFile file,
			Principal principal) throws Exception {
		
		// TODO: Handle Exceptions
		String contentType = file.getContentType();
		if (!(contentType.equals(MediaType.IMAGE_GIF_VALUE) || contentType.equals(MediaType.IMAGE_JPEG_VALUE) || contentType.equals(MediaType.IMAGE_PNG_VALUE))) {
			throw new Exception();
			// TODO: Handle exception
		}
		
		File fileEntity = new File();
		fileEntity.setFile(file.getBytes());
		fileEntity.setContentType(contentType);
		
		User user = this.userRepository.findByUsername(principal.getName()).orElse(null);
		
		Photo photo = new Photo(name, description, fileEntity, user);
		fileEntity.setPhoto(photo);
		fileRepository.saveAndFlush(fileEntity);
		return "redirect:/";
	}
	
	@GetMapping("/browse")
	public String browse(Model model) {
		List<Photo> allPhotos = this.photoRepository.findAll();
		model.addAttribute("view", "photo/browse");
		model.addAttribute("photos", allPhotos);
		return "base-layout";
	}
	
	@GetMapping("/file/{id}")
	public ResponseEntity<byte[]> getPhoto(@PathVariable long id) {
		File fileEntity = fileRepository.getOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(fileEntity.getContentType()));
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(fileEntity.getFile(), headers, HttpStatus.OK);
		return responseEntity;
	}
}
