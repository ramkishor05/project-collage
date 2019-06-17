package org.brijframework.college.controller.admin.settings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.Section;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.models.dto.SectionDTO;
import org.brijframework.college.service.SectionService;
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
public class ManageSectionController {
	@Autowired
	private StudentClassesService studentClassesService;
	@Autowired
	StudentClassesService classesService;
	@Autowired
	private SectionService sectionService;

	@RequestMapping(value = "manage-section.html", method = RequestMethod.GET)
	public String getsectionsData(ModelMap model, HttpServletRequest request) {
		model.addAttribute("StudentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("Sections", sectionService.getSections());
		model.addAttribute("Section", new Section());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "addvieweditsection";
	}

	@RequestMapping(value = "add-section.html", method = RequestMethod.POST)
	public String createSection(@ModelAttribute Section section1, ModelMap model) {
		sectionService.create(section1);
		model.addAttribute("StudentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("Sections", sectionService.getSections());
		model.addAttribute("Section", new Section());
		return "addvieweditsection";
	}

	@RequestMapping(value = "add-section.html", method = RequestMethod.GET)
	public String createSection(ModelMap model, HttpServletRequest request) {
		model.addAttribute("StudentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("Sections", sectionService.getSections());
		model.addAttribute("Section", new Section());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "addvieweditsection";
	}

	@RequestMapping(value = "getsection", method = RequestMethod.POST)
	public @ResponseBody
	String getSection(@RequestParam int sectionId)
			throws JsonProcessingException {
		SectionDTO sectionDTO = sectionService.getSectionById(sectionId);
		return new ObjectMapper().writeValueAsString(sectionDTO);
	}

	@RequestMapping(value = "update-section.html", method = RequestMethod.POST)
	public String updateSection(@ModelAttribute Section section1, ModelMap model) {
		sectionService.update(section1);
		model.addAttribute("StudentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("Sections", sectionService.getSections());
		model.addAttribute("Section", new Section());
		return "addvieweditsection";
	}

	@RequestMapping(value = "update-section.html", method = RequestMethod.GET)
	public String updateSection(ModelMap model, HttpServletRequest request) {
		model.addAttribute("Sections", sectionService.getSections());
		
		model.addAttribute("classlist",
				studentClassesService.getAllClass());
		model.addAttribute("Section", new Section());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "addvieweditsection";
	}

	@RequestMapping(value = "deletesection.html", method = RequestMethod.GET)
	public String deleteSection(@RequestParam int sectionId, ModelMap model,
			HttpServletRequest request) {
		sectionService.deleteById(sectionId);
		model.addAttribute("StudentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("Sections", sectionService.getSections());
		model.addAttribute("classlist",
				classesService.findAll(StudentClasses.class));
		model.addAttribute("Section", new Section());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "addvieweditsection";
	}
}
