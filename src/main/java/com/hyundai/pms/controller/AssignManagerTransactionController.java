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

import com.hyundai.pms.entity.AssignManagerTransaction;
import com.hyundai.pms.entity.ProjectReassignDTO;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.AssignManagerTransactionService;
import com.hyundai.pms.webModel.PaginationWebModel;

@CrossOrigin
@RequestMapping("/assignProjectTransaction")
@RestController
public class AssignManagerTransactionController {
	
	@Autowired
	private AssignManagerTransactionService ps;
	
	@PostMapping("/getAllAssignManagerTransaction")
	public Response getAllAssignManagerTransaction(@RequestBody PaginationWebModel paginationWebModel) {
		return ps.getAllAssignManagerTransaction(paginationWebModel);	
	}
	
	@GetMapping("/getAssignManagerTransaction/{id}")
	public Response getAssignManagerTransactionById(@PathVariable int id) {
		Optional<AssignManagerTransaction> pmt= ps.getAssignManagerTransactionById(id);
		return new Response(1, "Success", pmt);
	}
	
	@PostMapping("/addAssignManagerTransaction")
	public Response addAssignManagerTransaction(@RequestBody AssignManagerTransaction pmt) {
		ps.addAssignManagerTransaction(pmt);
		return new Response(1, "Success", pmt);
	}
	
	@PutMapping("/updateAssignManagerTransaction")
	public Response updateAssignManagerTransaction(@RequestBody AssignManagerTransaction pmt) {
		ps.updateAssignManagerTransaction(pmt);
		return new Response(1, "Success", pmt);		
	}
	
	@DeleteMapping("/deleteAssignManagerTransaction/{id}")
	public Response deleteAssignManagerTransaction(@PathVariable int id) {
		ps.deleteAssignManagerTransaction(id);
		return new Response(1, "Success", id);		
		}
	
	@GetMapping("/getManagerAvailableProjects/{manager}")
	public Response getManagerAvaiableProjects(@PathVariable String manager) {
		List<Map<String, Object>> list =ps.getManagerAvaiableProjects(manager);
		return new Response(1, "Success", list);		
	}
	
	@PostMapping("/reassignManagerToProjects")
	public Response reassignManagerToProjects(@RequestBody ProjectReassignDTO data) { 
		ps.reassignManagerToProjects(data);
		return new Response(1, "Success", data);
	}
	
	@GetMapping("/getAllAssignManagerWithoutPagination")
	public Response getAllProjectManagerWithoutPagination() {
		List<Map<String, Object>> list = ps.getAllProjectManagerWithoutPagination();
		System.err.println("----------------> "+list.toString());
		return new Response(1, "Success", list);
	}

}
