package com.hyundai.pms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.CustomerMaster;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerMaster,Long> {
	
	@Query("SELECT c FROM CustomerMaster c WHERE c.customerName LIKE %:searchKey% OR c.customerLocation LIKE %:searchKey% OR c.partner LIKE %:searchKey%")
	Page<List<CustomerMaster>> getAllCustomers(Pageable pageable,String searchKey);



}