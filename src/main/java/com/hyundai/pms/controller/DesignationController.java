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

import com.hyundai.pms.entity.DesignationMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.DesignationService;



@RestController
@RequestMapping("/designation")
@CrossOrigin(value="http://localhost:4200/")
public class DesignationController {
	
	private final Logger logger = LoggerFactory.getLogger(DesignationController.class);
	
	@Autowired
	private DesignationService designationservice;
	
	@GetMapping("/getall")
	public List<DesignationMaster> getAll(){
		logger.info("Fetching all designations.");
		return designationservice.getAll();
	}
	
	@GetMapping("/getbyid/{desgId}")
	public Optional<DesignationMaster> getById(@PathVariable int desgId) {
		logger.info("Fetching designation with ID: {}", desgId);
		return designationservice.getById(desgId);
	}
	
	@PostMapping("/add")
	public Response adddep(@RequestBody DesignationMaster desgbody) {
		logger.info("Adding new designation: {}", desgbody);
		return designationservice.adddesignation(desgbody);
	}
	
	@PutMapping("/update")
	public Response updatedep(@RequestBody DesignationMaster desgbody) {
		logger.info("Updating designation: {}", desgbody);
		return designationservice.updatedesignation(desgbody);
	}
	
	@DeleteMapping("/delete/{desgId}")
	public Response deletedep(@PathVariable int desgId) {
		logger.info("Deleting designation with ID: {}", desgId);
		return designationservice.deletedesignation(desgId);
	}

}