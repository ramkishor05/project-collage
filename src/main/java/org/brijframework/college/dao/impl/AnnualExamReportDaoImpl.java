package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.AnnualExamReportDao;
import org.brijframework.college.model.AnnualExamReport;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class AnnualExamReportDaoImpl extends DAOImpl<Integer, AnnualExamReport>
		implements AnnualExamReportDao {

	@Override
	public AnnualExamReport getAnnualExamReport(int sessionId, int classId,
			int sectionId, String studentId, String annualExamType,
			String examType) {
		return (AnnualExamReport) sessionFactory.getCurrentSession()
				.createCriteria(AnnualExamReport.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.eq("annualExamType", annualExamType))
				.add(Restrictions.eq("examType", examType)).uniqueResult();
	}

	@Override
	public List<AnnualExamReport> getAnnualExamReport(int sessionId,
			int classId, int sectionId, String studentId) {
		return (List<AnnualExamReport>) sessionFactory.getCurrentSession()
				.createCriteria(AnnualExamReport.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.list();
	}

	@Override
	public List<AnnualExamReport> getAnnualExamReport(int sessionId,
			int classId, int sectionId, String studentId, String annualExamType) {
		return (List<AnnualExamReport>) sessionFactory.getCurrentSession()
				.createCriteria(AnnualExamReport.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.eq("annualExamType", annualExamType)).list();
	}

	@Override
	public AnnualExamReport getAnnualMaxExam(int sessionId, int classId,
			int sectionId, String annualExamType, String examType) {
		return (AnnualExamReport) sessionFactory.getCurrentSession()
				.createCriteria(AnnualExamReport.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("examType", examType))
				.setMaxResults(1)
				.add(Restrictions.eq("annualExamType", annualExamType)).uniqueResult();
	}

}
