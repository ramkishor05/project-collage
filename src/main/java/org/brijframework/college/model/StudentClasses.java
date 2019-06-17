package org.brijframework.college.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "class")
public class StudentClasses extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "class_id")
	private Integer classId;
	@Column(name = "class_name")
	private String className;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "studentClasses")
	private List<Section> sections;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "studentClasses")
	private List<StudentAttendance> studentAttendances;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "studentClasses")
	private List<AssignClass> assignClasses;

	public List<AssignClass> getAssignClasses() {
		return assignClasses;
	}

	public void setAssignClasses(List<AssignClass> assignClasses) {
		this.assignClasses = assignClasses;
	}

	public List<StudentAttendance> getStudentAttendances() {
		return studentAttendances;
	}

	public void setStudentAttendances(List<StudentAttendance> studentAttendances) {
		this.studentAttendances = studentAttendances;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}