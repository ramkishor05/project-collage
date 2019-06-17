package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.SectionWiseFeeDao;
import org.brijframework.college.model.SectionWiseFee;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("sectionWiseFeeDao")
public class SectionWiseFeeDaoImpl extends DAOImpl<Integer, SectionWiseFee>
		implements SectionWiseFeeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SectionWiseFee> getSectionWiseFee(int sessionId, int classId,
			Integer[] monthId, int categoryId, int sectionId) {
		return (List<SectionWiseFee>) sessionFactory
				.getCurrentSession()
				.createCriteria(SectionWiseFee.class)
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.in("month.monthId", monthId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("active", true))
				.add(Restrictions
						.eq("feesCategories.feeCategoryId", categoryId)).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SectionWiseFee> getFeeAllotement(int sessionId, int classId,
			int sectionId, int monthId) {
		Criterion post = Restrictions.eq("month.monthId", monthId);
		Criterion pre = Restrictions.eq("month.monthId", 13);
		return (List<SectionWiseFee>) sessionFactory.getCurrentSession()
				.createCriteria(SectionWiseFee.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("active", true))
				.add(Restrictions.or(pre, post)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SectionWiseFee> getSectionWiseFeeAllotmentByMonth(int monthId) {
		Criterion post = Restrictions.eq("month.monthId", monthId);
		Criterion pre = Restrictions.eq("month.monthId", 13);
		return (List<SectionWiseFee>) sessionFactory.getCurrentSession()
				.createCriteria(SectionWiseFee.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.or(pre, post)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SectionWiseFee> getSectionWiseFeeAllotmentBysessionId(
			int sessionId) {
		return (List<SectionWiseFee>) sessionFactory.getCurrentSession()
				.createCriteria(SectionWiseFee.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@SuppressWarnings("unchecked")
	public List<SectionWiseFee> getSectionWiseFeeAllotmentBysectionId(
			int sessionId, int classId, int sectionId) {
		return (List<SectionWiseFee>) sessionFactory.getCurrentSession()
				.createCriteria(SectionWiseFee.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId)).list();
	}

}
