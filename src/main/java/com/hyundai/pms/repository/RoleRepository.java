package com.hyundai.pms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.RoleMaster;



@Repository
public interface RoleRepository extends JpaRepository<RoleMaster, Long>{
	
	@Query("SELECT r FROM RoleMaster r WHERE roleName LIKE %:searchKey%")
	Page<List<RoleMaster>> findAllRoles(Pageable pageable, String searchKey);

}