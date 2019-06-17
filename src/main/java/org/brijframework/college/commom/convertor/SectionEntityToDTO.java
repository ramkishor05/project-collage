package org.brijframework.college.commom.convertor;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.model.Section;
import org.brijframework.college.models.dto.SectionDTO;

public class SectionEntityToDTO {
	public static List<SectionDTO> sectionEntityToDTO(List<Section> sectionList) {
		List<SectionDTO> sectionDTOs = new ArrayList<SectionDTO>();
		for (Section section : sectionList) {
			SectionDTO sectionDTO = new SectionDTO();
			sectionDTO.setClassName(section.getStudentClasses().getClassName());
			sectionDTO.setSectionName(section.getSectionName());
			sectionDTO.setSectionId(section.getSectionId());
			sectionDTOs.add(sectionDTO);
		}
		return sectionDTOs;
	}
	
}
