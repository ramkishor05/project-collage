package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.ExpenseDao;
import org.brijframework.college.model.Expense;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("ExpenseDao")
public class ExpenseDaoImpl extends DAOImpl<Integer, Expense> implements
		ExpenseDao {

	@SuppressWarnings("unchecked")
	public List<Expense> getOverallByPageNo(int pageNo) {
		return sessionFactory.getCurrentSession().createCriteria(Expense.class)
				.add(Restrictions.eq("active", true)).setMaxResults(5)
				.setFirstResult(pageNo * 5).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expense> findtodaysexpense(Date date) {
		return (List<Expense>) (sessionFactory.getCurrentSession()
				.createCriteria(Expense.class).add(Restrictions
				.eq("date", date))).add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expense> finddatewiseexpense(Date from, Date to) {
		return (List<Expense>) (sessionFactory.getCurrentSession()
				.createCriteria(Expense.class).add(Restrictions.between("date",
				from, to))).add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expense> findyearlyexpense(Date from, Date to) {
		return (List<Expense>) (sessionFactory.getCurrentSession()
				.createCriteria(Expense.class).add(Restrictions.between("date",
				from, to))).add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findEmployeebyRole(String role) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(Expense.class)
				.setProjection(
						Projections.distinct(Projections
								.property("employeeName")))
				.add(Restrictions.eq("employeeRole", role)).list();
	}

}
