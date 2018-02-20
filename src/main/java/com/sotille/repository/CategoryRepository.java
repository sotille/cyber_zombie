package com.sotille.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sotille.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

	List<Category> findByDescription(String description);

}