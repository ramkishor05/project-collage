package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.brijframework.college.dao.EmployeeAttendanceDao;
import org.brijframework.college.dao.EmployeeDao;
import org.brijframework.college.dao.HolidayDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.model.EmployeeAttendance;
import org.brijframework.college.model.Employees;
import org.brijframework.college.model.Holiday;
import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.EmployeeAttendanceDTO;
import org.brijframework.college.models.dto.EmployeesDTO;
import org.brijframework.college.models.dto.HolidayDTO;
import org.brijframework.college.service.EmployeeAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeAttendanceServiceImpl extends
		CRUDServiceImpl<Integer, EmployeeAttendance, EmployeeAttendanceDao>
		implements EmployeeAttendanceService {

	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	SessionDao sessionDao;
	@Autowired
	HolidayDao holidayDao;

	@Autowired
	public EmployeeAttendanceServiceImpl(EmployeeAttendanceDao dao) {
		super(dao);
	}

	@Override
	public List<EmployeesDTO> getEmployeeRegister() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -6);
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);
		Date date = new Date();
		List<EmployeesDTO> employeesDTOs = new ArrayList<EmployeesDTO>();
		for (Employees employee : dao.findAll(Employees.class)) {
			EmployeesDTO employeesDTO = new EmployeesDTO();
			List<EmployeeAttendanceDTO> employeeAttendanceDTOs = new ArrayList<EmployeeAttendanceDTO>();
			for (EmployeeAttendance employeeAttendance : dao
					.getRegisterByEmployeeId(employee.getEmployeeId(),
							df.parse(df.format(cal.getTime())),
							df.parse(df.format(cal1.getTime())))) {
				EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO();
				employeeAttendanceDTO.setAttendanceStatus(employeeAttendance
						.getAttendanceStatus());
				employeeAttendanceDTO.setDateOfAttendance(df
						.format(employeeAttendance.getDateOfAttendance()));
				employeeAttendanceDTO
						.setEmployeeAttendanceId(employeeAttendance
								.getEmployeeAttendanceId());
				if (df.format(employeeAttendance.getDateOfAttendance())
						.equalsIgnoreCase(df.format(date))) {
					employeeAttendanceDTO.setStatus("true");
				} else {
					employeeAttendanceDTO.setStatus("false");
				}
				employeeAttendanceDTOs.add(employeeAttendanceDTO);
			}
			employeesDTO.setFullName(employee.getFirstName() + " "
					+ employee.getLastName());
			employeesDTO.setId(employee.getEmployeeId());
			employeesDTO.setEmployeeAttendanceDTOs(employeeAttendanceDTOs);
			employeesDTOs.add(employeesDTO);
		}
		return employeesDTOs;
	}

	@Override
	public void setEmployeeAttendance(int id, String status, String currentDate)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = dateFormat.parse(currentDate);
		java.sql.Date sDate = new java.sql.Date(myDate.getTime());
		EmployeeAttendance attendance = dao.getAttendanceReportByIdDate(id,
				sDate);
		if (attendance == null) {
			EmployeeAttendance employeeAttendance = new EmployeeAttendance();
			Employees employees = new Employees();
			employees.setEmployeeId(id);
			employeeAttendance.setEmployees(employees);
			employeeAttendance.setAttendanceStatus(status);
			employeeAttendance.setDateOfAttendance(sDate);
			dao.create(employeeAttendance);
		} else {
			attendance.setAttendanceStatus(status);
			dao.update(attendance);
		}
	}

	@Override
	public List<EmployeesDTO> showEmployeeAttendanceRecord(String fromDate,
			String toDate) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date frmDate = dateFormat.parse(fromDate);
		Date tDate = dateFormat.parse(toDate);
		List<EmployeesDTO> employeesDTOs = new ArrayList<EmployeesDTO>();
		for (Employees employee : dao.findAllByIsActiveTrue(Employees.class)) {
			int totalNoOfDays = 0;
			int noOfDaysPresent = 0;
			int noOfDaysAbsent = 0;
			int late = 0;
			int leave = 0;
			double percentageOfAttendance = 0;
			EmployeesDTO employeesDTO = new EmployeesDTO();
			List<EmployeeAttendanceDTO> employeeAttendanceDTOs = new ArrayList<EmployeeAttendanceDTO>();
			for (EmployeeAttendance employeeAttendance : dao
					.getEmployeeAttendanceList(employee.getEmployeeId(),
							frmDate, tDate)) {
				totalNoOfDays++;
				EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO();
				String status = employeeAttendance.getAttendanceStatus();
				employeeAttendanceDTO.setAttendanceStatus(status);
				employeeAttendanceDTO.setDateOfAttendance(dateFormat
						.format(employeeAttendance.getDateOfAttendance()));
				employeeAttendanceDTO.setFirstName(employee.getFirstName());
				employeeAttendanceDTO
						.setEmployeeAttendanceId(employeeAttendance
								.getEmployeeAttendanceId());
				if (status.equalsIgnoreCase("PRESENT")) {
					noOfDaysPresent += 1;
				}
				if (status.equalsIgnoreCase("ABSENT")) {
					noOfDaysAbsent += 1;
				}
				if (status.equalsIgnoreCase("LATE")) {
					late += 1;
				}
				if (status.equalsIgnoreCase("LEAVE")) {
					leave += 1;
				}

				percentageOfAttendance = Math
						.round(((double) (noOfDaysPresent + late) / totalNoOfDays) * 100);
				employeeAttendanceDTO.setTotalLate(late);
				employeeAttendanceDTO.setTotalLeave(leave);
				employeeAttendanceDTO.setTotalNoOfDays(totalNoOfDays);
				employeeAttendanceDTO.setNoOfDaysPresent(noOfDaysPresent);
				employeeAttendanceDTO.setNoOfDaysAbsent(noOfDaysAbsent);
				employeeAttendanceDTO
						.setPercentageOfAttendance(percentageOfAttendance);
				employeeAttendanceDTOs.add(employeeAttendanceDTO);
			}
			employeesDTO.setFrom(fromDate);
			employeesDTO.setTo(toDate);
			employeesDTO.setFullName(employee.getFirstName() + " "
					+ employee.getLastName());
			employeesDTO.setTotalNoOfDays(totalNoOfDays);
			employeesDTO.setNoOfDaysPresent(noOfDaysPresent);
			employeesDTO.setNoOfDaysAbsent(noOfDaysAbsent);
			employeesDTO.setPercentageOfAttendance(percentageOfAttendance);
			employeesDTO.setId(employee.getEmployeeId());
			employeesDTO.setEmployeeAttendanceDTOs(employeeAttendanceDTOs);
			employeesDTOs.add(employeesDTO);
		}
		return employeesDTOs;

	}

	@Override
	public List<EmployeesDTO> showEmployeeAttendanceRecordAsPdf(
			String fromDate, String toDate, int empId) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date frmDate = dateFormat.parse(fromDate);

		Date tDate = dateFormat.parse(toDate);
		List<EmployeesDTO> employeesDTOs = new ArrayList<EmployeesDTO>();
		Employees employee = employeeDao.get(empId);
		int totalNoOfDays = 0;
		int noOfDaysPresent = 0;
		int noOfDaysAbsent = 0;
		int late = 0;
		int leave = 0;
		double percentageOfAttendance = 0;
		EmployeesDTO employeesDTO = new EmployeesDTO();
		List<EmployeeAttendanceDTO> employeeAttendanceDTOs = new ArrayList<EmployeeAttendanceDTO>();
		for (EmployeeAttendance employeeAttendance : dao
				.getEmployeeAttendanceList(employee.getEmployeeId(), frmDate,
						tDate)) {
			totalNoOfDays++;
			EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO();
			String status = employeeAttendance.getAttendanceStatus();
			employeeAttendanceDTO.setAttendanceStatus(status);
			employeeAttendanceDTO.setDateOfAttendance(dateFormat
					.format(employeeAttendance.getDateOfAttendance()));
			employeeAttendanceDTO.setFirstName(employee.getFirstName());
			employeeAttendanceDTO.setEmployeeAttendanceId(employeeAttendance
					.getEmployeeAttendanceId());
			if (status.equalsIgnoreCase("PRESENT")) {
				noOfDaysPresent += 1;
			}
			if (status.equalsIgnoreCase("ABSENT")) {
				noOfDaysAbsent += 1;
			}
			if (status.equalsIgnoreCase("LATE")) {
				late += 1;
			}
			if (status.equalsIgnoreCase("LEAVE")) {
				leave += 1;
			}
			employeeAttendanceDTO.setTotalLate(late);
			employeeAttendanceDTO.setTotalLeave(leave);
			employeeAttendanceDTO.setTotalNoOfDays(totalNoOfDays);
			employeeAttendanceDTO.setNoOfDaysPresent(noOfDaysPresent);
			employeeAttendanceDTO.setNoOfDaysAbsent(noOfDaysAbsent);
			employeeAttendanceDTO
					.setPercentageOfAttendance(percentageOfAttendance);
			employeeAttendanceDTOs.add(employeeAttendanceDTO);
		}
		percentageOfAttendance = Math
				.round(((double) (noOfDaysPresent + late) / totalNoOfDays) * 100);
		employeesDTO.setFrom(fromDate);
		employeesDTO.setTo(toDate);
		employeesDTO.setFullName(employee.getFirstName() + " "
				+ employee.getLastName());
		employeesDTO.setTotalNoOfDays(totalNoOfDays);
		employeesDTO.setNoOfDaysPresent(noOfDaysPresent);
		employeesDTO.setNoOfDaysAbsent(noOfDaysAbsent);
		employeesDTO.setPercentageOfAttendance(percentageOfAttendance);
		employeesDTO.setId(employee.getEmployeeId());
		employeesDTO.setEmployeeAttendanceDTOs(employeeAttendanceDTOs);
		employeesDTOs.add(employeesDTO);
		return employeesDTOs;
	}

	@Override
	public List<EmployeeAttendanceDTO> getMonthlyReport(Integer id, int monthId)
			throws ParseException {
		if (monthId > 9)
			monthId = monthId - 9;
		else
			monthId = monthId + 3;
		Session session = sessionDao.findCurrentSession();
		String currentSession = session.getSessionDuration();
		String year[] = currentSession.split("-");
		int currentYear;
		int present = 0;
		if (monthId < 4) {
			currentYear = Integer.parseInt(year[1]);
		} else {
			currentYear = Integer.parseInt(year[0]);
		}
		GregorianCalendar gc = new GregorianCalendar(currentYear, monthId - 1,
				1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(currentYear, monthId - 1, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		Date fromDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		Date toDate = df.parse(df.format(calendar.getTime()));
		calendar.setTime(toDate);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTime(fromDate);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		while (dayOfWeek != 1) {
			calendar.add(Calendar.DATE, 1);
			dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		}
		List<Integer> sundays = new ArrayList<Integer>();
		int k = calendar.get(Calendar.DAY_OF_MONTH);
		for (int s = k; s <= day; s = s + 7) {
			sundays.add(s);
		}
		List<EmployeeAttendanceDTO> dtos = new ArrayList<EmployeeAttendanceDTO>();
		List<EmployeeAttendance> attendances = dao.getEmployeeAttendanceList(
				id, fromDate, toDate);

		int total = attendances.size();
		double percent = 0;
		for (int i = 1; i <= day; i++) {
			EmployeeAttendanceDTO dto = new EmployeeAttendanceDTO();
			if (sundays.contains(i)) {
				dto.setAttendanceStatus("s");
			} else {
				for (EmployeeAttendance attendance : attendances) {
					calendar.setTime(attendance.getDateOfAttendance());
					int last = calendar.get(Calendar.DAY_OF_MONTH);
					if (last == i) {
						dto.setAttendanceStatus(attendance
								.getAttendanceStatus());
						if ((attendance.getAttendanceStatus()
								.equalsIgnoreCase("PRESENT"))
								|| (attendance.getAttendanceStatus()
										.equalsIgnoreCase("LATE"))) {
							present++;
						}
						break;

					} else {
						dto.setAttendanceStatus(" ");
					}
				}
			}
			percent = Math.round(((double) present / total) * 100);
			dto.setNoOfDaysPresent(present);
			dto.setTotalNoOfDays(total);
			dto.setPercentageOfAttendance(percent);
			dto.setDateOfAttendance(String.valueOf(i));
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<EmployeeAttendanceDTO> getCurrentDateData(String currentDate)
			throws ParseException {
		List<EmployeeAttendanceDTO> employeeAttendanceDTOs = new ArrayList<EmployeeAttendanceDTO>();
		List<Employees> employeeList = employeeDao.getEmployee(true);
		// variable
		int totalPresent = 0;
		int totalAbsent = 0;
		int totalLate = 0;
		int totalLeave = 0;
		// end
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = dateFormat.parse(currentDate);
		java.sql.Date sDate = new java.sql.Date(myDate.getTime());
		if (!employeeList.isEmpty() && employeeList != null) {
			EmployeeAttendanceDTO totalEmployee = new EmployeeAttendanceDTO();
			totalEmployee.setStatus("Total Employees");
			totalEmployee.setTotalNoOfDays(employeeList.size());
			employeeAttendanceDTOs.add(totalEmployee);
		}

		for (Employees employees : employeeList) {
			EmployeeAttendance employeeAttendance = dao
					.getAttendanceReportByIdDate(employees.getEmployeeId(),
							sDate);
			if (employeeAttendance != null) {
				if (employeeAttendance.getAttendanceStatus().equals("PRESENT")) {
					totalPresent++;
				}
				if (employeeAttendance.getAttendanceStatus().equals("LATE")) {
					totalLate++;
				}
				if (employeeAttendance.getAttendanceStatus().equals("LEAVE")) {
					totalLeave++;
				}
				if (employeeAttendance.getAttendanceStatus().equals("ABSENT")) {
					totalAbsent++;
				}
			} else {
				totalAbsent++;
			}
		}

		// set data in list
		// present employee
		EmployeeAttendanceDTO presentEmployee = new EmployeeAttendanceDTO();
		presentEmployee.setStatus("Total Present");
		presentEmployee.setTotalNoOfDays(totalPresent);
		employeeAttendanceDTOs.add(presentEmployee);
		// end
		// absent employee
		EmployeeAttendanceDTO absentEmployee = new EmployeeAttendanceDTO();
		absentEmployee.setStatus("Total Absent");
		absentEmployee.setTotalNoOfDays(totalAbsent);
		employeeAttendanceDTOs.add(absentEmployee);
		// end
		// late employee
		EmployeeAttendanceDTO lateEmployee = new EmployeeAttendanceDTO();
		lateEmployee.setStatus("Total Late");
		lateEmployee.setTotalNoOfDays(totalLate);
		employeeAttendanceDTOs.add(lateEmployee);
		// end
		// on leave
		EmployeeAttendanceDTO leaveEmployee = new EmployeeAttendanceDTO();
		leaveEmployee.setStatus("Total Leave");
		leaveEmployee.setTotalNoOfDays(totalLeave);
		employeeAttendanceDTOs.add(leaveEmployee);
		// end
		// end

		return employeeAttendanceDTOs;
	}

	@Override
	public List<EmployeeAttendanceDTO> getEmployeeSummuary()
			throws ParseException {

		List<Employees> employeeList = employeeDao.getEmployee(true);
		List<EmployeeAttendanceDTO> employeeAttendanceDTOs = new ArrayList<EmployeeAttendanceDTO>();
		// setting dates for getting data
		// get distinct attendance date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Integer startMonth[] = { 1, 2, 3 };
		int month = calendar.get(Calendar.MONTH)+1;
		int fromYear = 0;
		Date fromDate = new Date();
		if (Arrays.asList(startMonth).contains(month)) {
			calendar.add(Calendar.YEAR, -1);
			fromYear = calendar.get(Calendar.YEAR);
			fromDate = dateFormat.parse("" + fromYear + "-04-01");
		} else {
			fromYear = calendar.get(Calendar.YEAR);
			fromDate = dateFormat.parse("" + fromYear + "-04-01");
		}
		// to date
		Date toDate = dateFormat.parse(dateFormat.format(new Date()));
		List<Date> dates = dao.totalSchoolOpenDays(fromDate, toDate);
		// end setting dates

		// schoolSessionStartingDate
		Date schoolSessionStartingDate = new Date();
		if (!dates.isEmpty() && dates != null)
			schoolSessionStartingDate = dates.get(0);

		for (Employees employees : employeeList) {
			List<EmployeeAttendance> employeeAttendances = dao
					.getEmployeeAttendanceList(employees.getEmployeeId(),
							schoolSessionStartingDate, new Date());
			EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO();
			int totalPresent = 0;
			int totalAbsent = 0;
			int totalLate = 0;
			int totalLeave = 0;
			for (EmployeeAttendance employeeAttendance : employeeAttendances) {
				if (employeeAttendance.getAttendanceStatus().equals("PRESENT")) {
					totalPresent++;
				}
				if (employeeAttendance.getAttendanceStatus().equals("LATE")) {
					totalLate++;
				}
				if (employeeAttendance.getAttendanceStatus().equals("LEAVE")) {
					totalLeave++;
				}
				if (employeeAttendance.getAttendanceStatus().equals("ABSENT")) {
					totalAbsent++;
				}
			}
			employeeAttendanceDTO.setFirstName(employees.getFirstName() + " "
					+ employees.getLastName());
			employeeAttendanceDTO.setTotalNoOfDays(dates.size());
			employeeAttendanceDTO.setNoOfDaysAbsent(totalAbsent);
			employeeAttendanceDTO.setNoOfDaysLatet(totalLate);
			employeeAttendanceDTO.setNoOfDaysLeave(totalLeave);
			employeeAttendanceDTO.setNoOfDaysPresent(totalPresent);
			employeeAttendanceDTOs.add(employeeAttendanceDTO);
		}

		return employeeAttendanceDTOs;
	}

	@Override
	public Map<String, Object> getAttendaceDataForEmp(int empId, int monthId,
			int year) throws ParseException {
		int MONTHID = 0;
		int YEAR = 0;
		Calendar calendar = Calendar.getInstance();
		List<Integer> holidayList = new ArrayList<Integer>();
		Map<String, Object> map = new HashMap<String, Object>();
		Date firstOfDateMonth = new Date();
		Date lastOfDateMonth = new Date();
		calendar.setTime(new Date());
		if (monthId == 14 && year == 0) {
			calendar.setTime(new Date());
			MONTHID = calendar.get(Calendar.MONTH) + 1;
			YEAR = calendar.get(Calendar.YEAR);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			firstOfDateMonth = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			lastOfDateMonth = calendar.getTime();
			holidayList = holidyalist(firstOfDateMonth, lastOfDateMonth);
		} else {
			String date1 = "";
			if (monthId == 0) {
				date1 = (year - 1) + "-" + 12 + "-" + 01;
			} else if (monthId == 13) {
				date1 = (year + 1) + "-" + 1 + "-" + 01;
			} else {
				date1 = (year) + "-" + monthId + "-" + 01;
			}
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
			calendar.setTime(date);
			MONTHID = calendar.get(Calendar.MONTH) + 1;
			YEAR = calendar.get(Calendar.YEAR);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			firstOfDateMonth = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			lastOfDateMonth = calendar.getTime();
			holidayList = holidyalist(firstOfDateMonth, lastOfDateMonth);
		}
		map = getEmployeeAttendace(empId, firstOfDateMonth, lastOfDateMonth,
				holidayList);
		map.put("YEAR", YEAR);
		map.put("MONTHID", MONTHID);
		return map;

	}

	private Map<String, Object> getEmployeeAttendace(int empid,
			Date firstOfDateMonth, Date lastOfDateMonth,
			List<Integer> holidayList) throws ParseException {
		List<EmployeeAttendance> employeeAttendances = dao
				.getRegisterByEmployeeId(empid, firstOfDateMonth,
						lastOfDateMonth);
		List<Integer> employeeAttendanceDays = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		for (EmployeeAttendance employeeAttendance : employeeAttendances) {
			Date holidayDate = employeeAttendance.getDateOfAttendance();
			calendar.setTime(holidayDate);
			employeeAttendanceDays.add(calendar.get(Calendar.DAY_OF_MONTH));
		}
		return generateDateList(firstOfDateMonth, holidayList,
				employeeAttendanceDays, empid);
	}

	private Map<String, Object> generateDateList(Date firstOfDateMonth,
			List<Integer> holidayList, List<Integer> employeeAttendanceDays,
			int empId) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstOfDateMonth);
		int month = calendar.get(Calendar.MONTH);
		int totalMonthDays = 0;
		int totalSundays = 0;
		int totalPresent = 0;
		int totalAbsent = 0;
		int totalLate = 0;
		int totalLeave = 0;

		// Set month of Year
		map.put("MONTHOfYEAR",
				new SimpleDateFormat("yyyy-MMM").format(calendar.getTime()));

		List<HolidayDTO> holidayDTOs = new ArrayList<HolidayDTO>();
		// extra fields
		int days = calendar.get(Calendar.DAY_OF_WEEK);
		for (int i = 1; i < days; i++) {
			HolidayDTO holidayDTO = new HolidayDTO();
			holidayDTO.setHolidayCount("0");
			holidayDTOs.add(holidayDTO);
		}
		do {
			int day = calendar.get(Calendar.DAY_OF_WEEK);
			HolidayDTO holidayDTO = new HolidayDTO();
			holidayDTO.setHolidayDate(new SimpleDateFormat("dd-MMM-yyyy")
					.format(calendar.getTime()));
			holidayDTO.setHolidayCount(String.valueOf(calendar
					.get(Calendar.DAY_OF_MONTH)));
			totalMonthDays = calendar.get(Calendar.DAY_OF_MONTH);
			if (day == Calendar.SUNDAY) {
				if (employeeAttendanceDays.contains(calendar
						.get(Calendar.DAY_OF_MONTH))) {
					String date1 = calendar.get(calendar.YEAR) + "-"
							+ (month + 1) + "-"
							+ calendar.get(Calendar.DAY_OF_MONTH);
					EmployeeAttendance employeeAttendance = dao
							.getAttendanceReportByIdDate(empId,
									new java.sql.Date(new SimpleDateFormat(
											"yyyy-MM-dd").parse(date1)
											.getTime()));

					if (employeeAttendance.getAttendanceStatus().equals(
							"PRESENT")) {
						totalPresent = totalPresent + 1;
					}
					if (employeeAttendance.getAttendanceStatus().equals(
							"ABSENT")) {
						totalAbsent = totalAbsent + 1;
					}
					if (employeeAttendance.getAttendanceStatus().equals("LATE")) {
						totalLate = totalLate + 1;
					}
					if (employeeAttendance.getAttendanceStatus()
							.equals("LEAVE")) {
						totalLeave = totalLeave + 1;
					}
					holidayDTO.setDescription("Sunday");
					holidayDTO.setHolidayId(0);
					holidayDTO.setDes(employeeAttendance.getAttendanceStatus());

				} else {
					holidayDTO.setDescription("Sunday");
					holidayDTO.setHolidayId(0);
					holidayDTO.setDes("");
				}

				totalSundays = totalSundays + 1;
				/*
				 * holidayDTO.setDescription("Sunday");
				 * holidayDTO.setHolidayId(0);
				 */

			} else if (holidayList
					.contains(calendar.get(Calendar.DAY_OF_MONTH))) {
				String date1 = calendar.get(calendar.YEAR) + "-" + (month + 1)
						+ "-" + calendar.get(Calendar.DAY_OF_MONTH);
				Holiday holiday = holidayDao
						.getDataForCreateHoliday(new SimpleDateFormat(
								"yyyy-MM-dd").parse(date1));
				holidayDTO.setDescription(holiday.getDescription());
				holidayDTO.setHolidayId(holiday.getHolidayId());
			} else if (employeeAttendanceDays.contains(calendar
					.get(Calendar.DAY_OF_MONTH))) {
				String date1 = calendar.get(calendar.YEAR) + "-" + (month + 1)
						+ "-" + calendar.get(Calendar.DAY_OF_MONTH);
				EmployeeAttendance employeeAttendance = dao
						.getAttendanceReportByIdDate(empId, new java.sql.Date(
								new SimpleDateFormat("yyyy-MM-dd").parse(date1)
										.getTime()));
				if (employeeAttendance.getAttendanceStatus().equals("PRESENT")) {
					totalPresent = totalPresent + 1;
				}
				if (employeeAttendance.getAttendanceStatus().equals("ABSENT")) {
					totalAbsent = totalAbsent + 1;
				}
				if (employeeAttendance.getAttendanceStatus().equals("LATE")) {
					totalLate = totalLate + 1;
				}
				if (employeeAttendance.getAttendanceStatus().equals("LEAVE")) {
					totalLeave = totalLeave + 1;
				}
				holidayDTO.setDescription(employeeAttendance
						.getAttendanceStatus());
				holidayDTO.setHolidayId(0);

			} else {
				holidayDTO.setDescription("");
				holidayDTO.setHolidayId(0);
			}
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			holidayDTOs.add(holidayDTO);
		} while (calendar.get(Calendar.MONTH) == month);
		map.put("TOTALWORKINGDAYS",
				(totalMonthDays - (totalSundays + holidayList.size())));
		map.put("TOTALPRESENT", totalPresent);
		map.put("TOTALABSENT", totalAbsent);
		map.put("TOTALLATE", totalLate);
		map.put("TOTALLEAVE", totalLeave);
		map.put("HOLIDAYSLIST", holidayDTOs);
		return map;
	}

	private List<Integer> holidyalist(Date firstOfDateMonth,
			Date lastOfDateMonth) {
		List<Holiday> holidays = holidayDao.getDataForCreateHoliday(
				firstOfDateMonth, lastOfDateMonth);
		Calendar calendar = Calendar.getInstance();
		List<Integer> integers = new ArrayList<Integer>();
		for (Holiday holiday : holidays) {
			Date holidayDate = holiday.getHolidayDate();
			calendar.setTime(holidayDate);
			integers.add(calendar.get(Calendar.DAY_OF_MONTH));
		}
		return integers;
	}

}
