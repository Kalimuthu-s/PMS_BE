package com.hyundai.pms.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.PendingRequest;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.PendingRequestRepository;

@Service
public class PendingRequestService {
	
	@Autowired
	private PendingRequestRepository pendingrequestrepository;

	
	public List<PendingRequest> getAll() {
		return pendingrequestrepository.findAll();
	}

	
	public Optional<PendingRequest> getById(int pendingid) {
		return pendingrequestrepository.findById(pendingid);
	}

	
	public PendingRequest addpendingrequest(PendingRequest pendingbody) {
		pendingbody.setStatus("submitted");
		return pendingrequestrepository.save(pendingbody);
		 
	}
	
	
	
	public Response updatependingrequest(PendingRequest pendingBody) {
	    if (pendingBody == null) {
	        return new Response(-1, "failed", "PendingRequest object cannot be null");
	    }

	    try {
	        Optional<PendingRequest> result = pendingrequestrepository.findById(pendingBody.getPendingRequestId());
	        if (result.isPresent()) {
	            PendingRequest request = result.get();
	            request.setStatus(pendingBody.getStatus());

	            PendingRequest updatedRequest = pendingrequestrepository.save(request);
	            return new Response(1, "success", updatedRequest);
	        } else {
	            return new Response(-1, "failed", "PendingRequest not found with ID: " + pendingBody.getPendingRequestId());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new Response(-1, "failed", "Failed to update pending request");
	    }
	}



	
	public String deletependingrequest(int pendingid) {
		pendingrequestrepository.deleteById(pendingid);
		return "Deleted successfully " + pendingid;
	}

	
	public List<Map<String, Object>> searchPendingRequests(String status) {
		return pendingrequestrepository.searchPendingRequests(status);
	}


}
