package org.brijframework.college.controller.employee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.models.dto.MonthDTO;
import org.brijframework.college.service.AssignClassService;
import org.brijframework.college.service.MonthService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentAttendanceService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class EmployeeStudentAttendanceController {
	@Autowired
	StudentClassesService classesService;
	@Autowired
	StudentsAdmissionService admissionService;
	@Autowired
	MonthService monthService;
	@Autowired
	StudentAttendanceService studentAttendanceService;
	@Autowired
	private AssignClassService assignClassService;
	@Autowired
	SessionService sessionService;

	@RequestMapping(value = "employee-my-attendace-report", method = RequestMethod.GET)
	public String employeeattendancereport(Model model,HttpServletRequest request) {
		List<MonthDTO> monthList = monthService.getMonthInOrder();
		model.addAttribute("monthList", monthList);
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		return "employeemyattendancereport";
	}

/*	@RequestMapping(value = "student-attendance-menu", method = RequestMethod.GET)
	public String attendanceMenuPage(HttpServletRequest request) {
		return "studentattendancemenu";
	}*/

	/*@RequestMapping(value = "student-attendance-register", method = RequestMethod.GET)
	public String attendanceRegisterPage(ModelMap model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String ROLE = (String) session.getAttribute(CommonConstants.ROLE);
		Integer employeeId = (Integer) session
				.getAttribute(CommonConstants.EMPLOYER_ID);
		StudentClassesDTO studentClassesDTO = assignClassService
				.getAssignClassAndSection(employeeId);
		model.addAttribute("StudentClassesDTO", studentClassesDTO);
		model.addAttribute("CLASSID", studentClassesDTO.getClassId());
		model.addAttribute("SECTIONID", studentClassesDTO.getSectionId());
		model.addAttribute("ROLE", ROLE);
		return "studentattendanceregister";
	}
*/
	/*@RequestMapping(value = "/student-attendance-report", method = RequestMethod.GET)
	public String showreport(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		String ROLE = (String) session.getAttribute(CommonConstants.ROLE);

		if (ROLE.equals("ROLE_EMPLOYEE")) {
			Integer employeeId = (Integer) session
					.getAttribute(CommonConstants.EMPLOYER_ID);
			StudentClassesDTO studentClassesDTO = assignClassService
					.getAssignClassAndSection(employeeId);
			model.addAttribute("StudentClassesDTO", studentClassesDTO);
			model.addAttribute("CLASSID", studentClassesDTO.getClassId());
			model.addAttribute("SECTIONID", studentClassesDTO.getSectionId());
		} else {
			model.addAttribute("classesList",
					classesService.getAllClass());
		}
		model.addAttribute("CURRENTSESSIONID", sessionService.findCurrent()
				.getSessionId());
		model.addAttribute("ROLE", ROLE);
		model.addAttribute("monthList", monthService.getMonthInOrder());
		
		session.setAttribute("active", "students");
		return "studentsattendancereport";
	}*/

	/*@RequestMapping(value = "students-attendance-report", method = RequestMethod.POST)
	public @ResponseBody
	String getStudentAttendance(@RequestParam int classId,
			@RequestParam int sectionId, @RequestParam int monthId,
			HttpServletRequest request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(studentAttendanceService
				.getStudentsAttendanceList(classId, sectionId, monthId));
	}
*/
}
