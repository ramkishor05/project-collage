package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.DocumentCategoryDao;
import org.brijframework.college.model.DocumentsCategory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("documentCategoryDao")
public class DocumentCategoryDaoImpl extends
		DAOImpl<Integer, DocumentsCategory> implements DocumentCategoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentsCategory> getDocumentByType(String type) {
		return  sessionFactory.getCurrentSession()
				.createCriteria(DocumentsCategory.class)
				.add(Restrictions.eq("documentType", type))
				.add(Restrictions.eq("active", true))
				.list();
	}

}
