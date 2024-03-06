package com.hyundai.pms.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.AssignEmployeeTransaction;
import com.hyundai.pms.entity.AssignManagerTransaction;
import com.hyundai.pms.entity.ProjectMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.AssignEmployeeTransactionRepository;
import com.hyundai.pms.repository.AssignManagerTransactionRepository;
import com.hyundai.pms.repository.ProjectRepository;
import com.hyundai.pms.webModel.PaginationWebModel;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository pr;

	@Autowired
	private AssignManagerTransactionRepository projectTransactionRepository;

	public List<Map<String, Object>> getAllProject() {
		return pr.findAllProject();
	}
	
	public List<ProjectMaster> getAllProjects() {
		return pr.findAll();
	}
	
//	public Response getAllProjects(PaginationWebModel paginationWebModel) {
//		Map<String, Object> response = null;
//		try {
//			Pageable pageable = PageRequest.of(paginationWebModel.getPageNo(), paginationWebModel.getPageSize());
//			var page = pr.getAllProjects(pageable,paginationWebModel.getSearchKey());
//			
//			response = new HashMap<>();
//			
//			response.put("count", page.getTotalElements());
//			response.put("content", page.getContent());
//			
//			return new Response(1, "success", response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new Response(-1, "failed", "");
//	}

	public Optional<ProjectMaster> getProjectById(int id) {
		return pr.findById(id);
	}

	public Response addProject(ProjectMaster project) {
		 String startDateStr = project.getStartDate();
	    String endDateStr = project.getEndDate();

	    LocalDate startDate = LocalDate.parse(startDateStr);
	    LocalDate endDate = LocalDate.parse(endDateStr);
	    if(pr.findExistingProject(project.getProjectName()).isEmpty()) {
	    	pr.save(project);
	    	saveProjectTransaction(project);
	    	return new Response(1, "Success", project);
	    }
		  if (startDate.isAfter(endDate)) {
            return new Response(2,"Start date cannot be greater than end date.",null);
        }else
    	return new Response(2,"A project with the same name already exists.",null);
		
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
