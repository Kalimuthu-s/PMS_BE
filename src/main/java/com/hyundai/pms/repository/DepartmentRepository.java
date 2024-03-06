package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.DepartmentMaster;


@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentMaster, Integer>{
	
//	@Query("SELECT " +
//		       "d.departmentId as departmentId, " +
//		       "d.departmentName as departmentName, " +
//		       "d.grade as grade, " +
//		       "e.emp_id as managerId, " +
//		       "e.first_name as managerName " +
//		       "FROM DepartmentMaster d INNER JOIN EmployeeMaster e " +
//		       "WHERE (d.departmentId LIKE %:searchKey% " +
//		       "OR d.departmentName LIKE %:searchKey% " +
//		       "OR d.grade LIKE %:searchKey% " +
//		       "OR e.emp_id LIKE %:searchKey%) " +
//		       "ORDER BY d.departmentId")
//	Page<List<DepartmentMaster>> findAllDept(Pageable pageable, String searchKey);

	
	@Query("SELECT " +
			"d.departmentId as departmentId, " +
		       "d.departmentName as departmentName, e.emp_id as headOfDepartmentId, " +
		       "e.first_name as headOfDepartment " +
		       "FROM DepartmentMaster d INNER JOIN EmployeeMaster e with d.headOfDepartment=e.emp_id "
		       + "WHERE (d.departmentId LIKE %:searchKey% "+
		       "OR d.departmentName LIKE %:searchKey% " +
		       "OR e.first_name LIKE %:searchKey%) " +
		       "ORDER BY d.departmentId")
		Page<List<Map<String, Object>>> findAllDept(Pageable pageable, String searchKey);

	
//	public List<Map<String, Object>> fetchData(String searchKey) 
//	{ 
//		List<Map<String, Object>> resultList; 
//		if (searchKey != null && !searchKey.isEmpty()) 
//		{ 
//			resultList = yourRepository.findBySearchKey(searchKey); 
//			
//		} else { 
//			
//			resultList = yourRepository.findAll(); } return resultList; 
//			} 


}