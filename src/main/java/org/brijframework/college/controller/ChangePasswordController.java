package org.brijframework.college.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.common.constant.CommonConstants;
import org.brijframework.college.model.User;
import org.brijframework.college.model.util.PasswordEncoder;
import org.brijframework.college.models.dto.FeecategoryAmountDTO;
import org.brijframework.college.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class ChangePasswordController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "change-password.html", method = RequestMethod.GET)
	public String chabgePassword(ModelMap model) {
		model.addAttribute("FeecategoryAmountDTO", new FeecategoryAmountDTO());
		return "changepassword";
	}

	@RequestMapping(value = "change-password.html", method = RequestMethod.POST)
	public String chabgePasswordConfirm(ModelMap model,
			@ModelAttribute FeecategoryAmountDTO feecategoryAmountDTO,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute(CommonConstants.ROLE);
		User user = userService.checkUserByUserName(
				feecategoryAmountDTO.getFeeCategoryName(),
				feecategoryAmountDTO.getPassword());
		if (!feecategoryAmountDTO.getNewPassword().equals(
				feecategoryAmountDTO.getConfirmPassword())) {
			model.addAttribute("msg",
					"Confirmpassword and newPassword not match");
			model.addAttribute("FeecategoryAmountDTO",
					new FeecategoryAmountDTO());
			return "changepassword";
		}
		if (user == null) {
			model.addAttribute("msg", "You have entered wrong Username or Password");
			model.addAttribute("FeecategoryAmountDTO",
					new FeecategoryAmountDTO());
			return "changepassword";
		} else {
			user.setPassword(PasswordEncoder
					.getEcodedPassword(feecategoryAmountDTO.getNewPassword()));
			userService.update(user);
			model.addAttribute("info", "Password changed successfully");
			if (role.equals("ROLE_ADMIN")) {
				return "changepassword";
			} else {
				return "mainadminhome";
			}
		}
	}
}
