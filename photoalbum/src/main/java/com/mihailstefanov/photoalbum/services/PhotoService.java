package com.mihailstefanov.photoalbum.services;

import java.io.IOException;
import java.security.Principal;

import org.springframework.web.multipart.MultipartFile;

import com.mihailstefanov.photoalbum.entities.Photo;

public interface PhotoService {
	void addPhoto(String name, String description, MultipartFile file, Principal principal) throws IOException;
	void deletePhoto(Photo photo);
}
