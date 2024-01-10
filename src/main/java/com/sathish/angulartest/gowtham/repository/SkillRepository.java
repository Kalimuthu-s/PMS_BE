package com.sathish.angulartest.gowtham.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathish.angulartest.gowtham.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
