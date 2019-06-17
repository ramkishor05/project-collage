package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.LastDate;

public interface LastDateDao extends DAO<Integer, LastDate> {

	LastDate getLastDatebyMonth(int monthId, int sessionId);

	LastDate getLastDatebyMonth(int monthId);

	List<LastDate> getLastDatesBySessionId(Integer sessionId);

}
