package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.AssignEmployeeTransaction;
import com.hyundai.pms.entity.AssignManagerTransaction;
import com.hyundai.pms.entity.ProjectMaster;
import com.hyundai.pms.repository.AssignEmployeeTransactionRepository;
import com.hyundai.pms.repository.AssignManagerTransactionRepository;
import com.hyundai.pms.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository pr;

	@Autowired
	private AssignManagerTransactionRepository projectTransactionRepository;

	public List<ProjectMaster> getAllProject() {
		return pr.findAll();
	}

	public Optional<ProjectMaster> getProjectById(int id) {
		return pr.findById(id);
	}

	public ProjectMaster addProject(ProjectMaster project) {
		project = pr.save(project);

		this.saveProjectTransaction(project);

		return project;
	}

	private void saveProjectTransaction(ProjectMaster projectMaster) {

		AssignManagerTransaction projectManagerTransaction = new AssignManagerTransaction();
		projectManagerTransaction.setProjectName(String.valueOf(projectMaster.getProjectId()));
		projectManagerTransaction.setManagerName(projectMaster.getManagerName());
		projectManagerTransaction.setStartDate(projectMaster.getStartDate());
		projectManagerTransaction.setEndDate(projectMaster.getEndDate());
		projectManagerTransaction.setStatus(projectMaster.getStatus());

		projectTransactionRepository.save(projectManagerTransaction);
	}

	public ProjectMaster updateProject(ProjectMaster project) {
		return pr.save(project);
	}

	public void deleteProject(int id) {
		pr.deleteById(id);
	}

	public List<ProjectMaster> searchProjectByName(String projectname) {
		return pr.searchProjectByName(projectname);
	}

}
