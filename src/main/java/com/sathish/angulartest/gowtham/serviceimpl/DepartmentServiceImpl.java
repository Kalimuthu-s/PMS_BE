package com.sathish.angulartest.gowtham.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathish.angulartest.gowtham.entity.Department;
import com.sathish.angulartest.gowtham.entity.Skill;
import com.sathish.angulartest.gowtham.repository.DepartmentRepository;
import com.sathish.angulartest.gowtham.response.Response;
import com.sathish.angulartest.gowtham.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentrepository;
	

	@Override
	public List<Department> getAll() {
		return departmentrepository.findAll();
	}

	@Override
	public Optional<Department> getById(int depId) {
		return departmentrepository.findById(depId);
	}

	 @Override
	    public Response adddepartment(Department depbody) {
	        try {
	        	Department savedSkill = departmentrepository.save(depbody);
	            return new Response(1, "success", savedSkill);
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	        return new Response(-1, "failed","");
	    }

	 @Override
	    public Response updatedepartment(Department depbody) {
	        try {
	        	Department savedSkill = departmentrepository.save(depbody);
	            return new Response(1, "success", savedSkill);
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	        return new Response(-1, "failed","");
	    }
	
	
	@Override
	 public Response deletedepartment(int depId) {
	     try {
	         Optional<Department> optionalSkill = departmentrepository.findById(depId);

	         if (optionalSkill.isPresent()) {
	             Department deletedSkill = optionalSkill.get();
	             departmentrepository.deleteById(depId);
	             return new Response(1, "Success", deletedSkill);
	         } else {
	             return new Response(-1, "Skill not found","");
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return new Response(-1, "Failed to delete Skill: " ,"");
	     }
	 }

}
