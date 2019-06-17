package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.FeesCategoriesDao;
import org.brijframework.college.model.FeesCategories;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("feesCategoriesDao")
public class FeesCategoriesDaoImpl extends DAOImpl<Integer, FeesCategories> implements FeesCategoriesDao {

	@Override
	public FeesCategories getverifiedname(String name) {
		return (FeesCategories) sessionFactory.getCurrentSession()
				.createCriteria(FeesCategories.class)
				.add(Restrictions.eq("feeCategoryName", name))
					.add(Restrictions.eq("active", true))
				.uniqueResult();
	}

}
