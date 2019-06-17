package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.UniformPurchaseDao;
import org.brijframework.college.model.UniformPurchase;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("uniformPurchaseDao")
public class UniformPurchaseDaoImpl extends DAOImpl<Integer, UniformPurchase>
		implements UniformPurchaseDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformPurchase> findCurrentSessionPurchases(Date fromDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.add(Restrictions.ge("dateOfPurchase", fromDate))
				.addOrder(Order.desc("productPurchaseId"))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> finshopNames() {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.setProjection(
						Projections.distinct(Projections.property("shopName")))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findUniformNames() {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.setProjection(
						Projections.distinct(Projections
								.property("uniformName")))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findCategorybyName(String name) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.setProjection(
						Projections.distinct(Projections
								.property("uniformCategory")))
				.add(Restrictions.eq("uniformName", name))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findSizebycategory(String category, String name) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.setProjection(
						Projections.distinct(Projections
								.property("uniformSize")))
				.add(Restrictions.eq("uniformName", name))
				.add(Restrictions.eq("uniformCategory", category))
				.add(Restrictions.eq("active", true)).list();
	}

	@Override
	public UniformPurchase getUniformPriceandId(String name, String category,
			String size) {
		return (UniformPurchase) sessionFactory.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.add(Restrictions.eq("uniformName", name))
				.add(Restrictions.eq("uniformCategory", category))
				.add(Restrictions.eq("uniformSize", size))
				.add(Restrictions.gt("remainingQuantity", 0)).setMaxResults(1)
				.addOrder(Order.asc("productPurchaseId"))
				.add(Restrictions.eq("active", true)).uniqueResult();
	}

	@Override
	public UniformPurchase getNewUniformPriceandId(String name,
			String category, String size, int id) {
		return (UniformPurchase) sessionFactory.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.add(Restrictions.eq("uniformName", name))
				.add(Restrictions.eq("uniformCategory", category))
				.add(Restrictions.eq("uniformSize", size))
				.add(Restrictions.gt("remainingQuantity", 0))
				.add(Restrictions.gt("productPurchaseId", id))
				.add(Restrictions.eq("active", true)).setMaxResults(1)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformPurchase> findStockbyUniformName(String name,
			Date fromDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.add(Restrictions.ge("dateOfPurchase", fromDate))
				.add(Restrictions.eq("uniformName", name))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformPurchase> findStockbyUniformCategory(String name,
			String category, Date fromDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.add(Restrictions.ge("dateOfPurchase", fromDate))
				.add(Restrictions.eq("uniformName", name))
				.add(Restrictions.eq("uniformCategory", category))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformPurchase> findStockbyUniformSize(String name,
			String category, String size, Date fromDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.add(Restrictions.ge("dateOfPurchase", fromDate))
				.add(Restrictions.eq("uniformName", name))
				.add(Restrictions.eq("uniformCategory", category))
				.add(Restrictions.eq("uniformSize", size))
				.add(Restrictions.eq("active", true)).list();
	}

	@Override
	public UniformPurchase getPriceforUniform(String name, String category,
			String size) {
		return (UniformPurchase) sessionFactory.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.add(Restrictions.eq("uniformName", name))
				.add(Restrictions.eq("uniformCategory", category))
				.add(Restrictions.eq("uniformSize", size)).setMaxResults(1)
				.addOrder(Order.desc("productPurchaseId"))
				.add(Restrictions.eq("active", true)).uniqueResult();
	}

	@Override
	public int findMaxProductNo() {
		return (Integer) sessionFactory
				.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.setProjection(
						Projections.distinct(Projections.property("productNo")))
				.addOrder(Order.desc("productNo")).setMaxResults(1)
				.add(Restrictions.eq("active", true)).uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformPurchase> getUniformBoughtDetails(int stockPurchaseId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.add(Restrictions.eq("stocksupplier.stockPurchaseId",
						stockPurchaseId)).add(Restrictions.eq("active", true))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getProductPurchases(String name, String category,
			String size) {
		return (List<Integer>) sessionFactory
				.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.setProjection(
						Projections.distinct(Projections
								.property("productPurchaseId")))
				.add(Restrictions.eq("uniformName", name))
				.add(Restrictions.eq("uniformCategory", category))
				.add(Restrictions.eq("uniformSize", size))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformPurchase> getUniformStocks(String name, String category,
			String size) {
		return (List<UniformPurchase>) sessionFactory
				.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.add(Restrictions.eq("uniformName", name))
				.add(Restrictions.eq("uniformCategory", category))
				.add(Restrictions.eq("uniformSize", size))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformPurchase> getSizeWisePurchases(String name,
			String category, String size, Date frmDate) {
		return (List<UniformPurchase>) sessionFactory
				.getCurrentSession()
				.createCriteria(UniformPurchase.class)
				.add(Restrictions.eq("uniformName", name))
				.add(Restrictions.eq("uniformCategory", category))
				.add(Restrictions.eq("uniformSize", size))
					.add(Restrictions.ge("dateOfPurchase", frmDate))
				.add(Restrictions.eq("active", true)).list();
	}



}
