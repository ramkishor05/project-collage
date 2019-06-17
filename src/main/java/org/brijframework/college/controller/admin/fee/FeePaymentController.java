package org.brijframework.college.controller.admin.fee;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class FeePaymentController {
	@Autowired
	StudentsAdmissionService admissionService;
	@Autowired
	SessionService sessionService;
	@Autowired
	StudentClassesService classesService;
	@Autowired
	SectionService sectionService;
	@Autowired
	StudentFeeSubmissionDetailsService feeSubmissionDetailsService;

	@RequestMapping(value = "fee-payment", method = RequestMethod.GET)
	public String feePayment(HttpServletRequest request, Model model) {
		int m = Calendar.getInstance().get(Calendar.MONTH);
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("classesList", classesService.getAllClass());
		model.addAttribute("thismonth", m + 1);
		model.addAttribute("studentFeeSubmissionDetailsDTO",
				new StudentFeeSubmissionDetailsDTO());
		return "feepaymenttally";
	}

	@RequestMapping(value = "/getsectionlist", method = RequestMethod.POST)
	public @ResponseBody String getSectionList(@RequestParam("id") String id,
			HttpServletRequest request) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<SectionDTO> sectionDTOs = sectionService
				.getSectionListByClassId(Integer.parseInt(id));
		return mapper.writeValueAsString(sectionDTOs);
	}

	@RequestMapping(value = "/get-student-list", method = RequestMethod.POST)
	public @ResponseBody String getStudentList(
			@RequestParam("classId") int classId,
			@RequestParam("sectionId") int sectionId,
			@RequestParam("sessionId") int sessionId, HttpServletRequest request)
			throws JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(admissionService
				.getStudentsbyClassandSection(classId, sectionId, sessionId));
	}

	@RequestMapping(value = "/get-student-fee-payment", method = RequestMethod.GET)
	public @ResponseBody String getStudentFeePayment(
			@RequestParam("id") int id,
			@RequestParam("sessionId") int sessionId, HttpServletRequest request)
			throws JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(feeSubmissionDetailsService
				.getStudentFeePaymentDetails(id, sessionId));
	}

	@RequestMapping(value = "/get-student-fee-allotment", method = RequestMethod.POST)
	public @ResponseBody String getStudentFeeAllotment(
			@RequestParam("studentId") int admissionNo,
			@RequestParam("sessionId") int sessionId,
			@RequestParam("classId") int classId,
			@RequestParam("sectionId") int sectionId,
			@RequestParam("monthId") int monthId, HttpServletRequest request)
			throws JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(feeSubmissionDetailsService
				.getStudentFeePaymentAllotment(sessionId, classId, sectionId,
						admissionNo, monthId));
	}

	@RequestMapping(value = "fee-payment", method = RequestMethod.POST)
	public String feePaymentSave(
			HttpServletRequest request,
			ModelMap model,
			@ModelAttribute("studentFeeSubmissionDetailsDTO") StudentFeeSubmissionDetailsDTO feeSubmissionDetailsDTO) {
		if (feeSubmissionDetailsDTO.getFeeSubmissionDetailsDTOs() == null
				|| feeSubmissionDetailsDTO.getFeeSubmissionDetailsDTOs()
						.isEmpty())
			model.addAttribute("CommonDTO", feeSubmissionDetailsService
					.createStudentFeeSubmissionDetails(feeSubmissionDetailsDTO));
		else {
			model.addAttribute(
					"CommonDTO",
					feeSubmissionDetailsService
							.createStudentFeeSubmissionDetailsNew(feeSubmissionDetailsDTO));
		}
		
		//return "preschoolreceipt";
		//return "pdfkanchanslip";
		//return "pdfkennedyslip";
		//return "pdfcommonreceipt";
		//return "ymbfeeslip";
		return "maharanaslip";
	}

	@RequestMapping(value = "cheque-inprogress.html", method = RequestMethod.GET)
	public String getInproressListPage(ModelMap model) {
		model.addAttribute("StudentFeeSubmissionDetailsDTOs",
				feeSubmissionDetailsService.getInprogressList());
		return "inprogresschequelist";
	}

	@RequestMapping(value = "change-cheque-inprogress.html", method = RequestMethod.GET)
	public String getInproressListPage(@RequestParam int slipNo,
			@RequestParam String feePaidStatus, ModelMap model) {
		feeSubmissionDetailsService.updateStatusById(feePaidStatus, slipNo);
		model.addAttribute("StudentFeeSubmissionDetailsDTOs",
				feeSubmissionDetailsService.getInprogressList());
		return "inprogresschequelist";
	}
}
