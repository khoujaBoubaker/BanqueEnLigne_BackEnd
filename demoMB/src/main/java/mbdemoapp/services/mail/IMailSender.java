package mbdemoapp.services.mail;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public interface IMailSender {
	
	public void sendMail();

}
