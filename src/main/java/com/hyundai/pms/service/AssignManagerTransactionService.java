package com.hyundai.pms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.AssignManagerTransaction;
import com.hyundai.pms.entity.ProjectReassignDTO;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.AssignManagerTransactionRepository;
import com.hyundai.pms.webModel.PaginationWebModel;

@Service
public class AssignManagerTransactionService {
	
	@Autowired
	private AssignManagerTransactionRepository pr;
	
	public Response getAllAssignManagerTransaction(PaginationWebModel paginationWebModel){
		Map<String, Object> response = null;
		try {
			Pageable pageable = PageRequest.of(paginationWebModel.getPageNo(), paginationWebModel.getPageSize());
			var page =pr.getAllProjectManagerTransaction(pageable,paginationWebModel.getSearchKey());
			response = new HashMap<>();

			response.put("count", page.getTotalElements());
			response.put("content", page.getContent());

			return new Response(1, "success", response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(-1, "failed", "");
		 
	}
	
	public Optional<AssignManagerTransaction> getAssignManagerTransactionById(int id) {
		return pr.findById(id);
	}
	
	public AssignManagerTransaction addAssignManagerTransaction(AssignManagerTransaction pmt) {
		return pr.save(pmt);
	}
	
	public AssignManagerTransaction updateAssignManagerTransaction(AssignManagerTransaction pmt) {
		return pr.save(pmt);
	}
	
	public void deleteAssignManagerTransaction(int id) {
		pr.deleteById(id);
	}
	
	public List<Map<String, Object>> getManagerAvaiableProjects(String manager){
		return pr.getManagerAvaiableProjects(manager);
	}
	
	public String reassignManagerToProjects(ProjectReassignDTO data) {
			List<Integer> list =data.getProjectList();
			list.forEach(l->{
				AssignManagerTransaction p1=new AssignManagerTransaction();
				p1=pr.getOneById(l);
				p1.setManagerName(data.getAssignManagerId());
				pr.save(p1);
			});
		return "Manager Reassigned";
	}
	
	public List<Map<String, Object>> getAllProjectManagerWithoutPagination() {
		return pr.getAllProjectManagerWithoutPagination();
	}


}
