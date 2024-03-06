package com.hyundai.pms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.MonthlyDataDTO;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.service.EmployeeMonthlyDataService;

@RestController
@RequestMapping("/employeeMonthlyData")
@CrossOrigin(value="http://localhost:4200/")
public class EmployeeMonthlyDataController {
	
    @Autowired
    private EmployeeMonthlyDataService employeeMonthlyDataService;
//    
//    @PostMapping("/enter-contribution")
//    public Response enterContribution(@RequestBody MonthlyDataDTO MonthData) {
//
//        employeeMonthlyDataService.enterContribution(MonthData);
//
//        return new Response(1, "Success", null);
//    }
//    
// 
//    @GetMapping("/getAllMonthlyData")
//    public Response getAllEmployeeMonthlyDataWithJoin(){
//    	List<Map<String, Object>> list = employeeMonthlyDataService.getAllEmployeeMonthlyDataWithJoin();
//    	return new Response(1, "Success", list);
//    }
//    
//    
//    @PutMapping("/update-contribution")
//    public Response updateContribution(@RequestBody MonthlyDataDTO monthData) {
//       employeeMonthlyDataService.updateContribution(monthData);
//        return new Response(1, "Success", null);
//    }
//    
//    @RequestMapping("/delete-contribution")
//    public Response DeleteContribution(@RequestBody MonthlyDataDTO monthData) {
//        employeeMonthlyDataService.deleteContribution(monthData);
//        return new Response(1, "Success", monthData);
//    }

}
