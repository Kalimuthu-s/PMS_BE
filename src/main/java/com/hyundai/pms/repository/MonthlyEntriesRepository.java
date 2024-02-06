package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.MonthlyEntries;

@Repository
public interface MonthlyEntriesRepository extends JpaRepository<MonthlyEntries, Integer> {
	
	@Query(value = "SELECT m.month_id as month_id,m.emp_id as emp_id,concat(e.first_name,' ',e.last_name) as employeeName, m.project_id as project_id,p.project_name as projectName,"
	 		 + "m.january as january,m.february as february,m.march as march,m.april as april,m.may as may,m.june as june,m.july as july,"
			 + "m.august as august,m.september as september,m.october as october,m.november as november,m.december as december,m.year as year "
			 + "FROM monthly_entries m inner join employee_master e on m.emp_id=e.emp_id inner join project_master p on m.project_id=p.project_id where m.project_id=?1",nativeQuery = true)
	List<Map<String, Object>> findByProjectId(int projectId);
 
 @Query(value = "SELECT m.month_id as month_id,m.emp_id as emp_id,concat(e.first_name,' ',e.last_name) as employeeName, m.project_id as project_id,p.project_name as projectName,"
 		 + "m.january as january,m.february as february,m.march as march,m.april as april,m.may as may,m.june as june,m.july as july,"
		 + "m.august as august,m.september as september,m.october as october,m.november as november,m.december as december,m.year as year "
		 + "FROM monthly_entries m inner join employee_master e on m.emp_id=e.emp_id inner join project_master p on m.project_id=p.project_id",nativeQuery = true)
 List<Map<String, Object>> getAllMonthlyEntries();
}
