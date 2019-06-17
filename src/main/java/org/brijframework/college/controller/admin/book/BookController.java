package org.brijframework.college.controller.admin.book;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.model.Session;
import org.brijframework.college.model.Subjects;
import org.brijframework.college.models.dto.AllotDressDTO;
import org.brijframework.college.models.dto.BookPurchaseDTO;
import org.brijframework.college.models.dto.BookSellDTO;
import org.brijframework.college.models.dto.BookSupplierDTO;
import org.brijframework.college.models.dto.FeesCategoriesDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.SubjectDTO;
import org.brijframework.college.service.AllotDressService;
import org.brijframework.college.service.BookPurchaseService;
import org.brijframework.college.service.BookSellService;
import org.brijframework.college.service.BookSupplierService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.brijframework.college.service.SubjectService;
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
public class BookController {

	@Autowired
	BookPurchaseService bookPurchaseService;
	@Autowired
	StudentClassesService classService;
	@Autowired
	SessionService sessionService;
	@Autowired
	BookSellService bookSellService;
	@Autowired
	SubjectService subjectService;
	@Autowired
	AllotDressService allotdressService;
	@Autowired
	BookSupplierService bookSupplierService;
	@Autowired
	StudentFeeSubmissionDetailsService feeService;
	@Autowired
	StudentsAdmissionService admissionService;

	@RequestMapping(value = "book-menu", method = RequestMethod.GET)
	public String dressMenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "book");
		return "bookmenu";
	}

	@RequestMapping(value = "/purchase-book", method = RequestMethod.GET)
	public String purchaseuniform(Model model, HttpServletRequest request)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = bookPurchaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("BookPurchaseDTO", new BookPurchaseDTO());
		model.addAttribute("purchaseList",
				bookPurchaseService.getPurchaseDetails());
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "book");
		return "purchasebook";
	}

	@RequestMapping(value = "/save-BookPurchases", method = RequestMethod.POST)
	public String savepurchaseuniform(
			@ModelAttribute BookPurchaseDTO bookPurchaseDTO, Model model,
			HttpServletRequest request) throws ParseException {
		bookPurchaseService.savepurchasedetals(bookPurchaseDTO);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = bookPurchaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("BookPurchaseDTO", new BookPurchaseDTO());
		model.addAttribute("purchaseList",
				bookPurchaseService.getPurchaseDetails());
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("msg", "Successfully Added");
		HttpSession session = request.getSession();
		session.setAttribute("active", "book");
		return "purchasebook";
	}

	@RequestMapping(value = "/save-BookPurchases", method = RequestMethod.GET)
	public String showuniform(Model model, HttpServletRequest request)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = bookPurchaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("BookPurchaseDTO", new BookPurchaseDTO());
		model.addAttribute("purchaseList",
				bookPurchaseService.getPurchaseDetails());
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "book");
		return "purchasebook";
	}

	@RequestMapping(value = "getBookPurchasebyId", method = RequestMethod.POST)
	public @ResponseBody
	String uniformPurchase(@RequestParam int bookPurchaseId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		BookPurchaseDTO dto = bookPurchaseService
				.getPurchaseDetailsbyId(bookPurchaseId);
		return new ObjectMapper().writeValueAsString(dto);
	}

	@RequestMapping(value = "/edit-book-purchase.html", method = RequestMethod.POST)
	public String edituniform(@ModelAttribute BookPurchaseDTO bookPurchaseDTO,
			Model model, HttpServletRequest request) throws ParseException {
		String result = bookPurchaseService.updatepurchases(bookPurchaseDTO);
		model.addAttribute("msg", result);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = bookPurchaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("BookPurchaseDTO", new BookPurchaseDTO());
		model.addAttribute("purchaseList",
				bookPurchaseService.getPurchaseDetails());
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "book");
		return "purchasebook";
	}

	@RequestMapping(value = "/edit-book-purchase.html", method = RequestMethod.GET)
	public String editshowuniform(Model model, HttpServletRequest request)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = bookPurchaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("BookPurchaseDTO", new BookPurchaseDTO());
		model.addAttribute("purchaseList",
				bookPurchaseService.getPurchaseDetails());
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "book");
		return "purchasebook";
	}

	@RequestMapping(value = "/deletebookpurchases", method = RequestMethod.GET)
	public String deleteuniform(Model model, HttpServletRequest request,
			@RequestParam int id) throws ParseException {
		String result = bookPurchaseService.cancelPurchase(id);
		model.addAttribute("msg", result);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int productNo = bookPurchaseService.getProductNo();
		model.addAttribute("productNo", productNo);
		model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("BookPurchaseDTO", new BookPurchaseDTO());
		model.addAttribute("purchaseList",
				bookPurchaseService.getPurchaseDetails());
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "book");
		return "purchasebook";
	}
	@RequestMapping(value = "sell-book", method = RequestMethod.GET)
	public String selluniform(
			HttpServletRequest request,
			Model model) {
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("BookSellDTO", new BookSellDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "book");
		return "sellbook";
	}
	@RequestMapping(value = "sell-books", method = RequestMethod.POST)
	public String sellbook1(@ModelAttribute BookSellDTO bookSellDTO,
			HttpServletRequest request,
			Model model) {
		List<BookSellDTO> list=	bookSellService.savepayment(bookSellDTO);
		model.addAttribute("list", list);
		return "pdfbookreceipt";
	}
	@RequestMapping(value ="/getBookList/{id}/{id2}", method = RequestMethod.POST)
	public @ResponseBody
	String getCategory(@PathVariable("id") int classId,@PathVariable("id2") int sectionId)
			throws JsonProcessingException {
		List<BookPurchaseDTO> list = bookPurchaseService.getClassSectionBookList(classId,sectionId);
		return new ObjectMapper().writeValueAsString(list);
	}
	@RequestMapping(value = "sold-book-details", method = RequestMethod.GET)
	public String viewsold(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "book");
		return "viewsoldbook";
	}

	@RequestMapping(value = "/viewsoldbook/{id}/{id2}", method = RequestMethod.POST)
	public @ResponseBody
	String viewsold(@PathVariable("id") String from,
			@PathVariable("id2") String to, HttpServletRequest request,
			Model model, HttpServletResponse response) throws IOException,
			ParseException {
		List<BookSellDTO> list = bookSellService.getSoldBookDetails(from,
				to);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);

	}
	@RequestMapping(value = "getBookReceipt", method = RequestMethod.GET)
	public String showStudentDetails(Model model, @RequestParam int id,
			HttpServletRequest request) {
		List<BookSellDTO> list = bookSellService.getBookReceipt(id);
		model.addAttribute("list", list);
		return "pdfbookreceipt";
	}
	@RequestMapping(value = "/changeBookpaidstatus", method = RequestMethod.POST)
	public @ResponseBody
	String changePaidStatus(@RequestParam("paidstaus") String paidStatus,
			@RequestParam("receiptno") int receiptno, HttpServletRequest request)
			throws JsonProcessingException, ParseException {
		bookSellService.updatePaymentStatus(paidStatus, receiptno);
		return null;
	}
	@RequestMapping(value = "viewsoldbookdetails", method = RequestMethod.POST)
	public @ResponseBody
	String uniformdetails(@RequestParam int receiptno,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<BookSellDTO> list = bookSellService.getBookReceipt(receiptno);
		return new ObjectMapper().writeValueAsString(list);
	}
	@RequestMapping(value = "view-book-stock", method = RequestMethod.GET)
	public String stockbook(Model model, HttpServletRequest request)
			throws ParseException {
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		HttpSession session = request.getSession();
		session.setAttribute("active", "book");
		return "bookstockdetails";
	}
	@RequestMapping(value = "/getBookStockDetails/{id}/{id2}/{id3}", method = RequestMethod.POST)
	public @ResponseBody
	String getCity(@PathVariable("id") int classId,
			@PathVariable("id2") int sessionId,@PathVariable("id3") int sectionId,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<BookPurchaseDTO> list=bookPurchaseService.getBookStockDetailsnew(classId,sessionId,sectionId);
		model.addAttribute("stock", list);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(list);
	}
	@RequestMapping(value = "pay-book-dues", method = RequestMethod.GET)
	public String paydues(HttpServletRequest request,Model model) {
		model.addAttribute("classList", classService.getAllClass());
		model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("BookSellDTO", new BookSellDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "book");
		return "paybookdues";
	}
	@RequestMapping(value = "/getunpaidbookdues/{id}/{id2}", method = RequestMethod.POST)
	public @ResponseBody
	String viewunpaid(@PathVariable("id") int classId,
			@PathVariable("id2") int sessionId, HttpServletRequest request,
			Model model, HttpServletResponse response) throws IOException,
			ParseException {
		List<BookSellDTO> list = bookSellService.getUnpaidBookDues(classId,sessionId);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);

	}
	@RequestMapping(value = "bookdues-payment", method = RequestMethod.POST)
	public String payuniformdues(@ModelAttribute("BookSellDTO") BookSellDTO bookSellDTO,Model model,HttpServletRequest request) {
		BookSellDTO list = bookSellService.submitdues(bookSellDTO);
		model.addAttribute("list", list);
		return "pdfbookduesreceipt";
	}
	@RequestMapping(value = "viewbookduedetails", method = RequestMethod.POST)
	public @ResponseBody
	String duedetails(@RequestParam int receiptno,
			HttpServletRequest request, Model model)
			throws JsonProcessingException {
		List<BookSellDTO> list = bookSellService.getBookDueDetails(receiptno);
		return new ObjectMapper().writeValueAsString(list);
	}

@RequestMapping(value = "geBooktDuesReceipt", method = RequestMethod.GET)
public String duesReceipt(Model model, @RequestParam int id,
		HttpServletRequest request) {
	BookSellDTO list = bookSellService.getBookDuesReceipt(id);
	model.addAttribute("list", list);
	return "pdfbookduesreceipt";
}
@RequestMapping(value = "/book-purchase", method = RequestMethod.GET)
public String bookpurchase(Model model, HttpServletRequest request)
		throws ParseException {
	model.addAttribute("classList", classService.getAllClass());
	model.addAttribute("BookPurchaseDTO", new BookPurchaseDTO());
    model.addAttribute("current", sessionService.findCurrent());
	HttpSession session = request.getSession();
	session.setAttribute("active", "book");
	return "bookpurchase";
}
@RequestMapping(value = "/getsubjectsforbook/{id}/{id2}", method = RequestMethod.POST)
public @ResponseBody
String viewsubjects(@PathVariable("id") int classId,@PathVariable("id2") int sectionId,
		HttpServletRequest request,
		Model model, HttpServletResponse response) throws IOException,
		ParseException {
	List<SubjectDTO> list=subjectService.getSubjectById(classId, sectionId, sessionService.findCurrent().getSessionId());
	ObjectMapper mapper = new ObjectMapper();
	return mapper.writeValueAsString(list);

}
@RequestMapping(value = "getBookforSubject", method = RequestMethod.POST)
public @ResponseBody
String bookdetails(@RequestParam int id,
		HttpServletRequest request, Model model)
		throws JsonProcessingException {
	List<BookPurchaseDTO> list = bookPurchaseService.getBookbySubject(id,sessionService.findCurrent().getSessionId());
	return new ObjectMapper().writeValueAsString(list);
}
@RequestMapping(value = "getSubjectName", method = RequestMethod.POST)
public @ResponseBody
String subjectName(@RequestParam int id,
		HttpServletRequest request, Model model)
		throws JsonProcessingException {
       Subjects subject=subjectService.read(id);
	return new ObjectMapper().writeValueAsString(subject.getSubjectName());
}
@RequestMapping(value = "save-books-purchase", method = RequestMethod.POST)
public String savepurchases(
		HttpServletRequest request,
		Model model,
		@ModelAttribute("BookPurchaseDTO") BookPurchaseDTO bookPurchaseDTO) {
	allotdressService.savePurchasedBooks(bookPurchaseDTO);
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    model.addAttribute("currentDate", df.format(new Date()));
	model.addAttribute("purchaseList",
			allotdressService.getBookPurchaseDetails());
	model.addAttribute("shopNames",bookSupplierService.getSupplierNames());
	  model.addAttribute("total", allotdressService.getTotalAmount());
	model.addAttribute("BookSupplierDTO", new BookSupplierDTO());
	model.addAttribute("AllotDressDTO", new AllotDressDTO());
		HttpSession session = request.getSession();
	session.setAttribute("active", "book");
	return "bookpayment";
}
@RequestMapping(value = "getBookSupplier", method = RequestMethod.POST)
public @ResponseBody String getSupplier(HttpServletRequest request,@RequestParam String supplierName, Model model)
		throws JsonProcessingException, ParseException {
	return new ObjectMapper().writeValueAsString(bookSupplierService.getSupplierDetails(supplierName));
			
}
@RequestMapping(value = "getBoughtBookDetails", method = RequestMethod.POST)
public @ResponseBody String getBought(HttpServletRequest request,@RequestParam int stockPurchaseId, Model model)
		throws JsonProcessingException, ParseException {
	return new ObjectMapper().writeValueAsString(bookPurchaseService.getBoughtDetails(stockPurchaseId));
			
}
@RequestMapping(value = "pay-bookpurchases", method = RequestMethod.POST)
public String paysave(
		HttpServletRequest request,
		Model model,
		@ModelAttribute("BookSupplierDTO") BookSupplierDTO bookSupplierDTO) throws ParseException {
	BookSupplierDTO list=bookSupplierService.savePurchasedBook(bookSupplierDTO);
	  model.addAttribute("list", list);
   return "bookboughtslip";
}
@RequestMapping(value = "getAllotBookPurchasebyId", method = RequestMethod.POST)
public @ResponseBody String editpurchases(
		@RequestParam int allotDressId, HttpServletRequest request,
		Model model) throws JsonProcessingException {
	AllotDressDTO dto = allotdressService
			.getBookPurchaseDetailsbyId(allotDressId);
	return new ObjectMapper().writeValueAsString(dto);
}
@RequestMapping(value = "/edit-book-allot.html", method = RequestMethod.POST)
public String editalloted(
		@ModelAttribute("AllotDressDTO") AllotDressDTO allotDressDTO, Model model,
		HttpServletRequest request) throws ParseException {
     allotdressService
			.updatebookpurchases(allotDressDTO);
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    model.addAttribute("currentDate", df.format(new Date()));

	model.addAttribute("SupplierName",allotDressDTO.getSupplierName());
	 model.addAttribute("currentDate", df.format(new Date()));
		model.addAttribute("purchaseList",
				allotdressService.getBookPurchaseDetails());
		model.addAttribute("shopNames",bookSupplierService.getSupplierNames());
		  model.addAttribute("total", allotdressService.getTotalAmount());
		model.addAttribute("BookSupplierDTO", new BookSupplierDTO());
		model.addAttribute("AllotDressDTO", new AllotDressDTO());
	
	return "bookpayment";
}
@RequestMapping(value = "deleteallotedbook", method = RequestMethod.POST)
public @ResponseBody String deletealloted(
		@RequestParam int allotDressId, HttpServletRequest request,
		Model model) throws JsonProcessingException {
	allotdressService.deleteById(allotDressId);
     return new ObjectMapper().writeValueAsString(allotdressService.getBookPurchaseDetails());
}
@RequestMapping(value = "book-purchase-details", method = RequestMethod.GET)
public String getbookpurchases(
		HttpServletRequest request,
		Model model) throws ParseException {
	model.addAttribute("allbooks",
			bookSupplierService.getBookBoughtDetails());
 	HttpSession session = request.getSession();
	session.setAttribute("active", "dress");
	return "bookpurchasedetails";
}
@RequestMapping(value = "change-paidbook-status", method = RequestMethod.GET)
public String changestatus(
		HttpServletRequest request,
		Model model,@RequestParam int id,@RequestParam String status) throws ParseException {
	bookSupplierService.updateStatus(id,status);
	model.addAttribute("allbooks",
			bookSupplierService.getBookBoughtDetails());
 	HttpSession session = request.getSession();
	session.setAttribute("active", "dress");
	return "bookpurchasedetails";
}
@RequestMapping(value = "sell-book", method = RequestMethod.POST)
public String sellbook(@ModelAttribute BookSellDTO bookSellDTO,
		HttpServletRequest request,
		Model model) {
	bookSellService.savestudentbooks(bookSellDTO);
	StudentDTO student=admissionService.findStudentDetails(bookSellDTO.getStudentId());
	List<FeesCategoriesDTO> list =feeService.getNextMonthFeedetailsbystudentId(bookSellDTO.getStudentId());
	model.addAttribute("student", student);
	  model.addAttribute("list", list);
	return "studentboughtbook";
}
@RequestMapping(value = "check-book-sold", method = RequestMethod.GET)
public String checkbook(HttpServletRequest request,Model model) {
	model.addAttribute("classList", classService.getAllClass());
	model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
	model.addAttribute("current", sessionService.findCurrent());
     HttpSession session = request.getSession();
	session.setAttribute("active", "book");
	return "checkbooksold";
}

@RequestMapping(value = "getStudentBookdetails", method = RequestMethod.POST)
public @ResponseBody String boughtbook(
		@RequestParam int classId,@RequestParam int sessionId, HttpServletRequest request,
		Model model) throws JsonProcessingException {
	List<BookSellDTO> list = bookSellService.getStudentBooks(classId,sessionId);
	return new ObjectMapper().writeValueAsString(list);
}
@RequestMapping(value = "viewallsoldbookdetails", method = RequestMethod.POST)
public @ResponseBody
String booksolddetails(@RequestParam String studentId,
		HttpServletRequest request, Model model)
		throws JsonProcessingException {
	List<BookSellDTO> list = bookSellService.getBooksSoldtoStudent(studentId);
	return new ObjectMapper().writeValueAsString(list);
}
@RequestMapping(value = "viewpurchasedbook-receipt", method = RequestMethod.GET)
public String payslip(
		HttpServletRequest request,
		Model model,@RequestParam int id) throws ParseException {
	BookSupplierDTO list=bookSupplierService.getBookBoughtReceipt(id);
	  model.addAttribute("list", list);
   return "bookboughtslip";
}
@RequestMapping(value = "classwise-book-purchased", method = RequestMethod.GET)
public String classwisebook(HttpServletRequest request,Model model) {
	model.addAttribute("classList", classService.getAllClass());
	model.addAttribute("sessionList", sessionService.findAllByIsDeletedTrue(Session.class));
	model.addAttribute("current", sessionService.findCurrent());
     HttpSession session = request.getSession();
	session.setAttribute("active", "book");
	return "classwisebookpurchased";
}
@RequestMapping(value = "getClasswisePurchasedBook", method = RequestMethod.POST)
public @ResponseBody String purchasedclasswisebook(
		@RequestParam int classId,@RequestParam int sessionId,@RequestParam int sectionId, HttpServletRequest request,
		Model model) throws JsonProcessingException {
	List<BookPurchaseDTO> list = bookPurchaseService.getClasssecttionWiseBooks(sessionId,classId,sectionId);
	return new ObjectMapper().writeValueAsString(list);
}
@RequestMapping(value = "getStudentBookAmounts", method = RequestMethod.POST)
public @ResponseBody String booksdetails(
		@RequestParam int classId,@RequestParam int sessionId, HttpServletRequest request,
		Model model) throws JsonProcessingException {
	List<BookSellDTO> list = bookSellService.getStudentBooksbought(classId,sessionId);
	return new ObjectMapper().writeValueAsString(list);
}
}

