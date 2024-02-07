package com.hyundai.pms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.Skill;
import com.hyundai.pms.repository.SkillRepository;
import com.hyundai.pms.response.Response;
import com.hyundai.pms.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService{
	
	@Autowired
	private SkillRepository skillrepository;
	

	@Override
	public List<Skill> getAll() {
		return skillrepository.findAll();
	}
	
	@Override
	public Page<Skill> getAll(Pageable pageable) {
	    return skillrepository.findAll(pageable);
	}

	@Override
	public Optional<Skill> getById(int skillId) {
		return skillrepository.findById(skillId);
	}

	 
	 @Override
		public Response addskill(Skill skillBody) {
		    if (skillBody==null) {
		        return new Response(-1, "failed", "Skill object cannot be null");
		    }
		    if (skillBody.getSkillName()==null || skillBody.getSkillName().isEmpty()||skillBody.getSkillName()=="") {
		        return new Response(-1, "failed", "Skill name cannot be null or empty");
		    }
		    if (skillBody.getSkillCategory()==null || skillBody.getSkillCategory().isEmpty()||skillBody.getSkillCategory()=="") {
		        return new Response(-1, "failed", "SkillCategory cannot be null or empty");
		    }
		    if (skillBody.getProficiencyLevel()==null || skillBody.getProficiencyLevel().isEmpty()||skillBody.getProficiencyLevel()=="") {
		        return new Response(-1, "failed", "ProficiencyLevel cannot be null or empty");
		    }

		    try {
		    	Skill savedSkill = skillrepository.save(skillBody);
		        return new Response(1, "success", savedSkill);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return new Response(-1, "failed", "Failed to save Skill");
		}

	 
	 @Override
		public Response updateskill(Skill skillBody) {
		    if (skillBody==null) {
		        return new Response(-1, "failed", "Skill object cannot be null");
		    }
		    if (skillBody.getSkillName()==null || skillBody.getSkillName().isEmpty()||skillBody.getSkillName()=="") {
		        return new Response(-1, "failed", "Skill name cannot be null or empty");
		    }
		    if (skillBody.getSkillCategory()==null || skillBody.getSkillCategory().isEmpty()||skillBody.getSkillCategory()=="") {
		        return new Response(-1, "failed", "SkillCategory cannot be null or empty");
		    }
		    if (skillBody.getProficiencyLevel()==null || skillBody.getProficiencyLevel().isEmpty()||skillBody.getProficiencyLevel()=="") {
		        return new Response(-1, "failed", "ProficiencyLevel cannot be null or empty");
		    }

		    try {
		    	Skill updateSkill = skillrepository.save(skillBody);
		        return new Response(1, "success", updateSkill);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return new Response(-1, "failed", "Failed to update Skill");
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
