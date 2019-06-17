package org.brijframework.college.controller.admin.fee;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.SectionDTO;
import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;
import org.brijframework.college.service.SectionService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class SearchStudentFeeController {
	@Autowired
	StudentClassesService studentClassesService;
	@Autowired
	SectionService sectionService;
	@Autowired
	StudentsAdmissionService admissionService;
	@Autowired
	StudentFeeSubmissionDetailsService feeSubmissionDetailsService;
	@Autowired
	SessionService sessionService;

	@RequestMapping(value = "search-menu")
	public String searchMenu(Model model) {
		return "searchmenu";
	}

	@RequestMapping(value = "search-students-fee-details", method = RequestMethod.GET)
	public String searchStudent(Model model) {
		model.addAttribute("studentClasses",
				studentClassesService.getAllClass());
		model.addAttribute("studentFeeDetailsDTO",
				new StudentFeeSubmissionDetailsDTO());
		model.addAttribute("sessionList",
				sessionService.findAll(Session.class));
		model.addAttribute("current",sessionService.findCurrent());
		return "searchstudentsfeedetails";
	}

	@RequestMapping(value = "/getsectionlist/{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getSectionList(@PathVariable("id") String id,
			HttpServletRequest request) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<SectionDTO> sectionDTOs = sectionService
				.getSectionListByClassId(Integer.parseInt(id));
		return mapper.writeValueAsString(sectionDTOs);
	}

	@RequestMapping(value = "/get-student-fee-details", method = RequestMethod.POST)
	public @ResponseBody
	String getStudentFeeDetails(@RequestParam("classId") int classId,
			@RequestParam("sectionId") int sectionId,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("sessionId") int sessionId,HttpServletRequest request)
			throws JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(feeSubmissionDetailsService
				.getStudentFeeSubmissionDetails(classId, sectionId, fromDate,
						toDate,sessionId));
	}

	@RequestMapping(value = "/changepaidstatus", method = RequestMethod.POST)
	public @ResponseBody
	String changePaidStatus(@RequestParam("paidstaus") String paidStatus,
			@RequestParam("receiptno") int receiptno, HttpServletRequest request)
			throws JsonProcessingException, ParseException {
		feeSubmissionDetailsService.updateStatusById(paidStatus, receiptno);
		return null;
	}
}
