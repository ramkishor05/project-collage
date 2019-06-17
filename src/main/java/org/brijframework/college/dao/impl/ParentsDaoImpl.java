package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.ParentsDao;
import org.brijframework.college.model.Parents;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("parentsDao")
public class ParentsDaoImpl extends DAOImpl<Integer, Parents> implements
		ParentsDao {

}
