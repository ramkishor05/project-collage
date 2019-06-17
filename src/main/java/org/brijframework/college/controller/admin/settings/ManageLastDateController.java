package org.brijframework.college.controller.admin.settings;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.Month;
import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.LastDateDTO;
import org.brijframework.college.service.LastDateService;
import org.brijframework.college.service.MonthService;
import org.brijframework.college.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/admin/**")
public class ManageLastDateController {

	@Autowired
	LastDateService lastdateservice;
	@Autowired
	SessionService sessionService;
	@Autowired
	MonthService monthService;

	@RequestMapping(value = "manage-lastdates.html", method = RequestMethod.GET)
	public String getlastdate(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("months", monthService.findAll(Month.class));
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("LastDateDTO", new LastDateDTO());
		model.addAttribute("lastdates", lastdateservice
				.getLastDatesBySessionId(sessionService.findCurrent()
						.getSessionId()));
		session.setAttribute("active", "settings");
		return "managelastdates";
	}

	@RequestMapping(value = "update-lastdate.html", method = RequestMethod.POST)
	public String updateClass(@ModelAttribute LastDateDTO lastDateDTO,
			ModelMap model, HttpServletRequest request)
			throws ParseException, IOException {
		HttpSession session = request.getSession();
		String result=lastdateservice.changeLastDate(lastDateDTO);
		model.addAttribute("result", result);
		model.addAttribute("months", monthService.findAll(Month.class));
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("LastDateDTO", new LastDateDTO());
		model.addAttribute("lastdates", lastdateservice
				.getLastDatesBySessionId(sessionService.findCurrent()
						.getSessionId()));
		session.setAttribute("active", "settings");
		return "managelastdates";
	}

	@RequestMapping(value = "update-lastdate.html", method = RequestMethod.GET)
	public void updateClass(ModelMap model, HttpServletResponse response)
			throws IOException {
		response.sendRedirect("manage-lastdates.html");
	}

	@RequestMapping(value = "/getduedatebysessionid", method = RequestMethod.POST)
	public @ResponseBody
	String getDueDateList(@RequestParam("sessionId") int sessionId,
			HttpServletRequest request) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(lastdateservice
				.getLastDatesBySessionId(sessionId));
	}

	@RequestMapping(value = "create-lastdate.html", method = RequestMethod.POST)
	public String createDueDate(@ModelAttribute LastDateDTO lastDateDTO,
			ModelMap model, HttpServletRequest request) throws ParseException,
			IOException {
		HttpSession session = request.getSession();
		String msg = "", msg1 = "";
		Boolean active = lastdateservice.createLastDate(lastDateDTO);
		if (active == true) {
			msg1 = "Due Date Created Successfully";
		} else {
			msg = "Due Date For This Month Already Exist";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("msg1", msg1);
		model.addAttribute("months", monthService.findAll(Month.class));
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("LastDateDTO", new LastDateDTO());
		model.addAttribute("lastdates", lastdateservice
				.getLastDatesBySessionId(sessionService.findCurrent()
						.getSessionId()));
		session.setAttribute("active", "settings");
		return "managelastdates";
	}

	@RequestMapping(value = "create-lastdate.html", method = RequestMethod.GET)
	public void createLastDate(@ModelAttribute LastDateDTO lastDateDTO,
			ModelMap model, HttpServletResponse response)
			throws ParseException, IOException {
		response.sendRedirect("manage-lastdates.html");
	}
}
