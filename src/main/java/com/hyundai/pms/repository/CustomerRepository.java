package com.hyundai.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.CustomerMaster;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerMaster,Long> {

}