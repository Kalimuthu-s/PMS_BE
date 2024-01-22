package com.hyundai.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.ProjectMaster;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectMaster, Integer>{
	
	@Query(value = "select * from project_master where project_name like %?1%", nativeQuery = true)
	List<ProjectMaster> searchProjectByName(String projectname);
	

}
