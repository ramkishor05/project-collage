package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.FeeDiscount;
import org.brijframework.college.models.dto.FeeDiscountDTO;

public interface FeeDiscountService extends CRUDService<Integer, FeeDiscount> {
	public void createFeeDiscount(int classId, int sectioId,
			String studentAdmissionNo, String feeDiscountName,
			Double feeDiscountAmount,String lastDate,String fromDate,String toDate);

	public void updateFeeDiscount(int feeDiscountId, String feeDiscountName,
			Double feeDiscountAmount);

	public List<FeeDiscountDTO> getFeeDiscountDTOs(int classId, int sectioId,
			String studentAdmissionNo,String fromDate,String toDate);

	FeeDiscountDTO getFeeDiscountById(int feeDiscountId);
}
