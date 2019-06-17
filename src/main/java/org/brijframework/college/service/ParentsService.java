package org.brijframework.college.service;

import org.brijframework.college.model.Parents;
import org.brijframework.college.models.dto.ParentsDTO;

public interface ParentsService extends CRUDService<Integer, Parents> {

	String parentsRegistration(ParentsDTO dto) throws Exception;
}
