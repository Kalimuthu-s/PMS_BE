package com.sathish.angulartest.gowtham.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathish.angulartest.gowtham.entity.Designation;
import com.sathish.angulartest.gowtham.entity.Skill;
import com.sathish.angulartest.gowtham.repository.DesignationRepository;
import com.sathish.angulartest.gowtham.response.Response;
import com.sathish.angulartest.gowtham.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {
	
	@Autowired
	private DesignationRepository designationrepository;

	@Override
	public List<Designation> getAll() {
		return designationrepository.findAll();
	}

	@Override
	public Optional<Designation> getById(int desgId) {
		return designationrepository.findById(desgId);
	}

	@Override
    public Response adddesignation(Designation desgbody) {
        try {
        	Designation savedSkill = designationrepository.save(desgbody);
            return new Response(1, "success", savedSkill);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return new Response(-1, "failed","");
    }

	@Override
    public Response updatedesignation(Designation desgbody) {
        try {
        	Designation savedSkill = designationrepository.save(desgbody);
            return new Response(1, "success", savedSkill);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return new Response(-1, "failed","");
    }

	@Override
	 public Response deletedesignation(int desgId) {
	     try {
	         Optional<Designation> optionalSkill = designationrepository.findById(desgId);

	         if (optionalSkill.isPresent()) {
	        	 Designation deletedSkill = optionalSkill.get();
	             designationrepository.deleteById(desgId);
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
