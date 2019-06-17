package org.brijframework.college.service;

import java.text.ParseException;
import java.util.List;

import org.brijframework.college.model.EmployeeSalary;
import org.brijframework.college.models.dto.EmployeeSalaryDTO;

public interface EmployeeSalaryService extends CRUDService<Integer, EmployeeSalary> {

	List<EmployeeSalaryDTO> findAllSalaryDetailsofEmployee(int employeeId);

	List<EmployeeSalaryDTO> getEmployeePaymentDetail(int employeeId, int sessionId) throws ParseException;

	EmployeeSalaryDTO paysalary(EmployeeSalaryDTO employeeSalaryDTO);

	EmployeeSalaryDTO generateSalarySlip(int slipNo);

	List<EmployeeSalaryDTO> getInhandist();

	void updateStatusById(String status, int slipNo);

	List<EmployeeSalaryDTO> findOverallByPageNo(int i);

	List<EmployeeSalaryDTO> findtodaysexpense() throws ParseException;

	List<EmployeeSalaryDTO> findDatewiseExpense(String from, String to) throws ParseException;

	List<EmployeeSalaryDTO> findExpenseByDate(String date) throws ParseException;

	List<EmployeeSalaryDTO> findMonthlyPayments(int month) throws ParseException;

}
