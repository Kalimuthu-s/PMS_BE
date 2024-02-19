package com.hyundai.pms.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.AssignEmployeeSearchDTO;
import com.hyundai.pms.entity.AssignEmployeeTransaction;
import com.hyundai.pms.entity.EmployeeTransactionDTO;
import com.hyundai.pms.entity.MonthlyEntries;
import com.hyundai.pms.entity.ProjectMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.AssignEmployeeTransactionRepository;
import com.hyundai.pms.repository.MonthlyEntriesRepository;
import com.hyundai.pms.repository.ProjectRepository;
import com.hyundai.pms.webModel.PaginationWebModel;

@Service
public class AssignEmployeeTransactionService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AssignEmployeeTransactionRepository petr;
	
	@Autowired
	private MonthlyEntriesRepository monthlyEntryRepo;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Response getAllAssignEmployeeTransaction(PaginationWebModel paginationWebModel){
		Map<String, Object> response = null;
		try {
			Pageable pageable = PageRequest.of(paginationWebModel.getPageNo(), paginationWebModel.getPageSize());

			var page = petr.getAllEmployeeTransaction(pageable);
			response = new HashMap<>();
			response.put("count", page.getTotalElements());
			response.put("content", page.getContent());

			return new Response(1, "Success", response);
			} catch (Exception e) {
			e.printStackTrace();
			return new Response(2, "Failed", null);
		}
	}
	
	public Optional<AssignEmployeeTransaction> getAllAssignEmployeeTransactionById(int id) {
		return petr.findById(id);
	}
	
	public AssignEmployeeTransaction addAssignEmployeeTransaction(AssignEmployeeTransaction pet) {
		return petr.save(pet);
	}
	
	public AssignEmployeeTransaction updateAssignEmployeeTransaction(AssignEmployeeTransaction pet) {
		return petr.save(pet);
	}
	
	public String deleteAssignEmployeeTransaction(int id) {
		petr.deleteById(id);
		return "Success";
	}
	
	public List<Map<String, Object>> getAssignedEmployees() {
		return petr.getAssignedEmployees();
	}
	
	public List<Map<String, Object>> getEmployeesByFilters(AssignEmployeeSearchDTO dto){
		try {
			String sql = "";
			String sqlCondition="";
			
			
			
			if (dto.getAssignedStartDate()==null && dto.getAssignedEndDate()==null && dto.getProficiency()==null && dto.getSkill()!=null ) {
				sql += "s.skill_id='"+dto.getSkill()+"'";
				System.err.println("==================> only skill");
			}
			
			System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+dto.getSkill());
			System.err.println(">>>>>>>>>>>>>>>>>>>>"+dto.getProficiency());
			
			if(dto.getAssignedStartDate()==null && dto.getAssignedEndDate()==null && dto.getSkill()!=null && dto.getProficiency()!=null) {
				sql +="s.skill_id='"+dto.getSkill()+"' AND s.proficiency_level='"+dto.getProficiency()+"'";
				System.err.println("==================> only skill and proficiency"+sql);
			}
			
			
			String query = "SELECT e.emp_id AS employeeId, CONCAT(e.first_name, ' ', e.last_name) AS employeeName "
		            + "FROM employee_master e "
		            + "JOIN skill_transaction s ON e.emp_id = s.emp_id ";
			
			if (!sql.isEmpty()) {
			    query += "WHERE " + sql;
			}
			
			System.err.println("==================> Full query" + sql);

			List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

			return result;
		} catch (Exception e) {
			 e.printStackTrace();
			 return null; 
		}
	}
	

	public Response addMultipleEmployeeTransaction(EmployeeTransactionDTO dto) {
	    List<Long> list = dto.getEmployeeId();
	   
	    
		try{
	    list.forEach(employeeId -> {
			
			    if (petr.existsByEmployeeIdAndProjectId(employeeId, dto.getProjectId())) {
						 throw new IllegalStateException("Employees "+employeeId+" is already assigned to this project");
            }

	    	AssignEmployeeTransaction employee = new AssignEmployeeTransaction();
	    	employee.setEmployeeId(employeeId);

			employee.setAssignedStartDate(dto.getAssignedStartDate());
			employee.setAssignedEndDate(dto.getAssignedEndDate());
			employee.setProjectId(dto.getProjectId());
			petr.save(employee);

			Calendar localStartDate = Calendar.getInstance();
			localStartDate.setTime(dto.getAssignedStartDate());
			Calendar localEndDate = Calendar.getInstance();
			localEndDate.setTime(dto.getAssignedEndDate());

	        while (localStartDate.get(Calendar.YEAR) <= localEndDate.get(Calendar.YEAR)) {
	            MonthlyEntries monthlyEntries = new MonthlyEntries();
	            monthlyEntries.setEmp_id(employeeId);
	            monthlyEntries.setProjectId(dto.getProjectId());
	            int year = localStartDate.get(Calendar.YEAR);
	            monthlyEntries.setYear(year);
	            monthlyEntries.setYear(year);
	            monthlyEntries.setJanuary(0.0);
	            monthlyEntries.setFebruary(0.0);
	            monthlyEntries.setMarch(0.0);
	            monthlyEntries.setApril(0.0);
	            monthlyEntries.setMay(0.0);
	            monthlyEntries.setJune(0.0);
	            monthlyEntries.setJuly(0.0);
	            monthlyEntries.setAugust(0.0);
	            monthlyEntries.setSeptember(0.0);
	            monthlyEntries.setOctober(0.0);
	            monthlyEntries.setNovember(0.0);
	            monthlyEntries.setDecember(0.0);
	            monthlyEntries.setAssignedStartDate(dto.getAssignedStartDate());
	            monthlyEntries.setAssignedEndDate(dto.getAssignedEndDate());
	            monthlyEntryRepo.save(monthlyEntries);
	           localStartDate.add(Calendar.YEAR, 1);
	        }
	    });
		}
		 catch (IllegalStateException e) {
    		return new Response(2, e.getMessage(), null);
		}
	    return new Response(1, "success",null);
	}
}
