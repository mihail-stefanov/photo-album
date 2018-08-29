package com.mihailstefanov.photoalbum.bindingModels;

import javax.validation.constraints.NotNull;

public class CommentBindingModel {

	@NotNull
	private String text;

	public CommentBindingModel(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
