package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.SessionDTO;
import org.brijframework.college.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("sessionService")
public class SessionServiceImpl extends
		CRUDServiceImpl<Integer, Session, SessionDao> implements SessionService {
	@Autowired
	public SessionServiceImpl(SessionDao dao) {
		super(dao);
	}
	

	@Override
	public SessionDTO findCurrent() {
		Session session = dao.findCurrentSession();
		SessionDTO dto = new SessionDTO();
		if (session != null) {
			dto.setSessionDuration(session.getSessionDuration());
			dto.setSessionId(session.getSessionId());
		}
		return dto;
	}

	@Override
	public List<SessionDTO> findAllsession() {
		List<Session> sessionlist = dao.findAllByIsActiveTrue(Session.class);
		List<SessionDTO> list = new ArrayList<SessionDTO>();
		for (Session session : sessionlist) {
			if (session.isActive() == true) {
				SessionDTO dto = new SessionDTO();
				dto.setSessionDuration(session.getSessionDuration());
				dto.setSessionId(session.getSessionId());
				list.add(dto);
			}
		}
		return list;
	}

	@Override
	public SessionDTO getSessionById(int sessionId) {
		Session session = dao.get(sessionId);
		SessionDTO dto = new SessionDTO();
		dto.setSessionDuration(session.getSessionDuration());
		dto.setSessionId(session.getSessionId());
		return dto;
	}

	@Override
	public void setActivebyId(int sessionId) {
		dao.get(sessionId).setActive(false);

	}

	@Override
	public void changeCurrentSession(Session session1) {
		Session session = dao.findCurrentSession();
		if (session == null) {

		} else {
			session.setCurrentSession(false);
		}
		dao.get(session1.getSessionId()).setCurrentSession(true);
	}

	@Override
	public List<SessionDTO> findAllActiveSession() {
		List<Session> sessionlist = dao.findAll(Session.class);
		List<SessionDTO> list = new ArrayList<SessionDTO>();
		for (Session session : sessionlist) {
			if (session.isActive() == true) {
				SessionDTO dto = new SessionDTO();
				dto.setSessionDuration(session.getSessionDuration());
				dto.setSessionId(session.getSessionId());
				list.add(dto);
			}
		}
		return list;
	}

	@Override
	public SessionDTO getSessionByName(String sessionName) {
		SessionDTO dto = new SessionDTO();
		Session session = dao.getSessionByName(sessionName);
		if (session != null) {
			dto.setSessionDuration(session.getSessionDuration());
			dto.setSessionId(session.getSessionId());
		}
		return dto;
	}

	@Override
	public List<SessionDTO> getToSession(int sessionId) {
		List<Session> sessionlist = dao.findAllByIsActiveTrue(Session.class);
		List<SessionDTO> list = new ArrayList<SessionDTO>();
		for (int i = (sessionId + 1); i <= sessionlist.size(); i++) {
			SessionDTO dto = new SessionDTO();
			dto.setSessionDuration(dao.get(i).getSessionDuration());
			dto.setSessionId(dao.get(i).getSessionId());
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<SessionDTO> findAllprevioussession() {
		Session current=dao.findCurrentSession();
		List<Session> list=dao.findAllByIsActiveTrue(Session.class);
		List<SessionDTO> listdto = new ArrayList<SessionDTO>();
		for(Session session :list)
		{
			if(session.getSessionId()<=current.getSessionId())
			{
			SessionDTO dto=new SessionDTO();
			dto.setSessionDuration(session.getSessionDuration());
			dto.setSessionId(session.getSessionId());
			listdto.add(dto);
			
		}
	}
		return listdto;

}
	
	}
