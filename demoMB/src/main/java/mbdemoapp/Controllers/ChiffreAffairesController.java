package mbdemoapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.services.BanqueMetier.IBanqueMetier;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/chiffreAffaires")
public class ChiffreAffairesController {
	
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@RequestMapping(value="/jours/{nbrejours}",method=RequestMethod.GET)
	public double chiffreBusiness(@PathVariable int nbrejours) {
		
			return this.banqueMetier.BusinessChifferAffaireSelonCriteres(nbrejours);
		
		}

}
