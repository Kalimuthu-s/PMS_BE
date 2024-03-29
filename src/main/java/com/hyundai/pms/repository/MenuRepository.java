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
	
	@Query("SELECT m FROM MenuMaster m WHERE menuName LIKE %:searchKey% OR access LIKE %:searchKey% OR icon LIKE %:searchKey% OR mainMenu LIKE %:searchKey%")
	Page<List<MenuMaster>> getAllMenu(Pageable pageable, String searchKey);


}
