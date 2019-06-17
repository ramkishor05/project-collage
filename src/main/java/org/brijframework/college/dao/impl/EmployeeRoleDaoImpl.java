package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.EmployeeRoleDao;
import org.brijframework.college.model.EmployeeRole;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("EmployeeRoleDao")
@SuppressWarnings("unchecked")
public class EmployeeRoleDaoImpl extends DAOImpl<Integer, EmployeeRole>
		implements EmployeeRoleDao {

	@Override
	public List<EmployeeRole> getAllActiveEmployeeRole() {
		return sessionFactory.getCurrentSession()
				.createCriteria(EmployeeRole.class)
				.add(Restrictions.eq("active", true)).list();
	}

	@Override
	public EmployeeRole getgetRoleDTOByRole(String roleName) {
		return (EmployeeRole) sessionFactory.getCurrentSession()
				.createCriteria(EmployeeRole.class)
				.add(Restrictions.eq("roleName", roleName).ignoreCase())
				.add(Restrictions.eq("active", true)).uniqueResult();
	}

}
