package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.MonthlyEntries;

@Repository
public interface MonthlyEntriesRepository extends JpaRepository<MonthlyEntries, Integer> {

	@Query(value = "SELECT m.month_id as monthlyId,m.emp_id as emp_id,concat(e.first_name,' ',e.last_name) as employeeName, m.project_id as projectId,p.project_name as projectName,"
			+ "m.january as january,m.february as february,m.march as march,m.april as april,m.may as may,m.june as june,m.july as july,"
			+ "m.august as august,m.september as september,m.october as october,m.november as november,m.december as december,m.year as year "
			+ "FROM monthly_entries m inner join employee_master e on m.emp_id=e.emp_id inner join project_master p on m.project_id=p.project_id where m.project_id=?1", nativeQuery = true)
	List<Map<String, Object>> findByProjectId(int projectId);

	@Query(value = "SELECT m.month_id as monthlyId,m.emp_id as emp_id,concat(e.first_name,' ',e.last_name) as employeeName, m.project_id as projectId,p.project_name as projectName,"
			+ "m.january as january,m.february as february,m.march as march,m.april as april,m.may as may,m.june as june,m.july as july,"
			+ "m.august as august,m.september as september,m.october as october,m.november as november,m.december as december,m.year as year "
			+ "FROM monthly_entries m inner join employee_master e on m.emp_id=e.emp_id inner join project_master p on m.project_id=p.project_id", nativeQuery = true)
	List<Map<String, Object>> getAllMonthlyEntries();

	@Query(value = "select * from monthly_entries where emp_id=?1 and year=?2", nativeQuery = true)
	List<MonthlyEntries> findByEmp_idAndYear(int empId, String year);

	@Query(value = "SELECT " + "m.emp_id as employeeId, " + "concat(e.first_name,' ',e.last_name) as employeeName, "
			+ "SUM(m.january) as januaryTotal, " + "SUM(m.february) as februaryTotal, " + "SUM(m.march) as marchTotal, "
			+ "SUM(m.april) as aprilTotal, " + "SUM(m.may) as mayTotal, " + "SUM(m.june) as juneTotal, "
			+ "SUM(m.july) as julyTotal, " + "SUM(m.august) as augustTotal, " + "SUM(m.september) as septemberTotal, "
			+ "SUM(m.october) as octoberTotal, " + "SUM(m.november) as novemberTotal, "
			+ "SUM(m.december) as decemberTotal, " + "m.year as year, "
			+ "(SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) as yearlyTotal, "
			+ "ROUND((SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) / 12.0, 2)*100 as yearlyPercentage "
			+ "FROM monthly_entries m " + "INNER JOIN employee_master e ON m.emp_id = e.emp_id "
			+ "INNER JOIN project_master p ON m.project_id = p.project_id "
			+ "GROUP BY m.emp_id, e.first_name, e.last_name, m.year", nativeQuery = true)
	List<Map<String, Object>> getAllConsolidated();
	
	
//	List<Map<String, Object>> monthlyEntriesFilter();

}
