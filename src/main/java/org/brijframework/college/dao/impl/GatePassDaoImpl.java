package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.GatePassDao;
import org.brijframework.college.model.GatePass;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("gatePassDao")
public class GatePassDaoImpl extends DAOImpl<Integer, GatePass> implements
		GatePassDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<GatePass> findPreviousDetailsById(String id) {
		return sessionFactory.getCurrentSession()
				.createCriteria(GatePass.class)
				.add(Restrictions.eq("students.studentAdmissionNo", id)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GatePass> findDateWiseDetails(Date leavingDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(GatePass.class)
				.add(Restrictions.eq("leavingDate", leavingDate)).list();
	}

}
