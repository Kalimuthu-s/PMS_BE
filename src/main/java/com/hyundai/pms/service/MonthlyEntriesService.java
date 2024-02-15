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

			double totalContributionForjanuary = getTotalForJanuary(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			if ((totalContributionForjanuary + updatedMonthlyEntry.getJanuary()) > 1.0) {

				return new Response(2, "Total contribution for the employee in january month exceeds 1", null);
			}
			double totalContributionForfebruary = getTotalForFebruary(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			if ((totalContributionForfebruary + updatedMonthlyEntry.getFebruary()) > 1.0) {
				return new Response(2, "Total contribution for the employee in february month exceeds 1", null);
			}
			double totalContributionForMarch = getTotalForMarch(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			System.err.println("====================> " + totalContributionForMarch);
			if ((totalContributionForMarch + updatedMonthlyEntry.getMarch()) > 1.0) {
				return new Response(2, "Total contribution for the employee in march month exceeds 1", null);
			}
			double totalContributionForApril = getTotalForApril(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			if ((totalContributionForApril + updatedMonthlyEntry.getApril()) > 1.0) {
				return new Response(2, "Total contribution for the employee in april month exceeds 1", null);
			}
			double totalContributionForMay = getTotalForMay(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			if ((totalContributionForMay + updatedMonthlyEntry.getMay()) > 1.0) {
				return new Response(2, "Total contribution for the employee in may month exceeds 1", null);
			}
			double totalContributionForJune = getTotalForJune(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			if ((totalContributionForJune + updatedMonthlyEntry.getJune()) > 1.0) {
				return new Response(2, "Total contribution for the employee in june month exceeds 1", null);
			}
			double totalContributionForJuly = getTotalForJuly(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			if ((totalContributionForJuly + updatedMonthlyEntry.getJuly()) > 1.0) {
				return new Response(2, "Total contribution for the employee in june month exceeds 1", null);
			}
			double totalContributionForAugust = getTotalForAugust(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			if ((totalContributionForAugust + updatedMonthlyEntry.getAugust()) > 1.0) {
				return new Response(2, "Total contribution for the employee in August month exceeds 1", null);
			}
			double totalContributionForSeptember = getTotalForSeptember(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			if ((totalContributionForSeptember + updatedMonthlyEntry.getSeptember()) > 1.0) {
				return new Response(2, "Total contribution for the employee in september month exceeds 1", null);
			}
			double totalContributionForOctober = getTotalForOctober(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			if ((totalContributionForOctober + updatedMonthlyEntry.getOctober()) > 1.0) {
				return new Response(2, "Total contribution for the employee in october month exceeds 1", null);
			}
			double totalContributionForNovember = getTotalForNovember(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			if ((totalContributionForNovember + updatedMonthlyEntry.getNovember()) > 1.0) {
				return new Response(2, "Total contribution for the employee in 	november month exceeds 1", null);
			}
			double totalContributionForDecember = getTotalForDecember(updatedMonthlyEntry.getYear(),
					updatedMonthlyEntry.getEmp_id(), updatedMonthlyEntry.getMonthlyId());
			if ((totalContributionForDecember + updatedMonthlyEntry.getDecember()) > 1.0) {
				return new Response(2, "Total contribution for the employee in December month exceeds 1", null);
			}
			updatedMonthlyEntry.setMonthlyId(updatedMonthlyEntry.getMonthlyId());
			monthlyEntriesRepository.save(updatedMonthlyEntry);
			return new Response(1, "success", updatedMonthlyEntry);
		} else {
			return new Response(2, "error", updatedMonthlyEntry);
		}
	}

	private double getTotalForJanuary(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getJanuary() != null)
					.mapToDouble(contribution -> contribution.getJanuary()).sum();
		}
		return 0.0;
	}

	private double getTotalForFebruary(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getFebruary() != null)
					.mapToDouble(contribution -> contribution.getFebruary()).sum();
		}
		return 0.0;

	}

	private double getTotalForMarch(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getMarch() != null)
					.mapToDouble(contribution -> contribution.getMarch()).sum();
		}
		return 0.0;
	}

	private double getTotalForApril(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getApril() != null)
					.mapToDouble(contribution -> contribution.getApril()).sum();
		}
		return 0.0;
	}

	private double getTotalForMay(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getMay() != null)
					.mapToDouble(contribution -> contribution.getMay()).sum();
		}
		return 0.0;
	}

	private double getTotalForJune(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getJune() != null)
					.mapToDouble(contribution -> contribution.getJune()).sum();
		}
		return 0.0;
	}

	private double getTotalForJuly(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getJuly() != null)
					.mapToDouble(contribution -> contribution.getJuly()).sum();
		}
		return 0.0;
	}

	private double getTotalForAugust(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getAugust() != null)
					.mapToDouble(contribution -> contribution.getAugust()).sum();
		}
		return 0.0;
	}

	private double getTotalForSeptember(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getSeptember() != null)
					.mapToDouble(contribution -> contribution.getSeptember()).sum();
		}
		return 0.0;
	}

	private double getTotalForOctober(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getOctober() != null)
					.mapToDouble(contribution -> contribution.getOctober()).sum();
		}
		return 0.0;
	}

	private double getTotalForDecember(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getNovember() != null)
					.mapToDouble(contribution -> contribution.getNovember()).sum();
		}
		return 0.0;
	}

	private double getTotalForNovember(String year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getDecember() != null)
					.mapToDouble(contribution -> contribution.getDecember()).sum();
		}
		return 0.0;
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
				int startYear = dto.getStartDate().getYear() + 1900;
				int endYear = dto.getEndDate().getYear() + 1900;

				for (int year = startYear; year <= endYear; year++) {
					for (int month = startMonth; month <= endMonth; month++) {
						String monthName = getMonthName(month);
						selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + year + "-" + month
								+ "-01') = " + month + " THEN m." + monthName + " ELSE 0 END) AS " + monthName
								+ "Total,";
					}
				}
			}

			String query = "SELECT m.emp_id as employeeId, CONCAT(e.first_name, ' ', e.last_name) as employeeName, "
					+ selectCondition + "m.year as year, "
					+ "(SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) as yearlyTotal, "
					+ "ROUND((SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) / 12.0, 2)*100 as yearlyPercentage "
					+ "FROM monthly_entries m INNER JOIN employee_master e ON m.emp_id = e.emp_id "
					+ "INNER JOIN project_master p ON m.project_id = p.project_id " + whereCondition + " "
					+ "GROUP BY m.emp_id, e.first_name, e.last_name, m.year";

			List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

			return new Response(1, "Success", result);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new Response(1, "Failed", null);
		}
	}

	public Response monthlyEntriesFilterByName(MonthlyEntryDTO dto) {
		try {
			String whereCondition = "";

			if (dto.getEmployeeId() != null && dto.getProjectId() == null && dto.getStartDate() == null
					&& dto.getEndDate() == null) {
				whereCondition = "where m.emp_id=" + dto.getEmployeeId()+" and m.year="+dto.getYear();
			}
			String query = "SELECT m.emp_id as employeeId, CONCAT(e.first_name, ' ', e.last_name) as employeeName,sum(m.january)as januaryTotal,sum(m.february)as \r\n"
					+ "februaryTotal,sum(m.march)as marchTotal,sum(m.april)as aprilTotal,sum(m.may)as mayTotal,sum(m.june)as juneTotal,sum(m.july)as julyTotal,\r\n"
					+ "sum(m.august)as augustTotal,sum(m.september)as septemberTotal,sum(m.october)as octoberTotal,sum(m.november)as novemberTotal,sum(m.december)as decemberTotal, "
					+ "(SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) as yearlyTotal, "
					+ "ROUND((SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) / 12.0, 2)*100 as yearlyPercentage "
					+ "FROM monthly_entries m INNER JOIN employee_master e ON m.emp_id = e.emp_id "
					+ "INNER JOIN project_master p ON m.project_id = p.project_id " + whereCondition + " "
					+ "GROUP BY m.emp_id,e.first_name";

			List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

			return new Response(1, "Success", result);

		} catch (Exception e) {
			return new Response(2, "Error", dto);
		}
	}

	public Response monthlyEntriesFilterByProject(MonthlyEntryDTO dto) {
		try {
			String whereCondition = "";

			if (dto.getEmployeeId() == null && dto.getProjectId() != null && dto.getStartDate() == null
					&& dto.getEndDate() == null) {
				whereCondition = "where m.project_id=" + dto.getProjectId()+" and m.year="+dto.getYear();
			}
			String query = "SELECT m.emp_id as employeeId, CONCAT(e.first_name, ' ', e.last_name) as employeeName,sum(m.january)as januaryTotal,sum(m.february)as \r\n"
					+ "februaryTotal,sum(m.march)as marchTotal,sum(m.april)as aprilTotal,sum(m.may)as mayTotal,sum(m.june)as juneTotal,sum(m.july)as julyTotal,\r\n"
					+ "sum(m.august)as augustTotal,sum(m.september)as septemberTotal,sum(m.october)as octoberTotal,sum(m.november)as novemberTotal,sum(m.december)as decemberTotal, "
					+ "(SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) as yearlyTotal, "
					+ "ROUND((SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) / 12.0, 2)*100 as yearlyPercentage "
					+ "FROM monthly_entries m INNER JOIN employee_master e ON m.emp_id = e.emp_id "
					+ "INNER JOIN project_master p ON m.project_id = p.project_id " + whereCondition
					+ " GROUP BY m.emp_id,e.first_name";

			List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

			return new Response(1, "Success", result);
		} catch (Exception e) {
			return new Response(2, "Error", dto);
		}
	}

	public Response monthlyEntriesFilterByDate(MonthlyEntryDTO dto) {
		try {
			 String whereCondition = "";
			String selectCondition = "";

			if (dto.getStartDate() != null && dto.getEndDate() != null) {
				int startMonth = dto.getStartDate().getMonth() + 1;
				int endMonth = dto.getEndDate().getMonth() + 1;
				int startYear = dto.getStartDate().getYear() + 1900;
				int endYear = dto.getEndDate().getYear() + 1900;

				for (int year = startYear; year <= endYear; year++) {
					for (int month = startMonth; month <= endMonth; month++) {
						String monthName = getMonthName(month);
						selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + year + "-" + month
								+ "-01') = " + month + " THEN m." + monthName + " ELSE 0 END) AS " + monthName
								+ "Total,";
					}
				}
			}
			
			whereCondition="where m.year="+dto.getYear();
			
			
			
			String query = "SELECT m.emp_id as employeeId, CONCAT(e.first_name, ' ', e.last_name) as employeeName, "
					+ selectCondition + " m.year as year, "
					+ "(SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) as yearlyTotal, "
					+ "ROUND((SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) / 12.0, 2)*100 as yearlyPercentage "
					+ "FROM monthly_entries m INNER JOIN employee_master e ON m.emp_id = e.emp_id "
					+ "INNER JOIN project_master p ON m.project_id = p.project_id  " + whereCondition
					+ " GROUP BY m.emp_id, e.first_name, e.last_name, m.year";
			
			/*
			String query = "SELECT " + 
				    "m.emp_id as employeeId, " + "concat(e.first_name,' ',e.last_name) as employeeName,m.year as year "+selectCondition+
				    "ROUND(SUM(m.january), 2) as januaryTotal, " + 
				    "ROUND(SUM(m.february), 2) as februaryTotal, " + 
				    "ROUND(SUM(m.march), 2) as marchTotal, " + 
				    "ROUND(SUM(m.april), 2) as aprilTotal, " + 
				    "ROUND(SUM(m.may), 2) as mayTotal, " + 
				    "ROUND(SUM(m.june), 2) as juneTotal, " + 
				    "ROUND(SUM(m.july), 2) as julyTotal, " + 
				    "ROUND(SUM(m.august), 2) as augustTotal, " + 
				    "ROUND(SUM(m.september), 2) as septemberTotal, " + 
				    "ROUND(SUM(m.october), 2) as octoberTotal, " + 
				    "ROUND(SUM(m.november), 2) as novemberTotal, " + 
				    "ROUND(SUM(m.december), 2) as decemberTotal, " + 
				    "ROUND((SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)), 2) as total " + 
				    "FROM monthly_entries m " +  "INNER JOIN employee_master e ON m.emp_id = e.emp_id "+
				    "GROUP BY m.emp_id, e.first_name, e.last_name, m.year";

*/
			List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

			return new Response(1, "Success", result);

		} catch (Exception e) {
			return new Response(2, "Error", dto);
		}
	}

	private String getMonthName(int month) {
		switch (month) {
		case 1:
			return "january";
		case 2:
			return "february";
		case 3:
			return "march";
		case 4:
			return "april";
		case 5:
			return "may";
		case 6:
			return "june";
		case 7:
			return "july";
		case 8:
			return "august";
		case 9:
			return "september";
		case 10:
			return "october";
		case 11:
			return "november";
		case 12:
			return "december";
		default:
			return null;
		}
	}

}
