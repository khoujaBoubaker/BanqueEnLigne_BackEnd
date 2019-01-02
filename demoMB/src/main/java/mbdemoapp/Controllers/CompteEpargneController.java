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
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.domain.Client;
import mbdemoapp.domain.CompteCourant;
import mbdemoapp.domain.CompteEpargne;
import mbdemoapp.domain.Conseiller;
import mbdemoapp.services.compteEpargnes.ICompteEargneService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/comptesEpargnes")
public class CompteEpargneController {
	
	// service DI INJECTION
	 
	
	@Autowired
	private ICompteEargneService epargneService;
	
	@RequestMapping(value="/client/{id}",method=RequestMethod.POST)
	public CompteEpargne saveCompteEpargne(@RequestBody CompteEpargne compteEpargne,@PathVariable int id) {
		compteEpargne.setDatedecreation(new Date());
		this.epargneService.ajouterCompteEpargneToClient(id, compteEpargne);
		return compteEpargne;
		
		
	}
	
	
	@RequestMapping(value="/client/update/{id}",method=RequestMethod.POST)
	public CompteEpargne saveCompteEpargneSansNouvelleDate(@RequestBody CompteEpargne compteEpargne,@PathVariable int id) {
		System.out.println("modif en cours");
		this.epargneService.ajouterCompteEpargneToClient(id, compteEpargne);
		return compteEpargne;
		
		
	}
	
	@RequestMapping(value="/compteEpargne/delete/{id}",method=RequestMethod.DELETE)
	public Boolean supprimerCompteCourant(@PathVariable int id) {
		this.epargneService.deleteCompteEpargne(id);
		return true;
		
	}
	
	// Mise à jour
	
	@RequestMapping(value="/compteEparge/{id}",method=RequestMethod.PUT)
	public CompteEpargne update(@PathVariable int id,@RequestBody CompteEpargne compteEpargne)
	{
		// dans le client REST ARC ON DONNE LES VALEURS
		// DE LA NOUVELLE ENTITE
		// SANS L ID AVEC BODY ET METHODE POST
		compteEpargne.setIdcpt(id);
		this.epargneService.addCompteEpargne(compteEpargne);
		return compteEpargne;
		
	}
	
	
	@RequestMapping(value="/chercherCCE",method=RequestMethod.GET)
	public Page<CompteEpargne> chercherComptesCourants(@RequestParam(name="idclient") int id,
			@RequestParam(name="pageCE",defaultValue="0")int page,
			@RequestParam(name="sizeCE",defaultValue="5")int size){
            return epargneService.chercherComptesEpargnesparIdClient(id, page, size);
			}
	
	

}
