package org.brijframework.college.commom.convertor;

import org.brijframework.college.model.Notice;
import org.brijframework.college.models.dto.NoticeDTO;

public class NoticeEntityToDTO {
	private static final NoticeEntityToDTO noticeEntityToDTO = new NoticeEntityToDTO();

	public static final NoticeEntityToDTO getInstance() {
		return noticeEntityToDTO;
	}

	public NoticeDTO NoticeEntityToDTOConverter(Notice notice) {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setNoticeSubject(notice.getNoticeSubject());
		noticeDTO.setNoticeDate(String.valueOf(notice.getNoticeDate()));
		noticeDTO.setGenratedBy(notice.getGenratedBy());
		noticeDTO.setNoticeDescription(notice.getNoticeDescription());
		noticeDTO.setCreatedAt(notice.getCreatedAt());
		return noticeDTO;
	}
}
