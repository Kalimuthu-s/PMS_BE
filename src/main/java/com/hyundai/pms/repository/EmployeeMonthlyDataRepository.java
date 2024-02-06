package com.hyundai.pms.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.EmployeeMonthlyData;

@Repository
public interface EmployeeMonthlyDataRepository extends JpaRepository<EmployeeMonthlyData, Long>{
	
//	List<EmployeeMonthlyData> findByEmployeeIdAndMonth(Long employeeId, LocalDate month);
//    // Custom queries if needed
//    @Query(value="SELECT concat(e.first_name, ' ', e.last_name) as employeeName,m.emp_id as empId, "
//    		+ "m.project_id as projectId,p.project_name as projectName,m.year as year, "
//    		+ "SUM(CASE WHEN MONTH(month) = 1 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS january, "
//    		+ "SUM(CASE WHEN MONTH(month) = 2 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS february, "
//    		+ "SUM(CASE WHEN MONTH(month) = 3 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS march, "
//    		+ "SUM(CASE WHEN MONTH(month) = 4 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS april, "
//    		+ "SUM(CASE WHEN MONTH(month) = 5 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS may, "
//    		+ "SUM(CASE WHEN MONTH(month) = 6 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS june, "
//    		+ "SUM(CASE WHEN MONTH(month) = 7 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS july, "
//    		+ "SUM(CASE WHEN MONTH(month) = 8 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS august, "
//    		+ "SUM(CASE WHEN MONTH(month) = 9 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS september, "
//    		+ "SUM(CASE WHEN MONTH(month) = 10 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS october, "
//    		+ "SUM(CASE WHEN MONTH(month) = 11 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS november, "
//    		+ "SUM(CASE WHEN MONTH(month) = 12 THEN CAST(contribution AS DECIMAL(10, 2)) ELSE 0 END) AS december "
//    		+ "FROM employee_monthly_data m "
//    		+ "INNER JOIN employee_master e ON e.emp_id = m.emp_id "
//    		+ "INNER JOIN project_master p ON p.project_id = m.project_id "
//    		+ "GROUP BY m.year, m.emp_id, p.project_id order by m.emp_month_id desc",nativeQuery = true)
//    List<Map<String, Object>> getAllEmployeeMonthlyDataWithJoin();
//    
//    @Query("SELECT e FROM EmployeeMonthlyData e WHERE e.employeeId=:employeeId AND e.ProjectId=:projectId")
//    List<EmployeeMonthlyData> getByEmployeeIdAndMonth(Long employeeId, Long projectId,Pageable pageable);
//
//    @Query("DELETE FROM EmployeeMonthlyData e WHERE e.employeeId=:employeeId AND e.ProjectId=:projectId")
//    @Modifying
//    @Transactional
//	void deleteEmployeeMonthData(Long employeeId,Long projectId);

}
