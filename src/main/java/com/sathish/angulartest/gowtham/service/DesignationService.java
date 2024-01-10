package com.sathish.angulartest.gowtham.service;

import java.util.List;
import java.util.Optional;

import com.sathish.angulartest.gowtham.entity.Designation;
import com.sathish.angulartest.gowtham.response.Response;

public interface DesignationService {
	
    public List<Designation> getAll();
	
    public Optional<Designation> getById(int desgId);
	
    Response adddesignation(Designation desgbody);
	
	Response updatedesignation(Designation desgbody);

	Response deletedesignation(int desgId);

}
