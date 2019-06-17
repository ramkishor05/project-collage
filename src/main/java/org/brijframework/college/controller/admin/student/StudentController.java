package org.brijframework.college.controller.admin.student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.apache.commons.io.FileUtils;
import org.brijframework.college.model.City;
import org.brijframework.college.model.Country;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.State;
import org.brijframework.college.model.StudentCategory;
import org.brijframework.college.models.dto.NewStudentTransferDTO;
import org.brijframework.college.models.dto.StudentAdmissionDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.StudentDocumentDTO;
import org.brijframework.college.models.dto.StudentTransferDTO;
import org.brijframework.college.service.CityService;
import org.brijframework.college.service.CountryService;
import org.brijframework.college.service.DocumentCategoryService;
import org.brijframework.college.service.NewStudentTransferService;
import org.brijframework.college.service.SectionService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StateService;
import org.brijframework.college.service.StudentCategoryService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentDocumentService;
import org.brijframework.college.service.StudentTransferService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**", "/branch/**" })
public class StudentController {
	@Autowired
	StudentsAdmissionService admissionService;
	@Autowired
	StudentClassesService classService;
	@Autowired
	SectionService sectionService;
	@Autowired
	StudentCategoryService categoryService;
	@Autowired
	SessionService sessionService;
	@Autowired
	CountryService countryService;
	@Autowired
	StateService stateService;
	@Autowired
	CityService cityService;
	@Autowired
	StudentTransferService studentTransferService;
	@Autowired
	StudentDocumentService studentDocumentService;
	@Autowired
	DocumentCategoryService documentCategoryService;
	@Autowired
	NewStudentTransferService newStudentTransferService;

	// private Logger logger = Logger.getLogger(StudentController.class);

	@RequestMapping(value = "/students-manage", method = RequestMethod.GET)
	public String showSetting(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "studentsmanage";
	}

	@RequestMapping(value = "/registration-menu", method = RequestMethod.GET)
	public String showmenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "registrationmenu";
	}

	@RequestMapping(value = "/get-rollno", method = RequestMethod.POST)
	public @ResponseBody String getRollNo(@RequestParam String classId,
			@RequestParam String sectionId) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(admissionService
				.getRollNo(classId, sectionId));
	}

	@RequestMapping(value = "/studentsadmission", method = RequestMethod.GET)
	public String studentAdmission(Model model, HttpServletRequest request) {
		String[] uniqueNo = admissionService.getStudentAdmissionNo();
		model.addAttribute("studentAdmissionNum", uniqueNo[0]);
		model.addAttribute("lfNO", uniqueNo[1]);
		model.addAttribute("studentId", uniqueNo[2]);
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("sectionList", sectionService.findAll(Section.class));
		model.addAttribute("studentcategoryList",
				categoryService.findAllByIsDeletedTrue(StudentCategory.class));
		model.addAttribute("countryList",
				countryService.findAllByIsDeletedTrue(Country.class));
		model.addAttribute("StudentDetails", new StudentDTO());
		model.addAttribute("documentList",
				documentCategoryService.getDocumentByType("Student"));
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "studentsadmission";
	}

	@RequestMapping(value = "/ragistration", method = RequestMethod.POST)
	public void studentAdmission(
			@ModelAttribute("StudentDetails") StudentDTO studentDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException {
		int admissionNo = admissionService.getUniqueIdNo();
		if (studentDTO.getPhoto().getSize() > 0) {
			MultipartFile file = studentDTO.getPhoto();
			String type = file.getContentType().split("/")[0];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (type.equalsIgnoreCase("image")) {
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/img/studentImages/" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		}
		if (studentDTO.getParentphoto1().getSize() > 0) {
			MultipartFile file = studentDTO.getParentphoto1();
			String type = file.getContentType().split("/")[0];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (type.equalsIgnoreCase("image")) {
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/img/parentImages/" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		}
		if (studentDTO.getParentphoto2().getSize() > 0) {
			MultipartFile file = studentDTO.getParentphoto2();
			String type = file.getContentType().split("/")[0];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (type.equalsIgnoreCase("image")) {
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/img/parentImages/" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		}
		if (studentDTO.getParentphoto3().getSize() > 0) {
			MultipartFile file = studentDTO.getParentphoto3();
			String type = file.getContentType().split("/")[0];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (type.equalsIgnoreCase("image")) {
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/img/parentImages/" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		}
		admissionService.registerStudentDetails(studentDTO);
		if (studentDTO.getDocumentList() != null) {
			for (StudentDocumentDTO documentDTO : studentDTO.getDocumentList()) {

				if (documentDTO.getDocumentFile().getSize() > 0) {
					MultipartFile files = documentDTO.getDocumentFile();
					String name = documentDTO.getDocumentName() + "("
							+ studentDTO.getAdmissionNo().trim() + ")";
					File file = new File(request.getSession()
							.getServletContext().getRealPath("/")
							+ "/studentDocuments/"
							+ name
							+ files.getOriginalFilename());

					FileUtils.writeByteArrayToFile(file, files.getBytes());
					/*
					 * System.out.println("Go to the location:  " +
					 * file.toString() +
					 * " on your computer and verify that the image has been stored."
					 * );
					 */

					documentDTO.setFileName(name + files.getOriginalFilename());
					documentDTO
							.setStudentId(studentDTO.getAdmissionNo().trim());
				}
			}
			studentDocumentService.saveUploadDocuments(studentDTO
					.getDocumentList());

		}

		response.sendRedirect("show-student-details?id=" + admissionNo + "");
	}

	@RequestMapping(value = "/student-details", method = RequestMethod.GET)
	public String showStudentdetails(
			@ModelAttribute("studentDTO") StudentDTO studentDTO, Model model,
			HttpServletRequest request) {
		model.addAttribute("studentDTO", new StudentDTO());
		model.addAttribute("classList", classService.getAllClass());
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		model.addAttribute("sectionList", sectionService.findAll(Section.class));
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("StudentCategorys",
				categoryService.findAll(StudentCategory.class));
		return "viewstudentdetails";
	}

	@RequestMapping(value = "/student-details", method = RequestMethod.POST)
	public String showStudentdetail(
			@ModelAttribute("studentDTO") StudentDTO studentDTO, Model model,
			HttpServletRequest request) {
		model.addAttribute("studentDTO", new StudentDTO());
		model.addAttribute("classList", classService.getAllClass());
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		model.addAttribute("sectionList", sectionService.findAll(Section.class));
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		return "viewstudentdetails";
	}

	@RequestMapping(value = "/show-student-details", method = RequestMethod.GET)
	public String showStudentDetails(Model model, @RequestParam String id,
			HttpServletRequest request) {
		StudentDTO studentDTO = admissionService.findStudentDetails(id);
		model.addAttribute("StudentDetailsBean", studentDTO);
		List<StudentDocumentDTO> documentList = studentDocumentService
				.getDocumentForStudent(studentDTO.getStudentId());
		model.addAttribute("documentList", documentList);
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "showstudentdetails";
	}

	@RequestMapping(value = "/show-student-profile", method = RequestMethod.GET)
	public String showInActiveStudentDetails(Model model,
			@RequestParam String id, HttpServletRequest request) {
		StudentDTO studentDTO = admissionService.findInActiveStudentDetails(id);
		model.addAttribute("StudentDetailsBean", studentDTO);
		List<StudentDocumentDTO> documentList = studentDocumentService
				.getDocumentForStudent(studentDTO.getStudentId());
		model.addAttribute("documentList", documentList);
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "showstudentdetails";
	}

	@RequestMapping(method = RequestMethod.GET, value = "student-profile.html")
	public ModelAndView studentProfile(ModelAndView modelAndView,
			@RequestParam String id, HttpServletRequest request) {
		StudentDTO studentDTO = admissionService.findStudentDetails(id);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		list.add(studentDTO);
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(list);
		String reportsDirPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/img/studentImages/";
		File reportsDir = new File(reportsDirPath);

		parameterMap.put(JRParameter.REPORT_FILE_RESOLVER,
				new SimpleFileResolver(reportsDir));
		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("student-profile", parameterMap);

		return modelAndView;

	}

	@RequestMapping(value = "/edit-student-details", method = RequestMethod.GET)
	public String ediStudentDetails(Model model, @RequestParam String id,
			HttpServletRequest request) {
		model.addAttribute("sessionList",
				sessionService.findAllByIsDeletedTrue(Session.class));

		model.addAttribute("studentcategoryList",
				categoryService.findAllByIsDeletedTrue(StudentCategory.class));
		model.addAttribute("countryList",
				countryService.findAllByIsDeletedTrue(Country.class));
		model.addAttribute("classList", classService.getAllClass());

		model.addAttribute("sectionList", sectionService.findAll(Section.class));

		model.addAttribute("stateList",
				countryService.findAllByIsDeletedTrue(State.class));
		model.addAttribute("cityList",
				countryService.findAllByIsDeletedTrue(City.class));
		StudentDTO studentDTO = admissionService.findStudentDetails(id);
		model.addAttribute("studentDTO", studentDTO);
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "editstudentdetails";
	}

	@RequestMapping(value = "/edit-student-details", method = RequestMethod.POST)
	public void updateStudentDetails(
			@ModelAttribute("studentDTO") StudentDTO studentDTO, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException {
		if (studentDTO.getPhoto().getSize() > 0
				&& studentDTO.getPhoto() != null) {
			MultipartFile file = studentDTO.getPhoto();
			String type = file.getContentType().split("/")[0];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (type.equalsIgnoreCase("image")) {
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/img/studentImages/" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		}
		if (studentDTO.getParentphoto1().getSize() > 0) {
			MultipartFile file = studentDTO.getParentphoto1();
			String type = file.getContentType().split("/")[0];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (type.equalsIgnoreCase("image")) {
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/img/parentImages/" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		}
		if (studentDTO.getParentphoto2().getSize() > 0) {
			MultipartFile file = studentDTO.getParentphoto2();
			String type = file.getContentType().split("/")[0];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (type.equalsIgnoreCase("image")) {
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/img/parentImages/" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		}
		if (studentDTO.getParentphoto3().getSize() > 0) {
			MultipartFile file = studentDTO.getParentphoto3();
			String type = file.getContentType().split("/")[0];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (type.equalsIgnoreCase("image")) {
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/img/parentImages/" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		}
		admissionService.updateStudentDetails(studentDTO);

		response.sendRedirect("show-student-details?id="
				+ studentDTO.getAdmissionNo() + "");
	}

	@RequestMapping(value = "/cancelled-students", method = RequestMethod.GET)
	public String cancelstudents(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		return "cancelledstudents";
	}

	@RequestMapping(value = "/re-admission", method = RequestMethod.GET)
	public String readmission(HttpServletRequest request, Model model,
			@RequestParam String id) {
		admissionService.ReadmitStudent(id);
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		return "cancelledstudents";
	}

	@RequestMapping(value = "/cancelled-registration", method = RequestMethod.GET)
	public String cancelreg(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		return "cancelledlist";
	}

	@RequestMapping(value = "/delete-registration", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, Model model,
			@RequestParam int id) {
		admissionService.deleteRegistration(id);
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		return "cancelledlist";
	}

	@RequestMapping(value = "/transferred-students", method = RequestMethod.GET)
	public String transfer(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		model.addAttribute("sessionList", sessionService.findAll(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		return "transferredlist";
	}

	@RequestMapping(value = "/student-login-details", method = RequestMethod.GET)
	public String showlogin(HttpServletRequest request, Model model) {
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "viewlogindetails";
	}

	@RequestMapping(value = "/getstudentlogindetails/{id1}/{id2}/{id3}", method = RequestMethod.POST)
	public @ResponseBody String getStudentsbyclass(
			@PathVariable("id1") int classId,
			@PathVariable("id2") int sessionId,
			@PathVariable("id3") int pageno, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		int totalNoOfPages = 0;
		List<StudentDTO> totalList = admissionService
				.getStudentsbySessionandClass(classId, sessionId);
		totalNoOfPages = (totalList.size() / 10) + 1;
		List<StudentDTO> list = admissionService
				.getStudentsbySessionClasspageno(classId, sessionId,
						pageno - 1, totalNoOfPages);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/getlogindetailsbyname/{id1}/{id2}/{id3}", method = RequestMethod.POST)
	public @ResponseBody String logindetailsbyname(
			@PathVariable("id1") int classId,
			@PathVariable("id2") int sessionId,
			@PathVariable("id3") String firstname, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		List<StudentDTO> list = admissionService.getlogindetailsbyname(classId,
				sessionId, firstname);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/updatepassword/{id1}/{id2}/{id3}/{id4}/{id5}", method = RequestMethod.POST)
	public @ResponseBody String changepassword(
			@PathVariable("id1") String changeId,
			@PathVariable("id2") String newpassword,
			@PathVariable("id3") int sessionId,
			@PathVariable("id4") int classId, @PathVariable("id5") int pageno,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		admissionService.updatePasswordbyId(changeId, newpassword);
		int totalNoOfPages = 0;
		List<StudentDTO> totalList = admissionService
				.getStudentsbySessionandClass(classId, sessionId);
		totalNoOfPages = (totalList.size() / 10) + 1;
		List<StudentDTO> list = admissionService
				.getStudentsbySessionClasspageno(classId, sessionId,
						pageno - 1, totalNoOfPages);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/updatepasswordbyname/{id1}/{id2}/{id3}/{id4}/{id5}", method = RequestMethod.POST)
	public @ResponseBody String changepasswordbyname(
			@PathVariable("id1") String changeId,
			@PathVariable("id2") String newpassword,
			@PathVariable("id3") int sessionId,
			@PathVariable("id4") int classId,
			@PathVariable("id5") String firstname, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		admissionService.updatePasswordbyId(changeId, newpassword);
		List<StudentDTO> list = admissionService.getlogindetailsbyname(classId,
				sessionId, firstname);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/admission-pdf", method = RequestMethod.GET)
	public String pdfadmission(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "pdfadmissionform";
	}

	@RequestMapping(value = "/newadmission-pdf", method = RequestMethod.GET)
	public String newpdfadmission(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "pdfadmissionforms";
	}

	@RequestMapping(value = "application-form.html", method = RequestMethod.GET)
	public String NewApplicationForm(Model model) {
		model.addAttribute("studentAdmissionDTO", new StudentAdmissionDTO());
		model.addAttribute("countryList",
				countryService.findAllByIsDeletedTrue(Country.class));
		model.addAttribute("SNo", admissionService.getSerialNo());
		model.addAttribute("classList", classService.getAllClass());
		return "studentnewadmission";
	}

	@RequestMapping(value = "/StudentAdmission", method = RequestMethod.POST)
	public String createNeAdmission(
			@ModelAttribute("studentAdmissionDTO") StudentAdmissionDTO admissionDTO,
			HttpServletRequest request, Model model) throws IOException,
			ParseException {
		if (admissionDTO.getPhoto().getSize() > 0) {
			MultipartFile file = admissionDTO.getPhoto();
			String type = file.getContentType().split("/")[0];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (type.equalsIgnoreCase("image")) {
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/img/studentImages/" + file.getOriginalFilename());
				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		}
		StudentAdmissionDTO dto = admissionService
				.StudentAdmission(admissionDTO);
		model.addAttribute("studentDTO", dto);
		return "pdfadmissionforms";

	}

	@RequestMapping(value = "application-details.html", method = RequestMethod.GET)
	public String ShowAllApplication(Model model) {
		List<StudentAdmissionDTO> dtos = admissionService.getApplicationList();
		model.addAttribute("applicationDetails", dtos);
		return "applicationdetails";
	}

	@RequestMapping(value = "cancel-registration", method = RequestMethod.GET)
	public String cancelApp(@RequestParam int id, Model model) {
		admissionService.cancelRegistration(id);
		List<StudentAdmissionDTO> dtos = admissionService.getApplicationList();
		model.addAttribute("applicationDetails", dtos);
		return "applicationdetails";
	}

	@RequestMapping(value = "show-application", method = RequestMethod.GET)
	public String ShowAApplication(Model model, @RequestParam int id) {
		model.addAttribute("studentDTO",
				admissionService.getStudentAdmissionDetails(id));
		return "pdfadmissionforms";
	}

	@RequestMapping(method = RequestMethod.GET, value = "registrationPDF")
	public ModelAndView childAccessoriesPDF(@RequestParam String studentId,
			HttpServletRequest request) throws ParseException {
		ModelAndView modelAndView;
		String reportsDirPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/img/";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StudentDTO studentDTO = admissionService
				.findStudentDetailsforPDF(studentId);
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		studentDTOs.add(studentDTO);
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(studentDTOs);
		parameterMap.put("imagepath", reportsDirPath + "studentImages/"
				+ studentDTO.getImageName());
		parameterMap.put("photo1name", reportsDirPath + "parentImages/"
				+ studentDTO.getPhoto1name());
		parameterMap.put("photo2name", reportsDirPath + "parentImages/"
				+ studentDTO.getPhoto2name());
		parameterMap.put("photo3name", reportsDirPath + "parentImages/"
				+ studentDTO.getPhoto3name());
		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("registrationPDF", parameterMap);
		return modelAndView;

	}

	@RequestMapping(value = "get-studentdatabyId", method = RequestMethod.POST)
	public @ResponseBody String studentData(
			@RequestParam("studentId") String studentId,
			HttpServletRequest request) throws JsonProcessingException,
			ParseException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(admissionService
				.findStudentData(studentId));

	}

	@RequestMapping(value = "app-form.html", method = RequestMethod.GET)
	public String OffLineAppForm() {
		return "OffLineAppForm";
	}

	@RequestMapping(value = "generate-tc.html", method = RequestMethod.GET)
	public String generateTC(@RequestParam String id, Model model) {
		//StudentTransferDTO dto = studentTransferService.generateTC(id);

		StudentDTO studentDTO = admissionService.findStudentDetails(id);
		NewStudentTransferDTO dto=newStudentTransferService.getneweGeneratedTC(id);
		if (dto.getStudentId() == null) {
			model.addAttribute("Student", studentDTO);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("today", df.format(new Date()));
			model.addAttribute("newStudentTransferDTO",	new NewStudentTransferDTO());
			 //return "generatetc";
			return "generatetcnew";
		} else {

			//return "redirect:getPDFTc?id=" + id;
			return "redirect:new_issue-tcPDF?id="+id;
		}
	}

	@RequestMapping(value = "getPDFTc", method = RequestMethod.GET)
	public ModelAndView getTc(@RequestParam String id,
			ModelAndView modelAndView, HttpServletRequest request)
			throws ParseException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StudentTransferDTO dto = studentTransferService.generateTC(id);
		List<StudentTransferDTO> listdto = new ArrayList<StudentTransferDTO>();
		listdto.add(dto);
		String reportsDirPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/img/";
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(listdto);
		parameterMap.put("datasource", JRdataSource);
		File reportsDir = new File(reportsDirPath);

		parameterMap.put(JRParameter.REPORT_FILE_RESOLVER,
				new SimpleFileResolver(reportsDir));
		modelAndView = new ModelAndView("maharanatc", parameterMap);
		return modelAndView;
	}

	@RequestMapping(value = "issue-tc", method = RequestMethod.POST)
	public ModelAndView issueTC(
			@ModelAttribute("StudentTransferDTO") StudentTransferDTO studentTransferDTO,
			ModelAndView modelAndView, HttpServletRequest request)
			throws ParseException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		studentTransferService.saveTransferData(studentTransferDTO);
		StudentTransferDTO dto = studentTransferService
				.generateTC(studentTransferDTO.getStudentId());
		List<StudentTransferDTO> listdto = new ArrayList<StudentTransferDTO>();
		listdto.add(dto);
		String reportsDirPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/img/";
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(listdto);
		parameterMap.put("datasource", JRdataSource);
		File reportsDir = new File(reportsDirPath);

		parameterMap.put(JRParameter.REPORT_FILE_RESOLVER,
				new SimpleFileResolver(reportsDir));
		modelAndView = new ModelAndView("maharanatc", parameterMap);
		return modelAndView;
	}
	@RequestMapping(value = "new_issue-tcPDF", method = RequestMethod.GET)
	public ModelAndView getissueNewTC(
			@ModelAttribute("id") String id,
			ModelAndView modelAndView, HttpServletRequest request) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		NewStudentTransferDTO dto = newStudentTransferService
				.getneweGeneratedTC(id);
		List<NewStudentTransferDTO> dtoList = new ArrayList<NewStudentTransferDTO>();
		dtoList.add(dto);
		String reportsDirPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/img/studentImages";
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(dtoList);
		parameterMap.put("datasource", JRdataSource);
		File reportsDir = new File(reportsDirPath);

		parameterMap.put(JRParameter.REPORT_FILE_RESOLVER,
				new SimpleFileResolver(reportsDir));
		modelAndView = new ModelAndView("newStudentTc", parameterMap);
		return modelAndView;

	}

	@RequestMapping(value = "new_issue-tc", method = RequestMethod.GET)
	public ModelAndView issueNewTC(
			@ModelAttribute("newStudentTransferDTO") NewStudentTransferDTO newStudentTransferDTO,
			ModelAndView modelAndView, HttpServletRequest request) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		newStudentTransferService.saveNewStudentTransfer(newStudentTransferDTO);
		NewStudentTransferDTO dto = newStudentTransferService
				.getneweGeneratedTC(newStudentTransferDTO.getStudentId());
		List<NewStudentTransferDTO> dtoList = new ArrayList<NewStudentTransferDTO>();
		dtoList.add(dto);
		System.out.println(dto.getFatherName());
		String reportsDirPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/img/studentImages";
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(dtoList);
		parameterMap.put("datasource", JRdataSource);
		File reportsDir = new File(reportsDirPath);

		parameterMap.put(JRParameter.REPORT_FILE_RESOLVER,
				new SimpleFileResolver(reportsDir));
		modelAndView = new ModelAndView("newStudentTc", parameterMap);
		return modelAndView;

	}

	@RequestMapping(value = "getClassPdf", method = RequestMethod.GET)
	public String classpdf(@RequestParam int classId,
			@RequestParam int sessionId, Model model) {
		model.addAttribute("classWiseList", admissionService
				.getStudentsbySessionandClass(classId, sessionId));
		return "pdfclassreport";
	}

	@RequestMapping(value = "getClassExcel", method = RequestMethod.GET)
	public ModelAndView classexcel(@RequestParam int classId,
			@RequestParam int sessionId, ModelAndView modelAndView) {
		List<StudentDTO> list = admissionService.getStudentsbySessionandClass(
				classId, sessionId);
		return new ModelAndView("ClassWiseExcel", "classWiseList", list);

	}

	@RequestMapping(value = "getSectionPdf", method = RequestMethod.GET)
	public String sectionPdf(@RequestParam int classId,
			@RequestParam int sectionId, @RequestParam int sessionId,
			Model model) {
		model.addAttribute("sectionWiseList", admissionService
				.getStudentsbyClassandSection(classId, sectionId, sessionId));
		return "pdfsectionreport";
	}

	@RequestMapping(value = "getSectionExcel", method = RequestMethod.GET)
	public ModelAndView sectionExcel(@RequestParam int classId,
			@RequestParam int sectionId, @RequestParam int sessionId,
			ModelAndView modelAndView) {
		List<StudentDTO> list = admissionService.getStudentsbyClassandSection(
				classId, sectionId, sessionId);
		return new ModelAndView("SectionWiseExcel", "sectionWiseList", list);
	}

	@RequestMapping(value = "transfer-student", method = RequestMethod.GET)
	public void applicationTransfer(@RequestParam int id, Model model,
			HttpServletResponse response) throws IOException, ParseException {
		String admno = admissionService.transferStudent(id);
		response.sendRedirect("show-student-details?id=" + admno + "");
	}

	@RequestMapping(value = "student-performa", method = RequestMethod.GET)
	public String studentperforma(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "students");
		return "studentperforma";
	}

	@RequestMapping(value = "get-studentperforma-byId", method = RequestMethod.POST)
	public @ResponseBody String getPerforma(
			@RequestParam("studentId") String studentId,
			HttpServletRequest request) throws JsonProcessingException,
			ParseException {
		ObjectMapper mapper = new ObjectMapper();
		StudentDTO dto = admissionService.findStudentPerforma(studentId);
		String balance = admissionService.findStudentbalance(studentId);
		dto.setBalanceAmount(balance);
		return mapper.writeValueAsString(dto);

	}

	@RequestMapping(value = "PDFstudent-byId", method = RequestMethod.GET)
	public ModelAndView studentperformaPDF(@RequestParam String id,
			ModelAndView modelAndView, HttpServletRequest request) {
		StudentDTO dto = admissionService.findStudentPerforma(id);
		String balance = admissionService.findStudentbalance(id);
		List<StudentDTO> listdto = new ArrayList<StudentDTO>();
		dto.setBalanceAmount(balance);
		listdto.add(dto);
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(listdto);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String reportsDirPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/img/studentImages/";
		File reportsDir = new File(reportsDirPath);
		parameterMap.put(JRParameter.REPORT_FILE_RESOLVER,
				new SimpleFileResolver(reportsDir));
		parameterMap.put("datasource", JRdataSource);
		modelAndView = new ModelAndView("student-data", parameterMap);
		return modelAndView;

	}

}
