package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.NoticeDao;
import org.brijframework.college.model.Notice;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("noticeDao")
public class NoticeDaoImpl extends DAOImpl<Integer, Notice> implements
		NoticeDao {

	@SuppressWarnings("unchecked")
	public List<Notice> getAllNoticeByPageno(int pageNo) {
		return sessionFactory.getCurrentSession().createCriteria(Notice.class)
				.addOrder(Order.desc("createdAt"))
				.setMaxResults(6).setFirstResult(pageNo * 6).list();
	}

}
