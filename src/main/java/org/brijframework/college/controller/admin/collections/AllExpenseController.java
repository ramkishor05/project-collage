package org.brijframework.college.controller.admin.collections;
/*package com.fadsan.controller.admin.collections;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fadsan.model.EmployeeRole;
import com.fadsan.model.Expense;
import com.fadsan.models.dto.ExpenseDTO;
import com.fadsan.models.dto.StudentFeeSubmissionDetailsDTO;
import com.fadsan.service.EmployeeRoleService;
import com.fadsan.service.ExpenseService;
import com.fadsan.service.StudentFeeSubmissionDetailsService;

@RequestMapping({ "/admin/**", "/employee/**" })
@Controller
public class AllExpenseController {
	@Autowired
	ExpenseService expenseService;
	@Autowired
	EmployeeRoleService employeeRoleService;
	@Autowired
	StudentFeeSubmissionDetailsService FeeSubmissionDetailsService;

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
		List<Expense> expenses = expenseService.findAll(Expense.class);
		totalNoOfPages = (expenses.size() / 5) + 1;
		model.addAttribute("pageno", pageno);
		model.addAttribute("totalNoOfPages", totalNoOfPages);
		model.addAttribute("overallExpenses",
				expenseService.findoverallByPageNo(pageno - 1));

		Float overallExpenses = 0f;
		for (Expense expense : expenses) {
			overallExpenses += expense.getExpenseAmount();
			model.addAttribute("overall_expenses", overallExpenses);
		}
		
		 * List<ExpenseDTO> expenseDto = expenseService.findoverall();
		 * model.addAttribute("expensedto", expenseDto);
		 
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "overallexpenses";
	}

	@RequestMapping(value = "show-todays-expenses", method = RequestMethod.GET)
	public String todaysexpense(Model model, HttpServletRequest request)
			throws IOException, ParseException {
		List<ExpenseDTO> expenseDto = expenseService.findtodaysexpense();
		model.addAttribute("expensedto", expenseDto);
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "todaysexpenses";
	}

	@RequestMapping(value = "show-datewise-expenses", method = RequestMethod.GET)
	public String datewiseexpenses(Model model, HttpServletRequest request)
			throws IOException {

		return "datewiseexpenses";
	}

	@RequestMapping(value = "show-yearly-expenses", method = RequestMethod.GET)
	public String colectionsMenu(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "yearlyexpenses";
	}

	@RequestMapping(value = "add-expense", method = RequestMethod.GET)
	public String showExpense(
			@RequestParam(required = false,defaultValue="0") String msg,
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
}
*/