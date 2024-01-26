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

import com.hyundai.pms.entity.ProjectEmployeeTransaction;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.ProjectEmployeeTransactionService;

@RestController
@RequestMapping("/projectEmployeeTransaction")
@CrossOrigin(value="http://localhost:4200/")
public class ProjectEmployeeTransactionController {
	
	@Autowired
	private ProjectEmployeeTransactionService pet;
	
	@GetMapping("/getAllProjectEmployeeTransaction")
	public Response getAllProjectEmployeeTransaction() {
		List<ProjectEmployeeTransaction> list = pet.getAllProjectEmployeeTransaction();
		return new Response(1, "Success", list);
	}
	
	@GetMapping("/getAllProjectEmployeeTransactionById/{id}")
	public Response getAllProjectEmployeeTransactionById(@PathVariable int id) {
		Optional<ProjectEmployeeTransaction> one = pet.getAllProjectEmployeeTransactionById(id);
		return new Response(1, "Success", one);
	}
	
	@PostMapping("/addProjectEmployeeTransaction")
	public Response addProjectEmployeeTransaction(@RequestBody ProjectEmployeeTransaction body) {
		pet.addProjectEmployeeTransaction(body);
		return new Response(1, "Success", pet);
	}
	
	@PutMapping("/updateProjectEmployeeTransaction")
	public Response updateProjectEmployeeTransaction(@RequestBody ProjectEmployeeTransaction body) {
		pet.updateProjectEmployeeTransaction(body);
		return new Response(1, "Success", pet);
	}
	
	@DeleteMapping("/deleteProjectEmployeeTransaction/{id}")
	public Response deleteProjectEmployeeTransaction(@PathVariable int id) {
		String dpet = pet.deleteProjectEmployeeTransaction(id);
		return new Response(1, dpet, id);
	}

}
