package com.mihailstefanov.photoalbum.services;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mihailstefanov.photoalbum.entities.File;
import com.mihailstefanov.photoalbum.entities.Photo;
import com.mihailstefanov.photoalbum.entities.User;
import com.mihailstefanov.photoalbum.repositories.FileRepository;
import com.mihailstefanov.photoalbum.repositories.UserRepository;

@Service
public class PhotoServiceImpl implements PhotoService {
	
	private final FileRepository fileRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public PhotoServiceImpl(FileRepository fileRepository, UserRepository userRepository) {
		this.fileRepository = fileRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void addPhoto(String name, String description, MultipartFile file, Principal principal) throws IOException {
		String contentType = file.getContentType();
		File fileEntity = new File();
		fileEntity.setFile(file.getBytes());
		fileEntity.setContentType(contentType);
		
		User user = this.userRepository.findByUsername(principal.getName()).orElse(null);
		
		Photo photo = new Photo(name, description, fileEntity, user);
		fileEntity.setPhoto(photo);
		this.fileRepository.saveAndFlush(fileEntity);
	}

	@Override
	public void deletePhoto(Photo photo) {
		User user = photo.getUser();
		photo.getComments().forEach(comment -> comment.getUser().getComments().remove(comment));
		user.getPhotos().remove(photo);
		this.userRepository.save(user);
	}

}
