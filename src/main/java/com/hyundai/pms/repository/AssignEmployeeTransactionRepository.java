package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.AssignEmployeeTransaction;

@Repository
public interface AssignEmployeeTransactionRepository extends JpaRepository<AssignEmployeeTransaction, Integer> {

	@Query(value = "select t.proj_emp_id as projectEmpId, concat(e.first_name,' ',e.last_name) as employeeName,"
			+ " p.project_name as projectName, t.project_id as projectId, t.employee_id as employeeId, "
			+ "t.assigned_start_date as assignedStartDate, t.assigned_end_date as assignedEndDate from "
			+ "assign_employee_transaction t inner join project_master p on t.project_id = p.project_id "
			+ "inner join employee_master e on t.employee_id = e.emp_id order by t.proj_emp_id desc", nativeQuery = true)
	Page<Map<String, Object>> getAllEmployeeTransaction(Pageable pageable,String searchKey);

	@Query(value = "select t.proj_emp_id as projectEmpId, concat(e.first_name,' ',e.last_name) as employeeName,"
			+ " p.project_name as projectName, t.project_id as projectId, t.employee_id as employeeId, "
			+ "t.assigned_start_date as assignedStartDate, t.assigned_end_date as assignedEndDate from "
			+ "assign_employee_transaction t inner join project_master p on t.project_id = p.project_id "
			+ "inner join employee_master e on t.employee_id = e.emp_id "+
			"WHERE e.first_name LIKE %:searchKey%  " +
            "OR e.last_name LIKE %:searchKey% " +
            "OR p.project_name LIKE %:searchKey% " +
            "OR t.assigned_start_date LIKE %:searchKey% " +
            "OR t.assigned_end_date LIKE %:searchKey% " +
			"order by t.proj_emp_id desc", nativeQuery = true)
	Page<Map<String, Object>> getAllEmployeeTransactions(Pageable pageable,String searchKey);


	@Query(value = "select t.proj_emp_id as projectEmpId, concat(e.first_name,' ',e.last_name) as employeeName,"
			+ " p.project_name as projectName, "
			+ "t.assigned_start_date as assignedStartDate, t.assigned_end_date as assignedEndDate from "
			+ "assign_employee_transaction t inner join project_master p on t.project_id = p.project_id "
			+ "inner join employee_master e on t.employee_id = e.emp_id", nativeQuery = true)
	List<Map<String, Object>> getAssignedEmployees();

//	@Query(value = "select * from assign_employee_transaction where assigned_start_date>=?1 = assigned_end_date=?2",nativeQuery = true)
//	List<Map<String, Object>> getEmployeesByDate(String startDate,String endDate);

boolean existsByEmployeeIdAndProjectId(Long employeeId, int projectId);

}
