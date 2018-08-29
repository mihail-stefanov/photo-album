package com.mihailstefanov.photoalbum.services;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.mihailstefanov.photoalbum.bindingModels.UserRegisterBindingModel;
import com.mihailstefanov.photoalbum.entities.User;

public interface UserService extends UserDetailsService {
	boolean createUser(UserRegisterBindingModel userRegisterBindingModel);
	List<User> getAllUsers();
	@PreAuthorize("hasRole('ADMIN')")
	void delete();
	boolean checkAlreadyExists(UserRegisterBindingModel userRegisterBindingModel);
}
