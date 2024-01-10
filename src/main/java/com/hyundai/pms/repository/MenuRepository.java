package com.hyundai.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.MenuMaster;

@Repository
public interface MenuRepository extends JpaRepository<MenuMaster, Integer>{

}
