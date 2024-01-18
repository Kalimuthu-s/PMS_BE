package com.hyundai.pms.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.hyundai.pms.entity.ProjectManagerTransaction;
import com.hyundai.pms.entity.ProjectReassignDTO;
import com.hyundai.pms.repository.ProjectManagerTransactionRepository;

@Service
public class ProjectManagerTransactionService {
	
	@Autowired
	private ProjectManagerTransactionRepository pr;
	
	public List<ProjectManagerTransaction> getAllProjectManagerTransaction(){
		return pr.findAll();
	}
	
	public Optional<ProjectManagerTransaction> getProjectManagerTransactionById(int id) {
		return pr.findById(id);
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
	
	public String reassignManagerToProjects(ProjectReassignDTO data) {
			List<Integer> list =data.getProjectList();
			list.forEach(l->{
				ProjectManagerTransaction p1=new ProjectManagerTransaction();
				p1=pr.getOneById(l);
				p1.setManagerName(data.getAssignTo());
				pr.save(p1);
			});
		return "Manager Reassigned";
	}


}
