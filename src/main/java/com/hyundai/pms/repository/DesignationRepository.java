package com.hyundai.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.DesignationMaster;

@Repository
public interface DesignationRepository extends JpaRepository<DesignationMaster, Integer>{

}