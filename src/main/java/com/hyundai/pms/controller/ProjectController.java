package com.hyundai.pms.controller;

import java.util.List;
import java.util.Map;
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

import com.hyundai.pms.entity.ProjectMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.ProjectService;
import com.hyundai.pms.webModel.PaginationWebModel;

@CrossOrigin
@RequestMapping("/project")
@RestController
public class ProjectController {
	
	@Autowired
	private ProjectService ps;
	
	@GetMapping("/getAllProject")
	public Response  getAllProject() {
		List<Map<String, Object>> list = ps.getAllProject();
		return new Response(1, "Success", list);
	}
	
	@GetMapping("/getAllProjects")
	public Response  getAllProjects() {
		List<ProjectMaster> list = ps.getAllProjects();
		return new Response(1, "Success", list);
	}
	
//	@GetMapping("/getAllProjects")
//	public Response  getAllProjects(@RequestBody PaginationWebModel paginationWebModel) {
//		List<Map<String, Object>> list = ps.getAllProjects(paginationWebModel);
//		return new Response(1, "Success", list);
//	}
	
	@GetMapping("/getProjectById/{id}")
	public Response getProjectById(@PathVariable int id) {
		Optional<ProjectMaster> project=ps.getProjectById(id);
		return new Response(1, "Success", project);		
	}
	
	@PostMapping("/addProject")
	public Response addProject(@RequestBody ProjectMaster project) {
//		ps.addProject(project);
		return ps.addProject(project);		
	}
	
	@PutMapping("/updateProject")
	public Response updateProject(@RequestBody ProjectMaster project) {
		ps.updateProject(project);
		return new Response(1, "Success", project);		
	}
	
	@DeleteMapping("/deleteProject/{id}")
	public Response deleteProject(@PathVariable int id) {
		ps.deleteProject(id);
		return new Response(1, "Success", id);		
	}
	
	@GetMapping("/searchProjectByName/{projectname}")
	public Response searchProjectByName(@PathVariable String projectname) {
		List<ProjectMaster> list= ps.searchProjectByName(projectname);
		return new Response(1, "Success", list);		
	}

}
