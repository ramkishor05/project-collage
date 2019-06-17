package org.brijframework.college.controller.library;

import java.util.List;

import org.brijframework.college.models.dto.IssueBookDTO;
import org.brijframework.college.models.dto.LibraryDTO;
import org.brijframework.college.models.dto.SectionDTO;
import org.brijframework.college.models.dto.SessionDTO;
import org.brijframework.college.models.dto.StudentClassesDTO;
import org.brijframework.college.service.ISBNService;
import org.brijframework.college.service.IssueBookService;
import org.brijframework.college.service.LibraryService;
import org.brijframework.college.service.SectionService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.brijframework.college.service.SubjectService;
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

@RequestMapping("/admin/**")
@Controller
public class LibraryController {

	@Autowired
	LibraryService libraryService;
	@Autowired
	StudentClassesService studentClassService;
	@Autowired
	SubjectService subjectService;
	@Autowired
	SessionService sessionService;
	@Autowired
	SectionService sectionService;
	@Autowired
	IssueBookService issueBookService;
	@Autowired
	ISBNService isbnService;

	@RequestMapping(value = "library-home.html", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String libraryHome() {
		return "libraryHome";
	}
																									/* new controller */
	
	@RequestMapping(value = "addBookHome.html", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String addBookHome(Model model) 
	{
		List<StudentClassesDTO> list = studentClassService.getAllClass();
		model.addAttribute("list", list);
		model.addAttribute("LibraryDTO", new LibraryDTO());
		return "addBookHome";
	}

	@RequestMapping(value = "addBook.html", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String addLibrary(@ModelAttribute("LibraryDTO") LibraryDTO libraryDTO) {
		libraryService.addBook(libraryDTO);
		return "libraryHome";
	}

	@RequestMapping(value = "getBookDetails.html", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String getBookDetails(Model model) throws JsonProcessingException {
		List<StudentClassesDTO> list = studentClassService.getAllClass();
		model.addAttribute("list", list);
		model.addAttribute("LibraryDTO", new LibraryDTO());
		return "bookDetails";

	}

	@RequestMapping(value = "getSubjectList.html", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getSubjectByClass(Model model, @RequestParam Integer classId)
			throws JsonProcessingException {
		List<LibraryDTO> list = libraryService.getSubjectByClass(classId);
		return new ObjectMapper().writeValueAsString(list);

	}

	@RequestMapping(value = "getSubjectDetails.html", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getSubjectDetails(Model model,
			@RequestParam Integer classId1, String subjectName)
			throws JsonProcessingException {
		List<LibraryDTO> list = libraryService.getSubjectDetails(classId1,
				subjectName);
		return new ObjectMapper().writeValueAsString(list);

	}

	@RequestMapping(value = "bookIssue.html", method = RequestMethod.GET)
	public String bookIssueHome(Model model) {
		List<StudentClassesDTO> list = studentClassService.getAllClass();
		List<SessionDTO> sessionList = sessionService.findAllActiveSession();
		model.addAttribute("classList", list);
		model.addAttribute("sessionList", sessionList);
		model.addAttribute("current", sessionService.findCurrent());

		return "bookIssue";
	}
	
	@RequestMapping(value = "returnBook.html", method = RequestMethod.GET)
	public String bookReturnHome(@ModelAttribute("issueBookDTO") IssueBookDTO issueBookDTO)
	{
		return "bookReturn";
	}
	
	@RequestMapping(value = "returnExitBook.html", method = RequestMethod.GET)
	public String bookReturnExitHome(@ModelAttribute("issueBookDTO") IssueBookDTO issueBookDTO)
	{
		
		issueBookService.returnBookToLibarary(issueBookDTO.getIssueBookId());
		
		return "bookReturn";
	}

	@ResponseBody
	@RequestMapping(value = "/getsectionlist", method = RequestMethod.POST)
	public String getSectionListByClassId(@RequestParam("id") String id)
			throws JsonProcessingException {
		List<SectionDTO> list = sectionService.getSectionListByClassId(Integer
				.parseInt(id));
		return new ObjectMapper().writeValueAsString(list);

	}

	@ResponseBody
	@RequestMapping(value = "getIssuedBookByStudentId.html", method = RequestMethod.POST)
	public String getIssuedBookByStudentId(@RequestParam String studentId)
			throws JsonProcessingException 
	{
		List<IssueBookDTO> dtoList = issueBookService
				.getIssuedBookByStudentId(studentId);
		return new ObjectMapper().writeValueAsString(dtoList);

	}

	@ResponseBody
	@RequestMapping(value = "issueBookToStudent.html", method = RequestMethod.POST)
	public String issueBookToStudent(@RequestParam String BookName,
			String SubjectName, int admissionNo, int classId, int bookId) 
	{
		// issueBookService.issueBookToStudent(bookId,admissionNo);
		libraryService.updateLibraryBook(bookId, classId);
		return SubjectName;
	}

	@ResponseBody
	@RequestMapping(value = "viewSameBooks", method = RequestMethod.POST)
	public String getBookForIssueToStudent(@RequestParam String bookName,
			int bookId, Integer admissioNo) throws JsonProcessingException {
		System.out.println(admissioNo);
		List<IssueBookDTO> list = issueBookService.getIssueBookToStudent(
				bookName, bookId, admissioNo);
		return new ObjectMapper().writeValueAsString(list);
	}
	
	@RequestMapping(value = "newIssueBook.html", method = RequestMethod.POST)
	public String newIssueBook(@ModelAttribute("issueBookDTO") IssueBookDTO issueBookDTO) 
	   {
		System.out.println("NEW"+issueBookDTO.getIsbn() + issueBookDTO.getIssueBookId());
		issueBookService.issueBookToStudent(issueBookDTO.getIssueBookId(),Integer.parseInt(issueBookDTO.getStudentId()), issueBookDTO.getIsbn());
		libraryService.updateLibraryBook(issueBookDTO.getIssueBookId(),Integer.parseInt(issueBookDTO.getStudentId()));
		isbnService.updateISBN(issueBookDTO.getIsbn(), issueBookDTO.getIssueBookId());
		return "redirect:bookIssue.html";

	}

}
