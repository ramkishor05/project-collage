package org.brijframework.college.controller.admin.batchpromote;

import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.FeecategoryAmountDTO;
import org.brijframework.college.service.CountryService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.brijframework.college.service.StudentsAdmissionService;
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
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class BatchPromoteController {
	@Autowired
	private StudentsAdmissionService studentsAdmissionService;
	@Autowired
	private StudentFeeSubmissionDetailsService studentFeeSubmissionDetailsService;
	@Autowired
	private StudentClassesService studentClassesService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "batch-promote.html", method = RequestMethod.GET)
	public String backUpPage(ModelMap model) {
		model.addAttribute("FeecategoryAmountDTO", new FeecategoryAmountDTO());

		model.addAttribute("Classes", studentClassesService.getAllClass());
		model.addAttribute("Sessiones", sessionService.findAllActiveSession());
		return "batchpromote";
	}

	@RequestMapping(value = "gettosession", method = RequestMethod.POST)
	public @ResponseBody String getToSession(@RequestParam int sessionId)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(sessionService
				.getToSession(sessionId));
	}

	@RequestMapping(value = "getstudentforbatchtransfer", method = RequestMethod.POST)
	public @ResponseBody String getStudent(@RequestParam int classId,
			@RequestParam int sectionId,
			@RequestParam(defaultValue = "0", required = false) int sessionId)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(studentsAdmissionService
				.getStudentsbyClassandSection(classId, sectionId, sessionId));
	}

	@RequestMapping(value = "transfer-batch.html", method = RequestMethod.POST)
	public String createUpPage(
			@ModelAttribute FeecategoryAmountDTO feecategoryAmountDTO,
			ModelMap model) {
		Boolean flag = studentsAdmissionService
				.transferBatch(feecategoryAmountDTO);
		if (flag == true) {
			model.addAttribute("msg", "Student Transferred Successfully");
		} else {
			model.addAttribute("msg",
					"Selected Students are already exist in this session.");
		}
		model.addAttribute("FeecategoryAmountDTO", new FeecategoryAmountDTO());
		model.addAttribute("Classes", studentClassesService.getAllClass());
		model.addAttribute("Sessiones",
				studentClassesService.findAll(Session.class));
		return "batchpromote";
	}

}
