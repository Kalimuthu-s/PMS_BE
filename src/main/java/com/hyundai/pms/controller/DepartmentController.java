package com.hyundai.pms.controller;

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
import com.hyundai.pms.webModel.PaginationWebModel;

@RestController
@RequestMapping("/department")
@CrossOrigin(value = "http://localhost:4200/")
public class DepartmentController {

	private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentservice;

	@PostMapping("/getall")
	public Response getAll(@RequestBody PaginationWebModel paginationWebModel) {
		logger.info("Fetching all departments.");
		return departmentservice.getAll(paginationWebModel);
	}

	@GetMapping("/getbyid/{depId}")
	public Response getById(@PathVariable int depId) {
		logger.info("Fetching department with ID: {}", depId);
		Optional<DepartmentMaster> dept = departmentservice.getById(depId);
		return new Response(1, "Success", dept);

	}

	@PostMapping("/add")
	public Response adddepartment(@RequestBody DepartmentMaster depbody) {
		logger.info("Adding new department: {}", depbody);
		departmentservice.adddepartment(depbody);
		return new Response(1, "Success", depbody);

	}

	@PutMapping("/update")
	public Response updatedepartment(@RequestBody DepartmentMaster depbody) {
		logger.info("Updating department: {}", depbody);
		departmentservice.updatedepartment(depbody);
		return new Response(1, "Success", depbody);
	}

	@DeleteMapping("/delete/{depId}")
	public Response deletedepartment(@PathVariable int depId) {
		logger.info("Deleting department with ID: {}", depId);
		departmentservice.deletedepartment(depId);
		return new Response(1, "Success", depId);
	}

}