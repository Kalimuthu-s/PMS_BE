package com.hyundai.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.ProjectManagerTransaction;
import com.hyundai.pms.service.ProjectManagerTransactionService;

@CrossOrigin
@RestController
public class ProjectManagerTransactionController {
	
	@Autowired
	private ProjectManagerTransactionService ps;
	
	@GetMapping("/getAllProjectManagerTransaction")
	public List<ProjectManagerTransaction> getAllProjectManagerTransaction() {
		List<ProjectManagerTransaction> list = ps.getAllProjectManagerTransaction();
		return list;
	}
	
	@GetMapping("/getAllProjectManagerTransaction/{id}")
	public ProjectManagerTransaction getProjectManagerTransactionById(@PathVariable int id) {
		ProjectManagerTransaction pmt=ps.getProjectManagerTransactionById(id);
		return pmt;
		
	}
	
	@PostMapping("/addProjectManagerTransaction")
	public ProjectManagerTransaction addProjectManagerTransaction(@RequestBody ProjectManagerTransaction pmt) {
		return ps.addProjectManagerTransaction(pmt);
	}
	
	@PutMapping("/updateProjectManagerTransaction")
	public ProjectManagerTransaction updateProjectManagerTransaction(@RequestBody ProjectManagerTransaction pmt) {
		return ps.updateProjectManagerTransaction(pmt);
	}
	
	@DeleteMapping("/deleteProjectManagerTransaction/{id}")
	public String deleteProjectManagerTransaction(@PathVariable int id) {
		ps.deleteProjectManagerTransaction(id);
		return "Project Manager Transaction Deleted";
	}
	
	@GetMapping("/getManagerAvaiableProjects/{manager}")
	public List<ProjectManagerTransaction> getManagerAvaiableProjects(@PathVariable String manager) {
		return ps.getManagerAvaiableProjects(manager);
	}
	

}
