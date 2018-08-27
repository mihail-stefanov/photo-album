package com.mihailstefanov.photoalbum.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mihailstefanov.photoalbum.bindingModels.UserBindingModel;
import com.mihailstefanov.photoalbum.entities.User;

public interface UserService extends UserDetailsService {
	boolean createUser(UserBindingModel userBindingModel);
	List<User> getAllUsers();
}
