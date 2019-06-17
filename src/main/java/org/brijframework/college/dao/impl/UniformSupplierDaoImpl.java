package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.UniformSupplierDao;
import org.brijframework.college.model.UniformSupplier;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("uniformSupplierDao")
public class UniformSupplierDaoImpl extends DAOImpl<Integer, UniformSupplier>
		implements UniformSupplierDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getSuppliersId() {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(UniformSupplier.class)
				.setProjection(
						Projections.distinct(Projections
								.property("supplierName"))).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformSupplier> getDetailsbySupplier(String supplierName,
			Date frmDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(UniformSupplier.class)
				.add(Restrictions.ge("dateOfPurchase", frmDate))
				.add(Restrictions.eq("supplierName", supplierName)).list();
	}

	@Override
	public UniformSupplier getReceipt() {
		return (UniformSupplier) sessionFactory.getCurrentSession()
				.createCriteria(UniformSupplier.class)
				.addOrder(Order.desc("receiptNo")).setMaxResults(1)
				.uniqueResult();
	}

	@Override
	public UniformSupplier getStocksId(int receiptNo) {
		return (UniformSupplier) sessionFactory.getCurrentSession()
				.createCriteria(UniformSupplier.class)
				.add(Restrictions.eq("receiptNo", receiptNo)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformSupplier> getBoughtDetails(Date frmDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(UniformSupplier.class)
				.add(Restrictions.ge("dateOfPurchase", frmDate)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformSupplier> getOverallByPageNo(int pageNo) {
		return sessionFactory.getCurrentSession()
				.createCriteria(UniformSupplier.class)
				.add(Restrictions.eq("active", true)).setMaxResults(5)
				.setFirstResult(pageNo * 5).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformSupplier> findTodaysExpense(Date date) {
		return (List<UniformSupplier>) (sessionFactory.getCurrentSession()
				.createCriteria(UniformSupplier.class).add(Restrictions.eq(
				"dateOfPurchase", date)))
				.add(Restrictions.eq("paidStatus", "Completed"))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UniformSupplier> findDatewiseExpense(Date fromDate, Date toDate) {
		return (List<UniformSupplier>) (sessionFactory.getCurrentSession()
				.createCriteria(UniformSupplier.class).add(Restrictions
				.between("dateOfPurchase", fromDate, toDate)))
				.add(Restrictions.eq("paidStatus", "Completed"))
				.add(Restrictions.eq("active", true)).list();
	}

	@Override
	public UniformSupplier getLastpaymenttoSupplier(String supplierName,
			String supplierNo) {
		return (UniformSupplier) sessionFactory.getCurrentSession()
				.createCriteria(UniformSupplier.class)
				.add(Restrictions.eq("supplierName", supplierName))
				.add(Restrictions.eq("supplierNo", supplierNo))
				.addOrder(Order.desc("stockPurchaseId")).setMaxResults(1)
				.uniqueResult();
	}
}
