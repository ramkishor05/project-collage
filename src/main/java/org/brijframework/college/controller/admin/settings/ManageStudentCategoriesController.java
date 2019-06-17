package org.brijframework.college.controller.admin.settings;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.StudentCategory;
import org.brijframework.college.service.StudentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/**")
public class ManageStudentCategoriesController {

	@Autowired
	StudentCategoryService studentCategoryService;

	@RequestMapping(value = "/manage-student-categories", method = RequestMethod.GET)
	public String manageStudentCategories(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "managestudentcategories";
	}

	@RequestMapping(value = "/create-student-categories", method = RequestMethod.GET)
	public String showcreateStudentCategories(Model model,HttpServletRequest request) {
		model.addAttribute("StudentCategory", new StudentCategory());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "createstudentcategories";
	}

	@RequestMapping(value = "/create-student-categories", method = RequestMethod.POST)
	public String createStudentCategories(
			@ModelAttribute("StudentCategory") StudentCategory studentCategory,
			Model model) {
		studentCategoryService.create(studentCategory);
		model.addAttribute("StudentCategory", new StudentCategory());
		model.addAttribute("msg", "Successfully Inserted");
		return "createstudentcategories";
	}

	@RequestMapping(value = "/update-student-categories", method = RequestMethod.GET)
	public String updateStudentCategories(Model model,HttpServletRequest request) {
		List<StudentCategory> categoryList = studentCategoryService
				.findAllByIsDeletedTrue(StudentCategory.class);
		model.addAttribute("list", categoryList);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "updatestudentcategories";
	}

	@RequestMapping(value = "/edit-student-categories")
	public String newStudentCategories(Model model, @RequestParam Integer id) {

		StudentCategory student = studentCategoryService.getCategoryById(id);

		model.addAttribute("StudentCategory", student);
		return "newstudentcategories";
	}

	@RequestMapping(value = "/update-student-categories", method = RequestMethod.POST)
	public String newStudentCategories(
			@ModelAttribute("StudentCategory") StudentCategory studentCategory,
			Model model) {
		studentCategoryService.update(studentCategory);
		List<StudentCategory> categoryList = studentCategoryService
				.findAllByIsDeletedTrue(StudentCategory.class);
		model.addAttribute("list", categoryList);
		model.addAttribute("msg", "Successfully Updated");
		return "updatestudentcategories";
	}

	@RequestMapping(value = "/delete-student-categories", method = RequestMethod.GET)
	public String deleteStudentCategories(Model model,HttpServletRequest request) {
		List<StudentCategory> categoryList = studentCategoryService
				.findAllByIsDeletedTrue(StudentCategory.class);
		model.addAttribute("list", categoryList);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "deletestudentcategories";
	}

	@RequestMapping(value = "/delete-categories", method = RequestMethod.GET)
	public String deletedStudentCategories(Model model, @RequestParam Integer id,HttpServletRequest request) {
		studentCategoryService.setActiveById(id);
		List<StudentCategory> categoryList = studentCategoryService
				.findAllByIsDeletedTrue(StudentCategory.class);
		model.addAttribute("list", categoryList);
		model.addAttribute("msg", "Successfully deleted");
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "deletestudentcategories";
	}
}
