package org.brijframework.college.controller.admin.settings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.Section;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.models.dto.SectionDTO;
import org.brijframework.college.models.dto.SessionDTO;
import org.brijframework.college.service.SectionService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/admin/**")
public class ManageSessionController {
	@Autowired
	private StudentClassesService studentClassesService;
	@Autowired
	StudentClassesService classesService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private SessionService sessionService;

	@RequestMapping(value = "manage-session.html", method = RequestMethod.GET)
	public String getsessionsData(ModelMap model, HttpServletRequest request) {
		model.addAttribute("Sessions", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("Session", new Session());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "addvieweditsession";
	}

	@RequestMapping(value = "getsession", method = RequestMethod.POST)
	public @ResponseBody String getSession(@RequestParam int sessionId)
			throws JsonProcessingException {
		SessionDTO sessionDTO = sessionService.getSessionById(sessionId);
		return new ObjectMapper().writeValueAsString(sessionDTO);
	}

	@RequestMapping(value = "add-session.html", method = RequestMethod.POST)
	public String createSession(@ModelAttribute Session session1,
			ModelMap model, RedirectAttributes redirectAttributes) {
		sessionService.create(session1);
		redirectAttributes.addFlashAttribute("msg",
				"Session Successfully Added");
		return "redirect:manage-session.html";
	}

	@RequestMapping(value = "changecurrent-session.html", method = RequestMethod.POST)
	public String changecurrentSession(@ModelAttribute Session session1,
			ModelMap model) {
		sessionService.changeCurrentSession(session1);
		model.addAttribute("Sessions", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("Session", new Session());
		return "addvieweditsession";
	}

	@RequestMapping(value = "add-session.html", method = RequestMethod.GET)
	public String createSection(ModelMap model, HttpServletRequest request) {
		model.addAttribute("Sessions", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("Session", new Session());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "addvieweditsession";
	}

	@RequestMapping(value = "update-session.html", method = RequestMethod.POST)
	public String updateSection(@ModelAttribute Session session1,
			ModelMap model, RedirectAttributes redirectAttributes) {
		sessionService.update(session1);
		redirectAttributes.addFlashAttribute("msg",
				"Session Successfully Updated");
		return "redirect:manage-session.html";
	}

	@RequestMapping(value = "update-session.html", method = RequestMethod.GET)
	public String updateSection(ModelMap model, HttpServletRequest request) {
		model.addAttribute("Sessions", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("Session", new Session());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "addvieweditsession";
	}

	@RequestMapping(value = "deletesession.html", method = RequestMethod.GET)
	public String deleteSection(@RequestParam int sessionId, ModelMap model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		sessionService.setActivebyId(sessionId);
		redirectAttributes.addFlashAttribute("msg",
				"Session Successfully Deleted");
		return "redirect:manage-session.html";
	}
}
