package org.brijframework.college.controller.admin.collections;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.AdvanceSalary;
import org.brijframework.college.model.BookSupplier;
import org.brijframework.college.model.EmployeeRole;
import org.brijframework.college.model.EmployeeSalary;
import org.brijframework.college.model.Expense;
import org.brijframework.college.model.UniformSupplier;
import org.brijframework.college.models.dto.AdvanceSalaryDTO;
import org.brijframework.college.models.dto.BookSupplierDTO;
import org.brijframework.college.models.dto.EmployeeSalaryDTO;
import org.brijframework.college.models.dto.ExpenseDTO;
import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;
import org.brijframework.college.models.dto.UniformSupplierDTO;
import org.brijframework.college.service.AdvanceSalaryService;
import org.brijframework.college.service.BookSupplierService;
import org.brijframework.college.service.EmployeeRoleService;
import org.brijframework.college.service.EmployeeSalaryService;
import org.brijframework.college.service.ExpenseService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.brijframework.college.service.UniformSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping({ "/admin/**", "/employee/**" })
@Controller
public class ExpenseController {
	@Autowired
	ExpenseService expenseService;
	@Autowired
	EmployeeRoleService employeeRoleService;
	@Autowired
	StudentFeeSubmissionDetailsService FeeSubmissionDetailsService;
	@Autowired
	UniformSupplierService uniformSuplierService;
	@Autowired
	BookSupplierService bookSupplierService;
	@Autowired
	EmployeeSalaryService salaryService;
	@Autowired
	AdvanceSalaryService advanceSalaryService;

	@RequestMapping(value = "all-expense-menu", method = RequestMethod.GET)
	public String expenseMenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "expensemenu";
	}

	@RequestMapping(value = "show-overall-expenses", method = RequestMethod.GET)
	public String overallexpenses(
			Model model,
			HttpServletRequest request,
			@RequestParam(value = "pageno", defaultValue = "0", required = false) int pageno)
			throws IOException {
		int totalNoOfPages = 0;
		/************************* Get AllClass List ********************/
		List<Expense> expenses = expenseService
				.findAllByIsDeletedTrue(Expense.class);
		List<UniformSupplier> uniformSuppliers = uniformSuplierService
				.findAllByIsDeletedTrue(UniformSupplier.class);
		List<BookSupplier> bookSuppliers = bookSupplierService
				.findAllByIsDeletedTrue(BookSupplier.class);
		List<EmployeeSalary> salary = salaryService
				.findAllByIsDeletedTrue(EmployeeSalary.class);
		List<AdvanceSalary> advanceSalaries = advanceSalaryService
				.findAllByIsDeletedTrue(AdvanceSalary.class);
		/************************* Calculate Highest Row ********************/
		int extraExpenseSize = expenses.size();
		int uniformExpenseSize = uniformSuppliers.size();
		int bookExpneseSize = bookSuppliers.size();
		int salarySize = salary.size();
		int advanceSalarSize = advanceSalaries.size();
		int maxRow = Math.max(Math.max(extraExpenseSize, uniformExpenseSize),
				bookExpneseSize + salarySize + advanceSalarSize);
		totalNoOfPages = (maxRow / 5) + 1;
		/************************* Calculate Total Expenses ********************/
		Float overallExpenses = 0f;
		for (UniformSupplier supplier : uniformSuppliers) {
			overallExpenses += supplier.getPaidAmount();
		}
		for (Expense expense : expenses) {
			overallExpenses += expense.getExpenseAmount();
		}
		for (BookSupplier bookSupplier : bookSuppliers) {
			overallExpenses += bookSupplier.getPaidAmount();

		}
		for (EmployeeSalary empsalary : salary) {
			overallExpenses += empsalary.getPaidAmount();
		}
		for (AdvanceSalary advanceSalary : advanceSalaries) {
			overallExpenses += advanceSalary.getPaidAmount();
		}
		/************************* Get and Set 5 Value PerPage ********************/
		model.addAttribute("overallExpenses",
				expenseService.findoverallByPageNo(pageno - 1));
		model.addAttribute("overallUniformExpenses",
				uniformSuplierService.findOverallByPageNo(pageno - 1));
		model.addAttribute("overallBookExpenses",
				bookSupplierService.findOverallByPageNo(pageno - 1));
		model.addAttribute("overallSalaryExpenses",
				salaryService.findOverallByPageNo(pageno - 1));
		model.addAttribute("overallAdvanceSalaryExpenses",
				advanceSalaryService.findOverallByPageNo(pageno - 1));
		model.addAttribute("pageno", pageno);
		model.addAttribute("totalNoOfPages", totalNoOfPages);
		model.addAttribute("overall_expenses", overallExpenses);
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "overallexpenses";
	}

	@RequestMapping(value = "show-todays-expenses", method = RequestMethod.GET)
	public String TodaysExpense(Model model, HttpServletRequest request)
			throws Exception {
		Double extraExpence = 0.0;
		Integer unifromExpence = 0;
		Double bookExpence = 0.0;
		Double salaryExpence = 0.0;
		Double advanceExpence = 0.0;
		List<ExpenseDTO> expenseDTOs = expenseService.findtodaysexpense();
		for (ExpenseDTO expenseDTO : expenseDTOs) {
			extraExpence += Double.parseDouble(expenseDTO.getExpenseAmount());
		}
		List<UniformSupplierDTO> uniformSupplierDTOs = uniformSuplierService
				.findTodaysExpense();
		for (UniformSupplierDTO uniformSupplierDTO : uniformSupplierDTOs) {
			unifromExpence += uniformSupplierDTO.getPaidAmount();
		}
		List<BookSupplierDTO> bookSupplierDTOs = bookSupplierService
				.findtodaysexpense();
		for (BookSupplierDTO bookSupplierDTO : bookSupplierDTOs) {
			bookExpence += bookSupplierDTO.getPaidAmount();
		}
		List<EmployeeSalaryDTO> salaryDTOs = salaryService.findtodaysexpense();
		for (EmployeeSalaryDTO salary : salaryDTOs) {
			salaryExpence += Double.parseDouble(salary.getPaidAmount());
		}
		List<AdvanceSalaryDTO> advanceSalaryDTOs = advanceSalaryService
				.getTodaysAdvance("");
		for (AdvanceSalaryDTO advanceSalaryDTO : advanceSalaryDTOs) {
			advanceExpence += Double.parseDouble(String
					.valueOf(advanceSalaryDTO.getPaidAmount()));
		}
		model.addAttribute("TOTALEXPENCE", (extraExpence + unifromExpence
				+ bookExpence + salaryExpence + advanceExpence));
		model.addAttribute("EXTRAEXPENCE", extraExpence);
		model.addAttribute("UNIFORMEXPENCE", unifromExpence);
		model.addAttribute("BOOKEXPENCE", bookExpence);
		model.addAttribute("SALARYEXPENCE", salaryExpence);
		model.addAttribute("ADVANCEEXPENCE", advanceExpence);

		model.addAttribute("ExtraExpense", expenseDTOs);
		model.addAttribute("UniformExpenses", uniformSupplierDTOs);
		model.addAttribute("BooksExpenses", bookSupplierDTOs);
		model.addAttribute("SalaryExpenses", salaryDTOs);
		model.addAttribute("AdvanceExpenses", advanceSalaryDTOs);
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "todaysexpenses";
	}

	@RequestMapping(value = "show-datewise-expenses", method = RequestMethod.GET)
	public String datewiseexpenses(Model model, HttpServletRequest request)
			throws IOException {
		return "datewiseexpenses";
	}

	@RequestMapping(value = "/show-datewise-expenses/{from}/{to}", method = RequestMethod.POST)
	public @ResponseBody String datewiseexpense(
			@PathVariable("from") String from, @PathVariable("to") String to,
			HttpServletRequest request, Model model) throws Exception {
		Double extraExpence = 0.0;
		Integer unifromExpence = 0;
		Integer salaryExpence = 0;
		Double bookExpence = 0.0;
		Double advanceExpence = 0.0;
		List<ExpenseDTO> extraExpenseDTOs = expenseService.finddatewiseexpense(
				from, to);
		List<UniformSupplierDTO> uniformExpensesDTOs = uniformSuplierService
				.findDatewiseExpense(from, to);
		List<BookSupplierDTO> bookExpensesDTOs = bookSupplierService
				.findDatewiseExpense(from, to);
		List<EmployeeSalaryDTO> salaryDTOs = salaryService.findDatewiseExpense(
				from, to);
		for (ExpenseDTO expenseDTO : extraExpenseDTOs) {
			extraExpence += Double.parseDouble(expenseDTO.getExpenseAmount());
		}
		for (BookSupplierDTO bookSupplierDTO : bookExpensesDTOs) {
			bookExpence += bookSupplierDTO.getPaidAmount();
		}
		for (UniformSupplierDTO uniformSupplierDTO : uniformExpensesDTOs) {
			unifromExpence += uniformSupplierDTO.getPaidAmount();
		}
		for (EmployeeSalaryDTO salary : salaryDTOs) {
			salaryExpence += Integer.parseInt(salary.getPaidAmount());
		}
		List<AdvanceSalaryDTO> advanceSalaryDTOs = advanceSalaryService
				.getTodaysAdvance(from, to);
		for (AdvanceSalaryDTO advanceSalaryDTO : advanceSalaryDTOs) {
			advanceExpence += Double.parseDouble(String
					.valueOf(advanceSalaryDTO.getPaidAmount()));
		}
		Map<String, Object> allExpenses = new HashMap<String, Object>();
		allExpenses.put("TOTALEXPENCE", (extraExpence + unifromExpence
				+ bookExpence + salaryExpence + advanceExpence));
		allExpenses.put("EXTRAEXPENCE", extraExpence);
		allExpenses.put("UNIFORMEXPENCE", unifromExpence);
		allExpenses.put("BOOKEXPENCE", bookExpence);
		allExpenses.put("SALARYEXPENCE", salaryExpence);
		allExpenses.put("extraExpenseDTOs", extraExpenseDTOs);
		allExpenses.put("uniformExpensesDTOs", uniformExpensesDTOs);
		allExpenses.put("salaryExpenseDTOs", salaryDTOs);
		allExpenses.put("bookExpensesDTOs", bookExpensesDTOs);
		allExpenses.put("AdvanceExpenses", advanceSalaryDTOs);
		allExpenses.put("ADVANCEEXPENCE", advanceExpence);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(allExpenses);
	}

	@RequestMapping(value = "show-yearly-expenses", method = RequestMethod.GET)
	public String colectionsMenu(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "yearlyexpenses";
	}

	@RequestMapping(value = "add-expense", method = RequestMethod.GET)
	public String showExpense(
			@RequestParam(required = false, defaultValue = "0") String msg,
			Model model, HttpServletRequest request) throws IOException {
		model.addAttribute("expenseDTO", new ExpenseDTO());
		model.addAttribute("rolelist",
				employeeRoleService.findAllByIsDeletedTrue(EmployeeRole.class));
		model.addAttribute("collectionlist",
				expenseService.findAll(Expense.class));
		List<StudentFeeSubmissionDetailsDTO> list = FeeSubmissionDetailsService
				.getOverallCollectionsDetails();
		Float total_paid_fee = 0f;
		for (StudentFeeSubmissionDetailsDTO details : list) {
			total_paid_fee += Float.parseFloat(details.getPaidAmount());
			model.addAttribute("total_amount", total_paid_fee);
		}
		List<Expense> list2 = expenseService.findAll(Expense.class);
		Float total_spend_fee = 0f;
		for (Expense collections : list2) {
			total_spend_fee += collections.getExpenseAmount();
			Float balance_fee = total_paid_fee - total_spend_fee;
			model.addAttribute("balance_amount", balance_fee);
		}
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		model.addAttribute("msg", msg);
		return "addexpense";
	}

	@RequestMapping(value = "add-Expenses-details", method = RequestMethod.POST)
	public void addColections(@ModelAttribute("expenseDTO") ExpenseDTO dto,
			HttpServletResponse response) throws IOException, ParseException {
		expenseService.addExpenses(dto);
		response.sendRedirect("add-expense?msg=Expenses Added successfully");
	}
	@RequestMapping(value="get-employee-by-role", method = RequestMethod.POST)
	public @ResponseBody String getEmployeebyRole(@RequestParam String role,Model model ) throws JsonProcessingException
	{
		List<String> employeeList=expenseService.findEmployeebyName(role);
		return new ObjectMapper().writeValueAsString(employeeList);
	}
}