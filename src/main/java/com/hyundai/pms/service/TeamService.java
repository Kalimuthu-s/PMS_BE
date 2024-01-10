package com.hyundai.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.TeamMaster;
import com.hyundai.pms.repository.TeamRepository;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository tr;
	
	public List<TeamMaster> getAllTeam(){
		return tr.findAll();
	}
	
	public Optional<TeamMaster> getTeamById(int id) {
		return tr.findById(id);
	}
	
	public TeamMaster addTeam(TeamMaster team) {
		return tr.save(team);
	}
	
	public TeamMaster updateTeam(TeamMaster team) {
		return tr.save(team);
	}
	
	public void deleteTeam(int id) {
		tr.deleteById(id);
	}

}
