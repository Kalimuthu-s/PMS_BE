package com.hyundai.pms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.hyundai.pms.entity.PendingRequest;
import com.hyundai.pms.response.Response;

public interface PendingRequestService {
	
    public List<PendingRequest> getAll();
	
    public Optional<PendingRequest> getById(int pendingid);
	
    String addpendingrequest(PendingRequest pendingbody);
	
    Response updatependingrequest(PendingRequest pendingbody);

	String deletependingrequest(int pendingid);

	public List<PendingRequest> searchPendingRequests(Integer pendingRequestId, Integer empMonthId, Integer managerId,
			String status, Date date);


}
