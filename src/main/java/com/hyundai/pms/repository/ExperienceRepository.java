package com.hyundai.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.ExperienceMaster;


@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceMaster,Long> {

}