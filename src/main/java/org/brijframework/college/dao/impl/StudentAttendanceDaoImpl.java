package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.StudentAttendanceDao;
import org.brijframework.college.model.StudentAttendance;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("studentAttendanceDao")
@SuppressWarnings("unchecked")
public class StudentAttendanceDaoImpl extends
		DAOImpl<Integer, StudentAttendance> implements StudentAttendanceDao {

	@Override
	public StudentAttendance getAttendanceReportById(String studentId,
			int employeeId, java.sql.Date currentDate) {
		Criterion post = Restrictions.eq("employees.employeeId", employeeId);
		Criterion pre = Restrictions.eq("employees.employeeId", 1);
		StudentAttendance studentAttendence = (StudentAttendance) (sessionFactory
				.getCurrentSession().createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.or(pre, post)).add(Restrictions.eq(
				"datOfAttendance", currentDate))).uniqueResult();
		if (studentAttendence == null) {
			return null;
		} else {
			return studentAttendence;
		}
	}

	@Override
	public List<StudentAttendance> getStudentAttendanceByStudentId(
			String studentId) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.list();
	}

	public List<StudentAttendance> getStudentAttendanceList(int courseid,
			int branchid, int batchid, int subjectid, Date fromdate, Date todate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("course.id", courseid))
				.add(Restrictions.eq("branch.id", branchid))
				.add(Restrictions.eq("batches.id", branchid))
				.add(Restrictions.eq("subjects.id", subjectid))
				.add(Restrictions.between("datOfAttendance", fromdate, todate))
				.list();
	}

	@Override
	public List<StudentAttendance> getStudentAttendanceList(int courseid,
			int branchid, int batchid, int subjectid, String fromdate,
			String todate) {
		return null;
	}

	@Override
	public StudentAttendance getAttendenceByDate(String id, java.sql.Date date) {
		return (StudentAttendance) sessionFactory.getCurrentSession()
				.createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("students.studentAdmissionNo", id))
				.add(Restrictions.eq("datOfAttendance", date)).uniqueResult();
	}

	@Override
	public List<StudentAttendance> getStudentAttendanceDayByDay(int classId,
			int sectionId, String studentAdmissionNumber, Date fromDate,
			Date toDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.id", studentAdmissionNumber))
				.add(Restrictions.between("datOfAttendance", fromDate, toDate))
				.list();
	}

	@Override
	public List<StudentAttendance> getReportByCourseBranchBatchStudentId(
			int courseId, int batchId, int branchId, String admissionNo,
			int subjectId, Date fromdate, Date todate) {

		return sessionFactory.getCurrentSession()
				.createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("course.id", courseId))
				.add(Restrictions.eq("branch.id", branchId))
				.add(Restrictions.eq("batches.id", batchId))
				.add(Restrictions.eq("subjects.id", subjectId))
				.add(Restrictions.eq("students.id", admissionNo))
				.add(Restrictions.between("datOfAttendance", fromdate, todate))
				.list();
	}

	@Override
	public List<StudentAttendance> getReportByCourseBranchBatchSubjectId(
			int courseId, int branchId, int batchId, int subjectsId, Date from,
			Date to) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("course.id", courseId))
				.add(Restrictions.eq("branch.id", branchId))
				.add(Restrictions.eq("batches.id", batchId))
				.add(Restrictions.eq("subjects.id", subjectsId))
				.add(Restrictions.between("datOfAttendance", from, to)).list();

	}

	@Override
	public List<StudentAttendance> getMonthlyReport(String id, Integer id2,
			Date from, Date to) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("students.id", id))
				.add(Restrictions.eq("subjects.id", id2))
				.add(Restrictions.between("datOfAttendance", from, to)).list();

	}

	@Override
	public StudentAttendance getAttendanceReportByIdDate(String studentId,
			java.sql.Date currentDate) {
		StudentAttendance studentAttendence = (StudentAttendance) (sessionFactory
				.getCurrentSession().createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.eq("datOfAttendance", currentDate)))
				.uniqueResult();
		if (studentAttendence == null) {
			return null;
		} else {
			return studentAttendence;
		}
	}

	public List<StudentAttendance> getStudentAttendanceBystudentId(
			String studentId, Date fromDate, Date toDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.between("datOfAttendance", fromDate, toDate))
				.list();
	}

	@Override
	public StudentAttendance getAttendanceReportByIdDate(int classId,
			int sectionId, String id, java.sql.Date sDate) {
		return (StudentAttendance) sessionFactory.getCurrentSession()
				.createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.id", id))
				.add(Restrictions.eq("datOfAttendance", sDate)).uniqueResult();
	}

	@Override
	public List<Date> totalSchoolOpenDays(int classId, int sectionId,
			Date fromDate, Date toDate) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentAttendance.class)
				.setProjection(
						Projections.distinct(Projections
								.property("datOfAttendance")))
				.addOrder(Order.asc("datOfAttendance"))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.ge("datOfAttendance", fromDate))
				.add(Restrictions.le("datOfAttendance", toDate)).list();
	}

	@Override
	public List<StudentAttendance> totalStudentSchoolOpenDays(int classId,
			int sectionId, String studentId, Date fromDate, Date toDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentAttendance.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.ge("datOfAttendance", fromDate))
				.add(Restrictions.le("datOfAttendance", toDate)).list();
	}

}