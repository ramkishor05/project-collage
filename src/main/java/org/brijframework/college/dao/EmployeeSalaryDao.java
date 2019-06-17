package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.EmployeeSalary;

public interface EmployeeSalaryDao extends DAO<Integer, EmployeeSalary> {

	List<Integer> findAllPayedMonths(int employeeId, Integer sessionId);

	List<EmployeeSalary> findEmployeePaidDetails(int employeeId,
			Integer sessionId, Integer ids);

	List<Integer> getallDistinctSlipNo(int sessionId, int employeeId);

	Integer getLastMonthIdOfSlip(Integer integer);

	List<EmployeeSalary> getPrevsSalaryPaidDetails(int sessionId, Integer monthId,
			int employeeId);

	List<Integer> getMonthIdsBySlipNo(Integer slipNo);

	EmployeeSalary getNewSlipNo();

	EmployeeSalary findSingleSlipData(int slipNo);

	List<Integer> findPayedMonthsfromSlip(int slipNo);

	List<Integer> getallInhandDistinctSlipNo();

	List<EmployeeSalary> findAllDetailsofSlip(int slipNo);

	List<EmployeeSalary> getOverallSalaryByPageNo(int i);

	List<Integer> getallDistinctSlipNoByPageNo(int i);

	List<Integer> getallDistinctSlipNoToday(Date parse);

	List<Integer> getallDistinctSlipNoByDate(Date parse, Date parse2);

	List<Integer> getallDistinctSlipNoByMonth(int monthId, Integer sessionId);

	EmployeeSalary findLastPayedMonth(int employeeId);

	EmployeeSalary findPaymentinCurrent(int employeeId, Integer sessionId);

	

}
