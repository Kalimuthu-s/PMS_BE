package com.hyundai.pms.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.LocationMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.LocationRepository;
import com.hyundai.pms.webModel.PaginationWebModel;

@Service
public class LocationService {
	private static final Logger logger = LoggerFactory.getLogger(ExperienceService.class);

	
		@Autowired
		private LocationRepository locationRepository;

		public Response saveLocation(LocationMaster location) {
         locationRepository.save(location);
         logger.info("Experience saved successfully: {}", location);
         return new Response(1,"success",location);
		}
		
		
	
		public Response getAllLocations(PaginationWebModel paginationWebModel) {
			Map<String, Object> response = new HashMap<>();
			try {
				Pageable pageable = PageRequest.of(paginationWebModel.getPageNo(), paginationWebModel.getPageSize());
				

				var page = locationRepository.findAllLocation(pageable,paginationWebModel.getSearchKey());
				
				response.put("count", page.getTotalElements());
				response.put("content", page.getContent());
				
		        return new Response(1, "success", response) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return new Response(-1, "failed", response);
		}
		
		
	 
		public Optional<LocationMaster> getLocationById(Long locationId) {
	        return locationRepository.findById(locationId);
		}
		
		
	  
		public Response updateLocation(Long locationId, LocationMaster updatedLocation) {
	        if (locationRepository.existsById(locationId)) {
	            updatedLocation.setLocationId(locationId);
	            updatedLocation=  locationRepository.save(updatedLocation);
	        	logger.info("Experience updated successfully: {}", updatedLocation);
	            return new Response(1,"success",updatedLocation);
	        } else {
	        	 logger.error("Experience with ID {} not found during update.", locationId);
	            return new Response(2,"error",updatedLocation);
	        }
		}

	  
		public Response deleteLocation(Long locationId) {
			 if (locationRepository.existsById(locationId)) {
				 locationRepository.deleteById(locationId);
		        return new Response(1,"success",null);
		        }
			 else {
				 return new Response(2,"error",null);
			 }
		}
}