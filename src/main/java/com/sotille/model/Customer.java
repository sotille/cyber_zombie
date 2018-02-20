package com.sotille.model;
import org.springframework.data.annotation.Id;

import com.google.gson.Gson;

public class Customer {

	@Id
	private String id;

	private String firstName;
	
	private String job;
	
	private double amount;
	
	private double rate;
	
	private long latitude;

	private long longitude;
	
	private String contato;
		
	private Category category; 


	public Customer() {
	}

	public Customer(String firstName) {
		this.firstName = firstName;
	}
	
	public Customer(String firstName, String job, String amount, String rate,String latitude, String longitude) {
		this.firstName = firstName;
		this.job = job;
		this.amount = Double.parseDouble(amount);
		this.rate  = Double.parseDouble(rate);
		this.latitude = Long.parseLong(latitude);
		this.longitude = Long.parseLong(longitude);
	}
	
	
	public Customer(String firstName, String job, String amount, String rate,String latitude, String longitude, Category category) {
		this.firstName = firstName;
		this.job = job;
		this.amount = Double.parseDouble(amount);
		this.rate  = Double.parseDouble(rate);
		this.latitude = Long.parseLong(latitude);
		this.longitude = Long.parseLong(longitude);
		this.category = category;
	}
	

	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

}