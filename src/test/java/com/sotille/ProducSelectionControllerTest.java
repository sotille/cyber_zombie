package com.sotille;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sotille.model.Customer;
import com.sotille.repository.CustomerRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ProducSelectionControllerTest
{

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void setup()
    {
       
    }

    @Test
    public void testValidCustomerId() throws IllegalArgumentException
    {
    	
    	List<Customer> customers = customerRepository.findAll();
    	
        String customerName = "Alice";
       
        Assert.assertEquals(customerName, customers.get(0).getFirstName());
    
        customerName = String.valueOf("Bob");
        
        Assert.assertEquals(customerName, customers.get(1).getFirstName());
    }

}