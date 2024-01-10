package com.sathish.angulartest.gowtham.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathish.angulartest.gowtham.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
