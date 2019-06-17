package org.brijframework.college.service.impl;

import java.io.IOException;

import org.brijframework.college.commom.convertor.CountryDTOToEntity;
import org.brijframework.college.dao.CountryDao;
import org.brijframework.college.model.Country;
import org.brijframework.college.models.dto.CountryDTO;
import org.brijframework.college.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("countryService")
public class CountryServiceImpl extends
		CRUDServiceImpl<Integer, Country, CountryDao> implements CountryService {
	@Autowired
	public CountryServiceImpl(CountryDao dao) {
		super(dao);
	}

	@Transactional
	public void addCountry(CountryDTO countryDTO) {
		CountryDTOToEntity countryDTOToEntity = new CountryDTOToEntity();
		dao.create(countryDTOToEntity.convertCountryFromDtoToEntity(countryDTO));

	}

	@Transactional
	public void updateCountry(CountryDTO countryDTO) {
		Country country = read(countryDTO.getId());
		country.setCountryName(countryDTO.getCountryName());
		//country.setCountryCode(countryDTO.getCountryCode());
		dao.update(country);
	}

	@Override
	public boolean generateBackup(String testpath) {
		boolean flage = true;
		
		
		try {
			//String[] command = {"cmd.exe", "/C", "Start", "D:\\export.bat"};
			String[] command = {"cmd.exe", "/C", "Start",testpath };
            Process p =  Runtime.getRuntime().exec(command);
            int processComplete = p.waitFor();
            if(processComplete == 0)
            {
             System.out.println("Restored the Backup !");
            }
            else
            {
             System.out.println("Couldn't Restore the backup !");
            }
			
		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return flage;
	}

	@Override
	public CountryDTO getCountryByName(String countryName) {
		Country country = dao.getCountryByName(countryName);
		CountryDTO countryDTO = new CountryDTO();
		if (country != null) {
			//countryDTO.setCountryCode(country.getCountryCode());
			countryDTO.setCountryName(country.getCountryName());
			countryDTO.setCreatedAt(country.getCreatedAt());
		}
		return countryDTO;
	}

	@Override
	public boolean restoreBackup(String testpath) throws InterruptedException {
boolean flage = true;
		try {
			//String[] command = {"cmd.exe", "/C", "Start", "D:\\import.bat"};
			String[] command = {"cmd.exe", "/C", "Start",testpath};
            Process p =  Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			/* System.exit(-1); */
		}
		return flage;
	}
}
