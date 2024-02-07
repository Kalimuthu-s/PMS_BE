package com.hyundai.pms.serviceimpl;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.Department;
import com.hyundai.pms.repository.DepartmentRepository;
import com.hyundai.pms.response.Response;
import com.hyundai.pms.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentrepository;
	

	@Override
	public List<Department> getAll() {
		return departmentrepository.findAll();
	}
	
	@Override
	public Page<Department> getAll(Pageable pageable) {
	    return departmentrepository.findAll(pageable);
	}

	@Override
	public Optional<Department> getById(int depId) {
		return departmentrepository.findById(depId);
	}

	@Override
	public Response addDepartment(Department depbody) {
	    if (depbody==null) {
	        return new Response(-1, "failed", "Department object cannot be null");
	    }
	    if (depbody.getDepartmentName()==null || depbody.getDepartmentName ().isEmpty()||depbody.getDepartmentName()=="") {
	        return new Response(-1, "failed", "Department name cannot be null or empty");
	    }

	    try {
	        Department savedDepartment = departmentrepository.save(depbody);
	        return new Response(1, "success", savedDepartment);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new Response(-1, "failed", "Failed to save department");
	}

	@Override
	public Response updatedepartment(Department depbody) {
	    if (depbody==null) {
	        return new Response(-1, "failed", "Department object cannot be null");
	    }
	    if (depbody.getDepartmentName()==null || depbody.getDepartmentName().isEmpty()||depbody.getDepartmentName().equals("")) {
	        return new Response(-1, "failed", "Department name cannot be null or empty");
	    }

	    try {
	        Department updatedDepartment = departmentrepository.save(depbody);
	        return new Response(1, "success", updatedDepartment);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new Response(-1, "failed", "Failed to update department");
	}
	
	
	@Override
	 public Response deletedepartment(int depId) {
	     try {
	         Optional<Department> optionalDepartment = departmentrepository.findById(depId);

	         if (optionalDepartment.isPresent()) {
	             Department deletedDepartment = optionalDepartment.get();
	             departmentrepository.deleteById(depId);
	             return new Response(1, "Success", deletedDepartment);
	         } else {
	             return new Response(-1, "Department not found","");
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return new Response(-1, "Failed to delete Department: " ,"");
	     }
	 }

}
