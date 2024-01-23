package com.hyundai.pms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
      

}
