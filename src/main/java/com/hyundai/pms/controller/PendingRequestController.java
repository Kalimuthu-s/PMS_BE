package com.hyundai.pms.controller;

import java.util.Date;
import java.util.List;
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
import com.hyundai.pms.response.Response;
import com.hyundai.pms.service.PendingRequestService;
import com.hyundai.pms.serviceimpl.PendingRequestServiceImpl;

@RestController
@RequestMapping("/pendingrequest")
@CrossOrigin(value="http://localhost:4200/")
public class PendingRequestController {
	
	private final Logger logger = LoggerFactory.getLogger(PendingRequestServiceImpl.class);
	
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
	public String addrequest(@RequestBody PendingRequest pendingbody) {
		logger.info("Adding new PendingRequest", pendingbody);
		return pendingrequestservice.addpendingrequest(pendingbody);
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
	    public ResponseEntity<List<PendingRequest>> searchPendingRequests(
	            @RequestParam(required = false) Integer pendingRequestId,
	            @RequestParam(required = false) Integer empMonthId,
	            @RequestParam(required = false) Integer managerId,
	            @RequestParam(required = false) String status,
	            @RequestParam(required = false) Date date) {

	        List<PendingRequest> result = pendingrequestservice.searchPendingRequests(pendingRequestId, empMonthId, managerId, status, date);
	        return ResponseEntity.ok(result);
	    }

}
