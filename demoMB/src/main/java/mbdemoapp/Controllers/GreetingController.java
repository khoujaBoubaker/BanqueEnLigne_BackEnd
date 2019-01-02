package mbdemoapp.Controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GreetingController {
	
	private String UPLOAD_DIR="/demoMB/src/main/resourcs/upload/";
	
	
	
	
	@RequestMapping("/greeting")
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		saveUploadFiles(file);
		}
	
	private void saveUploadFiles(MultipartFile file)
	throws IOException{
		
		byte[] bytes=file.getBytes();
		Path path=Paths.get(UPLOAD_DIR+"_we_upload_this_"+file.getOriginalFilename());
		Files.write(path, bytes);
	}

}
