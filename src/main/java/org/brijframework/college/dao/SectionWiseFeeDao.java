package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.SectionWiseFee;

public interface SectionWiseFeeDao extends DAO<Integer, SectionWiseFee> {
	List<SectionWiseFee> getSectionWiseFee(int sessionId, int classId,
			Integer[] monthId, int categoryId, int sectionId);

	public List<SectionWiseFee> getFeeAllotement(int sessionId, int classId,
			int sectionId, int monthId);

	public List<SectionWiseFee> getSectionWiseFeeAllotmentByMonth(int monthId);

	public List<SectionWiseFee> getSectionWiseFeeAllotmentBysessionId(
			int sessionId);

	List<SectionWiseFee> getSectionWiseFeeAllotmentBysectionId(int sessionId,
			int classId, int sectionId);
}
