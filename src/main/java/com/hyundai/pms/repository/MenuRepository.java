package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.MenuMaster;

@Repository
public interface MenuRepository extends JpaRepository<MenuMaster, Integer>{

	@Query("SELECT m.menuName as menuName,r.roleName as roleId FROM MenuMaster m INNER JOIN RoleMaster r ON m.roleId=r.roleId")
	Page<List<Map<String, Object>>> getAllMenuDetails(Pageable pageable);
	
	@Query("SELECT p from MenuMaster p WHERE p.roleId=:role")
	List<MenuMaster>getMenuByRole(String role);

}
