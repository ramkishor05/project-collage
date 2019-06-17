package org.brijframework.college.controller.admin.collections;
/*package com.fadsan.controller.admin.collections;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fadsan.models.dto.StudentFeeSubmissionDetailsDTO;
import com.fadsan.service.EmployeeRoleService;
import com.fadsan.service.StudentFeeSubmissionDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/admin/**")
@Controller
public class AllCollectionsController {

	@Autowired
	EmployeeRoleService employeeRoleService;
	@Autowired
	StudentFeeSubmissionDetailsService FeeSubmissionDetailsService;

	@RequestMapping(value = "collections-menu", method = RequestMethod.GET)
	public String colectionsMenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "collectionsmenu";
	}

	@RequestMapping(value = "collections-report-menu", method = RequestMethod.GET)
	public String collectionsReportMenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "collectionsreportmenu";
	}

	@RequestMapping(value = "show-overall-collections", method = RequestMethod.GET)
	public String showOverallColections(
			Model model,
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "pageno", defaultValue = "0", required = false) int pageno)
			throws IOException {
		int totalNoOfPages = 0;
		List<StudentFeeSubmissionDetailsDTO> totalList = FeeSubmissionDetailsService
				.getOverallCollectionsDetails();
		totalNoOfPages = (totalList.size() / 8) + 1;

		model.addAttribute("pageno", pageno);
		model.addAttribute("totalNoOfPages", totalNoOfPages);
		model.addAttribute("overallCollection", FeeSubmissionDetailsService
				.getOverallCollectionsDetailsByPageNo(pageno - 1));

		Float overallFee = 0f;
		for (StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO : totalList) {
			overallFee += Float.parseFloat(studentFeeSubmissionDetailsDTO
					.getPaidAmount());
		}
		
		 * for (StudentFeeSubmissionDetails details :
		 * FeeSubmissionDetailsService
		 * .findAll(StudentFeeSubmissionDetails.class)) { overallFee +=
		 * details.getPaidAmount(); model.addAttribute("overall_fee",
		 * overallFee); }
		 
		
		 * Float overallFineAmount = 0f; for (StudentFeeSubmissionDetailsDTO
		 * feeSubmissionDetailsDTO : FeeSubmissionDetailsService
		 * .getOverallCollectionsDetails()) { overallFineAmount +=
		 * Float.parseFloat(feeSubmissionDetailsDTO .getFineAmount());
		 * model.addAttribute("overall_fine", overallFineAmount); } Float
		 * overallDiscount = 0f; for (StudentFeeSubmissionDetailsDTO
		 * feeSubmissionDetailsDTO : FeeSubmissionDetailsService
		 * .getOverallCollectionsDetails()) { overallDiscount +=
		 * Float.parseFloat(feeSubmissionDetailsDTO .getDiscountAmount());
		 * model.addAttribute("overall_discount", overallDiscount); }
		 * model.addAttribute("overall_collections", overallFee +
		 * overallFineAmount - overallDiscount);
		 
		model.addAttribute("overall_collections", overallFee);

		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "showoverallcollections";
	}

	@RequestMapping(value = "show-todays-collections", method = RequestMethod.GET)
	public String showTodaysCollections(Model model,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, ParseException {
		List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTO = FeeSubmissionDetailsService
				.getTodaysCollectionsDetails();
		Float todayFee = 0f;
		for (StudentFeeSubmissionDetailsDTO details : feeSubmissionDetailsDTO) {
			todayFee += Float.parseFloat(details.getPaidAmount());
		}
		for (StudentFeeSubmissionDetailsDTO details : feeSubmissionDetailsDTO) {
			todayFee += Float.parseFloat(details.getPaidAmount());
			model.addAttribute("today_fee", todayFee);
		}
		Float overallFineAmount = 0f;
		for (StudentFeeSubmissionDetailsDTO feeSubmission : feeSubmissionDetailsDTO) {
			overallFineAmount += Float
					.parseFloat(feeSubmission.getFineAmount());
			model.addAttribute("overall_fine", overallFineAmount);
		}
		Float overallDiscount = 0f;
		for (StudentFeeSubmissionDetailsDTO feeSubmission : feeSubmissionDetailsDTO) {
			overallDiscount += Float.parseFloat(feeSubmission
					.getDiscountAmount());
			model.addAttribute("overall_discount", overallDiscount);
		}
		model.addAttribute("today_collections", todayFee);
		model.addAttribute("todaysCollection", feeSubmissionDetailsDTO);
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "showtodayscollections";
	}

	@RequestMapping(value = "show-datewise-collections", method = RequestMethod.GET)
	public String getDatewiseCollections(Model model,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, ParseException {
		
		 * List<StudentFeeSubmissionDetails> feeSubmissionDetails =
		 * FeeSubmissionDetailsService
		 * .findAll(StudentFeeSubmissionDetails.class); Float datewiseCollection
		 * = 0f; for (StudentFeeSubmissionDetails details :
		 * feeSubmissionDetails) { datewiseCollection +=
		 * details.getPaidAmount(); model.addAttribute("datewise_collections",
		 * datewiseCollection); }
		 
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "showdatewisecollections";
	}

	@RequestMapping(value = "show-datewise-collections", method = RequestMethod.POST)
	public @ResponseBody
	String showDatewiseCollections(@RequestParam String fromDate,
			@RequestParam String toDate, Model model)
			throws JsonProcessingException, ParseException {

		return new ObjectMapper()
				.writeValueAsString(FeeSubmissionDetailsService
						.getDateWiseCollectionsDetails(fromDate, toDate));
	}

	@RequestMapping(value = "show-yearly-collections", method = RequestMethod.GET)
	public String getYearlyCollections(Model model,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException, ParseException {
		HttpSession session = request.getSession();
		session.setAttribute("active", "collection");
		return "showyearlycollections";
	}

	@RequestMapping(value = "show-yearly-collections", method = RequestMethod.POST)
	public @ResponseBody
	String showYearlyCollections(@RequestParam String year)
			throws JsonProcessingException, ParseException {
		return new ObjectMapper()
				.writeValueAsString(FeeSubmissionDetailsService
						.getYearlyCollectionsDetails(year));
	}
}
*/