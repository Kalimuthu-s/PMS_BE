package com.hyundai.pms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.Consolidate;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.MonthlyEntriesRepository;
import com.hyundai.pms.repository.OverallUtilizationRepository;
import com.hyundai.pms.webModel.PaginationWebModel;

@Service
public class OverallUtilizationService {
	
	@Autowired
	private MonthlyEntriesRepository monthlyRepo;
	
	@Autowired
	private OverallUtilizationRepository overallUtilizationRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Response getOverallConsolidatedUsingDate(Consolidate consolidate) {
		try {
			String sql = "";

			if (consolidate.getStartDate() != null && consolidate.getEndDate() != null) {
				sql += "(emd.months BETWEEN Date('" + DateConvertion(consolidate.getStartDate()) + "') AND Date('"
						+ DateConvertion(consolidate.getEndDate()) + "'))";
			}
			if (consolidate.getProjectid() != 0 && consolidate.getEmpid() != 0 && consolidate.getStartDate()!=null && consolidate.getEndDate()!=null) {
				sql += "AND (emp.emp_id= " + consolidate.getEmpid() + " AND p.project_id=" + consolidate.getProjectid()+ ")";
				System.err.println("both project and employee id::::"+sql);

			}
			if(consolidate.getEmpid()!=0 && consolidate.getStartDate() !=null && consolidate.getEndDate()!=null && consolidate.getProjectid()==0) {
				sql +="AND (emp.emp_id= " + consolidate.getEmpid() + ")";
				System.err.println("empbased start and end date:::::::"+sql);
			}
			
			if(consolidate.getEmpid()!=0 && consolidate.getProjectid()==0 && consolidate.getStartDate() ==null && consolidate.getEndDate() ==null) {
				sql += "(emp.emp_id= " + consolidate.getEmpid() + ")";
				System.err.println("Search By Only Employee Id::::"+sql);
			}
			
			if(consolidate.getProjectid() !=0 && consolidate.getStartDate() !=null && consolidate.getEndDate() !=null && consolidate.getEmpid()==0) {
				sql += "AND (p.project_id=" + consolidate.getProjectid() + ")";
				System.err.println("search By Project with Date:::::::"+sql);
			}
			
			if(consolidate.getProjectid()!=0 && consolidate.getEmpid()==0 && consolidate.getStartDate()==null && consolidate.getEndDate()==null) {
				sql += "(p.project_id=" + consolidate.getProjectid() + ")";
				System.err.println("Search By Only ProjectId::::::::::"+sql);
			}
			
			String query = "SELECT SUM(emd.contribution) as TotalContribution, emd.emp_id as employeeId, emd.project_id as projectId, p.project_name as projectName, " +
		               "emp.first_name as managerName, emd.months as month, emp.first_name, emp.last_name " +
		               "FROM employee_monthly_data emd " +
		               "INNER JOIN employee_master emp ON emp.emp_id = emd.emp_id " +
		               "INNER JOIN project_master p ON p.project_id = emd.project_id " +
		               "WHERE " + sql + " " +
		               "GROUP BY emd.emp_id, emd.project_id, p.project_name, emp.first_name, emd.months, emp.last_name,emd.contribution";

			System.err.println("query Values are::::::" + query);

			List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

			return new Response(1, "Consolidated Data Getting Successfully", result);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(2, "Error Getting Employee", null);
		}
	}

	private static String DateConvertion(Date date) {
		SimpleDateFormat originalDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		try {
			Date originalDate = originalDateFormat.parse(date.toString());
			SimpleDateFormat desiredDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String desiredDateString = desiredDateFormat.format(originalDate);
			return desiredDateString;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.toString();
	}

	public Response getOverAllConsolidatedData() {
		try {
			List<Map<String, Object>> getallconsolidateddata = overallUtilizationRepo
					.getAllEmployeeOverallUtilizationDetails();
			return new Response(1, "Consolidated Data Getting Successfully", getallconsolidateddata);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(2, "Error Fetching Overall Consolidated Data", null);
		}
	}

	public Response getByProjectsData(Consolidate consolidate) {
		try {
			List<Map<String, Object>> getdatasbyProject = overallUtilizationRepo.getdatabyProjects(
					consolidate.getProjectid(), consolidate.getStartDate(), consolidate.getEndDate());
			return new Response(1, "Consolidated Data Getting Successfully", getdatasbyProject);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(2, "Error getting the data", null);
		}
	}
	
//	public Response getAllConsolidated(String year) {
//		try {
//			List<Map<String, Object>> monthlyData = monthlyRepo.getAllConsolidated(year);
//			return new Response(1, "Consolidated Data Getting Successfully", monthlyData);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Response(2, "Error getting the data", null);
//		}
//	}
	
	public Response getAllConsolidatedData(PaginationWebModel paginationWebModel) {
		Map<String, Object> response = new HashMap<>();
		try {
			Pageable pageable = PageRequest.of(paginationWebModel.getPageNo(), paginationWebModel.getPageSize());

			var page = monthlyRepo.getAllConsolidatedData(pageable);
			response.put("count", page.getTotalElements());
			response.put("content", page.getContent());
			return new Response(1, "Consolidated Data Getting Successfully", response);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(2, "Error getting the data", null);
		}
	}
	
//	public Response getAllConsolidatedData() {
//		try {
//			List<Map<String, Object>> monthlyData = monthlyRepo.getAllConsolidatedData();
//			return new Response(1, "Consolidated Data Getting Successfully", monthlyData);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Response(2, "Error getting the data", null);
//		}
//	}


}
