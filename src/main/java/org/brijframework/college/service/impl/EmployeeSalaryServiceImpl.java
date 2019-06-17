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
import org.brijframework.college.dao.EmployeeAttendanceDao;
import org.brijframework.college.dao.EmployeeDao;
import org.brijframework.college.dao.EmployeeSalaryDao;
import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.model.AdvanceSalary;
import org.brijframework.college.model.EmployeeAttendance;
import org.brijframework.college.model.EmployeeSalary;
import org.brijframework.college.model.Employees;
import org.brijframework.college.model.Month;
import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.EmployeeSalaryDTO;
import org.brijframework.college.service.EmployeeSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("employeeSalaryService")
public class EmployeeSalaryServiceImpl extends
		CRUDServiceImpl<Integer, EmployeeSalary, EmployeeSalaryDao> implements
		EmployeeSalaryService {
	@Autowired
	SessionDao sessionDao;
	@Autowired
	MonthDao monthDao;
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	EmployeeAttendanceDao attendanceDao;
	@Autowired
	AdvanceSalaryDao advanceSalaryDao;

	@Autowired
	public EmployeeSalaryServiceImpl(EmployeeSalaryDao dao) {
		super(dao);

	}

	@Override
	public List<EmployeeSalaryDTO> findAllSalaryDetailsofEmployee(int employeeId) {
		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 * 
		 * int months = 1;
		 * 
		 * Session current = sessionDao.findCurrentSession(); String getYear[] =
		 * (current.getSessionDuration()).split("-"); GregorianCalendar gc = new
		 * GregorianCalendar( Integer.parseInt(getYear[0]), 3 - 1, 1); Date
		 * firstDay = df.parse(df.format(new Date(gc.getTime().getTime())));
		 * Date pay = firstDay; Date join = df.parse(dates); long diff =
		 * firstDay.getTime() - join.getTime(); long differDays =
		 * TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); if (differDays >
		 * 0) { pay = join; } Calendar cal = Calendar.getInstance();
		 * cal.setTime(pay); months = cal.get(Calendar.MONTH) + 1; if (months >
		 * 10) months = months - 10; else months = months + 2;
		 */
		Session current = sessionDao.findCurrentSession();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeSalaryDTO> listdto = new ArrayList<EmployeeSalaryDTO>();
		List<Integer> months = dao.findAllPayedMonths(employeeId,
				current.getSessionId());
		for (Integer ids : months) {

			List<EmployeeSalary> lists = dao.findEmployeePaidDetails(
					employeeId, current.getSessionId(), ids);
			for (EmployeeSalary salary : lists) {
				EmployeeSalaryDTO dto = new EmployeeSalaryDTO();
				dto.setMonth(monthDao.get(ids).getMonthName());
				dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
				dto.setFirstName(salary.getEmployees().getFirstName());
				dto.setLastName(salary.getEmployees().getLastName());
				dto.setPaidAmount(String.valueOf(salary.getPaidAmount()));
				dto.setSalaryAmount(salary.getSalaryAmount());
				listdto.add(dto);
			}

		}
		return listdto;

	}

	@Override
	public List<EmployeeSalaryDTO> getEmployeePaymentDetail(int employeeId,int sessionId)
			throws ParseException {
		int temp=0;
		Session current = sessionDao.findCurrentSession();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeSalaryDTO> employeesalaryDTOs = new ArrayList<EmployeeSalaryDTO>();
		
		List<Integer> paidMonthIds = dao.findAllPayedMonths(employeeId,
				sessionId);
		List<Integer> leCurrentMonth = new ArrayList<Integer>();
		List<Integer> geCurrentMonth = new ArrayList<Integer>();
		List<Integer> slipNos = dao.getallDistinctSlipNo(sessionId, employeeId);
		Calendar calendar = Calendar.getInstance();
		int monthId = calendar.get(Calendar.MONTH) + 1;
		if (monthId == 1) {
			monthId = 11;
		} else if (monthId == 2) {
			monthId = 12;
		} else {
			monthId = monthId - 2;
		}
		monthId = monthId - 1;
		if (monthId == 0)
			monthId = 12;
		for (Integer integer : slipNos) {
			Integer lastMonthId = dao.getLastMonthIdOfSlip(integer);
			if (lastMonthId <= monthId) {
				leCurrentMonth.add(lastMonthId);
			} else {
				geCurrentMonth.add(lastMonthId);
			}
		}
		Date joiningDate = employeeDao.get(employeeId)
				.getJoiningDate();
		Session currents = sessionDao.get(sessionId);
		String getYear[] = (currents.getSessionDuration()).split("-");
		GregorianCalendar gc1 = new GregorianCalendar(
				Integer.parseInt(getYear[0]), 3 - 1, 1);
		Date firstDay = df.parse(df.format(new Date(gc1.getTime().getTime())));
		Date pay = firstDay;

		long diff = firstDay.getTime() - joiningDate.getTime();
		long differDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		if (differDays < 0) {
			pay = joiningDate;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(pay);
		
		int months1 = cal.get(Calendar.MONTH) + 1;
	
		if (months1 > 2)
			months1 = months1 - 2;
		else
			months1 = months1 + 10;
	
		List<Month> months = monthDao.findSomeMonths(months1-1);
		Employees employees = employeeDao.get(employeeId);

		for (Month month : months) {
			if (month.getMonthId() != 13) {
				EmployeeSalaryDTO salaryDTO = new EmployeeSalaryDTO();
				salaryDTO.setMonth(month.getMonthName());
				salaryDTO.setMonthId(month.getMonthId());
				if (paidMonthIds.contains(month.getMonthId())) {
					String paidAmount = "";
					String paidStatus = "";
					String monthNames = "";
					boolean flag = false;
					for (EmployeeSalary salarydetails : dao
							.getPrevsSalaryPaidDetails(sessionId,
									month.getMonthId(), employeeId)) {

						salaryDTO.setPaidAmount("-");
						salaryDTO.setSalaryPaidStatus(salarydetails
								.getSalaryPaidStatus());
						salaryDTO.setFineAmount("0");
						salaryDTO.setSlipNo(0);
						salaryDTO.setPaidBy("-");
						salaryDTO.setBankName("-");
						salaryDTO.setChequeNo("-");
						salaryDTO.setSalaryAmount(salarydetails.getSalary());
						salaryDTO.setDateOfPayment("-");
						salaryDTO.setAdvance("-");

						if (leCurrentMonth.contains(month.getMonthId())
								&& month.getMonthId() < monthId) {
							// if (month.getMonthId() == monthId) {
							paidAmount = String.valueOf(salarydetails
									.getPaidAmount());
							paidStatus = salarydetails.getSalaryPaidStatus();
							salaryDTO.setSlipNo(salarydetails.getSlipNo());
							salaryDTO
									.setSalaryAmount(salarydetails.getSalary());

							salaryDTO.setPaidBy(salarydetails.getPaidBy());
							if (salarydetails.getPaidBy().equals("cheque")) {

								salaryDTO.setBankName(salarydetails
										.getBankName());
								salaryDTO.setChequeNo(salarydetails
										.getChequeNo());
							} else {
								salaryDTO.setBankName("-");
								salaryDTO.setChequeNo("-");
							}
							for (Integer slipMonthId : dao
									.getMonthIdsBySlipNo(salarydetails
											.getSlipNo())) {
								monthNames += " "
										+ monthDao.get(slipMonthId)
												.getMonthName();
							}
							salaryDTO.setFineAmount((String
									.valueOf(salarydetails.getFine())));
							salaryDTO
									.setIncentive(salarydetails.getIncentive());
							salaryDTO.setAdvance("Salary");
							salaryDTO.setDateOfPayment(df.format(salarydetails
									.getDateOfPayment()));
							flag = true;
						} else if (month.getMonthId() == monthId) {
							for (EmployeeSalary salarydetailsNew : dao
									.getPrevsSalaryPaidDetails(sessionId,
											month.getMonthId(), employeeId)) {
								paidAmount = String.valueOf(salarydetailsNew
										.getPaidAmount());
								paidStatus = salarydetailsNew
										.getSalaryPaidStatus();

								salaryDTO.setSlipNo(salarydetailsNew
										.getSlipNo());
								salaryDTO.setSalaryAmount(salarydetailsNew
										.getSalary());
								salaryDTO.setPaidBy(salarydetailsNew
										.getPaidBy());
								if (salarydetailsNew.getPaidBy().equals(
										"cheque")) {

									salaryDTO.setBankName(salarydetailsNew
											.getBankName());
									salaryDTO.setChequeNo(salarydetailsNew
											.getChequeNo());

								} else {

									salaryDTO.setBankName("-");
									salaryDTO.setChequeNo("-");

								}
								for (Integer slipMonthId : dao
										.getMonthIdsBySlipNo(salarydetails
												.getSlipNo())) {
									monthNames += " "
											+ monthDao.get(slipMonthId)
													.getMonthName();
								}
								salaryDTO.setFineAmount((String
										.valueOf(salarydetails.getFine())));
								salaryDTO.setIncentive(salarydetails
										.getIncentive());
								salaryDTO.setDateOfPayment(df
										.format(salarydetails
												.getDateOfPayment()));
								salaryDTO.setAdvance("Salary");
							}
							flag = true;
						} else {

							if (geCurrentMonth.contains(month.getMonthId())
									&& month.getMonthId() > monthId) {
								// if (month.getMonthId() == monthId) {
								paidAmount = String.valueOf(salarydetails
										.getPaidAmount());
								paidStatus = salarydetails
										.getSalaryPaidStatus();
								salaryDTO.setSlipNo(salarydetails.getSlipNo());

								salaryDTO.setPaidBy(salarydetails.getPaidBy());
								salaryDTO.setSalaryAmount(salarydetails
										.getSalaryAmount());
								if (salarydetails.getPaidBy().equals("cheque")) {

									salaryDTO.setBankName(salarydetails
											.getBankName());
									salaryDTO.setChequeNo(salarydetails
											.getChequeNo());
								} else {
									salaryDTO.setBankName("-");
									salaryDTO.setChequeNo("-");
								}
								for (Integer slipMonthId : dao
										.getMonthIdsBySlipNo(salarydetails
												.getSlipNo())) {
									monthNames += " "
											+ monthDao.get(slipMonthId)
													.getMonthName();
								}
								salaryDTO.setFineAmount((String
										.valueOf(salarydetails.getFine())));
								salaryDTO.setIncentive(salarydetails
										.getIncentive());
								salaryDTO.setAdvance("Advance");
								salaryDTO.setDateOfPayment(df
										.format(salarydetails
												.getDateOfPayment()));
								flag = true;
							}
						}

					}
					if (flag) {

						salaryDTO.setPaidAmount(paidAmount);
						salaryDTO.setCommonString(monthNames);
						salaryDTO.setSalaryPaidStatus(paidStatus);
					}
					salaryDTO.setStatus("YES");
				} else {
					salaryDTO.setPaidAmount("-");
					salaryDTO.setSalaryPaidStatus("-");
					salaryDTO.setStatus("NO");
					salaryDTO.setSlipNo(0);
					salaryDTO.setPaidBy("-");
					salaryDTO.setBankName("-");
					salaryDTO.setChequeNo("-");
					salaryDTO.setFineAmount("0");
					salaryDTO.setSalaryAmount(employees.getSalary());
					salaryDTO.setIncentive(0);
					salaryDTO.setDateOfPayment("-");
					salaryDTO.setAdvance("-");

				}
				if (month.getMonthId() <= monthId) {

					String year[] = current.getSessionDuration().split("-");
					int currentYear;
					int totalPresent = 0;
					int totalAbsent = 0;
					int totalLate = 0;
					int totalLeave = 0;
					int neededmonth = month.getMonthId();
					if (neededmonth > 10)
						neededmonth = neededmonth - 10;
					else
						neededmonth = neededmonth + 2;
					if (neededmonth < 3) {
						currentYear = Integer.parseInt(year[1]);
					} else {
						currentYear = Integer.parseInt(year[0]);
					}
					GregorianCalendar gc = new GregorianCalendar(currentYear,
							neededmonth - 1, 1);

					calendar = Calendar.getInstance();
					calendar.set(currentYear, neededmonth - 1, 1);
					calendar.set(Calendar.DATE,
							calendar.getActualMaximum(Calendar.DATE));
					Date fromDate = df.parse(df.format(new Date(gc.getTime()
							.getTime())));
					Date toDate = df.parse(df.format(calendar.getTime()));
					calendar.setTime(toDate);

					List<EmployeeAttendance> attendances = attendanceDao
							.getEmployeeAttendanceList(employeeId, fromDate,
									toDate);

					int total = attendances.size();
					double percent = 0;

					for (EmployeeAttendance employeeAttendance : attendances) {

						if (employeeAttendance.getAttendanceStatus().equals(
								"PRESENT")) {
							totalPresent++;
						}
						if (employeeAttendance.getAttendanceStatus().equals(
								"LATE")) {
							totalLate++;
						}
						if (employeeAttendance.getAttendanceStatus().equals(
								"LEAVE")) {
							totalLeave++;
						}
						if (employeeAttendance.getAttendanceStatus().equals(
								"ABSENT")) {
							totalAbsent++;
						}
					}

					percent = Math
							.round(((double) (totalPresent + totalLate) / total) * 100);
					salaryDTO.setTotalAbsent(totalAbsent);
					salaryDTO.setTotalDays(total);
					salaryDTO.setTotallate(totalLate);
					salaryDTO.setTotalLeave(totalLeave);
					salaryDTO.setTotalPresent(totalPresent);
					salaryDTO.setPresent(totalPresent + totalLate);
					salaryDTO.setPercentAttendance(percent);
				} else {
					salaryDTO.setTotalAbsent(0);
					salaryDTO.setTotalDays(0);
					salaryDTO.setTotallate(0);
					salaryDTO.setTotalLeave(0);
					salaryDTO.setTotalPresent(0);
					salaryDTO.setPresent(0);
					salaryDTO.setPercentAttendance(0);
				}
				temp=0;
				List<AdvanceSalary> advancepay=advanceSalaryDao.findAdvanceformonth(sessionId,month.getMonthId(), employeeId);
				for(AdvanceSalary amounts:advancepay)
				{
					temp+=amounts.getPaidAmount();
				}
				salaryDTO.setAdvanceAmount(temp);
				employeesalaryDTOs.add(salaryDTO);

			}

		}
		return employeesalaryDTOs;
	}

	@Override
	public EmployeeSalaryDTO paysalary(EmployeeSalaryDTO employeeSalaryDTO) {
		EmployeeSalary details = dao.getNewSlipNo();

		int slipNo = 0;
		if (details == null) {
			slipNo = 50501;

		} else {
			slipNo = details.getSlipNo() + 1;
		}
		for (int monthId : employeeSalaryDTO.getMonthsId()) {
			EmployeeSalary salary = new EmployeeSalary();
			salary.setActive(true);
			if (employeeSalaryDTO.getPaidBy().equals("cheque")) {
				salary.setBankName(employeeSalaryDTO.getBankName());
				salary.setChequeNo(employeeSalaryDTO.getChequeNo());
				salary.setSalaryPaidStatus("InHand");
			} else {
				salary.setSalaryPaidStatus("Completed");
			}
			salary.setDateOfPayment(new Date());
			salary.setEmployees(employeeDao.get(employeeSalaryDTO
					.getEmployeeId()));
			salary.setFine(Integer.parseInt(employeeSalaryDTO.getFineAmount()));
			salary.setIncentive(employeeSalaryDTO.getIncentive());
			salary.setMonth(monthDao.get(monthId));
			salary.setPaidAmount(Integer.parseInt(employeeSalaryDTO
					.getPaidAmount()));
			salary.setPaidAmountInWord(employeeSalaryDTO.getPaidAmountInWord());
			salary.setPaidBy(employeeSalaryDTO.getPaidBy());
			salary.setSalaryAmount(employeeSalaryDTO.getSalaryAmount());
			salary.setSession(sessionDao.findCurrentSession());
			salary.setDue(Integer.parseInt(employeeSalaryDTO.getDueAmount()));
			salary.setSlipNo(slipNo);
			salary.setSalary(salary.getEmployees().getSalary());
			salary.setAdvance(employeeSalaryDTO.getAdvanceAmount());
			dao.create(salary);

		}
		return generateSalarySlip(slipNo);

	}

	@Override
	public EmployeeSalaryDTO generateSalarySlip(int slipNo) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		EmployeeSalary salary = dao.findSingleSlipData(slipNo);
		EmployeeSalaryDTO dto = new EmployeeSalaryDTO();
		dto.setSlipNo(slipNo);
		dto.setSalaryAmount(salary.getSalaryAmount());
		dto.setPaidBy(salary.getPaidBy());
		dto.setIncentive(salary.getIncentive());
		dto.setPaidAmountInWord(salary.getPaidAmountInWord());
		dto.setPaidAmount(String.valueOf(salary.getPaidAmount()));
		dto.setNetPayableAmount(String.valueOf(salary.getPaidAmount()
				+ salary.getDue()));
		dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
		dto.setDueAmount(String.valueOf(salary.getDue()));
		dto.setFirstName(salary.getEmployees().getFirstName());
		dto.setLastName(salary.getEmployees().getLastName());
		dto.setEmployeeId(salary.getEmployees().getEmployeeId());
		dto.setFineAmount(String.valueOf(salary.getFine()));
		dto.setAdvanceAmount(salary.getAdvance());
		if (salary.getPaidBy().equals("cheque")) {
			dto.setBankName(salary.getBankName());
			dto.setChequeNo(salary.getChequeNo());
		}
		List<Integer> monthsdata = dao.findPayedMonthsfromSlip(slipNo);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int current = cal.get(Calendar.MONTH) + 1;

		if (current > 2)
			current = current - 2;
		else
			current = current + 10;
		current = current - 1;
		if (current == 0)
			current = 12;
		String names = "";
		int status = 0;

		for (int months : monthsdata) {
			names += monthDao.get(months).getMonthName() + " \t";

			if (months > current) {
				status++;
			}

		}
		dto.setMonth(names);
		if (status > 0) {
			dto.setAdvance("Advance for " + String.valueOf(status) + " month");
		} else {
			dto.setAdvance("none");
		}
		return dto;
	}

	@Override
	public List<EmployeeSalaryDTO> getInhandist() {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<EmployeeSalaryDTO> listdto = new ArrayList<EmployeeSalaryDTO>();
		List<Integer> integers = dao.getallInhandDistinctSlipNo();
		for (Integer integer : integers) {
			EmployeeSalary salary = dao.findSingleSlipData(integer);
			EmployeeSalaryDTO dto = new EmployeeSalaryDTO();
			dto.setSlipNo(integer);
			dto.setSalaryAmount(salary.getSalaryAmount());
			dto.setPaidBy(salary.getPaidBy());
			dto.setIncentive(salary.getIncentive());
			dto.setPaidAmountInWord(salary.getPaidAmountInWord());
			dto.setPaidAmount(String.valueOf(salary.getPaidAmount()));
			dto.setNetPayableAmount(String.valueOf(salary.getPaidAmount()
					+ salary.getDue()));
			dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
			dto.setDueAmount(String.valueOf(salary.getDue()));
			dto.setFirstName(salary.getEmployees().getFirstName());
			dto.setLastName(salary.getEmployees().getLastName());
			dto.setEmployeeId(salary.getEmployees().getEmployeeId());
			dto.setFineAmount(String.valueOf(salary.getFine()));
			if (salary.getPaidBy().equals("cheque")) {
				dto.setBankName(salary.getBankName());
				dto.setChequeNo(salary.getChequeNo());
			}
			List<Integer> monthsdata = dao.findPayedMonthsfromSlip(integer);
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int current = cal.get(Calendar.MONTH) + 1;

			if (current > 2)
				current = current - 2;
			else
				current = current + 10;
			current = current - 1;
			if (current == 0)
				current = 12;
			String names = "";
			int status = 0;

			for (int months : monthsdata) {
				names += monthDao.get(months).getMonthName() + " \t";

				if (months > current) {
					status++;
				}

			}
			dto.setMonth(names);
			if (status > 0) {
				dto.setAdvance("Advance for " + String.valueOf(status)
						+ " month");
			} else {
				dto.setAdvance("Salary");
			}
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public void updateStatusById(String status, int slipNo) {
		List<EmployeeSalary> lists = dao.findAllDetailsofSlip(slipNo);
		for (EmployeeSalary salary : lists) {
			salary.setSalaryPaidStatus(status);
		}

	}

	@Override
	public List<EmployeeSalaryDTO> findOverallByPageNo(int i) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeSalaryDTO> listdto = new ArrayList<EmployeeSalaryDTO>();
		List<Integer> integers = dao.getallDistinctSlipNoByPageNo(i);
		for (Integer integer : integers) {
			EmployeeSalary salary = dao.findSingleSlipData(integer);
			EmployeeSalaryDTO dto = new EmployeeSalaryDTO();
			dto.setSlipNo(salary.getSlipNo());
			dto.setSalaryAmount(salary.getSalaryAmount());
			dto.setPaidBy(salary.getPaidBy());
			dto.setIncentive(salary.getIncentive());
			dto.setPaidAmountInWord(salary.getPaidAmountInWord());
			dto.setPaidAmount(String.valueOf(salary.getPaidAmount()));
			dto.setNetPayableAmount(String.valueOf(salary.getPaidAmount()
					+ salary.getDue()));
			dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
			dto.setDueAmount(String.valueOf(salary.getDue()));
			dto.setFirstName(salary.getEmployees().getFirstName());
			dto.setLastName(salary.getEmployees().getLastName());
			dto.setEmployeeId(salary.getEmployees().getEmployeeId());
			dto.setSalaryPaidStatus(salary.getSalaryPaidStatus());
			dto.setFineAmount(String.valueOf(salary.getFine()));
			if (salary.getPaidBy().equals("cheque")) {
				dto.setBankName(salary.getBankName());
				dto.setChequeNo(salary.getChequeNo());
			}
			else
			{
				dto.setBankName("-");
				dto.setChequeNo("-");
			}
			List<Integer> monthsdata = dao.findPayedMonthsfromSlip(salary
					.getSlipNo());
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int current = cal.get(Calendar.MONTH) + 1;

			if (current > 2)
				current = current - 2;
			else
				current = current + 10;
			current = current - 1;
			if (current == 0)
				current = 12;
			String names = "";
			int status = 0;

			for (int months : monthsdata) {
				names += monthDao.get(months).getMonthName() + " \t";

				if (months > current) {
					status++;
				}

			}
			dto.setMonth(names);
			if (status > 0) {
				dto.setAdvance("Advance for " + String.valueOf(status)
						+ " month");
			} else {
				dto.setAdvance("Salary");
			}
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<EmployeeSalaryDTO> findtodaysexpense() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeSalaryDTO> listdto = new ArrayList<EmployeeSalaryDTO>();
		List<Integer> integers = dao.getallDistinctSlipNoToday(df.parse(df
				.format(new Date())));
		for (Integer integer : integers) {
			EmployeeSalary salary = dao.findSingleSlipData(integer);
			EmployeeSalaryDTO dto = new EmployeeSalaryDTO();
			dto.setSlipNo(salary.getSlipNo());
			dto.setSalaryAmount(salary.getSalaryAmount());
			dto.setPaidBy(salary.getPaidBy());
			dto.setIncentive(salary.getIncentive());
			dto.setPaidAmountInWord(salary.getPaidAmountInWord());
			dto.setPaidAmount(String.valueOf(salary.getPaidAmount()));
			dto.setNetPayableAmount(String.valueOf(salary.getPaidAmount()
					+ salary.getDue()));
			dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
			dto.setDueAmount(String.valueOf(salary.getDue()));
			dto.setFirstName(salary.getEmployees().getFirstName());
			dto.setLastName(salary.getEmployees().getLastName());
			dto.setEmployeeId(salary.getEmployees().getEmployeeId());
			dto.setSalaryPaidStatus(salary.getSalaryPaidStatus());
			dto.setFineAmount(String.valueOf(salary.getFine()));
			if (salary.getPaidBy().equals("cheque")) {
				dto.setBankName(salary.getBankName());
				dto.setChequeNo(salary.getChequeNo());
			}
			else
			{
				dto.setBankName("-");
				dto.setChequeNo("-");
			}
			List<Integer> monthsdata = dao.findPayedMonthsfromSlip(salary
					.getSlipNo());
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int current = cal.get(Calendar.MONTH) + 1;

			if (current > 2)
				current = current - 2;
			else
				current = current + 10;
			current = current - 1;
			if (current == 0)
				current = 12;
			String names = "";
			int status = 0;

			for (int months : monthsdata) {
				names += monthDao.get(months).getMonthName() + " \t";

				if (months > current) {
					status++;
				}

			}
			dto.setMonth(names);
			if (status > 0) {
				dto.setAdvance("Advance for " + String.valueOf(status)
						+ " month");
			} else {
				dto.setAdvance("Salary");
			}
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<EmployeeSalaryDTO> findDatewiseExpense(String from, String to) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeSalaryDTO> listdto = new ArrayList<EmployeeSalaryDTO>();
		List<Integer> integers = dao.getallDistinctSlipNoByDate(
				df.parse(from), df.parse(to));
		for (Integer integer : integers) {
			EmployeeSalary salary = dao.findSingleSlipData(integer);
			EmployeeSalaryDTO dto = new EmployeeSalaryDTO();
			dto.setSlipNo(salary.getSlipNo());
			dto.setSalaryAmount(salary.getSalaryAmount());
			dto.setPaidBy(salary.getPaidBy());
			dto.setIncentive(salary.getIncentive());
			dto.setPaidAmountInWord(salary.getPaidAmountInWord());
			dto.setPaidAmount(String.valueOf(salary.getPaidAmount()));
			dto.setNetPayableAmount(String.valueOf(salary.getPaidAmount()
					+ salary.getDue()));
			dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
			dto.setDueAmount(String.valueOf(salary.getDue()));
			dto.setFirstName(salary.getEmployees().getFirstName());
			dto.setLastName(salary.getEmployees().getLastName());
			dto.setEmployeeId(salary.getEmployees().getEmployeeId());
			dto.setSalaryPaidStatus(salary.getSalaryPaidStatus());
			dto.setFineAmount(String.valueOf(salary.getFine()));
			if (salary.getPaidBy().equals("cheque")) {
				dto.setBankName(salary.getBankName());
				dto.setChequeNo(salary.getChequeNo());
			}
			else
			{
				dto.setBankName("-");
				dto.setChequeNo("-");
			}
			List<Integer> monthsdata = dao.findPayedMonthsfromSlip(salary
					.getSlipNo());
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int current = cal.get(Calendar.MONTH) + 1;

			if (current > 2)
				current = current - 2;
			else
				current = current + 10;
			current = current - 1;
			if (current == 0)
				current = 12;
			String names = "";
			int status = 0;

			for (int months : monthsdata) {
				names += monthDao.get(months).getMonthName() + " \t";

				if (months > current) {
					status++;
				}

			}
			dto.setMonth(names);
			if (status > 0) {
				dto.setAdvance("Advance for " + String.valueOf(status)
						+ " month");
			} else {
				dto.setAdvance("Salary");
			}
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<EmployeeSalaryDTO> findExpenseByDate(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeSalaryDTO> listdto = new ArrayList<EmployeeSalaryDTO>();
		List<Integer> integers = dao.getallDistinctSlipNoToday(df.parse(date));
		for (Integer integer : integers) {
			EmployeeSalary salary = dao.findSingleSlipData(integer);
			EmployeeSalaryDTO dto = new EmployeeSalaryDTO();
			dto.setSlipNo(salary.getSlipNo());
			dto.setSalaryAmount(salary.getSalaryAmount());
			dto.setPaidBy(salary.getPaidBy());
			dto.setIncentive(salary.getIncentive());
			dto.setPaidAmountInWord(salary.getPaidAmountInWord());
			dto.setPaidAmount(String.valueOf(salary.getPaidAmount()));
			dto.setNetPayableAmount(String.valueOf(salary.getPaidAmount()
					+ salary.getDue()));
			dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
			dto.setDueAmount(String.valueOf(salary.getDue()));
			dto.setFirstName(salary.getEmployees().getFirstName());
			dto.setLastName(salary.getEmployees().getLastName());
			dto.setEmployeeId(salary.getEmployees().getEmployeeId());
			dto.setSalaryPaidStatus(salary.getSalaryPaidStatus());
			dto.setFineAmount(String.valueOf(salary.getFine()));
			if (salary.getPaidBy().equals("cheque")) {
				dto.setBankName(salary.getBankName());
				dto.setChequeNo(salary.getChequeNo());
			}
			else
			{
				dto.setBankName("-");
				dto.setChequeNo("-");
			}
			List<Integer> monthsdata = dao.findPayedMonthsfromSlip(salary
					.getSlipNo());
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int current = cal.get(Calendar.MONTH) + 1;

			if (current > 2)
				current = current - 2;
			else
				current = current + 10;
			current = current - 1;
			if (current == 0)
				current = 12;
			String names = "";
			int status = 0;

			for (int months : monthsdata) {
				names += monthDao.get(months).getMonthName() + " \t";

				if (months > current) {
					status++;
				}

			}
			dto.setMonth(names);
			if (status > 0) {
				dto.setAdvance("Advance for " + String.valueOf(status)
						+ " month");
			} else {
				dto.setAdvance("Salary");
			}
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<EmployeeSalaryDTO> findMonthlyPayments(int monthId) throws ParseException {
		Session session = sessionDao.findCurrentSession();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeSalaryDTO> listdto = new ArrayList<EmployeeSalaryDTO>();
		List<Integer> integers = dao.getallDistinctSlipNoByMonth(
				monthId,session.getSessionId());
		for (Integer integer : integers) {
			EmployeeSalary salary = dao.findSingleSlipData(integer);
			EmployeeSalaryDTO dto = new EmployeeSalaryDTO();
			dto.setSlipNo(salary.getSlipNo());
			dto.setSalaryAmount(salary.getSalaryAmount());
			dto.setPaidBy(salary.getPaidBy());
			dto.setIncentive(salary.getIncentive());
			dto.setPaidAmountInWord(salary.getPaidAmountInWord());
			dto.setPaidAmount(String.valueOf(salary.getPaidAmount()));
			dto.setNetPayableAmount(String.valueOf(salary.getPaidAmount()
					+ salary.getDue()));
			dto.setDateOfPayment(df.format(salary.getDateOfPayment()));
			dto.setDueAmount(String.valueOf(salary.getDue()));
			dto.setFirstName(salary.getEmployees().getFirstName());
			dto.setLastName(salary.getEmployees().getLastName());
			dto.setEmployeeId(salary.getEmployees().getEmployeeId());
			dto.setSalaryPaidStatus(salary.getSalaryPaidStatus());
			dto.setFineAmount(String.valueOf(salary.getFine()));
			if (salary.getPaidBy().equals("cheque")) {
				dto.setBankName(salary.getBankName());
				dto.setChequeNo(salary.getChequeNo());
			}
			else
			{
				dto.setBankName("-");
				dto.setChequeNo("-");
			}
			List<Integer> monthsdata = dao.findPayedMonthsfromSlip(salary
					.getSlipNo());
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int current = cal.get(Calendar.MONTH) + 1;

			if (current > 2)
				current = current - 2;
			else
				current = current + 10;
			current = current - 1;
			if (current == 0)
				current = 12;
			String names = "";
			int status = 0;

			for (int months : monthsdata) {
				names += monthDao.get(months).getMonthName() + " \t";

				if (months > current) {
					status++;
				}

			}
			dto.setMonth(names);
			if (status > 0) {
				dto.setAdvance("Advance for " + String.valueOf(status)
						+ " month");
			} else {
				dto.setAdvance("Salary");
			}
			listdto.add(dto);
		}
		return listdto;
	}

}
