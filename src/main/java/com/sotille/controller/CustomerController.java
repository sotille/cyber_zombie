 package com.sotille.controller;
   
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sotille.error.CustomErrorType;
import com.sotille.model.Customer;
import com.sotille.repository.CategoryRepository;
import com.sotille.repository.CustomerRepository;

  @RestController
  public class CustomerController {
	  
    public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/customer/tt", method = RequestMethod.GET)
    public String createInitialData() {
		
		this.customerRepository.deleteAll();

		// save a couple of customers
		this.customerRepository.save(new Customer("Alice","Pedreiro","55.3","8.4","0","0", categoryRepository.findByDescription("Pedreiro").get(0)));
		this.customerRepository.save(new Customer("Bob","Encanador","55.3","8.4","0","0",categoryRepository.findByDescription("Encanador").get(0)));
				
        return this.customerRepository.findAll().toString();
    }    
	
    // -------------------Retrieve All Customers---------------------------------------------
    @RequestMapping(value = "/customer/", method = RequestMethod.GET)
    public String listAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            return "No customers Found";
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return customers.toString();
    }
 
    // -------------------Retrieve Single Customer------------------------------------------
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public String getCustomer(@PathVariable("id") String id) {
        logger.info("Fetching Customer with id {}", id);
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            logger.error("Customer with id {} not found.", id);
            return "Customer with id {} not found.";
        }
        return customer.toString();
    }
 
    // -------------------Create a Customer-------------------------------------------
    @RequestMapping(value = "/customer/", method = RequestMethod.POST)
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Customer : {}", customer);
 
//        if (customerRepository.isCustomerExist(customer)) {
//            logger.error("Unable to create. A Customer with name {} already exist", customer.getName());
//            return new ResponseEntity(new CustomErrorType("Unable to create. A Customer with name " + 
//            customer.getFirstName() + " already exist."),HttpStatus.CONFLICT);
//        }
        
        customerRepository.save(customer);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/customer/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a Customer ------------------------------------------------
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
        logger.info("Updating Customer with id {}", id);
 
        Customer currentCustomer = customerRepository.findById(id);
 
        if (currentCustomer == null) {
            logger.error("Unable to update. Customer with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. Customer with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentCustomer.setFirstName(customer.getFirstName());
        currentCustomer.setJob(customer.getJob());
        currentCustomer.setAmount(customer.getAmount());
 
        customerRepository.save(currentCustomer);
        return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
    }
 
    // ------------------- Delete a Customer-----------------------------------------
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") String id) {
        logger.info("Fetching & Deleting Customer with id {}", id);
 
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            logger.error("Unable to delete. Customer with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Customer with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        
        customerRepository.delete(customer);
        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Customers-----------------------------
    @RequestMapping(value = "/customer/", method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteAllCustomers() {
        logger.info("Deleting All Customers");
 
        customerRepository.deleteAll();
        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }
  }