package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.Subjects;

public interface SubjectDao extends DAO<Integer, Subjects>{

	List<Subjects> getSubjectList(int classId, int sectionId, int sessionId);
	public Subjects getSubjects(int classId, int sectionId, String subjectName,int sessionId);
	List<Subjects> getSubjectforclass(int classId, Integer sessionId);

}
