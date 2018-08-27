package com.mihailstefanov.photoalbum.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mihailstefanov.photoalbum.common.factories.RoleFactory;

@Configuration
public class ApplicationBeanConfiguration {
	
	@Bean
	public RoleFactory roleFactory() {
		return new RoleFactory();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
