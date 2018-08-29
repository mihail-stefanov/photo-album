package com.mihailstefanov.photoalbum.services;

import java.security.Principal;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mihailstefanov.photoalbum.bindingModels.CommentBindingModel;
import com.mihailstefanov.photoalbum.entities.Comment;
import com.mihailstefanov.photoalbum.entities.Photo;
import com.mihailstefanov.photoalbum.entities.User;
import com.mihailstefanov.photoalbum.repositories.CommentRepository;
import com.mihailstefanov.photoalbum.repositories.PhotoRepository;
import com.mihailstefanov.photoalbum.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final PhotoRepository photoRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PhotoRepository photoRepository, ModelMapper modelMapper) {
		this.commentRepository = commentRepository;
		this.userRepository = userRepository;
		this.photoRepository = photoRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void addComment(CommentBindingModel commentBindingModel, Principal principal, long photoId) {
		Comment comment = this.modelMapper.map(commentBindingModel, Comment.class);
		User user = this.userRepository.findByUsername(principal.getName()).orElse(null);
		Photo photo = this.photoRepository.findById(photoId).orElse(null);
		comment.setUser(user);
		comment.setPhoto(photo);
		comment.setDatePosted(new Date());
		this.commentRepository.saveAndFlush(comment);
	}
	
}
