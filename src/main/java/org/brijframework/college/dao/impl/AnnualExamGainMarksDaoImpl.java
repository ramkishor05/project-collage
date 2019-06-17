package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.AnnualExamGainMarksDao;
import org.brijframework.college.model.AnnualExamGainMarks;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class AnnualExamGainMarksDaoImpl extends
		DAOImpl<Integer, AnnualExamGainMarks> implements AnnualExamGainMarksDao {

	@Override
	public AnnualExamGainMarks getAnnualExamGainMarks(int subjectId,
			int annualExamReportId) {
		return (AnnualExamGainMarks) sessionFactory
				.getCurrentSession()
				.createCriteria(AnnualExamGainMarks.class)
				.add(Restrictions.eq("subjects.subjectsId", subjectId))
				.add(Restrictions.eq("annualExamReport.annualExamReportId",
						annualExamReportId)).uniqueResult();
	}

}
