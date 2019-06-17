package org.brijframework.college.controller.admin.notice;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.brijframework.college.models.dto.NoticeDTO;
import org.brijframework.college.service.NoticeService;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@RequestMapping(value = "/manage-notice.html", method = RequestMethod.GET)
	public String showNoticeMenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "notice");
		return "noticemenu";
	}

	@RequestMapping(value = "/create-notice.html", method = RequestMethod.GET)
	public String showCreateNotice(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "notice");
		model.addAttribute("noticeDTO", new NoticeDTO());
		return "create-notice";
	}

	@RequestMapping(value = "create-notice", method = RequestMethod.POST)
	public String CreateNotice(
			@ModelAttribute("noticeDTO") NoticeDTO noticeDTO, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException {
		model.addAttribute("NoticeDto", noticeService.CreateNotice(noticeDTO));
		HttpSession session = request.getSession();
		session.setAttribute("active", "notice");
		model.addAttribute("noticeDTO", new NoticeDTO());
//		return "create-notice";
		return "pdfnotice" ;
	}

	@RequestMapping(value = "show-notice.html", method = RequestMethod.GET)
	public String ViewAllNotice(
			@RequestParam(value = "pageno", defaultValue = "0", required = false) int pageno,
			@RequestParam(required = false) String msg, Model model,
			HttpServletRequest request) throws ParseException, IOException {
		int totalNoOfPages = 0;
		List<NoticeDTO> list = noticeService.ShowAllNotice();
		totalNoOfPages = (list.size() / 6) + 1;
		model.addAttribute("pageno", pageno);
		model.addAttribute("totalNoOfPages", totalNoOfPages);
		model.addAttribute("noticeList",
				noticeService.getAllNoticeByPageno(pageno - 1));
		HttpSession session = request.getSession();
		session.setAttribute("active", "notice");
		model.addAttribute("msg", msg);
		return "show-notice";
	}

	@RequestMapping(value = "edit.html", method = RequestMethod.GET)
	public String EditNotice(
			@Param int id,
			HttpServletResponse response,
			@RequestParam(value = "pageno", defaultValue = "0", required = false) int pageno,
			HttpServletRequest request, Model model) throws IOException,
			ParseException {
		NoticeDTO noticeDTO = noticeService.EditNotice(id);
		noticeDTO.setPageno(pageno);
		model.addAttribute("edit", noticeDTO);
		model.addAttribute("noticetDTO", new NoticeDTO());
		HttpSession session = request.getSession();
		session.setAttribute("active", "notice");
		return "edit-notice";
	}

	@RequestMapping(value = "update-notice.html", method = RequestMethod.POST)
	public void UpdateNotice(@ModelAttribute("noticetDTO") NoticeDTO noticeDTO,
			HttpServletResponse response, Model model) throws IOException,
			ParseException {
		noticeService.UpdateNotice(noticeDTO);
		response.sendRedirect("show-notice.html?pageno="
				+ noticeDTO.getPageno() + "&msg=Notice Updated Succeccfully");

	}

	@RequestMapping(value = "delete.html", method = RequestMethod.GET)
	public void DeleteNotice(
			@Param int id,
			HttpServletResponse response,
			@RequestParam(value = "pageno", defaultValue = "0", required = false) int pageno)
			throws IOException {
		noticeService.deleteById(id);
		response.sendRedirect("show-notice.html?pageno=" + pageno
				+ "&msg=Notice deleted Successfully");
	}

	@RequestMapping(value = "view.html", method = RequestMethod.GET)
	public String ViewNotice(@Param int id, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException,
			ParseException {
		NoticeDTO dto = noticeService.EditNotice(id);
		model.addAttribute("noticeDto", dto);
		HttpSession session = request.getSession();
		session.setAttribute("active", "notice");
		return "view-notice";

	}

	@RequestMapping(value = "view-notice-pdf", method = RequestMethod.GET)
	public String viewnoticepdf(@RequestParam int id, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException {
		model.addAttribute("NoticeDto", noticeService.viewpdfnotice(id));
		return "pdfnotice";
	}
}
