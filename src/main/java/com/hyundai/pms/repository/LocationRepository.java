package com.hyundai.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.LocationMaster;


@Repository
public interface LocationRepository extends JpaRepository<LocationMaster,Long>{

}