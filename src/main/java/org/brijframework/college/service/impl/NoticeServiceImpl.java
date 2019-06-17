package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.brijframework.college.commom.convertor.NoticeEntityToDTO;
import org.brijframework.college.dao.NoticeDao;
import org.brijframework.college.model.Notice;
import org.brijframework.college.models.dto.NoticeDTO;
import org.brijframework.college.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("noticeService")
public class NoticeServiceImpl extends
		CRUDServiceImpl<Integer, Notice, NoticeDao> implements NoticeService {
	@Autowired
	public NoticeServiceImpl(NoticeDao dao) {
		super(dao);

	}

	@Transactional
	public NoticeDTO CreateNotice(NoticeDTO noticeDTO) throws ParseException {
		Notice notice = new Notice();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		notice.setNoticeSubject(noticeDTO.getNoticeSubject());
		notice.setNoticeDate(dateFormat.parse(noticeDTO.getNoticeDate()));
		notice.setGenratedBy(noticeDTO.getGenratedBy());
		notice.setNoticeDescription(noticeDTO.getNoticeDescription());
		notice.setCreatedAt(new Date());
		dao.create(notice);
		NoticeEntityToDTO entityToDTO = new NoticeEntityToDTO();
		NoticeDTO dto = entityToDTO.NoticeEntityToDTOConverter(notice);
		return dto;
	}

	@Transactional
	public List<NoticeDTO> ShowAllNotice() {
		List<Notice> list = dao.findAll(Notice.class);
		List<NoticeDTO> dtos = new ArrayList<NoticeDTO>();
		for (Notice notice : list) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setNoticeSubject(notice.getNoticeSubject());
			noticeDTO.setNoticeDate(String.valueOf(notice.getNoticeDate()));
			noticeDTO.setGenratedBy(notice.getGenratedBy());
			noticeDTO.setNoticeDescription(notice.getNoticeDescription());
			noticeDTO.setNoticeId(notice.getNoticeId());
			dtos.add(noticeDTO);
		}
		return dtos;
	}

	@Transactional
	public List<NoticeDTO> getAllNoticeByPageno(int pageNo) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Notice> list = dao.getAllNoticeByPageno(pageNo);
		List<NoticeDTO> dtos = new ArrayList<NoticeDTO>();
		for (Notice notice : list) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setNoticeSubject(notice.getNoticeSubject());
			noticeDTO.setNoticeDate(df.format((notice.getNoticeDate())));
			noticeDTO.setGenratedBy(notice.getGenratedBy());
			noticeDTO.setNoticeDescription(notice.getNoticeDescription());
			noticeDTO.setNoticeId(notice.getNoticeId());
			dtos.add(noticeDTO);
		}
		return dtos;
	}

	@Transactional
	public NoticeDTO EditNotice(int id) throws ParseException {
		Notice notice = dao.get(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		NoticeDTO dto = new NoticeDTO();
		String noticeDate = df.format(notice.getNoticeDate());
		dto.setNoticeSubject(notice.getNoticeSubject());
		dto.setNoticeDate(noticeDate);
		dto.setGenratedBy(notice.getGenratedBy());
		dto.setNoticeDescription(notice.getNoticeDescription());
		dto.setNoticeId(id);
		return dto;

	}

	@Transactional
	public void UpdateNotice(NoticeDTO noticeDTO) throws ParseException {
		Notice notice = dao.get(noticeDTO.getNoticeId());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		notice.setNoticeSubject(noticeDTO.getNoticeSubject());
		notice.setNoticeDate(dateFormat.parse(noticeDTO.getNoticeDate()));
		notice.setGenratedBy(noticeDTO.getGenratedBy());
		notice.setNoticeDescription(noticeDTO.getNoticeDescription());
		dao.update(notice);
	}

	@Override
	public NoticeDTO viewpdfnotice(int id) {
		Notice notice = dao.get(id);
		NoticeEntityToDTO entityToDTO = new NoticeEntityToDTO();
		NoticeDTO dto = entityToDTO.NoticeEntityToDTOConverter(notice);
		return dto;
	}
}
