package org.brijframework.college.controller.admin.uniform;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.AllotDressDTO;
import org.brijframework.college.models.dto.DressPaymentDTO;
import org.brijframework.college.models.dto.FeesCategoriesDTO;
import org.brijframework.college.models.dto.UniformPurchaseDTO;
import org.brijframework.college.models.dto.UniformSupplierDTO;
import org.brijframework.college.service.AllotDressService;
import org.brijframework.college.service.DressPaymentService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.brijframework.college.service.UniformPurchaseService;
import org.brijframework.college.service.UniformSupplierService;
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
public class SchoolUniformController {
	@Autowired
	UniformPurchaseService uniformPurhaseService;
	@Autowired
	StudentClassesService classService;
	@Autowired
	AllotDressService allotdressService;
	@Autowired
	StudentsAdmissionService admissionService;
	@Autowired
	DressPaymentService dressPaymentService;
	@Autowired
	SessionService sessionService;
	@Autowired
	UniformSupplierService uniformSupplierService;
	@Autowired
	StudentFeeSubmissionDetailsService feeService;
	

	@RequestMapping(value = "uniform-menu", method = RequestMethod.GET)
	public String dressMenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "uniformmenu";
	}

	@RequestMapping(value = "/purchase-uniform", method = RequestMethod.GET)
	public String purchaseuniform(Model model, HttpServletRequest request)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = uniformPurhaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("uniformNames",
				uniformPurhaseService.getUniformNames());
		model.addAttribute("purchaseList",
				uniformPurhaseService.getPurchaseDetails());
		model.addAttribute("shopNames", uniformPurhaseService.findShopNames());
		model.addAttribute("UniformPurchaseDTO", new UniformPurchaseDTO());
		return "purchaseuniform";
	}

	@RequestMapping(value = "getUniformWiseCategory", method = RequestMethod.POST)
	public @ResponseBody String getCategory(@RequestParam String name)
			throws JsonProcessingException {

		List<String> list = uniformPurhaseService.getCategoryByName(name);
		return new ObjectMapper().writeValueAsString(list);
	}

	@RequestMapping(value = "getCategoryWiseSize", method = RequestMethod.POST)
	public @ResponseBody String getSize(@RequestParam String category,
			@RequestParam String name, HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<String> list = uniformPurhaseService.getSizebyCategory(category,
				name);
		return new ObjectMapper().writeValueAsString(list);
	}

	@RequestMapping(value = "/save-UniformPurchases", method = RequestMethod.POST)
	public String savepurchaseuniform(
			@ModelAttribute UniformPurchaseDTO uniformPurchaseDTO, Model model,
			HttpServletRequest request) throws ParseException {
		uniformPurhaseService.savepurchasedetals(uniformPurchaseDTO);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = uniformPurhaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("uniformNames",
				uniformPurhaseService.getUniformNames());
		model.addAttribute("purchaseList",
				uniformPurhaseService.getPurchaseDetails());
		model.addAttribute("shopNames", uniformPurhaseService.findShopNames());
		model.addAttribute("UniformPurchaseDTO", new UniformPurchaseDTO());
		model.addAttribute("msg", "Successfully Added");
		return "purchaseuniform";
	}

	@RequestMapping(value = "/save-UniformPurchases", method = RequestMethod.GET)
	public String showuniform(Model model, HttpServletRequest request)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = uniformPurhaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("uniformNames",
				uniformPurhaseService.getUniformNames());
		model.addAttribute("purchaseList",
				uniformPurhaseService.getPurchaseDetails());
		model.addAttribute("shopNames", uniformPurhaseService.findShopNames());
		model.addAttribute("UniformPurchaseDTO", new UniformPurchaseDTO());
		return "purchaseuniform";
	}

	@RequestMapping(value = "getUniformPurchasebyId", method = RequestMethod.POST)
	public @ResponseBody String uniformPurchase(
			@RequestParam int productPurchaseId, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		UniformPurchaseDTO dto = uniformPurhaseService
				.getPurchaseDetailsbyId(productPurchaseId);
		return new ObjectMapper().writeValueAsString(dto);
	}

	@RequestMapping(value = "/edit-uniform-purchase.html", method = RequestMethod.POST)
	public String edituniform(
			@ModelAttribute UniformPurchaseDTO uniformPurchaseDTO, Model model,
			HttpServletRequest request) throws ParseException {
		String result = uniformPurhaseService
				.updatepurchases(uniformPurchaseDTO);
		model.addAttribute("msg", result);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = uniformPurhaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("uniformNames",
				uniformPurhaseService.getUniformNames());
		model.addAttribute("purchaseList",
				uniformPurhaseService.getPurchaseDetails());
		model.addAttribute("shopNames", uniformPurhaseService.findShopNames());
		model.addAttribute("UniformPurchaseDTO", new UniformPurchaseDTO());
		return "purchaseuniform";
	}

	@RequestMapping(value = "/edit-uniform-purchase.html", method = RequestMethod.GET)
	public String editshowuniform(Model model, HttpServletRequest request)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = uniformPurhaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("uniformNames",
				uniformPurhaseService.getUniformNames());
		model.addAttribute("purchaseList",
				uniformPurhaseService.getPurchaseDetails());
		model.addAttribute("shopNames", uniformPurhaseService.findShopNames());
		model.addAttribute("UniformPurchaseDTO", new UniformPurchaseDTO());
		return "purchaseuniform";
	}

	@RequestMapping(value = "/deletepurchases", method = RequestMethod.GET)
	public String deleteuniform(Model model, HttpServletRequest request,
			@RequestParam int id) throws ParseException {
		String result = uniformPurhaseService.cancelPurchase(id);
		model.addAttribute("msg", result);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = uniformPurhaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("uniformNames",
				uniformPurhaseService.getUniformNames());
		model.addAttribute("purchaseList",
				uniformPurhaseService.getPurchaseDetails());
		model.addAttribute("shopNames", uniformPurhaseService.findShopNames());
		model.addAttribute("UniformPurchaseDTO", new UniformPurchaseDTO());
		return "purchaseuniform";
	}

	@RequestMapping(value = "sell-uniform", method = RequestMethod.GET)
	public String selluniform(
			HttpServletRequest request,
			Model model,
			@ModelAttribute("UniformPurchaseDTO") UniformPurchaseDTO uniformPurchaseDTO) {
		model.addAttribute("uniform",
				uniformPurhaseService.getUniformNamesandCategory());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("UniformPurchaseDTO", new UniformPurchaseDTO());
		allotdressService.emptytable();
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "selluniform";
	}

	@RequestMapping(value = "getPriceandIdforUniform", method = RequestMethod.POST)
	public @ResponseBody String getPrice(@RequestParam String category,
			@RequestParam String name, @RequestParam String size,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		UniformPurchaseDTO dto = uniformPurhaseService.getUniformPriceandId(
				name, category, size);
		return new ObjectMapper().writeValueAsString(dto);
	}

	@RequestMapping(value = "save-uniformSold", method = RequestMethod.POST)
	public String savesolduniform(
			HttpServletRequest request,
			Model model,
			@ModelAttribute("UniformPurchaseDTO") UniformPurchaseDTO uniformPurchaseDTO) {
		allotdressService.saveUniform(uniformPurchaseDTO);
		model.addAttribute("uniform",
				uniformPurhaseService.getUniformNamesandCategory());
		model.addAttribute("classList", classService.getAllClass());

		model.addAttribute("UniformPurchaseDTO", new UniformPurchaseDTO());
		String admissionNo = allotdressService.getStudentData();
		model.addAttribute("studentdata",
				admissionService.findStudentDetails(admissionNo));
		List<AllotDressDTO> list = allotdressService.getAllSoldUniformDetails();
		model.addAttribute("details", list);
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "selluniform";
	}

	@RequestMapping(value = "save-uniformSold", method = RequestMethod.GET)
	public String newallotdress(HttpServletRequest request, Model model) {
		model.addAttribute("uniform",
				uniformPurhaseService.getUniformNamesandCategory());
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("UniformPurchaseDTO", new UniformPurchaseDTO());
		model.addAttribute("DressPaymentDTO", new DressPaymentDTO());
		allotdressService.emptytable();
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "selluniform";
	}

	@RequestMapping(value = "getUniformNames", method = RequestMethod.POST)
	public @ResponseBody String getNames(HttpServletRequest request, Model model)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(uniformPurhaseService
				.getUniformNames());
	}

	@RequestMapping(value = "checknewstock", method = RequestMethod.POST)
	public @ResponseBody String checknewstock(@RequestParam String category,
			@RequestParam String name, @RequestParam String size,
			@RequestParam int id, HttpServletRequest request, Model model)
			throws JsonProcessingException {
		UniformPurchaseDTO dto = uniformPurhaseService.getUniformNewPriceandId(
				name, category, size, id);
		return new ObjectMapper().writeValueAsString(dto);
	}

	@RequestMapping(value = "/cancelalloted/{id}", method = RequestMethod.POST)
	public @ResponseBody String removealloted(
			@PathVariable("id") int allotedId, HttpServletRequest request,
			Model model, HttpServletResponse response) throws IOException {
		allotdressService.removealloted(allotedId);
		List<AllotDressDTO> list = allotdressService.getAllSoldUniformDetails();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);

	}

	@RequestMapping(value = "/submitsolduniform", method = RequestMethod.GET)
	public String savesold(Model model, @RequestParam int id,
			@RequestParam String id2, @RequestParam String id3,
			@RequestParam int total, @RequestParam int net,
			HttpServletRequest request) {
		List<DressPaymentDTO> list = dressPaymentService
				.getUniformpaymentdetails(id, id2, id3, total, net);
		model.addAttribute("list", list);
		return "pdfdressreceipt";
	}

	@RequestMapping(value = "view-uniform-stock", method = RequestMethod.GET)
	public String uniformstock(HttpServletRequest request, Model model)
			throws ParseException {
		List<UniformPurchaseDTO> list = uniformPurhaseService
				.getUniformStockDetails();
		model.addAttribute("stock", list);
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "viewuniformstock";
	}

	@RequestMapping(value = "getStockbyName", method = RequestMethod.POST)
	public @ResponseBody String stockbyname(@RequestParam String name,
			HttpServletRequest request, Model model,
			HttpServletResponse response) throws IOException, ParseException {
		List<UniformPurchaseDTO> list = uniformPurhaseService
				.getUniformStockDetailsbyname(name);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);

	}

	@RequestMapping(value = "getStockbySize", method = RequestMethod.POST)
	public @ResponseBody String stockbysize(@RequestParam String name,
			@RequestParam String category, HttpServletRequest request,
			Model model, HttpServletResponse response) throws IOException,
			ParseException {
		List<UniformPurchaseDTO> list = uniformPurhaseService
				.getUniformStockDetailsbysize(name, category);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);

	}

	@RequestMapping(value = "sold-uniform-details", method = RequestMethod.GET)
	public String viewsold(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "solduniformdetails";
	}

	@RequestMapping(value = "/viewsolduniform/{id}/{id2}", method = RequestMethod.POST)
	public @ResponseBody String viewsold(@PathVariable("id") String from,
			@PathVariable("id2") String to, HttpServletRequest request,
			Model model, HttpServletResponse response) throws IOException,
			ParseException {
		List<DressPaymentDTO> list = dressPaymentService.getSoldUniformDetails(
				from, to);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);

	}

	@RequestMapping(value = "uniform-payment", method = RequestMethod.POST)
	public String payuniform(
			@ModelAttribute("DressPaymentDTO") DressPaymentDTO dressPaymentDTO,
			Model model, HttpServletRequest request) {
		List<DressPaymentDTO> list = dressPaymentService
				.submitpaymentdetails(dressPaymentDTO);
		model.addAttribute("list", list);
		return "pdfdressreceipt";
	}

	@RequestMapping(value = "getPriceforUniform", method = RequestMethod.POST)
	public @ResponseBody String getuniformPrice(@RequestParam String category,
			@RequestParam String name, @RequestParam String size,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		UniformPurchaseDTO list = uniformPurhaseService.getUniformPrice(
				category, name, size);
		return new ObjectMapper().writeValueAsString(list);
	}

	@RequestMapping(value = "/changeUniformpaidstatus", method = RequestMethod.POST)
	public @ResponseBody String changePaidStatus(
			@RequestParam("paidstaus") String paidStatus,
			@RequestParam("receiptno") int receiptno, HttpServletRequest request)
			throws JsonProcessingException, ParseException {
		dressPaymentService.updatePaymentStatus(paidStatus, receiptno);
		return null;
	}

	@RequestMapping(value = "getUniformReceipt", method = RequestMethod.GET)
	public String showStudentDetails(Model model, @RequestParam int id,
			HttpServletRequest request) {
		List<DressPaymentDTO> list = dressPaymentService.getUniformReceipt(id);
		model.addAttribute("list", list);
		return "pdfdressreceipt";
	}

	@RequestMapping(value = "viewsolduniformdetails", method = RequestMethod.POST)
	public @ResponseBody String uniformdetails(@RequestParam int receiptno,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<DressPaymentDTO> list = dressPaymentService
				.getUniformReceipt(receiptno);
		return new ObjectMapper().writeValueAsString(list);
	}

	@RequestMapping(value = "pay-uniform-dues", method = RequestMethod.GET)
	public String paydues(HttpServletRequest request, Model model) {
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList",
				sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("DressPaymentDTO", new DressPaymentDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "payuniformdues";
	}

	@RequestMapping(value = "/getunpaiddues/{id}/{id2}", method = RequestMethod.POST)
	public @ResponseBody String paydues(@PathVariable("id") int classId,
			@PathVariable("id2") int sessionId, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		List<DressPaymentDTO> list = dressPaymentService.getClasswiseDues(
				classId, sessionId);
		return new ObjectMapper().writeValueAsString(list);
	}

	@RequestMapping(value = "dues-payment", method = RequestMethod.POST)
	public String payuniformdues(
			@ModelAttribute("DressPaymentDTO") DressPaymentDTO dressPaymentDTO,
			Model model, HttpServletRequest request) {
		DressPaymentDTO list = dressPaymentService.submitdues(dressPaymentDTO);
		model.addAttribute("list", list);
		return "pdfuniformduesreceipt";
	}

	@RequestMapping(value = "viewduedetails", method = RequestMethod.POST)
	public @ResponseBody String duedetails(@RequestParam int receiptno,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<DressPaymentDTO> list = dressPaymentService
				.getDueDetails(receiptno);
		return new ObjectMapper().writeValueAsString(list);
	}

	@RequestMapping(value = "getDuesReceipt", method = RequestMethod.GET)
	public String duesReceipt(Model model, @RequestParam int id,
			HttpServletRequest request) {
		DressPaymentDTO list = dressPaymentService.getDuesReceipt(id);
		model.addAttribute("list", list);
		return "pdfuniformduesreceipt";
	}

	@RequestMapping(value = "uniform-purchase", method = RequestMethod.GET)
	public String uniformpurchase(HttpServletRequest request, Model model) {
		model.addAttribute("uniform",
				uniformPurhaseService.getUniformNamesandCategory());

		model.addAttribute("UniformPurchaseDTO", new UniformPurchaseDTO());
		allotdressService.emptytable();
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "uniformpurchase";
	}
	@RequestMapping(value = "save-temp-purchase", method = RequestMethod.POST)
	public String savepurchases(
			HttpServletRequest request,
			Model model,
			@ModelAttribute("UniformPurchaseDTO") UniformPurchaseDTO uniformPurchaseDTO) {
		allotdressService.savePurchasedUniforms(uniformPurchaseDTO);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("purchaseList",
				allotdressService.getPurchaseDetails());
		model.addAttribute("shopNames",uniformSupplierService.getSupplierNames());
		  model.addAttribute("total", allotdressService.getTotalAmount());
		model.addAttribute("UniformSupplierDTO", new UniformSupplierDTO());
		model.addAttribute("AllotDressDTO", new AllotDressDTO());
			HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "purchasepayment";
	}
	@RequestMapping(value = "save-temp-purchase", method = RequestMethod.GET)
	public String getpurchase(
			HttpServletRequest request,
			Model model) {
		model.addAttribute("uniform",
				uniformPurhaseService.getUniformNamesandCategory());

		model.addAttribute("UniformPurchaseDTO", new UniformPurchaseDTO());
		allotdressService.emptytable();
		HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "uniformpurchase";
	}
	@RequestMapping(value = "pay-purchases", method = RequestMethod.POST)
	public String paysave(
			HttpServletRequest request,
			Model model,
			@ModelAttribute("UniformSupplierDTO") UniformSupplierDTO uniformSupplierDTO) throws ParseException {
		UniformSupplierDTO list=uniformSupplierService.savePurchasedUniforms(uniformSupplierDTO);
		  model.addAttribute("list", list);
	   return "uniformboughtslip";
	}
	@RequestMapping(value = "getUniformSupplier", method = RequestMethod.POST)
	public @ResponseBody String getSupplier(HttpServletRequest request,@RequestParam String supplierName, Model model)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper().writeValueAsString(uniformSupplierService.getSupplierDetails(supplierName));
				
	}
	@RequestMapping(value = "getBoughtDetails", method = RequestMethod.POST)
	public @ResponseBody String getBought(HttpServletRequest request,@RequestParam int stockPurchaseId, Model model)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper().writeValueAsString(uniformPurhaseService.getBoughtDetails(stockPurchaseId));
				
	}
	@RequestMapping(value = "purchased-uniform-details", method = RequestMethod.GET)
	public String getpurchasedetails(
			HttpServletRequest request,
			Model model) throws ParseException {
		model.addAttribute("alluniforms",
				uniformSupplierService.getUniformsBoughtDetails());
     	HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "purchaseduniformdetails";
	}
	@RequestMapping(value = "viewpurchased-receipt", method = RequestMethod.GET)
	public String payslip(
			HttpServletRequest request,
			Model model,@RequestParam int id) throws ParseException {
		UniformSupplierDTO list=uniformSupplierService.getBoughtReceipt(id);
		  model.addAttribute("list", list);
	   return "uniformboughtslip";
	}
	@RequestMapping(value = "change-paid-status", method = RequestMethod.GET)
	public String changestatus(
			HttpServletRequest request,
			Model model,@RequestParam int id,@RequestParam String status) throws ParseException {
		uniformSupplierService.updateStatus(id,status);
		model.addAttribute("alluniforms",
				uniformSupplierService.getUniformsBoughtDetails());
     	HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "purchaseduniformdetails";
	}
	@RequestMapping(value = "check-uniform-sold", method = RequestMethod.GET)
	public String viewsolduniforms(HttpServletRequest request, Model model) {
		model.addAttribute("uniform",
				uniformPurhaseService.getUniformNamesandCategory());
        HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "checkuniformsold";
	}
	@RequestMapping(value = "showdresssold", method = RequestMethod.POST)
	public @ResponseBody String checksold(HttpServletRequest request,@RequestParam String name,@RequestParam String category,@RequestParam String size, Model model)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper().writeValueAsString(dressPaymentService.getSoldUniformDetailsbysize(name,category,size));
				
	}
	@RequestMapping(value = "getAllotPurchasebyId", method = RequestMethod.POST)
	public @ResponseBody String editpurchases(
			@RequestParam int allotDressId, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		AllotDressDTO dto = allotdressService
				.getPurchaseDetailsbyId(allotDressId);
		return new ObjectMapper().writeValueAsString(dto);
	}
	@RequestMapping(value = "/edit-uniform-allot.html", method = RequestMethod.POST)
	public String editalloted(
			@ModelAttribute("AllotDressDTO") AllotDressDTO allotDressDTO, Model model,
			HttpServletRequest request) throws ParseException {
	     allotdressService
				.updatepurchases(allotDressDTO);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("purchaseList",
				allotdressService.getPurchaseDetails());
		model.addAttribute("SupplierName",allotDressDTO.getSupplierName());
		System.out.println(allotDressDTO.getSupplierName());
		model.addAttribute("shopNames",uniformSupplierService.getSupplierNames());
		  model.addAttribute("total", allotdressService.getTotalAmount());
		model.addAttribute("UniformSupplierDTO", new UniformSupplierDTO());
		model.addAttribute("AllotDressDTO", new AllotDressDTO());
		
		return "purchasepayment";
	}
	@RequestMapping(value = "deletealloted", method = RequestMethod.POST)
	public @ResponseBody String deletealloted(
			@RequestParam int allotDressId, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		allotdressService.deleteById(allotDressId);
	     return new ObjectMapper().writeValueAsString(allotdressService.getPurchaseDetails());
	}
	@RequestMapping(value = "getStockforDress", method = RequestMethod.POST)
	public @ResponseBody String stockfordress(@RequestParam String name,
			@RequestParam String category,@RequestParam String size, HttpServletRequest request,
			Model model, HttpServletResponse response) throws IOException,
			ParseException {
		int stock = uniformPurhaseService
				.getUniformStockDetailsforeach(name, category,size);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(stock);

	}
	@RequestMapping(value = "/getlastfeemonthcategories/{id}/{id2}", method = RequestMethod.POST)
	public @ResponseBody String lastmonth(
			@PathVariable("id") String studentId,@PathVariable("id2") int total, HttpServletRequest request,
			Model model, HttpServletResponse response) throws IOException {
		dressPaymentService.savesolddetails(studentId,total);
		List<FeesCategoriesDTO> list =feeService.getNextMonthFeedetailsbystudentId(studentId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);

	}
	@RequestMapping(value = "showdresspurchased", method = RequestMethod.POST)
	public @ResponseBody String checkpurchased(HttpServletRequest request,@RequestParam String name,@RequestParam String category,@RequestParam String size, Model model)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper().writeValueAsString(uniformPurhaseService.getPurchasedUniformDetailsbysize(name,category,size));
				
	}
	@RequestMapping(value = "check-uniform-purchased", method = RequestMethod.GET)
	public String viewpurchases(HttpServletRequest request, Model model) {
		model.addAttribute("uniform",
				uniformPurhaseService.getUniformNamesandCategory());
        HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "checkuniformpurchased";
	}
	@RequestMapping(value = "class-wise-solddetails", method = RequestMethod.GET)
	public String checkbook(HttpServletRequest request,Model model) {
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
	     HttpSession session = request.getSession();
		session.setAttribute("active", "dress");
		return "classwisesolddetails";
	}
	@RequestMapping(value = "getStudentUniformdetails", method = RequestMethod.POST)
	public @ResponseBody String boughtbook(
			@RequestParam int classId,@RequestParam int sessionId, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		List<DressPaymentDTO> list = dressPaymentService.getStudentUniforms(classId,sessionId);
		return new ObjectMapper().writeValueAsString(list);
	}
	@RequestMapping(value = "viewallsolduniformsdetails", method = RequestMethod.POST)
	public @ResponseBody
	String booksolddetails(@RequestParam int feeId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<DressPaymentDTO> list = dressPaymentService.geUniformSold(feeId);
		return new ObjectMapper().writeValueAsString(list);
	}
	@RequestMapping(value = "getStudentUniformAmounts", method = RequestMethod.POST)
	public @ResponseBody String studentdata(
			@RequestParam int classId,@RequestParam int sessionId, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		List<DressPaymentDTO> list = dressPaymentService.getStudentSoldUniforms(classId,sessionId);
		return new ObjectMapper().writeValueAsString(list);
	}
}
