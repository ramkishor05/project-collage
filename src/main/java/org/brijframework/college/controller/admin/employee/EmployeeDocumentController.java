package org.brijframework.college.controller.admin.employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.brijframework.college.models.dto.EmployeeDocumentDTO;
import org.brijframework.college.service.EmployeeDocumentService;
import org.brijframework.college.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class EmployeeDocumentController {
	
	 private static final int BUFFER_SIZE = 8192;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeDocumentService documentService;

	@RequestMapping(value = "employee-documents", method = RequestMethod.GET)
	public String selluniform(HttpServletRequest request, Model model) {
		model.addAttribute("TeacherList",employeeService.findAllEmployee() );
		model.addAttribute("employeeDocumentDTO", new EmployeeDocumentDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "employee");
		return "employeeDocuments";
	}
	@RequestMapping(value = "getDocumentforEmployees", method = RequestMethod.POST)
	public @ResponseBody String getDueDateList(@RequestParam("id") int id,
			HttpServletRequest request) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("employeeDTO",employeeService.findEmployeeDetails(id));
		map.put("documentList",documentService.findDocumentsforEmployee(id));
		return new ObjectMapper().writeValueAsString(map);

	}
	
	@RequestMapping(value = "upload-employeeDocuments", method = RequestMethod.POST)
	public String studentAdmission(
			@ModelAttribute("employeeDocumentDTO") EmployeeDocumentDTO employeeDocumentDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response,RedirectAttributes attributes) throws IOException, ParseException {
		for(EmployeeDocumentDTO documentDTO:employeeDocumentDTO.getDocumentList())
		{
			
		if (documentDTO.getDocumentFile().getSize() > 0) {
			MultipartFile files = documentDTO.getDocumentFile();
			String name=documentDTO.getDocumentName()+"("+employeeDocumentDTO.getEmployeeId()+")";
			/*try{
			InputStream inputStream = null;
			OutputStream outputStream = null;
			String name=documentDTO.getDocumentName()+"("+studentDocumentDTO.getStudentId()+")";
				inputStream = file.getInputStream();
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/studentDocuments/" + name+ file.getOriginalFilename());
				

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}*/
			File file = new File(request.getSession()
					.getServletContext().getRealPath("/")
					+ "/employeeDocuments/" + name+ files.getOriginalFilename());
					 
					FileUtils.writeByteArrayToFile(file, files.getBytes());
				/*	System.out.println("Go to the location:  " + file.toString()
					+ " on your computer and verify that the image has been stored.");*/
			
				documentDTO.setFileName(name+ files.getOriginalFilename());
		}
		}
		
		documentService.saveDocuments(employeeDocumentDTO);
		attributes.addFlashAttribute("msg", "Uploaded Successfully");
		return "redirect:employee-documents";
	}
	@RequestMapping(value = "empdocument-download", method = RequestMethod.GET)
	public  void getDownload(
			@RequestParam String id,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException {
		 ServletContext context = request.getSession().getServletContext();
	        String fullPath = request.getSession()
					.getServletContext().getRealPath("/")
					+ "/employeeDocuments/"  + id;      
	        File downloadFile = new File(fullPath);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	        String mimeType = context.getMimeType(fullPath);
	        if (mimeType == null) {
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	       
	 
	        // set content attributes for the response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	 
	        // get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	 
	        byte[] buffer = new byte[BUFFER_SIZE];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
	 
	    }
	}


