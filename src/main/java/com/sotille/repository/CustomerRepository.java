package com.sotille.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sotille.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	Customer findByFirstName(String firstName);
	
	Customer findById(String _id);

	List<Customer> findByJob(String job);

}