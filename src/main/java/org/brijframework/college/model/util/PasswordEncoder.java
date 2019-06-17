package org.brijframework.college.model.util;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class PasswordEncoder {

	public static String getEcodedPassword(String oldpassword) {
		MessageDigestPasswordEncoder ms = new MessageDigestPasswordEncoder(
				"MD5");
		return ms.encodePassword(oldpassword, null);
	
    }
	
	public static void main(String[] args) {
		System.out.println(PasswordEncoder.getEcodedPassword("e10adc3949ba59abbe56e057f20f883e"));
	}
}