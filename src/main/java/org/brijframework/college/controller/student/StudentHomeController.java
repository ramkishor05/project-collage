package org.brijframework.college.controller.student;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.common.constant.SessionConstants;
import org.brijframework.college.model.User;
import org.brijframework.college.models.dto.MonthDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.service.AnnualExamReportService;
import org.brijframework.college.service.MonthService;
import org.brijframework.college.service.MonthlyExamReportService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/student/**")
public class StudentHomeController {
	@Autowired
	StudentsAdmissionService admissionService;
	@Autowired
	MonthService monthService;
	@Autowired
	private MonthlyExamReportService monthlyExamReportService;
	@Autowired
	private AnnualExamReportService annualExamReportService;

	@RequestMapping(value = "/student-profile", method = RequestMethod.GET)
	public String showStudentDetails(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER_INFO);
		StudentDTO studentDTO = admissionService
				.getStudentProfile(user.getId());
		model.addAttribute("StudentDetailsBean", studentDTO);
		return "showstudentdetails";
	}

	@RequestMapping(value = "/attendancestu-report.html", method = RequestMethod.GET)
	public String showAttendanceReport(Model model) {
		List<MonthDTO> monthList = monthService.getMonthInOrder();
		model.addAttribute("monthList", monthList);
		return "attendancereport";
	}
	@RequestMapping(value = "my-monthly-exam-report.html", method = RequestMethod.GET)
	public String viewMonthlyExamReport(ModelMap model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER_INFO);
		StudentDTO studentDTO = admissionService
				.getStudentProfile(user.getId());
		Map<String, Object> map=monthlyExamReportService.getMonthlyExamReport(studentDTO.getSessionId(), studentDTO.getClassId(), studentDTO.getSectionId(),
				studentDTO.getAdmissionNo());
		model.addAttribute("map", map);
		return "mymonthlyexamreport";
	}
	@RequestMapping(value = "view-my-report-cards.html", method = RequestMethod.GET)
	public String getStudentReportCardPage(ModelMap model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER_INFO);
		StudentDTO studentDTO = admissionService
				.getStudentProfile(user.getId());
		Map<String, Object> map=annualExamReportService.getDataForGenerateReportCard(studentDTO.getSessionId(), studentDTO.getClassId(), studentDTO.getSectionId(),
				studentDTO.getAdmissionNo());
		model.addAttribute("map", map);
		return "myreportcard";
	}
}
