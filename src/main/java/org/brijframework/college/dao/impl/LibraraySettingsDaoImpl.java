package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.LibraraySettingsDao;
import org.brijframework.college.model.LibraraySettings;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository("LibraraySettingsDao")
public class LibraraySettingsDaoImpl extends DAOImpl<Integer, LibraraySettings>
implements LibraraySettingsDao{

	@Override
	public LibraraySettings checkData(int sessionId) {
		return (LibraraySettings) sessionFactory.getCurrentSession()
				.createCriteria(LibraraySettings.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("active", true)).uniqueResult();
				

}
}
