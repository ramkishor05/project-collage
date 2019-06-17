package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.EmployeeSalaryDao;
import org.brijframework.college.model.EmployeeSalary;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("employeeSalaryDao")
public class EmployeeSalaryDaoImpl extends DAOImpl<Integer, EmployeeSalary>
		implements EmployeeSalaryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findAllPayedMonths(int employeeId, Integer sessionId) {

		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("employees.employeeId", employeeId))
				.add(Restrictions.ne("salaryPaidStatus", "Cancel")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeSalary> findEmployeePaidDetails(int employeeId,
			Integer sessionId, Integer ids) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
					.add(Restrictions.eq("session.sessionId", sessionId))
					.add(Restrictions.eq("month.monthId", ids))
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("employees.employeeId", employeeId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getallDistinctSlipNo(int sessionId, int employeeId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.setProjection(
						Projections.distinct(Projections.property("slipNo")))
				.add(Restrictions.eq("employees.employeeId", employeeId))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	public Integer getLastMonthIdOfSlip(Integer slipNo) {
		return (Integer) sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("slipNo", slipNo))
				.addOrder(Order.desc("month.monthId")).setMaxResults(1)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeSalary> getPrevsSalaryPaidDetails(int sessionId,
			Integer monthId, int employeeId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("month.monthId", monthId))
				.add(Restrictions.eq("employees.employeeId", employeeId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getMonthIdsBySlipNo(Integer slipNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("slipNo", slipNo)).list();
	}

	@Override
	public EmployeeSalary getNewSlipNo() {
		return (EmployeeSalary) sessionFactory.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.addOrder(Order.desc("slipNo")).setMaxResults(1)
				.uniqueResult();
	}

	@Override
	public EmployeeSalary findSingleSlipData(int slipNo) {
		return (EmployeeSalary) sessionFactory.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.add(Restrictions.eq("slipNo", slipNo)).setMaxResults(1)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findPayedMonthsfromSlip(int slipNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("slipNo", slipNo)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getallInhandDistinctSlipNo() {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.setProjection(
						Projections.distinct(Projections.property("slipNo")))
				.add(Restrictions.eq("salaryPaidStatus", "InHand")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeSalary> findAllDetailsofSlip(int slipNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.add(Restrictions.eq("slipNo", slipNo)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeSalary> getOverallSalaryByPageNo(int i) {
		return sessionFactory.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.add(Restrictions.eq("active", true)).setMaxResults(5)
				.setFirstResult(i * 5).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getallDistinctSlipNoByPageNo(int i) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.setProjection(
						Projections.distinct(Projections
								.property("slipNo")))
		                 .add(Restrictions.eq("active", true))
				.setMaxResults(5)
				.setFirstResult(i * 5)
				.add(Restrictions.ne("salaryPaidStatus", "Cancel")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getallDistinctSlipNoToday(Date today) {
		System.out.println(today);
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.setProjection(
						Projections.distinct(Projections.property("slipNo")))
						.add(Restrictions.ne("salaryPaidStatus", "Cancel"))
				     .add(Restrictions.eq("dateOfPayment", today)).list();

}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getallDistinctSlipNoByDate(Date from, Date to) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.setProjection(
						Projections.distinct(Projections.property("slipNo")))
						.add(Restrictions.ne("salaryPaidStatus", "Cancel"))
				     .add(Restrictions.between("dateOfPayment", from,to)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getallDistinctSlipNoByMonth(int monthId,
			Integer sessionId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.setProjection(
						Projections.distinct(Projections.property("slipNo")))
					.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("month.monthId", monthId)).list();
	}

	@Override
	public EmployeeSalary findLastPayedMonth(int employeeId) {
		return  (EmployeeSalary) sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.add(Restrictions.ne("salaryPaidStatus", "Cancel"))
				.add(Restrictions.eq("employees.employeeId", employeeId))
				.addOrder(Order.desc("employeeSalaryId")).setMaxResults(1)
				.uniqueResult();
	}

	@Override
	public EmployeeSalary findPaymentinCurrent(int employeeId, Integer sessionId) {
		return  (EmployeeSalary) sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeSalary.class)
				.add(Restrictions.ne("salaryPaidStatus", "Cancel"))
					.add(Restrictions.eq("session.sessionId", sessionId))
					.add(Restrictions.eq("employees.employeeId", employeeId))
				.addOrder(Order.desc("employeeSalaryId")).setMaxResults(1)
				.uniqueResult();
	}
}
