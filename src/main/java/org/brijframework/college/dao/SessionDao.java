package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.Session;

public interface SessionDao extends DAO<Integer, Session>{

	Session findCurrentSession();

	List<Session> findallsessions();
	
	Session getSessionByName(String sessionName);

}
