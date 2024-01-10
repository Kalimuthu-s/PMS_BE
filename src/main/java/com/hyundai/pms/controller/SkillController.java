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



@RestController
@RequestMapping("/skill")
@CrossOrigin(value="http://localhost:4200/")
public class SkillController {
	
	private final Logger logger = LoggerFactory.getLogger(SkillController.class);
	
	@Autowired
	private SkillService skillservice;
	
	@GetMapping("/getall")
	public List<SkillMaster> getAll(){
		logger.info("Fetching all skills.");
		return skillservice.getAll();
	}
	
	@GetMapping("/getbyid/{skillId}")
	public Optional<SkillMaster> getById(@PathVariable int skillId) {
		logger.info("Fetching skill with ID: {}", skillId);
		return skillservice.getById(skillId);
	}
	
	@PostMapping("/add")
    public Response addSkill(@RequestBody SkillMaster skillBody) {
		logger.info("Adding new skill: {}", skillBody);
        return skillservice.addskill(skillBody);
        
    }
	
	@PutMapping("/update")
	public Response updateSkill(@RequestBody SkillMaster skillBody) {
		logger.info("Updating skill: {}", skillBody);
		return skillservice.updateskill(skillBody);
	}
	
	@DeleteMapping("/delete/{skillId}")
	public Response deleteSkill(@PathVariable int skillId) {
		logger.info("Deleting skill with ID: {}", skillId);
		return skillservice.deleteskill(skillId);
	}

}