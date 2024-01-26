package com.hyundai.pms.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.DepartmentMaster;


@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentMaster, Integer>{
	
	@Query("SELECT d.departmentId as departmentId, d.departmentName as departmentName, d.grade as grade,e.emp_id as managerId, e.first_name as managerName FROM DepartmentMaster d INNER JOIN EmployeeMaster e ON d.managerId = e.emp_id")
	Page<Map<String , Object>> findAllDept(Pageable pageable);

}