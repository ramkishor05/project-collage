package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.AdvanceSalaryDao;
import org.brijframework.college.model.AdvanceSalary;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("advanceSalaryDao")
@SuppressWarnings("unchecked")
public class AdvanceSalaryDaoImpl extends DAOImpl<Integer, AdvanceSalary>
		implements AdvanceSalaryDao {

	@Override
	public List<AdvanceSalary> findAdvancebyEmployeeId(int employeeId,
			int sessionId) {
		return (List<AdvanceSalary>) (sessionFactory.getCurrentSession()
				.createCriteria(AdvanceSalary.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("employees.employeeId", employeeId))
				.list());
	}

	@Override
	public List<AdvanceSalary> findAdvanceformonth(int sessionId,
			Integer monthId, int employeeId) {
		return (List<AdvanceSalary>) (sessionFactory.getCurrentSession()
				.createCriteria(AdvanceSalary.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("month.monthId", monthId))
				.add(Restrictions.eq("employees.employeeId", employeeId))
				.list());
	}

	@Override
	public List<AdvanceSalary> getTodaysAdvance(Date Date) {
		return (List<AdvanceSalary>) (sessionFactory.getCurrentSession()
				.createCriteria(AdvanceSalary.class)
				.add(Restrictions.eq("dateOfPayment", Date)).list());
	}

	@Override
	public List<AdvanceSalary> getTodaysAdvance(Date from, Date to) {
		return (List<AdvanceSalary>) (sessionFactory.getCurrentSession()
				.createCriteria(AdvanceSalary.class)
				.add(Restrictions.ge("dateOfPayment", from))
				.add(Restrictions.le("dateOfPayment", to)).list());
	}

	@Override
	public List<AdvanceSalary> getByPageNo(int pageNo) {
		return (List<AdvanceSalary>) (sessionFactory.getCurrentSession()
				.createCriteria(AdvanceSalary.class)
				.add(Restrictions.eq("active", true)).setMaxResults(5)
				.setFirstResult(pageNo * 5)).list();
	}

}
