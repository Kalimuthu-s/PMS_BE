package com.hyundai.pms.controller;

import java.util.List;
import java.util.Optional;

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

import com.hyundai.pms.entity.ExperienceMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.ExperienceService;


@RestController
@RequestMapping("/experience")
@CrossOrigin(value="http://localhost:4200/")
public class ExperienceController {
	
	@Autowired
	private ExperienceService experienceService;
	
	 @PostMapping("/addExperience")
	    public Response addExperience(@RequestBody ExperienceMaster experience) {
	        return experienceService.saveExperience(experience);
	    }

	    @GetMapping("/getAllExperience")
	    public List<ExperienceMaster> getAllExperiences() {
	        return experienceService.getAllExperiences();
	    }

	    @GetMapping("/getExperienceById/{id}")
	    public Optional<ExperienceMaster> getExperienceById(@PathVariable Long id) {
	        return experienceService.getExperienceById(id);
	    }

	    @PutMapping("/updateExperience/{id}")
	    public Response updateExperience(@PathVariable Long id, @RequestBody ExperienceMaster updatedExperience) {
	        return experienceService.updateExperience(id, updatedExperience);
	    }

	    @DeleteMapping("/deleteExperience/{id}")
	    public Response deleteExperience(@PathVariable Long id) {
	       return experienceService.deleteExperience(id);
	    }
}