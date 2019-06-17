package org.brijframework.college.dao;

import org.brijframework.college.model.UserRole;
import org.brijframework.college.model.UserRoleId;

public interface UserRoleDao extends DAO<UserRoleId, UserRole> {

	UserRole getByUserIdAndRoleId(int userId,int roleId);
}
