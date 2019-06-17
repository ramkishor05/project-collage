package org.brijframework.college.controller.admin.employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.brijframework.college.common.constant.SessionConstants;
import org.brijframework.college.model.City;
import org.brijframework.college.model.Country;
import org.brijframework.college.model.State;
import org.brijframework.college.model.User;
import org.brijframework.college.models.dto.EmployeeDocumentDTO;
import org.brijframework.college.models.dto.EmployeesDTO;
import org.brijframework.college.models.dto.StudentClassesDTO;
import org.brijframework.college.service.AssignClassService;
import org.brijframework.college.service.CountryService;
import org.brijframework.college.service.DocumentCategoryService;
import org.brijframework.college.service.EmployeeDocumentService;
import org.brijframework.college.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	CountryService countryService;
	@Autowired
	AssignClassService assignClassService;
	@Autowired
	EmployeeDocumentService employeeDocumentService;
	@Autowired
	DocumentCategoryService documentCategoryService;

	@RequestMapping(value = "/admin/employee",method = RequestMethod.GET)
	public String showSetting(Model model) {
		return "employee";
	}
	@RequestMapping(value =  "/branch/employee",method = RequestMethod.GET)
	public String showSettings(Model model) {
		return "employee";
	}

	@RequestMapping(value = "employee-registration", method = RequestMethod.GET)
	public String registration(
			@ModelAttribute("employeesDTO") EmployeesDTO employeesDTO,
			Model model, HttpServletRequest request) {
		model.addAttribute("countryList",
				countryService.findAllByIsDeletedTrue(Country.class));
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		model.addAttribute("documentList", documentCategoryService.getDocumentByType("Employee"));
		return "employee-registration";
	}

	@RequestMapping(value = "registration-submit", method = RequestMethod.POST)
	public void registrationSubmit(
			@ModelAttribute("employeesDTO") EmployeesDTO employeesDTO,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws MessagingException, Exception {
		if (employeesDTO.getImamgeName() == null) {
		} else if (employeesDTO.getImamgeName().getSize() > 0) {
			MultipartFile file = employeesDTO.getImamgeName();
			String type = file.getContentType().split("/")[0];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (type.equalsIgnoreCase("image")) {
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/style/employeeImages/" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		}
		int id = employeeService.saveEmployeeRegistration(employeesDTO);
		if(employeesDTO.getDocumentList()!=null)
		{
		for(EmployeeDocumentDTO documentDTO:employeesDTO.getDocumentList())
		{
			
		if (documentDTO.getDocumentFile().getSize() > 0) {
			MultipartFile files = documentDTO.getDocumentFile();
			
			String name=documentDTO.getDocumentName()+"("+id+")";
			File file = new File(request.getSession()
					.getServletContext().getRealPath("/")
					+ "/employeeDocuments/" + name+ files.getOriginalFilename());
					 
					FileUtils.writeByteArrayToFile(file, files.getBytes());
				/*	System.out.println("Go to the location:  " + file.toString()
					+ " on your computer and verify that the image has been stored.");*/
			
				documentDTO.setFileName(name+ files.getOriginalFilename());
				documentDTO.setEmployeeId(id);
		}
		}
		
		employeeDocumentService.saveDocuments(employeesDTO.getDocumentList());
		}
		response.sendRedirect("show-employee-details?id=" + id + "");

	}

	@RequestMapping(value = "employee-details", method = RequestMethod.GET)
	public String showEmployeedetails(
			@ModelAttribute("employeeDTO") EmployeesDTO employeeDTO,
			Model model, HttpServletRequest request) {
		model.addAttribute("employeeDTO", new EmployeesDTO());
		model.addAttribute("allemployees", employeeService.findAllEmployee());
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		return "employeedetails";
	}

	@RequestMapping(value = { "show-employee-details",
			"/employee/show-employee-details" }, method = RequestMethod.GET)
	public String showStudentDetails(
			@ModelAttribute("employeeDTO") EmployeesDTO employeeDTO,
			Model model, @RequestParam int id, HttpServletRequest request) {
		employeeDTO = employeeService.findEmployeeDetails(id);
		model.addAttribute("EmployeeDetailsBean", employeeDTO);
		StudentClassesDTO assign = assignClassService
				.getAssignClassAndSection(id);
		model.addAttribute("assigned", assign);
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		List<EmployeeDocumentDTO> documentList = employeeDocumentService
				.findDocumentsforEmployee(employeeDTO.getId());
		model.addAttribute("documentList", documentList);
		return "showemployeedetails";
	}

	@RequestMapping(value = "edit-employee-details", method = RequestMethod.GET)
	public String editStudentDetails(
			@ModelAttribute("employeeDTO") EmployeesDTO employeeDTO,
			Model model, @RequestParam int id, HttpServletRequest request) {
		model.addAttribute("countryList",
				countryService.findAllByIsDeletedTrue(Country.class));
		model.addAttribute("stateList",
				countryService.findAllByIsDeletedTrue(State.class));
		model.addAttribute("cityList",
				countryService.findAllByIsDeletedTrue(City.class));
		employeeDTO = employeeService.findEmployeeDetails(id);
		model.addAttribute("employeeDTO", employeeDTO);
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		return "editemployeedetails";
	}

	@RequestMapping(value = "edit-employee-details", method = RequestMethod.POST)
	public String updateEmployeeDetails(
			@ModelAttribute("employeeDTO") EmployeesDTO employeeDTO,
			HttpServletRequest request, Model model, @RequestParam int id)
			throws ParseException, IOException {
		if (employeeDTO.getImamgeName() == null) {
		}
		if (employeeDTO.getImamgeName().getSize() > 0) {
			MultipartFile file = employeeDTO.getImamgeName();
			String type = file.getContentType().split("/")[0];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (type.equalsIgnoreCase("image")) {
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/style/employeeImages/" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		}
		employeeService.updateEmployeesdata(employeeDTO);
		/*
		 * model.addAttribute("countryList",
		 * countryService.findAllByIsDeletedTrue(Country.class));
		 * 
		 * model.addAttribute("stateList",
		 * countryService.findAllByIsDeletedTrue(State.class));
		 * model.addAttribute("cityList",
		 * countryService.findAllByIsDeletedTrue(City.class)); employeeDTO =
		 * employeeService.findEmployeeDetails(id);
		 * model.addAttribute("employeeDTO", employeeDTO);
		 * model.addAttribute("msg", "Update Employee Successfully");
		 * HttpSession session = request.getSession();
		 * session.setAttribute("active", "staff");
		 */

		return "redirect:show-employee-details?id=" + employeeDTO.getId();
	}

	@RequestMapping(value = "/employee/employeemy-profile", method = RequestMethod.GET)
	public String showStudentDetails(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER_INFO);

		session.setAttribute("active", "staff");
		EmployeesDTO employeeDTO = employeeService.finddetailsbyuserId(user
				.getId());
		model.addAttribute("EmployeeDetailsBean", employeeDTO);
		return "showemployeedetails";
	}
}
