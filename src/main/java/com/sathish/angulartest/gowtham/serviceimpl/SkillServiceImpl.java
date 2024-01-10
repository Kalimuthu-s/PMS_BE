package com.sathish.angulartest.gowtham.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathish.angulartest.gowtham.entity.Skill;
import com.sathish.angulartest.gowtham.repository.SkillRepository;
import com.sathish.angulartest.gowtham.response.Response;
import com.sathish.angulartest.gowtham.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService{
	
	@Autowired
	private SkillRepository skillrepository;
	

	@Override
	public List<Skill> getAll() {
		return skillrepository.findAll();
	}

	@Override
	public Optional<Skill> getById(int skillId) {
		return skillrepository.findById(skillId);
	}

	 @Override
	    public Response addskill(Skill skillBody) {
	        try {
	        	Skill savedSkill = skillrepository.save(skillBody);
	            return new Response(1, "success", savedSkill);
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	        return new Response(-1, "failed","");
	    }

	 @Override
	    public Response updateskill(Skill skillBody) {
	        try {
	        	Skill savedSkill = skillrepository.save(skillBody);
	            return new Response(1, "success", savedSkill);
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	        return new Response(-1, "failed","");
	    }

	 @Override
	 public Response deleteskill(int skillId) {
	     try {
	         Optional<Skill> optionalSkill = skillrepository.findById(skillId);

	         if (optionalSkill.isPresent()) {
	             Skill deletedSkill = optionalSkill.get();
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
