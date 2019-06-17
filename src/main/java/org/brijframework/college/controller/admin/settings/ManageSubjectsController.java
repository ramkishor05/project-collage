package org.brijframework.college.controller.admin.settings;

import java.util.List;

import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.StudentClassesDTO;
import org.brijframework.college.models.dto.SubjectDTO;
import org.brijframework.college.service.SectionService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/**")
public class ManageSubjectsController {

	@Autowired
	StudentClassesService studentClassesService;
	@Autowired
	SubjectService subjectService;
	@Autowired
	SectionService sectionService;
	@Autowired
	SessionService sessionService;

	@RequestMapping(value = "/manage-student-subjects", method = RequestMethod.GET)
	public String manageStudentsubjects() {

		return "managesubjects";
	}

	@RequestMapping(value = "/create-subjects", method = RequestMethod.GET)
	public String showcreateSubjects(Model model) {
		model.addAttribute("SubjectDTO", new SubjectDTO());
		List<StudentClassesDTO> list = studentClassesService.getAllClass();
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("list", list);
		return "createsubjects";
	}

	@RequestMapping(value = "/create-subjects", method = RequestMethod.POST)
	public String createSubjects(
			@ModelAttribute("subjectsDTO") SubjectDTO subjectsdto, Model model) {
		subjectService.addSubject(subjectsdto);
		model.addAttribute("msg", "Successfully Created");
		List<StudentClassesDTO> list = studentClassesService.getAllClass();
		model.addAttribute("list", list);
		model.addAttribute("SubjectDTO", new SubjectDTO());
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		return "createsubjects";
	}

	@RequestMapping(value = "/update-student-subjects", method = RequestMethod.GET)
	public String showupdateSubjects(Model model) {
		model.addAttribute("SubjectDTO", new SubjectDTO());
		List<StudentClassesDTO> list = studentClassesService.getAllClass();
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("list", list);
		return "updatesubjects";
	}

	@RequestMapping(value = "/edit-update-subjects", method = RequestMethod.GET)
	public String updateSubjects(Model model, @RequestParam int subjectId) {
		List<StudentClassesDTO> list = studentClassesService.getAllClass();
		model.addAttribute("SubjectDTO",
				subjectService.getSubjectById(subjectId));
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("list", list);
		return "updatesubjects";
	}

	@RequestMapping(value = "/update-subjects", method = RequestMethod.GET)
	public String editSubjects(Model model) {
		model.addAttribute("SubjectDTO", new SubjectDTO());
		List<StudentClassesDTO> list = studentClassesService.getAllClass();
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("list", list);
		return "editsubjects";
	}

	@RequestMapping(value = "/update-subjects", method = RequestMethod.POST)
	public String updateoldSubjects(@ModelAttribute SubjectDTO subjectDTO,
			Model model) {
		subjectService.updateSubject(subjectDTO);
		model.addAttribute("SubjectDTO",
				subjectService.getSubjectById(subjectDTO.getId()));
		List<StudentClassesDTO> list = studentClassesService.getAllClass();
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("list", list);
		model.addAttribute("msg", "Successfully Updated");
		return "updatesubjects";
	}
}
