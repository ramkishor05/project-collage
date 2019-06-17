package org.brijframework.college.service;

import java.text.ParseException;
import java.util.List;

import org.brijframework.college.model.AdvanceSalary;
import org.brijframework.college.models.dto.AdvanceSalaryDTO;

public interface AdvanceSalaryService extends CRUDService<Integer, AdvanceSalary> {

	List<AdvanceSalaryDTO> findAdvancepaymentsbyEmployeeId(int employeeId);

	void saveAdvances(AdvanceSalaryDTO advanceSalaryDTO) throws ParseException;

	List<AdvanceSalaryDTO> findAdvancepaymentsbymonth(int employeeId,
			int monthId, int sessionId);

	List<AdvanceSalaryDTO> getTodaysAdvance(String stringDate)
			throws ParseException;

	List<AdvanceSalaryDTO> getTodaysAdvance(String from, String to)
			throws Exception;

	List<AdvanceSalaryDTO> findOverallByPageNo(int pageNo);

}
