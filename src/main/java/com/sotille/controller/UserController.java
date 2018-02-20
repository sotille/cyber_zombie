package com.sotille.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// package & imports
 @RestController
 public class UserController {

     @RequestMapping("/users") /* Maps to all HTTP actions by default (GET,POST,..)*/
     public @ResponseBody
     String getUsers() {
         return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
             "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
     }
     
 }