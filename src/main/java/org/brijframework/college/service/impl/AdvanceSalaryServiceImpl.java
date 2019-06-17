package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.brijframework.college.dao.AdvanceSalaryDao;
import org.brijframework.college.dao.EmployeeDao;
import org.brijframework.college.dao.EmployeeSalaryDao;
import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.model.AdvanceSalary;
import org.brijframework.college.model.EmployeeSalary;
import org.brijframework.college.model.Employees;
import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.AdvanceSalaryDTO;
import org.brijframework.college.service.AdvanceSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("advanceSalaryService")
public class AdvanceSalaryServiceImpl extends
		CRUDServiceImpl<Integer, AdvanceSalary, AdvanceSalaryDao> implements
		AdvanceSalaryService {

	@Autowired
	SessionDao sessionDao;
	@Autowired
	EmployeeSalaryDao salaryDao;
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	MonthDao monthDao;

	@Autowired
	public AdvanceSalaryServiceImpl(AdvanceSalaryDao dao) {
		super(dao);

	}

	@Override
	public List<AdvanceSalaryDTO> findAdvancepaymentsbyEmployeeId(int employeeId) {
		Session current = sessionDao.findCurrentSession();
		List<AdvanceSalary> advancelist = dao.findAdvancebyEmployeeId(
				employeeId, current.getSessionId());
		List<AdvanceSalaryDTO> listdto = new ArrayList<AdvanceSalaryDTO>();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		if (advancelist.isEmpty()) {
			AdvanceSalaryDTO dto = new AdvanceSalaryDTO();
			Employees employees = employeeDao.get(employeeId);
			dto.setFirstName(employees.getFirstName());
			dto.setLastName(employees.getLastName());
			dto.setMobile(employees.getMobile());
			dto.setImagename(employees.getImageName());
			dto.setDateOfPayment("no");
			listdto.add(dto);
		} else {
			for (AdvanceSalary salary : advancelist) {
				AdvanceSalaryDTO dto = new AdvanceSalaryDTO();
				dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
				dto.setPaidAmount(salary.getPaidAmount());
				dto.setLastName(salary.getEmployees().getLastName());
				dto.setEmployeeId(salary.getEmployees().getEmployeeId());
				dto.setFirstName(salary.getEmployees().getFirstName());
				dto.setImagename(salary.getEmployees().getImageName());
				dto.setMobile(salary.getEmployees().getMobile());
				listdto.add(dto);
			}
		}
		return listdto;
	}

	@Override
	public void saveAdvances(AdvanceSalaryDTO advanceSalaryDTO)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int allotMonth = 0;
		int allotSession = 0;
		Date joiningDate = employeeDao.get(advanceSalaryDTO.getEmployeeId())
				.getJoiningDate();
		Session current = sessionDao.findCurrentSession();
		String getYear[] = (current.getSessionDuration()).split("-");
		GregorianCalendar gc = new GregorianCalendar(
				Integer.parseInt(getYear[0]), 4 - 1, 1);
		Date firstDay = df.parse(df.format(new Date(gc.getTime().getTime())));
		Date pay = firstDay;

		long diff = firstDay.getTime() - joiningDate.getTime();
		long differDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		if (differDays < 0) {
			pay = joiningDate;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(pay);

		int months = cal.get(Calendar.MONTH) + 1;
     if (months > 3) {
			months =months-3;
		}
		 else {
			 months = months +9;
		}

		EmployeeSalary salary = salaryDao.findLastPayedMonth(advanceSalaryDTO
				.getEmployeeId());
		if (salary == null) {
			allotMonth = months+1;
			allotSession = current.getSessionId();

		} else {
			EmployeeSalary currentSalary = salaryDao.findPaymentinCurrent(
					advanceSalaryDTO.getEmployeeId(), current.getSessionId());
			if (currentSalary == null) {
				allotMonth = salary.getMonth().getMonthId() + 1;

				if (allotMonth == 13) {
					allotMonth = months;
					allotSession = current.getSessionId();
				} else {

					allotSession = salary.getSession().getSessionId();
				}

			} else {
				allotSession = current.getSessionId();
				allotMonth = currentSalary.getMonth().getMonthId() + 1;
			}

		}

		AdvanceSalary advance = new AdvanceSalary();
		advance.setActive(true);
		advance.setDateOfPayment(new Date());
		advance.setPaidAmount(advanceSalaryDTO.getPaidAmount());
		advance.setEmployees(employeeDao.get(advanceSalaryDTO.getEmployeeId()));
		advance.setMonth(monthDao.get(allotMonth));
		advance.setSession(sessionDao.get(allotSession));
		dao.create(advance);

	}

	@Override
	public List<AdvanceSalaryDTO> findAdvancepaymentsbymonth(int employeeId,
			int monthId, int sessionId) {
		List<AdvanceSalaryDTO> listdto = new ArrayList<AdvanceSalaryDTO>();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<AdvanceSalary> advancepay = dao.findAdvanceformonth(sessionId,
				monthId, employeeId);
		for (AdvanceSalary amounts : advancepay) {
			AdvanceSalaryDTO dto = new AdvanceSalaryDTO();
			dto.setDateOfPayment(df.format(amounts.getDateOfPayment()));
			dto.setPaidAmount(amounts.getPaidAmount());
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<AdvanceSalaryDTO> getTodaysAdvance(String stringDate)
			throws ParseException {
		List<AdvanceSalaryDTO> listdto = new ArrayList<AdvanceSalaryDTO>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		if (stringDate.equals("")) {
			date = df.parse(df.format(date));
		} else {
			date = df.parse(stringDate);
		}
		List<AdvanceSalary> advancelist = dao.getTodaysAdvance(date);
		for (AdvanceSalary salary : advancelist) {
			AdvanceSalaryDTO dto = new AdvanceSalaryDTO();
			dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
			dto.setPaidAmount(salary.getPaidAmount());
			dto.setLastName(salary.getEmployees().getLastName());
			dto.setEmployeeId(salary.getEmployees().getEmployeeId());
			dto.setEmployeeName(salary.getEmployees().getFirstName() + " "
					+ salary.getEmployees().getLastName());
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<AdvanceSalaryDTO> getTodaysAdvance(String from, String to)
			throws Exception {
		List<AdvanceSalaryDTO> listdto = new ArrayList<AdvanceSalaryDTO>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<AdvanceSalary> advancelist = dao.getTodaysAdvance(df.parse(from),
				df.parse(to));
		for (AdvanceSalary salary : advancelist) {
			AdvanceSalaryDTO dto = new AdvanceSalaryDTO();
			dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
			dto.setPaidAmount(salary.getPaidAmount());
			dto.setLastName(salary.getEmployees().getLastName());
			dto.setEmployeeId(salary.getEmployees().getEmployeeId());
			dto.setEmployeeName(salary.getEmployees().getFirstName() + " "
					+ salary.getEmployees().getLastName());
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<AdvanceSalaryDTO> findOverallByPageNo(int pageNo) {
		List<AdvanceSalaryDTO> listdto = new ArrayList<AdvanceSalaryDTO>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (AdvanceSalary salary : dao.getByPageNo(pageNo)) {
			AdvanceSalaryDTO dto = new AdvanceSalaryDTO();
			dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
			dto.setPaidAmount(salary.getPaidAmount());
			dto.setLastName(salary.getEmployees().getLastName());
			dto.setEmployeeId(salary.getEmployees().getEmployeeId());
			dto.setEmployeeName(salary.getEmployees().getFirstName() + " "
					+ salary.getEmployees().getLastName());
			listdto.add(dto);
		}
		return listdto;
	}

}