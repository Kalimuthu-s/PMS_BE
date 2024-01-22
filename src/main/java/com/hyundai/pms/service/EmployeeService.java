package com.hyundai.pms.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hyundai.pms.entity.DepartmentMaster;
import com.hyundai.pms.entity.DesignationMaster;
import com.hyundai.pms.entity.EmployeeMaster;
import com.hyundai.pms.entity.ExperienceMaster;
import com.hyundai.pms.entity.LocationMaster;
import com.hyundai.pms.entity.PmsResponseMessage;
import com.hyundai.pms.entity.TeamMaster;
import com.hyundai.pms.repository.DepartmentRepository;
import com.hyundai.pms.repository.DesignationRepository;
import com.hyundai.pms.repository.EmployeeRepository;
import com.hyundai.pms.repository.ExperienceRepository;
import com.hyundai.pms.repository.LocationRepository;
import com.hyundai.pms.repository.TeamRepository;
import com.zaxxer.hikari.HikariDataSource;

@Service
public class EmployeeService {
	

	@Autowired
	private DepartmentRepository departmentrepo;

	@Autowired
	private TeamRepository teamrepo;

	@Autowired
	private EmployeeRepository employeerepo;

	@Autowired
	private LocationRepository locationrepo;

	@Autowired
	private DesignationRepository designationrepo;

	@Autowired
	private ExperienceRepository exprepo;


	public PmsResponseMessage getAll() {
		try {
			System.err.println("method is calling>>>>>>>>>>>>>>");
			List<Map<String, Object>> emps = employeerepo.findAllEmployees();
			return new PmsResponseMessage(1, "Success", emps, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(-1, "Internal Server Error", null, false);
		}
	}
	
	public PmsResponseMessage getallemployee() {
		try {
			System.err.println("method is calling>>>>>>>>>>>>>>");
			List<EmployeeMaster> emps = employeerepo.findAll();
					return new PmsResponseMessage(200, "Success", emps, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(500, "Internal Server Error", null, false);
		}
	}
	
	

	public PmsResponseMessage create(EmployeeMaster employeemaster) {
		try {
			employeemaster = employeerepo.save(employeemaster);
			return new PmsResponseMessage(201, "Data saved successfully", employeemaster, true);
		} catch (Exception e) {
			return new PmsResponseMessage(-2, "Internal Server Error", null, false);
		}
	}

	public PmsResponseMessage getAllManagers() {
		try {
			List<Map<String, Object>> managerData = employeerepo.findManagers("Manager");
			System.err.println("?????????????????????" + managerData.toString());
			return new PmsResponseMessage(1, "Success", managerData, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(-1, "Internal Server Error", null, false);
		}
	}

	public PmsResponseMessage getAllDepartments() {
		try {
			List<DepartmentMaster> departments = departmentrepo.findAll();
			return new PmsResponseMessage(200, "Success", departments, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(500, "Internal Server Error", null, false);
		}
	}

	public PmsResponseMessage getAllTeams() {
		try {
			List<TeamMaster> teams = teamrepo.findAll();
			return new PmsResponseMessage(200, "Success", teams, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(500, "Internal Server Error", null, false);
		}
	}

	public PmsResponseMessage getAllLocations() {
		try {
			List<LocationMaster> locations = locationrepo.findAll();
			return new PmsResponseMessage(200, "Success", locations, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(500, "Internal Server Error", null, false);
		}
	}

	public PmsResponseMessage getAllExperiences() {
		try {
			List<ExperienceMaster> experiences = exprepo
					.findAll();

			return new PmsResponseMessage(200, "Success", experiences, true);
		} catch (Exception e) {
			return new PmsResponseMessage(500, "Internal Server Error", null, false);
		}
	}

	public PmsResponseMessage getAllDesignations() {
		try {
			List<DesignationMaster> designation = designationrepo.findAll();
			return new PmsResponseMessage(200, "Success", designation, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(500, "Internal Server Error", null, false);
		}
	}

	public PmsResponseMessage getById(Integer emp_id) {
		List<Map<String, Object>> empOptional = employeerepo.findByEmployeeId(emp_id);

		if (!empOptional.isEmpty()) {
			return new PmsResponseMessage(200, "success", empOptional, true);
		} else {
			return new PmsResponseMessage(500, "No Data Found", null, false);
		}
	}

	public PmsResponseMessage updateEmp(EmployeeMaster employeemaster) {
		try {
			int empId = employeemaster.getEmp_id();
			if (empId > 0) {
				EmployeeMaster updatedEmployee = employeerepo.save(employeemaster);
				return new PmsResponseMessage(1, "Employee Updated Successfully", updatedEmployee, true);
			}
			return new PmsResponseMessage(2, "Employee Not Updated", null, false);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(-2, "Internal Server Error", null, false);
		}
	}

	public PmsResponseMessage deleteAndReturn(int emp_id) {
		Optional<EmployeeMaster> deletedEmployee = employeerepo.findById(emp_id);

		if (deletedEmployee.isPresent()) {
			employeerepo.deleteById(emp_id);
			return new PmsResponseMessage(200, "Deletion successful", deletedEmployee, true);
		} else {
			return new PmsResponseMessage(500, "Deletion failed - Employee not found", null, false);
		}
	}

	// Excell upload insert into database

//	public boolean insertEmployeeData(Employeemaster employee) {
//		String sql = "INSERT INTO employee_master (first_name, last_name, date_of_birth, hire_date, "
//				+ "designation_id, dept_id, manager_id, location_id, phone_number, "
//				+ "experience_id, team_id, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//		try (Connection connection = hikariDataSource.getConnection();
//				PreparedStatement statement = connection.prepareStatement(sql)) {
//
//			statement.setString(1, employee.getFirst_name());
//			statement.setString(2, employee.getLast_name());
//			statement.setString(3, (employee.getDate_of_birth()));
//			statement.setString(4, (employee.getHire_date()));
//			statement.setString(5, employee.getDesignation_id());
//			statement.setString(6, employee.getDept_id());
//			statement.setString(7, employee.getManager_id());
//			statement.setString(8, employee.getLocation_id());
//			statement.setString(9, employee.getPhone_number());
//			statement.setString(10, employee.getExperience_id());
//			statement.setString(11, employee.getTeam_id());
//			statement.setString(12, employee.getEmail());
//
//			int rowsAffected = statement.executeUpdate();
//
//			return rowsAffected > 0;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}


//	@Transactional
//    public List<EmployeeExcellDto> processExcelFile(MultipartFile file)
//            throws IOException, EncryptedDocumentException, ParseException {
//        try {
//            List<EmployeeExcellDto> employees = excelHelper.readExcelFile(file);
//            List<Employeemaster> entities = employees.stream()
//                    .map(EmployeeMapper::dtoToEntity)
//                    .collect(Collectors.toList());
//            entities = repo.saveAll(entities);
//
//            return entities.stream()
//                    .map(EmployeeMapper::entityToDto)
//                    .collect(Collectors.toList());
//        } catch (IOException | EncryptedDocumentException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }

//	@Autowired
//	private EmployeeExcelDtoRepo dtorepo;



}
