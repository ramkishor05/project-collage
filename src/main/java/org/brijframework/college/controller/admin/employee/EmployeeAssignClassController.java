package org.brijframework.college.controller.admin.employee;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.AssignClass;
import org.brijframework.college.model.Employees;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.models.dto.AssignClassDTO;
import org.brijframework.college.service.AssignClassService;
import org.brijframework.college.service.EmployeeService;
import org.brijframework.college.service.StudentClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class EmployeeAssignClassController {
	@Autowired
	StudentClassesService classesService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	AssignClassService assignClassService;

	@RequestMapping(value = "classes-assign", method = RequestMethod.GET)
	public String showSetting(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		model.addAttribute("ClassesList",
				classesService.findAll(StudentClasses.class));
		model.addAttribute("TeacherList",
				employeeService.findAllByIsDeletedTrue(Employees.class));
		model.addAttribute("AssignClassDTO", new AssignClassDTO());
		model.addAttribute("classesAssignedList",
				assignClassService.findAllByIsDeletedTrue(AssignClass.class));
		return "assignclasses";
	}

	@RequestMapping(value = "getclassassigned", method = RequestMethod.GET)
	public @ResponseBody String showClassAssign(@RequestParam int employeeId)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(assignClassService
				.getAssignTeacherByClassId(employeeId));
	}

	@RequestMapping(value = "checkclassassign", method = RequestMethod.POST)
	public @ResponseBody String checkClassAssign(
			@RequestParam("classId") int classId,
			@RequestParam("sectionId") int sectionId)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(assignClassService
				.checkAssignClassesForTecher(sectionId, classId));
	}

	@RequestMapping(value = "assign-classes", method = RequestMethod.POST)
	public String assignclass(@ModelAttribute AssignClassDTO assignClassDTO,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "staff");
		assignClassService.assignClassToTeacher(assignClassDTO);
		model.addAttribute("ClassesList",
				classesService.findAll(StudentClasses.class));
		model.addAttribute("TeacherList",
				employeeService.findAll(Employees.class));
		model.addAttribute("AssignClassDTO", new AssignClassDTO());
		 model.addAttribute("msg", "Class Is Successfully Assign To Teacher"); 
		return "assignclasses";
	}

	@RequestMapping(value = "assign-classes", method = RequestMethod.GET)
	public void assignclass(HttpServletResponse response) throws IOException {
		response.sendRedirect("classes-assign");
	}

	@RequestMapping(value = "deleteclassassigned", method = RequestMethod.POST)
	public @ResponseBody String deleteClassAssigned(
			@RequestParam("classAssignId") int classAssignId)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(assignClassService
				.deleteAssignedClassById(classAssignId));
	}
}
