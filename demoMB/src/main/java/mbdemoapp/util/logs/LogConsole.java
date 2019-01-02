package mbdemoapp.util.logs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mysql.fabric.xmlrpc.base.Data;

@Component
@Qualifier("console")
@Primary
public class LogConsole implements ILogger {
	
	SimpleDateFormat dateformat=new SimpleDateFormat("dd:MM:yyyy à HH:mm:ss");

	@Override
	public void log(String message) {
		// TODO Auto-generated method stub
		System.out.printf("banque@khouja-aspect %s : %s\n",dateformat.format(new Date()),message);
		
	}

}
