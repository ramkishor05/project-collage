package org.brijframework.college.controller.admin.settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.brijframework.college.models.dto.SchoolRegistrationDTO;
import org.brijframework.college.service.SchoolRegistraionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/**")
public class SchoolRegistrationController {
	@Autowired
	SchoolRegistraionService schoolService;
	@RequestMapping(value="school-registration.html",method=RequestMethod.GET)
	public String schoolRegistrationPage(Model model,HttpServletRequest request) throws IOException
	{
		SchoolRegistrationDTO dto= schoolService.getSchoolList();
		System.out.println(dto);
if(dto!=null)
		{
		String relativeWebPath = "/img";
		String absoluteDiskPath = request.getSession().getServletContext().getRealPath(relativeWebPath);
        File file = new File(absoluteDiskPath,dto.getSchoolLogoName());
        DiskFileItem diskFileItem = new DiskFileItem("file", "image/png", true, file.getName(), 100000000, file.getParentFile()); 
        diskFileItem.getOutputStream();
        MultipartFile multipartFile = new CommonsMultipartFile(diskFileItem); 
        System.out.println(multipartFile);
		dto.setSchoolLogo(multipartFile);
		model.addAttribute("schoolRegistration", dto);
		}
else
{
	model.addAttribute("schoolRegistration", new SchoolRegistrationDTO());
	}
		
		return "school-registration";
	}

	@RequestMapping(value="register-school",method=RequestMethod.POST)
	public String registerSchool(@ModelAttribute("schoolRegistration") SchoolRegistrationDTO schoolRegistrationDTO,RedirectAttributes redirectAttribute,HttpServletRequest request ) throws IOException
	{
		String newFileName="";
		if (schoolRegistrationDTO.getSchoolLogo().getSize() > 0) {
			MultipartFile file = schoolRegistrationDTO.getSchoolLogo();
			 newFileName =schoolRegistrationDTO.getSchoolName()+"."+ file.getContentType().split("/")[1];
			 System.out.println(newFileName);
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (newFileName.equalsIgnoreCase("image")) {
				inputStream = (InputStream) file.getInputStream();
				URL url = this.getClass().getClassLoader().getResource("/img/"
						+ newFileName);
				Path path = Paths.get(url.getPath());
				Files.deleteIfExists(path);
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/img/"
						+ newFileName);
				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}

		}
		schoolRegistrationDTO.setSchoolLogoName(newFileName);
		String message=schoolService.registerSchool(schoolRegistrationDTO);
		

		redirectAttribute.addFlashAttribute("message", message);
		return "redirect:school-registration.html";
	}
	@RequestMapping(value="update-registered-school",method=RequestMethod.POST)
	public String updateRegisteredSchool(@ModelAttribute("schoolRegistration") SchoolRegistrationDTO schoolRegistrationDTO,RedirectAttributes redirectAttribute,HttpServletRequest request ) throws IOException
	{
		String newfileName="";
		if (schoolRegistrationDTO.getSchoolLogo().getSize() > 0) {
			MultipartFile file = schoolRegistrationDTO.getSchoolLogo();
			newfileName =schoolRegistrationDTO.getSchoolName()+"."+ file.getContentType().split("/")[1];
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (newfileName.equalsIgnoreCase("image")) {
				inputStream = (InputStream) file.getInputStream();
				URL url = this.getClass().getClassLoader().getResource("/img/"
						+ newfileName);
				Path path = Paths.get(url.getPath());
				Files.deleteIfExists(path);
				outputStream = new FileOutputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ "/img/"
						+ newfileName);

				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {

					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}

		}
		schoolRegistrationDTO.setSchoolLogoName(newfileName);
		String message=schoolService.updateRegisteredSchool(schoolRegistrationDTO);
		
		redirectAttribute.addFlashAttribute("message", message);
		return "redirect:school-registration.html";
	}

@RequestMapping(value="delete-registered-school",method=RequestMethod.GET)
public String deleteRegisteredSchool(@RequestParam int id,RedirectAttributes redirectAttribute,HttpServletRequest request ) throws IOException
{
	String message=schoolService.deleteRegisteredSchool(id);
	
	redirectAttribute.addFlashAttribute("message", message);
	return "redirect:school-registration.html";
}
}
