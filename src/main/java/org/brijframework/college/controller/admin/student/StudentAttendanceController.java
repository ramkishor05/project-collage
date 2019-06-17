package org.brijframework.college.controller.admin.student;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.common.constant.CommonConstants;
import org.brijframework.college.models.dto.StudentClassesDTO;
import org.brijframework.college.service.AssignClassService;
import org.brijframework.college.service.MonthService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentAttendanceService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class StudentAttendanceController {
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

	@RequestMapping(value = "student-attendance-menu", method = RequestMethod.GET)
	public String attendanceMenuPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "studentattendancemenu";
	}

	@RequestMapping(value = "student-attendance-register", method = RequestMethod.GET)
	public String attendanceRegisterPage(ModelMap model,
			HttpServletRequest request) {
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
		model.addAttribute("noww", new Date());
		System.out.println(new Date());
		session.setAttribute("active", "students");
		return "studentattendanceregister";
	}

	@RequestMapping(value = "student-attendance-report", method = RequestMethod.GET)
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
	}

	@RequestMapping(value = "students-attendance-report", method = RequestMethod.POST)
	public @ResponseBody
	String getStudentAttendance(@RequestParam int classId,
			@RequestParam int sectionId, @RequestParam int monthId,
			HttpServletRequest request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(studentAttendanceService
				.getStudentsAttendanceList(classId, sectionId, monthId));
	}

}
