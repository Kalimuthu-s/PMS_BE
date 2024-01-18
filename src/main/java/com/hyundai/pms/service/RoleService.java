package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.Response;
import com.hyundai.pms.entity.RoleMaster;
import com.hyundai.pms.repository.RoleRepository;
@Service
public class RoleService {
	private static final Logger logger = LoggerFactory.getLogger(ExperienceService.class);

	
	@Autowired
	RoleRepository roleRepository;
	
	 public Response saveRole(RoleMaster role) {
	         roleRepository.save(role);
	         logger.info("Experience saved successfully: {}", role);
	         return new Response(1, "success", role);
	    }
	 
	 public List<RoleMaster> getAllRoles() {
	        return (List<RoleMaster>) roleRepository.findAll();
	 	}

	 public Optional<RoleMaster> getRoleById(Long roleId) {
	        return roleRepository.findById(roleId);
	    }
	 
	 public Response updateRole(Long roleId, RoleMaster updatedRole) {
	        if (roleRepository.existsById(roleId)) {
	            updatedRole.setRoleId(roleId);
	             roleRepository.save(updatedRole);
	             logger.info("Experience updated successfully: {}", updatedRole);
	             return new Response(1, "success", updatedRole);
	        } else {
	            // Handle not found scenario
	        	logger.error("Experience with ID {} not found during update.", roleId);
	            return new Response(2, "Error", updatedRole);
	        }
	    }
	 
	 public String deleteRole(Long roleId) {
		 if (roleRepository.existsById(roleId)) {
			 roleRepository.deleteById(roleId);
	        return "Deleted Successfully";
	        }
		 else {
			 return "Failed To Delete";
		 }
	}
}