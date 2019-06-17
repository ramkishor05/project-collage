package org.brijframework.college.service;

import java.text.ParseException;
import java.util.List;

import org.brijframework.college.model.Notice;
import org.brijframework.college.models.dto.NoticeDTO;

public interface NoticeService extends CRUDService<Integer, Notice> {
	NoticeDTO CreateNotice(NoticeDTO noticeDTO) throws ParseException;

	List<NoticeDTO> ShowAllNotice();

	NoticeDTO EditNotice(int id) throws ParseException;

	List<NoticeDTO> getAllNoticeByPageno(int pageNo);

	void UpdateNotice(NoticeDTO noticeDTO) throws ParseException;

	NoticeDTO viewpdfnotice(int id);
}
