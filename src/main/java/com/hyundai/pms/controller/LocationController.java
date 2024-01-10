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

import com.hyundai.pms.entity.LocationMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.LocationService;


@RestController
@RequestMapping("/location")
@CrossOrigin(value="http://localhost:4200/")
public class LocationController {

	
	@Autowired
	public LocationService locationService;
	
	 @PostMapping("/addLocation")
	    public Response addLocation(@RequestBody LocationMaster location) {
	        return locationService.saveLocation(location);
	    }
	 
	 @GetMapping("/getAllLocations")
	    public List<LocationMaster> getAllLocations() {
	        return locationService.getAllLocations();
	    }
	 
	 @GetMapping("/getLocationById/{id}")
	    public Optional<LocationMaster> getLocationById(@PathVariable Long id) {
	        return locationService.getLocationById(id);
	    }
	 
	 @PutMapping("/updateLocation/{id}")
	    public Response updateLocation(@PathVariable Long id, @RequestBody LocationMaster updatedLocation) {
	        return locationService.updateLocation(id, updatedLocation);
	    }
	 
	 @DeleteMapping("/deleteLocation/{id}")
	    public Response deleteLocation(@PathVariable Long id) {
	       return locationService.deleteLocation(id);
	    }


}