package com.hyundai.pms.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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

	private double getTotalForJanuary(int year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getJanuary() != null)
					.mapToDouble(contribution -> contribution.getJanuary()).sum();
		}
		return 0.0;
	}

	private double getTotalForFebruary(int year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getFebruary() != null)
					.mapToDouble(contribution -> contribution.getFebruary()).sum();
		}
		return 0.0;

	}

	private double getTotalForMarch(int year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getMarch() != null)
					.mapToDouble(contribution -> contribution.getMarch()).sum();
		}
		return 0.0;
	}

	private double getTotalForApril(int year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getApril() != null)
					.mapToDouble(contribution -> contribution.getApril()).sum();
		}
		return 0.0;
	}

	private double getTotalForMay(int year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getMay() != null)
					.mapToDouble(contribution -> contribution.getMay()).sum();
		}
		return 0.0;
	}

	private double getTotalForJune(int year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getJune() != null)
					.mapToDouble(contribution -> contribution.getJune()).sum();
		}
		return 0.0;
	}

	private double getTotalForJuly(int year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getJuly() != null)
					.mapToDouble(contribution -> contribution.getJuly()).sum();
		}
		return 0.0;
	}

	private double getTotalForAugust(int year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getAugust() != null)
					.mapToDouble(contribution -> contribution.getAugust()).sum();
		}
		return 0.0;
	}

	private double getTotalForSeptember(int year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getSeptember() != null)
					.mapToDouble(contribution -> contribution.getSeptember()).sum();
		}
		return 0.0;
	}

	private double getTotalForOctober(int year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getOctober() != null)
					.mapToDouble(contribution -> contribution.getOctober()).sum();
		}
		return 0.0;
	}

	private double getTotalForDecember(int year, Long employeeId, int monthId) {
		List<MonthlyEntries> existingEntries = monthlyEntriesRepository.findByEmpIdAndYear(employeeId, year, monthId);
		if (!existingEntries.isEmpty()) {
			return existingEntries.stream().filter(data -> data.getNovember() != null)
					.mapToDouble(contribution -> contribution.getNovember()).sum();
		}
		return 0.0;
	}

	private double getTotalForNovember(int year, Long employeeId, int monthId) {
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
		    String yearCondition="";
		    String totalCondition = "";
		    int monthCount=0;
			
			if (dto.getEmployeeId() != null && dto.getProjectId() == null && dto.getStartDate() != null && dto.getEndDate() != null && dto.getTeamId()==null) {
				System.err.println("=========> employee and date filter "+dto.getEmployeeId()+dto.getProjectId()+dto.getStartDate()+dto.getEndDate());

				LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDate = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    
			    LocalDate startDateForYear = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForYear = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate startDateForTotal = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForTotal = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    boolean isFirstIteration = true;
			    boolean isFirstIteration1 = true;

			    while(!startDateForYear.isAfter(endDateForYear)) {
			        String year = String.valueOf(startDateForYear.getYear());
			        if (!isFirstIteration) {
			            yearCondition += ",";
			        } else {
			            isFirstIteration = false;
			        }
			        System.err.println("=========> Year is "+year);
			        
			        yearCondition += "'"+year+"'";
			        
			        startDateForYear = startDateForYear.plusYears(1);
			    }
			    String lastYear = String.valueOf(endDateForYear.getYear());
			    if (!yearCondition.contains("'" + lastYear + "'")) {
			        yearCondition += ",'" + lastYear + "'";
			    }
			    while (!startDate.isAfter(endDate)) {
			        String monthName = startDate.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDate.getYear());
			        selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDate + "') = " + startDate.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) AS " + monthName + year + "Total,";
			        startDate = startDate.plusMonths(1);
			    }
			   
			    whereCondition = "where m.emp_id='"+dto.getEmployeeId()+"' and m.year in ("+yearCondition+")";

			    while (!startDateForTotal.isAfter(endDateForTotal)) {
			        String monthName = startDateForTotal.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDateForTotal.getYear());
			        if (!isFirstIteration1) {
			            totalCondition += "+";
			        } else {
			            isFirstIteration1 = false;
			        }
			        totalCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDateForTotal + "') = " + startDateForTotal.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) ";
			        startDateForTotal = startDateForTotal.plusMonths(1);
			        System.err.println("=======> month count "+monthCount);
			        monthCount =monthCount+1;
			       
			    }
			}
			
			if (dto.getEmployeeId() == null && dto.getProjectId() != null && dto.getStartDate() != null && dto.getEndDate() != null && dto.getTeamId()==null) {
				System.err.println("=========> project and date filter "+dto.getEmployeeId()+dto.getProjectId()+dto.getStartDate()+dto.getEndDate());

				LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDate = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    
			    LocalDate startDateForYear = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForYear = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate startDateForTotal = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForTotal = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			    boolean isFirstIteration = true;
			    boolean isFirstIteration1 = true;

			    while(!startDateForYear.isAfter(endDateForYear)) {
			        String year = String.valueOf(startDateForYear.getYear());
			        if (!isFirstIteration) {
			            yearCondition += ",";
			        } else {
			            isFirstIteration = false;
			        }
			        System.err.println("=========> Year is "+year);
			        
			        yearCondition += "'"+year+"'";
			        
			        startDateForYear = startDateForYear.plusYears(1);
			    }
			    String lastYear = String.valueOf(endDateForYear.getYear());
			    if (!yearCondition.contains("'" + lastYear + "'")) {
			        yearCondition += ",'" + lastYear + "'";
			    }
			    while (!startDate.isAfter(endDate)) {
			        String monthName = startDate.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDate.getYear());
			        selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDate + "') = " + startDate.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) AS " + monthName + year + "Total,";
			        startDate = startDate.plusMonths(1);
			    }
			   
			    whereCondition = "where m.project_id='"+dto.getProjectId()+"' and m.year in ("+yearCondition+")";

			    while (!startDateForTotal.isAfter(endDateForTotal)) {
			        String monthName = startDateForTotal.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDateForTotal.getYear());
			        if (!isFirstIteration1) {
			            totalCondition += "+";
			        } else {
			            isFirstIteration1 = false;
			        }
			        totalCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDateForTotal + "') = " + startDateForTotal.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) ";
			        startDateForTotal = startDateForTotal.plusMonths(1);
			        System.err.println("=======> month count "+monthCount);
			        monthCount =monthCount+1;
			       
			    }
			}

			if (dto.getEmployeeId() == null && dto.getProjectId() == null && dto.getStartDate() != null && dto.getEndDate() != null && dto.getTeamId()==null) {
				System.err.println("=========> date only filter "+dto.getEmployeeId()+dto.getProjectId()+dto.getStartDate()+dto.getEndDate());

				LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDate = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    
			    LocalDate startDateForYear = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForYear = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate startDateForTotal = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForTotal = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    boolean isFirstIteration = true;
			    boolean isFirstIteration1 = true;

			    while(!startDateForYear.isAfter(endDateForYear)) {
			        String year = String.valueOf(startDateForYear.getYear());
			        if (!isFirstIteration) {
			            yearCondition += ",";
			        } else {
			            isFirstIteration = false;
			        }
			        System.err.println("=========> Year is "+year);
			        
			        yearCondition += "'"+year+"'";
			        
			        startDateForYear = startDateForYear.plusYears(1);
			    }
			    String lastYear = String.valueOf(endDateForYear.getYear());
			    if (!yearCondition.contains("'" + lastYear + "'")) {
			        yearCondition += ",'" + lastYear + "'";
			    }
			    while (!startDate.isAfter(endDate)) {
			        String monthName = startDate.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDate.getYear());
			        selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDate + "') = " + startDate.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) AS " + monthName + year + "Total,";
			        startDate = startDate.plusMonths(1);
			    }
			   
			    whereCondition = "where m.year in ("+yearCondition+")";

			    while (!startDateForTotal.isAfter(endDateForTotal)) {
			        String monthName = startDateForTotal.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDateForTotal.getYear());
			        if (!isFirstIteration1) {
			            totalCondition += "+";
			        } else {
			            isFirstIteration1 = false;
			        }
			        totalCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDateForTotal + "') = " + startDateForTotal.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) ";
			        startDateForTotal = startDateForTotal.plusMonths(1);
			        System.err.println("=======> month count "+monthCount);
			        monthCount =monthCount+1;
			       
			    }
			}
			
			if (dto.getEmployeeId() == null && dto.getProjectId() == null && dto.getStartDate() != null && dto.getEndDate() != null && dto.getTeamId()!=null) {
				System.err.println("=========> team & date filter "+dto.getEmployeeId()+dto.getProjectId()+dto.getStartDate()+dto.getEndDate());

				LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDate = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    
			    LocalDate startDateForYear = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForYear = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate startDateForTotal = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForTotal = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    boolean isFirstIteration = true;
			    boolean isFirstIteration1 = true;

			    while(!startDateForYear.isAfter(endDateForYear)) {
			        String year = String.valueOf(startDateForYear.getYear());
			        if (!isFirstIteration) {
			            yearCondition += ",";
			        } else {
			            isFirstIteration = false;
			        }
			        System.err.println("=========> Year is "+year);
			        
			        yearCondition += "'"+year+"'";
			        
			        startDateForYear = startDateForYear.plusYears(1);
			    }
			    String lastYear = String.valueOf(endDateForYear.getYear());
			    if (!yearCondition.contains("'" + lastYear + "'")) {
			        yearCondition += ",'" + lastYear + "'";
			    }
			    while (!startDate.isAfter(endDate)) {
			        String monthName = startDate.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDate.getYear());
			        selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDate + "') = " + startDate.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) AS " + monthName + year + "Total,";
			        startDate = startDate.plusMonths(1);
			    }
			   
			    whereCondition = "where t.team_id='"+dto.getTeamId()+"' and m.year in ("+yearCondition+")";

			    while (!startDateForTotal.isAfter(endDateForTotal)) {
			        String monthName = startDateForTotal.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDateForTotal.getYear());
			        if (!isFirstIteration1) {
			            totalCondition += "+";
			        } else {
			            isFirstIteration1 = false;
			        }
			        totalCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDateForTotal + "') = " + startDateForTotal.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) ";
			        startDateForTotal = startDateForTotal.plusMonths(1);
			        System.err.println("=======> month count "+monthCount);
			        monthCount =monthCount+1;
			       
			    }
			}
			
			

			
			if (dto.getEmployeeId() != null && dto.getProjectId() != null && dto.getStartDate() != null && dto.getEndDate() != null && dto.getTeamId()==null) {
				System.err.println("=========> emp,proj,date filter "+dto.getEmployeeId()+dto.getProjectId()+dto.getStartDate()+dto.getEndDate());

			    LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDate = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    
			    LocalDate startDateForYear = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForYear = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate startDateForTotal = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForTotal = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    boolean isFirstIteration = true;
			    boolean isFirstIteration1 = true;

			    while(!startDateForYear.isAfter(endDateForYear)) {
			        String year = String.valueOf(startDateForYear.getYear());
			        if (!isFirstIteration) {
			            yearCondition += ",";
			        } else {
			            isFirstIteration = false;
			        }
			        System.err.println("=========> Year is "+year);
			        
			        yearCondition += "'"+year+"'";
			        
			        startDateForYear = startDateForYear.plusYears(1);
			    }
			    String lastYear = String.valueOf(endDateForYear.getYear());
			    if (!yearCondition.contains("'" + lastYear + "'")) {
			        yearCondition += ",'" + lastYear + "'";
			    }
			    while (!startDate.isAfter(endDate)) {
			        String monthName = startDate.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDate.getYear());
			        selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDate + "') = " + startDate.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) AS " + monthName + year + "Total,";
			        startDate = startDate.plusMonths(1);
			    }
			   
			    whereCondition = "where m.emp_id=" + dto.getEmployeeId() + " and m.project_id=" + dto.getProjectId() + " and m.year in ("+yearCondition+")";

			    while (!startDateForTotal.isAfter(endDateForTotal)) {
			        String monthName = startDateForTotal.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDateForTotal.getYear());
			        if (!isFirstIteration1) {
			            totalCondition += "+";
			        } else {
			            isFirstIteration1 = false;
			        }
			        totalCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDateForTotal + "') = " + startDateForTotal.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) ";
			        startDateForTotal = startDateForTotal.plusMonths(1);
			        System.err.println("=======> month count "+monthCount);
			        monthCount =monthCount+1;
			       
			    }
			}
			
			if (dto.getEmployeeId() != null && dto.getProjectId() != null && dto.getStartDate() != null && dto.getEndDate() != null && dto.getTeamId()!=null) {
				System.err.println("=========> all filter "+dto.getEmployeeId()+dto.getProjectId()+dto.getStartDate()+dto.getEndDate());

			    LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDate = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    
			    LocalDate startDateForYear = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForYear = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate startDateForTotal = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForTotal = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    boolean isFirstIteration = true;
			    boolean isFirstIteration1 = true;

			    while(!startDateForYear.isAfter(endDateForYear)) {
			        String year = String.valueOf(startDateForYear.getYear());
			        if (!isFirstIteration) {
			            yearCondition += ",";
			        } else {
			            isFirstIteration = false;
			        }
			        System.err.println("=========> Year is "+year);
			        
			        yearCondition += "'"+year+"'";
			        
			        startDateForYear = startDateForYear.plusYears(1);
			    }
			    String lastYear = String.valueOf(endDateForYear.getYear());
			    if (!yearCondition.contains("'" + lastYear + "'")) {
			        yearCondition += ",'" + lastYear + "'";
			    }
			    while (!startDate.isAfter(endDate)) {
			        String monthName = startDate.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDate.getYear());
			        selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDate + "') = " + startDate.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) AS " + monthName + year + "Total,";
			        startDate = startDate.plusMonths(1);
			    }
			   
			    whereCondition = "where m.emp_id=" + dto.getEmployeeId() + " and m.project_id=" + dto.getProjectId() + " and t.team_id='"+dto.getTeamId()+"' and m.year in ("+yearCondition+")";

			    while (!startDateForTotal.isAfter(endDateForTotal)) {
			        String monthName = startDateForTotal.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDateForTotal.getYear());
			        if (!isFirstIteration1) {
			            totalCondition += "+";
			        } else {
			            isFirstIteration1 = false;
			        }
			        totalCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDateForTotal + "') = " + startDateForTotal.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) ";
			        startDateForTotal = startDateForTotal.plusMonths(1);
			        System.err.println("=======> month count "+monthCount);
			        monthCount =monthCount+1;
			       
			    }
			}
			
			if (dto.getEmployeeId() != null && dto.getProjectId() == null && dto.getStartDate() != null && dto.getEndDate() != null && dto.getTeamId()!=null) {
				System.err.println("=========> Employee,Team,Date filter "+dto.getEmployeeId()+dto.getProjectId()+dto.getStartDate()+dto.getEndDate());

			    LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDate = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    
			    LocalDate startDateForYear = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForYear = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate startDateForTotal = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForTotal = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    boolean isFirstIteration = true;
			    boolean isFirstIteration1 = true;

			    while(!startDateForYear.isAfter(endDateForYear)) {
			        String year = String.valueOf(startDateForYear.getYear());
			        if (!isFirstIteration) {
			            yearCondition += ",";
			        } else {
			            isFirstIteration = false;
			        }
			        System.err.println("=========> Year is "+year);
			        
			        yearCondition += "'"+year+"'";
			        
			        startDateForYear = startDateForYear.plusYears(1);
			    }
			    String lastYear = String.valueOf(endDateForYear.getYear());
			    if (!yearCondition.contains("'" + lastYear + "'")) {
			        yearCondition += ",'" + lastYear + "'";
			    }
			    while (!startDate.isAfter(endDate)) {
			        String monthName = startDate.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDate.getYear());
			        selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDate + "') = " + startDate.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) AS " + monthName + year + "Total,";
			        startDate = startDate.plusMonths(1);
			    }
			   
			    whereCondition = "where m.emp_id=" + dto.getEmployeeId() + " and t.team_id='"+dto.getTeamId()+"' and m.year in ("+yearCondition+")";

			    while (!startDateForTotal.isAfter(endDateForTotal)) {
			        String monthName = startDateForTotal.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDateForTotal.getYear());
			        if (!isFirstIteration1) {
			            totalCondition += "+";
			        } else {
			            isFirstIteration1 = false;
			        }
			        totalCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDateForTotal + "') = " + startDateForTotal.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) ";
			        startDateForTotal = startDateForTotal.plusMonths(1);
			        System.err.println("=======> month count "+monthCount);
			        monthCount =monthCount+1;
			       
			    }
			}
			
			if (dto.getEmployeeId() == null && dto.getProjectId() != null && dto.getStartDate() != null && dto.getEndDate() != null && dto.getTeamId()!=null) {
				System.err.println("=========> Project,Team,Date filter "+dto.getEmployeeId()+dto.getProjectId()+dto.getStartDate()+dto.getEndDate());

			    LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDate = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    
			    LocalDate startDateForYear = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForYear = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate startDateForTotal = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate endDateForTotal = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    boolean isFirstIteration = true;
			    boolean isFirstIteration1 = true;

			    while(!startDateForYear.isAfter(endDateForYear)) {
			        String year = String.valueOf(startDateForYear.getYear());
			        if (!isFirstIteration) {
			            yearCondition += ",";
			        } else {
			            isFirstIteration = false;
			        }
			        System.err.println("=========> Year is "+year);
			        
			        yearCondition += "'"+year+"'";
			        
			        startDateForYear = startDateForYear.plusYears(1);
			    }
			    String lastYear = String.valueOf(endDateForYear.getYear());
			    if (!yearCondition.contains("'" + lastYear + "'")) {
			        yearCondition += ",'" + lastYear + "'";
			    }
			    while (!startDate.isAfter(endDate)) {
			        String monthName = startDate.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDate.getYear());
			        selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDate + "') = " + startDate.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) AS " + monthName + year + "Total,";
			        startDate = startDate.plusMonths(1);
			    }
			   
			    whereCondition = "where m.project_id=" + dto.getProjectId() + " and t.team_id='"+dto.getTeamId()+"' and m.year in ("+yearCondition+")";

			    while (!startDateForTotal.isAfter(endDateForTotal)) {
			        String monthName = startDateForTotal.getMonth().toString().toLowerCase();
			        String year = String.valueOf(startDateForTotal.getYear());
			        if (!isFirstIteration1) {
			            totalCondition += "+";
			        } else {
			            isFirstIteration1 = false;
			        }
			        totalCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + startDateForTotal + "') = " + startDateForTotal.getMonthValue() + " THEN m." + monthName + " ELSE 0 END) ";
			        startDateForTotal = startDateForTotal.plusMonths(1);
			        System.err.println("=======> month count "+monthCount);
			        monthCount =monthCount+1;
			       
			    }
			}
			 

			String query = "SELECT m.emp_id as employeeId, e.hire_date as hireDate, CONCAT(e.first_name, ' ', e.last_name) as employeeName, "
					+ selectCondition 
					+ "(("+totalCondition+") / "+monthCount+")*100 as yearlyPercentage "
					+ "FROM monthly_entries m INNER JOIN employee_master e ON m.emp_id = e.emp_id "
					+ "INNER JOIN team_master t ON t.team_id=e.team_id "
					+ "INNER JOIN project_master p ON m.project_id = p.project_id " + whereCondition + " "
					+ "GROUP BY m.emp_id, e.first_name, e.last_name";
			
			System.err.println("=========> "+query);

			List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

			return new Response(1, "Success", result);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new Response(1, "Failed", null);
		}
	}
	

	
	}

	
//	public Response monthlyEntriesFilter(MonthlyEntryDTO dto) {
//	    try {
//	        List<Map<String, Object>> finalResult = new ArrayList<>();
//
//	        int startYear = dto.getStartDate().getYear() + 1900;
//	        int endYear = dto.getEndDate().getYear() + 1900;
//
//	        System.err.println("=========> date filter " + dto.getEmployeeId() + dto.getProjectId() + dto.getStartDate() + dto.getEndDate());
//	        
//	        for (int year = startYear; year <= endYear; year++) {
//	            String whereCondition = "where m.year=" + year;
//	            String selectCondition = "";
//
//	            for (int month = 1; month <= 12; month++) {
//	                String monthName = getMonthName(month);
//	                selectCondition += "SUM(CASE WHEN m.year = " + year + " AND MONTH('" + year + "-" + month
//	                        + "-01') = " + month + " THEN m." + monthName + " ELSE 0 END) AS " + monthName
//	                        + "Total,";
//	            }
//
//	            String query = "SELECT m.emp_id as employeeId, CONCAT(e.first_name, ' ', e.last_name) as employeeName, "
//	                    + selectCondition + "m.year as year, "
//	                    + "(SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) as yearlyTotal, "
//	                    + "ROUND((SUM(m.january) + SUM(m.february) + SUM(m.march) + SUM(m.april) + SUM(m.may) + SUM(m.june) + SUM(m.july) + SUM(m.august) + SUM(m.september) + SUM(m.october) + SUM(m.november) + SUM(m.december)) / 12.0, 2)*100 as yearlyPercentage "
//	                    + "FROM monthly_entries m INNER JOIN employee_master e ON m.emp_id = e.emp_id "
//	                    + "INNER JOIN project_master p ON m.project_id = p.project_id " + whereCondition + " "
//	                    + "GROUP BY m.emp_id, e.first_name, e.last_name, m.year";
//
//	            List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
//	            finalResult.addAll(result);
//	        }
//
//	        return new Response(1, "Success", finalResult);
//	    } catch (DataAccessException e) {
//	        e.printStackTrace();
//	        return new Response(1, "Failed", null);
//	    }
//	}

//	private String getMonthName(int month) {
//	    switch (month) {
//	        case 1:
//	            return "january";
//	        case 2:
//	            return "february";
//	        case 3:
//	            return "march";
//	        case 4:
//	            return "april";
//	        case 5:
//	            return "may";
//	        case 6:
//	            return "june";
//	        case 7:
//	            return "july";
//	        case 8:
//	            return "august";
//	        case 9:
//	            return "september";
//	        case 10:
//	            return "october";
//	        case 11:
//	            return "november";
//	        case 12:
//	            return "december";
//	        default:
//	            return null;
//	    }
//	}
//
	
//	public Response monthlyEntriesFilter(MonthlyEntryDTO dto) {
//	    try {
//	        List<Map<String, Object>> finalResult = new ArrayList<>();
//
//	        int startYear = dto.getStartDate().getYear() + 1900;
//	        int endYear = dto.getEndDate().getYear() + 1900;
//	        int startMonth = dto.getStartDate().getMonth() + 1;
//	        int endMonth = dto.getEndDate().getMonth() + 1;
//
//	        System.err.println("=========> date filter " + dto.getEmployeeId() + dto.getProjectId() + dto.getStartDate() + dto.getEndDate());
//	        
//	        for (int year = startYear; year <= endYear; year++) {
//	            String whereCondition = "WHERE MONTH(m.assigned_start_date) >= " + startMonth + " AND MONTH(m.assigned_end_date) <= " + endMonth;
//	            String query = "SELECT m.emp_id AS employeeId, CONCAT(e.first_name, ' ', e.last_name) AS employeeName, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 1 THEN m.january ELSE 0 END) AS january, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 2 THEN m.february ELSE 0 END) AS february, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 3 THEN m.march ELSE 0 END) AS march, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 4 THEN m.april ELSE 0 END) AS april, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 5 THEN m.may ELSE 0 END) AS may, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 6 THEN m.june ELSE 0 END) AS june, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 7 THEN m.july ELSE 0 END) AS july, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 8 THEN m.august ELSE 0 END) AS august, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 9 THEN m.september ELSE 0 END) AS september, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 10 THEN m.october ELSE 0 END) AS october, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 11 THEN m.november ELSE 0 END) AS november, " +
//	                    "SUM(CASE WHEN MONTH(m.assigned_start_date) = 12 THEN m.december ELSE 0 END) AS december " +
//	                    "FROM monthly_entries m " +
//	                    "INNER JOIN employee_master e ON m.emp_id = e.emp_id " +
//	                    "INNER JOIN project_master p ON m.project_id = p.project_id " +
//	                    whereCondition +
//	                    " GROUP BY m.emp_id, e.first_name, e.last_name";
//
//	            List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
//	            System.err.println(">>>>>>>>>>>>>>>>>>>>>>>"+query);
//	            finalResult.addAll(result);
//	        }
//
//
//	        return new Response(1, "Success", finalResult);
//	    } catch (DataAccessException e) {
//	        e.printStackTrace();
//	        return new Response(1, "Failed", null);
//	    }
//	}



