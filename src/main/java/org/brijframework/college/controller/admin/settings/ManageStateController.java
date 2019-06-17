package org.brijframework.college.controller.admin.settings;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.Country;
import org.brijframework.college.model.State;
import org.brijframework.college.models.dto.StateDTO;
import org.brijframework.college.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/**")
public class ManageStateController {
	@Autowired
	StateService stateService;

	@RequestMapping(value = "/manage-state", method = RequestMethod.GET)
	public String manageBranch(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "managestate";
	}

	@RequestMapping(value = "/create-state", method = RequestMethod.GET)
	public String createCourses(@RequestParam(required = false) String msg,
			Model model, HttpServletRequest request) {
		model.addAttribute("stateDTO", new StateDTO());
		List<Country> countries = stateService
				.findAllByIsDeletedTrue(Country.class);
		model.addAttribute("countrylist", countries);
		model.addAttribute("msg", msg);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "createnewstate";
	}

	@RequestMapping(value = "/add-state", method = RequestMethod.POST)
	public void addState(@ModelAttribute("stateDTO") StateDTO stateDTO,
			Model model, HttpServletResponse response) throws IOException {
		stateService.addState(stateDTO);
		response.sendRedirect("create-state?msg=State added successfully ");
	}

	@RequestMapping(value = "/update-state", method = RequestMethod.GET)
	public String showAllState(@RequestParam(required = false) String msg,
			Model model, HttpServletRequest request) {
		List<State> states = stateService.findAllByIsDeletedTrue(State.class);
		model.addAttribute("statelist", states);
		model.addAttribute("msg", msg);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "showstate";
	}

	@RequestMapping(value = "/editstate", method = RequestMethod.GET)
	public String editBranch(@RequestParam(required = false) String msg,
			@RequestParam("id") Integer id, Model model,
			HttpServletRequest request) {
		model.addAttribute("stateDTO", new StateDTO());
		StateDTO stateDTO = stateService.getStateByStateId(id);
		model.addAttribute("stateName", stateDTO.getStateName());
		model.addAttribute("stateCode", stateDTO.getStateCode());
		model.addAttribute("countryId", stateDTO.getCountryId());
		model.addAttribute("id", id);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "updatestate";
	}

	@RequestMapping(value = "/updatestate", method = RequestMethod.POST)
	public void updateBranch(@ModelAttribute("stateDTO") StateDTO stateDTO,
			HttpServletResponse response) throws IOException {
		stateService.updateState(stateDTO);
		response.sendRedirect("update-state?msg=State updated successfully ");
	}

	@RequestMapping(value = "/delete-state", method = RequestMethod.GET)
	public String deleteBranch(Model model, HttpServletRequest request,
			@RequestParam(required = false) String msg) {
		List<State> state = stateService.findAllByIsDeletedTrue(State.class);
		model.addAttribute("statelist", state);
		model.addAttribute("msg", msg);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "deletestate";
	}

	@RequestMapping(value = "/deletestate", method = RequestMethod.GET)
	public void delBranch(@RequestParam("id") Integer id,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		stateService.deleteById(id);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		response.sendRedirect("delete-state?msg=State name deleted successfully ");
	}

}
