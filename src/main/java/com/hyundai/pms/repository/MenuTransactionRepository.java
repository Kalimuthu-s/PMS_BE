package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.MenuTransaction;

@Repository
public interface MenuTransactionRepository extends JpaRepository<MenuTransaction, Integer> {
	
	@Query(value = "select m.menu_name as menuName, m.access as access,m.icon as icon, m.main_menu as mainMenu from menu_transaction mt "
			+ "inner join menu_master m on mt.menu_id=m.menu_id where role_id=?1", nativeQuery = true )
	List<Map<String, Object>> getDynamicMenuByRole(int roleId);
}
