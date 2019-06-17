package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.StudentWiseFeeDao;
import org.brijframework.college.model.StudentWiseFee;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("studentWiseFeeDao")
public class StudentWiseFeeDaoImpl extends DAOImpl<Integer, StudentWiseFee>
		implements StudentWiseFeeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentWiseFee> getStudentWiseFee(int sessionId, int classId,
			Integer[] monthId, int categoryId, int sectionId, String studentId) {
		return (List<StudentWiseFee>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentWiseFee.class)
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.in("month.monthId", monthId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions
						.eq("feesCategories.feeCategoryId", categoryId))
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentWiseFee> getFeeAllotement(int sessionId, int classId,
			int sectionId, int monthId, String admissionNo) {
		Criterion post = Restrictions.eq("month.monthId", monthId);
		Criterion pre = Restrictions.eq("month.monthId", 13);
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentWiseFee.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions
						.eq("students.studentAdmissionNo", admissionNo))
				.add(Restrictions.eq("active", true))
				.add(Restrictions.or(pre, post)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentWiseFee> getStudentByAdmissionNoFromDateToDate(
			int monthId) {
		Criterion post = Restrictions.eq("month.monthId", monthId);
		Criterion pre = Restrictions.eq("month.monthId", 13);
		return (List<StudentWiseFee>) sessionFactory.getCurrentSession()
				.createCriteria(StudentWiseFee.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.or(pre, post)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentWiseFee> getStudentFeeAllotementBySessionId(int sessionId) {
		return (List<StudentWiseFee>) sessionFactory.getCurrentSession()
				.createCriteria(StudentWiseFee.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@SuppressWarnings("unchecked")
	public List<StudentWiseFee> getAllotedFeeByStudentId(int sessionId,
			int classId, int sectionId, String studentId) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentWiseFee.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.list();
	}

	@Override
	public StudentWiseFee getStudentWiseFee(int sessionId, int classId,
			int categoryId, int sectionId, String studentId) {
		return (StudentWiseFee) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentWiseFee.class)
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions
						.eq("feesCategories.feeCategoryId", categoryId))
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.uniqueResult();
	}

	@Override
	public StudentWiseFee getDues(Integer sessionId, Integer classId,
			Integer sectionId, int monthId, String studentAdmissionNo) {
		return (StudentWiseFee) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentWiseFee.class)
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("feesCategories.feeCategoryId", 13))
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("month.monthId", monthId))
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo)).uniqueResult();
	}

	@Override
	public StudentWiseFee getduesbyMonth(String studentId, int i, int sessionId) {
		return (StudentWiseFee) sessionFactory.getCurrentSession()
				.createCriteria(StudentWiseFee.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("feesCategories.feeCategoryId", 13))
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("month.monthId", i))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentWiseFee> getStudentWiseFee(Integer sessionId,
			int monthId, int categoryId, int studentId) {
		return (List<StudentWiseFee>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentWiseFee.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("month.monthId", monthId))
				.add(Restrictions
						.eq("feesCategories.feeCategoryId", categoryId))
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("students.studentAdmissionNo",
						String.valueOf(studentId))).list();
	}

}
