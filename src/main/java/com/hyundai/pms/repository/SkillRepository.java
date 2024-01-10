package com.hyundai.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.SkillMaster;


@Repository
public interface SkillRepository extends JpaRepository<SkillMaster, Integer> {

}