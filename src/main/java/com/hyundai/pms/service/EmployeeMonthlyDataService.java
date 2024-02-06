package com.hyundai.pms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.EmployeeMonthlyData;
import com.hyundai.pms.entity.MonthlyDataDTO;
import com.hyundai.pms.repository.EmployeeMonthlyDataRepository;

@Service
public class EmployeeMonthlyDataService {
	
    @Autowired
    private EmployeeMonthlyDataRepository employeeMonthlyDataRepository;

//    public void enterContribution(MonthlyDataDTO monthData) {
//    	System.err.println("===========================> "+monthData.toString());
//
//    	for (int i = 1; i <= 12; i++) {
//    		
//            EmployeeMonthlyData monthlyData = new EmployeeMonthlyData();
//            monthlyData.setEmployeeId(monthData.getEmpId());
//            monthlyData.setProjectId(monthData.getProjectId());
//
//            LocalDate monthDate = LocalDate.of(monthData.getYear(), i, 1);
//            // Set the month based on the loop variable i
//            monthlyData.setMonth(monthDate);
//
//            // Set the contribution based on the corresponding monthData field
//            switch (i) {
//                case 1:
//                    monthlyData.setContribution(handleNull(monthData.getJanuary()));
//                    break;
//                case 2:
//                    monthlyData.setContribution(handleNull(monthData.getFebruary()));
//                    break;
//                case 3:
//                    monthlyData.setContribution(handleNull(monthData.getMarch()));
//                    break;
//                case 4:
//                    monthlyData.setContribution(handleNull(monthData.getApril()));
//                    break;
//                case 5:
//                    monthlyData.setContribution(handleNull(monthData.getMay()));
//                    break;
//                case 6:
//                    monthlyData.setContribution(handleNull(monthData.getJune()));
//                    break;
//                case 7:
//                    monthlyData.setContribution(handleNull(monthData.getJuly()));
//                    break;
//                case 8:
//                    monthlyData.setContribution(handleNull(monthData.getAugust()));
//                    break;
//                case 9:
//                    monthlyData.setContribution(handleNull(monthData.getSeptember()));
//                    break;
//                case 10:
//                    monthlyData.setContribution(handleNull(monthData.getOctober()));
//                    break;
//                case 11:
//                    monthlyData.setContribution(handleNull(monthData.getNovember()));
//                    break;
//                case 12:
//                    monthlyData.setContribution(handleNull(monthData.getDecember()));
//                    break;   
//
//                default:
//                    break;
//            }
//
//            monthlyData.setYear(monthData.getYear());
//            
//          
//            double totalContributionForMonth = getTotalContributionForMonth(monthData.getEmpId(), monthlyData.getMonth());
//            if ((totalContributionForMonth + monthlyData.getContribution()) > 1.0) {
//                throw new IllegalArgumentException("Total contribution for the employee in the given month exceeds 1");
//            }
//            employeeMonthlyDataRepository.save(monthlyData); 
//        }
//    }
    
    
 
 
//    public void updateContribution(MonthlyDataDTO monthData) {
//        for (int i = 1; i <= 12; i++) {
//            LocalDate monthDate = LocalDate.of(2024, i, 1);
//            
//            Pageable pageable = PageRequest.of(0, 1);
//            List<EmployeeMonthlyData> result = employeeMonthlyDataRepository.getByEmployeeIdAndMonth(monthData.getEmpId(),monthData.getProjectId(),pageable);
//
//            if (!result.isEmpty()) {
//            	employeeMonthlyDataRepository.deleteEmployeeMonthData(monthData.getEmpId(),monthData.getProjectId());
//            	this.enterContribution(monthData);
//            }
//            else {
//            	throw new NullPointerException("There is no id of "+monthData.getEmpId() + " "+monthData.getProjectId());
//            }
//            
//        }
//    }

    
//    public void deleteContribution(MonthlyDataDTO monthData) {
//        // Check if the entry exists
//       
//        	employeeMonthlyDataRepository.deleteEmployeeMonthData(monthData.getEmpId(),monthData.getProjectId());
//       
//    }


//    private double getTotalContributionForMonth(Long employeeId, LocalDate month) {
//        List<EmployeeMonthlyData> monthlyDataList = employeeMonthlyDataRepository
//                .findByEmployeeIdAndMonth(employeeId, month);
//
//        if (!monthlyDataList.isEmpty()) {
//            return monthlyDataList.stream()
//                    .filter(data -> data.getContribution() != null)
//                    .mapToDouble(contribution -> contribution.getContribution())
//                    .sum();
//        }
//        return 0.0;
//    }
    
   
//    public List<Map<String, Object>> getAllEmployeeMonthlyDataWithJoin(){
//    	
//    	return employeeMonthlyDataRepository.getAllEmployeeMonthlyDataWithJoin();
//    }
//    
    
    private Double handleNull(Double value) {
        return (value != null) ? value : 0.0;
    }  

}
