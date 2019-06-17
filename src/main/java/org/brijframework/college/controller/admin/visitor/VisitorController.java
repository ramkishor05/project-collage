package org.brijframework.college.controller.admin.visitor;

import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;

import org.brijframework.college.model.VisitorsDetails;
import org.brijframework.college.service.VisitorsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class VisitorController {
	@Autowired
	private VisitorsDetailsService visitorsDetailsService;

	@RequestMapping(value = "visitor.html")
	public String visitor(
			@RequestParam(required = false, defaultValue = "") String date,
			ModelMap model) throws Exception {
		model.addAttribute("VisitorsDetails", new VisitorsDetails());
		model.addAttribute("map",
				visitorsDetailsService.getVisitorsDetailsByDate(date));
		return "visitor";
	}

	@RequestMapping(value = "visitor.html", method = RequestMethod.POST)
	public @ResponseBody String visitorDateWise(@RequestParam String date,
			ModelMap model) throws Exception {
		System.out.println(date);
		return new ObjectMapper().writeValueAsString(visitorsDetailsService
				.getVisitorsDetailsByDate(date));
	}

	@RequestMapping(value = "add-visitors.html", method = RequestMethod.POST)
	public String addVisitors(@ModelAttribute VisitorsDetails visitorsDetails,
			RedirectAttributes redirectAttributes) {
		visitorsDetailsService.addVisitorsDetails(visitorsDetails);
		redirectAttributes.addFlashAttribute("msg", "Vistior Add SuccessFully");
		return "redirect:visitor.html";

	}

	@RequestMapping(value = "update-visitors.html", method = RequestMethod.GET)
	public String updateVisitors(@RequestParam int visitorId,
			@RequestParam String outDate, RedirectAttributes redirectAttributes)
			throws ParseException {
		VisitorsDetails visitorsDetails = visitorsDetailsService
				.read(visitorId);
		Time outTime = new Time(Calendar.getInstance().getTimeInMillis());
		visitorsDetails.setOutTime(outTime);
		visitorsDetailsService.update(visitorsDetails);
		redirectAttributes.addFlashAttribute("msg",
				"Vistior Update SuccessFully");
		return "redirect:visitor.html?date="+outDate;

	}

	@RequestMapping(value = "filter-visitors.html", method = RequestMethod.POST)
	public @ResponseBody String filterVisitorWise(
			@RequestParam String visitorName, ModelMap model) throws Exception {
		return new ObjectMapper().writeValueAsString(visitorsDetailsService
				.getVisitorsDetailsByName(visitorName));
	}
}
