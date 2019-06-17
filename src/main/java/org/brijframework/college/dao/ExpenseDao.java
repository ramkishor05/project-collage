package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.Expense;

public interface ExpenseDao extends DAO<Integer, Expense> {

	List<Expense> findtodaysexpense(Date date);

	List<Expense> finddatewiseexpense(Date from, Date to);

	List<Expense> findyearlyexpense(Date from, Date to);

	List<Expense> getOverallByPageNo(int pageNo);

	List<String> findEmployeebyRole(String role);
}
