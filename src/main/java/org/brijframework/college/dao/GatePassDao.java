package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.GatePass;

public interface GatePassDao extends DAO<Integer, GatePass>{

	List<GatePass> findPreviousDetailsById(String id);

	List<GatePass> findDateWiseDetails(Date leavingDate);

}
