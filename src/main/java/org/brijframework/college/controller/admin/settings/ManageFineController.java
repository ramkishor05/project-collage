package org.brijframework.college.controller.admin.settings;

import org.brijframework.college.models.dto.FineDTO;
import org.brijframework.college.service.FineService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/admin/**")
public class ManageFineController {
	@Autowired
	private FineService fineService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private StudentFeeSubmissionDetailsService studentFeeSubmissionDetailsService;

	@RequestMapping(value = "fine.html", method = RequestMethod.GET)
	public String finePage(ModelMap model) {
		FineDTO fine = fineService.getFineByName("Late Fee Fine",sessionService.findCurrent().getSessionId());
		model.addAttribute("current", sessionService.findCurrent());
		model.addAttribute("sessionList", sessionService.findAllsession());
		if (fine == null)
			model.addAttribute("Fine", new FineDTO());
		else
			model.addAttribute("Fine", fine);
		return "fine";
	}

	@RequestMapping(value = "fine.html", method = RequestMethod.POST)
	public String finePageAddUpDate(@ModelAttribute("Fine") FineDTO fine,
			RedirectAttributes redirectAttributes) {
		
		if (studentFeeSubmissionDetailsService.getBySessionId(
				fine.getSessionId()).isEmpty()) {
			if (fine.getFineId() == 0)
				fineService.createFine(fine);
			else
				fineService.updateFine(fine);
			redirectAttributes.addFlashAttribute("msg",
					"Added Fine Successfully");
		} else {
			redirectAttributes
					.addFlashAttribute("msg",
							"Fees has been already payed so fine cannot be changed");
		}
		return "redirect:fine.html";
	}
	@RequestMapping(value = "getFinebysessions", method = RequestMethod.POST)
	public @ResponseBody String getFeeCategory(@RequestParam int sessionId)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(fineService.getFineByName("Late Fee Fine",sessionId));
	}
}
