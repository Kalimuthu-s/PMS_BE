package com.hyundai.pms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.Consolidate;
import com.hyundai.pms.entity.DepartmentMaster;
import com.hyundai.pms.entity.DesignationMaster;
import com.hyundai.pms.entity.EmployeeDTO;
import com.hyundai.pms.entity.EmployeeMaster;
import com.hyundai.pms.entity.ExperienceMaster;
import com.hyundai.pms.entity.LocationMaster;
import com.hyundai.pms.entity.PmsResponseMessage;
import com.hyundai.pms.entity.SkillGetDTO;
import com.hyundai.pms.entity.SkillTransactionMaster;
import com.hyundai.pms.entity.TeamMaster;
import com.hyundai.pms.entity.UserMaster;
import com.hyundai.pms.repository.DepartmentRepository;
import com.hyundai.pms.repository.DesignationRepository;
import com.hyundai.pms.repository.EmployeeOverallUtilizationRepository;
import com.hyundai.pms.repository.EmployeeRepository;
import com.hyundai.pms.repository.ExperienceRepository;
import com.hyundai.pms.repository.LocationRepository;
import com.hyundai.pms.repository.SkillTransactionRepository;
import com.hyundai.pms.repository.TeamRepository;
import com.hyundai.pms.repository.UserRepository;
import com.zaxxer.hikari.HikariDataSource;

@Service
public class EmployeeService {
	
	private HikariDataSource hikariDataSource;
	
	@Autowired
	private DepartmentRepository departmentrepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EmployeeOverallUtilizationRepository employeeOverallUtilizationRepo;

//	public Employeemasterservice(HikariDataSource hikariDataSource) {
//		this.hikariDataSource = hikariDataSource;
//	}


	public PmsResponseMessage getAll() {
		try {
			List<Map<String, Object>> emps = employeerepo.findAllEmployees();
			return new PmsResponseMessage(1, "Success", emps, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(-1, "Internal Server Error", null, false);
		}
	}
	
	public PmsResponseMessage getallemployee() {
		try {
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
	
	

//	public PmsResponseMessage create(EmployeeMaster employeemaster) {
//		try {
//			employeerepo.save(employeemaster);
//			UserMaster user = new UserMaster();
//			user.setUsername(employeemaster.getFirst_name());
//			user.setPassword("Hyundai@123");
//			user.setRoleId(employeemaster.getRoleId());
//			user.setEmail(employeemaster.getEmail());
//			user.setStatus(true);
//			userRepo.save(user);
//			this.emailUserDetails(user);
//			return new PmsResponseMessage(201, "Data saved successfully", employeemaster, true);
//		} catch (Exception e) {
//			return new PmsResponseMessage(-2, "Internal Server Error", null, false);
//		}
//	}
//	
	public void emailUserDetails(UserMaster userMaster) throws AddressException, MessagingException {
		System.err.println("==========================> after email call"+userMaster.toString());
			    MimeMessage message = mailSender.createMimeMessage();
			    message.setFrom(new InternetAddress("praveenkannan849@gmail.com"));
			    message.setRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse("sathish.datchanamoorthi@hyundai-autoever.com"));
			    message.setSubject("Test email from PMS");

			    String htmlContent = "<h1>UserNmae : "+userMaster.getUserId()
			    		+ "Password : </h1>" + userMaster.getPassword() +
			            "<p>Please change the default password.</p>";
			    message.setContent(htmlContent, "text/html; charset=utf-8");

			    mailSender.send(message);
		}
		
	
	public PmsResponseMessage saveData(EmployeeDTO employeedto) {
		try {
			EmployeeMaster empl = employeerepo.checkExistingEmail(employeedto.getEmail());
			if(empl != null && employeedto.getEmail().equals(empl.getEmail())) {
				return new PmsResponseMessage(202, "Email Already exists", null, false);
			}
			else if(empl != null && employeedto.getPhoneNumber().equals(empl.getPhoneNumber())) {
				return new PmsResponseMessage(203, "Phone Number Already exists", null, false);
			}
			else {
			 this.saveEmployeeRecord(employeedto);
			  return new PmsResponseMessage(201,"Employee Added Successfully",employeedto,true);
			}
		} catch (Exception e) {
			return new PmsResponseMessage(-2, "Internal Server Error", null, false);
		}
	}
	
	

	private PmsResponseMessage saveEmployeeRecord(EmployeeDTO employeedto) {
		EmployeeMaster emp = new EmployeeMaster();
		emp.setEmp_id(employeedto.getEmp_id());
		emp.setFirst_name(employeedto.getFirst_name());
		emp.setLast_name(employeedto.getLast_name());
		emp.setDate_of_birth(employeedto.getDate_of_birth());
		emp.setHire_date(employeedto.getHire_date());
		emp.setDesignation(employeedto.getDesignation());
		emp.setDepartment(employeedto.getDepartment());
		emp.setManager(employeedto.getManager());
		emp.setLocation(employeedto.getLocation());
		emp.setPhoneNumber(employeedto.getPhoneNumber());
		emp.setExperience(employeedto.getExperienceId());
		emp.setTeam(employeedto.getTeam());
		emp.setEmail(employeedto.getEmail());
		emp.setGender(employeedto.getGender());
		emp.setRoleId(employeedto.getRoleId());
		
		employeerepo.save(emp);
		UserMaster user = new UserMaster();
		user.setUsername(employeedto.getFirst_name());
		user.setPassword(passwordEncoder.encode("Hyundai@123"));
		user.setRoleId(employeedto.getRoleId());
		user.setEmail(employeedto.getEmail());
		user.setStatus(true);
		userRepo.save(user);
		
		this.saveSkillData(employeedto);
		if(employeedto.getEmp_id() > 0) {
			return new PmsResponseMessage(201, "Data updated successfully", emp, true);
		}
		return new PmsResponseMessage(201, "Data saved successfully", emp, true);
	}

	

	public PmsResponseMessage getAllManagers() {
		try {
			List<Map<String, Object>> managerData = employeerepo.findManagers("Manager");
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

	public PmsResponseMessage getById(Long emp_id) {
		System.err.println("lllllllllllll"+emp_id);
		List<Map<String, Object>> empOptional = employeerepo.findByEmployeeId(emp_id);
		if (!empOptional.isEmpty()) {
            Map<String, Object> employeeData = empOptional.get(0);

            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmp_id(Long.parseLong(employeeData.get("emp_id").toString()));
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
            employeeDTO.setExperienceId(employeeData.get("experienceId").toString());
            employeeDTO.setExperienceLevel(employeeData.get("experienceLevel").toString());
            employeeDTO.setRoleId(employeeData.get("roleId").toString());

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

	public PmsResponseMessage updateEmp(EmployeeDTO employeedto) {
		try {
			Long empId = employeedto.getEmp_id();
			if (empId > 0) {
				EmployeeMaster emp = new EmployeeMaster();
				emp.setEmp_id(employeedto.getEmp_id());
				emp.setFirst_name(employeedto.getFirst_name());
				emp.setLast_name(employeedto.getLast_name());
				emp.setDate_of_birth(employeedto.getDate_of_birth());
				emp.setHire_date(employeedto.getHire_date());
				emp.setDesignation(employeedto.getDesignation());
				emp.setDepartment(employeedto.getDepartment());
				emp.setManager(employeedto.getManager());
				emp.setLocation(employeedto.getLocation());
				emp.setPhoneNumber(employeedto.getPhoneNumber());
				emp.setExperience(employeedto.getExperienceId());
				emp.setTeam(employeedto.getTeam());
				emp.setEmail(employeedto.getEmail());
				emp.setGender(employeedto.getGender());
				emp.setRoleId(employeedto.getRoleId());
				employeerepo.save(emp);
				
				this.saveSkillData(employeedto);
			}
			return new PmsResponseMessage(1, "Employee Updated", employeedto, false);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(-2, "Internal Server Error", null, false);
		}
	}
	
	private void saveSkillData(EmployeeDTO employeedto) {
		System.err.println(">>>>>>>>>>>>>>>>>>>"+employeedto.getEmp_id());
		List<SkillGetDTO> skills = employeedto.getSkillList();
		if(employeedto.getEmp_id() > 0) {
			skillRepo.deleteByEmployeeId(employeedto.getEmp_id());
		}
		for(SkillGetDTO skill:skills) {
			SkillTransactionMaster skillmaster = new SkillTransactionMaster();
			skillmaster.setEmployeeId(employeedto.getEmp_id());
			skillmaster.setSkillId(skill.getSkillId());
			skillmaster.setProficiencyLevel(skill.getProficiencyLevel());
			skillRepo.save(skillmaster);
	}
	
}

	public PmsResponseMessage deleteAndReturn(Long emp_id) {
		Optional<EmployeeMaster> deletedEmployee = employeerepo.findById(emp_id);

		if (deletedEmployee.isPresent()) {
			employeerepo.deleteById(emp_id);
			return new PmsResponseMessage(1, "Deletion successful", deletedEmployee, true);
		} else {
			return new PmsResponseMessage(-1, "Deletion failed - Employee not found", null, false);
		}
	}
	
	public PmsResponseMessage getOverallConsolidatedUsingDate(Consolidate consolidate) {
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

			return new PmsResponseMessage(1, "Consolidated Data Getting Successfully", result, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(2, "Error Getting Employee", null, false);
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

	public PmsResponseMessage getOverAllConsolidatedData() {
		try {
			List<Map<String, Object>> getallconsolidateddata = employeeOverallUtilizationRepo
					.getAllEmployeeOverallUtilizationDetails();
			return new PmsResponseMessage(1, "Consolidated Data Getting Successfully", getallconsolidateddata, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(2, "Error Fetching Overall Consolidated Data", null, false);
		}
	}

	public PmsResponseMessage getByProjectsData(Consolidate consolidate) {
		try {
			List<Map<String, Object>> getdatasbyProject = employeeOverallUtilizationRepo.getdatabyProjects(
					consolidate.getProjectid(), consolidate.getStartDate(), consolidate.getEndDate());
			return new PmsResponseMessage(1, "Consolidated Data Getting Successfully", getdatasbyProject, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new PmsResponseMessage(2, "Error getting the data", null, false);
		}
	}
	
	public List<Map<String, Object>> getAllEmpBySkill(int skill){
		return employeerepo.getAllEmpBySkill(skill);
	}
	
	public List<Map<String, Object>> getEmployeeByName(String name){
		return employeerepo.getEmployeeByName(name);
	}
	
	public List<Map<String, Object>> getEmployeeByFullName(String name) {
		String[] parts = name.split(" ");
		String firstName = parts[0];
		String lastName = parts[1];
		return employeerepo.getEmployeeByFullName(firstName);
	}
	public static Date dateConversion(String date) {
		try {
			// Parse the string into a java.util.Date
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate = dateFormat.parse(date);

			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			return sqlDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
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
