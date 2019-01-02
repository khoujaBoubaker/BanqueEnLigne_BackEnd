package mbdemoapp.services.mail;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.soap.addressing.AddressingException;


@Component
public class SmtpMailSender {

	private void sendmail() throws AddressingException{
		
		
		Properties props=new Properties();
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","587");
		
	
		
		
	}

}
