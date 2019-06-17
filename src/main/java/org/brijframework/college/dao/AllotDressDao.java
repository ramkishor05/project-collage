package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.AllotDress;

public interface AllotDressDao extends DAO<Integer, AllotDress> {

	List<AllotDress> finddetailsbyId(Integer productPurchaseId);

}
