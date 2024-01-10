package com.hyundai.pms.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.MenuMaster;
import com.hyundai.pms.entity.UserResponse;
import com.hyundai.pms.service.MenuService;

@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService ms;


	@GetMapping("/getAllMenu")
	public List<Map<String, Object>> getAllMenu() {
		return ms.getAllMenu();
	}

	@GetMapping("/getMenuById/{id}")
	public Optional<MenuMaster> getMenuById(@PathVariable int id) {
		Optional<MenuMaster> menu = ms.getMenuById(id);
		return menu;

	}

	@GetMapping("/getMenuByRole/{role}")
	public List<MenuMaster> getMenuByRole(@PathVariable String role){
		return ms.getMenuByRole(role);
	}

	@PostMapping("/addMenu")
	public MenuMaster addMenu(@RequestBody MenuMaster menu) {
		return ms.addMenu(menu);
	}

	@PutMapping("/updateMenu")
	public MenuMaster updateMenu(@RequestBody MenuMaster menu) {
		return ms.updateMenu(menu);
	}

	@DeleteMapping("/deleteMenu/{id}")
	public UserResponse deleteMenu(@PathVariable int id) {
		ms.deleteMenu(id);
		return new UserResponse(1, "Menu Deleted", null, true);
	}

}
