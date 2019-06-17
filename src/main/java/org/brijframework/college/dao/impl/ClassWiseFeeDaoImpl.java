package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.ClassWiseFeeDao;
import org.brijframework.college.model.ClassWiseFee;
import org.brijframework.college.model.EditFeeAmount;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("classWiseFeeDao")
public class ClassWiseFeeDaoImpl extends DAOImpl<Integer, ClassWiseFee>
		implements ClassWiseFeeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassWiseFee> getClassWiseFee(int sessionId, int classId,
			Integer[] monthId, int categoryId) {
		return (List<ClassWiseFee>) sessionFactory
				.getCurrentSession()
				.createCriteria(ClassWiseFee.class)
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.in("month.monthId", monthId))
				.add(Restrictions.eq("active", true))
				.add(Restrictions
						.eq("feesCategories.feeCategoryId", categoryId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassWiseFee> getFeeAllotement(int sessionId, int classId,
			int monthId) {
		Criterion post = Restrictions.eq("month.monthId", monthId);
		Criterion pre = Restrictions.eq("month.monthId", 13);

		return (List<ClassWiseFee>) sessionFactory.getCurrentSession()
				.createCriteria(ClassWiseFee.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("active", true))
				.add(Restrictions.or(pre, post)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassWiseFee> getClassWiseFeeAllotementByMonth(int monthId) {
		Criterion post = Restrictions.eq("month.monthId", monthId);
		Criterion pre = Restrictions.eq("month.monthId", 13);
		return (List<ClassWiseFee>) sessionFactory.getCurrentSession()
				.createCriteria(ClassWiseFee.class)
				.add(Restrictions.or(pre, post))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	public List<EditFeeAmount> getDiscountAfterEdit(int monthId) {
		Criterion post = Restrictions.eq("month.monthId", monthId);
		Criterion pre = Restrictions.eq("month.monthId", 13);
		return (List<EditFeeAmount>) sessionFactory.getCurrentSession()
				.createCriteria(EditFeeAmount.class)
				.add(Restrictions.or(pre, post)).list();
	}

	@SuppressWarnings("unchecked")
	public List<EditFeeAmount> getDiscountAfterEditBySessionId(int sessionId) {
		return (List<EditFeeAmount>) sessionFactory.getCurrentSession()
				.createCriteria(EditFeeAmount.class)
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassWiseFee> getClassWiseFeeAllotementBySessionId(int sessionId) {
		return (List<ClassWiseFee>) sessionFactory.getCurrentSession()
				.createCriteria(ClassWiseFee.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassWiseFee> getAllotedFeesByClassId(int classId, int sessionId) {
		return (List<ClassWiseFee>) sessionFactory.getCurrentSession()
				.createCriteria(ClassWiseFee.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId)).list();
	}

}
