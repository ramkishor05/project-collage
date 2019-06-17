package org.brijframework.college.controller.admin.collections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.Expense;
import org.brijframework.college.model.Month;
import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.AdvanceSalaryDTO;
import org.brijframework.college.models.dto.BookSellDTO;
import org.brijframework.college.models.dto.BookSupplierDTO;
import org.brijframework.college.models.dto.DressPaymentDTO;
import org.brijframework.college.models.dto.EmployeeSalaryDTO;
import org.brijframework.college.models.dto.ExpenseDTO;
import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;
import org.brijframework.college.models.dto.UniformSupplierDTO;
import org.brijframework.college.service.AdvanceSalaryService;
import org.brijframework.college.service.BookSellService;
import org.brijframework.college.service.BookSupplierService;
import org.brijframework.college.service.DressPaymentService;
import org.brijframework.college.service.EmployeeRoleService;
import org.brijframework.college.service.EmployeeSalaryService;
import org.brijframework.college.service.ExpenseService;
import org.brijframework.college.service.MonthService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.brijframework.college.service.UniformSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/admin/**")
@Controller
public class AvailableCollectionsController {
	@Autowired
	EmployeeRoleService employeeRoleService;
	@Autowired
	StudentFeeSubmissionDetailsService FeeSubmissionDetailsService;
	@Autowired
	ExpenseService expenseService;
	@Autowired
	MonthService monthService;
	@Autowired
	SessionService sessionService;
	@Autowired
	private UniformSupplierService uniformSupplierService;
	@Autowired
	private BookSupplierService bookSupplierService;
	@Autowired
	private EmployeeSalaryService salaryService;
	@Autowired
	private AdvanceSalaryService advanceSalaryService;
	@Autowired
	DressPaymentService dressPaymentService;
	@Autowired
	BookSellService bookSellService;

	@RequestMapping(value = "available-collections-menu", method = RequestMethod.GET)
	public String availableCollectionsMenu(Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "availablecollectionsmenu";
	}

	@RequestMapping(value = "monthly-available-collections-report", method = RequestMethod.GET)
	public String monthlyAvailableCollectionsReport(Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		model.addAttribute("monthList", monthService.findAll(Month.class));
		return "monthlyavailablecollectionsreport";
	}

	@RequestMapping(value = "show-available-collection-details", method = RequestMethod.POST)
	public @ResponseBody String showDatewiseCollections(
			@RequestParam int month, Model model)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper()
				.writeValueAsString(FeeSubmissionDetailsService
						.getAvailableMonthlyCollectionsDetails(month));
	}

	@RequestMapping(value = "yearly-available-collections-report", method = RequestMethod.GET)
	public String yearlyAvailableCollectionsReport(Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		return "yearlyavailablecollectionsreport";
	}

	@RequestMapping(value = "show-available-collection", method = RequestMethod.GET)
	public String viewColectionsPage(Model model, HttpServletRequest request) {
		List<StudentFeeSubmissionDetailsDTO> list = FeeSubmissionDetailsService
				.getOverallCollectionsDetails();
		Float totalCollections = 0f;
		for (StudentFeeSubmissionDetailsDTO details : list) {
			Float paidAmount = Float.parseFloat(details.getPaidAmount());
			totalCollections += paidAmount;
			model.addAttribute("totalCollections", totalCollections);
		}
		List<Expense> list2 = expenseService.findAll(Expense.class);
		Float totalExpense = 0f;
		for (Expense expense : list2) {
			totalExpense += expense.getExpenseAmount();
			Float availableBalance = totalCollections - totalExpense;
			model.addAttribute("totalExpenses", totalExpense);
			model.addAttribute("availableBalance", availableBalance);
		}
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "showavailablecollection";
	}

	@RequestMapping(value = "show-yearly-collection-details", method = RequestMethod.POST)
	public @ResponseBody String showyearlyavailableCollections(
			@RequestParam int year, Model model)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper()
				.writeValueAsString(FeeSubmissionDetailsService
						.getAvailableyearlyCollectionsDetails(year));
	}

	@RequestMapping(value = "today-collections-expense-report", method = RequestMethod.GET)
	public String showTodayCollectionExpensePage(
			@RequestParam(required = false, defaultValue = "") String date,
			@RequestParam(required = false, defaultValue = "0") int pageNo,
			ModelMap model) throws Exception {
		Double totalCollection = 0.0;
		Double totalExpence = 0.0;
		Double totalSimpleExpence = 0.0;
		Double totalUnformExpence = 0.0;
		Double totalBooExpence = 0.0;
		Double totalSalaryExpence = 0.0;
		int totalAdvanceSalaryExpence = 0;
		String newDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (date.equals("")) {
			newDate = format.format(new Date());
		} else {
			newDate = date;
		}

		List<ExpenseDTO> expenseDTOs = expenseService.getTodaysAllExpense(date);
		for (ExpenseDTO expenseDTO : expenseDTOs) {
			totalExpence += Double.parseDouble(expenseDTO.getExpenseAmount());
			totalSimpleExpence += Double.parseDouble(expenseDTO
					.getExpenseAmount());
		}
		List<UniformSupplierDTO> uniformSupplierDTOs = uniformSupplierService
				.getUniformsBoughtDetails(newDate);
		for (UniformSupplierDTO uniformSupplierDTO : uniformSupplierDTOs) {
			totalExpence += uniformSupplierDTO.getPaidAmount();
			totalUnformExpence += uniformSupplierDTO.getPaidAmount();
		}

		List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = FeeSubmissionDetailsService
				.getTodaysCollectionsDetails(newDate);
		for (StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO : studentFeeSubmissionDetailsDTOs) {
			totalCollection += Double
					.parseDouble(studentFeeSubmissionDetailsDTO.getPaidAmount());
		}
		List<BookSellDTO> list = bookSellService.getDatewiseSoldDetails(newDate);
		List<DressPaymentDTO> uniformList = dressPaymentService.getDateWiseSoldDetails(newDate);
		for (BookSellDTO details : list) {
			totalCollection += details.getPaidAmount();
		}
		for (DressPaymentDTO details : uniformList) {
			totalCollection += details.getPaidAmount();
		}
		List<BookSupplierDTO> bookSupplierDTOs = bookSupplierService
				.getBookBoughtDetails(newDate);
		for (BookSupplierDTO bookSupplierDTO : bookSupplierDTOs) {
			totalBooExpence += bookSupplierDTO.getPaidAmount();
			totalExpence += bookSupplierDTO.getPaidAmount();
		
		}
		List<EmployeeSalaryDTO> salaryDTOs = salaryService.findExpenseByDate(newDate);
				
		for (EmployeeSalaryDTO salary : salaryDTOs) {
			totalSalaryExpence += Double.parseDouble(salary.getPaidAmount());
			totalExpence += Double.parseDouble(salary.getPaidAmount());
		
		}
		List<AdvanceSalaryDTO> advanceSalaryDTOs=advanceSalaryService.getTodaysAdvance(date);
		for(AdvanceSalaryDTO advanceSalaryDTO:advanceSalaryDTOs){
			totalAdvanceSalaryExpence +=advanceSalaryDTO.getPaidAmount();
			totalExpence +=advanceSalaryDTO.getPaidAmount();
		}
		model.addAttribute("uniformList", uniformList);
		model.addAttribute("list", list);
		model.addAttribute("ExpenseDTOs", expenseDTOs);
		model.addAttribute("UniformSupplierDTOs", uniformSupplierDTOs);
		model.addAttribute("StudentFeeSubmissionDetailsDTOs",
				studentFeeSubmissionDetailsDTOs);
		model.addAttribute("BookSupplierDTOs", bookSupplierDTOs);
		model.addAttribute("SalaryDTOs", salaryDTOs);
		model.addAttribute("AdvanceSalaryDTOs", advanceSalaryDTOs);
		
		model.addAttribute("TOTALCOLLECTION", totalCollection);
		model.addAttribute("TOTALEXPENCE", totalExpence);
		model.addAttribute("TOTALSIMPLEEXPENCE", totalSimpleExpence);
		model.addAttribute("TOTALUNIFORMEXPENCE", totalUnformExpence);
		model.addAttribute("TOTALSALARYEXPENSE", totalSalaryExpence);
		model.addAttribute("TOTALBOOKEXPENCE", totalBooExpence);
		model.addAttribute("TOTALADVANCESALARYEXPENCE", totalAdvanceSalaryExpence);
		model.addAttribute("RESTAMOUNT", (totalCollection - totalExpence));
		model.addAttribute("PAGENO", (pageNo + 1));
		model.addAttribute("DATE", newDate);
		return "todayavailablecollectionexpense";
	}
}
