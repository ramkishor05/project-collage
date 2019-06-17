package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.Section;

public interface SectionDao extends DAO<Integer, Section>{

	List<Section> getSectionListByClassId(int classId);
	
	Section getSectionByClassIdAndSectionName(int classId,String sectionName);

}
