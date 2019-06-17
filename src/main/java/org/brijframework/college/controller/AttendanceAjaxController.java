package org.brijframework.college.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.common.constant.SessionConstants;
import org.brijframework.college.model.User;
import org.brijframework.college.models.dto.EmployeesDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.service.AssignClassService;
import org.brijframework.college.service.EmployeeAttendanceService;
import org.brijframework.college.service.EmployeeService;
import org.brijframework.college.service.StudentAttendanceService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**", "/student/**","/branch/**" })
public class AttendanceAjaxController {

	@Autowired
	private StudentAttendanceService studentAttendenceService;
	@Autowired
	private StudentsAdmissionService studentsAdmissionService;
	@Autowired
	StudentAttendanceService studentAttendanceService;
	@Autowired
	EmployeeAttendanceService employeeAttendanceService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	AssignClassService assignClassService;

	@RequestMapping(value = "/attendance-register/{id1}/{id2}", method = RequestMethod.POST)
	public @ResponseBody String getAttendanceRegister(
			@PathVariable("id1") int classId,
			@PathVariable("id2") int sectionId, HttpServletRequest request)
			throws Exception 
	         {
		    ObjectMapper mapper = new ObjectMapper();
		     return mapper.writeValueAsString(studentAttendanceService
				.getWeeklyAttendanceRegister(classId, sectionId));
	}

	@RequestMapping(value = "show-student-attendance-list", method = RequestMethod.POST)
	public @ResponseBody String getStudentAttendance(@RequestParam int monthId,
			HttpServletRequest request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER_INFO);
		StudentDTO dto = studentsAdmissionService.getStudentProfile(user
				.getId());
		return mapper.writeValueAsString(studentAttendanceService
				.getStudentAttendance(dto.getAdmissionNo(), monthId));
	}

	@RequestMapping(value = "setattendencerecord", method = RequestMethod.POST)
	public @ResponseBody String setAttendenceRecord(
			@RequestParam("id") String id,
			@RequestParam("title") String status,
			@RequestParam("classId") int classId,
			@RequestParam("sectionId") int sectionId,
			@RequestParam("cd") String currentDate, ModelMap model,
			HttpServletRequest request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session = request.getSession();
		User employee = (User) session.getAttribute(SessionConstants.USER_INFO);
		studentAttendenceService.setAttendanceRecord(id, status, classId,
				sectionId, employee.getId(), currentDate);
		return mapper.writeValueAsString(studentAttendanceService
				.getWeeklyAttendanceRegister(classId, sectionId));
	}

	@RequestMapping(value = "get-employees-list", method = RequestMethod.POST)
	public @ResponseBody String getEmployeeList(ModelMap model,
			HttpServletRequest request) throws JsonProcessingException,
			ParseException {
		List<EmployeesDTO> employeesDTOs = employeeAttendanceService
				.getEmployeeRegister();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(employeesDTOs);
	}

	@RequestMapping(value = "setemployee-attendance-record", method = RequestMethod.POST)
	public @ResponseBody String setAttendenceRecord(@RequestParam("id") int id,
			@RequestParam("title") String status,
			@RequestParam("cd") String currentDate, ModelMap model,
			HttpServletRequest request) throws JsonProcessingException,
			ParseException {
		employeeAttendanceService
				.setEmployeeAttendance(id, status, currentDate);
		return null;
	}

	@RequestMapping(value = "getcurrentdatedata", method = RequestMethod.GET)
	public @ResponseBody String getCurrentDateData(
			@RequestParam String currentDate, ModelMap model,
			HttpServletRequest request) throws JsonProcessingException,
			ParseException {
		return new ObjectMapper().writeValueAsString(employeeAttendanceService
				.getCurrentDateData(currentDate));
	}

	@RequestMapping(value = "getstudentdatewisedata", method = RequestMethod.GET)
	public @ResponseBody String getStudentDateWiseData(
			@RequestParam int classId, @RequestParam int sectionId,
			@RequestParam String currentDate, ModelMap model,
			HttpServletRequest request) throws JsonProcessingException,
			ParseException {
		return new ObjectMapper().writeValueAsString(studentAttendanceService
				.studentsDateWiseAttendanceList(classId, sectionId,
						currentDate));
	}

	@RequestMapping(value = "getstudentcurrentdatedata", method = RequestMethod.GET)
	public @ResponseBody String getStudentCurrentDateData(
			@RequestParam int classId, @RequestParam int sectionId,
			@RequestParam String currentDate, ModelMap model,
			HttpServletRequest request) throws JsonProcessingException,
			ParseException {
		return new ObjectMapper().writeValueAsString(studentAttendanceService
				.getStudentsCurrentDateAttendanceList(classId, sectionId,
						currentDate));
	}

	@RequestMapping(value = "getemployeeAttendancedatewisedata", method = RequestMethod.GET)
	public @ResponseBody String getDateWiseData(@RequestParam String Date,
			ModelMap model, HttpServletRequest request)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper().writeValueAsString(employeeService
				.getActiveEmployeesDateWiseAttendance(Date));
	}

	@RequestMapping(value = "show-employees-attendance-list", method = RequestMethod.POST)
	public @ResponseBody String showEmployeeList(
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, ModelMap model,
			HttpServletRequest request) throws JsonProcessingException,
			ParseException {
		List<EmployeesDTO> employeeDTOs = employeeAttendanceService
				.showEmployeeAttendanceRecord(fromDate, toDate);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(employeeDTOs);
	}

	@RequestMapping(value = "show-employee-attendance-list", method = RequestMethod.POST)
	public @ResponseBody String getemployeeAttendance(
			@RequestParam int monthId, HttpServletRequest request)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER_INFO);
		EmployeesDTO dto = employeeService.finddetailsbyuserId(user.getId());
		return mapper.writeValueAsString(employeeAttendanceService
				.getMonthlyReport(dto.getId(), monthId));
	}

	@RequestMapping(value = "view-student-report-as-pdf", method = RequestMethod.GET)
	public String overallViewaspdf(Model model, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam int monthId)
			throws ParseException 
	{
		model.addAttribute("list", studentAttendanceService
				.getStudentsAttendanceList(classId, sectionId, monthId));
		return "studentattendancereportaspdf";
	}
	@RequestMapping(value = "view-student-report-as-excel", method = RequestMethod.GET)
	public ModelAndView excel(Model model, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam int monthId,ModelAndView modelAndView)
			throws ParseException
	{
		List<StudentDTO> list=studentAttendanceService
				.getStudentsAttendanceList(classId, sectionId, monthId);
		return new ModelAndView("attendanceStudent", "list", list);
	}

	@RequestMapping(value = "getemployeeattendancesummary", method = RequestMethod.GET)
	public @ResponseBody String getEmployeeAttendanceSummary()
			throws ParseException, JsonProcessingException 
	 {
		    return new ObjectMapper().writeValueAsString(employeeAttendanceService
				.getEmployeeSummuary());
	}

	@RequestMapping(value = "getstudentattendancesummary", method = RequestMethod.GET)
	public @ResponseBody String getStudenntAttendanceSummary(
			@RequestParam int classId, int sectionId) throws ParseException,
			JsonProcessingException {
		    return new ObjectMapper().writeValueAsString(studentAttendanceService
				.getStudentAttendanceSummary(classId, sectionId));
	}
}
