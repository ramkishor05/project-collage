package org.brijframework.college.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.brijframework.college.model.StudentWiseFee;
import org.brijframework.college.models.dto.BookSellDTO;
import org.brijframework.college.models.dto.DressPaymentDTO;
import org.brijframework.college.service.BookSellService;
import org.brijframework.college.service.DressPaymentService;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.brijframework.college.service.StudentWiseFeeService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class FeePaymentAjaxController {
	@Autowired
	private StudentFeeSubmissionDetailsService studentFeeSubmissionDetailsService;
	@Autowired
	private StudentsAdmissionService studentsAdmissionService;
	@Autowired
	SessionService sessionService;
	@Autowired
	DressPaymentService dressPaymentService;
	@Autowired
	BookSellService bookSellService;
	@Autowired
	StudentWiseFeeService studentWiseFeeService;

	@RequestMapping(value = "get-student-fee-payment-details", method = RequestMethod.GET)
	public @ResponseBody String getStudentFeePayMentDetails(
			@RequestParam String studentAdmissionNo, @RequestParam int sessionId)
			throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("StudentDTO", studentsAdmissionService.getStudentId(Integer
				.parseInt(studentAdmissionNo)));
		map.put("StudentFeeSubmissionDetailsDTOs",
				studentFeeSubmissionDetailsService.getStudentFeePaymentDetail(
						studentAdmissionNo, sessionId));
		return new ObjectMapper().writeValueAsString(map);
	}

	@RequestMapping(value = "get-payment-details", method = RequestMethod.GET)
	public @ResponseBody String getPaymentDetails(
			@RequestParam String studentAdmissionNo)
			throws JsonProcessingException {
		return new ObjectMapper()
				.writeValueAsString(studentFeeSubmissionDetailsService
						.getStudentFeePaymentDetail(studentAdmissionNo,
								sessionService.findCurrent().getSessionId()));
	}

	@RequestMapping(value = "books-uniform-details", method = RequestMethod.GET)
	public @ResponseBody String getBooksUniformDetails(
			@RequestParam int studentId, @RequestParam int categoryId,
			@RequestParam int monthId) throws JsonProcessingException {
		List<DressPaymentDTO> dressPayments = new ArrayList<DressPaymentDTO>();
		List<BookSellDTO> bookSells = new ArrayList<BookSellDTO>();
		Map<String, Object> map = new HashMap<String, Object>();
		for (StudentWiseFee studentWiseFees : studentWiseFeeService
				.getStudentWiseFee(sessionService.findCurrent().getSessionId(),
						monthId, categoryId, studentId)) {
			if (categoryId == 14) {
				for (DressPaymentDTO dressPayment : dressPaymentService
						.geUniformSold(studentWiseFees.getStudentWiseFeeId())) {
					dressPayments.add(dressPayment);
				}

			} else if (categoryId == 15) {
				for (BookSellDTO bookSellDTO : bookSellService
						.getBooksSold(studentWiseFees.getStudentWiseFeeId())) {
					bookSells.add(bookSellDTO);
				}
			} else {
				map.put("", "");
			}
		}
		map.put("UniformDetails", dressPayments);
		map.put("BooksDetails", bookSells);
		return new ObjectMapper().writeValueAsString(map);
	}

	@RequestMapping(value = "get-payment-status-details", method = RequestMethod.GET)
	public @ResponseBody String getPaymentStatusDetails(
			@RequestParam String studentId, @RequestParam String slipNo)
			throws JsonProcessingException {
		return new ObjectMapper()
				.writeValueAsString(studentFeeSubmissionDetailsService
						.getPaymentStatusDetails(studentId, slipNo));
	}

	@RequestMapping(value = "get-fee-unpaid-month", method = RequestMethod.GET)
	public @ResponseBody String getFeeUnPayMonth(
			@RequestParam String studentAdmissionNo)
			throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("StudentFeeSubmissionDetailsDTOs",
				studentFeeSubmissionDetailsService
						.getStudentFeePaymentDetail(studentAdmissionNo));
		return new ObjectMapper().writeValueAsString(map);
	}

}
