package com.mihailstefanov.photoalbum.bindingModels;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public class PhotoBindingModel {

	@NotNull
	@Length(min = 1, max = 30, message = "The photo name must be between 1 and 30 characters long.")
	private String name;

	@NotNull
	@Length(min = 1, max = 140, message = "The photo description must be between 1 and 140 characters long.")
	private String description;

	@NotNull
	private MultipartFile file;

	public PhotoBindingModel(String name, String description, MultipartFile file) {
		this.name = name;
		this.description = description;
		this.file = file;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
