package com.hyundai.pms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.Response;
import com.hyundai.pms.entity.TeamMaster;
import com.hyundai.pms.repository.TeamRepository;
import com.hyundai.pms.webModel.PaginationWebModel;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository tr;
	
	public Response getAllTeam(PaginationWebModel paginationWebModel){
		Map<String, Object> response = null;
		try {
			Pageable pageable = PageRequest.of(paginationWebModel.getPageNo(), paginationWebModel.getPageSize());
			var page = tr.getAllTeam(pageable,paginationWebModel.getSearchKey());
			
			response = new HashMap<>();
			
			response.put("count", page.getTotalElements());
			response.put("content", page.getContent());
			
			return new Response(1, "success", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(-1, "failed", "");
	}
	
	public Response getAllTeamOnly() {
		List<TeamMaster> list = tr.findAll();
		return  new Response(1, "Success", list);
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
