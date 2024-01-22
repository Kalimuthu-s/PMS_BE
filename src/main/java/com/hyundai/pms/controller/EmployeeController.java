package com.hyundai.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.EmployeeMaster;
import com.hyundai.pms.entity.PmsResponseMessage;
import com.hyundai.pms.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(value = "http://localhost:4200/")
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@GetMapping("/getalllist")
	public PmsResponseMessage getAll() {
		return service.getAll();
	}
	
	@GetMapping("/getallemployee")
	public PmsResponseMessage getallemployee() {
		return service.getallemployee();
	}

	@PostMapping("/savedata")
	public PmsResponseMessage create(@RequestBody EmployeeMaster employeemaster) {
		return service.create(employeemaster);
	}

	@GetMapping("/allmanagers")
	public PmsResponseMessage getAllManagers() {
		return service.getAllManagers();
	}

	@GetMapping("/alldepartments")
	public PmsResponseMessage getAllDepartments() {
		return service.getAllDepartments();
	}

	@GetMapping("/allteams")
	public PmsResponseMessage getAllTeams() {
		return service.getAllTeams();
	}
	
	@GetMapping("/allLocations")
	public PmsResponseMessage getAllLocations() {
		return service.getAllLocations();
	}
	
	@GetMapping("/getAllDesignation")
	public PmsResponseMessage getAllDesignations() {
		return service.getAllDesignations();
	}
	
	@GetMapping("/getallExperiences")
	public PmsResponseMessage getAllExperiences() {
		return service.getAllExperiences();
	}
	
	@PostMapping("delete/{emp_id}")
	public PmsResponseMessage deleteEmployee(@PathVariable int emp_id) {
		return service.deleteAndReturn(emp_id);
	}

	@GetMapping("findById/{emp_id}")
	public PmsResponseMessage getById(@PathVariable Integer emp_id) {
		return service.getById(emp_id);
		
	}
	@PutMapping("/updatedata")
	public PmsResponseMessage Update(@RequestBody EmployeeMaster employeemaster) {
		System.err.println("??????????/"+employeemaster.toString());
			return service.updateEmp(employeemaster);
		
	}
	
	
	

	// @Autowired
	// private ExcelHelper excelhelper;


	// Delete operation by praveen k
//@DeleteMapping("delete/{emp_id}")
//public ResponseEntity<String> delete(@PathVariable Integer emp_id) {
//    service.delete(emp_id);
//    return new ResponseEntity<>("Data Delete Successfully", HttpStatus.NO_CONTENT);
//}

	/*
	 * @PostMapping("/upload") public ResponseEntity<ResponseMessage>
	 * uploadFile(@RequestParam("file") MultipartFile file) { String message = "";
	 * 
	 * if (file.isEmpty()) { message = "Please select a file to upload"; return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage()); }
	 * 
	 * try { if (service.dateValidation()) { message =
	 * "Data already exists in the employee table. Upload is not allowed."; return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage()); }
	 * 
	 * // Continue with the file upload logic here // ...
	 * 
	 * // If the file upload is successful, you can return a success message message
	 * = "File uploaded successfully"; return
	 * ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage()); } catch
	 * (Exception e) { e.printStackTrace(); message =
	 * "An error occurred during file upload: " + e.getMessage(); return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
	 * ResponseMessage()); } }
	 */

	/*
	 * @PostMapping("/upload") public ResponseEntity<ResponseMessage>
	 * uploadFile(@RequestParam("file") MultipartFile file) { String message = "";
	 * 
	 * if (file.isEmpty()) { message = "Please select a file to upload"; return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage()); }
	 * 
	 * try {
	 * 
	 * Employee_master employee = new Employee_master(); if
	 * (service.insertEmployeeData(employee)) { message =
	 * "Data already exists in the employee table. Upload is not allowed."; return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage()); }
	 * 
	 * // Continue with the file upload logic here // ...
	 * 
	 * // If the file upload is successful, you can return a success message message
	 * = "File uploaded successfully"; return
	 * ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage()); } catch
	 * (Exception e) { e.printStackTrace(); message =
	 * "An error occurred during file upload: " + e.getMessage(); return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
	 * ResponseMessage()); } }
	 */

//@PostMapping("/upload")
//public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
//    String message = "starting";
//
//    if (file.isEmpty()) {
//        message = "Please select a file to upload";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//    }
//
//    try {
//        List<Employeemaster> employees = service.processExcelFile(file);
//
//        if (employees.isEmpty()) {
//            message = "No data found in the Excel file";
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//        }
//
//        for (Employeemaster employee : employees) {
//            if (!service.insertEmployeeData(employee)) {
//                message = "Error inserting employee data into the database";
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
//            }
//        }
//
//        message = "File uploaded and data inserted successfully";
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//    } catch (Exception e) {
//        e.printStackTrace();
//        message = "An error occurred during file upload: " + e.getMessage();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
//    }
//}

	// @PostMapping("/savedata")
	// public ResponseEntity<Map<String, Object>> create(@RequestBody Employeemaster
	// employeemaster) {
//	    Employeemaster createData = service.create(employeemaster);
//	    Map<String, Object> responseMap = new HashMap<>();
//	    responseMap.put("data", createData);
//	    responseMap.put("message", "Data saved successfully");
//	    return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
	// }

	// Findby Id Data


}
