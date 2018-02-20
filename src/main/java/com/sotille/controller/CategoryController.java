 package com.sotille.controller;
   
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sotille.model.Category;
import com.sotille.repository.CategoryRepository;

  @RestController
  @RequestMapping("/category")
  public class CategoryController {

	  
	@Autowired
	private CategoryRepository repository;
	  
	@RequestMapping(value = "kk", method = RequestMethod.GET)
    public String listaLivros() {
          return "{kk:aa}";
    }  
	
	
	@RequestMapping(value = "tt", method = RequestMethod.GET)
    public String listaCustomer() {
		
		this.repository.deleteAll();

		// save a couple of categories
		this.repository.save(new Category("Pedreiro"));
		this.repository.save(new Category("Encanador"));
				
        return this.repository.findAll().toString();
    }    
  }