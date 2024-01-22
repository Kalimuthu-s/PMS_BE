package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.EmployeeMaster;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeMaster, Integer>{
	

	Optional<EmployeeMaster> findByEmail(String email);

	@Query(value = "SELECT e.emp_id as emp_id,e.first_name as firstname,e.last_name as lastname, d.designation_name as designation_name FROM employee_master e JOIN designation_master d "
			+ "ON e.designation_id = d.designation_id WHERE d.designation_name LIKE %?1%", nativeQuery = true)
	public List<Map<String, Object>> findManagers(String para);

	@Query(value = "SELECT e.emp_id as emp_id,e.email as email,e.first_name as firstname,e.last_name as lastname,d.designation_name as designation_name,d.job_role FROM employee_master e INNER JOIN designation_master d ON e.designation_id = d.designation_id", nativeQuery = true)
	List<Map<String, Object>> findAllEmployees();

	@Query(value = "SELECT d.designation_id as designation,e.phone_number as phoneNumber,e.emp_id as emp_id,e.email as email,e.first_name as first_name,e.last_name as last_name,e.date_of_birth as date_of_birth,e.gender as gender,e.hire_date as hire_date,"
			+ "d.designation_name as designation_name,d.job_role,dm.dept_id as department,dm.dept_name as department_name,"
			+ "l.location_id as location,l.location_name as location_name,t.team_id as team,"
			+ "t.team_name as team_name,em.exp_id as experience_id,em.exp_level as exp_level,"
			+ "em.no_of_years as no_of_years,e_manager.email as manager_email,"
			+ "e_manager.first_name as manager_firstname,e_manager.last_name as manager_lastname,e.manager_id as manager "
			+ "FROM employee_master e INNER JOIN designation_master d ON e.designation_id = d.designation_id INNER JOIN "
			+ "location_master l ON e.location_id = l.location_id INNER JOIN department_master dm ON e.dept_id = dm.dept_id "
			+ "INNER JOIN team_master t ON e.team_id = t.team_id INNER JOIN experience_master em ON e.exp_id = em.exp_id LEFT JOIN "
			+ "employee_master e_manager ON e_manager.emp_id = e.manager_id WHERE e.emp_id=:emp_id", nativeQuery = true)
	List<Map<String, Object>> findByEmployeeId(Integer emp_id);

}
