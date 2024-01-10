package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.MenuMaster;
import com.hyundai.pms.repository.MenuRepository;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepository mr;
	
	public List<MenuMaster> getAllMenu(){
		return mr.findAll();
	}
	
	public Optional<MenuMaster> getMenuById(int id) {
		return mr.findById(id);
	}
	
	public MenuMaster addMenu(MenuMaster menu) {
		return mr.save(menu);
	}
	
	public MenuMaster updateMenu(MenuMaster menu) {
		return mr.save(menu);
	}
	
	public void deleteMenu(int id) {
		mr.deleteById(id);
	}

}
