package com.sotille.model;

import org.springframework.data.annotation.Id;

import com.google.gson.Gson;

public class Category {

	@Id
	private String id;

	private String description;

	public Category(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
