package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.SectionDao;
import org.brijframework.college.model.Section;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("SectionDao")
public class SectionDaoImpl extends DAOImpl<Integer, Section> implements
		SectionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Section> getSectionListByClassId(int classId) {
		return (List<Section>) (sessionFactory.getCurrentSession()
				.createCriteria(Section.class).add(Restrictions.eq(
				"studentClasses.classId", classId))).list();
	}

	@Override
	public Section getSectionByClassIdAndSectionName(int classId, String sectionName) {
		return (Section) sessionFactory.getCurrentSession()
				.createCriteria(Section.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("sectionName", sectionName).ignoreCase())
				.uniqueResult();
	}

}
