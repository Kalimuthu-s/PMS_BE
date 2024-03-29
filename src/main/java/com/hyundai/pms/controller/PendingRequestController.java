package com.hyundai.pms.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.PendingRequest;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.PendingRequestService;

@RestController
@RequestMapping("/pendingRequest")
@CrossOrigin(value="http://localhost:4200/")
public class PendingRequestController {
	
	private final Logger logger = LoggerFactory.getLogger(PendingRequestController.class);
	
	@Autowired
	private PendingRequestService pendingrequestservice;
	
	@GetMapping("/getall")
	public List<PendingRequest> getAll(){
		return pendingrequestservice.getAll();
	}
	
	@GetMapping("/getbyid/{pendingid}")
	public Optional<PendingRequest> getById(@PathVariable int pendingid){
		return pendingrequestservice.getById(pendingid);
	}
	
	@PostMapping("/add")
	public Response addrequest(@RequestBody PendingRequest pendingbody) {
		logger.info("Adding new PendingRequest", pendingbody);
		PendingRequest pendingRequest = pendingrequestservice.addpendingrequest(pendingbody);
		return new Response(1, "Success", pendingRequest);
	}
	
	@PutMapping("/update")
	public Response updaterequest(@RequestBody PendingRequest pendingbody) {
		return pendingrequestservice.updatependingrequest(pendingbody);
	}

	
	@DeleteMapping("/delete/{pendingid}")
	public String deleterequest(@PathVariable int pendingid) {
		return pendingrequestservice.deletependingrequest(pendingid);
	}
	
	 @GetMapping("/search")
	    public Response searchPendingRequests(@RequestParam String status) {

		 List<Map<String, Object>> result = pendingrequestservice.searchPendingRequests(status);
	        return new Response(1, "Success", result);
	    }
}
