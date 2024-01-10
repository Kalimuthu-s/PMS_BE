package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import com.hyundai.pms.entity.Response;
import com.hyundai.pms.entity.SkillMaster;


public interface SkillService {
	
	public List<SkillMaster> getAll();
	
    public Optional<SkillMaster> getById(int skillid);
	
    Response addskill(SkillMaster skillbody);
	
	Response updateskill(SkillMaster skillbody);

	Response deleteskill(int skillid);

}