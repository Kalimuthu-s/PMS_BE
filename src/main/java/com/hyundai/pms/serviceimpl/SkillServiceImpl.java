package com.hyundai.pms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.Response;
import com.hyundai.pms.entity.SkillMaster;
import com.hyundai.pms.repository.SkillRepository;
import com.hyundai.pms.service.SkillService;



@Service
public class SkillServiceImpl implements SkillService{
	
	@Autowired
	private SkillRepository skillrepository;
	

	@Override
	public List<SkillMaster> getAll() {
		return skillrepository.findAll();
	}

	@Override
	public Optional<SkillMaster> getById(int skillId) {
		return skillrepository.findById(skillId);
	}

	 @Override
	    public Response addskill(SkillMaster skillBody) {
	        try {
	        	SkillMaster savedSkill = skillrepository.save(skillBody);
	            return new Response(1, "success", savedSkill);
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	        return new Response(-1, "failed","");
	    }

	 @Override
	    public Response updateskill(SkillMaster skillBody) {
	        try {
	        	SkillMaster savedSkill = skillrepository.save(skillBody);
	            return new Response(1, "success", savedSkill);
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	        return new Response(-1, "failed","");
	    }

	 @Override
	 public Response deleteskill(int skillId) {
	     try {
	         Optional<SkillMaster> optionalSkill = skillrepository.findById(skillId);

	         if (optionalSkill.isPresent()) {
	             SkillMaster deletedSkill = optionalSkill.get();
	             skillrepository.deleteById(skillId);
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