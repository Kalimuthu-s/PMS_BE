package com.hyundai.pms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.DesignationMaster;

@Repository
public interface DesignationRepository extends JpaRepository<DesignationMaster, Integer>{
	
	@Query("SELECT d from DesignationMaster d where designationName LIKE %:searchKey% or jobRole LIKE %:searchKey%")
	Page<List<DesignationMaster>> getAllDesignation(Pageable pageable, String searchKey);

}