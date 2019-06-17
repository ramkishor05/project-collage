package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.BookPurchaseDao;
import org.brijframework.college.model.BookPurchase;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("bookPurchaseDao")
public class BookPurchaseDaoImpl extends DAOImpl<Integer, BookPurchase>
		implements BookPurchaseDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BookPurchase> findCurrentSessionPurchases(int sessionId) {
		return sessionFactory.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.addOrder(Order.desc("bookPurchaseId"))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findBookList(Integer sessionId, int id) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", id))
				.setProjection(
						Projections.distinct(Projections.property("bookTitle")))
				.add(Restrictions.eq("active", true)).list();
	}

	@Override
	public BookPurchase findbookDetails(String name, Integer sessionId, int id) {
		return (BookPurchase) sessionFactory.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", id))
				.add(Restrictions.eq("bookTitle", name))
				.add(Restrictions.gt("remainingQuantity", 0)).setMaxResults(1)
				.addOrder(Order.asc("bookPurchaseId"))
				.add(Restrictions.eq("active", true)).uniqueResult();
	}

	@Override
	public BookPurchase findoutofStockBook(String name, Integer sessionId,
			int id) {
		return (BookPurchase) sessionFactory.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", id))
				.add(Restrictions.eq("remainingQuantity", 0))
				.add(Restrictions.eq("bookTitle", name)).setMaxResults(1)
				.addOrder(Order.desc("bookPurchaseId"))
				.add(Restrictions.eq("active", true)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookPurchase> findbookstock(int sessionId, int classId,
			String name) {
		return sessionFactory.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.ge("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("bookTitle", name))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findbookbysubject(int subjectId, Integer sessionId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.ge("session.sessionId", sessionId))
				.add(Restrictions.eq("subject.subjectsId", subjectId))
				.setProjection(
						Projections.distinct(Projections.property("bookTitle")))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookPurchase> getbookBoughtDetails(int stockPurchaseId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.eq("stocksupplier.stockPurchaseId",
						stockPurchaseId)).add(Restrictions.eq("active", true))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookPurchase> findbookbysubject(int subjectId,
			Integer sessionId, String name) {
		return sessionFactory.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.ge("session.sessionId", sessionId))
				.add(Restrictions.eq("subject.subjectsId", subjectId))
				.add(Restrictions.eq("bookTitle", name))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookPurchase> findBooksbyclass(int classId, int sessionId) {
		return sessionFactory.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findBookListclassSection(Integer sessionId,
			int classId, int sectionId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.setProjection(
						Projections.distinct(Projections.property("bookTitle")))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("active", true)).list();
	}

	@Override
	public BookPurchase findbookDetailsnew(String name, Integer sessionId,
			int classId, int sectionId) {
		return (BookPurchase) sessionFactory.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("bookTitle", name))
				.add(Restrictions.gt("remainingQuantity", 0))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.setMaxResults(1).addOrder(Order.asc("bookPurchaseId"))
				.add(Restrictions.eq("active", true)).uniqueResult();
	}

	@Override
	public BookPurchase findoutofStockBooknew(String name, Integer sessionId,
			int classId, int sectionId) {
		return (BookPurchase) sessionFactory.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("remainingQuantity", 0))
				.add(Restrictions.eq("bookTitle", name)).setMaxResults(1)
				.addOrder(Order.desc("bookPurchaseId"))
				.add(Restrictions.eq("active", true)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookPurchase> findBooksbyclasssection(int sessionId,
			int classId, int sectionId) {
		return sessionFactory.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookPurchase> findbookstocknew(int sessionId, int classId,
			int sectionId, String name) {
		return sessionFactory.getCurrentSession()
				.createCriteria(BookPurchase.class)
				.add(Restrictions.ge("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("bookTitle", name))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("active", true)).list();
	}

}
