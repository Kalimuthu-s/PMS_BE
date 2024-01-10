package com.hyundai.pms.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.hyundai.pms.entity.DepartmentMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.DepartmentService;



@RestController
@RequestMapping("/department")
@CrossOrigin(value="http://localhost:4200/")
public class DepartmentController {
	
	private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentservice;
	
	
	
	@GetMapping("/getall")
	public List<DepartmentMaster> getAll(){
		logger.info("Fetching all departments.");
		return departmentservice.getAll();
	}
	
	@GetMapping("/getbyid/{depId}")
	public Optional<DepartmentMaster> getById(@PathVariable int depId) {
		logger.info("Fetching department with ID: {}", depId);
		return departmentservice.getById(depId);
	}
	
	@PostMapping("/add")
	public Response adddepartment(@RequestBody DepartmentMaster depbody) {
		logger.info("Adding new department: {}", depbody);
		return departmentservice.adddepartment(depbody);
	}
	
	@PutMapping("/update")
	public Response updatedepartment(@RequestBody DepartmentMaster depbody) {
		 logger.info("Updating department: {}", depbody);
		return departmentservice.updatedepartment(depbody);
	}
	
	@DeleteMapping("/delete/{depId}")
	public Response deletedepartment(@PathVariable int depId) {
		logger.info("Deleting department with ID: {}", depId);
		return departmentservice.deletedepartment(depId);
	}

}