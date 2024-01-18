package com.hyundai.pms.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.CustomerMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.CustomerRepository;

@Service
public class CustomerService {
	
	
	@Autowired
	CustomerRepository customerrepository;
	
	 public Response saveCustomer(CustomerMaster customer) {
		   validateCustomer(customer);
	         customerrepository.save(customer);
	         return new Response(1,"success",customer);
	    }

	 public List<CustomerMaster> getAllCustomers() {
	        return (List<CustomerMaster>) customerrepository.findAll();
	    }

	 public Optional<CustomerMaster> getCustomerById(Long customerId) {
	        return customerrepository.findById(customerId);
	  }

	 public Response updateCustomer(Long customerId, CustomerMaster updatedCustomer) {
	        if (customerrepository.existsById(customerId)) {
	            updatedCustomer.setCustomerId(customerId);
	            customerrepository.save(updatedCustomer);
	            return new Response(1,"success",updatedCustomer);
	        } else {
	            return new Response(2,"error",updatedCustomer);
	        }
	    }
	 
	 
	 
	 public String deleteCustomer(Long customerId) {
		 if (customerrepository.existsById(customerId)) {
	        customerrepository.deleteById(customerId);
	        return "Deleted Successfully";
	        }
		 else {
			 return "Failed To Delete";
		 }
	    }
	 
	 
	 private void validateCustomer(CustomerMaster customer) {
	        if (customer == null) {
	            throw new IllegalArgumentException("Customer object cannot be null");
	        }

	        validateStringField("Customer name", customer.getCustomerName());
	        validateStringField("Customer location", customer.getCustomerLocation());
	        validatePositiveOrZero("Number of projects completed", customer.getNoOfProjectsCompleted());
	        validateStringField("Status", customer.getStatus());
	    }

	    private void validateStringField(String fieldName, String value) {
	        if (value == null || value.trim().isEmpty()) {
	        }
	    }

	    private void validatePositiveOrZero(String fieldName, int value) {
	        if (value < 0) {
	            throw new IllegalArgumentException(fieldName + " should be a positive number or zero");
	        }
	    }
	
	

}