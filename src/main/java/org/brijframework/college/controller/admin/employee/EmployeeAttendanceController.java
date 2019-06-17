package org.brijframework.college.controller.admin.employee;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.Month;
import org.brijframework.college.service.EmployeeAttendanceService;
import org.brijframework.college.service.EmployeeService;
import org.brijframework.college.service.MonthService;
import org.brijframework.college.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class EmployeeAttendanceController {
	@Autowired
	EmployeeAttendanceService employeeAttendanceService;
	@Autowired
	MonthService monthService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private SessionService sessionService;

	@RequestMapping(value = "employee-attendance-menu", method = RequestMethod.GET)
	public String employeeAttendanceMenuPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		return "employeeattendancemenu";
	}

	@RequestMapping(value = "employee-attendance-register", method = RequestMethod.GET)
	public String employeeAttendanceRegisterPage(ModelMap model,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		model.addAttribute("MAP", employeeService.getActiveEmployeeList());
		return "employeeattendanceregister";
	}

	@RequestMapping(value = "employee-attendance-report", method = RequestMethod.GET)
	public String employeeAttendanceReportPage(ModelMap model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		return "employeeattendancereport";
	}

	@RequestMapping(value = "view-employee-report-as-pdf", method = RequestMethod.GET)
	public String overallViewaspdf(Model model,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("id") int id)
			throws ParseException {
		model.addAttribute("list", employeeAttendanceService
				.showEmployeeAttendanceRecordAsPdf(fromDate, toDate, id));
		return "pdfemployeereport";
	}

	@RequestMapping(value = "perticular-emp-attendance.html", method = RequestMethod.GET)
	public String employeeAttendencePage(@RequestParam int id,
			@RequestParam(required = false, defaultValue = "14") int monthId,
			@RequestParam(required = false, defaultValue = "0") int year,
			Model model) throws Exception {
		model.addAttribute("EMPLOYEESDTO",
				employeeService.findEmployeeDetails(id));
		model.addAttribute("MAP", employeeAttendanceService
				.getAttendaceDataForEmp(id, monthId, year));
		model.addAttribute("Sessions", sessionService.findAllActiveSession());
		model.addAttribute("Months", monthService.findAll(Month.class));
		model.addAttribute("CurrentSession", sessionService.findCurrent());
		model.addAttribute("EMPID", id);
		return "perticularempattendance";
	}

}
