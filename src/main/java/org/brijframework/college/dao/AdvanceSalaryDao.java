package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.AdvanceSalary;

public interface AdvanceSalaryDao extends DAO<Integer, AdvanceSalary> {

	List<AdvanceSalary> findAdvancebyEmployeeId(int employeeId, int sessionId);

	List<AdvanceSalary> findAdvanceformonth(int sessionId, Integer monthId,
			int employeeId);

	List<AdvanceSalary> getTodaysAdvance(Date frmDate, Date toDate);

	List<AdvanceSalary> getTodaysAdvance(Date Date);

	List<AdvanceSalary> getByPageNo(int pageNo);

}
