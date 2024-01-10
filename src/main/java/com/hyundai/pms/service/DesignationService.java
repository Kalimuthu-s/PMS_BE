package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import com.hyundai.pms.entity.DesignationMaster;
import com.hyundai.pms.entity.Response;


public interface DesignationService {
	
    public List<DesignationMaster> getAll();
	
    public Optional<DesignationMaster> getById(int desgId);
	
    Response adddesignation(DesignationMaster desgbody);
	
	Response updatedesignation(DesignationMaster desgbody);

	Response deletedesignation(int desgId);

}