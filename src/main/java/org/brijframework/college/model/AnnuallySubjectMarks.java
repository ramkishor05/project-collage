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
@Table(name = "annually_subject_marks")
public class AnnuallySubjectMarks extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "annually_subject_marks_id")
	private int annuaallySubjectMarksId;
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
	@JoinColumn(name = "subject_id")
	private Subjects subjects;
	@Column(name = "exam_report_type")
	private String examReportType;
	@Column(name = "exam_type")
	private String examType;
	@Column(name = "max_marks")
	private String maxMarks;

	@Override
	public Integer getId() {
		return annuaallySubjectMarksId;
	}

	@Override
	public void setId(Integer id) {
		this.annuaallySubjectMarksId = id;
	}

	public int getAnnuaallySubjectMarksId() {
		return annuaallySubjectMarksId;
	}

	public void setAnnuaallySubjectMarksId(int annuaallySubjectMarksId) {
		this.annuaallySubjectMarksId = annuaallySubjectMarksId;
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

	public String getExamReportType() {
		return examReportType;
	}

	public void setExamReportType(String examReportType) {
		this.examReportType = examReportType;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public Subjects getSubjects() {
		return subjects;
	}

	public void setSubjects(Subjects subjects) {
		this.subjects = subjects;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

}
