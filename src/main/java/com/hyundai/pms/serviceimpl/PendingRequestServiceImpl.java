package com.hyundai.pms.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.PendingRequest;
import com.hyundai.pms.repository.PendingRequestRepository;
import com.hyundai.pms.response.Response;
import com.hyundai.pms.service.PendingRequestService;

@Service
public class PendingRequestServiceImpl implements PendingRequestService {

	@Autowired
	private PendingRequestRepository pendingrequestrepository;

	@Override
	public List<PendingRequest> getAll() {
		return pendingrequestrepository.findAll();
	}

	@Override
	public Optional<PendingRequest> getById(int pendingid) {
		return pendingrequestrepository.findById(pendingid);
	}

	@Override
	public String addpendingrequest(PendingRequest pendingbody) {
		pendingbody.setStatus("submitted");
		pendingrequestrepository.save(pendingbody);
		return "Adding Success";
	}
	
	
	@Override
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



	@Override
	public String deletependingrequest(int pendingid) {
		pendingrequestrepository.deleteById(pendingid);
		return "Deleted successfully " + pendingid;
	}

	@Override
	public List<PendingRequest> searchPendingRequests(Integer pendingRequestId, Integer empMonthId, Integer managerId,
			String status, Date date) {
		if (pendingRequestId != null) {
			return pendingrequestrepository.findByPendingRequestId(pendingRequestId);
		} else if (empMonthId != null) {
			return pendingrequestrepository.findByEmpMonthId(empMonthId);
		} else if (managerId != null) {
			return pendingrequestrepository.findByManagerId(managerId);
		} else if (status != null) {
			return pendingrequestrepository.findByStatus(status);
		} else if (date != null) {
			return pendingrequestrepository.findByDate(date);
		} else {
			return pendingrequestrepository.findAll();
		}
	}

	
}
