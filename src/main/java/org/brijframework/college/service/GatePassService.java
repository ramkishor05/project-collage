package org.brijframework.college.service;

import java.text.ParseException;
import java.util.List;

import org.brijframework.college.model.GatePass;
import org.brijframework.college.models.dto.GatePassDTO;

public interface GatePassService extends CRUDService<Integer, GatePass> {

	Integer findGatePassNo();

	GatePassDTO generateGatePass(GatePassDTO gatePassDTO);

	GatePassDTO getPDFgatePass(Integer id);

	List<GatePassDTO> findPreviousDetailsbyId(String id);

	List<GatePassDTO> findDateWiseDetails(String dates) throws ParseException;

}
