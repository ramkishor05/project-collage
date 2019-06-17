package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.EmployeeRole;

public interface EmployeeRoleDao extends DAO<Integer, EmployeeRole> {
	public List<EmployeeRole> getAllActiveEmployeeRole();
	public EmployeeRole getgetRoleDTOByRole(String roleName);
}
