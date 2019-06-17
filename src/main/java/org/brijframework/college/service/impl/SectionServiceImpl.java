package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.dao.SectionDao;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.models.dto.SectionDTO;
import org.brijframework.college.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("sectionService")
public class SectionServiceImpl extends
		CRUDServiceImpl<Integer, Section, SectionDao> implements SectionService {
	@Autowired
	public SectionServiceImpl(SectionDao dao) {
		super(dao);

	}

	@Override
	public List<SectionDTO> getSectionListByClassId(int classId) {
		List<SectionDTO> sectionDTOs = new ArrayList<SectionDTO>();
		for (Section section : dao.getSectionListByClassId(classId)) {
			SectionDTO sectionDTO = new SectionDTO();
			sectionDTO.setSectionName(section.getSectionName());
			sectionDTO.setSectionId(section.getSectionId());
			sectionDTOs.add(sectionDTO);
		}
		return sectionDTOs;
	}

	@Override
	public SectionDTO getSectionById(int sectionId) {
		Section section = dao.get(sectionId);
		SectionDTO sectionDTO = new SectionDTO();
		sectionDTO.setClassName(section.getStudentClasses().getClassName());
		sectionDTO.setClassId(section.getStudentClasses().getClassId());
		sectionDTO.setSectionName(section.getSectionName());
		sectionDTO.setSectionId(section.getSectionId());
		return sectionDTO;
	}

	@Override
	public List<SectionDTO> getSections() {
		List<Section> list = dao.findAll(Section.class);
		List<SectionDTO> sectionDTOs = new ArrayList<SectionDTO>();
		for (Section section : list) {
			SectionDTO sectionDTO = new SectionDTO();
			sectionDTO.setClassName(section.getStudentClasses().getClassName());
			sectionDTO.setSectionName(section.getSectionName());
			sectionDTO.setSectionId(section.getSectionId());
			sectionDTOs.add(sectionDTO);
		}
		return sectionDTOs;
	}

	@Override
	public SectionDTO getSectionByClassIdAndSectionName(int classId,
			String sectionName) {
		Section section = dao.getSectionByClassIdAndSectionName(classId,
				sectionName);
		SectionDTO sectionDTO = new SectionDTO();
		if (section != null) {
			sectionDTO.setClassName(section.getStudentClasses().getClassName());
			sectionDTO.setSectionName(section.getSectionName());
			sectionDTO.setSectionId(section.getSectionId());
		}
		return sectionDTO;
	}
}
