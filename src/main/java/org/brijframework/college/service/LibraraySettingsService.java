package org.brijframework.college.service;

import org.brijframework.college.model.LibraraySettings;
import org.brijframework.college.models.dto.LibraraySettingsDTO;

public interface LibraraySettingsService extends CRUDService<Integer, LibraraySettings> {

	LibraraySettingsDTO checkData(int sessionId);

	String saveLibraray(LibraraySettingsDTO libraraySettingDTO);

}
