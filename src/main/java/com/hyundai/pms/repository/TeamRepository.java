package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.TeamMaster;

@Repository
public interface TeamRepository extends JpaRepository<TeamMaster, Integer>{
	
	@Query(value = "select t.team_id as teamId,t.team_name as teamName,t.dept_id as departmentId,t.team_grade teamGrade,d.dept_name as departmentName from team_master t join department_master d on t.dept_id=d.dept_id", nativeQuery = true)
	Page<List<Map<String, Object>>> getAllTeam(Pageable pageable);
}
