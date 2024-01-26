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

import com.hyundai.pms.entity.Response;
import com.hyundai.pms.entity.TeamMaster;
import com.hyundai.pms.service.TeamService;
import com.hyundai.pms.webModel.PaginationWebModel;

@CrossOrigin
@RestController
@RequestMapping(value = "/team")
public class TeamController {
	
	@Autowired
	private TeamService ts;
	
	@PostMapping("/getAllTeam")
	public Response getAllTeam(@RequestBody PaginationWebModel paginationWebModel) {
		return ts.getAllTeam(paginationWebModel);
	}
	
	@GetMapping("/getTeamById/{id}")
	public Response getTeamById(@PathVariable int id) {
		Optional<TeamMaster> team=ts.getTeamById(id);
		return new Response(1, "Success", team);
	}
	
	@PostMapping("/addTeam")
	public Response addTeam(@RequestBody TeamMaster team) {
		ts.addTeam(team);
		return new Response(1, "Success", team);
	}
	
	@PutMapping("/updateTeam")
	public Response updateTeam(@RequestBody TeamMaster team) {
		ts.updateTeam(team);
		return new Response(1, "Success", team);
	}
	
	@DeleteMapping("/deleteTeam/{id}")
	public Response deleteTeam(@PathVariable int id) {
		ts.deleteTeam(id);
		return new Response(1, "Success", id);
	}

}
