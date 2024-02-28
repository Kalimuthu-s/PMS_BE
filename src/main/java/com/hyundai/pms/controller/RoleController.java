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

import com.hyundai.pms.entity.Response;
import com.hyundai.pms.entity.RoleMaster;
import com.hyundai.pms.service.RoleService;
import com.hyundai.pms.webModel.PaginationWebModel;

@RestController
@RequestMapping("/role")
@CrossOrigin(value = "http://localhost:4200/")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PostMapping("/addRole")
	public Response addRole(@RequestBody RoleMaster role) {
		roleService.saveRole(role);
		return new Response(1, "Success", role);
	}

	@PostMapping("/getAllRoles")
	public Response getAllRoles(@RequestBody PaginationWebModel paginationWebModel) {
		return roleService.getAllRoles(paginationWebModel);
	}
	
	@GetMapping("/getAllRole")
	public Response getAllRole() {
		return roleService.getAllRole();
	}

	@GetMapping("/getRoleById/{id}")
	public Response getRoleById(@PathVariable Long id) {
		Optional<RoleMaster> role = roleService.getRoleById(id);
		return new Response(1, "Success", role);
	}

	@PutMapping("/updateRole/{id}")
	public Response updateRole(@PathVariable Long id, @RequestBody RoleMaster updatedRole) {
		roleService.updateRole(id, updatedRole);
		return new Response(1, "Success", updatedRole);
	}

	@DeleteMapping("/deleteRole/{id}")
	public Response deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
		return new Response(1, "Success", id);
	}
}