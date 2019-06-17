package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.Section;
import org.brijframework.college.models.dto.SectionDTO;

public interface SectionService extends CRUDService<Integer, Section> {

	List<SectionDTO> getSectionListByClassId(int classId);

	List<SectionDTO> getSections();

	SectionDTO getSectionById(int sectionId);

	SectionDTO getSectionByClassIdAndSectionName(int classId, String sectionName);

}
