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

import com.hyundai.pms.entity.ProjectManagerTransaction;
import com.hyundai.pms.entity.ProjectReassignDTO;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.ProjectManagerTransactionService;

@CrossOrigin
@RequestMapping("/project")
@RestController
public class ProjectManagerTransactionController {
	
	@Autowired
	private ProjectManagerTransactionService ps;
	
	@GetMapping("/getAllProjectManagerTransaction")
	public Response getAllProjectManagerTransaction() {
		List<Map<String, Object>> list = ps.getAllProjectManagerTransaction();
		return new Response(1, "Success", list);		
	}
	
	@GetMapping("/getProjectManagerTransaction/{id}")
	public Response getProjectManagerTransactionById(@PathVariable int id) {
		Optional<ProjectManagerTransaction> pmt= ps.getProjectManagerTransactionById(id);
		return new Response(1, "Success", pmt);		
	}
	
	@PostMapping("/addProjectManagerTransaction")
	public Response addProjectManagerTransaction(@RequestBody ProjectManagerTransaction pmt) {
		ps.addProjectManagerTransaction(pmt);
		return new Response(1, "Success", pmt);		
	}
	
	@PutMapping("/updateProjectManagerTransaction")
	public Response updateProjectManagerTransaction(@RequestBody ProjectManagerTransaction pmt) {
		ps.updateProjectManagerTransaction(pmt);
		return new Response(1, "Success", pmt);		
	}
	
	@DeleteMapping("/deleteProjectManagerTransaction/{id}")
	public Response deleteProjectManagerTransaction(@PathVariable int id) {
		ps.deleteProjectManagerTransaction(id);
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

}
