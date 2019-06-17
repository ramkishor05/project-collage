package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.EditFeeAmountDao;
import org.brijframework.college.model.EditFeeAmount;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class EditFeeAmountDaoImpl extends DAOImpl<Integer, EditFeeAmount>
		implements EditFeeAmountDao {

	@Override
	public List<Integer> getEditFeeAmountDTOs(int classId, int sectionId,
			int sessionId, int feeCategoryId, String studentId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EditFeeAmount.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("feesCategories.feeCategoryId",
						feeCategoryId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.list();
	}

	@Override
	public EditFeeAmount getDiscountAmountBySessionMonthCategoryId(
			int sessionId, int monthId, int feeCategoryId, int studentId) {
		return (EditFeeAmount) sessionFactory
				.getCurrentSession()
				.createCriteria(EditFeeAmount.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("month.monthId", monthId))
				.add(Restrictions.eq("feesCategories.feeCategoryId",
						feeCategoryId))
				.add(Restrictions.eq("students.studentAdmissionNo",
						String.valueOf(studentId))).uniqueResult();
	}

	@Override
	public EditFeeAmount getEditFeeAmountDTOs(int classId, int sectionId,
			int sessionId, int feeCategoryId, String studentId, int monthId) {
		return (EditFeeAmount) sessionFactory
				.getCurrentSession()
				.createCriteria(EditFeeAmount.class)
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("feesCategories.feeCategoryId",
						feeCategoryId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.eq("month.monthId", monthId)).uniqueResult();
	}

	@Override
	public List<EditFeeAmount> getEditFeeAmountDTOsLast(int classId,
			int sectionId, int sessionId, int feeCategoryId, String studentId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EditFeeAmount.class)
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("feesCategories.feeCategoryId",
						feeCategoryId))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.setMaxResults(1).list();
	}

}
