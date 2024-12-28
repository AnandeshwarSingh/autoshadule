package org.anand.repository;

import java.util.Properties;

import jakarta.mail.Transport;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;






public class GemailSender {
	
	public boolean sendEmail(String to, String from, String subject, String message) {
		boolean flag = false;
		//smtp properties
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		
		String username="anandeshwar.chandel@gmail.com";
		String password = "kqxm ltom azht hyik";
				
		
		//session
		Session session= Session.getInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		
		try {
			Message msg = new MimeMessage(session);
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setFrom(new InternetAddress(from));
			msg.setSubject(subject);
			msg.setText(message); 
			Transport.send(msg);
			flag = true;
			
			
		}catch(Exception e) {
			System.out.println("Exception is"+e);
		}
		
		
		return flag;
	}
}
