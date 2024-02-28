package com.hyundai.pms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.SkillMaster;


@Repository
public interface SkillRepository extends JpaRepository<SkillMaster, Integer> {
	
	@Query(value = "select * from skill_master where skill_name like %?1% or skill_category like %?1%",nativeQuery = true)
	Page<List<SkillMaster>> findAllSkills(Pageable pageable, String searckKey);

}