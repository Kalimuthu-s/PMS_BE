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
import com.hyundai.pms.webModel.PaginationWebModel;

@RestController
@RequestMapping("/customer")
@CrossOrigin(value = "http://localhost:4200/")
public class CustomerController {

	@Autowired
	private CustomerService customerservice;

	@PostMapping("/addCustomer")
	public Response addCustomer(@RequestBody CustomerMaster customer) {
		customerservice.saveCustomer(customer);
		return new Response(1, "Success", customer);
	}

	@PostMapping("/getAllCustomer")
	public Response getAllCustomers(@RequestBody PaginationWebModel paginationWebModel) {
		return customerservice.getAllCustomers(paginationWebModel);
	}
	
	@GetMapping("/getAllCustomerList")
	public Response getAllCustomerList() {
		List<CustomerMaster> clist= customerservice.getAllCustomerList();
		return new Response(1, "Success", clist);
	}


	@GetMapping("/getCustomerById/{id}")
	public Response getCustomerById(@PathVariable Long id) {
		Optional<CustomerMaster> cust = customerservice.getCustomerById(id);
		return new Response(1, "Success", cust);
	}

	@PutMapping("/updateCustomer/{id}")
	public Response updateCustomer(@PathVariable Long id, @RequestBody CustomerMaster updatedCustomer) {
		customerservice.updateCustomer(id, updatedCustomer);
		return new Response(1, "Success", updatedCustomer);
	}

	@DeleteMapping("/deleteCustomer/{id}")
	public Response deleteCustomer(@PathVariable Long id) {
		customerservice.deleteCustomer(id);
		return new Response(1, "Success", id);
	}

}