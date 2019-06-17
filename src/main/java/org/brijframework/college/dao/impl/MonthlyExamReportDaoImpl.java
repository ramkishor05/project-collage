package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.MonthlyExamReportDao;
import org.brijframework.college.model.MonthlyExamReport;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class MonthlyExamReportDaoImpl extends
		DAOImpl<Integer, MonthlyExamReport> implements MonthlyExamReportDao {

	@Override
	public MonthlyExamReport getMonthlyExamReport(int sessionId, int classId,
			int sectionId, String studentId, int subjectId, int monthId) {
		MonthlyExamReport monthlyExamReport = (MonthlyExamReport) sessionFactory
				.getCurrentSession().createCriteria(MonthlyExamReport.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("subjects.subjectsId", subjectId))
				.add(Restrictions.eq("month.monthId", monthId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.uniqueResult();
		return monthlyExamReport;
	}

	@Override
	public List<MonthlyExamReport> maxMonthId(int sessionId, int classId,
			int sectionId, String studentId) {
		return sessionFactory.getCurrentSession()
				.createCriteria(MonthlyExamReport.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.addOrder(Order.desc("month.monthId")).setMaxResults(1).list();
	}

	@Override
	public List<MonthlyExamReport> getMonthlyExamReports(int sessionId, int classId,
			int sectionId, String studentId, int monthSerialNo) {
		return sessionFactory
				.getCurrentSession().createCriteria(MonthlyExamReport.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.eq("month.monthId", monthSerialNo))
				.list();
	}

	@Override
	public List<MonthlyExamReport> getMonthIdBeetweenJanToMarch(int sessionId,
			int classId, int sectionId, String studentId) {
		return sessionFactory.getCurrentSession()
				.createCriteria(MonthlyExamReport.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.le("month.monthId", 3))
				.addOrder(Order.desc("month.monthId")).setMaxResults(1).list();
	}

	@Override
	public MonthlyExamReport getMaxMarks(int classId, int sessionId,
			int sectionId, int monthId,int subjectId) {
		MonthlyExamReport monthlyExamReport = (MonthlyExamReport) sessionFactory
				.getCurrentSession().createCriteria(MonthlyExamReport.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("subjects.subjectsId", subjectId))
				.add(Restrictions.eq("month.monthId", monthId))
				.setMaxResults(1)
				.uniqueResult();
		return monthlyExamReport;
	}
}
