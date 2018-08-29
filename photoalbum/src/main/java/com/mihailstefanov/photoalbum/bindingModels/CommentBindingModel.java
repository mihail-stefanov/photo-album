package com.mihailstefanov.photoalbum.bindingModels;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CommentBindingModel {

	@NotNull
	@Length(min = 1, max = 140, message = "The comment cannot be empty and it cannot exceed 140 characters.")
	private String text;

	public CommentBindingModel(String text) {
		this.text = text;
	}
	
	public CommentBindingModel() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
