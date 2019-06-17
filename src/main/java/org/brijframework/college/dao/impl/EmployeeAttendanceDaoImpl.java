package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.EmployeeAttendanceDao;
import org.brijframework.college.model.EmployeeAttendance;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class EmployeeAttendanceDaoImpl extends
		DAOImpl<Integer, EmployeeAttendance> implements EmployeeAttendanceDao {

	@Override
	public List<EmployeeAttendance> getRegisterByEmployeeId(Integer employeeId,
			Date frmDate, Date tDate) {
		return (List<EmployeeAttendance>) (sessionFactory.getCurrentSession()
				.createCriteria(EmployeeAttendance.class)
				.add(Restrictions.eq("employees.employeeId", employeeId))
				.add(Restrictions.between("dateOfAttendance", frmDate, tDate)))
				.list();
	}

	@Override
	public EmployeeAttendance getAttendanceReportByIdDate(int id,
			java.sql.Date sDate) {
		return (EmployeeAttendance) (sessionFactory.getCurrentSession()
				.createCriteria(EmployeeAttendance.class)
				.add(Restrictions.eq("employees.employeeId", id))
				.add(Restrictions.eq("dateOfAttendance", sDate)))
				.uniqueResult();
	}

	@Override
	public List<EmployeeAttendance> getEmployeeAttendanceList(int employeeId,
			java.util.Date frmDate, java.util.Date tDate) {
		return (List<EmployeeAttendance>) (sessionFactory.getCurrentSession()
				.createCriteria(EmployeeAttendance.class)
				.add(Restrictions.eq("employees.employeeId", employeeId))
				.add(Restrictions.between("dateOfAttendance", frmDate, tDate)))
				.list();
	}

	@Override
	public List<Date> totalSchoolOpenDays(Date frmDate, Date tDate) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(EmployeeAttendance.class)
				.setProjection(
						Projections.distinct(Projections
								.property("dateOfAttendance")))
				.addOrder(Order.asc("dateOfAttendance"))
				.add(Restrictions.ge("dateOfAttendance", frmDate))
				.add(Restrictions.le("dateOfAttendance", tDate)).list();
	}

}
