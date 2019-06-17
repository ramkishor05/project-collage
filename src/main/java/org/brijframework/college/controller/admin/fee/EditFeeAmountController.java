package org.brijframework.college.controller.admin.fee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.EditFeeAmountDTO;
import org.brijframework.college.service.ClassWiseFeeService;
import org.brijframework.college.service.EditFeeAmountService;
import org.brijframework.college.service.SectionWiseFeeService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.brijframework.college.service.StudentWiseFeeService;
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
@SuppressWarnings("unchecked")
public class EditFeeAmountController {
	@Autowired
	private SessionService sessionService;
	@Autowired
	private StudentClassesService studentClassesService;
	@Autowired
	private ClassWiseFeeService classWiseFeeService;
	@Autowired
	private SectionWiseFeeService sectionWiseFeeService;
	@Autowired
	private StudentWiseFeeService studentWiseFeeService;
	@Autowired
	private StudentFeeSubmissionDetailsService studentFeeSubmissionDetailsService;
	@Autowired
	private EditFeeAmountService editFeeAmountService;

	@RequestMapping(value = "view-student-alloted-fee.html")
	public String getStudentAllotmentFeePage(ModelMap model) {
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("classesList", studentClassesService.getAllClass());
		model.addAttribute("current", sessionService.findCurrent());
		return "studentallotedfee";
	}

	@RequestMapping(value = "getstudentallotedfee", method = RequestMethod.POST)
	public @ResponseBody
	String getStudentAllotmentFee(@RequestParam int sessionId,
			@RequestParam int sectionId, @RequestParam int classId,
			@RequestParam String studentAdmissionNo, ModelMap model)
			throws JsonProcessingException {
		Map map = new HashMap();
		map.put("ClassWiseFeeDTOs",
				classWiseFeeService.getAllotedFeesByClassId(classId, sessionId));
		map.put("SectionWiseFeeDTOs", sectionWiseFeeService
				.getSectionWiseFeeAllotementBySectionId(sessionId, classId,
						sectionId));
		map.put("StudentWiseFeeDTOs", studentWiseFeeService
				.getFeeAllotementByStudentId(sessionId, classId, sectionId,
						studentAdmissionNo));
		return new ObjectMapper().writeValueAsString(map);
	}

	@RequestMapping(value = "changeallotedfeeamount", method = RequestMethod.GET)
	public String editAllotedFeeAmountPage(@RequestParam int sessionId,
			@RequestParam int classId, @RequestParam int sectionId,
			@RequestParam int feeCategoryId, @RequestParam String studentId,
			@RequestParam int monthId, @RequestParam String datatype,
			@RequestParam String monthName, @RequestParam Double feeAmount,
			ModelMap model) {
		EditFeeAmountDTO editFeeAmountDTO = new EditFeeAmountDTO();
		editFeeAmountDTO.setClassId(classId);
		editFeeAmountDTO.setFeesCategoriesId(feeCategoryId);
		editFeeAmountDTO.setMonthId(monthId);
		editFeeAmountDTO.setSectionId(sectionId);
		editFeeAmountDTO.setSessionId(sessionId);
		editFeeAmountDTO.setStudentAdmissionNo(studentId);
		editFeeAmountDTO.setCurrentAmount(feeAmount);
		editFeeAmountDTO.setMonthName(monthName);
		model.addAttribute("EditFeeAmountDTO", editFeeAmountDTO);
		List<EditFeeAmountDTO> editFeeAmountDTOs = editFeeAmountService
				.getEditFeeAmountDTOs(classId, sectionId, sessionId,
						feeCategoryId, monthId, studentId, feeAmount);
		model.addAttribute("editFeeAmountDTOs", editFeeAmountDTOs);
		model.addAttribute("monthId", monthId);
		return "changeallotedfeeamount";
	}

	@RequestMapping(value = "save-change-fee-amount.html", method = RequestMethod.POST)
	public String saveChangeFeeAmount(
			@ModelAttribute EditFeeAmountDTO editFeeAmountDTO, ModelMap model) {
		editFeeAmountService.createEditFeeAmount(editFeeAmountDTO);
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("classesList", studentClassesService.getAllClass());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("msg", "Updated in Fee Amount to "
				+ editFeeAmountDTO.getDiscount() + " is Successfully");
		return "studentallotedfee";
	}

	@RequestMapping(value = "save-change-fee-amount.html", method = RequestMethod.GET)
	public String saveChangeFeeAmountGet(ModelMap model) {
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("classesList", studentClassesService.getAllClass());
		model.addAttribute("current", sessionService.findCurrent());
		return "studentallotedfee";
	}
}
