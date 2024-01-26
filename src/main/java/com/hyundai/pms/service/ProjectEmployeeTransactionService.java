package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.ProjectEmployeeTransaction;
import com.hyundai.pms.repository.ProjectEmployeeTransactionRepository;

@Service
public class ProjectEmployeeTransactionService {
	
	@Autowired
	private ProjectEmployeeTransactionRepository petr;
	
	public List<ProjectEmployeeTransaction> getAllProjectEmployeeTransaction(){
    return petr.findAll();
	}
	
	public Optional<ProjectEmployeeTransaction> getAllProjectEmployeeTransactionById(int id) {
		return petr.findById(id);
	}
	
	public ProjectEmployeeTransaction addProjectEmployeeTransaction(ProjectEmployeeTransaction pet) {
		return petr.save(pet);
	}
	
	public ProjectEmployeeTransaction updateProjectEmployeeTransaction(ProjectEmployeeTransaction pet) {
		return petr.save(pet);
	}
	
	public String deleteProjectEmployeeTransaction(int id) {
		petr.deleteById(id);
		return "Success";
	}
}
