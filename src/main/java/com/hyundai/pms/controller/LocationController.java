package com.hyundai.pms.controller;

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
import com.hyundai.pms.webModel.PaginationWebModel;

@RestController
@RequestMapping("/location")
@CrossOrigin(value = "http://localhost:4200/")
public class LocationController {

	@Autowired
	public LocationService locationService;

	@PostMapping("/addLocation")
	public Response addLocation(@RequestBody LocationMaster location) {
		locationService.saveLocation(location);
		return new Response(1, "Success", location);
	}

	 @PostMapping("/getAllLocations")
	    public Response getAllLocations(@RequestBody PaginationWebModel paginationWebModel) {
	        return locationService.getAllLocations(paginationWebModel);
	    }

	@GetMapping("/getLocationById/{id}")
	public Response getLocationById(@PathVariable Long id) {
		Optional<LocationMaster> location = locationService.getLocationById(id);
		return new Response(1, "Success", location);
	}

	@PutMapping("/updateLocation/{id}")
	public Response updateLocation(@PathVariable Long id, @RequestBody LocationMaster updatedLocation) {
		locationService.updateLocation(id, updatedLocation);
		return new Response(1, "Success", updatedLocation);
	}

	@DeleteMapping("/deleteLocation/{id}")
	public Response deleteLocation(@PathVariable Long id) {
		locationService.deleteLocation(id);
		return new Response(1, "Success", id);
	}

}