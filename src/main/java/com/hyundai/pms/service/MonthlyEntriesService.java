package com.hyundai.pms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.MonthlyEntries;
import com.hyundai.pms.entity.MonthlyEntryDTO;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.repository.MonthlyEntriesRepository;

@Service
public class MonthlyEntriesService {
	@Autowired
	private MonthlyEntriesRepository monthlyEntriesRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Response saveMonthlyEntry(MonthlyEntries monthlyEntry) {
		monthlyEntriesRepository.save(monthlyEntry);
		return new Response(1, "success", monthlyEntry);
	}

	public Response getAllMonthlyEntries() {
		List<Map<String, Object>> list = monthlyEntriesRepository.getAllMonthlyEntries();
		return new Response(1, "Success", list);
	}

	public Optional<MonthlyEntries> getMonthlyEntryById(int id) {
		return monthlyEntriesRepository.findById(id);
	}

	public Response updateMonthlyEntry(MonthlyEntries updatedMonthlyEntry) {

		if (monthlyEntriesRepository.existsById(updatedMonthlyEntry.getMonthlyId())) {
			updatedMonthlyEntry.setMonthlyId(updatedMonthlyEntry.getMonthlyId());
			double totalContributionForjanuary = getTotalForJanuary(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForjanuary + updatedMonthlyEntry.getJanuary()) > 1.0) {
				System.err.println("===========> Jan condition");
				return new Response(2, "Total contribution for the employee in january month exceeds 1", null);
			}
			double totalContributionForfebruary = getTotalForFebruary(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForfebruary + updatedMonthlyEntry.getFebruary()) > 1.0) {
				System.err.println("===========> Feb condition");
				return new Response(2, "Total contribution for the employee in february month exceeds 1", null);
			}
			double totalContributionForMarch = getTotalForMarch(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForMarch + updatedMonthlyEntry.getMarch()) > 1.0) {
				System.err.println("===========> Mar condition");
				return new Response(2, "Total contribution for the employee in march month exceeds 1", null);
			}
			double totalContributionForApril = getTotalForApril(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForApril + updatedMonthlyEntry.getApril()) > 1.0) {
				System.err.println("===========> Apr condition");
				return new Response(2, "Total contribution for the employee in april month exceeds 1", null);
			}
			double totalContributionForMay = getTotalForMay(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForMay + updatedMonthlyEntry.getMay()) > 1.0) {
				System.err.println("===========> May condition");
				return new Response(2, "Total contribution for the employee in may month exceeds 1", null);
			}
			double totalContributionForJune = getTotalForJune(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForJune + updatedMonthlyEntry.getJune()) > 1.0) {
				System.err.println("===========> Jun condition");
				return new Response(2, "Total contribution for the employee in june month exceeds 1", null);
			}
			double totalContributionForJuly = getTotalForJuly(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForJuly + updatedMonthlyEntry.getJuly()) > 1.0) {
				System.err.println("===========> Jul condition");
				return new Response(2, "Total contribution for the employee in june month exceeds 1", null);
			}
			double totalContributionForAugust = getTotalForAugust(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForAugust + updatedMonthlyEntry.getAugust()) > 1.0) {
				System.err.println("===========> Aug condition");
				return new Response(2, "Total contribution for the employee in August month exceeds 1", null);
			}
			double totalContributionForSeptember = getTotalForSeptember(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForSeptember + updatedMonthlyEntry.getSeptember()) > 1.0) {
				System.err.println("===========> Sep condition");
				return new Response(2, "Total contribution for the employee in september month exceeds 1", null);
			}
			double totalContributionForOctober = getTotalForOctober(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForOctober + updatedMonthlyEntry.getOctober()) > 1.0) {
				System.err.println("===========> Oct condition");
				return new Response(2, "Total contribution for the employee in october month exceeds 1", null);
			}
			double totalContributionForNovember = getTotalForNovember(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForNovember + updatedMonthlyEntry.getNovember()) > 1.0) {
				System.err.println("===========> Nov condition");
				return new Response(2, "Total contribution for the employee in 	november month exceeds 1", null);
			}
			double totalContributionForDecember = getTotalForDecember(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(),updatedMonthlyEntry);
			if ((totalContributionForDecember + updatedMonthlyEntry.getDecember()) > 1.0) {
				System.err.println("===========> Dec condition");
				return new Response(2, "Total contribution for the employee in December month exceeds 1", null);
			}
			monthlyEntriesRepository.save(updatedMonthlyEntry);
			return new Response(1, "success", updatedMonthlyEntry);
		} else {
			return new Response(2, "error", updatedMonthlyEntry);
		}
	}

	private double getTotalForJanuary(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {
	            sum += one.getJanuary();
	    }
	    return sum;
	}


	private double getTotalForFebruary(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {

	            sum += one.getFebruary();
	    }
	    return sum;
	}


	private double getTotalForMarch(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {

	            sum += one.getMarch();
	    }
	    return sum;
	}

	private double getTotalForApril(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {

	            sum += one.getApril();
	    }
	    return sum;
	}

	private double getTotalForMay(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {

	            sum += one.getMay();

	    }
	    return sum;
	}

	private double getTotalForJune(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {

	            sum += one.getJune();

	    }
	    return sum;
	}

	private double getTotalForJuly(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {

	            sum += one.getJuly();

	    }
	    return sum;
	}

	private double getTotalForAugust(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {

	            sum += one.getAugust();

	    }
	    return sum;
	}

	private double getTotalForSeptember(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {

	            sum += one.getSeptember();
	    }
	    return sum;
	}

	private double getTotalForOctober(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {

	            sum += one.getOctober();

	    }
	    return sum;
	}

	private double getTotalForNovember(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {

	            sum += one.getNovember();
	    }
	    return sum;
	}

	private double getTotalForDecember(String year, int employeeId, MonthlyEntries updatedEntry) {
	    List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmp_idAndYear(employeeId, year);
	    double sum = 0;
	    for (MonthlyEntries one : existingEntries) {

	            sum += one.getDecember();

	    }
	    return sum;
	}

	public Response deleteMonthlyEntry(int id) {
		if (monthlyEntriesRepository.existsById(id)) {
			monthlyEntriesRepository.deleteById(id);
			return new Response(1, "success", null);
		} else {
			return new Response(2, "error", null);
		}
	}

	public Response findByProjectId(int projectId) {
		List<Map<String, Object>> list = monthlyEntriesRepository.findByProjectId(projectId);
		return new Response(1, "Success", list);
	}

//	public Response monthlyEntriesFilter(MonthlyEntryDTO dto) {
//		try {
//
//			String whereCondition = "";
//			String selectCondition = "";
//
//			if (dto.getEmployeeId() != null && dto.getProjectId() == null && dto.getStartDate() == null
//					&& dto.getEndDate() == null) {
//				whereCondition = "where m.emp_id=" + dto.getEmployeeId();
//			}
//			if (dto.getEmployeeId() == null && dto.getProjectId() != null && dto.getStartDate() == null
//					&& dto.getEndDate() == null) {
//				whereCondition = "where m.project_id=" + dto.getProjectId();
//			}
//			
//			if (dto.getEmployeeId() == null && dto.getProjectId() == null && dto.getStartDate() != null
//			        && dto.getEndDate() != null) {
//			    int startMonth = dto.getStartDate().getMonth()+1;
//			    int endMonth = dto.getEndDate().getMonth()+1;
//			    for(int i = startMonth;i<=endMonth;i++) {
//			    	selectCondition+="SUM(m."+getMonthName(i)+") as "+getMonthName(i)+"Total,";
//			    }
//			}
//
//			String query = "SELECT m.emp_id as employeeId, CONCAT(e.first_name, ' ', e.last_name) as employeeName, "+selectCondition
//					+ "m.year as year, "
//					+ "(SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) as yearlyTotal, "
//					+ "ROUND((SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) / 12.0, 2)*100 as yearlyPercentage "
//					+ "FROM monthly_entries m INNER JOIN employee_master e ON m.emp_id = e.emp_id "
//					+ "INNER JOIN project_master p ON m.project_id = p.project_id " + whereCondition+" "
//					+ "GROUP BY m.emp_id, e.first_name, e.last_name, m.year";
//			
//			System.err.println("======= Final Query "+query);
//			
//			List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
//
//			return new Response(1, "Succcess", result);
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			return new Response(1, "Failed", null);
//		}
//	}
	public Response monthlyEntriesFilter(MonthlyEntryDTO dto) {
	    try {
	        String whereCondition = "";
	        String selectCondition = "";

	        if (dto.getEmployeeId() != null && dto.getProjectId() == null && dto.getStartDate() == null
	                && dto.getEndDate() == null) {
	            whereCondition = "where m.emp_id=" + dto.getEmployeeId();
	        }
	        if (dto.getEmployeeId() == null && dto.getProjectId() != null && dto.getStartDate() == null
	                && dto.getEndDate() == null) {
	            whereCondition = "where m.project_id=" + dto.getProjectId();
	        }

	        if (dto.getStartDate() != null && dto.getEndDate() != null) {
	            int startMonth = dto.getStartDate().getMonth() + 1;
	            int endMonth = dto.getEndDate().getMonth() + 1;
	            int startYear = dto.getStartDate().getYear() + 1900; // Adjust for year
	            int endYear = dto.getEndDate().getYear() + 1900; // Adjust for year

	            // Constructing dynamic month columns for the requested months only
	            for (int year = startYear; year <= endYear; year++) {
	                for (int month = startMonth; month <= endMonth; month++) {
	                    String monthName = getMonthName(month);
	                    selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + year + "-" + month + "-01') = " + month + " THEN m." + monthName + " ELSE 0 END) AS " + monthName + "Total,";
	                }
	            }
	        }

	        String query = "SELECT m.emp_id as employeeId, CONCAT(e.first_name, ' ', e.last_name) as employeeName, "
	                + selectCondition
	                + "m.year as year, "
	                + "(SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) as yearlyTotal, "
	                + "ROUND((SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) / 12.0, 2)*100 as yearlyPercentage "
	                + "FROM monthly_entries m INNER JOIN employee_master e ON m.emp_id = e.emp_id "
	                + "INNER JOIN project_master p ON m.project_id = p.project_id " + whereCondition + " "
	                + "GROUP BY m.emp_id, e.first_name, e.last_name, m.year";

	        System.err.println("======= Final Query " + query);

	        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

	        return new Response(1, "Success", result);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return new Response(1, "Failed", null);
	    }
	}

	
	private String getMonthName(int month) {
	    switch (month) {
	        case 1: return "january";
	        case 2: return "february";
	        case 3: return "march";
	        case 4: return "april";
	        case 5: return "may";
	        case 6: return "june";
	        case 7: return "july";
	        case 8: return "august";
	        case 9: return "september";
	        case 10: return "october";
	        case 11: return "november";
	        case 12: return "december";
	        default: return null;
	    }
	}

}
