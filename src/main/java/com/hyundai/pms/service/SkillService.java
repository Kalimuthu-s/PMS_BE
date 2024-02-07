package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hyundai.pms.entity.Skill;
import com.hyundai.pms.response.Response;

public interface SkillService {
	
	public List<Skill> getAll();
	
    public Optional<Skill> getById(int skillid);
	
    Response addskill(Skill skillbody);
	
	Response updateskill(Skill skillbody);

	Response deleteskill(int skillid);
	
	public Page<Skill> getAll(Pageable pageable);

}
