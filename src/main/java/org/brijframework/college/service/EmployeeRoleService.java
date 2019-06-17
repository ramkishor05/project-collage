package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.EmployeeRole;
import org.brijframework.college.models.dto.RoleDTO;

public interface EmployeeRoleService extends CRUDService<Integer, EmployeeRole> {
	void addEmployeeRole(RoleDTO dto);

	void updateEmployeeRole(RoleDTO dto);

	RoleDTO getRoleDTOById(int RoleId);

	List<EmployeeRole> getAllActiveEmployeeRole();
	
	void deletedEmployeeRole(int RoleId);
	
	RoleDTO getgetRoleDTOByRole(String roleName);
}
