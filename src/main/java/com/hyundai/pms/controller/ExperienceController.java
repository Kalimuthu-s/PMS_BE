package com.hyundai.pms.controller;

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
import com.hyundai.pms.webModel.PaginationWebModel;

@RestController
@RequestMapping("/experience")
@CrossOrigin(value = "http://localhost:4200/")
public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;

	@PostMapping("/addExperience")
	public Response addExperience(@RequestBody ExperienceMaster experience) {
		experienceService.saveExperience(experience);
		return new Response(1, "Success", experience);
	}

	 @PostMapping("/getAllExperience")
	    public Response getAllExperiences(@RequestBody PaginationWebModel paginationWebModel) {
	        return experienceService.getAllExperiences(paginationWebModel);
	    }

	@GetMapping("/getExperienceById/{id}")
	public Response getExperienceById(@PathVariable Long id) {
		Optional<ExperienceMaster> exp = experienceService.getExperienceById(id);
		return new Response(1, "Success", exp);
	}

	@PutMapping("/updateExperience/{id}")
	public Response updateExperience(@PathVariable Long id, @RequestBody ExperienceMaster updatedExperience) {
		experienceService.updateExperience(id, updatedExperience);
		return new Response(1, "Success", updatedExperience);
	}

	@DeleteMapping("/deleteExperience/{id}")
	public Response deleteExperience(@PathVariable Long id) {
		experienceService.deleteExperience(id);
		return new Response(1, "Success", id);

	}
}