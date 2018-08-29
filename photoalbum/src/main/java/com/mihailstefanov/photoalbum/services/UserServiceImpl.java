package com.mihailstefanov.photoalbum.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mihailstefanov.photoalbum.bindingModels.UserRegisterBindingModel;
import com.mihailstefanov.photoalbum.entities.Role;
import com.mihailstefanov.photoalbum.entities.User;
import com.mihailstefanov.photoalbum.enums.RoleAuthority;
import com.mihailstefanov.photoalbum.repositories.RoleRepository;
import com.mihailstefanov.photoalbum.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public boolean createUser(UserRegisterBindingModel userRegisterBindingModel) {
		User user = this.modelMapper.map(userRegisterBindingModel, User.class);
		user.setPassword(this.bCryptPasswordEncoder.encode(userRegisterBindingModel.getPassword()));
		Set<Role> authorities = new HashSet<>();
		
		Optional<Role> roleAdmin = this.roleRepository.findOptionalByAuthority(RoleAuthority.ADMIN);
		Optional<Role> roleUser = this.roleRepository.findOptionalByAuthority(RoleAuthority.USER);
		
		Role adminRoleToAdd = roleAdmin.isPresent() ? roleAdmin.get() : new Role(RoleAuthority.ADMIN);
		Role userRoleToAdd = roleUser.isPresent() ? roleUser.get() : new Role(RoleAuthority.USER);
		
		if (this.userRepository.findAll().isEmpty()) {
			authorities.add(adminRoleToAdd);
		} 
		
		authorities.add(userRoleToAdd);
		user.setAuthorities(authorities);
		return this.userRepository.save(user) != null;
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

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkAlreadyExists(UserRegisterBindingModel userRegisterBindingModel) {
		User user = this.userRepository.findByUsername(userRegisterBindingModel.getUsername()).orElse(null);
		return user != null;
	}

}
