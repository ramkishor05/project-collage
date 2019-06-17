package org.brijframework.college.controller.admin.settings;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.Country;
import org.brijframework.college.models.dto.CountryDTO;
import org.brijframework.college.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admin/**")
public class ManageCountryController {
	@Autowired
	CountryService countryService;

	@RequestMapping(value = "/manage-country", method = RequestMethod.GET)
	public String showSetting(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "managecountry";
	}

	@RequestMapping(value = "create-country", method = RequestMethod.GET)
	public String createCountry(Model model, HttpServletRequest request,
			@RequestParam(required = false) String msg) {
		model.addAttribute("countryDTO", new CountryDTO());
		model.addAttribute("msg", msg);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "createnewcountry";
	}

	@RequestMapping(value = "/add-country", method = RequestMethod.POST)
	public void addCountry(Model model,
			@ModelAttribute("countryDTO") CountryDTO countryDTO,
			HttpServletResponse response) throws IOException {
		countryService.addCountry(countryDTO);
		response.sendRedirect("create-country?msg=Country Successfully Inserted");
	}

	@RequestMapping(value = "/show-country", method = RequestMethod.GET)
	public String showAllCountry(Model model, HttpServletRequest request) {
		List<Country> countries = countryService
				.findAllByIsDeletedTrue(Country.class);
		model.addAttribute("countrylist", countries);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "showcountry";
	}

	@RequestMapping(value = "/editcountry", method = RequestMethod.GET)
	public String editCourses(@RequestParam("id") Integer id, Model model,
			HttpServletRequest request) {
		model.addAttribute("countryDTO", new CountryDTO());
		Country country = countryService.read(id);
		model.addAttribute("countryName", country.getCountryName());
		model.addAttribute("countryCode", country.getCountryCode());
		model.addAttribute("id", id);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "updatecountry";
	}

	@RequestMapping(value = "/update-country", method = RequestMethod.POST)
	public void updateCourses(
			@ModelAttribute("countryDTO") CountryDTO countryDTO,
			HttpServletResponse response) throws IOException {
		countryService.updateCountry(countryDTO);
		response.sendRedirect("show-country");
	}

	@RequestMapping(value = "/delete-country", method = RequestMethod.GET)
	public String deleteCourses(Model model, HttpServletRequest request) {
		List<Country> countries = countryService
				.findAllByIsDeletedTrue(Country.class);
		model.addAttribute("countrylist", countries);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");

		return "deletecountry";
	}

	@RequestMapping(value = "/deletecountry", method = RequestMethod.GET)
	public String delCourses(@RequestParam("id") Integer id,
			HttpServletResponse response, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws IOException {
		countryService.deleteById(id);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		redirectAttributes.addFlashAttribute("msg", "Country Successfully Deleted");
		return "redirect:delete-country";
	}

}
