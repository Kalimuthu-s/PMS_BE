package com.hyundai.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.ProjectEmployeeTransaction;

@Repository
public interface ProjectEmployeeTransactionRepository extends JpaRepository<ProjectEmployeeTransaction, Integer> {

}
