package org.brijframework.college.controller.admin.uniform;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.models.dto.AllotDressDTO;
import org.brijframework.college.models.dto.DressDTO;
import org.brijframework.college.models.dto.DressPaymentDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.service.AllotDressService;
import org.brijframework.college.service.DressPaymentService;
import org.brijframework.college.service.DressService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class UniformController {
	@Autowired
	DressService dressService;
	@Autowired
	StudentClassesService classService;
	@Autowired
	StudentsAdmissionService admissionService;
	@Autowired
	SessionService sessionService;
	@Autowired
	AllotDressService allotdressService;
	@Autowired
	DressPaymentService dressPaymentService;

	@RequestMapping(value = "dress-menu", method = RequestMethod.GET)
	public String dressMenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "dressmenu";
	}

	@RequestMapping(value = "change-prices", method = RequestMethod.GET)
	public String managedress(HttpServletRequest request, Model model) {
		model.addAttribute("Dresses", dressService.findfirstdressName());
		model.addAttribute("dresslist", dressService.getDressNames());
		model.addAttribute("currentdress", dressService.getCurrentdressname());
		model.addAttribute("Dress", new DressDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "managedress";
	}

	@RequestMapping(value = "getDress", method = RequestMethod.POST)
	public @ResponseBody
	String getState(@RequestParam int dressId, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		DressDTO dressDTO = dressService.getDressById(dressId);
		return new ObjectMapper().writeValueAsString(dressDTO);
	}

	@RequestMapping(value = "/getDressDetails/{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getdress(@PathVariable("id") int dressId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {

		List<DressDTO> list = dressService.getDressDetails(dressId);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/updateprices/{id}/{id2}", method = RequestMethod.POST)
	public @ResponseBody
	String getupdatedress(@PathVariable("id") int changeId,
			@PathVariable("id2") int newprice, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		dressService.updatePrices(changeId, newprice);
		List<DressDTO> list = dressService.getDressDetails(changeId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}

	/*
	 * @RequestMapping(value = "allot-dress", method = RequestMethod.GET) public
	 * String allotdress(HttpServletRequest request,Model model) {
	 * model.addAttribute("dresses", dressService.getDressNameandCategory());
	 * model.addAttribute("dresslists", dressService.getDressName());
	 * model.addAttribute("classList", classService.getAllClass()); HttpSession
	 * session = request.getSession(); session.setAttribute("active", "dress");
	 * return "allotdress"; }
	 */
	@RequestMapping(value = "/getdressCategory/{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getCategory(@PathVariable("id") int dressId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<DressDTO> list = dressService.getDressCategory(dressId);
		return new ObjectMapper().writeValueAsString(list);
	}

	@RequestMapping(value = "/getdressSize/{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getSize(@PathVariable("id") int dressId, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		List<DressDTO> list = dressService.getDressSize(dressId);
		return new ObjectMapper().writeValueAsString(list);
	}

	@RequestMapping(value = "/getdressPrice/{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getPrice(@PathVariable("id") int dressId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		DressDTO dressDTO = dressService.getPrice(dressId);
		return new ObjectMapper().writeValueAsString(dressDTO);
	}

	@RequestMapping(value = "/getNamesList/{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getNamesList(@PathVariable("id") int classId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<StudentDTO> list = admissionService.getStudentsbySessionClassId(
				classId, sessionService.findCurrent().getSessionId());
		return new ObjectMapper().writeValueAsString(list);
	}

	@RequestMapping(value = "/getstudentsdata/{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getStudentsdata(@PathVariable("id") String admissionNo,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		StudentDTO list = admissionService.findStudentDetails(admissionNo);
		return new ObjectMapper().writeValueAsString(list);
	}

	@RequestMapping(value = "/savealloteddress/{id}/{id2}", method = RequestMethod.POST)
	public @ResponseBody
	String savedress(@PathVariable("id") int dressId,
			@PathVariable("id2") int quantity, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		allotdressService.savedressdata(dressId, quantity);
		List<AllotDressDTO> list = allotdressService.getAllDetails();
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/removealloted/{id}", method = RequestMethod.POST)
	public @ResponseBody
	String removealloted(@PathVariable("id") int allotedId,
			HttpServletRequest request, Model model,
			HttpServletResponse response) throws IOException {
		allotdressService.removealloted(allotedId);
		List<AllotDressDTO> list = allotdressService.getAllDetails();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);

	}

	@RequestMapping(value = "/submitdressdata", method = RequestMethod.GET)
	public String showStudentDetails(Model model, @RequestParam int id,
			@RequestParam String id2, @RequestParam String id3,
			@RequestParam int total, HttpServletRequest request) {
		List<DressPaymentDTO> list = dressPaymentService.savepaymentdetails(id,
				id2, id3, total);
		model.addAttribute("list", list);
		return "pdfdressreceipt";
	}

	@RequestMapping(value = "view-sold-dress", method = RequestMethod.GET)
	public String viewsold(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "viewsolddress";
	}

	@RequestMapping(value = "/viewsolddressdetsils/{id}/{id2}", method = RequestMethod.POST)
	public @ResponseBody
	String viewsold(@PathVariable("id") String from,
			@PathVariable("id2") String to, HttpServletRequest request,
			Model model, HttpServletResponse response) throws IOException,
			ParseException {
		List<DressPaymentDTO> list = dressPaymentService.getSoldDetails(from,
				to);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);

	}

	@RequestMapping(value = "getDressReceipt", method = RequestMethod.GET)
	public String showStudentDetails(Model model, @RequestParam int id,
			HttpServletRequest request) {
		List<DressPaymentDTO> list = dressPaymentService.getDressReceipt(id);
		model.addAttribute("list", list);
		return "pdfdressreceipt";
	}

	@RequestMapping(value = "/getStudentsbyclasssection/{id1}/{id2}", method = RequestMethod.POST)
	public @ResponseBody
	String getStudentsbyclass(@PathVariable("id1") int classId,
			@PathVariable("id2") int sectionId, HttpServletRequest request,
			Model model) throws JsonProcessingException {

		List<StudentDTO> list = admissionService
				.getStudentsbyClassandSection(classId, sectionId,
						sessionService.findCurrent().getSessionId());

		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "allot-dress", method = RequestMethod.GET)
	public String allotdress(HttpServletRequest request, Model model,@ModelAttribute ("DressDTO") DressDTO dressDTO) {
		model.addAttribute("dresses", dressService.getDressNameandCategory());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("DressDTO", new DressDTO());
		model.addAttribute("dresslists", dressService.getDressName());
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "allotdress";
	}
	@RequestMapping(value = "/getIdforcategory/{category}/{name}", method = RequestMethod.POST)
	public @ResponseBody
	String getIdforcategory(@PathVariable("category") String category,@PathVariable("name") String name, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		int dressId=dressService.getDressIdforcategory(name,category);
		return new ObjectMapper().writeValueAsString(dressId);
	}
	@RequestMapping(value = "/getdressPriceforsize/{id}/{size}", method = RequestMethod.POST)
	public @ResponseBody
	String getPriceforsize(@PathVariable("id") int dressId,@PathVariable("size") String size,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		DressDTO dressDTO = dressService.getPriceforsize(dressId,size);
		return new ObjectMapper().writeValueAsString(dressDTO);
	}
	@RequestMapping(value = "save-dress", method = RequestMethod.POST)
	public String savedress(HttpServletRequest request, Model model,@ModelAttribute ("DressDTO") DressDTO dressDTO) {
		model.addAttribute("dresses", dressService.getDressNameandCategory());
		model.addAttribute("classList", classService.getAllClass());
		allotdressService.savedress(dressDTO);
		model.addAttribute("DressDTO", new DressDTO());
		String admissionNo=allotdressService.getStudentData();
		model.addAttribute("studentdata",  admissionService.findStudentDetails(admissionNo));
		List<AllotDressDTO> list = allotdressService.getAllDetails();
		model.addAttribute("details", list);
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "allotdress";
	}
	@RequestMapping(value = "save-dress", method = RequestMethod.GET)
	public String newallotdress(HttpServletRequest request, Model model,@ModelAttribute ("DressDTO") DressDTO dressDTO) {
		model.addAttribute("dresses", dressService.getDressNameandCategory());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("DressDTO", new DressDTO());
		model.addAttribute("dresslists", dressService.getDressName());
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "allotdress";
	}
	@RequestMapping(value = "/getdressnames", method = RequestMethod.POST)
	public @ResponseBody
	String getdressnames(HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<DressDTO> list = dressService.getDressName();
		return new ObjectMapper().writeValueAsString(list);
	}
}
