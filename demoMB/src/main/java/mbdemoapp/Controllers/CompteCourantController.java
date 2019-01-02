package mbdemoapp.Controllers;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.clients.services.IClientService;
import mbdemoapp.comptes.services.ICompteService;
import mbdemoapp.comptesCourant.Services.CompteCourantsService;
import mbdemoapp.domain.Client;
import mbdemoapp.domain.CompteCourant;
import mbdemoapp.services.BanqueMetier.IBanqueMetier;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/comptesCourants")
public class CompteCourantController {
	
	@Autowired
	private CompteCourantsService service;
	
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@Autowired
	private IClientService clientService;
	
	@RequestMapping(value="/client/{id}",method=RequestMethod.POST)
	public CompteCourant saveCompteCourant(@RequestBody CompteCourant compteCourant,@PathVariable int id) {
		//SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd");
		compteCourant.setDatedecreation(new Date());
		this.clientService.ajouterCompteCourantAunClient(id, compteCourant);
		return compteCourant;
		
	}
	
	@RequestMapping(value="/comptecourant/{id}",method=RequestMethod.PUT)
	public CompteCourant updateCompteCourant(@RequestBody CompteCourant comptecourant,@PathVariable int id) {
		comptecourant.setIdcpt(id);
		comptecourant.setNumeroCompte(comptecourant.getNumeroCompte());
		this.service.addCompteCourant(comptecourant);
		return comptecourant;
		
	}
	
	
	@RequestMapping(value="/comptecourantClient/{id}",method=RequestMethod.PUT)
	public CompteCourant updateCompteCourantdunClient(@RequestBody CompteCourant comptecourant,@PathVariable int id) {
		
		Client client=this.clientService.findClientById(id);
		comptecourant.setClient(client);
		this.service.addCompteCourant(comptecourant);
		return comptecourant;
		
	}
	
	// SUPPRESSION COMPTE COURANT
	@RequestMapping(value="/comptecourant/delete/{id}",method=RequestMethod.DELETE)
	public Boolean supprimerCompteCourant(@PathVariable int id) {
		this.service.deleteCompteCourant(id);
		return true;
		
	}
	
	
	@RequestMapping(value="/chercherCCP",method=RequestMethod.GET)
	public Page<CompteCourant> chercherComptesCourants(@RequestParam(name="idclient") int id,
			@RequestParam(name="pageCC",defaultValue="0")int page,
			@RequestParam(name="sizeCC",defaultValue="5")int size){
            return service.chercherCompteCourantsParIdClient(id, page, size);
			}
	
	
	
	@RequestMapping(value="/chercherCCPTRIESPARDECOUVERT",method=RequestMethod.GET)
	public Page<CompteCourant> chercherComptesCourantsTriesParDecouvert(@RequestParam(name="idclient") int id,
			@RequestParam(name="pageCC",defaultValue="0")int page,
			@RequestParam(name="sizeCC",defaultValue="5")int size){
            return service.chercherComptesCourantsTriesParDecouvert(id, page, size);
			}
	
	@RequestMapping(value="/chercherCCPTRIESPARSOLDE",method=RequestMethod.GET)
	public Page<CompteCourant> chercherComptesCourantsTriesParSolde(
			@RequestParam(name="idclient") int id,
			@RequestParam(name="pageCC",defaultValue="0")int page,
			@RequestParam(name="sizeCC",defaultValue="5")int size){
		return this.service.chercherComptesCourantsTriesParSoldeDec(id, page, size);
		
		
	}
	
	@RequestMapping(value="/soldeTotal",method=RequestMethod.GET)
	public double soldeTotal(@RequestParam(name="idclient") int id) {
		return this.banqueMetier.soldeTotalComptesCourants(id);
		
	}
	
	@RequestMapping(value="/soldeTotalEpargne",method=RequestMethod.GET)
	public double soldeEpargne(@RequestParam(name="idclient") int id) {
		return this.banqueMetier.soldeTotalComptesEpargnes(id);
	}
	
	
	
	

}
