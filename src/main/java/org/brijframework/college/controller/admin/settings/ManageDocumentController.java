package org.brijframework.college.controller.admin.settings;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.models.dto.DocumentCategoryDTO;
import org.brijframework.college.service.DocumentCategoryService;
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
@RequestMapping("/admin/**")
public class ManageDocumentController {
	@Autowired
	DocumentCategoryService documentService;

	@RequestMapping(value = "manage-document.html", method = RequestMethod.GET)
	public String getlastdate(ModelMap model, HttpServletRequest request) {
		model.addAttribute("documentCategoryDTO", new DocumentCategoryDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		return "managedocuments";
	}

	@RequestMapping(value = "get-documentbyType", method = RequestMethod.POST)
	public @ResponseBody String getDueDateList(
			@RequestParam("type") String type, HttpServletRequest request)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(documentService
				.getDocumentByType(type));
	}
	@RequestMapping(value = "add-document-category", method = RequestMethod.POST)
	public String createDueDate(@ModelAttribute("documentCategoryDTO") DocumentCategoryDTO documentCategoryDTO,
			ModelMap model, HttpServletRequest request) throws ParseException,
			IOException {
		documentService.addDocumentCategory(documentCategoryDTO);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		model.addAttribute("msg", "Added Successfully");
		model.addAttribute("documentCategoryDTO", new DocumentCategoryDTO());
		return "managedocuments";
	}
	@RequestMapping(value = "add-document-category", method = RequestMethod.GET)
	public String document(
			ModelMap model, HttpServletRequest request) throws ParseException,
			IOException {
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		model.addAttribute("documentCategoryDTO", new DocumentCategoryDTO());
		return "managedocuments";
	}
	@RequestMapping(value = "get-documentbyId", method = RequestMethod.POST)
	public @ResponseBody String documentedit(
			@RequestParam("id") int id, HttpServletRequest request)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(documentService.read(id));
	}
	@RequestMapping(value = "edit-document-category", method = RequestMethod.POST)
	public String editDocument(@ModelAttribute("documentCategoryDTO") DocumentCategoryDTO documentCategoryDTO,
			ModelMap model, HttpServletRequest request) throws ParseException,
			IOException {
		documentService.editDocumentCategory(documentCategoryDTO);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		model.addAttribute("msg", "Updated Successfully");
		model.addAttribute("documentCategoryDTO", new DocumentCategoryDTO());
		return "managedocuments";
	}
	@RequestMapping(value = "edit-document-category", method = RequestMethod.GET)
	public String documentsedit(
			ModelMap model, HttpServletRequest request) throws ParseException,
			IOException {
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		model.addAttribute("documentCategoryDTO", new DocumentCategoryDTO());
		return "managedocuments";
	}
	@RequestMapping(value = "delete-document-category", method = RequestMethod.GET)
	public String deleteDocument(@RequestParam int id,
			ModelMap model, HttpServletRequest request) throws ParseException,
			IOException {
		documentService.deleteCategory(id);
		HttpSession session = request.getSession();
		session.setAttribute("active", "settings");
		model.addAttribute("msg", "Deleted Successfully");
		model.addAttribute("documentCategoryDTO", new DocumentCategoryDTO());
		return "managedocuments";
	}
}
