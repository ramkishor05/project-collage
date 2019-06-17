package org.brijframework.college.controller.admin.advancesearch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class FeeSlipSearchController {
	@Autowired
	private StudentFeeSubmissionDetailsService studentFeeSubmissionDetailsService;
	@Autowired
	private StudentClassesService classesService;
	@Autowired
	private SessionService sessionService;

	@RequestMapping(value = "fee-slip-search.html", method = RequestMethod.GET)
	public String searchMenu(HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		session.setAttribute("active", "feeslip");
		return "searchmenu";
	}
	@RequestMapping(value = "search-by-slip-no.html", method = RequestMethod.GET)
	public String searchBySlipNo(HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		session.setAttribute("active", "feeslip");
		return "searchbyslipno";
	}

	@RequestMapping(value = "getfeedetaibyslipno", method = RequestMethod.POST)
	public @ResponseBody
	String getFeeDetaiBySlipNo(@RequestParam int slipNo, ModelMap model)
			throws JsonProcessingException 
	{
		return new ObjectMapper()
				.writeValueAsString(studentFeeSubmissionDetailsService
						.getallfeesubmissiondetailsByCommonSlipNo(slipNo));
	}

	@RequestMapping(value = "search-by-date.html", method = RequestMethod.GET)
	public String searchByDate(HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		session.setAttribute("active", "feeslip");
		return "searchbydate";
	}

	@RequestMapping(value = "getfeedetaibydate", method = RequestMethod.POST)
	public @ResponseBody
	String getFeeDetaiByDate(@RequestParam String fromDate,
			@RequestParam String toDate, ModelMap model)
			throws JsonProcessingException 
	{
		return new ObjectMapper()
				.writeValueAsString(studentFeeSubmissionDetailsService
						.getallfeesubmissiondetailsByDate(fromDate, toDate));
	}

	@RequestMapping(value = "search-by-admission.html", method = RequestMethod.GET)
	public String searchByAdmissionNo(HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		session.setAttribute("active", "feeslip");
		return "searchbyadmissionno";
	}

	@RequestMapping(value ="getfeedetaibyadmissionno", method = RequestMethod.POST)
	public @ResponseBody
	String getFeeDetaiByAdmissionNo(@RequestParam String admissionNo,
			ModelMap model) throws JsonProcessingException 
	{
		return new ObjectMapper()
				.writeValueAsString(studentFeeSubmissionDetailsService
						.getallfeesubmissiondetailsAdmissionNo(admissionNo));
	}

	@RequestMapping(value = "search-by-student-name.html", method = RequestMethod.GET)
	public String searchByName(HttpServletRequest request, Model model) 
	{
		
		model.addAttribute("classList",
				classesService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "feeslip");
		return "searchbyname";
	}

	@RequestMapping(value = "getfeedetaibyname", method = RequestMethod.POST)
	public @ResponseBody
	String getFeeDetaiByName(@RequestParam int classId,
			@RequestParam int sessionId, @RequestParam String name,
			ModelMap model) throws JsonProcessingException 
	{
		return new ObjectMapper()
				.writeValueAsString(studentFeeSubmissionDetailsService
						.getallfeesubmissiondetailsByStudentName(classId,
								sessionId, name));
	}

}
