package org.brijframework.college.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "annual_exam_total_gain_marks")
public class AnnualExamTotalGainMarks extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "annual_exam_total_gain_marks_id")
	private int annualExamTotalGainMarksId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private StudentClasses studentClasses;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private Section section;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Students students;
	@Column(name = "total_marks")
	private Double totalMarks;
	@Column(name = "total_gain_marks")
	private Double totalGainMarks;
	@Column(name = "annual_exam_type")
	private String annualExamType;

	@Override
	public Integer getId() {
		return annualExamTotalGainMarksId;
	}

	@Override
	public void setId(Integer id) {
		this.annualExamTotalGainMarksId = id;
	}

	public int getAnnualExamTotalGainMarksId() {
		return annualExamTotalGainMarksId;
	}

	public void setAnnualExamTotalGainMarksId(int annualExamTotalGainMarksId) {
		this.annualExamTotalGainMarksId = annualExamTotalGainMarksId;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public StudentClasses getStudentClasses() {
		return studentClasses;
	}

	public void setStudentClasses(StudentClasses studentClasses) {
		this.studentClasses = studentClasses;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Students getStudents() {
		return students;
	}

	public void setStudents(Students students) {
		this.students = students;
	}

	public Double getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Double totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Double getTotalGainMarks() {
		return totalGainMarks;
	}

	public void setTotalGainMarks(Double totalGainMarks) {
		this.totalGainMarks = totalGainMarks;
	}

	public String getAnnualExamType() {
		return annualExamType;
	}

	public void setAnnualExamType(String annualExamType) {
		this.annualExamType = annualExamType;
	}

}
