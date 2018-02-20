 package com.sotille.controller;
   
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

  @RestController
  public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String listaCustomer() {				
        return "Welcome to the cyberZombie Project";
    }    
}