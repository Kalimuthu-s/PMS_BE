package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.LocationMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.LocationRepository;

@Service
public class LocationService {
	private static final Logger logger = LoggerFactory.getLogger(ExperienceService.class);

	
		@Autowired
		private LocationRepository locationRepository;

		public Response saveLocation(LocationMaster location) {
         locationRepository.save(location);
         logger.info("Experience saved successfully: {}", location);
         return new Response(1,"success",location,true);
		}
		
		
	
		public List<LocationMaster> getAllLocations() {
	        return (List<LocationMaster>) locationRepository.findAll();
		}
		
		
	 
		public Optional<LocationMaster> getLocationById(Long locationId) {
	        return locationRepository.findById(locationId);
		}
		
		
	  
		public Response updateLocation(Long locationId, LocationMaster updatedLocation) {
	        if (locationRepository.existsById(locationId)) {
	            updatedLocation.setLocationId(locationId);
	            updatedLocation=  locationRepository.save(updatedLocation);
	        	logger.info("Experience updated successfully: {}", updatedLocation);
	            return new Response(1,"success",updatedLocation,true);
	        } else {
	        	 logger.error("Experience with ID {} not found during update.", locationId);
	            return new Response(2,"error",updatedLocation,false);
	        }
		}

	  
		public Response deleteLocation(Long locationId) {
			 if (locationRepository.existsById(locationId)) {
				 locationRepository.deleteById(locationId);
		        return new Response(1,"success",null,true);
		        }
			 else {
				 return new Response(2,"error",null,false);
			 }
		}
}