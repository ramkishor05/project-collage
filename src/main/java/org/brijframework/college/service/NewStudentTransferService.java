package org.brijframework.college.service;

import org.brijframework.college.model.NewStudentTransfer;
import org.brijframework.college.models.dto.NewStudentTransferDTO;

public interface NewStudentTransferService extends CRUDService<Integer, NewStudentTransfer>{

	void saveNewStudentTransfer(NewStudentTransferDTO newStudentTransferDTO);

	NewStudentTransferDTO getneweGeneratedTC(String studentId);
	
	

}
