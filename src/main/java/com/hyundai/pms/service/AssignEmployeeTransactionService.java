package com.hyundai.pms.service;

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
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.AssignEmployeeTransactionRepository;
import com.hyundai.pms.repository.MonthlyEntriesRepository;
import com.hyundai.pms.webModel.PaginationWebModel;

@Service
public class AssignEmployeeTransactionService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AssignEmployeeTransactionRepository petr;
	
	@Autowired
	private MonthlyEntriesRepository monthlyEntryRepo;
	
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

//			if (dto.getAssignedStartDate()!=null && dto.getAssignedEndDate()!=null && dto.getSkill()==null && dto.getProficiency()==null) {
//				sql += "t.employee_id not in (SELECT DISTINCT pe.employee_id FROM assign_employee_transaction pe WHERE pe.assigned_start_date>='"+dto.getAssignedStartDate()+"' && t.assigned_end_date<='"+dto.getAssignedEndDate()+"')";
//				System.err.println("==================> only start and end date");
//			}
			if (dto.getAssignedStartDate()==null && dto.getAssignedEndDate()==null && dto.getProficiency()==null && dto.getSkill()!=null ) {
				sql += "s.skill_id='"+dto.getSkill()+"'";
				System.err.println("==================> only skill");
			}
			if(dto.getAssignedStartDate()==null && dto.getAssignedEndDate()==null && dto.getSkill()!=null && dto.getProficiency()!=null) {
				sql +="s.skill_id='"+dto.getSkill()+"' && s.proficiency_level='"+dto.getProficiency()+"'";
				System.err.println("==================> only skill and proficiency");
			}
			
//			if(dto.getAssignedStartDate()!=null && dto.getAssignedEndDate()!=null && dto.getSkill()!=null && dto.getProficiency()!=null) {
//				sql += "(s.skill_id='"+dto.getSkill()+"' && s.proficiency_level='"+dto.getProficiency()+"' && t.employee_id) not in (SELECT DISTINCT pe.employee_id FROM assign_employee_transaction pe WHERE pe.assigned_start_date>='"+dto.getAssignedStartDate()+"' && t.assigned_end_date<='"+dto.getAssignedEndDate()+"')";
//				System.err.println("==================> All");
//			}
//			if (sql != null && !sql.isEmpty()) {
//			    sqlCondition = " AND " + sql; // Concatenate with "AND" if sql is not empty
//			}
			
			String query = "select t.employee_id as employeeId, concat(e.first_name,' ',e.last_name) as employeeName, "
					+ "t.project_id as projectId, p.project_name as projectName, "
					+ "t.assigned_start_date as assignedStartDate, t.assigned_end_date as assignedEndDate, s.skill_id as skillId, "
					+ "s.proficiency_level as skillProficiency from assign_employee_transaction t "
					+ "inner join project_master p on t.project_id = p.project_id "
					+ "inner join employee_master e on t.employee_id = e.emp_id "
					+ "inner join skill_transaction s on t.employee_id = s.emp_id "
					+ "where "+sql;

			System.err.println("==================> Full query" + sql);
			
			List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

			return result;
		} catch (Exception e) {
			 e.printStackTrace();
			 return null; 
		}
	}
	
	
	public String addMultipleEmployeeTransaction(EmployeeTransactionDTO dto) {
		List<Integer> list = dto.getEmployeeId();
		list.forEach(l->{
			AssignEmployeeTransaction employee = new AssignEmployeeTransaction();
			MonthlyEntries monthlyEntries = new MonthlyEntries();
			employee.setEmployeeId(l);
			employee.setAssignedStartDate(dto.getAssignedStartDate());
			employee.setAssignedEndDate(dto.getAssignedEndDate());
			employee.setProjectId(dto.getProjectId());
			petr.save(employee);
			
			monthlyEntries.setEmp_id(l);
			monthlyEntries.setProjectId(dto.getProjectId());
			String year = dto.getAssignedStartDate().substring(0, 4);
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
			monthlyEntryRepo.save(monthlyEntries);
		});
		return "Success";
	}
}
