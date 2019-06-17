package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.brijframework.college.commom.util.RandomPasswordUtil;
import org.brijframework.college.dao.AssignClassDao;
import org.brijframework.college.dao.CityDao;
import org.brijframework.college.dao.CountryDao;
import org.brijframework.college.dao.EmployeeAttendanceDao;
import org.brijframework.college.dao.EmployeeDao;
import org.brijframework.college.dao.HolidayDao;
import org.brijframework.college.dao.LoginRoleDao;
import org.brijframework.college.dao.StateDao;
import org.brijframework.college.dao.UserDao;
import org.brijframework.college.dao.UserRoleDao;
import org.brijframework.college.model.AssignClass;
import org.brijframework.college.model.EmployeeAttendance;
import org.brijframework.college.model.Employees;
import org.brijframework.college.model.Holiday;
import org.brijframework.college.model.User;
import org.brijframework.college.model.UserRole;
import org.brijframework.college.model.UserRolePrimaryKey;
import org.brijframework.college.model.util.EmailUtility;
import org.brijframework.college.model.util.PasswordEncoder;
import org.brijframework.college.models.dto.EmployeeSalaryDTO;
import org.brijframework.college.models.dto.EmployeesDTO;
import org.brijframework.college.models.dto.FeecategoryAmountDTO;
import org.brijframework.college.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("employeeService")
public class EmployeesServiceImpl extends
		CRUDServiceImpl<Integer, Employees, EmployeeDao> implements
		EmployeeService {

	@Autowired
	public EmployeesServiceImpl(EmployeeDao dao) {
		super(dao);

	}

	@Autowired
	UserDao userDao;
	@Autowired
	CountryDao countryDao;
	@Autowired
	StateDao stateDao;
	@Autowired
	CityDao cityDao;
	@Autowired
	LoginRoleDao roleDao;
	@Autowired
	UserRoleDao userRoleDao;
	@Autowired
	private EmployeeAttendanceDao employeeAttendanceDao;

	@Autowired
	private HolidayDao holidayDao;
	@Autowired
	AssignClassDao assignClassDao;

	@Transactional
	public int saveEmployeeRegistration(EmployeesDTO employeesDTO)
			throws Exception, MessagingException {
		int size = dao.findAll(Employees.class).size() + 1;
		String name = employeesDTO.getFirstName();

		String userid = name.substring(0, 2) + size;
		String num = RandomPasswordUtil.getRandomString();
		String password = PasswordEncoder.getEcodedPassword(num);
		User user = new User();
		user.setUsername(userid);
		user.setPassword(password);
		user.setEnabled(true);
		userDao.create(user);
		UserRolePrimaryKey key = new UserRolePrimaryKey();
		key.setRole(roleDao.getUserRoleByName("ROLE_EMPLOYEE"));
		key.setUser(userDao.create(user));
		UserRole role = new UserRole();
		role.setUserRolePrimaryKey(key);
		userRoleDao.create(role);

		/************************* New Registration of Student **************************/
		Date date = new Date();
		java.sql.Date date2 = new java.sql.Date(date.getTime());
		Employees employees = new Employees();
		/*
		 * employees.setDepartments(departmentDao.get(employeesDTO
		 * .getDepartmentId()));
		 */
		employees.setActive(true);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
		employees
				.setJoiningDate(dateFormat.parse(employeesDTO.getJoiningDate()));
		employees.setFirstName(employeesDTO.getFirstName());
		employees.setLastName(employeesDTO.getLastName());
		employees.setDob(dateFormat.parse(employeesDTO.getDob()));
		employees.setAddress(employeesDTO.getAddress());
		employees.setBirthPlace(employeesDTO.getBirthPlace());
		employees.setBloodGroup(employeesDTO.getBloodGroup());
		employees.setCountry(countryDao.get(employeesDTO.getCountryId()));
		employees.setCity(cityDao.get(employeesDTO.getCityId()));
		employees.setState(stateDao.get(employeesDTO.getStateId()));
		employees.setCreatedAt(date2);
		employees.setEmail(employeesDTO.getEmail());
		employees.setFatherName(employeesDTO.getFatherName());
		employees.setGender(employeesDTO.getGender());
		employees.setMotherTongue(employeesDTO.getMotherTongue());
		employees.setMobile(employeesDTO.getMobile());
		employees.setMotherName(employeesDTO.getMotherName());
		employees.setOccupation(employeesDTO.getOccupation());
		employees.setPassoutMonth(employeesDTO.getPassoutMonth());
		employees.setPassoutYear(employeesDTO.getPassoutYear());
		employees.setHighestQualification(employeesDTO
				.getHighestQualification());
		employees.setPassword(num);
		employees.setPinCode(employeesDTO.getPinCode());
		employees.setSalary(0);
		employees.setUpdatedAt(date2);
		employees.setUser(user);
		if (employeesDTO.getImamgeName() == null) {
			employees.setImageName("defultimage.png");
		} else if (employeesDTO.getImamgeName().getSize() > 0) {
			employees.setImageName(employeesDTO.getImamgeName()
					.getOriginalFilename());
		} else {
			employees.setImageName("defultimage.png");
		}
		dao.create(employees);
		/*********************************** Email Details ***********************************************/
		String email = employeesDTO.getEmail();
		String message = "Your Username is :-" + userid
				+ "<br> Your Password is :-" + num;

		try {
			EmailUtility.sendEmailWithAttachment(email,
					"Username & Password From School Management", message);
		} catch (AddressException e) {

			e.printStackTrace();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		Employees newemployees = dao.finddetailsbyUserId(user.getId());
		return newemployees.getEmployeeId();
	}

	@Override
	public EmployeesDTO finddetailsbyuserId(Integer id) {
		Employees employees = dao.finddetailsbyUserId(id);
		return converEntityToDTO(employees);

	}

	private EmployeesDTO converEntityToDTO(Employees employees) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		EmployeesDTO dto = new EmployeesDTO();
		dto.setAddress(employees.getAddress());
		dto.setId(employees.getEmployeeId());
		dto.setBirthPlace(employees.getBirthPlace());
		dto.setBloodGroup(employees.getBloodGroup());
		dto.setCity(employees.getCity().getCityName());
		dto.setDob(dateFormat.format(employees.getDob()));
		dto.setEmail(employees.getEmail());
		dto.setFatherName(employees.getFatherName());
		dto.setFirstName(employees.getFirstName());
		dto.setGender(employees.getGender());
		dto.setHighestQualification(employees.getHighestQualification());
		dto.setJoiningDate(dateFormat.format(employees.getJoiningDate()));
		dto.setLastName(employees.getLastName());
		dto.setMobile(employees.getMobile());
		dto.setMotherName(employees.getMotherName());
		dto.setMotherTongue(employees.getMotherTongue());
		dto.setOccupation(employees.getOccupation());
		dto.setPassoutMonth(employees.getPassoutMonth());
		dto.setPassoutYear(employees.getPassoutYear());
		dto.setPinCode(employees.getPinCode());
		dto.setStateName(employees.getState().getStateName());
		dto.setCountryName(employees.getCountry().getCountryName());
		dto.setImagename(employees.getImageName());
		return dto;
	}

	@Override
	public List<EmployeesDTO> getEmployeesbyFirstname(String firstName) {
		List<Employees> list = dao.getEmployee(firstName);

		List<EmployeesDTO> lists = new ArrayList<EmployeesDTO>();

		for (Employees employees : list) {
			EmployeesDTO dto = new EmployeesDTO();
			dto.setFirstName(employees.getFirstName());
			dto.setLastName(employees.getLastName());
			dto.setId(employees.getEmployeeId());
			dto.setUserName(employees.getUser().getUsername());
			dto.setPassword(employees.getPassword());
			lists.add(dto);
		}
		return lists;

	}

	@Override
	public void setActiveById(int id) {
		dao.get(id).setActive(false);

	}

	@Override
	public EmployeesDTO findEmployeeDetails(int id) {

		Employees employees = dao.get(id);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		EmployeesDTO dto = new EmployeesDTO();
		dto.setAddress(employees.getAddress());
		dto.setBirthPlace(employees.getBirthPlace());
		dto.setBloodGroup(employees.getBloodGroup());
		dto.setCityId(employees.getCity().getCityId());
		dto.setCity(employees.getCity().getCityName());
		dto.setId(id);
		dto.setDob(dateFormat.format(employees.getDob()));
		dto.setEmail(employees.getEmail());
		dto.setFatherName(employees.getFatherName());
		dto.setFirstName(employees.getFirstName());
		dto.setGender(employees.getGender());
		dto.setHighestQualification(employees.getHighestQualification());
		dto.setJoiningDate(dateFormat.format(employees.getJoiningDate()));
		dto.setLastName(employees.getLastName());
		dto.setMobile(employees.getMobile());
		dto.setMotherName(employees.getMotherName());
		dto.setMotherTongue(employees.getMotherTongue());
		dto.setOccupation(employees.getOccupation());
		dto.setPassoutMonth(employees.getPassoutMonth());
		dto.setPassoutYear(employees.getPassoutYear());
		dto.setPassword(employees.getPassword());
		dto.setPinCode(employees.getPinCode());
		dto.setStateId(employees.getState().getStateId());
		dto.setStateName(employees.getState().getStateName());
		dto.setCountryId(employees.getCountry().getCountryId());
		dto.setCountryName(employees.getCountry().getCountryName());
		dto.setImagename(employees.getImageName());
		/*
		 * dto.setUserId(employees.getUser().getId());
		 * dto.setUserName(employees.getUser().getUsername());
		 */
		return dto;
	}

	@Override
	public void updateEmployeesdata(EmployeesDTO employeesDTO)
			throws ParseException {
		Date date = new Date();
		java.sql.Date date2 = new java.sql.Date(date.getTime());
		Employees employees = dao.get(employeesDTO.getId());
		employees.setActive(true);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		employees
				.setJoiningDate(dateFormat.parse(employeesDTO.getJoiningDate()));
		employees.setFirstName(employeesDTO.getFirstName());
		employees.setLastName(employeesDTO.getLastName());
		employees.setDob(dateFormat.parse(employeesDTO.getDob()));
		employees.setAddress(employeesDTO.getAddress());
		employees.setBirthPlace(employeesDTO.getBirthPlace());
		employees.setBloodGroup(employeesDTO.getBloodGroup());
		employees.setCountry(countryDao.get(employeesDTO.getCountryId()));
		employees.setCity(cityDao.get(employeesDTO.getCityId()));
		employees.setState(stateDao.get(employeesDTO.getStateId()));

		employees.setEmail(employeesDTO.getEmail());
		employees.setFatherName(employeesDTO.getFatherName());
		employees.setGender(employeesDTO.getGender());
		employees.setMotherTongue(employeesDTO.getMotherTongue());
		employees.setMobile(employeesDTO.getMobile());
		employees.setMotherName(employeesDTO.getMotherName());
		employees.setOccupation(employeesDTO.getOccupation());
		employees.setPassoutMonth(employeesDTO.getPassoutMonth());
		employees.setPassoutYear(employeesDTO.getPassoutYear());
		employees.setHighestQualification(employeesDTO
				.getHighestQualification());
		employees.setPinCode(employeesDTO.getPinCode());
		employees.setUpdatedAt(date2);
		if (employeesDTO.getImamgeName().getSize() > 0) {
			employees.setImageName(employeesDTO.getImamgeName()
					.getOriginalFilename());
		}

	}

	@Override
	public Map<String, Object> getActiveEmployeeList() throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EmployeesDTO> employeesDTOs = new ArrayList<EmployeesDTO>();
		List<Employees> employeeList = dao.getEmployee(true);
		for (Employees employees : employeeList) {
			EmployeesDTO employeesDTO = converEntityToDTO(employees);
			java.sql.Date sqlDate = new java.sql.Date(
					new java.util.Date().getTime());
			EmployeeAttendance employeeAttendance = employeeAttendanceDao
					.getAttendanceReportByIdDate(employees.getEmployeeId(),
							sqlDate);
			if (employeeAttendance == null) {
				employeesDTO.setStatus("");
			} else {
				employeesDTO
						.setStatus(employeeAttendance.getAttendanceStatus());
			}
			employeesDTOs.add(employeesDTO);
		}
		map.put("EmployeesDTOs", employeesDTOs);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int day = calendar.get(calendar.DAY_OF_WEEK);
		if (day == calendar.SUNDAY) {
			map.put("HOLIDAY", "Sunday");
		} else {
			Holiday holiday = holidayDao
					.getDataForCreateHoliday(simpleDateFormat
							.parse(simpleDateFormat.format(new Date())));
			if (holiday != null) {
				map.put("HOLIDAY", holiday.getDescription());
			} else {
				map.put("HOLIDAY", "");
			}
		}
		return map;
	}

	@SuppressWarnings("static-access")
	@Override
	public Map<String, Object> getActiveEmployeesDateWiseAttendance(String date)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		List<EmployeesDTO> employeesDTOs = new ArrayList<EmployeesDTO>();
		List<Employees> employeeList = dao.getEmployee(true);
		for (Employees employees : employeeList) {
			EmployeesDTO employeesDTO = converEntityToDTO(employees);
			java.sql.Date sqlDate = new java.sql.Date(sdf.parse(date).getTime());
			EmployeeAttendance employeeAttendance = employeeAttendanceDao
					.getAttendanceReportByIdDate(employees.getEmployeeId(),
							sqlDate);
			if (employeeAttendance == null) {
				employeesDTO.setStatus("");
			} else {
				employeesDTO
						.setStatus(employeeAttendance.getAttendanceStatus());
			}
			employeesDTOs.add(employeesDTO);
		}
		map.put("EmployeesDTOs", employeesDTOs);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(date));
		int day = calendar.get(calendar.DAY_OF_WEEK);
		if (day == calendar.SUNDAY) {
			map.put("HOLIDAY", "Sunday");
		} else {
			Holiday holiday = holidayDao.getDataForCreateHoliday(sdf
					.parse(date));
			if (holiday != null) {
				map.put("HOLIDAY", holiday.getDescription());
			} else {
				map.put("HOLIDAY", "");
			}
		}
		return map;
	}

	@Override
	public String changeemployeepassword(
			FeecategoryAmountDTO feecategoryAmountDTO) {
		String result = "";
		Employees employees = dao.get(feecategoryAmountDTO.getEmployeeId());
		FeecategoryAmountDTO amountDTO = new FeecategoryAmountDTO();
		amountDTO.setEmployeeId(feecategoryAmountDTO.getEmployeeId());
		if (!feecategoryAmountDTO.getNewPassword().equals(
				feecategoryAmountDTO.getConfirmPassword())) {
			result = "nomatch";
		} else if (!employees.getPassword().equals(
				feecategoryAmountDTO.getPassword())) {
			result = "wrong";
		} else {
			User user = employees.getUser();
			String encodedPassword = PasswordEncoder
					.getEcodedPassword(feecategoryAmountDTO.getNewPassword());
			user.setPassword(encodedPassword);
			userDao.update(user);
			employees.setPassword(feecategoryAmountDTO.getNewPassword());
			dao.update(employees);
			result = "done";

		}
		return result;
	}

	@Override
	public List<EmployeesDTO> findAllEmployee() {
		List<Employees> list = dao.getEmployee(true);
		List<EmployeesDTO> lists = new ArrayList<EmployeesDTO>();

		for (Employees employees : list) {
			EmployeesDTO dto = new EmployeesDTO();
			dto.setFirstName(employees.getFirstName());
			dto.setLastName(employees.getLastName());
			dto.setFullName(employees.getFirstName()+" "+employees.getLastName());
			dto.setId(employees.getEmployeeId());
			dto.setUserName(employees.getUser().getUsername());
			dto.setPassword(employees.getPassword());
			lists.add(dto);
		}
		return lists;
	}

	@Override
	public List<EmployeesDTO> findAllEmployeeDetails() {
		List<Employees> list = dao.getEmployee(true);
		List<EmployeesDTO> lists = new ArrayList<EmployeesDTO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Employees employees : list) {
			EmployeesDTO dto = new EmployeesDTO();
			dto.setFirstName(employees.getFirstName());
			dto.setLastName(employees.getLastName());
			dto.setId(employees.getEmployeeId());
			dto.setJoiningDate(sdf.format(employees.getJoiningDate()));
			AssignClass assign = assignClassDao
					.getAssignClassAndSection(employees.getEmployeeId());
			if (assign == null) {
				dto.setClassName("-");
				dto.setSectionName("-");
			} else {
				dto.setClassName(assign.getStudentClasses().getClassName());
				dto.setSectionName(assign.getSection().getSectionName());
			}
			dto.setHighestQualification(employees.getHighestQualification());
			dto.setId(employees.getEmployeeId());
			dto.setSalary(employees.getSalary());
			lists.add(dto);
		}
		return lists;
	}

	@Override
	public void submitsalary(EmployeesDTO employeeDTO) {
		for (EmployeesDTO dto : employeeDTO.getEmployeeslist()) {
			Employees employee = dao.get(dto.getId());
			employee.setSalary(dto.getSalary());
			
		}

	}

	
}
