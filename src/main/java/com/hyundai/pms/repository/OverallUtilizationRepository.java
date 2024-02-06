package com.hyundai.pms.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.OverallUtilization;

@Repository
public interface OverallUtilizationRepository extends JpaRepository<OverallUtilization, Integer> {
	
	  @Query(value = "SELECT emp.emp_id as employeeId, emp.first_name as firstName, emp.last_name as lastName,p.project_id as projectId, "
	            + "p.project_name as projectName, SUM(nt.contribution) as contribution, nt.months as month "
	            + "FROM employee_monthly_data nt "
	            + "INNER JOIN employee_master emp ON nt.emp_id = emp.emp_id "
	            + "INNER JOIN project_master p ON nt.project_id = p.project_id "
	            + "GROUP BY emp.emp_id, emp.first_name, emp.last_name, p.project_name, nt.months,p.project_id", nativeQuery = true)
	 List<Map<String, Object>> getAllEmployeeOverallUtilizationDetails();
	  
//		@Query(value = "", nativeQuery = true)
//		List<Map<String, Object>> getAllEmployeeUtilizedData(int empid,int projectid,Date startDate, Date endDate);
		
		@Query(value = "SELECT emd.emp_id as employeeId, emd.project_id as projectId, p.project_name as projectName, emp.first_name as managerName, " +
		        "emd.months as months, emd.contribution as contribution " +
		        "FROM employee_monthly_data emd " +
		        "INNER JOIN employee_master emp on emd.emp_id=emp.emp_id " +
		        "INNER JOIN project_master p ON p.project_id = emd.project_id " +
		        "WHERE (emd.months BETWEEN Date(:startDate) AND Date(:endDate)) AND p.project_id = :projectid", nativeQuery = true)
	    List<Map<String, Object>> getdatabyProjects(int projectid,Date startDate,Date endDate);
	
//	
//	@Query(value = "SELECT emd.emp_id as employeeId,emd.project_id as projectId,p.project_name as projectName,emp.first_name as managerName,emd.months as month,emd.contribution as contribution"
//			+ " FROM employee_monthly_data emd INNER JOIN employee_master emp on emd.emp_id=emp.emp_id "
//			+ "INNER JOIN project_master p ON p.project_id=emd.project_id WHERE (month>=:startDate AND month<=:endDate) OR (emp.emp_id=:empid) OR p.project_id=:projectid)", nativeQuery = true)
//	List<Map<String, Object>> getAllEmployeeUtilizedData(int empid,int projectid,Date startDate, Date endDate);
	
//	@Query(value = "SELECT emd.emp_id as employeeId,emd.project_id as projectId,p.project_name as projectName,emp.first_name as managerName,emd.month as month,emd.contribution as contribution"
//			+ " FROM employee_monthly_data emd INNER JOIN employee_master emp on emd.emp_id=emp.emp_id "
//			+ "INNER JOIN project_master p ON p.project_id=emd.project_id WHERE month>=:startDate AND month<=:endDate", nativeQuery = true)
//	List<Map<String, Object>> getAllEmployeeUtilizedDat(Date startDate, Date endDate);
	
	
	

}
