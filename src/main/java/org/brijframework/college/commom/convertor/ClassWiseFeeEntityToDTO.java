package org.brijframework.college.commom.convertor;

import org.brijframework.college.model.ClassWiseFee;
import org.brijframework.college.models.dto.ClassWiseFeeDTO;

public class ClassWiseFeeEntityToDTO {
	private static final ClassWiseFeeEntityToDTO classWiseFeeEntityToDTO = new ClassWiseFeeEntityToDTO();

	public static final ClassWiseFeeEntityToDTO getInstance() {
		return classWiseFeeEntityToDTO;
	}

	public ClassWiseFeeDTO convertEntityToDTO(ClassWiseFee classWiseFee) {
		ClassWiseFeeDTO classWiseFeeDTO = new ClassWiseFeeDTO();
		if (classWiseFee == null) {

		} else {
			classWiseFeeDTO.setActive(classWiseFee.isActive());
			classWiseFeeDTO.setClassId(classWiseFee.getClasses().getClassId());
			classWiseFeeDTO.setClassWiseFeeId(classWiseFee.getClassWiseFeeId());
			classWiseFeeDTO.setFeeAmount(String.valueOf(classWiseFee
					.getFeeAmount()));
			classWiseFeeDTO.setFeesCategoriesId(classWiseFee
					.getFeesCategories().getFeeCategoryId());
			classWiseFeeDTO.setClassName(classWiseFee.getClasses()
					.getClassName());
			classWiseFeeDTO.setFeesCategoriesName(classWiseFee
					.getFeesCategories().getFeeCategoryName());
			classWiseFeeDTO.setMonthId(classWiseFee.getMonth().getMonthId());
			classWiseFeeDTO
					.setMonthName(classWiseFee.getMonth().getMonthName());
			classWiseFeeDTO.setSessionId(classWiseFee.getSession()
					.getSessionId());
			classWiseFeeDTO.setSessionName(classWiseFee.getSession()
					.getSessionDuration());
		}
		return classWiseFeeDTO;
	}
}
