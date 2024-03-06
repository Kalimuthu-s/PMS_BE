package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import com.hyundai.pms.entity.FiltersDTO;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.entity.SkillMaster;
import com.hyundai.pms.webModel.PaginationWebModel;


public interface SkillService {
	
	public Response getAll(PaginationWebModel paginationWebModel);
	
	public Response getAllSkills();
	
    public Optional<SkillMaster> getById(int skillid);
	
    Response addskill(SkillMaster skillbody);
	
	Response updateskill(SkillMaster skillbody);

	Response deleteskill(int skillid);
	
}