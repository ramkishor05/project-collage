package org.brijframework.college.controller.admin.fee;

import org.brijframework.college.service.FeesCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/admin/**", "/employee/**" ,"/branch/**"})
public class AlterFeeRecieptController {
	@Autowired
	private FeesCategoriesService feesCategoriesService;
	@RequestMapping(value = "alterfeereciept.html", method = RequestMethod.GET)
	public String alterFeeRecipetPage(@RequestParam int slipNo,
			String studentAdmissionNo) {
		feesCategoriesService.getFeesCategories(studentAdmissionNo, slipNo);
		return "alterfeereciept";
	}

}
