package com.mihailstefanov.photoalbum.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mihailstefanov.photoalbum.bindingModels.UserBindingModel;
import com.mihailstefanov.photoalbum.common.factories.RoleFactory;
import com.mihailstefanov.photoalbum.entities.Role;
import com.mihailstefanov.photoalbum.entities.User;
import com.mihailstefanov.photoalbum.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final RoleFactory roleFactory;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, RoleFactory roleFactory) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleFactory = roleFactory;
	}

	@Override
	public boolean createUser(UserBindingModel userBindingModel) {
		User user = this.modelMapper.map(userBindingModel, User.class);
		user.setPassword(this.bCryptPasswordEncoder.encode(userBindingModel.getPassword()));
		Set<Role> authorities = new HashSet<>();
		
		if (this.userRepository.findAll().isEmpty()) {
			authorities.add(this.roleFactory.createRole("ADMIN"));
			authorities.add(this.roleFactory.createRole("USER"));
		} else {
			authorities.add(this.roleFactory.createRole("USER"));
		}
		
		user.setAuthorities(authorities);
		return this.userRepository.saveAndFlush(user) != null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username).orElse(null);
		
		if (user == null) {
			throw new UsernameNotFoundException("The username does not exist.");
		}
		
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

}
