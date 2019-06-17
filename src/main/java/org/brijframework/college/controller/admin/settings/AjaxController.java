package org.brijframework.college.controller.admin.settings;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.brijframework.college.models.dto.CityDTO;
import org.brijframework.college.models.dto.CountryDTO;
import org.brijframework.college.models.dto.ExpenseDTO;
import org.brijframework.college.models.dto.SectionDTO;
import org.brijframework.college.models.dto.SessionDTO;
import org.brijframework.college.models.dto.StateDTO;
import org.brijframework.college.models.dto.StudentAdmissionDTO;
import org.brijframework.college.models.dto.StudentClassesDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;
import org.brijframework.college.service.CityService;
import org.brijframework.college.service.CountryService;
import org.brijframework.college.service.EmployeeRoleService;
import org.brijframework.college.service.ExpenseService;
import org.brijframework.college.service.LastDateService;
import org.brijframework.college.service.SectionService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StateService;
import org.brijframework.college.service.StudentCategoryService;
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
@RequestMapping({ "/admin/**", "/employee/**" })
public class AjaxController {
	@Autowired
	StudentsAdmissionService studentsAdmissionService;
	@Autowired
	StateService stateService;
	@Autowired
	CityService cityService;
	@Autowired
	StudentFeeSubmissionDetailsService studentFeeSubmissionDetailsService;
	@Autowired
	ExpenseService expenseService;
	@Autowired
	LastDateService lastDateService;
	@Autowired
	private StudentCategoryService studentCategoryService;
	@Autowired
	private EmployeeRoleService employeeRoleService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private StudentClassesService studentClassesService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "/getStudentsListsbyclass/{id1}/{id2}/{id3}", method = RequestMethod.POST)
	public @ResponseBody String getStudentsbyclass(
			@PathVariable("id1") int classId,
			@PathVariable("id2") int sectionId,
			@PathVariable("id3") int sessionId, HttpServletRequest request,
			Model model) throws JsonProcessingException {

		List<StudentDTO> list = studentsAdmissionService
				.getStudentsbyClassandSection(classId, sectionId, sessionId);

		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/getstate/{id}", method = RequestMethod.POST)
	public @ResponseBody String getState(@PathVariable("id") int id,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {

		List<StateDTO> list = stateService.getStateByCountryId(id);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/getcityList/{id}/{countryId}", method = RequestMethod.POST)
	public @ResponseBody String getCity(@PathVariable("id") int id,
			@PathVariable("countryId") int countryId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {

		List<CityDTO> list = cityService.getCityByStateIdAndcountryId(id,
				countryId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/removestudent/{id}/{firstName}/{sessionId}/{classId}", method = RequestMethod.POST)
	public @ResponseBody String removeStudents(@PathVariable("id") String id,
			@PathVariable("firstName") String firstName,
			@PathVariable("sessionId") int sessionId,
			@PathVariable("classId") int classId, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		studentsAdmissionService.setActiveById(id);
		List<StudentDTO> list = studentsAdmissionService
				.getStudentsByClassIdAndName(classId, firstName, sessionId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/getStudents/{classId}/{firstName}/{sessionId}", method = RequestMethod.POST)
	public @ResponseBody String getStudents(
			@PathVariable("classId") int classId,
			@PathVariable("firstName") String firstName,
			@PathVariable("sessionId") int sessionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<StudentDTO> list = studentsAdmissionService
				.getStudentsByClassIdAndName(classId, firstName, sessionId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/getStudentsbyfatherName/{fatherName}/{sessionId}", method = RequestMethod.POST)
	public @ResponseBody String getStudentsbyfathername(
			@PathVariable("fatherName") String fatherName,
			@PathVariable("sessionId") int sessionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {

		List<StudentDTO> list = studentsAdmissionService
				.getStudentsbyFathername(fatherName, sessionId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}
	@RequestMapping(value = "/getStudentsbyfatherNames/{categoryId}/{sessionId}", method = RequestMethod.POST)
	public @ResponseBody String getStudentsbyCategory(
			@PathVariable("categoryId") int categoryId,
			@PathVariable("sessionId") int sessionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<StudentDTO> list = studentsAdmissionService
				.getStudentsbyFathername(categoryId, sessionId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/removestudentbyfather/{id}/{fatherName}/{sessionId}", method = RequestMethod.POST)
	public @ResponseBody String removeStudentsbyfather(
			@PathVariable("id") String id,
			@PathVariable("fatherName") String fatherName,
			@PathVariable("sessionId") int sessionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		studentsAdmissionService.setActiveById(id);
		List<StudentDTO> list = studentsAdmissionService
				.getStudentsbyFathername(fatherName, sessionId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/removeStudentbyclass/{id}/{classId}/{sectionId}/{sessionId}", method = RequestMethod.POST)
	public @ResponseBody String removeStudentsbyclass(
			@PathVariable("id") String id,
			@PathVariable("classId") int classId,
			@PathVariable("sectionId") int sectionId,
			@PathVariable("sessionId") int sessionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		studentsAdmissionService.setActiveById(id);
		List<StudentDTO> list = studentsAdmissionService
				.getStudentsbyClassandSection(classId, sectionId, sessionId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/showsubmissiondetails/{id}/{sessionId}", method = RequestMethod.POST)
	public @ResponseBody String getsubmissiondtails(
			@PathVariable("id") String admissionNo,
			@PathVariable("sessionId") int sessionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<StudentFeeSubmissionDetailsDTO> list = studentFeeSubmissionDetailsService
				.getallfeesubmissiondetails(admissionNo, sessionId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/getYearlyExpense/{yearf}/{yeart}", method = RequestMethod.POST)
	public @ResponseBody String yearlyexpense(
			@PathVariable("yearf") int yearfrom,
			@PathVariable("yeart") int yearto, HttpServletRequest request,
			Model model) throws JsonProcessingException, ParseException {
		List<ExpenseDTO> list = expenseService.findyearlyexpense(yearfrom,
				yearto);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/getDefaulterList/{sessionId}/{classId}/{sectionId}/{monthId}", method = RequestMethod.POST)
	public @ResponseBody String finddefaulterlist(
			@PathVariable("sessionId") int sessionId,
			@PathVariable("classId") int classId,
			@PathVariable("sectionId") int sectionId,
			@PathVariable("monthId") int monthId, HttpServletRequest request,
			Model model) throws JsonProcessingException {

		List<StudentDTO> list = studentFeeSubmissionDetailsService
				.getDefaulterList(sessionId, classId, sectionId, monthId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/checklastdate/{id1}", method = RequestMethod.POST)
	public @ResponseBody String checklastdate(@PathVariable("id1") int monthId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<String> check = lastDateService.getLastDatecheck(monthId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(check);
	}

	@RequestMapping(value = "/getCancelledList/{sessionId}", method = RequestMethod.POST)
	public @ResponseBody String getCancelledList(
			@PathVariable("sessionId") int sessionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<StudentDTO> list = studentsAdmissionService
				.getCancelledStudentsbySession(sessionId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}
	@RequestMapping(value = "/regCancelledList/{sessionId}", method = RequestMethod.POST)
	public @ResponseBody String cancelreg(
			@PathVariable("sessionId") int sessionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<StudentAdmissionDTO> list = studentsAdmissionService
				.getCancelledRegistration(sessionId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}
	@RequestMapping(value = "/regTransferList/{sessionId}", method = RequestMethod.POST)
	public @ResponseBody String transfer(
			@PathVariable("sessionId") int sessionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<StudentAdmissionDTO> list = studentsAdmissionService
				.getTransferredRegistration(sessionId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}
	@RequestMapping(value = "/getStudentsListsbyclassforfee/{id1}/{id2}/{id3}", method = RequestMethod.POST)
	public @ResponseBody String getStudentsbyclassforfee(
			@PathVariable("id1") int classId,
			@PathVariable("id2") int sectionId,
			@PathVariable("id3") int sessionId, HttpServletRequest request,
			Model model) throws JsonProcessingException {

		List<StudentDTO> list = studentsAdmissionService
				.getStudentsbyClassandSectionfeecheck(classId, sectionId,
						sessionId);

		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/getPaidList/{sessionId}/{classId}/{sectionId}/{monthId}", method = RequestMethod.POST)
	public @ResponseBody String findpaidlist(
			@PathVariable("sessionId") int sessionId,
			@PathVariable("classId") int classId,
			@PathVariable("sectionId") int sectionId,
			@PathVariable("monthId") int monthId, HttpServletRequest request,
			Model model) throws JsonProcessingException {

		List<StudentDTO> list = studentFeeSubmissionDetailsService.getPaidList(
				sessionId, classId, sectionId, monthId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "checkforstudentcategory", method = RequestMethod.POST)
	public @ResponseBody String checkForStudentCategory(
			@RequestParam String studentCategory)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(studentCategoryService
				.getCategory(studentCategory));
	}

	@RequestMapping(value = "check_city_name", method = RequestMethod.POST)
	public @ResponseBody String checkForCityName(@RequestParam String cityName,
			@RequestParam int countryId, @RequestParam int stateId)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(cityService
				.getCityByStateIdAndcountryId(stateId, countryId, cityName));
	}

	@RequestMapping(value = "check_session_name", method = RequestMethod.POST)
	public @ResponseBody String checkForSessionName(
			@RequestParam String sessionName) throws JsonProcessingException {
		String sendingString = "";
		SessionDTO sessionDTO = sessionService.getSessionByName(sessionName);
		if (sessionDTO.getSessionDuration() == null) {
			sendingString = "NO";
		} else {
			sendingString = "YES";
		}
		return new ObjectMapper().writeValueAsString(sendingString);
	}

	@RequestMapping(value = "check_class_name", method = RequestMethod.POST)
	public @ResponseBody String checkForClassName(@RequestParam String className)
			throws JsonProcessingException {
		String sendingString = "";
		StudentClassesDTO studentClassesDTO = studentClassesService
				.getClassByName(className);
		if (studentClassesDTO.getClassName() == null) {
			sendingString = "NO";
		} else {
			sendingString = "YES";
		}
		return new ObjectMapper().writeValueAsString(sendingString);
	}

	@RequestMapping(value = "check_section_name", method = RequestMethod.POST)
	public @ResponseBody String checkForSectionName(@RequestParam int classId,
			@RequestParam String sectionName) throws JsonProcessingException {
		String sendingString = "";
		SectionDTO sectionDTO = sectionService
				.getSectionByClassIdAndSectionName(classId, sectionName);
		if (sectionDTO.getSectionName() == null) {
			sendingString = "NO";
		} else {
			sendingString = "YES";
		}
		return new ObjectMapper().writeValueAsString(sendingString);
	}

	@RequestMapping(value = "check_country_name", method = RequestMethod.POST)
	public @ResponseBody String checkForCountryName(
			@RequestParam String countryName) throws JsonProcessingException {
		String sendingString = "";
		CountryDTO countryDTO = countryService.getCountryByName(countryName);
		if (countryDTO.getCountryName() == null) {
			sendingString = "NO";
		} else {
			sendingString = "YES";
		}
		return new ObjectMapper().writeValueAsString(sendingString);
	}

	@RequestMapping(value = "check_state_name", method = RequestMethod.POST)
	public @ResponseBody String checkForStateName(
			@RequestParam String stateName, @RequestParam int countryId)
			throws JsonProcessingException {
		String sendingString = "";
		StateDTO stateDTO = stateService.getStateByName(countryId, stateName);
		if (stateDTO.getStateName() == null) {
			sendingString = "NO";
		} else {
			sendingString = "YES";
		}
		return new ObjectMapper().writeValueAsString(sendingString);
	}
	@RequestMapping(value = "/removestudentbycategory/{id}/{sessionId}/{categoryId}", method = RequestMethod.POST)
	public @ResponseBody String removeStudentsbycategory(
			@PathVariable("id") String id,
			@PathVariable("categoryId") int categoryId,
			@PathVariable("sessionId") int sessionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		studentsAdmissionService.setActiveById(id);
		List<StudentDTO> list = studentsAdmissionService
				.getStudentsbyFathername(categoryId, sessionId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}
	@RequestMapping(value = "/getStudentsbycategory/{categoryId}/{sessionId}/{classId}", method = RequestMethod.POST)
	public @ResponseBody String getStudentsbycategory(
			@PathVariable("classId") int classId,
			@PathVariable("categoryId") int categoryId,
			@PathVariable("sessionId") int sessionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
			List<StudentDTO> list = studentsAdmissionService
				.getStudentsbyCategoryId(categoryId, sessionId,classId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}
	@RequestMapping(value = "/removestudentbyclasscategory/{id}/{sessionId}/{categoryId}/{classId}", method = RequestMethod.POST)
	public @ResponseBody String removeStudentsbyclasscategory(
			@PathVariable("id") String id,
			@PathVariable("categoryId") int categoryId,
			@PathVariable("sessionId") int sessionId,
			@PathVariable("classId") int classId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		studentsAdmissionService.setActiveById(id);
		List<StudentDTO> list = studentsAdmissionService
				.getStudentsbyCategoryId(categoryId, sessionId,classId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}
	@RequestMapping(value = "/removestudentsbyclasses/{id}/{classId}/{sessionId}", method = RequestMethod.POST)
	public @ResponseBody String removeStudentsbyclasses(
			@PathVariable("id") String id,
		    @PathVariable("sessionId") int sessionId,
			@PathVariable("classId") int classId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		studentsAdmissionService.setActiveById(id);
		List<StudentDTO> list=studentsAdmissionService
				.getStudentsBySessionClassId(classId,sessionId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}
}
