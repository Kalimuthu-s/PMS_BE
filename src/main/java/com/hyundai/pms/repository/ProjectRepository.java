package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.ProjectMaster;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectMaster, Integer>{
	
	@Query(value = "select * from project_master where project_name like ?1%", nativeQuery = true)
	List<ProjectMaster> searchProjectByName(String projectname);
	
	@Query(value = "select p.project_id as projectId,p.customer_id as customerId,p.manager_id as managerId,p.start_date as startDate,"
			+ "c.cust_name as customerName, concat(e.first_name,' ',e.last_name) as managerName, p.project_name as projectName,"
			+ "p.end_date as endDate,p.status as status from project_master p inner join customer_master c on p.customer_id=c.cust_id "
			+ "inner join employee_master e on p.manager_id=e.emp_id",nativeQuery = true)
	List<Map<String, Object>> findAllProject();
	

}
