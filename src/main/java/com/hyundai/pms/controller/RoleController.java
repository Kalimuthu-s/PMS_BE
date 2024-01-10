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



@RestController
@RequestMapping("/Role")
@CrossOrigin(value="http://localhost:4200/")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@PostMapping("/addRole")
    public Response addRole(@RequestBody RoleMaster role) {
        return roleService.saveRole(role);
    }
	
	@GetMapping("/getAllRoles")
    public List<RoleMaster> getAllRoles() {
        return roleService.getAllRoles();
    }
	
	@GetMapping("/getRoleById/{id}")
	public Optional<RoleMaster> getRoleById(@PathVariable Long id) {
	     return roleService.getRoleById(id);
	 }
	
	 @PutMapping("/updateRole/{id}")
	 public Response updateRole(@PathVariable Long id, @RequestBody RoleMaster updatedRole) {
	      return roleService.updateRole(id, updatedRole);
	 }
	 
	 @DeleteMapping("/deleteRole/{id}")
	 public String deleteRole(@PathVariable Long id) {
	       return roleService.deleteRole(id);
	 }
}