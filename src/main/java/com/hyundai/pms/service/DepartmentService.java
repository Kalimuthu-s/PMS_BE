package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import com.hyundai.pms.entity.DepartmentMaster;
import com.hyundai.pms.entity.Response;


public interface DepartmentService {
	
	public List<DepartmentMaster> getAll();
	
    public Optional<DepartmentMaster> getById(int depId);
	
    Response adddepartment(DepartmentMaster depbody);
	
    Response updatedepartment(DepartmentMaster depbody);

    Response deletedepartment(int depId);

}