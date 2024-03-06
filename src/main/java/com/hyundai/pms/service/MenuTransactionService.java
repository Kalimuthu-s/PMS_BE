package com.hyundai.pms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.MenuTransaction;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.MenuTransactionRepository;
import com.hyundai.pms.webModel.PaginationWebModel;

@Service
public class MenuTransactionService {
	
	@Autowired
	private MenuTransactionRepository mr;
	
	public Response getAllMenuTransaction(PaginationWebModel paginationWebModel) {
		Map<String, Object> response = null;

		try {

			Pageable pageable = PageRequest.of(paginationWebModel.getPageNo(), paginationWebModel.getPageSize());

			var page = mr.findAll(pageable);

			response = new HashMap<>();
			response.put("count", page.getTotalElements());
			response.put("content", page.getContent());

			return new Response(1, "success", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(-1, "failed", "");
	}
	
	public Optional<MenuTransaction> getMenuTransactionById(int id) {
		return mr.findById(id);
	}
	
	public List<Map<String, Object>> getDynamicMenuByRole(int roleId){
		return mr.getDynamicMenuByRole(roleId);
	}
	
	public MenuTransaction addMenuTransaction(MenuTransaction menu) {
		return mr.save(menu);
	}
	
	public MenuTransaction updateMenuTransaction(MenuTransaction menu) {
		return mr.save(menu);
	}
	
	public void deleteMenuTransaction(int id) {
		mr.deleteById(id);
	}


}
