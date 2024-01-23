package com.hyundai.pms.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.Designation;
import com.hyundai.pms.pagination.PaginationRequest;
import com.hyundai.pms.response.Response;
import com.hyundai.pms.service.DesignationService;

@RestController
@RequestMapping("/designation")
@CrossOrigin(value="http://localhost:4200/")
public class DesignationController {
	
	private final Logger logger = LoggerFactory.getLogger(DesignationController.class);
	
	@Autowired
	private DesignationService designationservice;
	
	@PostMapping("/getall")
	public ResponseEntity<Map<String, Object>> getAll(@RequestBody PaginationRequest paginationRequest) {
	    logger.info("Fetching all designations.");

	    Pageable pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize());
	    Page<Designation> result = designationservice.getAll(pageable); 

	    Map<String, Object> response = new HashMap<>();
	    response.put("designationList", result.getContent());
	    response.put("totalCount", result.getTotalElements());

	    return ResponseEntity.ok(response);
	}

	
	@GetMapping("/getbyid/{desgId}")
	public Optional<Designation> getById(@PathVariable int desgId) {
		logger.info("Fetching designation with ID", desgId);
		return designationservice.getById(desgId);
	}
	
	@PostMapping("/add")
	public Response adddep(@RequestBody Designation desgbody) {
		logger.info("Adding new designation", desgbody);
		return designationservice.addDesignation(desgbody);
	}
	
	@PutMapping("/update")
	public Response updatedep(@RequestBody Designation desgbody) {
		logger.info("Updating designation", desgbody);
		return designationservice.updatedesignation(desgbody);
	}
	
	@DeleteMapping("/delete/{desgId}")
	public Response deletedep(@PathVariable int desgId) {
		logger.info("Deleting designation with ID", desgId);
		return designationservice.deletedesignation(desgId);
	}

}
