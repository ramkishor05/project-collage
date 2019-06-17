package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.BookSupplierDao;
import org.brijframework.college.model.BookSupplier;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("bookSupplierDao")
@SuppressWarnings("unchecked")
public class BookSupplierDaoImpl extends DAOImpl<Integer, BookSupplier>
		implements BookSupplierDao {

	@Override
	public List<String> getSuppliersId() {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(BookSupplier.class)
				.setProjection(
						Projections.distinct(Projections
								.property("supplierName"))).list();
	}

	@Override
	public List<BookSupplier> getDetailsbySupplier(String supplierName,
			Date frmDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(BookSupplier.class)
				.add(Restrictions.ge("dateOfPurchase", frmDate))
				.add(Restrictions.eq("supplierName", supplierName)).list();
	}

	@Override
	public BookSupplier getReceipt() {
		return (BookSupplier) sessionFactory.getCurrentSession()
				.createCriteria(BookSupplier.class)
				.addOrder(Order.desc("receiptNo")).setMaxResults(1)
				.uniqueResult();
	}

	@Override
	public BookSupplier getReceiptData(int receiptNo) {
		return (BookSupplier) sessionFactory.getCurrentSession()
				.createCriteria(BookSupplier.class)
				.add(Restrictions.eq("receiptNo", receiptNo)).uniqueResult();
	}

	@Override
	public List<BookSupplier> getBoughtDetails(Date frmDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(BookSupplier.class)
				.add(Restrictions.ge("dateOfPurchase", frmDate)).list();
	}

	@Override
	public List<BookSupplier> getBoughtDetailsByDate(Date frmDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(BookSupplier.class)
				.add(Restrictions.eq("dateOfPurchase", frmDate)).list();
	}

	@Override
	public List<BookSupplier> getOverallExpenseByPageNo(int pageNo) {
		return sessionFactory.getCurrentSession()
				.createCriteria(BookSupplier.class)
				.add(Restrictions.eq("active", true)).setMaxResults(5)
				.setFirstResult(pageNo * 5).list();
	}

	@Override
	public List<BookSupplier> getTodaysExpenses(Date date) {
		System.out.println(date);
		return (List<BookSupplier>) (sessionFactory.getCurrentSession()
				.createCriteria(BookSupplier.class).add(Restrictions.eq(
				"dateOfPurchase", date))).add(Restrictions.eq("active", true))
				.list();
	}

	@Override
	public List<BookSupplier> findDatewiseExpense(Date fromDate, Date toDate) {
		return (List<BookSupplier>) (sessionFactory.getCurrentSession()
				.createCriteria(BookSupplier.class).add(Restrictions.between(
				"dateOfPurchase", fromDate, toDate))).add(
				Restrictions.eq("active", true)).list();
	}

	@Override
	public BookSupplier getLastpaymenttoSupplier(String supplierName,
			String supplierNo) {
		return (BookSupplier) sessionFactory.getCurrentSession()
				.createCriteria(BookSupplier.class)
				.add(Restrictions.eq("supplierName", supplierName))
				.add(Restrictions.eq("supplierNo", supplierNo))
				.addOrder(Order.desc("stockPurchaseId")).setMaxResults(1)
				.uniqueResult();
	}
}
