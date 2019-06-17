package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.BookSellDao;
import org.brijframework.college.model.BookSell;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("bookSellDao")
@SuppressWarnings("unchecked")
public class BookSellDaoImpl extends DAOImpl<Integer, BookSell> implements
		BookSellDao {

	@Override
	public BookSell getReceipt() {
		return (BookSell) sessionFactory.getCurrentSession()
				.createCriteria(BookSell.class)
				.add(Restrictions.ne("receiptNo", 0))
				.addOrder(Order.desc("receiptNo")).setMaxResults(1)
				.uniqueResult();
	}

	@Override
	public List<BookSell> getReceiptData(int receiptNo) {
		return (List<BookSell>) sessionFactory.getCurrentSession()
				.createCriteria(BookSell.class)
				.add(Restrictions.eq("receiptNo", receiptNo)).list();
	}

	@Override
	public List<BookSell> getSoldDetails(Date from, Date to) {
		return (List<BookSell>) sessionFactory.getCurrentSession()
				.createCriteria(BookSell.class)
				.add(Restrictions.ne("receiptNo", 0))
				.add(Restrictions.between("payDate", from, to)).list();
	}

	@Override
	public List<BookSell> getStudentBookDues(String studentAdmissionNo) {
		return (List<BookSell>) sessionFactory
				.getCurrentSession()
				.createCriteria(BookSell.class)
				.add(Restrictions.gt("dues", 0))
				.add(Restrictions.eq("duesReceiptNo", 0))
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo)).list();
	}

	@Override
	public List<BookSell> findDues(Integer receiptNo) {
		return (List<BookSell>) sessionFactory.getCurrentSession()
				.createCriteria(BookSell.class)
				.add(Restrictions.eq("duesReceiptNo", receiptNo))

				.list();
	}

	@Override
	public BookSell getBookDuesReceiptData(int receiptNo) {
		return (BookSell) sessionFactory.getCurrentSession()
				.createCriteria(BookSell.class)
				.add(Restrictions.eq("receiptNo", receiptNo)).uniqueResult();
	}

	@Override
	public BookSell getBookStudentDetails(Integer duesReceiptNo) {
		return (BookSell) sessionFactory.getCurrentSession()
				.createCriteria(BookSell.class).setMaxResults(1)
				.add(Restrictions.eq("receiptNo", duesReceiptNo))
				.uniqueResult();
	}

	@Override
	public List<BookSell> getStudentBook(String studentAdmissionNo) {
		return (List<BookSell>) sessionFactory
				.getCurrentSession()
				.createCriteria(BookSell.class)
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo)).list();
	}

	@Override
	public List<BookSell> getBooksSold(int feeId) {
		return (List<BookSell>) sessionFactory.getCurrentSession()
				.createCriteria(BookSell.class)
				.add(Restrictions.eq("studentFee.studentWiseFeeId", feeId))
				.list();
	}

	@Override
	public List<BookSell> getSoldDetails(Date date) {
		return (List<BookSell>) sessionFactory.getCurrentSession()
				.createCriteria(BookSell.class).addOrder(Order.desc("dues"))
				.add(Restrictions.eq("duesReceiptNo", 0))
				.add(Restrictions.eq("payDate", date)).list();
	}

	@Override
	public Long findSizeOfTable() {
		return (Long) sessionFactory.getCurrentSession()
				.createCriteria(BookSell.class)
				.setProjection(Projections.count("bookPaymentId"))
				.add(Restrictions.ne("receiptNo", 0)).uniqueResult();
	}

	@Override
	public List<BookSell> getSoldDetailsbypage(int i) {
		return (List<BookSell>) sessionFactory.getCurrentSession()
				.createCriteria(BookSell.class)
				.add(Restrictions.ne("receiptNo", 0))
				.setFirstResult(i*10)
				.setMaxResults(10)
				.list();
	}

	@Override
	public List<BookSell> getTodaySoldDetails(Date date) {
		return (List<BookSell>) sessionFactory.getCurrentSession()
				.createCriteria(BookSell.class)
				.add(Restrictions.ne("receiptNo", 0))
				.add(Restrictions.eq("payDate", date)).list();
	}
}
