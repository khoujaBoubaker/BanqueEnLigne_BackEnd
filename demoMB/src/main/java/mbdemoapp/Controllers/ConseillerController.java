package mbdemoapp.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.conseillers.services.IConseillerService;
import mbdemoapp.domain.Client;
import mbdemoapp.domain.Conseiller;
import mbdemoapp.repo.ConseillerRepo;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/conseillers")
public class ConseillerController {
	
	@Autowired
	private IConseillerService conseillerService;
	
	@Autowired
	private ConseillerRepo repo;
	
	
	@RequestMapping(value="/conseiller",method=RequestMethod.POST)
	public Conseiller save(@RequestBody Conseiller conseiller)
	{   this.conseillerService.addConseiller(conseiller);
		return conseiller;
	}
	
	
	@RequestMapping(value="/conseiller/delete/{id}",method=RequestMethod.DELETE)
	public Boolean delete(@PathVariable int id)
	{
		this.conseillerService.supprimerConseiller(id);
		return true;
	}
	
	@RequestMapping(value="/conseiller/saveInAgence/{id}",method=RequestMethod.POST)
	public Conseiller addConseillerAgence(@PathVariable int id,@RequestBody Conseiller conseiller) {
		conseiller.setDateEmbauche(new Date());
		this.conseillerService.saveConseillerDansUneAgence(id, conseiller);
		return conseiller;
		
	}
	
	
	//Misa a jour

	@RequestMapping(value="/conseiller/{id}",method=RequestMethod.PUT)
	public Conseiller update(@PathVariable int id,@RequestBody Conseiller conseiller)
	{
		// dans le client REST ARC ON DONNE LES VALEURS
		// DE LA NOUVELLE ENTITE
		// SANS L ID AVEC BODY ET METHODE POST
		conseiller.setId(id);
		conseillerService.addConseiller(conseiller);
		return conseiller;
	}
	
	
	// recherche par id
	@RequestMapping(value = "/conseiller/{id}", method = RequestMethod.GET)
    public Conseiller getContact(@PathVariable int id) {
         return conseillerService.findConseillerById(id);
	}
	
	@RequestMapping(value="/chercherConseillersParAgence/{id}",method=RequestMethod.GET)
	public List<Conseiller> chercherListeConseillerParAgence(@PathVariable int id){
		return this.conseillerService.TrouverListeConseillerParAgence(id);
		
	}
	
	
	
	// recherche par nom
	
	
	@RequestMapping(value="/chercherconseillers",method=RequestMethod.GET)
	public Page<Conseiller> chercher(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return repo._chercherConseillerParMotCle("%"+mc+"%", new PageRequest(page, size));
		
	}
	
	
	
	
	// TrI pr dte embauche
	@RequestMapping(value="/chercherconseillersTri",method=RequestMethod.GET)
	public Page<Conseiller> chercherConseillersTries(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return repo._chercherConseillerParMotCleTriees("%"+mc+"%", new PageRequest(page, size));
		
	}
	
	//tri par nom
	@RequestMapping(value="/chercherconseillersTriParNom",method=RequestMethod.GET)
	public Page<Conseiller> chercherConseillersTriesParNom(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return repo._chercherConseillerParMotCleTriees("%"+mc+"%", new PageRequest(page, size));
		
	}
	
	
	@RequestMapping(value="/chercherconseillersTriParDTN",method=RequestMethod.GET)
	public Page<Conseiller> chercherConseillersTriesParDateDeNaissance(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return repo._chercherConseillerParMotCleTrieesParDateDeNaissance("%"+mc+"%", new PageRequest(page, size));
		
	}
	
	
	
	@RequestMapping(value="/chercherconseillersTriParAgence",method=RequestMethod.GET)
	public Page<Conseiller> chercherConseillersParAgence(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return repo._chercherConseillerParMotCleTrieesParDateDeNaissance("%"+mc+"%", new PageRequest(page, size));
		
	}
	
	
	@RequestMapping(value="/chercherconseillersParAgence",method=RequestMethod.GET)
	public Page<Conseiller> chercherConseillersParAgence(
			@RequestParam(name="mc") int id,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return this.conseillerService.chercherConseillersParAgence(id, page, size);
		
	}

	

	public void setConseillerService(IConseillerService conseillerService) {
		this.conseillerService = conseillerService;
	}

}
