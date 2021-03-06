package com.mihailstefanov.photoalbum.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mihailstefanov.photoalbum.bindingModels.CommentBindingModel;
import com.mihailstefanov.photoalbum.bindingModels.PhotoBindingModel;
import com.mihailstefanov.photoalbum.entities.File;
import com.mihailstefanov.photoalbum.entities.Photo;
import com.mihailstefanov.photoalbum.repositories.FileRepository;
import com.mihailstefanov.photoalbum.repositories.PhotoRepository;
import com.mihailstefanov.photoalbum.services.CommentService;
import com.mihailstefanov.photoalbum.services.PhotoService;

@Controller
@RequestMapping("/photo")
public class PhotoController {
	
	private final PhotoRepository photoRepository;
	private final FileRepository fileRepository;
	private final CommentService commentService;
	private final PhotoService photoService;
	
	@Autowired
	public PhotoController(PhotoRepository photoRepository, FileRepository fileRepository, CommentService commentService, PhotoService photoService) {
		this.photoRepository = photoRepository;
		this.fileRepository = fileRepository;
		this.commentService = commentService;
		this.photoService = photoService;
	}
	
	@GetMapping("/add")
	public String addPhotoGet(Model model, @ModelAttribute PhotoBindingModel photoBindingModel	 ) {
		model.addAttribute("view", "photo/add");
		return "base-layout";
	}
	
	@PostMapping("/add")
	public String addPhoto(Model model, 
			@RequestParam("name") String name,
			@RequestParam("description") String description, 
			@RequestParam("file") MultipartFile file,
			@Valid @ModelAttribute PhotoBindingModel photoBindingModel, BindingResult bindingResult,
			Principal principal) throws Exception {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("view", "photo/add");
			return "base-layout";
		}

		String contentType = file.getContentType();
		if (!(contentType.equals(MediaType.IMAGE_GIF_VALUE) || contentType.equals(MediaType.IMAGE_JPEG_VALUE) || contentType.equals(MediaType.IMAGE_PNG_VALUE))) {
			bindingResult.addError(new FieldError("photoBindingModel", "file", "The file must be of type GIF, JPEG or PNG."));
			model.addAttribute("view", "photo/add");
			return "base-layout";
		}
		
		this.photoService.addPhoto(name, description, file, principal);
		
		return "redirect:/home";
	}
	
	@GetMapping("/{photoId}") 
	public String viewPhoto(Model model, @PathVariable long photoId, @ModelAttribute CommentBindingModel commentBindingModel) {
		Photo photo = this.photoRepository.findById(photoId).orElse(null);
		model.addAttribute("view", "photo/photo");
		model.addAttribute("photo", photo);
		return "base-layout";
	}
	
	@PostMapping("/{photoId}/comment/add")
	public String addCommentToPhoto(@PathVariable long photoId, Model model, @Valid @ModelAttribute CommentBindingModel commentBindingModel, BindingResult bindingResult, Principal principal) {
		
		if (!bindingResult.hasErrors()) {
			this.commentService.addComment(commentBindingModel, principal, photoId);
			return "redirect:/photo/" + photoId;
		}
		
		Photo photo = this.photoRepository.findById(photoId).orElse(null);
		model.addAttribute("view", "photo/photo");
		model.addAttribute("photo", photo);
		return "base-layout";
	}
	
	@PostMapping("/{photoId}/delete")
	public String deletePhoto(@PathVariable long photoId, Model model, Principal principal) {
		Photo photo = this.photoRepository.findById(photoId).orElse(null);

		if (!principal.getName().equals(photo.getUser().getUsername())) {
			model.addAttribute("view", "user/home");
			return "base-layout";
		}
		
		this.photoService.deletePhoto(photo);
		return "redirect:/home";
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
