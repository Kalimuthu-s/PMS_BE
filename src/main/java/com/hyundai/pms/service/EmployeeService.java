package com.hyundai.pms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.DepartmentMaster;
import com.hyundai.pms.entity.DesignationMaster;
import com.hyundai.pms.entity.EmployeeDTO;
import com.hyundai.pms.entity.EmployeeMaster;
import com.hyundai.pms.entity.ExperienceMaster;
import com.hyundai.pms.entity.LocationMaster;
import com.hyundai.pms.entity.PmsResponseMessage;
import com.hyundai.pms.entity.SkillDTO;
import com.hyundai.pms.entity.SkillGetDTO;
import com.hyundai.pms.entity.SkillMaster;
import com.hyundai.pms.entity.SkillTransactionMaster;
import com.hyundai.pms.entity.TeamMaster;
import com.hyundai.pms.repository.DepartmentRepository;
import com.hyundai.pms.repository.DesignationRepository;
import com.hyundai.pms.repository.EmployeeRepository;
import com.hyundai.pms.repository.ExperienceRepository;
import com.hyundai.pms.repository.LocationRepository;
import com.hyundai.pms.repository.SkillTransactionRepository;
import com.hyundai.pms.repository.TeamRepository;

@Service
public class EmployeeService {
	

	@Autowired
	private DepartmentRepository departmentrepo;

	@Autowired
	private TeamRepository teamrepo;
	
	@Autowired
	private SkillTransactionRepository skillRepo;

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
	
	public List<Map<String, Object>> getAllEmployeeNames(){
		return employeerepo.getAllEmployeeNames();
	}
	
	

	public PmsResponseMessage create(EmployeeMaster employeemaster) {
		try {
			employeemaster = employeerepo.save(employeemaster);
			return new PmsResponseMessage(201, "Data saved successfully", employeemaster, true);
		} catch (Exception e) {
			return new PmsResponseMessage(-2, "Internal Server Error", null, false);
		}
	}
	
	public PmsResponseMessage saveData(EmployeeDTO employeedto) {
		System.err.println("=================="+employeedto.getSkillList().toString());
		try {
			EmployeeMaster emp = new EmployeeMaster();
			
			emp.setFirst_name(employeedto.getFirst_name());
			emp.setLast_name(employeedto.getLast_name());
			emp.setDate_of_birth(employeedto.getDate_of_birth());
			emp.setHire_date(employeedto.getHire_date());
			emp.setDesignation(employeedto.getDesignation());
			emp.setDepartment(employeedto.getDepartment());
			emp.setManager(employeedto.getManager());
			emp.setLocation(employeedto.getLocation());
			emp.setPhoneNumber(employeedto.getPhoneNumber());
			emp.setExperience(employeedto.getExperience());
			emp.setTeam(employeedto.getTeam());
			emp.setEmail(employeedto.getEmail());
			emp.setGender(employeedto.getGender());
			
			employeerepo.save(emp);
			
			List<SkillGetDTO> skills = employeedto.getSkillList();
			for(SkillGetDTO skill:skills) {
				System.err.println("???????????????"+skill.toString());
				SkillTransactionMaster skillmaster = new SkillTransactionMaster();
				skillmaster.setEmployeeId(emp.getEmp_id());
				skillmaster.setSkillId(skill.getSkillId());;
				skillmaster.setProficiencyLevel(skill.getProficiencyLevel());
				skillRepo.save(skillmaster);
			}
//			skillmaster.setSkillId(employeedto.getSkillList());
//			skillmaster.setProficiencyLevel(employeedto.getSkillList());
			return new PmsResponseMessage(201, "Data saved successfully", emp, true);
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
            Map<String, Object> employeeData = empOptional.get(0);

            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmp_id(Integer.parseInt(employeeData.get("emp_id").toString()));
            employeeDTO.setFirst_name(employeeData.get("first_name").toString());
            employeeDTO.setLast_name(employeeData.get("last_name").toString());
            employeeDTO.setDate_of_birth(employeeData.get("date_of_birth").toString());
            employeeDTO.setHire_date(employeeData.get("hire_date").toString());
            employeeDTO.setEmail(employeeData.get("email").toString());
            employeeDTO.setGender(employeeData.get("gender").toString());
            employeeDTO.setPhoneNumber((String) employeeData.get("phoneNumber").toString());
            employeeDTO.setDesignation(employeeData.get("designation").toString());
            employeeDTO.setDepartment(employeeData.get("department").toString());
            employeeDTO.setManager(employeeData.get("manager").toString());
            employeeDTO.setLocation(employeeData.get("location").toString());
            employeeDTO.setTeam(employeeData.get("team").toString());
            employeeDTO.setExperience(employeeData.get("experience_id").toString());

            List<SkillGetDTO> data1 = new ArrayList<SkillGetDTO>();
            for (Map<String, Object> skill : empOptional) {
            	SkillGetDTO data = new SkillGetDTO();
            	    data.setProficiencyLevel(skill.get("proficiencyLevel").toString());
            	    data.setSkillId(Integer.parseInt(skill.get("skillId").toString()));
            	    data.setSkillName(skill.get("skillName").toString());
            	    data.setSkillCategory(skill.get("skillCategory").toString());
            	    data1.add(data);
            }
            employeeDTO.setSkillList(data1);

			return new PmsResponseMessage(200, "success", employeeDTO, true);
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
			return new PmsResponseMessage(1, "Deletion successful", deletedEmployee, true);
		} else {
			return new PmsResponseMessage(-1, "Deletion failed - Employee not found", null, false);
		}
	}
	
	public List<Map<String, Object>> getAllEmpBySkill(int skill){
		return employeerepo.getAllEmpBySkill(skill);
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

//Map<String, Object> one = empOptional.getFirst();
//EmployeeDTO empDto = new EmployeeDTO();
//empDto.setEmp_id(Integer.parseInt(one.get("emp_id").toString()));
//empDto.setFirst_name(one.get("first_name").toString());
//empDto.setLast_name(one.get("last_name").toString());
//empDto.setDate_of_birth(one.get("date_of_birth").toString());
//empDto.setHire_date(one.get("hire_date").toString());
//empDto.setEmail(one.get("email").toString());
//empDto.setGender(one.get("gender").toString());
//empDto.setPhoneNumber(one.get("phone_number").toString());
//empDto.setDesignation(one.get("first_name").toString());
//empDto.setDepartment(one.get("designation_id").toString());
//empDto.setManager(one.get("manager_id").toString());
//empDto.setLocation(one.get("location_id").toString());
//empDto.setTeam(one.get("team_id").toString());
//empDto.setExperience(one.get("exp_id").toString());
