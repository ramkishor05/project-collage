package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.AllotDressDao;
import org.brijframework.college.model.AllotDress;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("allotDressDao")
public class AllotDressDaoImpl extends DAOImpl<Integer, AllotDress> implements AllotDressDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<AllotDress> finddetailsbyId(Integer productPurchaseId) {
		return (List<AllotDress>)sessionFactory
				.getCurrentSession()
				.createCriteria(AllotDress.class)
				.add(Restrictions.eq("uniformPurchase.productPurchaseId", productPurchaseId)).list();
	}
	

}
