package com.hyundai.pms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.PendingRequest;

@Repository
public interface PendingRequestRepository extends JpaRepository<PendingRequest, Integer> {

	List<PendingRequest> findByPendingRequestId(Integer pendingRequestId);

	List<PendingRequest> findByEmpMonthId(Integer empMonthId);

	List<PendingRequest> findByManagerId(Integer managerId);

	List<PendingRequest> findByStatus(String status);

	List<PendingRequest> findByDate(Date date);

}
