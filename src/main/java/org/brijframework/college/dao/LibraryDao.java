package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.Library;

public interface LibraryDao  extends DAO<Integer, Library>{

	Library getLastRecord();

	List<Library> getSubjectByClass(Integer classId);

	List<Library> getSubjectDetails(Integer classId, String subjectName);

}
