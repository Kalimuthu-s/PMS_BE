package com.hyundai.pms.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.ExperienceMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.ExperienceRepository;
import com.hyundai.pms.webModel.PaginationWebModel;
@Service
public class ExperienceService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExperienceService.class);



	@Autowired
	private ExperienceRepository experienceRepository;
	
	public Response saveExperience(ExperienceMaster experience) {
		experienceRepository.save(experience);
		 logger.info("Experience saved successfully: {}", experience);
        return new Response(1,"success",experience);
		}
		
		
	
		public Response getAllExperiences(PaginationWebModel paginationWebModel) {

		Map<String, Object> response = null;

		try {
			Pageable pageable = PageRequest.of(paginationWebModel.getPageNo(), paginationWebModel.getPageSize());

			var page = experienceRepository.findAll(pageable);

			response = new HashMap<>();

			response.put("count", page.getTotalElements());
			response.put("content", page.getContent());

			return new Response(1, "success", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(-1, "failed", "");
	}
		
		
		public Optional<ExperienceMaster> getExperienceById(Long locationId) {
	        return experienceRepository.findById(locationId);
		}
		
		
		public Response updateExperience(Long experienceId, ExperienceMaster updatedExperience) {
	        if (experienceRepository.existsById(experienceId)) {
	        	updatedExperience.setExperienceId(experienceId);
	        	updatedExperience=  experienceRepository.save(updatedExperience);
	        	logger.info("Experience updated successfully: {}", updatedExperience);
	            return new Response(1,"success",updatedExperience);
	        } else {
	            // Handle not found scenario
	        	 logger.error("Experience with ID {} not found during update.", experienceId);
	            return new Response(2,"error",updatedExperience);
	        }
		}

	  
		public Response deleteExperience(Long experienceId) {
			 if (experienceRepository.existsById(experienceId)) {
				 experienceRepository.deleteById(experienceId);
		        return new Response(1,"success",null);
		        }
			 else {
				 return new Response(2,"error",null);
			 }
		}
}