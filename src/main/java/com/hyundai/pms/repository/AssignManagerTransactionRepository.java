package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.AssignManagerTransaction;

@Repository
public interface AssignManagerTransactionRepository extends JpaRepository<AssignManagerTransaction, Integer> {

	@Query(value = "select pmt.proj_man_trans_id, pmt.project_id as projectId, p.project_name as projectName, "
			+ "e.first_name as managerName,e.emp_id as managerId, pmt.start_date as startDate, pmt.end_date as endDate, "
			+ "pmt.status from assign_manager_transaction pmt left join project_master p on p.project_id=pmt.project_id "
			+ "left join employee_master e on e.emp_id=pmt.manager_id where p.project_name LIKE %:searchKey% "
			+ "OR e.first_name LIKE %:searchKey% OR pmt.start_date LIKE %:searchKey% OR pmt.end_date LIKE %:searchKey% "
			+ "OR pmt.status LIKE %:searchKey%", nativeQuery = true)
	Page<List<Map<String, Object>>> getAllProjectManagerTransaction(Pageable pageable,String searchKey);

	@Query(value = "select pmt.proj_man_trans_id, pmt.project_id as projectId, p.project_name as projectName, pmt.manager_id as managerId, e.first_name as managerName, pmt.start_date as startDate, pmt.end_date as endDate, pmt.status from assign_manager_transaction pmt "
			+ "inner join employee_master e on pmt.manager_id=e.emp_id inner join project_master p on pmt.project_id=p.project_id where pmt.manager_id=?1", nativeQuery = true)
	List<Map<String, Object>> getManagerAvaiableProjects(String manager);

	@Query(value = "select * from assign_manager_transaction where proj_man_trans_id=?1", nativeQuery = true)
	AssignManagerTransaction getOneById(int id);
	
	@Query(value = "select pmt.proj_man_trans_id, c.cust_name as customerName,pmt.project_id as projectId, p.project_name as projectName, "
			+ "e.first_name as managerName,e.emp_id as managerId, pmt.start_date as startDate, pmt.end_date as endDate, "
			+ "pmt.status from assign_manager_transaction pmt inner join project_master p on p.project_id=pmt.project_id "
			+ "inner join customer_master c on c.cust_id=p.customer_id "
			+ "inner join employee_master e on e.emp_id=pmt.manager_id", nativeQuery = true)
	List<Map<String, Object>> getAllProjectManagerWithoutPagination();

}
