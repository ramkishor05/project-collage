package org.brijframework.college.controller.admin.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.common.constant.CommonConstants;
import org.brijframework.college.models.dto.EmployeesDTO;
import org.brijframework.college.models.dto.FeecategoryAmountDTO;
import org.brijframework.college.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class MangeEmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "change-emp-password-page", method = RequestMethod.GET)
	public String changeEmployeePasswordPage(HttpServletRequest request,
			@RequestParam int id, ModelMap model) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		String returnPage = "";

		String USER_ROLE = (String) session.getAttribute(CommonConstants.ROLE);
		Integer EMPLOYER_ID = (Integer) session
				.getAttribute(CommonConstants.EMPLOYER_ID);
		FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
		feecategoryAmountDTO.setEmployeeId(id);
		if (USER_ROLE.equals("ROLE_ADMIN")) {
			model.addAttribute("FeecategoryAmountDTO", feecategoryAmountDTO);
			returnPage = "changeemppasswordpage";
		} else {
			if (EMPLOYER_ID == id) {
				model.addAttribute("FeecategoryAmountDTO", feecategoryAmountDTO);
				returnPage = "changeemppasswordpage";
			} else {
				returnPage = "home?msg=You Have no Authority To Do This";
			}
		}
		return returnPage;
	}

	@RequestMapping(value = "change-emp-password", method = RequestMethod.GET)
	public String changeEmployeePassword(HttpServletRequest request,
			ModelMap model) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		String returnPage = "";
		String USER_ROLE = (String) session.getAttribute(CommonConstants.ROLE);
		if (USER_ROLE.equals("ROLE_ADMIN")) {
			model.addAttribute("employeeDTO", new EmployeesDTO());
			returnPage = "employeedetails";
		} else {
			model.addAttribute("FeecategoryAmountDTO",
					new FeecategoryAmountDTO());
			returnPage = "home";
		}
		return returnPage;
	}

	@RequestMapping(value = "change-emp-password.html", method = RequestMethod.POST)
	public String changeEmpPassword(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute FeecategoryAmountDTO feecategoryAmountDTO,
			ModelMap model) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		FeecategoryAmountDTO amountDTO = new FeecategoryAmountDTO();
		amountDTO.setEmployeeId(feecategoryAmountDTO.getEmployeeId());
		String returnPage = "";

		String USER_ROLE = (String) session.getAttribute(CommonConstants.ROLE);
		String result = "";
		if (USER_ROLE.equals("ROLE_EMPLOYEE")) {
			result = employeeService
					.changeemployeepassword(feecategoryAmountDTO);
			returnPage = "redirect:/logout";

		} else {
			result = employeeService
					.changeemployeepassword(feecategoryAmountDTO);
			if (result.equals("done")) {
				model.addAttribute("msg", "Password Changed Successfully");
			}
			if (result.equals("nomatch")) {

				model.addAttribute("msg",
						"New Password and confirm Password doesnot match");

			} else if (result.equals("wrong")) {

				model.addAttribute("msg", "You Have Entered Wrong Password");
			}
			model.addAttribute("FeecategoryAmountDTO", amountDTO);
			returnPage = "changeemppasswordpage";
		}
		return returnPage;
	}
}
