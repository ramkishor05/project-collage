package org.brijframework.college.commom.convertor;

import org.brijframework.college.model.EmployeeRole;
import org.brijframework.college.models.dto.RoleDTO;

public class EmployeeRoleEntityToDTO {
	private static final EmployeeRoleEntityToDTO employeeRoleEntityToDTO = new EmployeeRoleEntityToDTO();

	public static final EmployeeRoleEntityToDTO getInstance() {
		return employeeRoleEntityToDTO;
	}

	public RoleDTO convertEntityToDTO(EmployeeRole employeeRole) {
		RoleDTO roleDTO = null;
		if (employeeRole != null) {
			roleDTO = new RoleDTO();
			roleDTO.setDelete(employeeRole.isActive());
			roleDTO.setId(employeeRole.getEmployeeRoleId());
			roleDTO.setRoleName(employeeRole.getRoleName());
		}
		return roleDTO;
	}
}
