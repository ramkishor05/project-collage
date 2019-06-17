package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.LoginRoleDao;
import org.brijframework.college.model.LoginRole;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("roleDao")
public class LoginRoleDaoImpl extends DAOImpl<Integer, LoginRole> implements
		LoginRoleDao {

	@Override
	public LoginRole getUserRoleByName(String name) {
		return (LoginRole) (sessionFactory.getCurrentSession().createCriteria(
				LoginRole.class).add(Restrictions.eq("name", name)))
				.uniqueResult();
	}

}
