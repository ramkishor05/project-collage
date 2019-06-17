package org.brijframework.college.dao;

import org.brijframework.college.model.Fine;

public interface FineDao extends DAO<Integer, Fine> {
	public Fine getFineByName(String Name, int sessionId);
}
