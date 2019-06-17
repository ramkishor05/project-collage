package org.brijframework.college.commom.convertor;

import org.brijframework.college.model.SectionWiseFee;
import org.brijframework.college.models.dto.SectionWiseFeeDTO;

public class SectionWiseFeeEntityToDTO {
	private static final SectionWiseFeeEntityToDTO sectionWiseFeeEntityToDTO = new SectionWiseFeeEntityToDTO();

	public static final SectionWiseFeeEntityToDTO getInstance() {
		return sectionWiseFeeEntityToDTO;
	}

	public SectionWiseFeeDTO convertEntityToDTO(SectionWiseFee sectionWiseFee) {
		SectionWiseFeeDTO sectionWiseFeeDTO = new SectionWiseFeeDTO();
		if (sectionWiseFee == null) {
		} else {
			sectionWiseFeeDTO.setActive(sectionWiseFee.isActive());
			sectionWiseFeeDTO.setClassId(sectionWiseFee.getClasses()
					.getClassId());
			sectionWiseFeeDTO.setClassName(sectionWiseFee.getClasses()
					.getClassName());
			sectionWiseFeeDTO.setFeeAmount(String.valueOf(sectionWiseFee
					.getFeeAmount()));
			sectionWiseFeeDTO.setFeesCategoriesId(sectionWiseFee
					.getFeesCategories().getFeeCategoryId());
			sectionWiseFeeDTO.setFeesCategoriesName(sectionWiseFee
					.getFeesCategories().getFeeCategoryName());
			sectionWiseFeeDTO
					.setMonthId(sectionWiseFee.getMonth().getMonthId());
			sectionWiseFeeDTO.setMonthName(sectionWiseFee.getMonth()
					.getMonthName());
			sectionWiseFeeDTO.setSectionId(sectionWiseFee.getSection()
					.getSectionId());
			sectionWiseFeeDTO.setSectionName(sectionWiseFee.getSection()
					.getSectionName());
			sectionWiseFeeDTO.setSectionWiseFeeId(sectionWiseFee
					.getSectionWiseFeeId());
			sectionWiseFeeDTO.setSessionId(sectionWiseFee.getSession()
					.getSessionId());
			sectionWiseFeeDTO.setSessionName(sectionWiseFee.getSession()
					.getSessionDuration());
		}
		return sectionWiseFeeDTO;
	}
}
