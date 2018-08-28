package com.mihailstefanov.photoalbum.bindingModels;

public class UserRegisterBindingModel {
	private String username;
	private String password;
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
