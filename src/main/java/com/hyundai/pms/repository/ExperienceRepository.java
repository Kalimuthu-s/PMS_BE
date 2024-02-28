package com.hyundai.pms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.ExperienceMaster;


@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceMaster,Integer> {	
	
	@Query("SELECT e FROM ExperienceMaster e WHERE e.experienceLevel LIKE %:searchKey% OR e.noOfYears LIKE %:searchKey%")
	Page<List<ExperienceMaster>> getAllExperiences(Pageable pageable, String searchKey);

}