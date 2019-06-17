package org.brijframework.college.controller.admin.fee;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class FeePaymentTallyController {
	@Autowired
	private SessionService sessionService;
	@Autowired
	private StudentClassesService studentClassesService;

	@RequestMapping(value = "fee-payment-tally.html", method = RequestMethod.GET)
	public String feePayment(HttpServletRequest request, Model model) {
		int m = Calendar.getInstance().get(Calendar.MONTH);
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		model.addAttribute("sessionList", sessionService.findAllsession());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("classesList", studentClassesService.getAllClass());
		model.addAttribute("thismonth", m + 1);
		model.addAttribute("studentFeeSubmissionDetailsDTO",
				new StudentFeeSubmissionDetailsDTO());
		return "feepaymenttally";
	}

	@RequestMapping(value = "view-class-wise-defauletrs.html", method = RequestMethod.GET)
	public String viewDefaultersList(ModelMap model, HttpServletRequest request) {
		model.addAttribute("classList", studentClassesService.getAllClass());
		HttpSession session = request.getSession();
		session.setAttribute("active", "fee");
		return "viewclasswisedefaulters";
	}
	
}
