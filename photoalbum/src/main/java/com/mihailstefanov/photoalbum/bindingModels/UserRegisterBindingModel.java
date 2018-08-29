package com.mihailstefanov.photoalbum.bindingModels;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {
	
	@NotNull
	@Pattern(regexp = "^\\S{2,30}$", message = "The username must be between 2 and 30 characters long. It cannot contain spaces.")
	private String username;
	
	@NotNull
	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])\\S{8,30}$", message = "The password must be between 8 and 30 characters long and must contain a small letter, a capital letter and a number.")
	private String password;
	
	@NotNull
	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])\\S{8,30}$", message = "The password must be between 8 and 30 characters long and must contain a small letter, a capital letter and a number.")
	private String confirmPassword;
	
	public UserRegisterBindingModel(String username, String password, String confirmPassword) {
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	public UserRegisterBindingModel() {
	}
		
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
