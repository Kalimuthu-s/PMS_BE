package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hyundai.pms.entity.Designation;
import com.hyundai.pms.response.Response;

public interface DesignationService {
	
    public List<Designation> getAll();
	
    public Optional<Designation> getById(int desgId);
	
    Response addDesignation(Designation desgbody);
	
	Response updatedesignation(Designation desgbody);

	Response deletedesignation(int desgId);
	
	public Page<Designation> getAll(Pageable pageable);

}
