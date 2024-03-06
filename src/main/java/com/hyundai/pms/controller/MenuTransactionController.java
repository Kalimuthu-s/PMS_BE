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
import com.hyundai.pms.entity.MenuTransaction;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.MenuService;
import com.hyundai.pms.service.MenuTransactionService;
import com.hyundai.pms.webModel.PaginationWebModel;

@CrossOrigin
@RestController
@RequestMapping("/menuTransaction")
public class MenuTransactionController {
	
	@Autowired
	private MenuTransactionService ms;


	@PostMapping("/getAllMenuTransaction")
	public Response getAllMenuTransaction(@RequestBody PaginationWebModel paginationWebModel) {
		return ms.getAllMenuTransaction(paginationWebModel);
	}

	@GetMapping("/getMenuTransactionById/{id}")
	public Response getMenuTransactionById(@PathVariable int id) {
		Optional<MenuTransaction> menu = ms.getMenuTransactionById(id);
		return new Response(1, "Success", menu);
	}
	
	@GetMapping("/getDynamicMenuByRole/{roleId}")
	public Response getDynamicMenuByRole(@PathVariable int roleId) {
		List<Map<String, Object>> list = ms.getDynamicMenuByRole(roleId);
		return new Response(1, "Success", list);
	}

	@PostMapping("/addMenuTransaction")
	public Response addMenuTransaction(@RequestBody MenuTransaction menu) {
		ms.addMenuTransaction(menu);
		return new Response(1, "Success", menu);
	}

	@PutMapping("/updateMenuTransaction")
	public Response updateMenuTransaction(@RequestBody MenuTransaction menu) {
		ms.updateMenuTransaction(menu);
		return new Response(1, "Success", menu);
	}

	@DeleteMapping("/deleteMenuTransaction/{id}")
	public Response deleteMenuTransaction(@PathVariable int id) {
		ms.deleteMenuTransaction(id);
		return new Response(1, "Success", id);
	}


}
