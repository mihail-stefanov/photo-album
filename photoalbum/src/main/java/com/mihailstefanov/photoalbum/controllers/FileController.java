package com.mihailstefanov.photoalbum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mihailstefanov.photoalbum.entities.File;
import com.mihailstefanov.photoalbum.repositories.FileRepository;

public class FileController {
	
	private final FileRepository fileRepository;

	@Autowired
	public FileController(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}
	
	@GetMapping("/photo/file/{id}")
	public ResponseEntity<byte[]> getPhoto(@PathVariable long id) {
		File fileEntity = fileRepository.getOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(fileEntity.getContentType()));
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(fileEntity.getFile(), headers, HttpStatus.OK);
		return responseEntity;
	}

}
