package org.brijframework.college.service.impl;

import org.brijframework.college.dao.DiscountDao;
import org.brijframework.college.model.Discount;
import org.brijframework.college.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("discountService")
public class DiscountServiceImpl extends CRUDServiceImpl<Integer, Discount, DiscountDao>
implements DiscountService {
@Autowired
public DiscountServiceImpl(DiscountDao dao) {
super(dao);
}

}
