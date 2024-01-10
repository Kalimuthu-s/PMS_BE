package com.hyundai.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.ProjectManagerTransaction;

@Repository
public interface ProjectManagerTransactionRepository extends JpaRepository<ProjectManagerTransaction, Integer>{
	
	@Query(value = "select * from project_manager_transaction where manager_id=?1", nativeQuery = true)
	List<ProjectManagerTransaction> getManagerAvaiableProjects(String manager);

}
