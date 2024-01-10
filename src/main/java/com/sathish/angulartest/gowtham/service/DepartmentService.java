package com.sathish.angulartest.gowtham.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sathish.angulartest.gowtham.entity.Department;
import com.sathish.angulartest.gowtham.response.Response;

public interface DepartmentService {
	
	public List<Department> getAll();
	
    public Optional<Department> getById(int depId);
	
    Response adddepartment(Department depbody);
	
    Response updatedepartment(Department depbody);

    Response deletedepartment(int depId);

}
