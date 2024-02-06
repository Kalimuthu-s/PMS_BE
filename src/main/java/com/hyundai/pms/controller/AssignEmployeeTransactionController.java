package com.hyundai.pms.controller;

import java.util.List;
import java.util.Map;
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

import com.hyundai.pms.entity.AssignEmployeeSearchDTO;
import com.hyundai.pms.entity.AssignEmployeeTransaction;
import com.hyundai.pms.entity.EmployeeTransactionDTO;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.AssignEmployeeTransactionService;
import com.hyundai.pms.webModel.PaginationWebModel;

@RestController
@RequestMapping("/assignEmployeeTransaction")
@CrossOrigin(value="http://localhost:4200/")
public class AssignEmployeeTransactionController {
	
	@Autowired
	private AssignEmployeeTransactionService pet;
	
	@PostMapping("/getAllAssignEmployeeTransaction")
	public Response getAllAssignEmployeeTransaction(@RequestBody PaginationWebModel paginationWebModel) {
		return pet.getAllAssignEmployeeTransaction(paginationWebModel);
	}
	
	@GetMapping("/getAllAssignEmployeeTransactionById/{id}")
	public Response getAllAssignEmployeeTransactionById(@PathVariable int id) {
		Optional<AssignEmployeeTransaction> one = pet.getAllAssignEmployeeTransactionById(id);
		return new Response(1, "Success", one);
	}
	
	@PostMapping("/addAssignEmployeeTransaction")
	public Response addAssignEmployeeTransaction(@RequestBody AssignEmployeeTransaction body) {
		pet.addAssignEmployeeTransaction(body);
		return new Response(1, "Success", body);
	}
	
	@PutMapping("/updateAssignEmployeeTransaction")
	public Response updateAssignEmployeeTransaction(@RequestBody AssignEmployeeTransaction body) {
		pet.updateAssignEmployeeTransaction(body);
		return new Response(1, "Success", body);
	}
	
	@DeleteMapping("/deleteAssignEmployeeTransaction/{id}")
	public Response deleteAssignEmployeeTransaction(@PathVariable int id) {
		String dpet = pet.deleteAssignEmployeeTransaction(id);
		return new Response(1, dpet, id);
	}
	
	@GetMapping("/getAssignedEmployees")
	public Response getAssignedEmployees() {
		List<Map<String, Object>> list = pet.getAssignedEmployees();
		return new  Response(1, "Success", list);
	}
	
	@PostMapping("/getEmployeesByFilters")
	public Response getEmployeesByFilters(@RequestBody AssignEmployeeSearchDTO dto) {
		List<Map<String, Object>> list = pet.getEmployeesByFilters(dto);
		return new Response(1, "Success", list);
	}
	
	@PostMapping("/addMultipleEmployeeTransaction")
	public Response addMultipleEmployeeTransaction(@RequestBody EmployeeTransactionDTO emp) {
		String response = pet.addMultipleEmployeeTransaction(emp);
		return new Response(1, response, emp);
	}

}
