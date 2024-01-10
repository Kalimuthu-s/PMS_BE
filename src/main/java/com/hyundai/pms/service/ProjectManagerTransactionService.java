package com.hyundai.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.ProjectManagerTransaction;
import com.hyundai.pms.repository.ProjectManagerTransactionRepository;

@Service
public class ProjectManagerTransactionService {
	
	@Autowired
	private ProjectManagerTransactionRepository pr;
	
	public List<ProjectManagerTransaction> getAllProjectManagerTransaction(){
		return null;
	}
	
	public ProjectManagerTransaction getProjectManagerTransactionById(int id) {
		return pr.getById(id);
	}
	
	public ProjectManagerTransaction addProjectManagerTransaction(ProjectManagerTransaction pmt) {
		return pr.save(pmt);
	}
	
	public ProjectManagerTransaction updateProjectManagerTransaction(ProjectManagerTransaction pmt) {
		return pr.save(pmt);
	}
	
	public void deleteProjectManagerTransaction(int id) {
		pr.deleteById(id);
	}
	
	public List<ProjectManagerTransaction> getManagerAvaiableProjects(String manager){
		return pr.getManagerAvaiableProjects(manager);
	}


}
