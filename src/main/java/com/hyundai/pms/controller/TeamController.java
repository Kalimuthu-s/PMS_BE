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
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.TeamMaster;
import com.hyundai.pms.service.TeamService;

@CrossOrigin
@RestController
public class TeamController {
	
	@Autowired
	private TeamService ts;
	
	@GetMapping("/getAllTeam")
	public List<TeamMaster> getAllTeam() {
		List<TeamMaster> list = ts.getAllTeam();
		return list;
	}
	
	@GetMapping("/getTeamById/{id}")
	public Optional<TeamMaster> getTeamById(@PathVariable int id) {
		Optional<TeamMaster> team=ts.getTeamById(id);
		return team;
		
	}
	
	@PostMapping("/addTeam")
	public TeamMaster addTeam(@RequestBody TeamMaster team) {
		return ts.addTeam(team);
	}
	
	@PutMapping("/updateTeam")
	public TeamMaster updateTeam(@RequestBody TeamMaster team) {
		return ts.updateTeam(team);
	}
	
	@DeleteMapping("/deleteTeam/{id}")
	public String deleteTeam(@PathVariable int id) {
		ts.deleteTeam(id);
		return "Team Deleted";
	}

}
