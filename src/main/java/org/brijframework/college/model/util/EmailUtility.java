package org.brijframework.college.model.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * A utility class for sending e-mail message with attachment.
 * 
 * @author www.codejava.net
 * 
 */
public class EmailUtility {

	/**
	 * Sends an e-mail message from a SMTP host with a list of attached files.
	 * 
	 */
	public static void sendEmailWithAttachment(String toAddress,
			String subject, String message) throws AddressException,
			MessagingException {
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "mail.jobclix.com");
		//properties.put("mail.hdwallpaperworld.net", "mail.hdwallpaperworld.net");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", "noreply@jobclix.com");
		properties.put("mail.password", "noreply@12345jobclix");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreply@jobclix.com",
						"noreply@12345jobclix");
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress("noreply@jobclix.com",
					"Jobclix"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		// msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments
		/*
		 * if (attachedFiles != null && attachedFiles.size() > 0) { for (File
		 * aFile : attachedFiles) { MimeBodyPart attachPart = new
		 * MimeBodyPart();
		 * 
		 * try { attachPart.attachFile(aFile); } catch (IOException ex) {
		 * ex.printStackTrace(); }
		 * 
		 * multipart.addBodyPart(attachPart); } }
		 */

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);
	}
}