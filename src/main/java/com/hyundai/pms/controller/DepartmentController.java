package com.hyundai.pms.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

import com.hyundai.pms.entity.Department;
import com.hyundai.pms.pagination.PaginationRequest;
import com.hyundai.pms.response.Response;
import com.hyundai.pms.service.DepartmentService;

@RestController
@RequestMapping("/department")
@CrossOrigin(value="http://localhost:4200/")
public class DepartmentController {
	
	private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentservice;
	

	
	@PostMapping("/getall")
	public ResponseEntity<Map<String, Object>> getAll(@RequestBody PaginationRequest paginationRequest) {
	    logger.info("Fetching all departments.");

	    Pageable pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize());
	    Page<Department> result = departmentservice.getAll(pageable);

	    Map<String, Object> response = new HashMap<>();
	    response.put("departmentList", result.getContent());
	    response.put("totalCount", result.getTotalElements());

	    return ResponseEntity.ok(response);
	}

	
	@GetMapping("/getbyid/{depId}")
	public Optional<Department> getById(@PathVariable int depId) {
		logger.info("Fetching department with ID", depId);
		return departmentservice.getById(depId);
	}
	
	@PostMapping("/add")
	public Response adddepartment(@RequestBody Department depbody) {
		logger.info("Adding new department", depbody);
		return departmentservice.addDepartment(depbody);
	}
	
	@PutMapping("/update")
	public Response updatedepartment(@RequestBody Department depbody) {
		 logger.info("Updating department", depbody);
		return departmentservice.updatedepartment(depbody);
	}
	
	@DeleteMapping("/delete/{depId}")
	public Response deletedepartment(@PathVariable int depId) {
		logger.info("Deleting department with ID", depId);
		return departmentservice.deletedepartment(depId);
	}

}
