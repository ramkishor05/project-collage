package org.brijframework.college.controller.admin.settings;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.models.dto.RoleDTO;
import org.brijframework.college.service.EmployeeRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/admin/**")
public class SettingsController {
	@Autowired
	EmployeeRoleService employeeRoleService;

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String showSetting(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "configuration";
	}

	@RequestMapping(value = "check_employee_role", method = RequestMethod.POST)
	public @ResponseBody
	String checkForEmployeeRole(@RequestParam String roleName)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(employeeRoleService
				.getgetRoleDTOByRole(roleName));
	}
	
	@RequestMapping(value = "/add-employee-role", method = RequestMethod.GET)
	public String viewEmployeeRole(@RequestParam(required = false) String msg,
			Model model, HttpServletRequest request) {
		model.addAttribute("roleDTO", new RoleDTO());
		model.addAttribute("EmployeeRoles",
				employeeRoleService.getAllActiveEmployeeRole());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		model.addAttribute("msg", msg);
		return "add-employee-role";
	}

	@RequestMapping(value = "getemployeerole", method = RequestMethod.POST)
	public @ResponseBody String getEmployeeRole(@RequestParam int roleId)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(employeeRoleService
				.getRoleDTOById(roleId));
	}

	@RequestMapping(value = "update-employee-role.html", method = RequestMethod.POST)
	public void updateEmployeeRole(@ModelAttribute("roleDTO") RoleDTO dto,
			HttpServletResponse response) throws IOException {
		employeeRoleService.updateEmployeeRole(dto);
		response.sendRedirect("add-employee-role?msg=Role Updated Successfully");
	}

	@RequestMapping(value = "/add-role", method = RequestMethod.POST)
	public void addEmployeeRole(@ModelAttribute("roleDTO") RoleDTO dto,
			HttpServletResponse response) throws IOException {
		employeeRoleService.addEmployeeRole(dto);
		response.sendRedirect("add-employee-role?msg=Role Created Successfully");
	}

	@RequestMapping(value = "deleted-employee-role.html", method = RequestMethod.GET)
	public void deleteEmployeeRole(@RequestParam int roleId,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		employeeRoleService.deletedEmployeeRole(roleId);
		response.sendRedirect("add-employee-role?msg=Role Delete Successfully");
	}
}
