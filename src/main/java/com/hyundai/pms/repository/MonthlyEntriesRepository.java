package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Query(value = "SELECT ANY_VALUE(m.month_id) as monthlyId, ANY_VALUE(m.emp_id) as emp_id,concat(ANY_VALUE(e.first_name),' ',ANY_VALUE(e.last_name)) as employeeName, ANY_VALUE(m.project_id) as projectId,p.project_name as projectName,"
			+ "ANY_VALUE(m.january) as sumOfJanuary,ANY_VALUE(m.february) as sumOfFebruary,ANY_VALUE(m.march) as sumOfMarch,ANY_VALUE(m.april) as sumOfApril,ANY_VALUE(m.may) as sumOfMay,ANY_VALUE(m.june) as sumOfJune,ANY_VALUE(m.july) as sumOfJuly,"
			+ "ANY_VALUE(m.august) as sumOfAugust,ANY_VALUE(m.september) as sumOfSeptember,ANY_VALUE(m.october) as sumOfOctober,ANY_VALUE(m.november) as sumOfNovember,ANY_VALUE(m.december) as sumOfDecember, "
			+ "SUM(m.january + m.february + m.march + m.april + m.may + m.june + m.july "
			+ "+ m.august + m.september + m.october + m.november + m.december ) as total "
			+ "FROM monthly_entries m inner join employee_master e on m.emp_id=e.emp_id inner join project_master p on m.project_id=p.project_id where m.emp_id=?1 "
			+ "group by p.project_name", nativeQuery = true)
	List<Map<String, Object>> findByEmployeeId(String employeeId);

	@Query(value = "SELECT m.month_id as monthlyId,m.emp_id as emp_id,concat(e.first_name,' ',e.last_name) as employeeName, m.project_id as projectId,p.project_name as projectName,"
			+ "m.january as january,m.february as february,m.march as march,m.april as april,m.may as may,m.june as june,m.july as july,"
			+ "m.august as august,m.september as september,m.october as october,m.november as november,m.december as december,m.year as year "
			+ "FROM monthly_entries m inner join employee_master e on m.emp_id=e.emp_id inner join project_master p on m.project_id=p.project_id", nativeQuery = true)
	List<Map<String, Object>> getAllMonthlyEntries();
	
	@Query(value = "SELECT m.month_id as monthlyId,m.emp_id as emp_id,concat(e.first_name,' ',e.last_name) as employeeName, m.project_id as projectId,p.project_name as projectName,"
			+ "m.january as january,m.february as february,m.march as march,m.april as april,m.may as may,m.june as june,m.july as july,"
			+ "m.august as august,m.september as september,m.october as october,m.november as november,m.december as december,m.year as year "
			+ "FROM monthly_entries m inner join employee_master e on m.emp_id=e.emp_id inner join project_master p on m.project_id=p.project_id ", nativeQuery = true)
	Page<Map<String, Object>> getAllMonthlyEntry(Pageable pageable);

	@Query(value = "select * from monthly_entries where emp_id=?1 and year=?2 and month_id!=?3", nativeQuery = true)
	List<MonthlyEntries> findByEmpIdAndYear(Long empId, int year, int monthId);

	@Query(value = "SELECT " + "m.emp_id as employeeId, "
			+ "concat(e.first_name,' ',e.last_name) as employeeName, e.hire_date as hireDate, "
			+ "SUM(m.january) as januaryTotal, " + "SUM(m.february) as februaryTotal, " + "SUM(m.march) as marchTotal, "
			+ "SUM(m.april) as aprilTotal, " + "SUM(m.may) as mayTotal, " + "SUM(m.june) as juneTotal, "
			+ "SUM(m.july) as julyTotal, " + "SUM(m.august) as augustTotal, " + "SUM(m.september) as septemberTotal, "
			+ "SUM(m.october) as octoberTotal, " + "SUM(m.november) as novemberTotal, "
			+ "SUM(m.december) as decemberTotal, " + "m.year as year, "
			+ "(SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) as yearlyTotal, "
			+ "ROUND((SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) / 12.0, 2)*100 as yearlyPercentage "
			+ "FROM monthly_entries m " + "INNER JOIN employee_master e ON m.emp_id = e.emp_id "
			+ "INNER JOIN project_master p ON m.project_id = p.project_id where m.year=2024 "
			+ "GROUP BY m.emp_id, e.first_name, e.last_name, m.year", nativeQuery = true)
	List<Map<String, Object>> getAllConsolidated();
	
	@Query(value = "SELECT " + "m.emp_id as employeeId, "
			+ "concat(e.first_name,' ',e.last_name) as employeeName, e.hire_date as hireDate, "
			+ "SUM(m.january) as januaryTotal, " + "SUM(m.february) as februaryTotal, " + "SUM(m.march) as marchTotal, "
			+ "SUM(m.april) as aprilTotal, " + "SUM(m.may) as mayTotal, " + "SUM(m.june) as juneTotal, "
			+ "SUM(m.july) as julyTotal, " + "SUM(m.august) as augustTotal, " + "SUM(m.september) as septemberTotal, "
			+ "SUM(m.october) as octoberTotal, " + "SUM(m.november) as novemberTotal, "
			+ "SUM(m.december) as decemberTotal, " + "m.year as year, "
			+ "(SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) as yearlyTotal, "
			+ "ROUND((SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) / 12.0, 2)*100 as yearlyPercentage "
			+ "FROM monthly_entries m " + "INNER JOIN employee_master e ON m.emp_id = e.emp_id "
			+ "INNER JOIN project_master p ON m.project_id = p.project_id where m.year=2024 "
			+ "GROUP BY m.emp_id, e.first_name, e.last_name, m.year", nativeQuery = true)
	Page<Map<String, Object>> getAllConsolidatedData(Pageable pageable);

	@Query(value = "select p.project_name as projectName, "+
					"SUM(m.january + m.february + m.march + m.april + m.may + m.june + "+
					"m.july + m.august + m.september + m.october + m.november + m.december ) as total, "+
					"SUM(m.january) as sumOfJanuary, SUM(m.february) as sumOfFebruary, SUM(m.march) as sumOfMarch, "+
					"SUM(m.april) as sumOfApril, SUM(m.may) as sumOfMay, SUM(m.june) as sumOfJune, "+
					"SUM(m.july) as sumOfJuly, SUM(m.august) as sumOfAugust, SUM(m.september) as sumOfSeptember, "+
					"SUM(m.october) as sumOfOctober, SUM(m.november) as sumOfNovember, SUM(m.december) as sumOfDecember "+
					"from monthly_entries m inner join project_master p on m.project_id=p.project_id "+
					"where m.project_id=?1 group by p.project_name",nativeQuery = true)
	List<Map<String, Object>> projectUtilizationFilter(String projectId);
	
	@Query(value = "select SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + "
			+ "SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december) as grandTotal "
			+ "FROM monthly_entries m",nativeQuery = true)
	List<Map<String, Object>> getGrandTotal();
	
	@Query(value = "select p.project_name as projectName, "+
			"SUM(m.january + m.february + m.march + m.april + m.may + m.june + "+
			"m.july + m.august + m.september + m.october + m.november + m.december ) as total, "+
			"SUM(m.january) as sumOfJanuary, SUM(m.february) as sumOfFebruary, SUM(m.march) as sumOfMarch, "+
			"SUM(m.april) as sumOfApril, SUM(m.may) as sumOfMay, SUM(m.june) as sumOfJune, "+
			"SUM(m.july) as sumOfJuly, SUM(m.august) as sumOfAugust, SUM(m.september) as sumOfSeptember, "+
			"SUM(m.october) as sumOfOctober, SUM(m.november) as sumOfNovember, SUM(m.december) as sumOfDecember "+
			"from monthly_entries m inner join project_master p on m.project_id=p.project_id "+
			"group by p.project_name",nativeQuery = true)
Page<Map<String, Object>>  getAllProjectUtilization(Pageable pageable);
	

}
