package com.hyundai.pms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.MenuMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.MenuRepository;
import com.hyundai.pms.webModel.PaginationWebModel;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepository mr;
	
	public Response getAllMenu(PaginationWebModel paginationWebModel) {
		Map<String, Object> response = null;

		try {

			Pageable pageable = PageRequest.of(paginationWebModel.getPageNo(), paginationWebModel.getPageSize());

			var page = mr.getAllMenu(pageable,paginationWebModel.getSearchKey());

			response = new HashMap<>();
			response.put("count", page.getTotalElements());
			response.put("content", page.getContent());

			return new Response(1, "success", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(-1, "failed", "");
	}
	
	public Optional<MenuMaster> getMenuById(int id) {
		return mr.findById(id);
	}
	
	public MenuMaster addMenu(MenuMaster menu) {
		return mr.save(menu);
	}
	
	public MenuMaster updateMenu(MenuMaster menu) {
		return mr.save(menu);
	}
	
	public void deleteMenu(int id) {
		mr.deleteById(id);
	}
	

}
