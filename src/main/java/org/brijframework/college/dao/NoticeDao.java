package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.Notice;

public interface NoticeDao extends DAO<Integer, Notice> {
	public List<Notice> getAllNoticeByPageno(int pageNo);
}
