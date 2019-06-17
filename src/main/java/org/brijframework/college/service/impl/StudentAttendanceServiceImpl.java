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

import org.brijframework.college.dao.HolidayDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.dao.StudentAttendanceDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.model.Employees;
import org.brijframework.college.model.Holiday;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.StudentAttendance;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.Students;
import org.brijframework.college.models.dto.SessionDTO;
import org.brijframework.college.models.dto.StudentAttendanceDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.service.MonthService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("studentAttendanceService")
@SuppressWarnings("unused")
public class StudentAttendanceServiceImpl extends
		CRUDServiceImpl<Integer, StudentAttendance, StudentAttendanceDao>
		implements StudentAttendanceService {

	@Autowired
	public StudentAttendanceServiceImpl(StudentAttendanceDao dao) {
		super(dao);
	}

	@Autowired
	SessionDao sessionDao;
	@Autowired
	StudentAttendanceDao studentAttendanceDao;
	@Autowired
	private StudentsAdmissionDao studentsAdmissionDao;
	@Autowired
	MonthService monthService;
	@Autowired
	private HolidayDao holidayDao;

	public void setAttendanceRecord(String id, String status, int classId,
			int sectionId, int employeeId, String currentDate)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = dateFormat.parse(currentDate);
		java.sql.Date sDate = new java.sql.Date(myDate.getTime());
		if (employeeId == 1) {
			StudentAttendance attendance = dao.getAttendanceReportByIdDate(id,
					sDate);
			if (attendance == null) {
				StudentAttendance attendanceNew = new StudentAttendance();
				Students students = new Students();
				Employees employees = new Employees();
				employees.setEmployeeId(employeeId);
				StudentClasses studentClasses = new StudentClasses();
				Section section = new Section();
				studentClasses.setClassId(classId);
				students.setStudentAdmissionNo(id);
				section.setSectionId(sectionId);
				attendanceNew.setStudents(students);
				attendanceNew.setEmployees(employees);
				attendanceNew.setStudentClasses(studentClasses);
				attendanceNew.setSection(section);
				attendanceNew.setAttendanceStatus(status);
				attendanceNew.setDatOfAttendance(myDate);
				dao.create(attendanceNew);
			} else {
				attendance.setAttendanceStatus(status);
				dao.update(attendance);
			}
		} else {
			StudentAttendance attendance = dao.getAttendanceReportById(id,
					employeeId, sDate);
			if (attendance == null) {
				StudentAttendance attendanceNew = new StudentAttendance();
				Students students = new Students();
				Employees employees = new Employees();
				employees.setEmployeeId(employeeId);
				StudentClasses studentClasses = new StudentClasses();
				Section section = new Section();
				studentClasses.setClassId(classId);
				students.setStudentAdmissionNo(id);
				section.setSectionId(sectionId);
				attendanceNew.setStudents(students);
				attendanceNew.setEmployees(employees);
				attendanceNew.setStudentClasses(studentClasses);
				attendanceNew.setSection(section);
				attendanceNew.setAttendanceStatus(status);
				attendanceNew.setDatOfAttendance(myDate);
				dao.create(attendanceNew);
			} else {
				attendance.setAttendanceStatus(status);
				dao.update(attendance);
			}
		}
	}

	@Autowired
	SessionService sessionService;

	@Override
	public List<StudentDTO> getWeeklyAttendanceRegister(int classId,
			int sectionId) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -6);
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);
		Date date = new Date();
		List<StudentDTO> dtos = new ArrayList<StudentDTO>();
		SessionDTO sessionDTO = sessionService.findCurrent();
		for (Students students : studentsAdmissionDao
				.getStudentsbyClassSectionId(classId, sectionId,
						sessionDTO.getSessionId())) {
			StudentDTO studentDTO = new StudentDTO();
			List<StudentAttendanceDTO> attendanceDTOs = new ArrayList<StudentAttendanceDTO>();
			for (StudentAttendance attendance : dao
					.getStudentAttendanceDayByDay(classId, sectionId,
							students.getStudentAdmissionNo(),
							df.parse(df.format(cal.getTime())),
							df.parse(df.format(cal1.getTime())))) {
				StudentAttendanceDTO dto = new StudentAttendanceDTO();
				dto.setAttendanceStatus(attendance.getAttendanceStatus());
				dto.setAttendanceDate(df.format(attendance.getDatOfAttendance()));
				dto.setId(attendance.getStudentAttendenceId());
				if (df.format(attendance.getDatOfAttendance())
						.equalsIgnoreCase(df.format(date))) {
					dto.setStatus("true");
				} else {
					dto.setStatus("false");
				}
				attendanceDTOs.add(dto);
			}
			studentDTO.setFullName(students.getFirstName() + " "
					+ students.getMiddleName() + " " + students.getLastName());
			studentDTO.setAdmissionNo(students.getStudentAdmissionNo());
			studentDTO.setStudentAttendanceDTOs(attendanceDTOs);
			dtos.add(studentDTO);
		}
		return dtos;
	}

	public List<StudentAttendanceDTO> getStudentAttendance(String studentId,
			int monthId) throws ParseException {
		if (monthId > 9)
			monthId = monthId - 9;
		else
			monthId = monthId + 3;
		Session session = sessionDao.findCurrentSession();
		String currentSession = session.getSessionDuration();
		String year[] = currentSession.split("-");
		int currentYear;
		int present = 0;
		int absent = 0;
		int late = 0;
		int leave = 0;
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
		List<StudentAttendanceDTO> dtos = new ArrayList<StudentAttendanceDTO>();
		List<StudentAttendance> attendances = studentAttendanceDao
				.getStudentAttendanceBystudentId(studentId, fromDate, toDate);

		int total = attendances.size();
		double percent = 0;
		for (int i = 1; i <= day; i++) {
			StudentAttendanceDTO dto = new StudentAttendanceDTO();
			if (sundays.contains(i)) {
				dto.setAttendanceStatus("s");
			} else {
				for (StudentAttendance attendance : attendances) {
					calendar.setTime(attendance.getDatOfAttendance());
					int last = calendar.get(Calendar.DAY_OF_MONTH);
					if (last == i) {
						dto.setAttendanceStatus(attendance
								.getAttendanceStatus());
						if (attendance.getAttendanceStatus().equalsIgnoreCase(
								"PRESENT")) {
							present += 1;
						}
						if (attendance.getAttendanceStatus().equalsIgnoreCase(
								"ABSENT")) {
							absent += 1;
						}
						if (attendance.getAttendanceStatus().equalsIgnoreCase(
								"LATE")) {
							late += 1;
						}
						if (attendance.getAttendanceStatus().equalsIgnoreCase(
								"LEAVE")) {
							leave += 1;
						}
						break;
					} else {
						dto.setAttendanceStatus(" ");

					}

				}
			}
			percent = Math.round(((double) (present + late) / total) * 100);
			dto.setTotalPresent(present);
			dto.setTotalAttendance(total);
			dto.setTotalLate(late);
			dto.setTotalLeave(leave);
			dto.setTotalAbsent(absent);
			dto.setPercent(percent);
			dto.setAttendanceDate(String.valueOf(i));
			dtos.add(dto);
		}
		return dtos;
	}

	private List<Integer> holidaylist(Date firstOfDateMonth,
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

	@Override
	public List<StudentDTO> getStudentsAttendanceList(int classId,
			int sectionId, int monthId) throws ParseException {
		if (monthId > 9)
			monthId = monthId - 9;
		else
			monthId = monthId + 3;
		Session session = sessionDao.findCurrentSession();
		String currentSession = session.getSessionDuration();
		String year[] = currentSession.split("-");
		int currentYear;
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
		// get holidays list
		List<Integer> holidayList = holidaylist(fromDate, toDate);
		// end
		List<Integer> sundays = new ArrayList<Integer>();
		int k = calendar.get(Calendar.DAY_OF_MONTH);
		for (int s = k; s <= day; s = s + 7) {
			sundays.add(s);
		}

		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		for (Students student : studentsAdmissionDao
				.getStudentsbyClassSectionId(classId, sectionId,
						session.getSessionId())) {

			StudentDTO studentDTO = new StudentDTO();
			List<StudentAttendance> attendances = studentAttendanceDao
					.getStudentAttendanceBystudentId(
							student.getStudentAdmissionNo(), fromDate, toDate);
			int total = attendances.size();
			List<StudentAttendanceDTO> dtos = new ArrayList<StudentAttendanceDTO>();
			double percent = 0;
			int present = 0;
			int absent = 0;
			int late = 0;
			int leave = 0;
			for (int i = 1; i <= day; i++) {
				StudentAttendanceDTO dto = new StudentAttendanceDTO();
				 if (holidayList.contains(i)) {
					String date1 = calendar.get(calendar.YEAR) + "-"
							+ (monthId) + "-" + i;
					Holiday holiday = holidayDao
							.getDataForCreateHoliday(new SimpleDateFormat(
									"yyyy-MM-dd").parse(date1));
					dto.setAttendanceStatus(holiday.getDescription());
				} else {
					for (StudentAttendance attendance : attendances) {
						calendar.setTime(attendance.getDatOfAttendance());
						int last = calendar.get(Calendar.DAY_OF_MONTH);
						if (sundays.contains(i)) {
							dto.setStatus("s");
							dto.setAttendanceStatus("");
						}
						else {
							dto.setStatus(" ");
						}
						if (last == i) {
							dto.setAttendanceStatus(attendance
									.getAttendanceStatus());
							if (attendance.getAttendanceStatus()
									.equalsIgnoreCase("PRESENT")) {
								present += 1;
							}
							if (attendance.getAttendanceStatus()
									.equalsIgnoreCase("ABSENT")) {
								absent += 1;
							}
							if (attendance.getAttendanceStatus()
									.equalsIgnoreCase("LATE")) {
								late += 1;
							}
							if (attendance.getAttendanceStatus()
									.equalsIgnoreCase("LEAVE")) {
								leave += 1;
							}
							break;
						} 
						else
						{
							dto.setAttendanceStatus("");
						}
						
					}
				}
				percent = Math.round(((double) (present + late) / total) * 100);
				dto.setTotalAttendance(total);
				dto.setTotalPresent(present);
				dto.setTotalAbsent(absent);
				dto.setTotalLate(late);
				dto.setTotalLeave(leave);
				dto.setPercent(percent);
				dto.setAttendanceDate(String.valueOf(i));
				dtos.add(dto);
			}
			studentDTO.setStudentAttendanceDTOs(dtos);
			studentDTO.setFullName(student.getFirstName() + " "
					+ student.getMiddleName() + " " + student.getLastName());
			studentDTO.setAdmissionNo(student.getStudentAdmissionNo());
			studentDTO.setClassName(student.getClasses().getClassName());
			studentDTO.setSectionName(student.getSection().getSectionName());
			studentDTO.setMonthName(monthService.read(monthId).getMonthName());
			studentDTO.setRollno(String.valueOf(student.getRollNo()));
			studentDTOs.add(studentDTO);
		}
		return studentDTOs;
	}

	@Override
	public List<StudentDTO> getStudentsDateWiseAttendanceList(int classId,
			int sectionId, String currentDate) throws ParseException {
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		Session session = sessionDao.findCurrentSession();
		List<Students> studentList = studentsAdmissionDao
				.getStudentsbyClassSectionId(classId, sectionId,
						session.getSessionId());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(currentDate);
		for (Students students : studentList) {
			StudentDTO studentDTO = new StudentDTO();
			StudentAttendance studentAttendance = studentAttendanceDao
					.getAttendanceReportByIdDate(classId, sectionId,
							students.getStudentAdmissionNo(),
							new java.sql.Date(date.getTime()));
			studentDTO.setFullName(students.getFirstName() + " "
					+ students.getMiddleName() + " " + students.getLastName());
			studentDTO.setStudentId(students.getStudentAdmissionNo());
			studentDTO.setFatherName(students.getFatherName());
			studentDTO.setRollno(Integer.toString(students.getRollNo()));
			if (studentAttendance == null) {
				studentDTO.setStudentAttendanceStatus("");
			} else {
				studentDTO.setStudentAttendanceStatus(studentAttendance
						.getAttendanceStatus());
			}
			studentDTOs.add(studentDTO);
		}
		return studentDTOs;
	}

	@Override
	public List<StudentAttendanceDTO> getStudentsCurrentDateAttendanceList(
			int classId, int sectionId, String currentDate)
			throws ParseException {
		List<StudentAttendanceDTO> studentAttendanceDTOs = new ArrayList<StudentAttendanceDTO>();
		Session session = sessionDao.findCurrentSession();
		List<Students> studentList = studentsAdmissionDao
				.getStudentsbyClassSectionId(classId, sectionId,
						session.getSessionId());
		// variable
		int totalPresent = 0;
		int totalAbsent = 0;
		int totalLate = 0;
		int totalLeave = 0;
		// end
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = dateFormat.parse(currentDate);
		java.sql.Date sDate = new java.sql.Date(myDate.getTime());
		if (!studentList.isEmpty() && studentList != null) {
			StudentAttendanceDTO totalStudents = new StudentAttendanceDTO();
			totalStudents.setStatus("Total Students");
			totalStudents.setTotalAttendance(studentList.size());
			studentAttendanceDTOs.add(totalStudents);
		}

		for (Students students : studentList) {
			StudentAttendance studentAttendance = studentAttendanceDao
					.getAttendanceReportByIdDate(classId, sectionId,
							students.getStudentAdmissionNo(), sDate);

			if (studentAttendance != null) {
				if (studentAttendance.getAttendanceStatus().equals("PRESENT")) {
					totalPresent++;
				}
				if (studentAttendance.getAttendanceStatus().equals("LATE")) {
					totalLate++;
				}
				if (studentAttendance.getAttendanceStatus().equals("LEAVE")) {
					totalLeave++;
				}
				if (studentAttendance.getAttendanceStatus().equals("ABSENT")) {
					totalAbsent++;
				}
			} else {
				totalAbsent++;
			}
			if (currentDate.equals(dateFormat.format(new Date()))) {
				SMS.sendMySMS(
						students.getMobile(),
						"Your+ward+" + students.getFirstName() + "+"
								+ students.getMiddleName() + "+"
								+ students.getLastName() + "+is+"
								+ studentAttendance.getAttendanceStatus()
								+ "+today");
			}
		}

		// set data in list
		// present employee
		StudentAttendanceDTO presentStudents = new StudentAttendanceDTO();
		presentStudents.setStatus("Total Present");
		presentStudents.setTotalAttendance(totalPresent);
		studentAttendanceDTOs.add(presentStudents);
		// end
		// absent employee
		StudentAttendanceDTO absentSudents = new StudentAttendanceDTO();
		absentSudents.setStatus("Total Absent");
		absentSudents.setTotalAttendance(totalAbsent);
		studentAttendanceDTOs.add(absentSudents);
		// end
		// late employee
		StudentAttendanceDTO lateStudents = new StudentAttendanceDTO();
		lateStudents.setStatus("Total Late");
		lateStudents.setTotalAttendance(totalLate);
		studentAttendanceDTOs.add(lateStudents);
		// end
		// on leave
		StudentAttendanceDTO leaveStudents = new StudentAttendanceDTO();
		leaveStudents.setStatus("Total Leave");
		leaveStudents.setTotalAttendance(totalLeave);
		studentAttendanceDTOs.add(leaveStudents);
		// end
		// end

		return studentAttendanceDTOs;

	}

	@Override
	public List<StudentAttendanceDTO> getStudentAttendanceSummary(int classId,
			int sectionId) throws ParseException {

		List<StudentAttendanceDTO> studentAttendanceDTOs = new ArrayList<StudentAttendanceDTO>();
		Session session = sessionDao.findCurrentSession();
		List<Students> studentList = studentsAdmissionDao
				.getStudentsbyClassSectionId(classId, sectionId,
						session.getSessionId());
		// setting dates for getting data
		// get distinct attendance date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Integer startMonth[] = { 1, 2, 3 };
		int month = calendar.get(Calendar.MONTH)+1;
		int fromYear = 0;
		Date fromDate = dateFormat.parse(dateFormat.format(new Date()));;
		if (Arrays.asList(startMonth).contains(month)) {
			calendar.add(Calendar.YEAR, -1);
			fromYear = calendar.get(Calendar.YEAR);
			fromDate = dateFormat.parse("" + fromYear + "-04-01");
		} else {
			fromYear = calendar.get(Calendar.YEAR);
			fromDate = dateFormat.parse("" + fromYear + "-04-01");
		}
		// to date
		Date toDate = new Date();
		List<Date> dates = dao.totalSchoolOpenDays(classId, sectionId,
				fromDate, toDate);
		// end setting dates

		// schoolSessionStartingDate
		Date schoolSessionStartingDate = new Date();
		if (!dates.isEmpty() && dates != null)
			schoolSessionStartingDate = dates.get(0);

		for (Students students : studentList) {
			List<StudentAttendance> studentAttendances = dao
					.totalStudentSchoolOpenDays(classId, sectionId,
							students.getStudentAdmissionNo(),
							schoolSessionStartingDate, new Date());
			StudentAttendanceDTO studentAttendanceDTO = new StudentAttendanceDTO();
			int totalPresent = 0;
			int totalAbsent = 0;
			int totalLate = 0;
			int totalLeave = 0;
			for (StudentAttendance studentAttendance : studentAttendances) {
				if (studentAttendance.getAttendanceStatus().equals("PRESENT")) {
					totalPresent++;
				}
				if (studentAttendance.getAttendanceStatus().equals("LATE")) {
					totalLate++;
				}
				if (studentAttendance.getAttendanceStatus().equals("LEAVE")) {
					totalLeave++;
				}
				if (studentAttendance.getAttendanceStatus().equals("ABSENT")) {
					totalAbsent++;
				}
			}
			studentAttendanceDTO.setFullName(students.getFirstName() + " "
					+ students.getMiddleName() + " " + students.getLastName());
			studentAttendanceDTO.setFatherName(students.getFatherName());
			studentAttendanceDTO.setRollno(students.getRollNo());
			studentAttendanceDTO.setTotalAttendance(dates.size());
			studentAttendanceDTO.setTotalAbsent(totalAbsent);
			studentAttendanceDTO.setTotalLate(totalLate);
			studentAttendanceDTO.setTotalLeave(totalLeave);
			studentAttendanceDTO.setTotalPresent(totalPresent);
			studentAttendanceDTOs.add(studentAttendanceDTO);
		}
		return studentAttendanceDTOs;

	}

	@Override
	public Map<String, Object> studentsDateWiseAttendanceList(int classId,
			int sectionId, String currentDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		Session session = sessionDao.findCurrentSession();
		List<Students> studentList = studentsAdmissionDao
				.getStudentsbyClassSectionId(classId, sectionId,
						session.getSessionId());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(currentDate);
		for (Students students : studentList) {
			StudentDTO studentDTO = new StudentDTO();
			StudentAttendance studentAttendance = studentAttendanceDao
					.getAttendanceReportByIdDate(classId, sectionId,
							students.getStudentAdmissionNo(),
							new java.sql.Date(date.getTime()));
			studentDTO.setFullName(students.getFirstName() + " "
					+ students.getMiddleName() + " " + students.getLastName());
			studentDTO.setStudentId(students.getStudentAdmissionNo());
			studentDTO.setFatherName(students.getFatherName());
			studentDTO.setRollno(Integer.toString(students.getRollNo()));
			if (studentAttendance == null) {
				studentDTO.setStudentAttendanceStatus("");
			} else {
				studentDTO.setStudentAttendanceStatus(studentAttendance
						.getAttendanceStatus());
			}
			studentDTOs.add(studentDTO);
		}
		map.put("StudentDTOs", studentDTOs);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(calendar.DAY_OF_WEEK);
		if (day == calendar.SUNDAY) {
			map.put("HOLIDAY", "Sunday");
		} else {
			Holiday holiday = holidayDao.getDataForCreateHoliday(date);
			if (holiday != null) {
				map.put("HOLIDAY", holiday.getDescription());
			} else {
				map.put("HOLIDAY", "");
			}
		}
		return map;

	}
}
