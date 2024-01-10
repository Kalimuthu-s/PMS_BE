package com.hyundai.pms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.CustomerMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.CustomerService;


@RestController
@RequestMapping("/Customer")
@CrossOrigin(value="http://localhost:4200/")
public class CustomerController {

	
	@Autowired
	private CustomerService customerservice;
	
	 @PostMapping("/addCustomer")
	    public Response addCustomer(@RequestBody CustomerMaster customer) {
	        return customerservice.saveCustomer(customer);
	    }
	 
	 
	 
	 @GetMapping("/getAllCustomer")
	    public List<CustomerMaster> getAllCustomers() {
	        return customerservice.getAllCustomers();
	    }
	 
	 
	 
	  @GetMapping("/getCustomerById/{id}")
	    public Optional<CustomerMaster> getCustomerById(@PathVariable Long id) {
	        return customerservice.getCustomerById(id);
	    }
	  
	  
	  
	  @PutMapping("/updateCustomer/{id}")
	    public Response updateCustomer(@PathVariable Long id, @RequestBody CustomerMaster updatedCustomer) {
	        return customerservice.updateCustomer(id, updatedCustomer);
	    }
	  
	  
	  
	  @DeleteMapping("/deleteCustomer/{id}")
	    public String deleteCustomer(@PathVariable Long id) {
		  	return  customerservice.deleteCustomer(id);
	    }

}