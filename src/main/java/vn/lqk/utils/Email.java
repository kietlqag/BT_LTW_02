package vn.lqk.utils;

import java.util.Properties;
import java.util.Random;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import vn.lqk.models.UserModel;

public class Email {

	public String getRandom() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	// Hàm gửi email
	public boolean sendEMail(UserModel user) {
		boolean test = false;
		String toEmail = user.getEmail();
		String fromEmail = "22110169@student.hcmute.edu.vn";
		String password = "123456"; 

		try {
			Properties pr = configEmail(new Properties());

			Session session = Session.getInstance(pr, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});

			Message mess = new MimeMessage(session);
			mess.setHeader("Content-Type", "text/plain; charset=UTF-8");
			mess.setFrom(new InternetAddress(fromEmail));
			mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			mess.setSubject("Confirm Code");
			mess.setText("Your code is: " + user.getCode());

			Transport.send(mess);

			test = true; 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return test;
	}

	public Properties configEmail(Properties pr) {
		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
		pr.setProperty("mail.smtp.port", "587");
		pr.setProperty("mail.smtp.auth", "true");
		pr.setProperty("mail.smtp.starttls.enable", "true");

		return pr;
	}
}
