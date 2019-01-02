package mbdemoapp.util.logs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;



@Component
@Qualifier("file")
public class Logfile implements ILogger {
	
	SimpleDateFormat dateFormat=new SimpleDateFormat("dd:MM:yyyy à HH:mm:ss");
	
	
	PrintWriter writer;

	@Override
	public void log(String message) {
		// TODO Auto-generated method stub
		try {
			writer = new PrintWriter(new FileOutputStream(new File("log.txt"),true),true);
			
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		}
		
		writer.printf("Banque khB@ %s : %s\n", dateFormat.format(new Date()), message);
		
	}

}
