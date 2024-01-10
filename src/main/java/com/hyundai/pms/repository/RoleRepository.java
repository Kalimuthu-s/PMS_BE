package com.hyundai.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.RoleMaster;



@Repository
public interface RoleRepository extends JpaRepository<RoleMaster, Long>{

}