package com.hyundai.pms.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.CustomerMaster;
import com.hyundai.pms.entity.FiltersDTO;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.CustomerRepository;
import com.hyundai.pms.webModel.PaginationWebModel;

@Service
public class CustomerService {
	
	
	@Autowired
	CustomerRepository customerrepository;
	
	 public Response saveCustomer(CustomerMaster customer) {
		   validateCustomer(customer);
	         customerrepository.save(customer);
	         return new Response(1,"success",customer);
	    }

	 public Response getAllCustomers(PaginationWebModel paginationWebModel) {
		Map<String, Object> response = null;

		try {
			Pageable pageable = PageRequest.of(paginationWebModel.getPageNo(), paginationWebModel.getPageSize());
			
			var page = customerrepository.getAllCustomers(pageable,paginationWebModel.getSearchKey());

			response = new HashMap<>();

			response.put("count", page.getTotalElements());
			response.put("content", page.getContent());

			return new Response(1, "success", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(-1, "failed", "");
	}
	 
	 public List<CustomerMaster> getAllCustomerList() {
		 return customerrepository.findAll();
	 }

	 public Optional<CustomerMaster> getCustomerById(Long customerId) {
	        return customerrepository.findById(customerId);
	  }

	 public Response updateCustomer(CustomerMaster updatedCustomer) {
	        if (updatedCustomer!=null) {
	            updatedCustomer.setCustomerId(updatedCustomer.getCustomerId());
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