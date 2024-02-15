package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.SkillTransactionMaster;

@Repository
public interface SkillTransactionRepository extends JpaRepository<SkillTransactionMaster, Integer> {

	@Query(value = "DELETE FROM skill_transaction WHERE emp_id = :empId", nativeQuery = true)
	@Modifying
	@Transactional
	void deleteByEmployeeId(@Param("empId") Long empId);
	
	@Query(value = "select t.skill_trans_id as skillTransactionId, concat(e.first_name,' ',e.last_name) as employeeName, "
			+ "s.skill_name as skillName, t.proficiency_level as proficiencyLevel from skill_transaction t "
			+ "inner join employee_master e on t.emp_id=e.emp_id "
			+ "inner join skill_master s on t.skill_id=s.skill_id where t.skill_id=?1 and t.proficiency_level=?2", nativeQuery = true)
	List<Map<String, Object>> getAllSkillByProficiency(String skill,String proficiency);


}
