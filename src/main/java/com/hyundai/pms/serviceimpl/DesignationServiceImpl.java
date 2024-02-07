package com.hyundai.pms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.Designation;
import com.hyundai.pms.repository.DesignationRepository;
import com.hyundai.pms.response.Response;
import com.hyundai.pms.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {
	
	@Autowired
	private DesignationRepository designationrepository;

	@Override
	public List<Designation> getAll() {
		return designationrepository.findAll();
	}
	
	@Override
	public Page<Designation> getAll(Pageable pageable) {
	    return designationrepository.findAll(pageable);
	}

	@Override
	public Optional<Designation> getById(int desgId) {
		return designationrepository.findById(desgId);
	}
	
	
	@Override
	public Response addDesignation(Designation desgbody) {
	    if (desgbody==null) {
	        return new Response(-1, "failed", "Designation object cannot be null");
	    }
	    if (desgbody.getDesignationName()==null || desgbody.getDesignationName().isEmpty()||desgbody.getDesignationName()=="") {
	        return new Response(-1, "failed", "Designation name cannot be null or empty");
	    }
	    if (desgbody.getJobRole()==null || desgbody.getJobRole().isEmpty()||desgbody.getJobRole()=="") {
	        return new Response(-1, "failed", "JobRole name cannot be null or empty");
	    }

	    try {
	    	Designation savedDesignation = designationrepository.save(desgbody);
	        return new Response(1, "success", savedDesignation);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new Response(-1, "failed", "Failed to save Designation");
	}
	
	
	@Override
	public Response updatedesignation(Designation desgbody) {
	    if (desgbody==null) {
	        return new Response(-1, "failed", "Designation object cannot be null");
	    }
	    if (desgbody.getDesignationName()==null || desgbody.getDesignationName().isEmpty()||desgbody.getDesignationName().equals("")) {
	        return new Response(-1, "failed", "Designation name cannot be null or empty");
	    }
	    if (desgbody.getJobRole()==null || desgbody.getJobRole().isEmpty()||desgbody.getJobRole()=="") {
	        return new Response(-1, "failed", "JobRole name cannot be null or empty");
	    }

	    try {
	    	Designation updateDesignation = designationrepository.save(desgbody);
	        return new Response(1, "success", updateDesignation);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new Response(-1, "failed", "Failed to update Designation");
	}

	@Override
	 public Response deletedesignation(int desgId) {
	     try {
	         Optional<Designation> optionaldesignation = designationrepository.findById(desgId);

	         if (optionaldesignation.isPresent()) {
	        	 Designation deletedDesignation = optionaldesignation.get();
	             designationrepository.deleteById(desgId);
	             return new Response(1, "Success", deletedDesignation);
	         } else {
	             return new Response(-1, "Designation not found","");
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return new Response(-1, "Failed to delete Designation: " ,"");
	     }
	 }

}
