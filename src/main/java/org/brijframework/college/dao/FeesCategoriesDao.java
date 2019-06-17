package org.brijframework.college.dao;

import org.brijframework.college.model.FeesCategories;

public interface FeesCategoriesDao extends DAO<Integer, FeesCategories> {

	FeesCategories getverifiedname(String name);

}
