package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.IssueBook;
import org.brijframework.college.models.dto.IssueBookDTO;


public interface IssueBookDao extends DAO<Integer, IssueBook>{

	List<IssueBook> getIssuedBookByStudentId(String studentId);

	

}
