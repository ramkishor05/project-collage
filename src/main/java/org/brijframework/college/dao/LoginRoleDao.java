package org.brijframework.college.dao;

import org.brijframework.college.model.LoginRole;

public interface LoginRoleDao extends DAO<Integer, LoginRole> {

	LoginRole getUserRoleByName(String name);
}
