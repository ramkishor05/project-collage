package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.UserRoleDao;
import org.brijframework.college.model.UserRole;
import org.brijframework.college.model.UserRoleId;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("userRoleDao")
public class UserRoleDaoImpl extends DAOImpl<UserRoleId, UserRole> implements
		UserRoleDao {

	@Transactional
	public UserRole getByUserIdAndRoleId(int userId, int roleId) {
		return (UserRole) (sessionFactory.getCurrentSession()
				.createCriteria(UserRole.class)
				.add(Restrictions.eq("userRolePrimaryKey.role.id", roleId))
				.add(Restrictions.eq("userRolePrimaryKey.user.id", userId)))
				.uniqueResult();
	}

}
