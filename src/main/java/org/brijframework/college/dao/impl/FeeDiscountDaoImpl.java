package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.FeeDiscountDao;
import org.brijframework.college.model.FeeDiscount;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class FeeDiscountDaoImpl extends DAOImpl<Integer, FeeDiscount> implements
		FeeDiscountDao {

	@Override
	public List<FeeDiscount> getFeeDiscountDTOs(int classId, int sectioId,
			String studentAdmissionNo, Date fromDate, Date toDate) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(FeeDiscount.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectioId))
				.add(Restrictions.ge("fromDate", fromDate))
				.add(Restrictions.le("toDate", toDate))
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo)).list();
	}

	@Override
	public List<FeeDiscount> getFeeDiscountDTOs(int classId, int sectioId,
			String studentAdmissionNo, Date lastDate) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(FeeDiscount.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectioId))
				.add(Restrictions.eq("lastDate", lastDate))
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo)).list();
	}

	@Override
	public List<FeeDiscount> getDiscountAmountByMonth(Date frmDate, Date toDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(FeeDiscount.class)
				.add(Restrictions.ge("fromDate", frmDate))
				.add(Restrictions.le("toDate", toDate)).list();
	}

}
