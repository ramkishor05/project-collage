package org.brijframework.college.service;

import java.text.ParseException;

import org.brijframework.college.model.StudentTransfer;
import org.brijframework.college.models.dto.StudentTransferDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public interface StudentTransferService extends CRUDService<Integer, StudentTransfer> {

	void saveTransferData(StudentTransferDTO studentTransferDTO) throws ParseException;

	StudentTransferDTO generateTC(String studentId);

}
