package com.hyundai.pms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.DesignationMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.DesignationRepository;
import com.hyundai.pms.service.DesignationService;


@Service
public class DesignationServiceImpl implements DesignationService {
	
	@Autowired
	private DesignationRepository designationrepository;

	@Override
	public List<DesignationMaster> getAll() {
		return designationrepository.findAll();
	}

	@Override
	public Optional<DesignationMaster> getById(int desgId) {
		return designationrepository.findById(desgId);
	}

	@Override
    public Response adddesignation(DesignationMaster desgbody) {
        try {
        	DesignationMaster savedSkill = designationrepository.save(desgbody);
            return new Response(1, "success", savedSkill);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return new Response(-1, "failed","");
    }

	@Override
    public Response updatedesignation(DesignationMaster desgbody) {
        try {
        	DesignationMaster savedSkill = designationrepository.save(desgbody);
            return new Response(1, "success", savedSkill);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return new Response(-1, "failed","");
    }

	@Override
	 public Response deletedesignation(int desgId) {
	     try {
	         Optional<DesignationMaster> optionalSkill = designationrepository.findById(desgId);

	         if (optionalSkill.isPresent()) {
	        	 DesignationMaster deletedSkill = optionalSkill.get();
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