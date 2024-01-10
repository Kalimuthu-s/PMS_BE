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
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.ProjectMaster;
import com.hyundai.pms.service.ProjectService;

@CrossOrigin
@RestController
public class ProjectController {
	
	@Autowired
	private ProjectService ps;
	
	@GetMapping("/getAllProject")
	public List<ProjectMaster> getAllProject() {
		List<ProjectMaster> list = ps.getAllProject();
		return list;
	}
	
	@GetMapping("/getProjectById/{id}")
	public Optional<ProjectMaster> getProjectById(@PathVariable int id) {
		Optional<ProjectMaster> project=ps.getProjectById(id);
		return project;
		
	}
	
	@PostMapping("/addProject")
	public ProjectMaster addProject(@RequestBody ProjectMaster project) {
		return ps.addProject(project);
	}
	
	@PutMapping("/updateProject")
	public ProjectMaster updateProject(@RequestBody ProjectMaster project) {
		return ps.updateProject(project);
	}
	
	@DeleteMapping("/deleteProject/{id}")
	public String deleteProject(@PathVariable int id) {
		ps.deleteProject(id);
		return "Project Deleted";
	}
	
//	@GetMapping("/getProjectsByManager/{manager}")
//	public List<ProjectMaster>

}
