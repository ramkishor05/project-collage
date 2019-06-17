package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.DressPaymentDao;
import org.brijframework.college.model.DressPayment;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("dressPaymentDao")
public class DressPaymentDaoImpl extends DAOImpl<Integer, DressPayment> implements DressPaymentDao {

	@Override
	public DressPayment getReceipt() {
		return (DressPayment) sessionFactory.getCurrentSession()
				.createCriteria(DressPayment.class)
				 .add(Restrictions.ne("receiptNo", 0))
				.addOrder(Order.desc("receiptNo")).setMaxResults(1)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DressPayment> getSoldDetails(Date from, Date to) {
		return (List<DressPayment>)sessionFactory
				.getCurrentSession()
				.createCriteria(DressPayment.class)
				 .add(Restrictions.ne("receiptNo", 0))
				.add(Restrictions.between("payDate", from, to)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DressPayment> getReceiptData(int receiptNo) {
		return (List<DressPayment>)sessionFactory
				.getCurrentSession()
				.createCriteria(DressPayment.class)
				.add(Restrictions.eq("receiptNo", receiptNo)).list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<DressPayment> getStudentDues(String studentAdmissionNo) {
		return (List<DressPayment>)sessionFactory
				.getCurrentSession()
				.createCriteria(DressPayment.class)
				.add(Restrictions.gt("dues", 0))
                .add(Restrictions.eq("duesReceiptNo", 0))
				.add(Restrictions.eq("students.studentAdmissionNo",studentAdmissionNo)).list();
	}

	@Override
	public DressPayment getDuesReceiptData(int receiptNo) {
		return (DressPayment)sessionFactory
				.getCurrentSession()
				.createCriteria(DressPayment.class)
				.add(Restrictions.eq("receiptNo", receiptNo)).uniqueResult();
	}

	@Override
	public DressPayment getStudentDetails(Integer duesReceiptNo) {
		return (DressPayment)sessionFactory
				.getCurrentSession()
				.createCriteria(DressPayment.class)
				.setMaxResults(1)
			.add(Restrictions.eq("receiptNo", duesReceiptNo)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DressPayment> findDues(Integer receiptNo) {
		return (List<DressPayment>)sessionFactory
				.getCurrentSession()
				.createCriteria(DressPayment.class)
                .add(Restrictions.eq("duesReceiptNo", receiptNo))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DressPayment> getSoldListbyId(Integer product) {
		return (List<DressPayment>)sessionFactory
				.getCurrentSession()
				.createCriteria(DressPayment.class)
                .add(Restrictions.eq("uniformPurchase.productPurchaseId", product))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DressPayment> getStudentUniformSold(String studentAdmissionNo) {
		return (List<DressPayment>)sessionFactory
				.getCurrentSession()
				.createCriteria(DressPayment.class)
				.add(Restrictions.eq("students.studentAdmissionNo",studentAdmissionNo)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DressPayment> getUniformsSold(int feeId) {
		return (List<DressPayment>)sessionFactory
				.getCurrentSession()
				.createCriteria(DressPayment.class)
				.add(Restrictions.eq("studentFee.studentWiseFeeId",feeId)).list();
	}

	@Override
	public Long findSizeOfTable() {
		return (Long) sessionFactory.getCurrentSession()
				.createCriteria(DressPayment.class)
				.setProjection(Projections.count("dressPaymentId"))
				.add(Restrictions.ne("receiptNo", 0)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DressPayment> getSoldDetailsbyPage(int page) {
		return (List<DressPayment>)sessionFactory
				.getCurrentSession()
				.createCriteria(DressPayment.class)
				 .add(Restrictions.ne("receiptNo", 0))
				 .setFirstResult(page*10)
				 .setMaxResults(10)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DressPayment> getTodaysCollection(Date parse) {
		return (List<DressPayment>)sessionFactory
				.getCurrentSession()
				.createCriteria(DressPayment.class)
				 .add(Restrictions.ne("receiptNo", 0))
				.add(Restrictions.eq("payDate", parse)).list();
	}

	
	}


