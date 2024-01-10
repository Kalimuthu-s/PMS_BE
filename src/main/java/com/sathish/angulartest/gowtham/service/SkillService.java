package com.sathish.angulartest.gowtham.service;

import java.util.List;
import java.util.Optional;

import com.sathish.angulartest.gowtham.entity.Skill;
import com.sathish.angulartest.gowtham.response.Response;

public interface SkillService {
	
	public List<Skill> getAll();
	
    public Optional<Skill> getById(int skillid);
	
    Response addskill(Skill skillbody);
	
	Response updateskill(Skill skillbody);

	Response deleteskill(int skillid);

}
