package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.EditFeeAmount;
import org.brijframework.college.models.dto.EditFeeAmountDTO;

public interface EditFeeAmountService extends
		CRUDService<Integer, EditFeeAmount> {
	public List<EditFeeAmountDTO> getEditFeeAmountDTOs(int classId,
			int sectionId, int sessionId, int feeCategoryId, int monthId,
			String studentId, double allottedAmount);

	public void createEditFeeAmount(EditFeeAmountDTO editFeeAmountDTO);
}
