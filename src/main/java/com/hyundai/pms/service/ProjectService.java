package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.ProjectMaster;
import com.hyundai.pms.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository pr;
	
	public List<ProjectMaster> getAllProject(){
		return pr.findAll();
	}
	
	public Optional<ProjectMaster> getProjectById(int id) {
		return pr.findById(id);
	}
	
	public ProjectMaster addProject(ProjectMaster project) {
		return pr.save(project);
	}
	
	public ProjectMaster updateProject(ProjectMaster project) {
		return pr.save(project);
	}
	
	public void deleteProject(int id) {
		pr.deleteById(id);
	}

}
