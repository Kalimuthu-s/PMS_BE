package com.hyundai.pms.service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.hyundai.pms.entity.Department;
import com.hyundai.pms.response.Response;

public interface DepartmentService {
	
	public List<Department> getAll();
	
    public Optional<Department> getById(int depId);
	
    Response addDepartment(Department depbody);
	
    Response updatedepartment(Department depbody);

    Response deletedepartment(int depId);

	public Page<Department> getAll(Pageable pageable);

}
