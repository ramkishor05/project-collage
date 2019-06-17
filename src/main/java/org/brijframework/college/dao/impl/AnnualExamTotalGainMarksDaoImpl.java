package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.AnnualExamTotalGainMarksDao;
import org.brijframework.college.model.AnnualExamTotalGainMarks;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class AnnualExamTotalGainMarksDaoImpl extends
		DAOImpl<Integer, AnnualExamTotalGainMarks> implements
		AnnualExamTotalGainMarksDao {

	@Override
	public AnnualExamTotalGainMarks getAnnualExamTotalGainMarks(int sessionId,
			int classId, int sectionId, String studentId, String annualExamType) {
		return (AnnualExamTotalGainMarks) sessionFactory.getCurrentSession()
				.createCriteria(AnnualExamTotalGainMarks.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.eq("annualExamType", annualExamType))
				.uniqueResult();
	}

	@Override
	public List<AnnualExamTotalGainMarks> getMaxAnnualExamTotalGainMarks(
			int sessionId, int classId, int sectionId, String annualExamType) {
		return sessionFactory.getCurrentSession()
				.createCriteria(AnnualExamTotalGainMarks.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("annualExamType", annualExamType))
				.addOrder(Order.desc("totalGainMarks")).setMaxResults(1).list();
	}

}
