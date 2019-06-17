package org.brijframework.college.controller.examreport;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.common.constant.CommonConstants;
import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.MonthlyExamReportDTO;
import org.brijframework.college.models.dto.StudentClassesDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.service.AssignClassService;
import org.brijframework.college.service.MonthService;
import org.brijframework.college.service.MonthlyExamReportService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class UnitTestExamReportController {
	@Autowired
	private SessionService sessionService;
	@Autowired
	private StudentClassesService studentClassesService;
	@Autowired
	private MonthService monthService;
	@Autowired
	private MonthlyExamReportService monthlyExamReportService;
	@Autowired
	private StudentsAdmissionService studentsAdmissionService;
	@Autowired
	private AssignClassService assignClassService;

	@RequestMapping(value = "exam-report-menu.html", method = RequestMethod.GET)
	public String examReportMenu(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "examreportmenupage";
	}

	@RequestMapping(value = "monthly-exam-menu.html", method = RequestMethod.GET)
	public String monthlyExamReportMenu(ModelMap model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "monthlyexammenupage";
	}

	@RequestMapping(value = "assign-marks-to-student.html", method = RequestMethod.GET)
	public String assignMonthlyExamMarks(ModelMap model,
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
					studentClassesService.getAllClass());
		}
		model.addAttribute("CURRENTSESSIONID", sessionService.findCurrent()
				.getSessionId());
		model.addAttribute("monthList",
				monthService.getMonthInOrderToSerialNo());
		model.addAttribute("MonthlyExamReportDTO", new MonthlyExamReportDTO());

		session.setAttribute("active", "students");
		return "assignmonthlymarkspage";
	}

	@RequestMapping(value = "assign-marks-to-student.html", method = RequestMethod.POST)
	public String saveMonthlyExamMarks(
			@ModelAttribute MonthlyExamReportDTO monthlyExamReportDTO,
			ModelMap model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		monthlyExamReportService
				.saveMonthlyExamReportData(monthlyExamReportDTO);
		/*model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("classesList", studentClassesService.getAllClass());
		model.addAttribute("monthList",
				monthService.getMonthInOrderToSerialNo());
		model.addAttribute("MonthlyExamReportDTO", new MonthlyExamReportDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");*/
		redirectAttributes.addFlashAttribute("msg", "Subject Marks Assigned To Student Successfully");
		return "redirect:assign-marks-to-student.html";
	}

	@RequestMapping(value = "generatemonthlyexamreprot.html", method = RequestMethod.GET)
	public String generateMonthlyExamReport(@RequestParam int sessionId,
			@RequestParam int classId, @RequestParam int sectionId,
			@RequestParam String studentId, ModelMap model,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(monthlyExamReportService.getMonthlyExamReport(sessionId,
				classId, sectionId, studentId));
		map.put("StudentDTO", studentsAdmissionService
				.getStudentProfile(Integer.parseInt(studentId)));
		model.addAttribute("MAP", map);
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "generatemonthlyexamreprot";
	}

	@RequestMapping(value = "viewmonthlysubjectmarks.html", method = RequestMethod.GET)
	public String viewMonthlySubjectMarks(ModelMap model,
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
					studentClassesService.getAllClass());
		}
		model.addAttribute("CURRENTSESSIONID", sessionService.findCurrent()
				.getSessionId());
		model.addAttribute("monthList",
				monthService.getMonthInOrderToSerialNo());
		model.addAttribute("MonthlyExamReportDTO", new MonthlyExamReportDTO());

		session.setAttribute("active", "students");
		return "viewmonthlysubjectmarks";
	}

	@RequestMapping(value = "edit-monthlygivenmarks", method = RequestMethod.GET)
	public String editMonthlySubjectMarks(ModelMap model,
			@RequestParam int monthlyExamReportId, HttpServletRequest request) {
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
					studentClassesService.getAllClass());
		}
		model.addAttribute("CURRENTSESSIONID", sessionService.findCurrent()
				.getSessionId());
		model.addAttribute("monthList",
				monthService.getMonthInOrderToSerialNo());
		model.addAttribute("MonthlyExamReportDTO", monthlyExamReportService
				.getMonthlyExamReport(monthlyExamReportId));

		session.setAttribute("active", "students");
		return "editmonthlyexamsubjectmarks";
	}

	@RequestMapping(value = "delete-monthlygivenmarks", method = RequestMethod.GET)
	public String deleteMonthlySubjectMarks(ModelMap model,
			@RequestParam int monthlyExamReportId, HttpServletRequest request) {
		monthlyExamReportService.deleteById(monthlyExamReportId);
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("classesList", studentClassesService.getAllClass());
		model.addAttribute("monthList",
				monthService.getMonthInOrderToSerialNo());
		model.addAttribute("MonthlyExamReportDTO", new MonthlyExamReportDTO());
		model.addAttribute("msg", "Delete Successfully");
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "viewmonthlysubjectmarks";
	}

	@RequestMapping(value = "update-monthlygivenmarks.html", method = RequestMethod.GET)
	public String updateMonthlySubjectMark(ModelMap model,
			@ModelAttribute MonthlyExamReportDTO monthlyExamReportDTO,
			HttpServletRequest request) {
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("classesList", studentClassesService.getAllClass());
		model.addAttribute("monthList",
				monthService.getMonthInOrderToSerialNo());
		model.addAttribute("MonthlyExamReportDTO", new MonthlyExamReportDTO());
		model.addAttribute("msg", "Update Successfully");
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "viewmonthlysubjectmarks";
	}

	@RequestMapping(value = "update-monthlygivenmarks.html", method = RequestMethod.POST)
	public String updateMonthlySubjectMarks(ModelMap model,
			@ModelAttribute MonthlyExamReportDTO monthlyExamReportDTO,
			HttpServletRequest request) {
		monthlyExamReportService
				.updateMonthlyExamReportData(monthlyExamReportDTO);
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("classesList", studentClassesService.getAllClass());
		model.addAttribute("monthList",
				monthService.getMonthInOrderToSerialNo());
		model.addAttribute("MonthlyExamReportDTO", new MonthlyExamReportDTO());
		model.addAttribute("msg", "Update Successfully");
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "viewmonthlysubjectmarks";
	}

	@RequestMapping(value = "view-monthly-exam-report.html", method = RequestMethod.GET)
	public String viewMonthlyExamReport(ModelMap model,
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
					studentClassesService.getAllClass());
		}
		model.addAttribute("CURRENTSESSIONID", sessionService.findCurrent()
				.getSessionId());

		session.setAttribute("active", "students");
		return "viewmonthlyexamreport";
	}
	@RequestMapping(value = "pdf-viewmonthlysubjectmarks", method = RequestMethod.GET)
	public String viewpdfreports(ModelMap model,HttpServletRequest request,@RequestParam String id) {
	    StudentDTO studentDTO=studentsAdmissionService.findStudentDetails(id);
		Map<String, Object> map=monthlyExamReportService.getMonthlyExamReport(studentDTO.getSessionId(), studentDTO.getClassId(), studentDTO.getSectionId(),
				studentDTO.getAdmissionNo());
		model.addAttribute("map", map);
		model.addAttribute("student", studentDTO);
		return "pdfmonthlyexamreport";
	}

}
