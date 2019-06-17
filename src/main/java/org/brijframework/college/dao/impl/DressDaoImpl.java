package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.DressDao;
import org.brijframework.college.model.Dress;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("dressDao")
public class DressDaoImpl extends DAOImpl<Integer, Dress> implements DressDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Dress> findfirstdress() {
		return (List<Dress>) sessionFactory.getCurrentSession()
				.createCriteria(Dress.class).setMaxResults(10).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dress> getDressNameslist() {
		return (List<Dress>) sessionFactory.getCurrentSession()
				.createCriteria(Dress.class).setFirstResult(11).list();
	}

	@Override
	public Dress getFirstDress() {
		return (Dress) sessionFactory.getCurrentSession()
				.createCriteria(Dress.class).setMaxResults(1).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dress> getDressAllDetails(String dressName) {
		return (List<Dress>) sessionFactory.getCurrentSession()
				.createCriteria(Dress.class)
				.add(Restrictions.eq("dressName", dressName)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dress> getDressNamelist() {
		return (List<Dress>) sessionFactory.getCurrentSession()
				.createCriteria(Dress.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dress> getCategoryList(String name) {
		return (List<Dress>) sessionFactory.getCurrentSession()
				.createCriteria(Dress.class)
				.add(Restrictions.eq("dressName", name)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dress> getSizeList(String category, String name) {
		return (List<Dress>) sessionFactory.getCurrentSession()
				.createCriteria(Dress.class)
				.add(Restrictions.eq("dressName", name))
				.add(Restrictions.eq("category", category)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDistinctCateegory(String dressName) {
		return (List<String>) sessionFactory
				.getCurrentSession()
				.createCriteria(Dress.class)
				.setProjection(
						Projections.distinct(Projections.property("category")))
				.add(Restrictions.eq("dressName", dressName)).list();
	}

	@Override
	public Dress findprice(String dressName, String category, String size) {
		return (Dress) sessionFactory.getCurrentSession()
				.createCriteria(Dress.class)
				.add(Restrictions.eq("category", category))
				.add(Restrictions.eq("size", size))
				.add(Restrictions.eq("dressName", dressName)).uniqueResult();
	}

}
