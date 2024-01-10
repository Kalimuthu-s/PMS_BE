package com.hyundai.pms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.DepartmentMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.DepartmentRepository;
import com.hyundai.pms.service.DepartmentService;



@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentrepository;
	

	@Override
	public List<DepartmentMaster> getAll() {
		return departmentrepository.findAll();
	}

	@Override
	public Optional<DepartmentMaster> getById(int depId) {
		return departmentrepository.findById(depId);
	}

	 @Override
	    public Response adddepartment(DepartmentMaster depbody) {
	        try {
	        	DepartmentMaster savedSkill = departmentrepository.save(depbody);
	            return new Response(1, "success", savedSkill);
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	        return new Response(-1, "failed","");
	    }

	 @Override
	    public Response updatedepartment(DepartmentMaster depbody) {
	        try {
	        	DepartmentMaster savedSkill = departmentrepository.save(depbody);
	            return new Response(1, "success", savedSkill);
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	        return new Response(-1, "failed","");
	    }
	
	
	@Override
	 public Response deletedepartment(int depId) {
	     try {
	         Optional<DepartmentMaster> optionalSkill = departmentrepository.findById(depId);

	         if (optionalSkill.isPresent()) {
	             DepartmentMaster deletedSkill = optionalSkill.get();
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