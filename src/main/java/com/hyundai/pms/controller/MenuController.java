package com.hyundai.pms.controller;

import java.util.List;
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
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.MenuService;
import com.hyundai.pms.webModel.PaginationWebModel;

@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService ms;


	@PostMapping("/getAllMenu")
	public Response getAllMenu(@RequestBody PaginationWebModel paginationWebModel) {
		return ms.getAllMenu(paginationWebModel);
	}

	@GetMapping("/getMenuById/{id}")
	public Response getMenuById(@PathVariable int id) {
		Optional<MenuMaster> menu = ms.getMenuById(id);
		return new Response(1, "Success", menu);

	}

	@PostMapping("/addMenu")
	public Response addMenu(@RequestBody MenuMaster menu) {
		ms.addMenu(menu);
		return new Response(1, "Success", menu);
	}

	@PutMapping("/updateMenu")
	public Response updateMenu(@RequestBody MenuMaster menu) {
		ms.updateMenu(menu);
		return new Response(1, "Success", menu);
	}

	@DeleteMapping("/deleteMenu/{id}")
	public Response deleteMenu(@PathVariable int id) {
		ms.deleteMenu(id);
		return new Response(1, "Success", id);
	}

}
