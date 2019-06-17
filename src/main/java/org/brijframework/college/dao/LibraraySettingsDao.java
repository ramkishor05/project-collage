package org.brijframework.college.dao;

import org.brijframework.college.model.LibraraySettings;

public interface LibraraySettingsDao extends DAO<Integer, LibraraySettings>{

	LibraraySettings checkData(int sessionId);

}
