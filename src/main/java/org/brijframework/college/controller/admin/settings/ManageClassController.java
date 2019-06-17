package org.brijframework.college.controller.admin.settings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.service.StudentClassesService;
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
public class ManageClassController {
	@Autowired
	private StudentClassesService studentClassesService;
	@Autowired
	StudentClassesService classesService;

	@RequestMapping(value = "manage-class.html", method = RequestMethod.GET)
	public String getClassesData(ModelMap model, HttpServletRequest request) {
	
		model.addAttribute("StudentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("StudentClass", new StudentClasses());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "addvieweditclass";
	}

	@RequestMapping(value = "add-class.html", method = RequestMethod.POST)
	public String createClass(
			@ModelAttribute("StudentClass") StudentClasses studentClasses,
			ModelMap model) {
		studentClassesService.create(studentClasses);
		model.addAttribute("StudentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("StudentClass", new StudentClasses());
		return "addvieweditclass";
	}

	@RequestMapping(value = "add-class.html", method = RequestMethod.GET)
	public String createClass(ModelMap model, HttpServletRequest request) {
		model.addAttribute("StudentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("StudentClass", new StudentClasses());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "addvieweditclass";
	}

	@RequestMapping(value = "getclass", method = RequestMethod.POST)
	public @ResponseBody
	String getClass(@RequestParam int classId) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(studentClassesService
				.getClassById(classId));
	}

	@RequestMapping(value = "update-class.html", method = RequestMethod.POST)
	public String updateClass(@ModelAttribute StudentClasses StudentClasses,
			ModelMap model) {
		studentClassesService.update(StudentClasses);
		model.addAttribute("StudentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("StudentClass", new StudentClasses());
		return "addvieweditclass";
	}

	@RequestMapping(value = "update-class.html", method = RequestMethod.GET)
	public String updateClass(ModelMap model, HttpServletRequest request) {
		model.addAttribute("StudentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("StudentClass", new StudentClasses());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "addvieweditclass";
	}

	@RequestMapping(value = "deleteclass.html", method = RequestMethod.GET)
	public String deleteClass(@RequestParam int classId, ModelMap model,
			HttpServletRequest request) {
		studentClassesService.deleteById(classId);
		model.addAttribute("StudentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("StudentClass", new StudentClasses());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "addvieweditclass";
	}
}
