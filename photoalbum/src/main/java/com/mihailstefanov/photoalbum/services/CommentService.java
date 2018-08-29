package com.mihailstefanov.photoalbum.services;

import java.security.Principal;

import com.mihailstefanov.photoalbum.bindingModels.CommentBindingModel;

public interface CommentService {
	void addComment(CommentBindingModel commentBindingModel, Principal principal, long photoId);
}
