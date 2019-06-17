package org.brijframework.college.controller.admin.fee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.FeesCategories;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.ClassWiseFeeDTO;
import org.brijframework.college.models.dto.CommonDTO;
import org.brijframework.college.models.dto.FeesCategoriesDTO;
import org.brijframework.college.models.dto.SectionDTO;
import org.brijframework.college.models.dto.SectionWiseFeeDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.StudentWiseFeeDTO;
import org.brijframework.college.service.ClassWiseFeeService;
import org.brijframework.college.service.FeesCategoriesService;
import org.brijframework.college.service.SectionService;
import org.brijframework.college.service.SectionWiseFeeService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.brijframework.college.service.StudentWiseFeeService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class FeesController {
	@Autowired
	FeesCategoriesService feecategoriesService;
	@Autowired
	StudentClassesService classService;
	@Autowired
	SessionService sessionService;
	@Autowired
	ClassWiseFeeService classWiseFeeService;
	@Autowired
	StudentWiseFeeService studentwiseFeeService;
	@Autowired
	SectionWiseFeeService sectionWiseFeeService;
	@Autowired
	StudentFeeSubmissionDetailsService studentFeeSubmissionDetailsService;
	@Autowired
	SectionService sectionService;
	@Autowired
	StudentsAdmissionService studentsAdmissionService;

	@RequestMapping(value = "fees-menu", method = RequestMethod.GET)
	public String feeAllotmentMenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "feesmenu";
	}

	@RequestMapping(value = "allot-fees-menu", method = RequestMethod.GET)
	public String feeAllotMenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "allotfeesmenu";
	}

	@RequestMapping(value = "create-edit-view-fees-categories", method = RequestMethod.GET)
	public String createEditViewFeeCategory(ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("FeesCategoriesDTO", new FeesCategoriesDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "createeditviewfeecategories";
	}

	@RequestMapping(value = "getcategories", method = RequestMethod.POST)
	public @ResponseBody String getFeeCategory(@RequestParam int feeCategoriesId)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(feecategoriesService
				.getFeeCategoriesDTOById(feeCategoriesId));
	}

	@RequestMapping(value = "add-fee-categories.html", method = RequestMethod.POST)
	public String addFeeCategories(
			@ModelAttribute FeesCategoriesDTO feeCategoryDTO, ModelMap model,
			HttpServletRequest request) {
		feecategoriesService.createFeeCategories(feeCategoryDTO);
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("FeesCategoriesDTO", new FeesCategoriesDTO());
		return "createeditviewfeecategories";
	}

	@RequestMapping(value = "add-fee-categories.html", method = RequestMethod.GET)
	public String addeFeeCategory(
			@ModelAttribute FeesCategoriesDTO feeCategoryDTO, ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("FeesCategoriesDTO", new FeesCategoriesDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "createeditviewfeecategories";
	}

	@RequestMapping(value = "update-fee-categories.html", method = RequestMethod.POST)
	public String updateFeeCategories(
			@ModelAttribute FeesCategoriesDTO feeCategoryDTO, ModelMap model) {
		feecategoriesService.updateFeeCategories(feeCategoryDTO);
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("FeesCategoriesDTO", new FeesCategoriesDTO());
		return "createeditviewfeecategories";
	}

	@RequestMapping(value = "update-fee-categories.html", method = RequestMethod.GET)
	public String updateeFeeCategories(
			@ModelAttribute FeesCategoriesDTO feeCategoryDTO, ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("FeesCategoriesDTO", new FeesCategoriesDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "createeditviewfeecategories";
	}

	@RequestMapping(value = "deletefeecategories.html", method = RequestMethod.GET)
	public String deleteFeeCategory(@RequestParam int feeCategoryId,
			ModelMap model, HttpServletRequest request) {
		FeesCategories feeCategory = feecategoriesService.read(feeCategoryId);
		feeCategory.setActive(false);
		feecategoriesService.update(feeCategory);
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("FeesCategoriesDTO", new FeesCategoriesDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "createeditviewfeecategories";
	}

	@RequestMapping(value = "allot-classwise-fee", method = RequestMethod.GET)
	public String allotclasswise(ModelMap model, HttpServletRequest request) {
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("ClassWiseFeeDTO", new ClassWiseFeeDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "allotclasswisefee";
	}

	@RequestMapping(value = "showallotedfeesbyclassid", method = RequestMethod.GET)
	public @ResponseBody String showAllotedFeesByClassId(
			@RequestParam int classId, @RequestParam int sessionId)
			throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(classWiseFeeService
				.getAllotedFeesByClassId(classId, sessionId));
	}

	@RequestMapping(value = "showallotedfeesbysectionid", method = RequestMethod.GET)
	public @ResponseBody String showAllotedFeesBySectionId(
			@RequestParam int classId, @RequestParam int sectionId,
			@RequestParam int sessionId) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(sectionWiseFeeService
				.getSectionWiseFeeAllotementBySectionId(sessionId, classId,
						sectionId));
	}

	@RequestMapping(value = "showallotedfeesbystudentid", method = RequestMethod.GET)
	public @ResponseBody String showAllotedFeesByStudentId(
			@RequestParam int classId, @RequestParam int sectionId,
			@RequestParam int sessionId, @RequestParam String studentId)
			throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(studentwiseFeeService
				.getFeeAllotementByStudentId(sessionId, classId, sectionId,
						studentId));
	}

	@RequestMapping(value = "verifyfeeallotedclasswise", method = RequestMethod.GET)
	public @ResponseBody String verifyotclasswise(@RequestParam int sessionId,
			@RequestParam int classId, @RequestParam String monthId,
			@RequestParam int categoryId, ModelMap model,
			HttpServletRequest request) {

		List<ClassWiseFeeDTO> classWiseFeeDTO = new ArrayList<ClassWiseFeeDTO>();
		List<ClassWiseFeeDTO> classWiseFeeDTOs = new ArrayList<ClassWiseFeeDTO>();
		List<SectionWiseFeeDTO> sectionWiseFeeDTO = new ArrayList<SectionWiseFeeDTO>();
		List<SectionWiseFeeDTO> sectionWiseFeeDTOs = new ArrayList<SectionWiseFeeDTO>();
		List<StudentWiseFeeDTO> studentWiseFeeDTO = new ArrayList<StudentWiseFeeDTO>();
		List<StudentWiseFeeDTO> studentWiseFeeDTOs = new ArrayList<StudentWiseFeeDTO>();
		String var = "";
		Integer[] all = { 13 };
		classWiseFeeDTO = classWiseFeeService.getClassWiseFee(sessionId,
				classId, all, categoryId);
		for (SectionDTO sectionDTO : sectionService
				.getSectionListByClassId(classId)) {
			sectionWiseFeeDTO = sectionWiseFeeService.getSectionWiseFee(
					sessionId, classId, all, categoryId,
					sectionDTO.getSectionId());
			for (StudentDTO studentDTO : studentsAdmissionService
					.getStudentsByClassId(classId)) {
				studentWiseFeeDTO = studentwiseFeeService.getStudentWiseFee(
						sessionId, classId, all, categoryId,
						sectionDTO.getSectionId(), studentDTO.getAdmissionNo());
			}
		}

		if (monthId.equalsIgnoreCase("first")) {
			Integer[] firstInstallment = { 1, 2, 3 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, firstInstallment, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, firstInstallment, categoryId,
						sectionDTO.getSectionId());
				for (StudentDTO studentDTO : studentsAdmissionService
						.getStudentsByClassId(classId)) {
					studentWiseFeeDTOs = studentwiseFeeService
							.getStudentWiseFee(sessionId, classId,
									firstInstallment, categoryId,
									sectionDTO.getSectionId(),
									studentDTO.getAdmissionNo());
				}
			}

		} else if (monthId.equalsIgnoreCase("second")) {
			Integer[] secondInstallment = { 4, 5, 6 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, secondInstallment, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, secondInstallment, categoryId,
						sectionDTO.getSectionId());
				for (StudentDTO studentDTO : studentsAdmissionService
						.getStudentsByClassId(classId)) {
					studentWiseFeeDTOs = studentwiseFeeService
							.getStudentWiseFee(sessionId, classId,
									secondInstallment, categoryId,
									sectionDTO.getSectionId(),
									studentDTO.getAdmissionNo());
				}
			}

		} else if (monthId.equalsIgnoreCase("third")) {
			Integer[] thirdInstallment = { 7, 8, 9 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, thirdInstallment, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, thirdInstallment, categoryId,
						sectionDTO.getSectionId());
				for (StudentDTO studentDTO : studentsAdmissionService
						.getStudentsByClassId(classId)) {
					studentWiseFeeDTOs = studentwiseFeeService
							.getStudentWiseFee(sessionId, classId,
									thirdInstallment, categoryId,
									sectionDTO.getSectionId(),
									studentDTO.getAdmissionNo());
				}
			}

		} else if (monthId.equalsIgnoreCase("fourth")) {
			Integer[] fourthInstallment = { 10, 11, 12 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, fourthInstallment, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, fourthInstallment, categoryId,
						sectionDTO.getSectionId());
				for (StudentDTO studentDTO : studentsAdmissionService
						.getStudentsByClassId(classId)) {
					studentWiseFeeDTOs = studentwiseFeeService
							.getStudentWiseFee(sessionId, classId,
									fourthInstallment, categoryId,
									sectionDTO.getSectionId(),
									studentDTO.getAdmissionNo());
				}
			}
		} else if (monthId.equalsIgnoreCase("13")) {
			Integer[] year = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, year, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, year, categoryId,
						sectionDTO.getSectionId());
				for (StudentDTO studentDTO : studentsAdmissionService
						.getStudentsByClassId(classId)) {
					studentWiseFeeDTOs = studentwiseFeeService
							.getStudentWiseFee(sessionId, classId, year,
									categoryId, sectionDTO.getSectionId(),
									studentDTO.getAdmissionNo());
				}
			}
		} else {
			Integer[] month = { Integer.parseInt(monthId) };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, month, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, month, categoryId,
						sectionDTO.getSectionId());
				for (StudentDTO studentDTO : studentsAdmissionService
						.getStudentsByClassId(classId)) {
					studentWiseFeeDTOs = studentwiseFeeService
							.getStudentWiseFee(sessionId, classId, month,
									categoryId, sectionDTO.getSectionId(),
									studentDTO.getAdmissionNo());
				}
			}
		}

		if (classWiseFeeDTOs.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (classWiseFeeDTO.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (sectionWiseFeeDTOs.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (sectionWiseFeeDTO.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (studentWiseFeeDTOs.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (studentWiseFeeDTO.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		return var;
	}

	@RequestMapping(value = "verifyfeeallotedstudentwise", method = RequestMethod.GET)
	public @ResponseBody String verifyotstudentwise(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam String studentId, @RequestParam int sectionId,
			@RequestParam String monthId, @RequestParam int categoryId,
			ModelMap model, HttpServletRequest request) {
		List<ClassWiseFeeDTO> classWiseFeeDTO = new ArrayList<ClassWiseFeeDTO>();
		List<ClassWiseFeeDTO> classWiseFeeDTOs = new ArrayList<ClassWiseFeeDTO>();
		List<SectionWiseFeeDTO> sectionWiseFeeDTO = new ArrayList<SectionWiseFeeDTO>();
		List<SectionWiseFeeDTO> sectionWiseFeeDTOs = new ArrayList<SectionWiseFeeDTO>();
		List<StudentWiseFeeDTO> studentWiseFeeDTOs = new ArrayList<StudentWiseFeeDTO>();
		List<StudentWiseFeeDTO> studentWiseFeeDTO = new ArrayList<StudentWiseFeeDTO>();

		String var = "";
		Integer[] all = { 13 };
		classWiseFeeDTO = classWiseFeeService.getClassWiseFee(sessionId,
				classId, all, categoryId);
		for (SectionDTO sectionDTO : sectionService
				.getSectionListByClassId(classId)) {
			sectionWiseFeeDTO = sectionWiseFeeService.getSectionWiseFee(
					sessionId, classId, all, categoryId,
					sectionDTO.getSectionId());
		}
		studentWiseFeeDTO = studentwiseFeeService.getStudentWiseFee(sessionId,
				classId, all, categoryId, sectionId, studentId);
		if (monthId.equalsIgnoreCase("first")) {
			Integer[] firstInstallment = { 1, 2, 3 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, firstInstallment, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, firstInstallment, categoryId,
						sectionDTO.getSectionId());
			}
			studentWiseFeeDTOs = studentwiseFeeService.getStudentWiseFee(
					sessionId, classId, firstInstallment, categoryId,
					sectionId, studentId);

		} else if (monthId.equalsIgnoreCase("second")) {
			Integer[] secondInstallment = { 4, 5, 6 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, secondInstallment, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, secondInstallment, categoryId,
						sectionDTO.getSectionId());
			}
			studentWiseFeeDTOs = studentwiseFeeService.getStudentWiseFee(
					sessionId, classId, secondInstallment, categoryId,
					sectionId, studentId);

		} else if (monthId.equalsIgnoreCase("third")) {
			Integer[] thirdInstallment = { 7, 8, 9 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, thirdInstallment, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, thirdInstallment, categoryId,
						sectionDTO.getSectionId());
			}
			studentWiseFeeDTOs = studentwiseFeeService.getStudentWiseFee(
					sessionId, classId, thirdInstallment, categoryId,
					sectionId, studentId);

		} else if (monthId.equalsIgnoreCase("fourth")) {
			Integer[] fourthInstallment = { 10, 11, 12 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, fourthInstallment, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, fourthInstallment, categoryId,
						sectionDTO.getSectionId());
			}
			studentWiseFeeDTOs = studentwiseFeeService.getStudentWiseFee(
					sessionId, classId, fourthInstallment, categoryId,
					sectionId, studentId);
		} else if (monthId.equalsIgnoreCase("13")) {
			Integer[] year = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, year, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, year, categoryId,
						sectionDTO.getSectionId());
			}
			studentWiseFeeDTOs = studentwiseFeeService.getStudentWiseFee(
					sessionId, classId, year, categoryId, sectionId, studentId);
		} else {
			Integer[] month = { Integer.parseInt(monthId) };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, month, categoryId);
			for (SectionDTO sectionDTO : sectionService
					.getSectionListByClassId(classId)) {
				sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
						sessionId, classId, month, categoryId,
						sectionDTO.getSectionId());
			}
			studentWiseFeeDTOs = studentwiseFeeService
					.getStudentWiseFee(sessionId, classId, month, categoryId,
							sectionId, studentId);
		}

		if (classWiseFeeDTOs.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (classWiseFeeDTO.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (sectionWiseFeeDTOs.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (sectionWiseFeeDTO.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (studentWiseFeeDTOs.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (studentWiseFeeDTO.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		return var;
	}

	@RequestMapping(value = "verifyfeeallotedsectionwise", method = RequestMethod.GET)
	public @ResponseBody String verifyotsectionwise(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam String monthId,
			@RequestParam int categoryId, ModelMap model,
			HttpServletRequest request) {

		List<ClassWiseFeeDTO> classWiseFeeDTO = new ArrayList<ClassWiseFeeDTO>();
		List<ClassWiseFeeDTO> classWiseFeeDTOs = new ArrayList<ClassWiseFeeDTO>();
		List<SectionWiseFeeDTO> sectionWiseFeeDTO = new ArrayList<SectionWiseFeeDTO>();
		List<SectionWiseFeeDTO> sectionWiseFeeDTOs = new ArrayList<SectionWiseFeeDTO>();
		List<StudentWiseFeeDTO> studentWiseFeeDTO = new ArrayList<StudentWiseFeeDTO>();
		List<StudentWiseFeeDTO> studentWiseFeeDTOs = new ArrayList<StudentWiseFeeDTO>();
		String var = "";
		Integer[] all = { 13 };
		classWiseFeeDTO = classWiseFeeService.getClassWiseFee(sessionId,
				classId, all, categoryId);
		sectionWiseFeeDTO = sectionWiseFeeService.getSectionWiseFee(sessionId,
				classId, all, categoryId, sectionId);
		for (StudentDTO studentDTO : studentsAdmissionService
				.getStudentsbyClassandSection(classId, sectionId, sessionId)) {
			List<StudentWiseFeeDTO> studentWiseFeeDTOs2 = studentwiseFeeService
					.getStudentWiseFee(sessionId, classId, all, categoryId,
							sectionId, studentDTO.getAdmissionNo());
			if (!studentWiseFeeDTOs2.isEmpty()) {
				studentWiseFeeDTO.addAll(studentWiseFeeDTOs2);
			}
		}

		if (monthId.equalsIgnoreCase("first")) {
			Integer[] firstInstallment = { 1, 2, 3 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, firstInstallment, categoryId);
			sectionWiseFeeDTOs = sectionWiseFeeService
					.getSectionWiseFee(sessionId, classId, firstInstallment,
							categoryId, sectionId);
			for (StudentDTO studentDTO : studentsAdmissionService
					.getStudentsbyClassandSection(classId, sectionId, sessionId)) {
				List<StudentWiseFeeDTO> studentWiseFeeDTOs2 = studentwiseFeeService
						.getStudentWiseFee(sessionId, classId,
								firstInstallment, categoryId, sectionId,
								studentDTO.getAdmissionNo());
				if (!studentWiseFeeDTOs2.isEmpty()) {
					studentWiseFeeDTOs.addAll(studentWiseFeeDTOs2);
				}
			}

		} else if (monthId.equalsIgnoreCase("second")) {
			Integer[] secondInstallment = { 4, 5, 6 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, secondInstallment, categoryId);
			sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
					sessionId, classId, secondInstallment, categoryId,
					sectionId);
			for (StudentDTO studentDTO : studentsAdmissionService
					.getStudentsbyClassandSection(classId, sectionId, sessionId)) {
				List<StudentWiseFeeDTO> studentWiseFeeDTOs2 = studentwiseFeeService
						.getStudentWiseFee(sessionId, classId,
								secondInstallment, categoryId, sectionId,
								studentDTO.getAdmissionNo());
				if (!studentWiseFeeDTOs2.isEmpty()) {
					studentWiseFeeDTOs.addAll(studentWiseFeeDTOs2);
				}
			}

		} else if (monthId.equalsIgnoreCase("third")) {
			Integer[] thirdInstallment = { 7, 8, 9 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, thirdInstallment, categoryId);
			sectionWiseFeeDTOs = sectionWiseFeeService
					.getSectionWiseFee(sessionId, classId, thirdInstallment,
							categoryId, sectionId);
			for (StudentDTO studentDTO : studentsAdmissionService
					.getStudentsbyClassandSection(classId, sectionId, sessionId)) {
				List<StudentWiseFeeDTO> studentWiseFeeDTOs2 = studentwiseFeeService
						.getStudentWiseFee(sessionId, classId,
								thirdInstallment, categoryId, sectionId,
								studentDTO.getAdmissionNo());
				if (!studentWiseFeeDTOs2.isEmpty()) {
					studentWiseFeeDTOs.addAll(studentWiseFeeDTOs2);
				}
			}

		} else if (monthId.equalsIgnoreCase("fourth")) {
			Integer[] fourthInstallment = { 10, 11, 12 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, fourthInstallment, categoryId);
			sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
					sessionId, classId, fourthInstallment, categoryId,
					sectionId);
			for (StudentDTO studentDTO : studentsAdmissionService
					.getStudentsbyClassandSection(classId, sectionId, sessionId)) {
				List<StudentWiseFeeDTO> studentWiseFeeDTOs2 = studentwiseFeeService
						.getStudentWiseFee(sessionId, classId,
								fourthInstallment, categoryId, sectionId,
								studentDTO.getAdmissionNo());
				if (!studentWiseFeeDTOs2.isEmpty()) {
					studentWiseFeeDTOs.addAll(studentWiseFeeDTOs2);
				}
			}
		} else if (monthId.equalsIgnoreCase("13")) {
			Integer[] year = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13 };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, year, categoryId);
			sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
					sessionId, classId, year, categoryId, sectionId);
			for (StudentDTO studentDTO : studentsAdmissionService
					.getStudentsbyClassandSection(classId, sectionId, sessionId)) {
				List<StudentWiseFeeDTO> studentWiseFeeDTOs2 = studentwiseFeeService
						.getStudentWiseFee(sessionId, classId, year,
								categoryId, sectionId,
								studentDTO.getAdmissionNo());
				if (!studentWiseFeeDTOs2.isEmpty()) {
					studentWiseFeeDTOs.addAll(studentWiseFeeDTOs2);
				}
			}
		} else {
			Integer[] month = { Integer.parseInt(monthId) };
			classWiseFeeDTOs = classWiseFeeService.getClassWiseFee(sessionId,
					classId, month, categoryId);
			sectionWiseFeeDTOs = sectionWiseFeeService.getSectionWiseFee(
					sessionId, classId, month, categoryId, sectionId);
			for (StudentDTO studentDTO : studentsAdmissionService
					.getStudentsbyClassandSection(classId, sectionId, sessionId)) {
				List<StudentWiseFeeDTO> studentWiseFeeDTOs2 = studentwiseFeeService
						.getStudentWiseFee(sessionId, classId, month,
								categoryId, sectionId,
								studentDTO.getAdmissionNo());
				if (!studentWiseFeeDTOs2.isEmpty()) {
					studentWiseFeeDTOs.addAll(studentWiseFeeDTOs2);
				}
			}
		}

		if (classWiseFeeDTOs.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (classWiseFeeDTO.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (sectionWiseFeeDTOs.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (sectionWiseFeeDTO.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (studentWiseFeeDTOs.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		if (studentWiseFeeDTO.isEmpty()) {
			var = "no";
		} else {
			return var = "yes";
		}
		return var;
	}

	@RequestMapping(value = "save-allotclasswisefee", method = RequestMethod.POST)
	public String saveallotclasswise(
			@ModelAttribute ClassWiseFeeDTO classWiseFeeDTO, ModelMap model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String msgs = "";
		if (classWiseFeeDTO.getInstallment().equalsIgnoreCase("first")) {
			int[] firstInstallment = { 1, 2, 3 };
			for (int id : firstInstallment) {
				classWiseFeeDTO.setMonthId(id);
				String status = classWiseFeeService
						.saveClassWiseFee(classWiseFeeDTO);
				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";

			}
		} else if (classWiseFeeDTO.getInstallment().equalsIgnoreCase("second")) {
			int[] firstInstallment = { 4, 5, 6 };
			for (int id : firstInstallment) {
				classWiseFeeDTO.setMonthId(id);
				classWiseFeeDTO.setMonthId(id);
				String status = classWiseFeeService
						.saveClassWiseFee(classWiseFeeDTO);
				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";
			}
		} else if (classWiseFeeDTO.getInstallment().equalsIgnoreCase("third")) {
			int[] firstInstallment = { 7, 8, 9 };
			for (int id : firstInstallment) {
				classWiseFeeDTO.setMonthId(id);
				classWiseFeeDTO.setMonthId(id);
				String status = classWiseFeeService
						.saveClassWiseFee(classWiseFeeDTO);
				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";
			}
		} else if (classWiseFeeDTO.getInstallment().equalsIgnoreCase("fourth")) {
			int[] firstInstallment = { 10, 11, 12 };
			for (int id : firstInstallment) {
				classWiseFeeDTO.setMonthId(id);
				classWiseFeeDTO.setMonthId(id);
				String status = classWiseFeeService
						.saveClassWiseFee(classWiseFeeDTO);
				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";
			}
		} else {

			String status = classWiseFeeService
					.saveClassWiseFee(classWiseFeeDTO);
			if (status == "no" || status.equals("no")) {
				msgs = "Fee cannot be alloted because fees is already paid by a student for a month";

			} else
				msgs = "Successfully Alloted";
		}
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("FeesCategoriesDTO", new FeesCategoriesDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("ClassWiseFeeDTO", new ClassWiseFeeDTO());
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		model.addAttribute("msg", msgs);

		return "allotclasswisefee";
	}

	@RequestMapping(value = "save-allotclasswisefee", method = RequestMethod.GET)
	public String saveallotclasswise(ModelMap model, HttpServletRequest request) {
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("FeesCategoriesDTO", new FeesCategoriesDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		model.addAttribute("ClassWiseFeeDTO", new ClassWiseFeeDTO());
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		return "allotclasswisefee";
	}

	@RequestMapping(value = "allot-sectionwise-fee", method = RequestMethod.GET)
	public String allotsectionwise(ModelMap model, HttpServletRequest request) {
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("SectionWiseFeeDTO", new SectionWiseFeeDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("sectionfeelist",
				sectionWiseFeeService.getAllSectionFees());
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		return "allotsectionwise";
	}

	@RequestMapping(value = "save-allotsection-wisefee", method = RequestMethod.POST)
	public String saveallotsectionwise(
			@ModelAttribute SectionWiseFeeDTO sectionWiseFeeDTO,
			ModelMap model, HttpServletRequest request) {
		String msgs = "";
		if (sectionWiseFeeDTO.getInstallment().equalsIgnoreCase("first")) {
			int[] firstInstallment = { 1, 2, 3 };
			for (int id : firstInstallment) {
				sectionWiseFeeDTO.setMonthId(id);
				String status = sectionWiseFeeService
						.saveSectionWiseFee(sectionWiseFeeDTO);

				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";
			}
		} else if (sectionWiseFeeDTO.getInstallment()
				.equalsIgnoreCase("second")) {
			int[] firstInstallment = { 4, 5, 6 };
			for (int id : firstInstallment) {
				sectionWiseFeeDTO.setMonthId(id);
				String status = sectionWiseFeeService
						.saveSectionWiseFee(sectionWiseFeeDTO);

				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";
			}
		} else if (sectionWiseFeeDTO.getInstallment().equalsIgnoreCase("third")) {
			int[] firstInstallment = { 7, 8, 9 };
			for (int id : firstInstallment) {
				sectionWiseFeeDTO.setMonthId(id);
				String status = sectionWiseFeeService
						.saveSectionWiseFee(sectionWiseFeeDTO);

				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";
			}
		} else if (sectionWiseFeeDTO.getInstallment()
				.equalsIgnoreCase("fourth")) {
			int[] firstInstallment = { 10, 11, 12 };
			for (int id : firstInstallment) {
				sectionWiseFeeDTO.setMonthId(id);
				String status = sectionWiseFeeService
						.saveSectionWiseFee(sectionWiseFeeDTO);

				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";
			}
		} else {
			String status = sectionWiseFeeService
					.saveSectionWiseFee(sectionWiseFeeDTO);

			if (status == "no" || status.equals("no")) {
				msgs = "Fee cannot be alloted because fees is already paid by a student for a month";

			} else
				msgs = "Successfully Alloted";
		}

		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("SectionWiseFeeDTO", new SectionWiseFeeDTO());
		model.addAttribute("msg", msgs);
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		model.addAttribute("sectionfeelist",
				sectionWiseFeeService.getAllSectionFees());
		return "allotsectionwise";
	}

	@RequestMapping(value = "save-allotsection-wisefee", method = RequestMethod.GET)
	public String saveallotsectionwise(ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("SectionWiseFeeDTO", new SectionWiseFeeDTO());
		model.addAttribute("msg", "Successfully Allotted");
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		model.addAttribute("sectionfeelist",
				sectionWiseFeeService.getAllSectionFees());
		return "allotsectionwise";
	}

	@RequestMapping(value = "save-allotstudent-wisefee", method = RequestMethod.GET)
	public String savedallotstudentwisefee(ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("StudentWiseFeeDTO", new StudentWiseFeeDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		model.addAttribute("sectionfeelist",
				sectionWiseFeeService.getAllSectionFees());
		model.addAttribute("studentfeelist",
				studentwiseFeeService.getAllStudentFees());
		return "allotstudentwisefee";
	}

	@RequestMapping(value = "save-allotstudent-wisefee", method = RequestMethod.POST)
	public String saveallotstudentwisefee(
			@ModelAttribute StudentWiseFeeDTO studentWiseFeeDTO,
			ModelMap model, HttpServletRequest request) {
		String msgs="";
		if (studentWiseFeeDTO.getInstallment().equalsIgnoreCase("first")) {
			int[] firstInstallment = { 1, 2, 3 };
			for (int id : firstInstallment) {
				studentWiseFeeDTO.setMonthId(id);
				String status =	studentwiseFeeService.saveStudentWiseFee(studentWiseFeeDTO);
				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";
			}
		} else if (studentWiseFeeDTO.getInstallment()
				.equalsIgnoreCase("second")) {
			int[] firstInstallment = { 4, 5, 6 };
			for (int id : firstInstallment) {
				studentWiseFeeDTO.setMonthId(id);
				String status =	studentwiseFeeService.saveStudentWiseFee(studentWiseFeeDTO);
				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";
			}
		} else if (studentWiseFeeDTO.getInstallment().equalsIgnoreCase("third")) {
			int[] firstInstallment = { 7, 8, 9 };
			for (int id : firstInstallment) {
				studentWiseFeeDTO.setMonthId(id);
				String status =	studentwiseFeeService.saveStudentWiseFee(studentWiseFeeDTO);
				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";
			}
		} else if (studentWiseFeeDTO.getInstallment()
				.equalsIgnoreCase("fourth")) {
			int[] firstInstallment = { 10, 11, 12 };
			for (int id : firstInstallment) {
				studentWiseFeeDTO.setMonthId(id);
				String status =	studentwiseFeeService.saveStudentWiseFee(studentWiseFeeDTO);
				if (status == "no" || status.equals("no")) {
					msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
					break;

				} else
					msgs = "Successfully Alloted";
			}
		} else {
			String status =	studentwiseFeeService.saveStudentWiseFee(studentWiseFeeDTO);
			if (status == "no" || status.equals("no")) {
				msgs = "Fee cannot be alloted because fees is already paid by a student for a month";
			} else
				msgs = "Successfully Alloted";
		}
		/* studentwiseFeeService.saveStudentWiseFee(studentWiseFeeDTO); */
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("FeesCategoriesDTO", new FeesCategoriesDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("StudentWiseFeeDTO", new StudentWiseFeeDTO());
		model.addAttribute("msg", msgs);
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		model.addAttribute("sectionfeelist",
				sectionWiseFeeService.getAllSectionFees());
		model.addAttribute("studentfeelist",
				studentwiseFeeService.getAllStudentFees());
		return "allotstudentwisefee";

	}

	@RequestMapping(value = "allot-studentwise-fee", method = RequestMethod.GET)
	public String allotstudentwisefee(ModelMap model, HttpServletRequest request) {
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("StudentWiseFeeDTO", new StudentWiseFeeDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		model.addAttribute("sectionfeelist",
				sectionWiseFeeService.getAllSectionFees());
		model.addAttribute("studentfeelist",
				studentwiseFeeService.getAllStudentFees());
		return "allotstudentwisefee";
	}

	@RequestMapping(value = "view-feedefaulter-list", method = RequestMethod.GET)
	public String defaulterlist(ModelMap model, HttpServletRequest request) {
		model.addAttribute("studentDTO", new StudentDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "feedefaulterlist";
	}

	@RequestMapping(value = "removeclasswisefee", method = RequestMethod.GET)
	public String removesClassWise(ModelMap model, HttpServletRequest request,
			@RequestParam int id) {
		classWiseFeeService.setActiveById(id);
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("ClassWiseFeeDTO", new ClassWiseFeeDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "allotclasswisefee";
	}

	@RequestMapping(value = "removesectionwisefee", method = RequestMethod.GET)
	public String removesectionwise(ModelMap model, HttpServletRequest request,
			@RequestParam int id) {
		sectionWiseFeeService.setActiveById(id);
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("SectionWiseFeeDTO", new SectionWiseFeeDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("sectionfeelist",
				sectionWiseFeeService.getAllSectionFees());
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		return "allotsectionwise";
	}

	@RequestMapping(value = "removestudentwisefee", method = RequestMethod.GET)
	public String removestudentwisefee(ModelMap model,
			HttpServletRequest request, @RequestParam int id) {
		studentwiseFeeService.setActiveById(id);
		model.addAttribute("FeeCategories", feecategoriesService
				.findAllByIsDeletedTrue(FeesCategories.class));
		model.addAttribute("StudentWiseFeeDTO", new StudentWiseFeeDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("classfeelist",
				classWiseFeeService.getAllClassFees());
		model.addAttribute("sectionfeelist",
				sectionWiseFeeService.getAllSectionFees());
		model.addAttribute("studentfeelist",
				studentwiseFeeService.getAllStudentFees());
		return "allotstudentwisefee";
	}

	@RequestMapping(value = "show-defaulter-pdf-report", method = RequestMethod.POST)
	public String pdfdefaulterlist(
			@ModelAttribute("studentDTO") StudentDTO studentDTO,
			ModelMap model, HttpServletRequest request) {
		List<StudentDTO> list = studentFeeSubmissionDetailsService
				.getDefaulterList(studentDTO.getSessionId(),
						studentDTO.getClassId(), studentDTO.getSectionId(),
						studentDTO.getMonthId());
		model.addAttribute("list", list);
		return "pdffeedefaulterlist";
	}

	@RequestMapping(value = "view-feepaid-list", method = RequestMethod.GET)
	public String paidlist(ModelMap model, HttpServletRequest request) {
		model.addAttribute("studentDTO", new StudentDTO());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "feepaidlist";
	}

	@RequestMapping(value = "show-paid-pdf-report", method = RequestMethod.POST)
	public String pdfpaidlist(
			@ModelAttribute("studentDTO") StudentDTO studentDTO,
			ModelMap model, HttpServletRequest request) {
		List<StudentDTO> list = studentFeeSubmissionDetailsService.getPaidList(
				studentDTO.getSessionId(), studentDTO.getClassId(),
				studentDTO.getSectionId(), studentDTO.getMonthId());
		model.addAttribute("list", list);
		return "pdffeepaidlist";
	}

	@RequestMapping(value = "fee-defaulter-menu", method = RequestMethod.GET)
	public String feedefaultermenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "feedefaultermenu";
	}

	@RequestMapping(value = "all-defaulters", method = RequestMethod.GET)
	public String alldefaulter(
			HttpServletRequest request,
			ModelMap model,
			@RequestParam(value = "pageno", defaultValue = "0", required = false) int pageno)
			throws IOException {
		int totalNoOfPages = 0;
		List<StudentDTO> totalList = studentFeeSubmissionDetailsService
				.getAllDefaulters();
		totalNoOfPages = (totalList.size() / 10) + 1;
		model.addAttribute("pageno", pageno);
		model.addAttribute("totalNoOfPages", totalNoOfPages);
		model.addAttribute("defaulterlist", studentFeeSubmissionDetailsService
				.getAllDefaultersbyPageNo(pageno - 1));
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "alldefaulters";
	}

	@RequestMapping(value = "class-wise-defaulters", method = RequestMethod.GET)
	public String classwisedefaulter(HttpServletRequest request, ModelMap model) {
		model.addAttribute("classList", classService.getAllClass());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "classwisefeedefaulters";
	}

	@RequestMapping(value = "/getclassdefaulters/{classId}", method = RequestMethod.POST)
	public @ResponseBody String finddefaulterlist(
			@PathVariable("classId") int classId, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		List<StudentDTO> list = studentFeeSubmissionDetailsService
				.getClassWiseDefaulterList(classId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "showclasswisedefaulterpdfreport", method = RequestMethod.GET)
	public String classwisepdfdefaulterlist(@RequestParam int id,
			ModelMap model, HttpServletRequest request) {
		List<StudentDTO> list = studentFeeSubmissionDetailsService
				.getClassWiseDefaulterList(id);
		model.addAttribute("list", list);
		return "pdfclassdefaulter";
	}
	@RequestMapping(value = "showclasswisedefaulterexcelreport", method = RequestMethod.GET)
	public ModelAndView classwiseexceldefaulterlist(@RequestParam int id,
			ModelMap model, HttpServletRequest request,ModelAndView modelAndView) {
		List<StudentDTO> list = studentFeeSubmissionDetailsService
				.getClassWiseDefaulterList(id);
		model.addAttribute("list", list);
		return new ModelAndView("ClassFeeDefaulter", "list", list);
	}

	@RequestMapping(value = "pdf-alldefaulters", method = RequestMethod.GET)
	public String pdfalldefaulters(ModelMap model, HttpServletRequest request) {
		List<StudentDTO> list = studentFeeSubmissionDetailsService
				.getAllDefaulters();
		model.addAttribute("list", list);
		return "pdfalldefaulters";
	}

	@RequestMapping(value = "generatePDF.html", method = RequestMethod.GET)
	public String addStudentFine(
			@RequestParam(defaultValue = "0", required = false) int slipNo,
			@RequestParam(defaultValue = "0", required = false) String studentAdmissionNo,
			ModelMap model) {
		CommonDTO commonDTO = studentFeeSubmissionDetailsService
				.getDataForFeeReceiptPDFGeneration(studentAdmissionNo, slipNo);
		model.addAttribute("CommonDTO", commonDTO);
		
		//return "preschoolreceipt";
		//return "pdfkennedyslip";
		//return "pdfkanchanslip";
		//return "pdfcommonreceipt";
		//return "ymbfeeslip";
		//return "pdffeeslip";
		return "maharanaslip";
	}

	@RequestMapping(value = "/fee-submission-details", method = RequestMethod.GET)
	public String showStudentdetails(Model model, HttpServletRequest request) {
		model.addAttribute("studentDTO", new StudentDTO());
		model.addAttribute("classList", classService.getAllClass());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("sectionList", sectionService.findAll(Section.class));
		return "feesubmissiondetails";
	}

	@RequestMapping(value = "verifyfeecategory", method = RequestMethod.GET)
	public @ResponseBody String verifyfee(@RequestParam String name,
			ModelMap model, HttpServletRequest request)
			throws JsonProcessingException {
		String var = feecategoriesService.verifyduplicatename(name);
		return var;
	}
	@RequestMapping(value = "deletefeeclasswise", method = RequestMethod.GET)
	public @ResponseBody
	String deleteAlletedFees(@RequestParam int classId,
			 @RequestParam int monthId,
			@RequestParam int classWiseFeeId) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String status = classWiseFeeService.deleteAllotedFeesCategory(classId,
				monthId, classWiseFeeId);
		return mapper.writeValueAsString(status);
	}
	@RequestMapping(value = "deletefeesectionwise", method = RequestMethod.POST)
	public @ResponseBody
	String deleteSectionWiseAlletedFees(@RequestParam int classId,
			@RequestParam int sectionId, @RequestParam int monthId,
			@RequestParam int sectionWiseFeeId) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String status = sectionWiseFeeService.deleteAllotedFeesCategory(
				classId, monthId, sectionId, sectionWiseFeeId);
		
		return mapper.writeValueAsString(status);
	}
	@RequestMapping(value = "deletefeestudentwise", method = RequestMethod.POST)
	public @ResponseBody
	String deleteStudentWiseAlletedFees(@RequestParam int classId,
			@RequestParam int sectionId, @RequestParam int monthId,
			@RequestParam int studentWiseFeeId,@RequestParam String studentAdmissionNo) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String status = studentwiseFeeService.deleteAllotedFeesCategory(
				classId, monthId, sectionId, studentWiseFeeId,studentAdmissionNo);
		
		return mapper.writeValueAsString(status);
	}
}
