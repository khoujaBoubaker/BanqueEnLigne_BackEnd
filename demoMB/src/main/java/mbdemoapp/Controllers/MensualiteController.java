package mbdemoapp.Controllers;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.domain.Credit;
import mbdemoapp.domain.Mensualite;
import mbdemoapp.repo.MensualiteRepo;
import mbdemoapp.services.BanqueMetier.IBanqueMetier;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/mensualites")
public class MensualiteController {
	
	@Autowired
	private IBanqueMetier banqueMetier;
	
	  
	
	@Autowired
	private MensualiteRepo mensualiteRepo;
	
	@RequestMapping(value="/chercherMensualites/",method=RequestMethod.GET)
	public Page<Mensualite> chercherMensualites(
			@RequestParam(name="idcredit") int idcredit,
			@RequestParam(name="pageM",defaultValue="0")int page,
			@RequestParam(name="sizeM",defaultValue="5")int size){
		return this.banqueMetier.listeDesMensualites(idcredit, page, size);
		
	}
	
	@RequestMapping(value="/MettreAjourMensualite/{id}",method=RequestMethod.PUT)
	public Mensualite MettreAjourMensualite(@PathVariable int id,@RequestBody Mensualite mensualite) {
		Credit credit=this.banqueMetier.findCreditById(id);
		mensualite.setDateDePaiementEffective(new Date());
		mensualite.setCredit(credit);
		mensualite.setEtat("paye");
		
		this.mensualiteRepo.save(mensualite);
		return mensualite;
		
	}

}
