package org.brijframework.college.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.common.constant.CommonConstants;
import org.brijframework.college.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class DataBackupController {
	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "backup.html", method = RequestMethod.GET)
	public String getDirectoryPath(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute(CommonConstants.ROLE);
		String testPath=this.getClass().getResource("/export.bat").toString();
	         boolean flage = countryService.generateBackup(testPath);
		if (flage) {
			model.addAttribute("msg",
					"backup created successfully in D:/schooldata.sql ");
		} else {
			model.addAttribute("msg", "backup not created");
		}
		if (role.equals("ROLE_ADMIN")) {
			return "redirect:home";
		} else {
			return "mainadminhome";
		}
	}
	@RequestMapping(value = "restore.html", method = RequestMethod.GET)
	public String getRestore(ModelMap model, HttpServletRequest request) throws InterruptedException {
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute(CommonConstants.ROLE);
		String testPath=this.getClass().getResource("/import.bat").toString();
		boolean flage = countryService.restoreBackup(testPath);
		if (flage) {
			model.addAttribute("msg",
					"Data Restored Successfully ");
		} else {
			model.addAttribute("msg", "Restore not completed");
		}
		if (role.equals("ROLE_ADMIN")) {
			return "redirect:home";
		} else {
			return "mainadminhome";
		}
	}
}
