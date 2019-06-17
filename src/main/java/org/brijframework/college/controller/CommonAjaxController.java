package org.brijframework.college.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.brijframework.college.dao.SubjectDao;
import org.brijframework.college.model.Subjects;
import org.brijframework.college.models.dto.EmployeesDTO;
import org.brijframework.college.models.dto.MonthlyExamReportDTO;
import org.brijframework.college.models.dto.SectionDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.SubjectDTO;
import org.brijframework.college.service.AnnualExamGainMarksService;
import org.brijframework.college.service.AnnualExamReportService;
import org.brijframework.college.service.AnnuallySubjectMarksService;
import org.brijframework.college.service.EmployeeService;
import org.brijframework.college.service.MonthService;
import org.brijframework.college.service.MonthlyExamReportService;
import org.brijframework.college.service.SectionService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.brijframework.college.service.SubjectService;
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
@RequestMapping("/**")
public class CommonAjaxController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private StudentsAdmissionService studentsAdmissionService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private MonthlyExamReportService monthlyExamReportService;
	@Autowired
	private MonthService monthService;
	@Autowired
	private AnnualExamGainMarksService annualExamGainMarksService;
	@Autowired
	private AnnualExamReportService annualExamReportService;
	@Autowired
	SectionService sectionService;
	@Autowired
	StudentsAdmissionService admissionService;
	@Autowired
	SessionService sessionService;
	@Autowired
	SubjectDao subjectDao;
	@Autowired
	private StudentClassesService studentClassesService;
	@Autowired
	private AnnuallySubjectMarksService annuallySubjectMarksService;

	@RequestMapping(value = "/getsectionbyclassid", method = RequestMethod.POST)
	public @ResponseBody String getSectionList(
			@RequestParam("sessionId") String sessionId,
			@RequestParam("classId") String classId, HttpServletRequest request)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<SectionDTO> sectionDTOs = sectionService
				.getSectionListByClassId(Integer.parseInt(classId));
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

	@RequestMapping(value = "getEmployee/{firstName}", method = RequestMethod.POST)
	public @ResponseBody String getEmployees(
			@PathVariable("firstName") String firstName,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {

		List<EmployeesDTO> list = employeeService
				.getEmployeesbyFirstname(firstName);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "removeEmployee/{id}/{firstName}", method = RequestMethod.POST)
	public @ResponseBody String removeStudents(@PathVariable("id") int id,
			@PathVariable("firstName") String firstName,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		employeeService.setActiveById(id);
		List<EmployeesDTO> list = employeeService
				.getEmployeesbyFirstname(firstName);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "get-student-subject-list", method = RequestMethod.POST)
	public @ResponseBody String getStudentSubjectList(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam int sectionId) throws JsonProcessingException,
			ParseException {
		ObjectMapper mapper = new ObjectMapper();
		Map map = new HashMap();
		map.put("StudentList", studentsAdmissionService
				.getStudentsbyClassandSection(classId, sectionId, sessionId));
		map.put("SubjectList",
				subjectService.getSubjectById(classId, sectionId, sessionId));
		return mapper.writeValueAsString(map);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "get-student-subject-list-marks", method = RequestMethod.POST)
	public @ResponseBody String getStudentSubjectListmarks(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam int monthId)
			throws JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		Map map = new HashMap();
		map.put("StudentList", studentsAdmissionService
				.getStudentsbyClassandSection(classId, sectionId, sessionId));
		map.put("SubjectList", subjectService.getSubjectByIdforMarks(classId,
				sectionId, sessionId, monthId));
		return mapper.writeValueAsString(map);
	}

	@RequestMapping(value = "get-student-list-marks", method = RequestMethod.POST)
	public @ResponseBody String getStudentListmarks(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam int monthId)
			throws JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(subjectService
				.getSubjectByIdforMarksStudent(classId, sectionId, sessionId,
						monthId));
	}

	@RequestMapping(value = "checkformarksassign", method = RequestMethod.POST)
	public @ResponseBody String checkForMarksAssign(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam String studentId,
			@RequestParam int subjectId, @RequestParam int monthId)
			throws JsonProcessingException, ParseException {
		String stringToSend = "";
		ObjectMapper mapper = new ObjectMapper();
		MonthlyExamReportDTO monthlyExamReportDTO = monthlyExamReportService
				.getMonthlyExamReport(sessionId, classId, sectionId, studentId,
						subjectId, monthId);
		if (monthlyExamReportDTO != null) {
			stringToSend = "OCCURE";
		}
		return mapper.writeValueAsString(stringToSend);
	}

	@RequestMapping(value = "getsubjectmarks", method = RequestMethod.POST)
	public @ResponseBody String getAssignSubjectMarkstoStudent(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam String studentId)
			throws JsonProcessingException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper
				.writeValueAsString(monthlyExamReportService
						.getMonthlyExamReport(sessionId, classId, sectionId,
								studentId));
	}

	@RequestMapping(value = "getmonthwisesunjectmarks", method = RequestMethod.POST)
	public @ResponseBody String getMonthWiseSubjectMarks(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam String studentId,
			@RequestParam int monthId) throws JsonProcessingException,
			ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("MonthName", monthService.read(monthId).getMonthName());
		map.put("MonthlyExamReportDTO", monthlyExamReportService
				.getMonthlyExamReport(sessionId, classId, sectionId, studentId,
						monthId));
		return new ObjectMapper().writeValueAsString(map);
	}

	@RequestMapping(value = "getsubjectlist", method = RequestMethod.POST)
	public @ResponseBody String getSubjectList(@RequestParam int sessionId,
			@RequestParam int classId, @RequestParam int sectionId)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper().writeValueAsString(subjectService
				.getSubjectById(classId, sectionId, sessionId));
	}

	@RequestMapping(value = "getsubjectmarksassign", method = RequestMethod.POST)
	public @ResponseBody String getSubjectMarksAssign(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam String studentId,
			@RequestParam String annualExamType, @RequestParam String examType)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(annualExamGainMarksService
				.getAnnualExamGainMarksList(sessionId, classId, sectionId,
						studentId, annualExamType, examType));
	}

	@RequestMapping(value = "getdataforreportcard", method = RequestMethod.POST)
	public @ResponseBody String getDataForReportCard(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam String studentId)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(annualExamReportService
				.getDataForGenerateReportCard(sessionId, classId, sectionId,
						studentId));
	}

	@RequestMapping(value = "getsubjectname", method = RequestMethod.POST)
	public @ResponseBody String getSubjectName(@RequestParam int subjectId)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(subjectService
				.getSubjectById(subjectId));
	}

	@RequestMapping(value = "updatesubjectname", method = RequestMethod.POST)
	public @ResponseBody String updateSubject(@RequestParam int subjectId,
			@RequestParam String subjectName) throws JsonProcessingException {
		Subjects subjects = subjectService.read(subjectId);
		subjects.setSubjectName(subjectName);
		subjectDao.update(subjects);
		return new ObjectMapper().writeValueAsString(subjectService
				.getSubjectById(subjectId));
	}

	@RequestMapping(value = "checkforexistanceofsubject", method = RequestMethod.POST)
	public @ResponseBody String getVerifySubjects(@RequestParam int classId,
			@RequestParam int sectionId, @RequestParam String subjectName,
			@RequestParam int sessionId) throws JsonProcessingException {
		String stringToSend = "";
		SubjectDTO subjectDTO = subjectService.getSubjects(classId, sectionId,
				subjectName, sessionId);
		if (subjectDTO.getSubjectName() == null) {
			stringToSend = "NO";
		} else {
			stringToSend = "YES";
		}
		return new ObjectMapper().writeValueAsString(stringToSend);
	}

	@RequestMapping(value = "deletesubjects", method = RequestMethod.POST)
	public @ResponseBody String deleteSubjects(@RequestParam int subjectId) {
		subjectService.setActiveById(subjectId);
		return "Subject Delete Successfuly";
	}

	@RequestMapping(value = "removeEmployeefromall/{id}", method = RequestMethod.POST)
	public @ResponseBody String removeemployee(@PathVariable("id") int id,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		employeeService.setActiveById(id);
		List<EmployeesDTO> list = employeeService.findAllEmployee();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "getMonths", method = RequestMethod.POST)
	public @ResponseBody String getMonths() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(studentClassesService
				.getAllClass());
	}

	@RequestMapping(value = "getsubjectforannuallyexam", method = RequestMethod.POST)
	public @ResponseBody String getSubjectListForAnnuallyExam(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam String examType,
			@RequestParam String annualExamType)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper()
				.writeValueAsString(annuallySubjectMarksService
						.getAnnuallySubjectMarks(sessionId, classId, sectionId,
								examType, annualExamType));
	}

	@RequestMapping(value = "students-for-annually-exam", method = RequestMethod.POST)
	public @ResponseBody String getStudentListForAnnuallyExam(
			@RequestParam int sessionId, @RequestParam int classId,
			@RequestParam int sectionId, @RequestParam String examType,
			@RequestParam String annualExamType)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper().writeValueAsString(annualExamReportService
				.getAnnualExamReport(sessionId, classId, sectionId, examType,
						annualExamType));
	}

	@RequestMapping(value = "get-Max-marks", method = RequestMethod.POST)
	public @ResponseBody String getMaxMarks(@RequestParam int sessionId,
			@RequestParam int classId, @RequestParam int sectionId,
			@RequestParam String examType, @RequestParam String annualExamType)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper().writeValueAsString(annualExamReportService
				.getMaxMarksforExam(sessionId, classId, sectionId, examType,
						annualExamType));
	}

	@RequestMapping(value = "get-student-section-by-class", method = RequestMethod.POST)
	public @ResponseBody String getStudentClassWise(@RequestParam int classId,
			@RequestParam int sessionId) throws JsonProcessingException {
		List<SectionDTO> sectionDTOs = sectionService
				.getSectionListByClassId(classId);
		List<StudentDTO> studentDTOs = studentsAdmissionService
				.getStudentsBySessionClassId(classId, sessionId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("SectionDTO", sectionDTOs);
		map.put("StudentDTO", studentDTOs);
		return new ObjectMapper().writeValueAsString(map);
	}
}
