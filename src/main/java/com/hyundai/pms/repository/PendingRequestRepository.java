package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.PendingRequest;

@Repository
public interface PendingRequestRepository extends JpaRepository<PendingRequest, Integer>{
	
	@Query(value = "select pending_id as pendingRequestId, emp_month_id as empMonthId, "
			+ "manager_id as managerId, status as status, date as date from pending_request where status=?1 order by pending_id", nativeQuery = true)
	List<Map<String, Object>> searchPendingRequests(String status);

}
