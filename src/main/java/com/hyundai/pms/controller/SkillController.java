package com.hyundai.pms.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.Response;
import com.hyundai.pms.entity.SkillMaster;
import com.hyundai.pms.service.SkillService;
import com.hyundai.pms.webModel.PaginationWebModel;



@RestController
@RequestMapping("/skill")
@CrossOrigin(value="http://localhost:4200/")
public class SkillController {
	
	private final Logger logger = LoggerFactory.getLogger(SkillController.class);
	
	@Autowired
	private SkillService skillservice;
	
	@PostMapping("/getall")
	public Response getAll(@RequestBody PaginationWebModel paginationWebModel){
		logger.info("Fetching all skills.");
		return skillservice.getAll(paginationWebModel);
	}
	
	@GetMapping("/getbyid/{skillId}")
	public Response getById(@PathVariable int skillId) {
		logger.info("Fetching skill with ID: {}", skillId);
		Optional<SkillMaster> skill= skillservice.getById(skillId);
		return new Response(1, "Success", skill);
	}
	
	@PostMapping("/add")
    public Response addSkill(@RequestBody SkillMaster skillBody) {
		logger.info("Adding new skill: {}", skillBody);
        skillservice.addskill(skillBody);
		return new Response(1, "Success", skillBody);
    }
	
	@PutMapping("/update")
	public Response updateSkill(@RequestBody SkillMaster skillBody) {
		logger.info("Updating skill: {}", skillBody);
		skillservice.updateskill(skillBody);
		return new Response(1, "Success", skillBody);
	}
	
	@DeleteMapping("/delete/{skillId}")
	public Response deleteSkill(@PathVariable int skillId) {
		logger.info("Deleting skill with ID: {}", skillId);
		skillservice.deleteskill(skillId);
		return new Response(1, "Success", skillId);
	}

}