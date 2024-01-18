package com.hyundai.pms.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.Department;
import com.hyundai.pms.entity.Skill;
import com.hyundai.pms.pagination.PaginationRequest;
import com.hyundai.pms.response.Response;
import com.hyundai.pms.service.SkillService;

@RestController
@RequestMapping("/skill")
@CrossOrigin(value="http://localhost:4200/")
public class SkillController {
	
	private final Logger logger = LoggerFactory.getLogger(SkillController.class);
	
	@Autowired
	private SkillService skillservice;
	
	@PostMapping("/getall")
	public List<Skill> getAll(@RequestBody PaginationRequest paginationrequest){
		logger.info("Fetching all Skill.");
		Pageable pageable= PageRequest.of(paginationrequest.getPage(),paginationrequest.getSize());
		Page<Skill> result = skillservice.getAll(pageable);
		return result.getContent();
	}
	
	@GetMapping("/getbyid/{skillId}")
	public Optional<Skill> getById(@PathVariable int skillId) {
		logger.info("Fetching skill with ID: {}", skillId);
		return skillservice.getById(skillId);
	}
	
	@PostMapping("/add")
    public Response addSkill(@RequestBody Skill skillBody) {
		logger.info("Adding new skill: {}", skillBody);
        return skillservice.addskill(skillBody);
        
    }
	
	@PutMapping("/update")
	public Response updateSkill(@RequestBody Skill skillBody) {
		logger.info("Updating skill: {}", skillBody);
		return skillservice.updateskill(skillBody);
	}
	
	@DeleteMapping("/delete/{skillId}")
	public Response deleteSkill(@PathVariable int skillId) {
		logger.info("Deleting skill with ID: {}", skillId);
		return skillservice.deleteskill(skillId);
	}

}
