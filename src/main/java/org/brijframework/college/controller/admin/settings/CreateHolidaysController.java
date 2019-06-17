package org.brijframework.college.controller.admin.settings;

import org.brijframework.college.models.dto.HolidayDTO;
import org.brijframework.college.service.HolidayService;
import org.brijframework.college.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class CreateHolidaysController {
	@Autowired
	private HolidayService holidayService;
	@Autowired
	private SessionService sessionService;

	@RequestMapping(value = "create-holidays.html", method = RequestMethod.GET)
	public String createHolidats(ModelMap model,
			@RequestParam(required = false, defaultValue = "14") int monthId,
			@RequestParam(required = false, defaultValue = "0") int year)
			throws Exception {
		model.addAttribute("MAP",
				holidayService.getDataForCreateHoliday(monthId, year));
		model.addAttribute("HolidayDTO", new HolidayDTO());
		return "holidaypage";
	}

	@RequestMapping(value = "create-holidays.html", method = RequestMethod.POST)
	public String createHoliday(@ModelAttribute HolidayDTO holidayDTO,
			RedirectAttributes redirectAttributes) throws Exception {
		holidayService.crateHolidays(holidayDTO);
		redirectAttributes.addAttribute("msg", "holiday Create Successfully");
		return "redirect:create-holidays.html";
	}

	@RequestMapping(value = "check-holidays.html", method = RequestMethod.POST)
	public @ResponseBody String checkHoliday(@RequestParam String holidayDate)
			throws Exception {
		return new ObjectMapper().writeValueAsString(holidayService
				.checkHolidays(holidayDate));
	}

	@RequestMapping(value = "edit-holiday.html", method = RequestMethod.POST)
	public String editHoliday(@ModelAttribute HolidayDTO holidayDTO,
			RedirectAttributes redirectAttributes) throws Exception {
		holidayService.editHolidays(holidayDTO);
		redirectAttributes.addAttribute("msg", "holiday Update Successfully");
		return "redirect:create-holidays.html";
	}

	@RequestMapping(value = "delete-holiday.html", method = RequestMethod.GET)
	public String editHoliday(@RequestParam int holidayId,
			RedirectAttributes redirectAttributes) throws Exception {
		holidayService.deleteById(holidayId);
		redirectAttributes.addAttribute("msg", "holiday Delete Successfully");
		return "redirect:create-holidays.html";
	}

}
