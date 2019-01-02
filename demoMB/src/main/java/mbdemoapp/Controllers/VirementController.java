package mbdemoapp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.clients.services.IClientService;
import mbdemoapp.domain.Client;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/clients/virements")
public class VirementController {
	
	@Autowired
	private IClientService clientService;
	
	

}
