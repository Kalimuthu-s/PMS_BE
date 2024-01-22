package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.ProjectManagerTransaction;

@Repository
public interface ProjectManagerTransactionRepository extends JpaRepository<ProjectManagerTransaction, Integer>{
	
	@Query(value = "select pmt.proj_man_trans_id, p.project_name as projectName, e.first_name as managerName, pmt.start_date as startDate, pmt.end_date as endDate, pmt.status from project_manager_transaction pmt inner join project_master p on pmt.project_id=p.project_id "
			+ "inner join employee_master e on pmt.manager_id=e.emp_id",nativeQuery = true)
	List<Map<String, Object>> getAllProjectManagerTransaction();
	
	@Query(value = "select pmt.proj_man_trans_id, p.project_name as projectName, pmt.manager_id, pmt.start_date as startDate, pmt.end_date as endDate, pmt.status from project_manager_transaction pmt \r\n"
			+ "inner join project_master p on pmt.project_id=p.project_id where pmt.manager_id=?1", nativeQuery = true)
	List<Map<String, Object>> getManagerAvaiableProjects(String manager);
	
	
	@Query(value = "select * from project_manager_transaction where proj_man_trans_id=?1", nativeQuery = true)
	ProjectManagerTransaction getOneById(int id);

}
