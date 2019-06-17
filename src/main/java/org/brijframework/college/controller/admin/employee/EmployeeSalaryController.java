package org.brijframework.college.controller.admin.employee;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.Employees;
import org.brijframework.college.model.Month;
import org.brijframework.college.models.dto.AdvanceSalaryDTO;
import org.brijframework.college.models.dto.EmployeeSalaryDTO;
import org.brijframework.college.models.dto.EmployeesDTO;
import org.brijframework.college.service.AdvanceSalaryService;
import org.brijframework.college.service.EmployeeSalaryService;
import org.brijframework.college.service.EmployeeService;
import org.brijframework.college.service.MonthService;
import org.brijframework.college.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class EmployeeSalaryController {
	
	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeSalaryService employeeSalaryService;
	@Autowired
	MonthService monthService;
	@Autowired
	AdvanceSalaryService advanceSalaryService;
	@Autowired
	SessionService sessionService;
	
	@RequestMapping(value = "create-salary", method = RequestMethod.GET)
	public String showEmployeedetails(Model model, HttpServletRequest request) {
		model.addAttribute("employeeDTO", new EmployeesDTO());
		List<EmployeesDTO> lists=employeeService.findAllEmployeeDetails();
		model.addAttribute("allemployees",lists);
        model.addAttribute("number",lists.size());
    	model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		return "allotsalary";
	}
	@RequestMapping(value = "save-salary", method = RequestMethod.POST)
	public String registrationSubmit(
			@ModelAttribute("employeeDTO") EmployeesDTO employeeDTO,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws MessagingException, Exception {
		employeeService.submitsalary(employeeDTO);
		model.addAttribute("employeeDTO", new EmployeesDTO());
		List<EmployeesDTO> lists=employeeService.findAllEmployeeDetails();
		model.addAttribute("allemployees",lists);
        model.addAttribute("number",lists.size());
        model.addAttribute("msg", "Salary Updated Successfully");
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		return "allotsalary";
	}
	@RequestMapping(value = "save-salary", method = RequestMethod.GET)
	public String registrationSubmitget(Model model, HttpServletRequest request) {
		model.addAttribute("employeeDTO", new EmployeesDTO());
		List<EmployeesDTO> lists=employeeService.findAllEmployeeDetails();
		model.addAttribute("allemployees",lists);
        model.addAttribute("number",lists.size());
    	model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		return "allotsalary";
	}
	@RequestMapping(value = "/get-employee-salarydetails", method = RequestMethod.POST)
	public @ResponseBody
	String getSectionList(@RequestParam("employeeId") int employeeId,
			HttpServletRequest request) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<EmployeeSalaryDTO> list=employeeSalaryService.findAllSalaryDetailsofEmployee(employeeId);
		return mapper.writeValueAsString(list);
	}
	@RequestMapping(value = "pay-salary", method = RequestMethod.GET)
	public String paysalary(Model model, HttpServletRequest request) {
		model.addAttribute("EmployeeSalaryDTO", new EmployeeSalaryDTO());
		List<EmployeesDTO> lists=employeeService.findAllEmployeeDetails();
		model.addAttribute("allemployees",lists);
        model.addAttribute("number",lists.size());
        model.addAttribute("sessionList", sessionService.findAllprevioussession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		return "paysalary";
	}
	@RequestMapping(value = "get-employee-salary-payment-details", method = RequestMethod.POST)
	public @ResponseBody String getStudentFeePayMentDetails(
			 @RequestParam int employeeId,@RequestParam int sessionId)
			throws JsonProcessingException, ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("employeeDTO",employeeService.findEmployeeDetails(employeeId));
		map.put("EmployeeSalaryDTOs",
				employeeSalaryService.getEmployeePaymentDetail(
						employeeId,sessionId));
		return new ObjectMapper().writeValueAsString(map);
	}
	@RequestMapping(value = "/admin/salary-payment", method = RequestMethod.POST)
	public String paysalary(
			@ModelAttribute("EmployeeSalaryDTO") EmployeeSalaryDTO employeeSalaryDTO,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws MessagingException, Exception {
		EmployeeSalaryDTO dto=employeeSalaryService.paysalary(employeeSalaryDTO);
		model.addAttribute("list",dto);
		return "pdfsalaryslip";
	}
	@RequestMapping(value = "generatePDFslip.html", method = RequestMethod.GET)
	public String getSalarySlipDetails(
			 @RequestParam int slipNo,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws MessagingException, Exception {
		EmployeeSalaryDTO dto=employeeSalaryService.generateSalarySlip(slipNo);
		model.addAttribute("list",dto);
		return "pdfsalaryslip";
	}
	@RequestMapping(value = "inhand-cheque", method = RequestMethod.GET)
	public String getInproressListPage(ModelMap model) {
		model.addAttribute("employeeCheque",
				employeeSalaryService.getInhandist());
		return "salarychequelist";
	}
	@RequestMapping(value = "salary-menu", method = RequestMethod.GET)
	public String showSetting(Model model) {
		return "salarymenu";
	}
	@RequestMapping(value = "change-cheque-inHand.html", method = RequestMethod.GET)
	public String getInproressListPage(@RequestParam int slipNo,
			@RequestParam String Status, ModelMap model) {
		employeeSalaryService.updateStatusById(Status, slipNo);
		model.addAttribute("employeeCheque",
				employeeSalaryService.getInhandist());
		return "salarychequelist";
	}
	@RequestMapping(value = "view-monthwise-paidsalary", method = RequestMethod.GET)
	public String salarydetails(Model model, HttpServletRequest request) {
	List<Month> lists=monthService.findAllByIsDeletedTrue(Month.class);
        model.addAttribute("months",lists);
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		return "viewmonthlypaidsalary";
	}
	@RequestMapping(value = "show-salary-paid-details", method = RequestMethod.POST)
	public @ResponseBody String getSalaryPaidDetails(
			 @RequestParam int month)
			throws JsonProcessingException, ParseException {
	List<EmployeeSalaryDTO> list=employeeSalaryService.findMonthlyPayments(month);
		return new ObjectMapper().writeValueAsString(list);
	}
	@RequestMapping(value = "pay-advance-salary", method = RequestMethod.GET)
	public String showSetting(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
			model.addAttribute("TeacherList",
				employeeService.findAllByIsDeletedTrue(Employees.class));
			
		model.addAttribute("AdvanceSalaryDTO", new AdvanceSalaryDTO());
		
		return "payadvance";
	}
	@RequestMapping(value = "getadvancepayments", method = RequestMethod.POST)
	public @ResponseBody String getAdvancePayments(
			 @RequestParam int employeeId)
			throws JsonProcessingException, ParseException {
	List<AdvanceSalaryDTO> list=advanceSalaryService.findAdvancepaymentsbyEmployeeId(employeeId);
		return new ObjectMapper().writeValueAsString(list);
	}
	@RequestMapping(value = "submit-advance-payment", method = RequestMethod.POST)
	public String payadvance(
			@ModelAttribute("AdvanceSalaryDTO") AdvanceSalaryDTO advanceSalaryDTO,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws MessagingException, Exception {
		advanceSalaryService.saveAdvances(advanceSalaryDTO);
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		model.addAttribute("TeacherList",
			employeeService.findAllByIsDeletedTrue(Employees.class));
		model.addAttribute("msg","Paid Successfully");
	model.addAttribute("AdvanceSalaryDTO", new AdvanceSalaryDTO());
		return "payadvance";
	}
	@RequestMapping(value = "submit-advance-payment", method = RequestMethod.GET)
	public String payadvanceget(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
			model.addAttribute("TeacherList",
				employeeService.findAllByIsDeletedTrue(Employees.class));
			
		model.addAttribute("AdvanceSalaryDTO", new AdvanceSalaryDTO());
		
		return "payadvance";
	}
	@RequestMapping(value = "getadvancebymonth", method = RequestMethod.POST)
	public @ResponseBody String getAdvancePaymentsbymonth(
			 @RequestParam int employeeId,@RequestParam int monthId,@RequestParam int sessionId)
			throws JsonProcessingException, ParseException {
	List<AdvanceSalaryDTO> list=advanceSalaryService.findAdvancepaymentsbymonth(employeeId,monthId,sessionId);
		return new ObjectMapper().writeValueAsString(list);
	}
	
}
