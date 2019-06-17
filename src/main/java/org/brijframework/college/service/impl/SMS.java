package org.brijframework.college.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SMS {

	public static String sendMySMS(String toMobile, String msg) {
		//static String head = "aVi7utR6ZUkGLFkGRwXxd4wLsXz7c1QQ";
		StringBuilder output = new StringBuilder();
		try {
			String userName = "9811973900";
			String pwd = "hariom";
			//String toMobile = "9811973900";
			//String msg = "Hello+This+Is+Testing+message";
			String url = "https://site2sms.p.mashape.com/index.php?uid="
					+ userName + "&pwd=" + pwd + "&phone=" + toMobile + "&msg="
					+ msg;
			URL hp = new URL(url);
			System.out.println(url);
			URLConnection hpCon = hp.openConnection();
			hpCon.setRequestProperty("X-Mashape-Authorization",
					"aVi7utR6ZUkGLFkGRwXxd4wLsXz7c1QQ");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					hpCon.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				output.append(inputLine);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}