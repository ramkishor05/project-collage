package org.brijframework.college.controller.admin.settings;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.City;
import org.brijframework.college.model.Country;
import org.brijframework.college.model.State;
import org.brijframework.college.models.dto.CityDTO;
import org.brijframework.college.service.CityService;
import org.brijframework.college.service.StateService;
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
public class ManageCityController {
	@Autowired
	CityService cityService;
	@Autowired
	private StateService stateService;

	@RequestMapping(value = "/manage-city", method = RequestMethod.GET)
	public String showSetting(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "managecity";
	}

	@RequestMapping(value = "/create-city", method = RequestMethod.GET)
	public String createCity(@RequestParam(required = false) String msg,
			Model model, HttpServletRequest request) {
		model.addAttribute("cityDTO", new CityDTO());
		List<Country> countries = cityService
				.findAllByIsDeletedTrue(Country.class);
		model.addAttribute("countrylist", countries);
		List<State> states = cityService.findAllByIsDeletedTrue(State.class);
		model.addAttribute("statelist", states);
		model.addAttribute("msg", msg);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "createnewcity";
	}

	@RequestMapping(value = "getstate", method = RequestMethod.POST)
	public @ResponseBody
	String getState(@RequestParam int countryId) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(stateService
				.getStateByCountryId(countryId));
	}

	@RequestMapping(value = "/add-city", method = RequestMethod.POST)
	public void addCity(Model model,
			@ModelAttribute("cityDTO") CityDTO cityDTO,
			HttpServletResponse response) throws IOException {
		cityService.addCity(cityDTO);
		response.sendRedirect("create-city?msg=City Successfully Inserted");
	}

	@RequestMapping(value = "/show-city", method = RequestMethod.GET)
	public String showAllCity(@RequestParam(required = false) String msg,
			Model model, HttpServletRequest request) {
		List<City> city = cityService.findAllByIsDeletedTrue(City.class);
		model.addAttribute("citylist", city);
		model.addAttribute("msg", msg);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "showcity";
	}

	@RequestMapping(value = "/editcity", method = RequestMethod.GET)
	public String editCity(@RequestParam("id") Integer id, Model model,
			HttpServletRequest request) {
		model.addAttribute("cityDTO", new CityDTO());
		CityDTO cityDTO = cityService.getCityById(id);
		model.addAttribute("cityName", cityDTO.getCityName());
		model.addAttribute("cityCode", cityDTO.getCityCode());
		model.addAttribute("STATEID", cityDTO.getStateId());
		model.addAttribute("COUNTRYID", cityDTO.getCountryId());
		model.addAttribute("id", id);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "updatecity";
	}

	@RequestMapping(value = "/update-city", method = RequestMethod.POST)
	public void updateCity(@ModelAttribute("cityDTO") CityDTO cityDTO,
			HttpServletResponse response) throws IOException {
		cityService.updateCity(cityDTO);
		response.sendRedirect("show-city?msg=Updated City Successfully ");
	}

	@RequestMapping(value = "/delete-city", method = RequestMethod.GET)
	public String deleteBatch(@RequestParam(required = false) String msg,
			Model model, HttpServletRequest request) {
		List<City> city = cityService.findAllByIsDeletedTrue(City.class);
		model.addAttribute("citylist", city);
		model.addAttribute("msg", msg);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "deletecity";
	}

	@RequestMapping(value = "/deletecity", method = RequestMethod.GET)
	public void delCourses(@RequestParam("id") Integer id,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		cityService.deleteById(id);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		response.sendRedirect("delete-city?msg=Deleted City Successfully");
	}
}
