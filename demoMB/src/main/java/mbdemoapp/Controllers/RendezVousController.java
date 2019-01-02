package mbdemoapp.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.domain.Conseiller;
import mbdemoapp.domain.RendezVous;
import mbdemoapp.rendezVous.Services.IRendezVousService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/rendezvous")
public class RendezVousController {
	
	@Autowired
	private IRendezVousService service;
	
	
	// retrait d'une agence 
	@RequestMapping(value="/liste",method=RequestMethod.GET)
	public Page<RendezVous> listeRendezvous(
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5")int size){
		    return this.service._listerRendezVous(page, size);
		    
	}
	
	
	@RequestMapping(value="/listeRendezVousParClient",method=RequestMethod.GET)
	public Page<RendezVous> listeRendezvousParClient(
			@RequestParam(name="idClient") int idClient,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5")int size,
			@RequestParam(name="motcle",defaultValue="") String mc){
		    return this.service._listerRendezVousParClient(idClient,mc, page, size);
		    
	}
	
	
	@RequestMapping(value="/ajoutRendezVous",method=RequestMethod.POST)
	public Boolean AjouterRendezVous(
			@RequestParam(name="idClient") int idClient,
			@RequestParam(name="idConseiller") int idConseiller,
			@RequestParam(name="libelle",defaultValue="aucun motif") String libelle,
			@RequestParam(name="jour") String jour,
			@RequestParam(name="heure") String heure) throws ParseException{
		    RendezVous rendezVous=new RendezVous();
		    SimpleDateFormat sdfjour=new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat sdfheure=new SimpleDateFormat("hh:mm");
		    rendezVous.setDateRendezVous(sdfjour.parse(jour));
		    rendezVous.setHeureRendezVous(sdfheure.parse(heure));
		    rendezVous.setLibelle(libelle);
		    this.service.addRendezVousClientConseiller(rendezVous, idClient, idConseiller);
		    return true;
		    
	}
	
	@RequestMapping(value="/deleteRendezVous/{id}",method=RequestMethod.DELETE)
	public Boolean supprimerRendezVous(@PathVariable int id) {
		this.service.supprimerRendezVous(id);
		System.out.println("rendez vous supprimé");
		return true;
		
	}
	
	
	@RequestMapping(value="/listeConseillersDispo",method=RequestMethod.GET)
	public List<Conseiller> ConseillersDispo(
			@RequestParam(name="idagence") int idagence,
			@RequestParam(name="jour") String jour,
			@RequestParam(name="heure",defaultValue="00:00") String heure){
		
		
		return this.service.chercherConseillersDisponibles(idagence, jour, heure);
		
		
	}
	
	
	

}
