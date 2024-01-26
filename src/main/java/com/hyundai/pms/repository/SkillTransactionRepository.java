package com.hyundai.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.SkillTransactionMaster;

@Repository
public interface SkillTransactionRepository extends JpaRepository<SkillTransactionMaster, Integer>{

}
