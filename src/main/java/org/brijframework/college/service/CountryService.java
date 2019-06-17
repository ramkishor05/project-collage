package org.brijframework.college.service;

import org.brijframework.college.model.Country;
import org.brijframework.college.models.dto.CountryDTO;

public interface CountryService extends CRUDService<Integer, Country> {
	void addCountry(CountryDTO countryDTO);

	void updateCountry(CountryDTO countryDTO);

	CountryDTO getCountryByName(String countryName);

	boolean generateBackup(String path);

	boolean restoreBackup(String host) throws InterruptedException;
}
/*
 * Process run = Runtime .getRuntime()
 * .exec("C:/Program Files/MySQL/MySQL Server 5.5/bin//mysqldump --host=" + host
 * + " --port=" + port + " --user=" + user + " --password=" + password +
 * " --compact --databases --add-drop-table --complete-insert --extended-insert "
 * + "--skip-comments --skip-triggers " + db);
 * 
 * InputStream in = run.getInputStream(); BufferedReader br = new
 * BufferedReader(new InputStreamReader(in)); StringBuffer temp = new
 * StringBuffer(); int count;
 * 
 * char[] cbuf = new char[4096];
 * 
 * while ((count = br.read(cbuf, 0, 4096)) != -1) temp.append(cbuf, 0, count);
 * br.close(); in.close(); File file = new File("D:/schooldata.sql");
 * BufferedWriter out = new BufferedWriter(new FileWriter(file));
 * out.write(temp.toString()); out.close();
 */