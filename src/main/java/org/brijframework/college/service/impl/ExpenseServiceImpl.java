package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.brijframework.college.dao.BookSupplierDao;
import org.brijframework.college.dao.ExpenseDao;
import org.brijframework.college.dao.UniformSupplierDao;
import org.brijframework.college.model.Expense;
import org.brijframework.college.models.dto.ExpenseDTO;
import org.brijframework.college.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("collectionService")
public class ExpenseServiceImpl extends
		CRUDServiceImpl<Integer, Expense, ExpenseDao> implements ExpenseService {
	@Autowired
	public ExpenseServiceImpl(ExpenseDao dao) {
		super(dao);
	}

	@Autowired
	private BookSupplierDao bookSupplierDao;
	@Autowired
	private UniformSupplierDao uniformSupplierDao;

	@Override
	public List<ExpenseDTO> findoverall() {
		float total = 0f;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Expense> expense = dao.findAllByIsActiveTrue(Expense.class);
		List<ExpenseDTO> dto = new ArrayList<ExpenseDTO>();
		for (Expense exp : expense) {
			ExpenseDTO expdto = new ExpenseDTO();
			expdto.setEmployeeName(exp.getEmployeeName());
			expdto.setExpenseAmount(exp.getExpenseAmount().toString());
			expdto.setPurposeDetails(exp.getPurposeDetails());
			expdto.setRoleName(exp.getEmployeeRole());
			expdto.setDateofexpense(df.format(exp.getDate()));
			total += exp.getExpenseAmount();
			expdto.setTotal(total);
			dto.add(expdto);
		}
		return dto;

	}

	public List<ExpenseDTO> findoverallByPageNo(int pageNo) {
		float total = 0f;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<ExpenseDTO> dto = new ArrayList<ExpenseDTO>();
		for (Expense exp : dao.getOverallByPageNo(pageNo)) {
			ExpenseDTO expdto = new ExpenseDTO();
			expdto.setEmployeeName(exp.getEmployeeName());
			expdto.setExpenseAmount(exp.getExpenseAmount().toString());
			expdto.setPurposeDetails(exp.getPurposeDetails());
			expdto.setRoleName(exp.getEmployeeRole());
			expdto.setDateofexpense(df.format(exp.getDate()));
			total += exp.getExpenseAmount();
			expdto.setTotal(total);
			dto.add(expdto);
		}
		return dto;

	}

	@Override
	public List<ExpenseDTO> findtodaysexpense() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		float total = 0f;
		List<Expense> expense = dao
				.findtodaysexpense(df.parse(df.format(date)));
		List<ExpenseDTO> dto = new ArrayList<ExpenseDTO>();
		for (Expense exp : expense) {
			ExpenseDTO expdto = new ExpenseDTO();
			expdto.setEmployeeName(exp.getEmployeeName());
			expdto.setExpenseAmount(exp.getExpenseAmount().toString());
			expdto.setPurposeDetails(exp.getPurposeDetails());
			expdto.setRoleName(exp.getEmployeeRole());
			total += exp.getExpenseAmount();
			expdto.setTotal(total);
			dto.add(expdto);
		}
		return dto;
	}

	@Override
	public List<ExpenseDTO> finddatewiseexpense(String from, String to)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Expense> expense = dao.finddatewiseexpense(df.parse(from),
				df.parse(to));
		List<ExpenseDTO> dto = new ArrayList<ExpenseDTO>();
		float total = 0f;
		for (Expense exp : expense) {
			ExpenseDTO expdto = new ExpenseDTO();
			expdto.setEmployeeName(exp.getEmployeeName());
			expdto.setExpenseAmount(exp.getExpenseAmount().toString());
			expdto.setPurposeDetails(exp.getPurposeDetails());
			expdto.setRoleName(exp.getEmployeeRole());
			expdto.setDateofexpense(df.format(exp.getDate()));
			total += exp.getExpenseAmount();
			expdto.setTotal(total);
			dto.add(expdto);
		}
		return dto;
	}

	@Override
	public List<ExpenseDTO> findyearlyexpense(int yearfrom, int yearto)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc = new GregorianCalendar(yearfrom, 4 - 1, 1);
		Calendar calendar = Calendar.getInstance();
		calendar.set(yearto, 3 - 1, 31);

		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		List<Expense> expense = dao.findyearlyexpense(
				df.parse(df.format(new Date(gc.getTime().getTime()))),
				df.parse(df.format(calendar.getTime())));
		List<ExpenseDTO> dto = new ArrayList<ExpenseDTO>();
		for (Expense exp : expense) {
			ExpenseDTO expdto = new ExpenseDTO();
			expdto.setEmployeeName(exp.getEmployeeName());
			expdto.setExpenseAmount(exp.getExpenseAmount().toString());
			expdto.setPurposeDetails(exp.getPurposeDetails());
			expdto.setRoleName(exp.getEmployeeRole());
			expdto.setDateofexpense(df.format(exp.getDate()));
			dto.add(expdto);
		}
		return dto;
	}

	private ExpenseDTO convertEntityToDTO(Expense expense) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ExpenseDTO expdto = null;
		if (expense != null) {
			expdto = new ExpenseDTO();
			expdto.setEmployeeName(expense.getEmployeeName());
			expdto.setExpenseAmount(expense.getExpenseAmount().toString());
			expdto.setPurposeDetails(expense.getPurposeDetails());
			expdto.setRoleName(expense.getEmployeeRole());
			expdto.setDateofexpense(df.format(expense.getDate()));
		}
		return expdto;
	}

	@Transactional
	public void addExpenses(ExpenseDTO dto) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Expense expense = new Expense();
		expense.setEmployeeRole(dto.getRoleName());
		expense.setEmployeeName(dto.getEmployeeName());
		expense.setExpenseAmount(Float.parseFloat(dto.getExpenseAmount()));
		expense.setPurposeDetails(dto.getPurposeDetails());
		expense.setDate(df.parse(df.format(new Date())));
		expense.setActive(true);
		dao.create(expense);

	}

	@Override
	public List<ExpenseDTO> getTodaysAllExpense(String date)
			throws ParseException {
		Date convertDate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (!date.equals("")) {
			convertDate = df.parse(date);
		} else {
			convertDate = df.parse(df.format(new Date()));
		}
		List<Expense> expenses = dao.findtodaysexpense(convertDate);
		List<ExpenseDTO> expenseDTOs = new ArrayList<ExpenseDTO>();
		for (Expense expense : expenses) {
			expenseDTOs.add(convertEntityToDTO(expense));
		}
		return expenseDTOs;
	}

	@Override
	public List<String> findEmployeebyName(String role) {
		List<String> employeeName=dao.findEmployeebyRole(role);
		return employeeName;
	}
}
