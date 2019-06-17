package org.brijframework.college.controller.admin.student;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.service.StudentsAdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/**")
public class GenerateTCController {
	@Autowired
	private StudentsAdmissionService studentsAdmissionService;

	@RequestMapping(value = "generatetc.html", method = RequestMethod.GET)
	public String viewTcPage(@RequestParam(required = false) int studentId,
			ModelMap model,HttpServletResponse response) throws IOException {
		StudentDTO studentDTO = studentsAdmissionService
				.getStudentId(studentId);
		studentDTO.setCurrentDate(new SimpleDateFormat("dd-MM-YYYY")
				.format(new Date()));
		model.addAttribute("StudentDTO", studentDTO);
		
	      return "generatetc";
	}
	@RequestMapping(value = "getpdftc", method = RequestMethod.GET)
	public String pdftc(@RequestParam(required = false) int studentId,
			ModelMap model,HttpServletResponse response) throws IOException {
		StudentDTO studentDTO = studentsAdmissionService
				.getStudentId(studentId);
		studentDTO.setCurrentDate(new SimpleDateFormat("dd-MM-YYYY")
				.format(new Date()));
		model.addAttribute("StudentDTO", studentDTO);
		
	      return "pdfstudenttc";
	}
}
