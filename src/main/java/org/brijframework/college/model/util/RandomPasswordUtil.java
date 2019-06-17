package org.brijframework.college.model.util;

import java.util.UUID;

public class RandomPasswordUtil {
	
	public static String getRandomString() {
		String num = (String) UUID.randomUUID().toString().subSequence(0, 8);
		String numm = num.replace("-", "");
		return numm;
	}

}
